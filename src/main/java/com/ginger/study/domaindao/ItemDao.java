package com.ginger.study.domaindao;

import java.util.Collection;

/**
 * ItemDao定义持久化操作的接口，用于隔离持久化代码。
 */
public interface ItemDao {
    public Item getItemById(Long id);
    public Collection findAll();
    public void updateItem(Item item);
}
