package edu.lmu.cs.eccms.ws.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import edu.lmu.cs.eccms.ws.domain.Item;
import edu.lmu.cs.eccms.ws.util.ApplicationContextTest;
import edu.lmu.cs.eccms.ws.util.DomainObjectUtils;

public class ItemDaoTest extends edu.lmu.cs.eccms.ws.util.ApplicationContextTest {

    private ItemDao itemDao;

    @Before
    public void getRequiredBeans() {
        itemDao = (ItemDao) applicationContext.getBean("itemDao");
    }
/*
    @Test
    public void testGetItemById() {
        // Grab the known event in the fixture.
        Item item = itemDao.getItemById(1000000L);

        Assert.assertEquals(Long.valueOf(1000000L), item.getId());
        Assert.assertEquals(10000, item.getAmount().intValue());
        Assert.assertEquals("Leonard Kleinrock", item.getFacultyMentor());
        Assert.assertEquals("The Worldwide Web", item.getTitle());
    }

    @Test
    public void testGetItemsByTitle() {
        List<Item> items = itemDao.getItems("The world", null, null, 0, 10);
        Assert.assertEquals(1, items.size());
        Assert.assertEquals(Long.valueOf(1000000L), items.get(0).getId());
    }
*/
    @Test
    public void testCreateItem() {
        // Create an id-less item.
        Item itemToCreate = new Item(true, "Andy", 123, "This is me", 0.99);
        //DomainObjectUtils.createItemObject(25000, "Reading", "Rainbow");
        itemDao.createItem(itemToCreate);

        // The created item should now have an ID of 1 because there is
        // nothing else in the text fixture.
        Assert.assertEquals(Long.valueOf(1L), itemToCreate.getId());

        // Reload the item that was just created.
        Item createdItem = itemDao.getItemById(1L);
        Assert.assertEquals(itemToCreate.getName(), createdItem.getName());
        //assertSimpleEquality(itemToCreate, createdItem);
        //Assert.assertEquals()
    }
/*
    @Test
    public void testCreateAndUpdateItem() {
        // Create an id-less item.
        Item itemToCreate = DomainObjectUtils.createItemObject(25000, "Reading", "Rainbow");
        Item itemToReplaceWith = DomainObjectUtils.createItemObject(12500, "Watching TV", "Monotone");

        itemDao.createItem(itemToCreate);

        // Keep the ID of the created item to make sure it does not change when updated.
        Long createdItemId = itemToCreate.getId();
        itemToReplaceWith.setId(createdItemId);

        // Reload the item that was just created with a new item with the same ID.
        itemDao.createOrUpdateItem(itemToReplaceWith);
        Item createdItem = itemDao.getItemById(createdItemId);

        assertSimpleEquality(itemToReplaceWith, createdItem);
        Assert.assertNotSame(createdItem.getFacultyMentor(), itemToCreate.getFacultyMentor());
        Assert.assertNotSame(createdItem.getTitle(), itemToCreate.getTitle());
    }

    /**
     * Helper function for asserting the equality of two items.
     *
    private void assertSimpleEquality(Item item1, Item item2) {
        Assert.assertEquals(item1.getId(), item2.getId());
        Assert.assertEquals(item1.getAmount(), item2.getAmount());
        Assert.assertEquals(item1.getTitle(), item2.getTitle());
        Assert.assertEquals(item1.getFacultyMentor(), item2.getFacultyMentor());
    }*/
}
