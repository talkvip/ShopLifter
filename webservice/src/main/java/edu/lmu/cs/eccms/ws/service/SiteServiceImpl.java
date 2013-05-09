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

import edu.lmu.cs.eccms.ws.dao.SiteDao;
import edu.lmu.cs.eccms.ws.domain.EditableSite;

public class SiteServiceImpl extends AbstractService implements SiteService {

    private SiteDao siteDao;

    public SiteServiceImpl(SiteDao siteDao) {
        this.siteDao = siteDao;
    }

    @Override
    public List<EditableSite> getEditableSites(String query, int skip, int max) {
        getLogger().debug("getEditableSites");
        return siteDao.getEditableSites(query, skip, max);
    }

    @Override
    public EditableSite createEditableSite(EditableSite site) {
        getLogger().debug("createEditableSite");
        return siteDao.createEditableSite(site);
    }

    @Override
    public void createOrUpdateEditableSite(EditableSite site) {
        getLogger().debug("createOrUpdateEditableSite");
        siteDao.createOrUpdateEditableSite(site);
    }

    @Override
    public EditableSite getEditableSiteById(Long id) {
        getLogger().debug("getEditableSiteById");
        return siteDao.getEditableSiteById(id);
    }

    @Override
    public void removeEditableSiteById(Long id) {
        getLogger().debug("removeEditableSiteById");
        siteDao.removeEditableSiteById(id);
    }

}
