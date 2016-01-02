package sample.testReactor;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;

import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.fn.Consumer;

public class Handler_Running implements Consumer<Event<StatefulTasks>>{
	//@Autowired
   // CountDownLatch latch;
	EventBus evtBus ;
	Handler_Running (EventBus evtBus){
		this.evtBus = evtBus;
	}
	public void accept(Event t) {
		// TODO Auto-generated method stub
		
		StatefulTasks taskObj = (StatefulTasks)t.getData();
		System.out.println("RUNNING---->" + taskObj.getName());
		taskObj.setState("RUNNING");
		evtBus.notify("completed",Event.wrap(taskObj));
		}

}
