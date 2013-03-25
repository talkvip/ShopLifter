package edu.lmu.cs.eccms.ws.service;

import java.util.List;

import edu.lmu.cs.eccms.ws.domain.Service;

public interface ServiceService {

    /**
     * Returns the service with the given id, or null if no such service exists.
     */
    Service getServiceById(Long id);

    /**
     * Returns a paginated set of services that match the required query term,
     * skipping the first <code>skip</code> results and returning at most
     * <code>max</code> results.
     */
    List<Service> getServices(String query, Boolean active, int skip, int max);

    /**
     * Saves the given service, which should have a null id.
     */
    Service createService(Service service);

    /**
     * Updates or saves the given service, which should have a non-null id.
     */
    void createOrUpdateService(Service service);

}
