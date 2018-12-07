package com.ginger.study.jdk.java.util.concurrent.FutureDemo;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ginger on 17-12-10
 *  * 给future加监听器:
 * http://blog.csdn.net/a1282379904/article/details/52335040
 */
public interface IFuture<V> extends Future<V> {
    boolean isSuccess(); // 是否成功
    V getNow(); //立即返回结果(不管Future是否处于完成状态)
    Throwable cause();  //若执行失败时的原因
    boolean isCancellable(); //是否可以取消
    IFuture<V> await() throws InterruptedException; //等待future的完成
    boolean await(long timeoutMillis) throws InterruptedException; // 超时等待future的完成
    boolean await(long timeout, TimeUnit timeunit) throws InterruptedException;
    IFuture<V> awaitUninterruptibly(); //<span style="line-height: 1.5;">等待future的完成，不响应中断</span>
    boolean awaitUninterruptibly(long timeoutMillis);//<span style="line-height: 1.5;">//超时</span><span style="line-height: 1.5;">等待future的完成，不响应中断</span>
    boolean awaitUninterruptibly(long timeout, TimeUnit timeunit);
//    IFuture<V> addListener(IFutureListener<V> l); //当future完成时，会通知这些加进来的监听器
//    IFuture<V> removeListener(IFutureListener<V> l);

}
