# springboot-async
springBoot异步调用方法
#注意
在默认情况下，未设置TaskExecutor时，默认是使用SimpleAsyncTaskExecutor这个线程池，但此线程不是真正意义上
的线程池，因为线程不重用，每次调用都会创建一个新的线程。可通过控制台日志输出可以看出，每次输出线程名都是递增的。
调用的异步方法，不能为同一个类的方法，简单来说，因为Spring在启动扫描时会为其创建一个代理类，而同类调用时，
还是调用本身的代理类的，所以和平常调用是一样的。其他的注解如@Cache等也是一样的道理，说白了，就是Spring的
代理机制造成的。


corePoolSize：线程池维护线程的最少数量
keepAliveSeconds：允许的空闲时间,当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
maxPoolSize：线程池维护线程的最大数量,只有在缓冲队列满了之后才会申请超过核心线程数的线程
queueCapacity：缓存队列
rejectedExecutionHandler：线程池对拒绝任务（无线程可用）的处理策略。这里采用了CallerRunsPolicy策略，当线
程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务。
还有一个是AbortPolicy策略：处理程序遭到拒绝将抛出运行时RejectedExecutionException。

而在一些场景下，若需要在关闭线程池时等待当前调度任务完成后才开始关闭，可以通过简单的配置，进行优雅的停机策略配置。关键就是通过setWaitForTasksToCompleteOnShutdown(true)和setAwaitTerminationSeconds方法。

setWaitForTasksToCompleteOnShutdown:表明等待所有线程执行完，默认为false。
setAwaitTerminationSeconds:等待的时间，因为不能无限的等待下去。

#业务
异步方式在系统中有很多的应用，比如说，一个业务完成了核心的操作之后，一些附加的操作，可以采用异步方式去实现，
这样可以使接口方法更快的返回。这里是要区分开那些是核心业务，哪些是附加业务，在保证核心执行的情况下，
异步去调用附加业务。
