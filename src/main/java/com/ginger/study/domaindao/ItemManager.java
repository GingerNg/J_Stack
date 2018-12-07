package com.ginger.study.domaindao;


import java.util.Collection;

public class ItemManager {
    private ItemDao itemDao;
    public void setItemDao(ItemDao itemDao) { this.itemDao = itemDao;}
    public Item loadItemById(Long id) {
        return itemDao.getItemById(id);
    }
    public Collection listAllItems() {
        return   itemDao.findAll();
    }

}
