package edu.lmu.cs.eccms.ws.service;

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
