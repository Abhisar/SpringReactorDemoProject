package sample.testReactor;

import static reactor.bus.selector.Selectors.$;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import reactor.Environment;
import reactor.bus.EventBus;
import reactor.jarjar.com.lmax.disruptor.YieldingWaitStrategy;
import reactor.jarjar.com.lmax.disruptor.dsl.ProducerType;
import reactor.spring.core.task.WorkQueueAsyncTaskExecutor;
/**
 * Hello world!
 *
 */
public class App  implements CommandLineRunner
{
	
	public void run(String... args) throws Exception {
        
		//Task Scheduler
	    WorkQueueAsyncTaskExecutor workQueueAsyncTaskExecutor= new WorkQueueAsyncTaskExecutor(new Environment());
	    workQueueAsyncTaskExecutor.setWaitStrategy(new YieldingWaitStrategy());
	    workQueueAsyncTaskExecutor.setProducerType(ProducerType.MULTI);
	    workQueueAsyncTaskExecutor.setThreads(2); // Specify the number of thread to execute task in parallel
	    workQueueAsyncTaskExecutor.setBacklog(64); // to set the number of pre-allocated tasks to keep in memory.
	    workQueueAsyncTaskExecutor.afterPropertiesSet();
//	    TaskItem task = null;
//	    for(int i =0;i<10;i++) {
//	        task = new TaskItem();
//	        task.setNum(i);
//	        //ScheduledFuture   futureTask = workQueueAsyncTaskExecutor.scheduleAtFixedRate(task, 0	, TimeUnit.MILLISECONDS.convert(1, TimeUnit.MINUTES), TimeUnit.MILLISECONDS);
//	      /*  ScheduledFuture   futureTask */ workQueueAsyncTaskExecutor.execute(task);
//	                 
//	    }
	    //Creating environement
	    Environment env =  Environment.initializeIfEmpty().assignErrorJournal();
	    
	    //Creating event BUS
	    EventBus evtBus =  	EventBus.create(env, Environment.THREAD_POOL);
	    
	    
	    //Registering Events
	    evtBus.on($("running"), new Handler_Running(evtBus));
	    evtBus.on($("completed"), new Handler_Completed(evtBus));
	    
	    //Crating task and attaching them to same EVENT BUS 
	    //All tasks will be handled by one EventBus
	    StatefulTasks S1 = new StatefulTasks(evtBus);
	    S1.setName(": TASK 1 :");
	    StatefulTasks S2 = new StatefulTasks(evtBus);
	    S2.setName(": TASK 2 :");
	    workQueueAsyncTaskExecutor.execute(S1);
	    workQueueAsyncTaskExecutor.execute(S2);
	         
	}
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ApplicationContext app = SpringApplication.run(App.class, args);
        while(true) {
        	
        }
    }
}
