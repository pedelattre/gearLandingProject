package Model;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Gear extends Observable{
	;
	public enum GearStatus{up, down, goingUp, goingDown, stuck}
	private GearStatus status;
	
	public Gear(){
		
		setStatus(GearStatus.down);
	}
	
	public void goUp(){
		maneuver(GearStatus.up);
		Timer timer = new Timer();
		timer.schedule (new TimerTask() {
            public void run()
            {
            	setStatus(GearStatus.up);
            }
        }, 3000);
	}
	
	public void goDown(){
		this.maneuver(GearStatus.down);
		Timer timer = new Timer();
		timer.schedule (new TimerTask() {
            public void run()
            {
            	setStatus(GearStatus.down);
            }
        }, 3000);
	}
	
	public GearStatus maneuver(GearStatus direction){
		if(direction == GearStatus.up)
			setStatus(GearStatus.goingUp);
		else if (direction == GearStatus.down)
			setStatus(GearStatus.goingDown);
		return direction;
	}		
	public GearStatus getStatus(){return this.status;}	
	public void setStatus(GearStatus gearStatus){
		this.status = gearStatus;
		setChanged();
		notifyObservers();
		System.out.println("Gear Status: "+this.status.toString());
	}

}
