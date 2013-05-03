package edu.lmu.cs.eccms.ws.dao;

/** Purpose    : Implementation for Database Interaction through Data-Access
 *               Object for domain item Item.
 *  Author     : Andrew Won
 *  Description: This file is an implementation of the ItemDao interface.  This
 *               class provides database access using HQL queries for
 *               Hibernate.  The dao, or Data-Access Object is the single point
 *               where the service interacts with any database or persistent
 *               data store.
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

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.lmu.cs.eccms.ws.dao.util.QueryBuilder;
import edu.lmu.cs.eccms.ws.domain.Item;

public class ItemDaoHibernateImpl extends HibernateDaoSupport implements ItemDao {

    @Override
    public Item getItemById(Long id) {
        return getHibernateTemplate().get(Item.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Item> getItems(String query,
            Boolean active, int skip, int max) {
        return createItemQuery(query, active)
                .build(getSession())
                .setFirstResult(skip)
                .setMaxResults(max)
                .list();
    }

    @Override
    public Item createItem(Item item) {
        getHibernateTemplate().save(item);
        return item;
    }

    @Override
    public void createOrUpdateItem(Item item) {
        getHibernateTemplate().saveOrUpdate(item);
    }

    @Override
    public void removeItemById(Long id) {
        Item item = getHibernateTemplate().get(Item.class, id);
        getHibernateTemplate().delete(item);
    }

    /**
     * Returns a base HQL query object (no pagination) for the given parameters
     * for items.
     *
     * @param query String for database query
     * @param active Flag whether item sought is active
     * @return HQL query object for given parameters
     */
    private QueryBuilder createItemQuery(String query, Boolean active) {
        QueryBuilder builder = new QueryBuilder(
            "select i from Item i",
            "order by id"
        );

        if (query != null) {
            builder.clause("lower(i.description) like lower(:query) or lower(i.name) like lower(:query)", "%" + query + "%");
        }

        if (active != null) {
            builder.clause("i.active = :active", active);
        }

        return builder;
    }

}
