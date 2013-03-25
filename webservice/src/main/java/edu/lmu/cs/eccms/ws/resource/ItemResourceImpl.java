package edu.lmu.cs.eccms.ws.resource;

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import edu.lmu.cs.eccms.ws.dao.UserDao;
import edu.lmu.cs.eccms.ws.domain.Item;
import edu.lmu.cs.eccms.ws.service.ItemService;

@Path("/items")
public class ItemResourceImpl extends AbstractResource implements ItemResource {

    private ItemService itemService;

    // TODO userDao must become userService when that is available.
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

        validate(id.equals(item.getId()), Response.Status.BAD_REQUEST, ITEM_INCONSISTENT);
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

}
