package com.ginger.study.domaindao;

import java.util.Collection;

/**
 * ItemDaoHibernateImpl完成具体的持久化工作
 */
public class ItemDaoHibernateImpl implements ItemDao {
    public Item getItemById(Long id) {
//        return (Item) getHibernateTemplate().load(Item.class, id);
        return null;
    }
    public Collection findAll() {
//        return (List) getHibernateTemplate().find("from Item");
        return null;
    }
    public void updateItem(Item item) {
//        getHibernateTemplate().update(item);
    }
}
