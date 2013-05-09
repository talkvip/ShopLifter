package edu.lmu.cs.eccms.ws.resource;

/** Purpose    : The JAX-RS implementation for the site resource.
 *  Author     : Andrew Won
 *  Description: This file provides a JAX-RS implementation for a web service
 *               resource providing web clients URI endpoints to interact
 *               with the web service.
 */

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import edu.lmu.cs.eccms.ws.dao.UserDao;
import edu.lmu.cs.eccms.ws.domain.EditableSite;
import edu.lmu.cs.eccms.ws.service.SiteService;

@Path("/sites")
public class SiteResourceImpl extends AbstractResource implements SiteResource {

    private SiteService siteService;

    public SiteResourceImpl(UserDao userDao, SiteService siteService) {
        super(userDao);
        this.siteService = siteService;
    }

    @Override
    public List<EditableSite> getEditableSites(String query, int skip, int max) {
        logServiceCall();

        validate((query != null), Response.Status.BAD_REQUEST,
                SITE_QUERY_PARAMETERS_MISSING);
        return siteService.getEditableSites(preprocessNullableQuery(query, skip, max, 0, 100),
                skip, max);
    }

    @Override
    public Response createEditableSite(EditableSite site) {
        logServiceCall();

        validate(site.getId() == null, Response.Status.BAD_REQUEST, SITE_OVERSPECIFIED);
        site = siteService.createEditableSite(site);

        return Response.created(java.net.URI.create(Long.toString(site.getId()))).build();
    }

    @Override
    public Response createOrUpdateEditableSite(Long id, EditableSite site) {
        logServiceCall();

        site.setId(id);
        // validate(id.equals(site.getId()), Response.Status.BAD_REQUEST, SITE_INCONSISTENT);
        siteService.createOrUpdateEditableSite(site);

        return Response.noContent().build();
    }

    @Override
    public EditableSite getEditableSiteById(Long id) {
        logServiceCall();

        EditableSite site = siteService.getEditableSiteById(id);
        validate(id != null, Response.Status.NOT_FOUND, SITE_NOT_FOUND);

        return site;
    }

    @Override
    public Response removeEditableSiteById(Long id) {
        logServiceCall();

        siteService.removeEditableSiteById(id);

        return Response.noContent().build();
    }

}
