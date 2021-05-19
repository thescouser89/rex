package org.jboss.pnc.scheduler.core.endpoints;

import io.vertx.mutiny.core.buffer.Buffer;
import io.vertx.mutiny.ext.web.client.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.jboss.pnc.scheduler.common.enums.Method;
import org.jboss.pnc.scheduler.core.GenericVertxHttpClient;
import org.jboss.pnc.scheduler.core.counter.Counter;
import org.jboss.pnc.scheduler.core.counter.Running;
import org.jboss.pnc.scheduler.dto.HeaderDTO;
import org.jboss.pnc.scheduler.dto.requests.FinishRequest;
import org.jboss.pnc.scheduler.model.Header;
import org.jboss.pnc.scheduler.model.requests.StartRequest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Path("/test")
public class HttpEndpoint {

    @Inject
    GenericVertxHttpClient client;

    @Inject
    ManagedExecutor executor;

    @Inject
    @Running
    Counter running;

    private final Queue<Long> record = new ConcurrentLinkedQueue<>();

    private boolean shouldRecord = false;

    @POST
    @Path("/accept")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response accept(String request) {
        record();
        return Response.ok().build();
    }

    @POST
    @Path("/stop")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response stop(String request) {
        record();
        return Response.ok().build();
    }

    @POST
    @Path("/acceptAndStart")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response acceptAndStart(StartRequest request) {
        record();
        executor.submit(() -> finishTask(request));
        return Response.ok("{\"task\": \"" + request.getPayload() + "\"}").build();
    }

    private void finishTask(StartRequest request) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            //ignore
        }
        FinishRequest body = new FinishRequest(true, "ALL IS OK");
        client.makeRequest(URI.create(request.getCallback()),
                Method.POST,
                List.of(Header.builder().name("Content-Type").value("application/json").build()),
                body,
                this::onResponse);
    }

    private void onResponse(HttpResponse<Buffer> response) {
        if (response.statusCode() < 400) {
            log.info("Positive response!");
        } else {
            log.warn("Negative response! " + response.body().toString());
        }
    }

    private void record() {
        if (shouldRecord) {
            record.offer(running.getValue());
        }
    }

    public void startRecordingQueue() {
        record.clear();
        shouldRecord = true;
    }

    public Collection<Long> stopRecording() {
        shouldRecord = false;
        return record;
    }
}
