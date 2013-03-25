package edu.lmu.cs.eccms.ws.resource;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.lmu.cs.eccms.ws.domain.Service;
import edu.lmu.cs.eccms.ws.util.ServiceException;

/**
 * The JAX-RS interface for operating on service resources.
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ServiceResource {
    /**
     * Possible resource error messages.
     */
    String SERVICE_OVERSPECIFIED = "service.overspecified";
    String SERVICE_INCONSISTENT = "service.inconsistent";
    String SERVICE_AWARDED_STATUS_INCORRECT = "service.awarded.status.incorrect";
    String SERVICE_NOT_FOUND = "service.not.found";
    String SERVICE_QUERY_PARAMETERS_MISSING = "service.query.parameters.missing";

    /**
     * Returns services according to the search parameters
     *
     * @param query
     *            the query
     * @param skip
     *            the number of initial results to skip
     * @param max
     *            the maximum number of results to display
     *
     * @return the (paginated) set of services matching the query parameters
     */
    @GET
    List<Service> getServices(@QueryParam("q") String query,
            @QueryParam("active") Boolean active, @QueryParam("skip") @DefaultValue("0") int skip,
            @QueryParam("max") @DefaultValue("100") int max);

    /**
     * Creates a service for which the server will generate the id.
     *
     * @param service
     *            the service object to create. The service must have a null id.
     * @return A response with HTTP 201 on success, or a response with HTTP 400 and message
     *         <code>service.overspecified</code> if the service's id is not null.
     */
    @POST
    Response createService(Service service);

    /**
     * Supposed to save the representation of the service with the given id. Inconsistent data should result in HTTP 400,
     * while a successful PUT should return Response.noContent.
     *
     * @param id
     *            the id of the service to save.
     * @return A response with HTTP 204 no content on success, or a response with HTTP 400 and message
     *         <code>service.inconsistent</code> if checked data does not have the save id as requested in the URL.
     */
    @PUT
    @Path("{id}")
    @RolesAllowed({"headmaster", "faculty", "staff"})
    Response createOrUpdateService(@PathParam("id") Long id, Service service);

    /**
     * Returns the service with the given id.
     *
     * @param id
     *            the id of the requested service.
     * @return the service with the given id.
     * @throws ServiceException
     *             if there is no service with the given id, causing the framework to generate an HTTP 404.
     */
    @GET
    @Path("{id}")
    Service getServiceById(@PathParam("id") Long id);
}
