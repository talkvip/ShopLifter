package edu.lmu.cs.eccms.ws.service;

/** Purpose    : The service interface for web service functionality to be
 *               retrieved by the item resource.
 *  Author     : Andrew Won
 *  Description: This file provides a service interface for a web service
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

import edu.lmu.cs.eccms.ws.domain.Item;

public interface ItemService {

    /**
     * Returns the item with the given id, or null if no such item exists.
     */
    Item getItemById(Long id);

    /**
     * Returns a paginated set of items that match the required query term,
     * skipping the first <code>skip</code> results and returning at most
     * <code>max</code> results.
     */
    List<Item> getItems(String query, Boolean active, int skip, int max);

    /**
     * Saves the given item, which should have a null id.
     */
    Item createItem(Item item);

    /**
     * Updates or saves the given item, which should have a non-null id.
     */
    void createOrUpdateItem(Item item);

}
