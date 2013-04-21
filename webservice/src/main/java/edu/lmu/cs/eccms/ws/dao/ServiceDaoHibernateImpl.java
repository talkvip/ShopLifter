package edu.lmu.cs.eccms.ws.dao;

/** Purpose    : Implementation for Database Interaction through Data-Access
 *               Object for domain item Service.
 *  Author     : Andrew Won
 *  Description: This file is an implementation of the ServiceDao interface.
 *               This class provides database access using HQL queries for
 *               Hibernate.  The dao, or Data-Access Object is the single point
 *               where the service interacts with any database or persistent
 *               data store.
 */

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History:
 *  -----------------
 *
 *   Ver      Date       Modified by:  Description of change/modification
 *  -----  -----------   ------------  ------------------------------------------
 *  1.0.0  21-April-2013  A. Won    Initial version/release
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.lmu.cs.eccms.ws.dao.util.QueryBuilder;
import edu.lmu.cs.eccms.ws.domain.Service;

public class ServiceDaoHibernateImpl extends HibernateDaoSupport implements ServiceDao {

    @Override
    public Service getServiceById(Long id) {
        return getHibernateTemplate().get(Service.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Service> getServices(String query,
            Boolean active, int skip, int max) {
        return createServiceQuery(query, active)
                .build(getSession())
                .setFirstResult(skip)
                .setMaxResults(max)
                .list();
    }

    @Override
    public Service createService(Service service) {
        getHibernateTemplate().save(service);
        return service;
    }

    @Override
    public void createOrUpdateService(Service service) {
        getHibernateTemplate().saveOrUpdate(service);
    }

    /**
     * Returns a base HQL query object (no pagination) for the given parameters
     * for services.
     *
     * @param query String for database query
     * @param active Flag whether service sought is active
     * @return HQL query object for given parameters
     */
    private QueryBuilder createServiceQuery(String query, Boolean active) {
        QueryBuilder builder = new QueryBuilder(
            "select s from Service s",
            "order by id"
        );

        if (query != null) {
            builder.clause("lower(s.description) like lower(:query) or lower(s.name) like lower(:query)", "%" + query + "%");
        }

        if (active != null) {
            builder.clause("s.active = :active", active);
        }

        return builder;
    }

}
