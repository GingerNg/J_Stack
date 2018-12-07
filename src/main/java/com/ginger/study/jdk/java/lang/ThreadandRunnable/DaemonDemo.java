package com.ginger.study.jdk.java.lang.ThreadandRunnable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Ginger on 18-1-12
 * Java 守护线程(服务线程) 优先级低
 * https://www.cnblogs.com/super-d2/p/3348183.html
 * 只要当前JVM实例中尚存在任何一个非守护线程没有结束，守护线程就全部工作；只有当最后一个非守护线程结束时，守护线程随着JVM一同结束工作。
 * http://blog.csdn.net/shimiso/article/details/8964414
 *
 *比较全面的Java线程知识点梳理
 * http://uule.iteye.com/blog/1101187
 */
public class DaemonDemo {

    public static void main(String[] args) throws InterruptedException

    {

        Runnable tr = new TestRunnable();

        Thread thread = new Thread(tr);

        thread.setDaemon(true); //设置守护线程

        thread.start(); //开始执行分进程

    }
}

class TestRunnable implements Runnable {

    public void run() {

        try {

            Thread.sleep(1000);//守护线程阻塞1秒后运行

//            不是所有的应用都可以分配给Daemon线程来进行服务，比如读写操作或者计算逻辑。因为在Daemon Thread还没来的及进行操作时，虚拟机可能已经退出了。
            File f = new File("daemon.txt");

            FileOutputStream os = new FileOutputStream(f, true);

            os.write("daemon".getBytes());

        } catch (IOException e1) {

            e1.printStackTrace();

        } catch (InterruptedException e2) {

            e2.printStackTrace();

        }

    }

}
