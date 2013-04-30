package edu.lmu.cs.eccms.ws.dao;

/** Purpose    : Interface for Database Interaction through Data-Access Object
 *               for domain item Item.
 *  Author     : Andrew Won
 *  Description: This file provides an interface for database access
 *               implementations which may be database specific.  The dao, or
 *               Data-Access Object is the single point where the service
 *               interacts with any database or persistent data store.
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

import edu.lmu.cs.eccms.ws.domain.Item;

public interface ItemDao {

    /**
     * Returns the item with the given id, or null if no such item exists.
     */
    Item getItemById(Long id);

    /**
     * Returns a paginated set of items that match the required query term,
     * skipping the first <code>skip</code> results and returning at most
     * <code>max</code> results.
     */
    List<Item> getItems(String query, Boolean active,
            int skip, int max);

    /**
     * Saves the given item, which should have a null id.
     */
    Item createItem(Item item);

    /**
     * Updates or saves the given item, which should have a non-null id.
     */
    void createOrUpdateItem(Item item);

}
