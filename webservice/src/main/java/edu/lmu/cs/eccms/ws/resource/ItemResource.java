package edu.lmu.cs.eccms.ws.resource;

/**
 * Purpose : The JAX-RS interface for operating on item resources. Author : Andrew Won Description: This file provides a
 * JAX-RS interface for a web service resource allowing web clients to interact with the web service.
 */

/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Revision History: -----------------
 *
 * Ver Date Modified by: Description of change/modification ----- ------------- ------------
 * ---------------------------------------- 1.0.0 21-April-2013 A. Won Initial version/release
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
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

import edu.lmu.cs.eccms.ws.domain.Item;
import edu.lmu.cs.eccms.ws.util.ServiceException;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ItemResource {
    /**
     * Possible resource error messages.
     */
    String ITEM_OVERSPECIFIED = "item.overspecified";
    String ITEM_INCONSISTENT = "item.inconsistent";
    String ITEM_NOT_FOUND = "item.not.found";
    String ITEM_QUERY_PARAMETERS_MISSING = "item.query.parameters.missing";

    /**
     * Returns items according to the search parameters
     *
     * @param query
     *            the query
     * @param skip
     *            the number of initial results to skip
     * @param max
     *            the maximum number of results to display
     *
     * @return the (paginated) set of items matching the query parameters
     */
    @GET
    List<Item> getItems(@QueryParam("q") String query, @QueryParam("active") Boolean active,
            @QueryParam("skip") @DefaultValue("0") int skip, @QueryParam("max") @DefaultValue("100") int max);

    /**
     * Creates a item for which the server will generate the id.
     *
     * @param item
     *            the item object to create. The item must have a null id.
     * @return A response with HTTP 201 on success, or a response with HTTP 400 and message
     *         <code>item.overspecified</code> if the item's id is not null.
     */
    @POST
    Response createItem(Item item);

    /**
     * Supposed to save the representation of the item with the given id. Inconsistent data should result in HTTP 400,
     * while a successful PUT should return Response.noContent.
     *
     * @param id
     *            the id of the item to save.
     * @return A response with HTTP 204 no content on success, or a response with HTTP 400 and message
     *         <code>item.inconsistent</code> if checked data does not have the save id as requested in the URL.
     */
    @PUT
    @Path("{id}")
    @RolesAllowed({"headmaster", "faculty", "staff"})
    Response createOrUpdateItem(@PathParam("id") Long id, Item item);

    /**
     * Returns the item with the given id.
     *
     * @param id
     *            the id of the requested item.
     * @return the item with the given id.
     * @throws ServiceException
     *             if there is no item with the given id, causing the framework to generate an HTTP 404.
     */
    @GET
    @Path("{id}")
    Item getItemById(@PathParam("id") Long id);

    /**
     * Deletes the item with the given id.
     *
     * @param id
     *            the id of the item requested to be deleted.
     * @return A response with HTTP 204 no content on success, or a response with HTTP 400 otherwise.
     * @throws ServiceException
     *             if there is no item with the given id, causing the framework to generate an HTTP 404.
     */
    @DELETE
    @Path("{id}")
    Response removeItemById(@PathParam("id") Long id);
}
