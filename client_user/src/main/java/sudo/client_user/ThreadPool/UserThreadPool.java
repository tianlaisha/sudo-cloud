package sudo.client_user.ThreadPool;

import com.zaxxer.hikari.util.UtilityElf;

import java.util.concurrent.*;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/2523:04
 */
public class UserThreadPool extends ThreadPoolExecutor {

    static int corePoolSize = 5; // 线程池的基本大小
    static int maximumPoolSize = 10; // 线程池允许创建的最大线程数
    static long keepAliveTime = 1000; // 线程空闲时候保持的时间
    static TimeUnit unit = TimeUnit.NANOSECONDS; // 线程池所允许线程的空闲时间单位
    static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(2);// 线程池所使用的缓冲队列
    /**
     * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
     * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
     * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
     * ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
     */
    static RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();// 拒绝任务处理方式- 策略 可以更换其他策略
    static ThreadFactory threadFactory = Executors.defaultThreadFactory(); // 用于设置创建线程的工厂--根据自己的喜好设定名称从而容易查找日志

    // 后续参数逐渐完善
    public static UserThreadPool getUserThreadPool() {
        return new UserThreadPool(UserThreadPool.corePoolSize, UserThreadPool.maximumPoolSize, UserThreadPool.keepAliveTime, UserThreadPool.unit,
                UserThreadPool.workQueue, UserThreadPool.threadFactory, UserThreadPool.handler);
    }

    public UserThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public UserThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public UserThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public UserThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }
}
