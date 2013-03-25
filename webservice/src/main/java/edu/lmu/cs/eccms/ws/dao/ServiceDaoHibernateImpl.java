package edu.lmu.cs.eccms.ws.dao;

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
     */
    private QueryBuilder createServiceQuery(String query, Boolean active) {
        // The desired return order is id.
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
