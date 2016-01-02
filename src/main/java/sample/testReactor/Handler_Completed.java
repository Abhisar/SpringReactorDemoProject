package sample.testReactor;

import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.fn.Consumer;

public class Handler_Completed implements Consumer<Event<StatefulTasks>>{
	//@Autowired
   // CountDownLatch latch;
	EventBus evtBus ;
	Handler_Completed (EventBus evtBus){
		this.evtBus = evtBus;
	}
	
	public void accept(Event t) {
		// TODO Auto-generated method stub
		
		StatefulTasks taskObj = (StatefulTasks)t.getData();
		System.out.println("COMPLETED---->" + taskObj.getName());
		taskObj.setState("COMPLETED");
		System.out.println("Task Completed"+taskObj.getName());
		
	}

}
