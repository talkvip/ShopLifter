package edu.lmu.cs.eccms.ws.resource;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

import edu.lmu.cs.eccms.ws.domain.Item;
import edu.lmu.cs.eccms.ws.util.DomainObjectUtils;

/**
 * Tests the item web resource.
 */
public class ItemResourceTest extends ResourceTest {

    private Item testItem = new Item(1000000L, true, "MacBook Air", 135, "Computer thingy.", 1199.98, null, null);
    private Item itemToCreate1 = DomainObjectUtils.createItemObject(true, "Andy", 123, "This is me", 0.99);
    private Item itemToCreate2 = DomainObjectUtils.createItemObject(null, "SamV", 8675309, "What did I break?", 0.53);

    @Test
    public void testGetItemByNonexistentId() {
        ClientResponse clientResponse = wr.path("items/17").get(ClientResponse.class);
        Assert.assertEquals(204, clientResponse.getStatus());
    }
    /*
    @Test
    public void testGetItemById() {
        Item item = wr.path("items/1000000").get(ClientResponse.class).getEntity(Item.class);
        Assert.assertTrue(testItem.equals(item));
    }

    @Test
    public void testGetItemsQueryByName() {
        List<Item> items = wr.path("items").queryParam("q", "leonard")
                .get(new GenericType<List<Item>>() { });

        Assert.assertEquals(1, items.size());
    }

    @Test
    public void testGetItemsQueryByTitle() {
        List<Item> items = wr.path("items").queryParam("q", "worldwide")
                .get(new GenericType<List<Item>>() { });

        Assert.assertEquals(1, items.size());
    }

    @Test(expected=com.sun.jersey.api.client.UniformInterfaceException.class)
    public void testGetItemsByNullQuery() {
        List<Item> items = wr.path("items")
                .get(new GenericType<List<Item>>() { });

        Assert.assertEquals(1, items.size());
    }

    @Test
    public void testGetItemsByBooleanQuery() {
        List<Item> items = wr.path("items").queryParam("presented", "false")
                .get(new GenericType<List<Item>>() { });

        Assert.assertEquals(2, items.size());
    }

    @Test
    public void testGetItemsByAwardedQuery() {
        List<Item> items = wr.path("items").queryParam("awarded", "AWARDED")
                .get(new GenericType<List<Item>>() { });

        Assert.assertEquals(1, items.size());
    }

    @Test
    public void testCreateItem() {
        Item itemToCreate = DomainObjectUtils.createItemObject(1024, "Tyler Nichols", "Marc Papkyriakou");

        ClientResponse response = wr.path("items").post(ClientResponse.class, itemToCreate);
        Assert.assertEquals(201, response.getStatus());
    }

    @Test
    public void testCreateItemWithSpecifiedIdProduces400() {
        Item itemToCreate = DomainObjectUtils.createItemObject(1024, "Tyler Nichols", "Marc Papkyriakou lol");

        itemToCreate.setId(500L);

        ClientResponse response = wr.path("items").post(ClientResponse.class, itemToCreate);
        Assert.assertEquals(400, response.getStatus());
    }

    @Test
    public void testUpdateItemProduces204() {
        Item itemToUpdate = DomainObjectUtils.createItemObject(1000, "Andy Won", "Won Item");

        itemToUpdate.setId(1000000L);

        ClientResponse response = wr.path("items/1000000").put(ClientResponse.class, itemToUpdate);
        Assert.assertEquals(204, response.getStatus());
    }

    @Test
    public void testUpdateItemWithInconsistentIdProduces400() {
        Item itemToUpdate = DomainObjectUtils.createItemObject(1000, "Andy Won", "Won Item");

        ClientResponse response = wr.path("items/1000").put(ClientResponse.class, itemToUpdate);
        Assert.assertEquals(400, response.getStatus());
    }
*/
}
