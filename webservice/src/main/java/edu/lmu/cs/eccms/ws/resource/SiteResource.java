package edu.lmu.cs.eccms.ws.resource;

/**
 * Purpose : The JAX-RS interface for operating on site resources.
 * Author : Andrew Won
 * Description: This file provides a JAX-RS interface for a web service resource
 *              allowing web clients to interact with the web service.
 */

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import edu.lmu.cs.eccms.ws.domain.EditableSite;
import edu.lmu.cs.eccms.ws.util.ServiceException;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface SiteResource {
    /**
     * Possible resource error messages.
     */
    String SITE_OVERSPECIFIED = "site.overspecified";
    String SITE_INCONSISTENT = "site.inconsistent";
    String SITE_NOT_FOUND = "site.not.found";
    String SITE_QUERY_PARAMETERS_MISSING = "site.query.parameters.missing";

    /**
     * Returns sites according to the search parameters
     *
     * @param query
     *            the query
     * @param skip
     *            the number of initial results to skip
     * @param max
     *            the maximum number of results to display
     *
     * @return the (paginated) set of sites matching the query parameters
     */
    @GET
    List<EditableSite> getEditableSites(@QueryParam("q") String query,
            @QueryParam("skip") @DefaultValue("0") int skip, @QueryParam("max") @DefaultValue("100") int max);

    /**
     * Creates a site for which the server will generate the id.
     *
     * @param site
     *            the site object to create. The site must have a null id.
     * @return A response with HTTP 201 on success, or a response with HTTP 400 and message
     *         <code>site.overspecified</code> if the site's id is not null.
     */
    @POST
    Response createEditableSite(EditableSite site);

    /**
     * Supposed to save the representation of the site with the given id. Inconsistent data should result in HTTP 400,
     * while a successful PUT should return Response.noContent.
     *
     * @param id
     *            the id of the site to save.
     * @return A response with HTTP 204 no content on success, or a response with HTTP 400 and message
     *         <code>site.inconsistent</code> if checked data does not have the save id as requested in the URL.
     */
    @PUT
    @Path("{id}")
    @RolesAllowed({"headmaster", "faculty", "staff"})
    Response createOrUpdateEditableSite(@PathParam("id") Long id, EditableSite site);

    /**
     * Returns the site with the given id.
     *
     * @param id
     *            the id of the requested site.
     * @return the site with the given id.
     * @throws ServiceException
     *             if there is no site with the given id, causing the framework to generate an HTTP 404.
     */
    @GET
    @Path("{id}")
    EditableSite getEditableSiteById(@PathParam("id") Long id);

    /**
     * Deletes the site with the given id.
     *
     * @param id
     *            the id of the site requested to be deleted.
     * @return A response with HTTP 204 no content on success, or a response with HTTP 400 otherwise.
     * @throws ServiceException
     *             if there is no site with the given id, causing the framework to generate an HTTP 404.
     */
    @DELETE
    @Path("{id}")
    Response removeEditableSiteById(@PathParam("id") Long id);

    @DELETE
    Response clearEditableSite();
}
