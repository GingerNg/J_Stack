package com.ginger.study.architecture.Disturbution.Kafka;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 * Created by Ginger on 17-9-14
 * 数据分区器
 */
public class CidPartitioner implements Partitioner {
    public CidPartitioner(VerifiableProperties props) {
        //注意 ： 构造函数的函数体没有东西，但是不能没有构造函数
    }

    @Override
    public int partition(Object key, int numPartitions) {
        try {
            long partitionNum = Long.parseLong((String) key);
            long pp = Math.abs(partitionNum % numPartitions);
            System.out.println(pp);
            return (int) pp;

//            System.out.println("flag");
//            return 1;
        } catch (Exception e) {
            return Math.abs(key.hashCode() % numPartitions);
        }
    }
}
