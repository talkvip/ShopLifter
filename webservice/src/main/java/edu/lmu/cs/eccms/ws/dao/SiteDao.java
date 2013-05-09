package edu.lmu.cs.eccms.ws.dao;

/** Purpose    : Interface for Database Interaction through Data-Access Object
 *               for domain site EditableSite.
 *  Author     : Andrew Won
 *  Description: This file provides an interface for database access
 *               implementations which may be database specific.  The dao, or
 *               Data-Access Object is the single point where the service
 *               interacts with any database or persistent data store.
 */

import java.util.List;

import edu.lmu.cs.eccms.ws.domain.EditableSite;

public interface SiteDao {

    /**
     * Returns the site with the given id, or null if no such site exists.
     */
    EditableSite getEditableSiteById(Long id);

    /**
     * Returns a paginated set of sites that match the required query term,
     * skipping the first <code>skip</code> results and returning at most
     * <code>max</code> results.
     */
    List<EditableSite> getEditableSites(String query, int skip, int max);

    /**
     * Saves the given site, which should have a null id.
     */
    EditableSite createEditableSite(EditableSite site);

    /**
     * Updates or saves the given site, which should have a non-null id.
     */
    void createOrUpdateEditableSite(EditableSite site);

    /**
     * Deletes an site by the given site ID.
     */
    void removeEditableSiteById(Long id);

}
