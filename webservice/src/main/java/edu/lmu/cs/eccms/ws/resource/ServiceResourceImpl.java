package edu.lmu.cs.eccms.ws.resource;

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import edu.lmu.cs.eccms.ws.dao.UserDao;
import edu.lmu.cs.eccms.ws.domain.Service;
import edu.lmu.cs.eccms.ws.service.ServiceService;

@Path("/services")
public class ServiceResourceImpl extends AbstractResource implements ServiceResource {

    private ServiceService serviceService;

    // TODO userDao must become userService when that is available.
    public ServiceResourceImpl(UserDao userDao, ServiceService serviceService) {
        super(userDao);
        this.serviceService = serviceService;
    }

    @Override
    public List<Service> getServices(String query, Boolean active, int skip, int max) {
        logServiceCall();

        validate((query != null || active != null), Response.Status.BAD_REQUEST,
                SERVICE_QUERY_PARAMETERS_MISSING);
        return serviceService.getServices(preprocessNullableQuery(query, skip, max, 0, 100), active,
                skip, max);
    }

    @Override
    public Response createService(Service service) {
        logServiceCall();

        validate(service.getId() == null, Response.Status.BAD_REQUEST, SERVICE_OVERSPECIFIED);
        service = serviceService.createService(service);

        return Response.created(java.net.URI.create(Long.toString(service.getId()))).build();
    }

    @Override
    public Response createOrUpdateService(Long id, Service service) {
        logServiceCall();

        validate(id.equals(service.getId()), Response.Status.BAD_REQUEST, SERVICE_INCONSISTENT);
        serviceService.createOrUpdateService(service);

        return Response.noContent().build();
    }

    @Override
    public Service getServiceById(Long id) {
        logServiceCall();

        Service service = serviceService.getServiceById(id);
        validate(id != null, Response.Status.NOT_FOUND, SERVICE_NOT_FOUND);

        return service;
    }

}
