/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2021-2021 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.pnc.rex.api;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.pnc.rex.api.openapi.OpenapiConstants;
import org.jboss.pnc.rex.dto.requests.FinishRequest;
import org.jboss.pnc.rex.dto.responses.ErrorResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Tag(name = "Callback endpoint")
@Path("/rest/callback")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface CallbackEndpoint {

    @Path("/{taskName}/finish")
    @Operation(summary = "[ADMIN] Used by remote entity to report Task completion.")
    @APIResponses(value = {
            @APIResponse(responseCode = OpenapiConstants.SUCCESS_CODE, description = OpenapiConstants.SUCCESS_DESCRIPTION),
            @APIResponse(responseCode = OpenapiConstants.INVALID_CODE, description = OpenapiConstants.INVALID_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @APIResponse(responseCode = OpenapiConstants.SERVER_ERROR_CODE, description = OpenapiConstants.SERVER_ERROR_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @POST
    @Deprecated
    void finish(@PathParam("taskName") @NotEmpty String taskName, @Valid @NotNull FinishRequest result);

    @Path("/{taskName}/succeed")
    @Operation(summary = "[ADMIN] Used by remote entity to report successful Task completion.")
    @APIResponses(value = {
            @APIResponse(responseCode = OpenapiConstants.SUCCESS_CODE, description = OpenapiConstants.SUCCESS_DESCRIPTION),
            @APIResponse(responseCode = OpenapiConstants.INVALID_CODE, description = OpenapiConstants.INVALID_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @APIResponse(responseCode = OpenapiConstants.SERVER_ERROR_CODE, description = OpenapiConstants.SERVER_ERROR_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @POST
    void succeed(@PathParam("taskName") @NotEmpty String taskName, @Valid @NotNull Object result);

    @Path("/{taskName}/fail")
    @Operation(summary = "[ADMIN] Used by remote entity to report failed Task completion.")
    @APIResponses(value = {
            @APIResponse(responseCode = OpenapiConstants.SUCCESS_CODE, description = OpenapiConstants.SUCCESS_DESCRIPTION),
            @APIResponse(responseCode = OpenapiConstants.INVALID_CODE, description = OpenapiConstants.INVALID_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @APIResponse(responseCode = OpenapiConstants.SERVER_ERROR_CODE, description = OpenapiConstants.SERVER_ERROR_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @POST
    void fail(@PathParam("taskName") @NotEmpty String taskName, @Valid @NotNull Object result);
}
