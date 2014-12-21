package Model;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Gear extends Observable{
	public enum GearStatus{up, down, goingUp, goingDown, stuck, doorOpen}
	private GearStatus status;
	private Door door;
	
	public Gear(){
		door = new Door();
	}
	public Gear(GearStatus status){
		door = new Door();
		this.setStatus(status);
	}
	
	public void goUp(){
		Timer timer = new Timer();
		maneuver(GearStatus.up);
		timer.schedule (new TimerTask() {
            public void run()
            {
            	door.setOpen(true);
            	Timer timer2 = new Timer();
            	setStatus(GearStatus.doorOpen);
            	timer2.schedule (new TimerTask() {
                    public void run()
                    {
                    	setStatus(GearStatus.up);
                    }
                }, 400);
            }
        }, 2000);
		
	}
	
	public void goDown(){
		this.door.setOpen(true);
		this.setStatus(GearStatus.doorOpen);
		Timer timer = new Timer();
		timer.schedule (new TimerTask() {
            public void run()
            {
            	maneuver(GearStatus.down);
            	Timer timer2 = new Timer();
        		timer2.schedule (new TimerTask() {
                    public void run()
                    {
                    	setStatus(GearStatus.down);
                    }
                }, 2000);
            	
            }
        }, 400);
		
		this.door.setOpen(false);
	}
	
	public GearStatus maneuver(GearStatus direction){
		if(direction == GearStatus.up)
			setStatus(GearStatus.goingUp);
		else if (direction == GearStatus.down)
			setStatus(GearStatus.goingDown);
		return direction;
	}
	
	public void toggleGear(){
		if(this.getStatus() == GearStatus.down)
			this.goUp();
		else if(this.getStatus() == GearStatus.up)
			this.goDown();
		else this.goDown();
	}
	
	// GETTERS & SETTERS
	public GearStatus getStatus(){return this.status;}	
	public void setStatus(GearStatus gearStatus){
		this.status = gearStatus;
		setChanged();
		notifyObservers();
		System.out.println("Gear Status: "+this.status.toString());
	}

	public Door getGearDoor() {
		return door;
	}

	public void setDoor(Door door) {
		this.door = door;
	}
	
	
}
