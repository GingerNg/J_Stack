package com.ginger.study.architecture.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Ginger on 18-1-9
 * Java实现缓存(LRU,FIFO)
 * https://www.cnblogs.com/liuyang0/p/6664586.html
 *
 * 在Java中不只是这两种数据结构可以实现缓存，比如ConcurrentHashMap、WeakHashMap在某些场景下也是可以作为缓存的
 */
public class LRULinkedHashMap<K, V> {
    private final int MAX_CACHE_SIZE;
    private final float DEFAULT_LOAD_FACTORY = 0.75f;

    LinkedHashMap<K, V> map;

    public LRULinkedHashMap(int cacheSize) {
        MAX_CACHE_SIZE = cacheSize;
        int capacity = (int)Math.ceil(MAX_CACHE_SIZE / DEFAULT_LOAD_FACTORY) + 1;  //防止LinkedHashMap自动扩容
        /*
         * 第三个参数设置为true，代表linkedlist按访问顺序排序，可作为LRU缓存
         * 第三个参数设置为false，代表按插入顺序排序，可作为FIFO缓存
         */
        map = new LinkedHashMap<K, V>(capacity, DEFAULT_LOAD_FACTORY, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {  //重写这个方法来控制缓存元素的删除，当缓存满了后，就可以通过返回true删除最久未被使用的元素，达到LRU的要求
                return size() > MAX_CACHE_SIZE;
            }
        };
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    public synchronized V get(K key) {
        return map.get(key);
    }

    public synchronized void remove(K key) {
        map.remove(key);
    }

    public synchronized Set<Map.Entry<K, V>> getAll() {
        return map.entrySet();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            stringBuilder.append(String.format("%s: %s  ", entry.getKey(), entry.getValue()));
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LRULinkedHashMap<Integer, Integer> lru1 = new LRULinkedHashMap<>(5);
        lru1.put(1, 1);
        lru1.put(2, 2);
        lru1.put(3, 3);
        System.out.println(lru1);
        lru1.get(1);
        System.out.println(lru1);
        lru1.put(4, 4);
        lru1.put(5, 5);
        lru1.put(6, 6);
        System.out.println(lru1);
    }
}
