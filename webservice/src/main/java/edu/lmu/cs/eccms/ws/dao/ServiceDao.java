package edu.lmu.cs.eccms.ws.dao;

/** Purpose    : Interface for Database Interaction through Data-Access Object
 *               for domain item Service.
 *  Author     : Andrew Won
 *  Description: This file provides an interface for database access
 *               implementations which may be database specific.  The dao, or
 *               Data-Access Object is the single point where the service
 *               interacts with any database or persistent data store.
 */

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History:
 *  -----------------
 *
 *   Ver      Date       Modified by:  Description of change/modification
 *  -----  -----------   ------------  ------------------------------------------
 *  1.0.0  21-April-2013  A. Won    Initial version/release
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

import java.util.List;

import edu.lmu.cs.eccms.ws.domain.Service;

public interface ServiceDao {

    /**
     * Returns the service with the given id, or null if no such service exists.
     */
    Service getServiceById(Long id);

    /**
     * Returns a paginated set of services that match the required query term,
     * skipping the first <code>skip</code> results and returning at most
     * <code>max</code> results.
     */
    List<Service> getServices(String query, Boolean active,
            int skip, int max);

    /**
     * Saves the given service, which should have a null id.
     */
    Service createService(Service service);

    /**
     * Updates or saves the given service, which should have a non-null id.
     */
    void createOrUpdateService(Service service);

}
