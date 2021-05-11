package org.jboss.pnc.scheduler.facade.api;

import org.jboss.pnc.scheduler.dto.TaskDTO;
import org.jboss.pnc.scheduler.dto.requests.CreateGraphRequest;

import java.util.List;
import java.util.Set;

public interface TaskProvider {

    //todo document
    Set<TaskDTO> create(CreateGraphRequest request);
    /**
     * returns all services based on filter
     *
     * @return set of services
     */
    List<TaskDTO> getAll(boolean waiting, boolean running, boolean finished);

    /**
     * Cancels execution of the service and its dependants
     *
     * @param serviceName existing service
     */
    void cancel(String serviceName);

    /**
     * Returns existing service based on param
     *
     * @param serviceName name of existing service
     * @return service entity
     */
    TaskDTO get(String serviceName);

    /**
     * Returns all related services
     * (all dependants, all dependencies, dependants of dependencies, dependencies of dependants)
     *
     * @param serviceName name of existing service
     * @return set of related services
     */
    List<TaskDTO> getAllRelated(String serviceName);

    /**
     * Used for communication with remote entity. Invoked by remote entity by provided callback.
     *
     * @param positive callback is positive or negative
     *          true == remote entity responds that it has finished execution of the service
     *          false == remote entity responds that the service has failed its execution
     */
    void acceptRemoteResponse(String serviceName, boolean positive, Object response);
}
