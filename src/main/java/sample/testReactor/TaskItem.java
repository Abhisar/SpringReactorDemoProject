package sample.testReactor;

import java.util.Date;
import java.util.Random;

import com.sun.jmx.snmp.Timestamp;

public class TaskItem implements Runnable {
    
int num;
     
public void run() {
    System.out.println("Executing at "+ new Timestamp() + " , Number="+ num +  " ,  threadId :  "+Thread.currentThread().getId());
    try {
		Thread.sleep(new Random().nextInt(1000));
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
         
}
     
public int getNum() {
    return num;
}
     
public void setNum(int num) {
		this.num = num;
	}
}