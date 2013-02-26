package edu.lmu.cs.eccms.ws.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import edu.lmu.cs.eccms.ws.domain.Item;
import edu.lmu.cs.eccms.ws.util.DomainObjectUtils;

public class ItemDaoTest extends edu.lmu.cs.eccms.ws.util.ApplicationContextTest {

    private ItemDao itemDao;
    private Item testItem = new Item(1000000L, true, "MacBook Air", 135, "Computer thingy.", 1199.98, null, null);
    private Item itemToCreate1 = DomainObjectUtils.createItemObject(true, "Andy", 123, "This is me", 0.99);
    private Item itemToCreate2 = DomainObjectUtils.createItemObject(null, "SamV", 8675309, "What did I break?", 0.53);

    @Before
    public void getRequiredBeans() {
        itemDao = (ItemDao) applicationContext.getBean("itemDao");
    }

    @Test
    public void testCreateItem() {
        itemDao.createItem(this.itemToCreate1);
        itemDao.createItem(this.itemToCreate2);

        // The created items should have auto-generated IDs of 1 and 2, respectively.
        Assert.assertEquals(Long.valueOf(1L), this.itemToCreate1.getId());
        Assert.assertEquals(Long.valueOf(2L), this.itemToCreate2.getId());

        Item createdItem1 = itemDao.getItemById(1L);
        Item createdItem2 = itemDao.getItemById(2L);
        Assert.assertTrue(this.itemToCreate1.equals(createdItem1));
        Assert.assertTrue(this.itemToCreate2.equals(createdItem2));
    }

    @Test
    public void testGetItemById() {
        Item item = itemDao.getItemById(1000000L);

        Assert.assertTrue(this.testItem.equals(item));
    }

    @Test
    public void testGetItemsByTitle() {
        List<Item> items = itemDao.getItems("thingy", null, 0, 10);
        Assert.assertEquals(1, items.size());
        Assert.assertTrue(testItem.equals(items.get(0)));
    }

    @Test
    public void testCreateAndUpdateItem() {
        Item itemToCreate = DomainObjectUtils.createItemObject(true, "Tom", 893, "Click", 0.94);
        Item itemToReplaceWith = DomainObjectUtils.createItemObject(false, "Ray", 899, "Clack", 0.49);

        itemDao.createItem(itemToCreate);

        Long createdItemId = itemToCreate.getId();
        itemToReplaceWith.setId(createdItemId);

        // Reload the item that was just created with a new item with the same ID.
        itemDao.createOrUpdateItem(itemToReplaceWith);
        Item createdItem = itemDao.getItemById(createdItemId);

        Assert.assertTrue(itemToReplaceWith.equals(createdItem));
        Assert.assertNotSame(createdItem, itemToCreate);
    }

}
