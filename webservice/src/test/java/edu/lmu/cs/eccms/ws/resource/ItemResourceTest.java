package edu.lmu.cs.eccms.ws.resource;

import java.util.List;

import junit.framework.Assert;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
    private Item itemToCreate = DomainObjectUtils.createItemObject(false, "Big Bad Box", 134, "8gb memory and more!",
            399.99);
    private Item itemToUpdate = DomainObjectUtils.createItemObject(false, "Big Bad Box", 134, "8gb memory and more!",
            399.99);

    @Test
    public void testGetItemByNonexistentId() {
        ClientResponse clientResponse = wr.path("items/17").get(ClientResponse.class);
        Assert.assertEquals(204, clientResponse.getStatus());
    }

    @Test
    public void testGetItemById() {
        Item item = wr.path("items/1000000").get(ClientResponse.class).getEntity(Item.class);
        assertThat(item, is(testItem));
    }

    @Test
    public void testGetItemsQueryByName() {
        List<Item> items = wr.path("items").queryParam("q", "Air").get(new GenericType<List<Item>>() {
        });

        Assert.assertEquals(1, items.size());
    }

    @Test
    public void testGetItemsQueryByDescription() {
        List<Item> items = wr.path("items").queryParam("q", "thingy").get(new GenericType<List<Item>>() {
        });

        Assert.assertEquals(1, items.size());
    }

    @Test(expected = com.sun.jersey.api.client.UniformInterfaceException.class)
    public void testGetItemsByNullQuery() {
        List<Item> items = wr.path("items").get(new GenericType<List<Item>>() {
        });

        Assert.assertEquals(1, items.size());
    }

    @Test
    public void testGetItemsQueryByNonExistingName() {
        List<Item> items = wr.path("items").queryParam("q", "Dell").get(new GenericType<List<Item>>() {
        });

        Assert.assertEquals(0, items.size());
    }

    @Test
    public void testGetItemsByActiveQuery() {
        List<Item> items = wr.path("items").queryParam("active", "true").get(new GenericType<List<Item>>() {
        });

        Assert.assertEquals(1, items.size());
    }

    @Test
    public void testCreateItem() {
        ClientResponse response = wr.path("items").post(ClientResponse.class, itemToCreate);
        Assert.assertEquals(201, response.getStatus());
    }

    @Test
    public void testCreateItemWithSpecifiedIdProduces400() {
        itemToCreate.setId(500L);

        ClientResponse response = wr.path("items").post(ClientResponse.class, itemToCreate);
        Assert.assertEquals(400, response.getStatus());
    }

    @Test
    public void testUpdateItemProduces204() {
        itemToUpdate.setId(1000000L);

        ClientResponse response = wr.path("items/1000000").put(ClientResponse.class, itemToUpdate);
        Assert.assertEquals(204, response.getStatus());

        Item updatedItem = wr.path("items/1000000").get(ClientResponse.class).getEntity(Item.class);
        assertThat(updatedItem, is(itemToUpdate));
    }

    @Test
    public void testUpdateItemWithInconsistentIdProduces400() {
        ClientResponse response = wr.path("items/1000").put(ClientResponse.class, itemToUpdate);
        Assert.assertEquals(400, response.getStatus());
    }
}
