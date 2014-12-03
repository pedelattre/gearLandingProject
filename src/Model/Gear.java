package Model;

public class Gear {
	
	public enum GearStatus{up, down, goingUp, goingDown, stuck}
	private GearStatus status;
	
	public Gear(){
		setStatus(GearStatus.down);
	}
	
	public void goUp(){
		this.maneuver(GearStatus.up);
		setStatus(GearStatus.up);
	}
	
	public void goDown(){
		this.maneuver(GearStatus.down);
		setStatus(GearStatus.down);
	}
	
	public void maneuver(GearStatus direction){
		if(direction == GearStatus.up)
			setStatus(GearStatus.goingUp);
		else if (direction == GearStatus.down)
			setStatus(GearStatus.goingDown);
		// Add a sleep(1000);			
	}		
	public GearStatus getStatus(){return this.status;}	
	public void setStatus(GearStatus gearStatus){this.status = gearStatus;}	

}
