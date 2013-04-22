package edu.lmu.cs.eccms.ws.service;

/** Purpose    : The service implementation for the currently configured web
 *               service functionality.
 *  Author     : Andrew Won
 *  Description: This file provides a service implementation for the web service
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

import edu.lmu.cs.eccms.ws.dao.ServiceDao;
import edu.lmu.cs.eccms.ws.domain.Service;

public class ServiceServiceImpl extends AbstractService implements ServiceService {

    private ServiceDao serviceDao;

    public ServiceServiceImpl(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    @Override
    public List<Service> getServices(String query, Boolean active, int skip, int max) {
        getLogger().debug("getServices");
        return serviceDao.getServices(query, active, skip, max);
    }

    @Override
    public Service createService(Service service) {
        getLogger().debug("createService");
        return serviceDao.createService(service);
    }

    @Override
    public void createOrUpdateService(Service service) {
        getLogger().debug("createOrUpdateService");
        serviceDao.createOrUpdateService(service);
    }

    @Override
    public Service getServiceById(Long id) {
        getLogger().debug("getServiceById");
        return serviceDao.getServiceById(id);
    }

}
