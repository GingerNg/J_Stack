package com.ginger.study.monitor.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 *
 * Metrics : 一款监控指标的度量类库
 * Metrics提供了Gauge、Counter、Meter、Histogram、Timer等度量工具类以及Health Check功能
 *
 * 应用级别的性能监控
 *
 * @source
 * https://www.cnblogs.com/nexiyi/p/metrics_sample_1.html
 *
 * Metrics Registries类似一个metrics容器，维护一个Map，可以是一个服务一个实例。
  支持五种metric类型：Gauges、Counters、Meters、Histograms和Timers。
  可以将metrics值通过JMX、Console，CSV文件和SLF4J loggers发布出来。

Metrics介绍
https://www.cnblogs.com/super-d2/p/5002061.html


VisualVM 是一个性能分析工具
jprofiler
 */
public class TestGauges {
    /**
     * 实例化一个registry，最核心的一个模块，相当于一个应用程序的metrics系统的容器，维护一个Map
     */
    private static final MetricRegistry metrics = new MetricRegistry();

    private static Queue<String> queue = new LinkedBlockingDeque<String>();

    /**
     * 在控制台上打印输出
     */
    private static ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();

    public static void main(String[] args) throws InterruptedException {
//        reporter.start(3, TimeUnit.SECONDS);

        //实例化一个Gauge
        Gauge<Integer> gauge = new Gauge<Integer>() {  // anonymous匿名内部类
            @Override
            public Integer getValue() {
                return queue.size();
            }
        };

        //注册到容器中
        metrics.register(MetricRegistry.name(TestGauges.class, "pending-job", "size"), gauge);

        //测试JMX  --> JAVA VirtualVM  VisualVM
        JmxReporter jmxReporter = JmxReporter.forRegistry(metrics).build();
        jmxReporter.start();

        //模拟数据
        for (int i=0; i<20; i++){
            queue.add("a");
            Thread.sleep(1000);
        }

    }
}
