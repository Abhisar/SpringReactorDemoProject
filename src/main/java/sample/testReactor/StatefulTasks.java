package sample.testReactor;
import reactor.bus.Event;
import reactor.bus.EventBus;

public class StatefulTasks implements Runnable{
	String state;
	String name ;
	EventBus eventBus;
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	
	
	StatefulTasks(EventBus eventBus){
		this.eventBus = eventBus;
	}
	
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void run() {
		//call start event
		
		System.out.println("ACTIVE ------- > " + getName());
		setState("ACTIVE");
		System.out.println("Notified for running ------> " + getName());
		eventBus.notify("running", Event.wrap(this));
		
		
	}

}
