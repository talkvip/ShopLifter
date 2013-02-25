package edu.lmu.cs.eccms.ws.dao;

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

    /**
     * Returns a base HQL query object (no pagination) for the given parameters
     * for items.
     */
    private QueryBuilder createItemQuery(String query, Boolean active) {
        // The desired return order is id.
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
