package edu.lmu.cs.eccms.ws.service;

/** Purpose    : The service interface for web service functionality to be
 *               retrieved by the site resource.
 *  Author     : Andrew Won
 *  Description: This file provides a service interface for a web service
 *               resource allowing the resource to interact with the rest of the
 *               web service.
 */

import java.util.List;

import edu.lmu.cs.eccms.ws.domain.EditableSite;

public interface SiteService {

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

    void clearEditableSite();
}
