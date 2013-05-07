package edu.lmu.cs.eccms.ws.resource;

/** Purpose    : The JAX-RS implementation for the item resource.
 *  Author     : Andrew Won
 *  Description: This file provides a JAX-RS implementation for a web service
 *               resource providing web clients URI endpoints to interact
 *               with the web service.
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

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import edu.lmu.cs.eccms.ws.dao.UserDao;
import edu.lmu.cs.eccms.ws.domain.Item;
import edu.lmu.cs.eccms.ws.service.ItemService;

@Path("/items")
public class ItemResourceImpl extends AbstractResource implements ItemResource {

    private ItemService itemService;

    public ItemResourceImpl(UserDao userDao, ItemService itemService) {
        super(userDao);
        this.itemService = itemService;
    }

    @Override
    public List<Item> getItems(String query, Boolean active, int skip, int max) {
        logServiceCall();

        validate((query != null || active != null), Response.Status.BAD_REQUEST,
                ITEM_QUERY_PARAMETERS_MISSING);
        return itemService.getItems(preprocessNullableQuery(query, skip, max, 0, 100), active,
                skip, max);
    }

    @Override
    public Response createItem(Item item) {
        logServiceCall();

        validate(item.getId() == null, Response.Status.BAD_REQUEST, ITEM_OVERSPECIFIED);
        item = itemService.createItem(item);

        return Response.created(java.net.URI.create(Long.toString(item.getId()))).build();
    }

    @Override
    public Response createOrUpdateItem(Long id, Item item) {
        logServiceCall();

        // TODO: Enable location header on response to client so we can verify id
        item.setId(id);
        // validate(id.equals(item.getId()), Response.Status.BAD_REQUEST, ITEM_INCONSISTENT);
        itemService.createOrUpdateItem(item);

        return Response.noContent().build();
    }

    @Override
    public Item getItemById(Long id) {
        logServiceCall();

        Item item = itemService.getItemById(id);
        validate(id != null, Response.Status.NOT_FOUND, ITEM_NOT_FOUND);

        return item;
    }

    @Override
    public Response removeItemById(Long id) {
        logServiceCall();

        itemService.removeItemById(id);

        return Response.noContent().build();
    }

}
