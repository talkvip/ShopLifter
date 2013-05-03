package edu.lmu.cs.eccms.ws.service;

/** Purpose    : The service implementation for the currently configured web
 *               service functionality.
 *  Author     : Andrew Won
 *  Description: This file provides a service implementation for the web service
 *               resource allowing the resource to interact with the rest of the
 *               web service.
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

import edu.lmu.cs.eccms.ws.dao.ItemDao;
import edu.lmu.cs.eccms.ws.domain.Item;

public class ItemServiceImpl extends AbstractService implements ItemService {

    private ItemDao itemDao;

    public ItemServiceImpl(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public List<Item> getItems(String query, Boolean active, int skip, int max) {
        getLogger().debug("getItems");
        return itemDao.getItems(query, active, skip, max);
    }

    @Override
    public Item createItem(Item item) {
        getLogger().debug("createItem");
        return itemDao.createItem(item);
    }

    @Override
    public void createOrUpdateItem(Item item) {
        getLogger().debug("createOrUpdateItem");
        itemDao.createOrUpdateItem(item);
    }

    @Override
    public Item getItemById(Long id) {
        getLogger().debug("getItemById");
        return itemDao.getItemById(id);
    }

    @Override
    public void removeItemById(Long id) {
        getLogger().debug("removeItemById");
        itemDao.removeItemById(id);
    }

}
