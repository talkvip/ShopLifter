package edu.lmu.cs.eccms.ws.dao;

/** Purpose    : Implementation for Database Interaction through Data-Access
 *               Object for domain site EditableSite.
 *  Author     : Andrew Won
 *  Description: This file is an implementation of the EditableSiteDao interface.  This
 *               class provides database access using HQL queries for
 *               Hibernate.  The dao, or Data-Access Object is the single point
 *               where the service interacts with any database or persistent
 *               data store.
 */

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.lmu.cs.eccms.ws.dao.util.QueryBuilder;
import edu.lmu.cs.eccms.ws.domain.EditableSite;

public class SiteDaoHibernateImpl extends HibernateDaoSupport implements SiteDao {

    @Override
    public EditableSite getEditableSiteById(Long id) {
        return getHibernateTemplate().get(EditableSite.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<EditableSite> getEditableSites(String query, int skip, int max) {
        return createEditableSiteQuery(query)
                .build(getSession())
                .setFirstResult(skip)
                .setMaxResults(max)
                .list();
    }

    @Override
    public EditableSite createEditableSite(EditableSite site) {
        getHibernateTemplate().save(site);
        return site;
    }

    @Override
    public void createOrUpdateEditableSite(EditableSite site) {
        getHibernateTemplate().saveOrUpdate(site);
    }

    @Override
    public void clearEditableSite() {
        getHibernateTemplate().deleteAll(getHibernateTemplate().loadAll(EditableSite.class));
    }

    @Override
    public void removeEditableSiteById(Long id) {
        EditableSite site = getHibernateTemplate().get(EditableSite.class, id);
        getHibernateTemplate().delete(site);
    }

    /**
     * Returns a base HQL query object (no pagination) for the given parameters
     * for sites.
     *
     * @param query String for database query
     * @param active Flag whether site sought is active
     * @return HQL query object for given parameters
     */
    private QueryBuilder createEditableSiteQuery(String query) {
        QueryBuilder builder = new QueryBuilder(
            "select i from EditableSite i",
            "order by id"
        );

        if (query != null) {
            builder.clause("lower(i.name) like lower(:query) or lower(i.data) like lower(:query)", "%" + query + "%");
        }

        return builder;
    }

}
