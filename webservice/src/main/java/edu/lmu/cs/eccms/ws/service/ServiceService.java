package edu.lmu.cs.eccms.ws.service;

/** Purpose    : The service interface for web service functionality to be
 *               retrieved by the service resource.
 *  Author     : Andrew Won
 *  Description: This file provides a service interface for a web service
 *               resource allowing the resource to interact with the rest of the
 *               web service.
 */

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History:
 *  -----------------
 *
 *   Ver      Date        Modified by:  Description of change/modification
 *  -----  -------------  ------------  ----------------------------------------
 *  1.0.0  21-April-2013  A. Won        Initial version/release
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

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
