package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import Model.Gear.GearStatus;

public class GearSet extends Observable{
	private Gear g1, g2, g3;
	private GearStatus generalStatus;
	public List<Gear> gearSet;
	private Sensors sensors;
	public boolean upable;
	public boolean downable;
	
	public GearSet(){
		g1 = new Gear();
		g2 = new Gear();
		g3 = new Gear();
		gearSet = new ArrayList<Gear>();
		gearSet.add(g1);
		gearSet.add(g2);
		gearSet.add(g3);
		sensors = new Sensors();
		setUpable(false);
		setDownable(false);
		setGearSetStatus(GearStatus.down);
	}
	
	public boolean isUpable() {
		if(sensors.getForce() >= -Sensors.planeWeight || sensors.getSpeed() <= 300)
		return false;
		else return true;
	}

	public Sensors getSensors() {
		return sensors;
	}

	public void setSensors(Sensors sensors) {
		this.sensors = sensors;
	}

	public void setUpable(boolean upable) {
		this.upable = upable;
	}

	public boolean isDownable() {
		if(sensors.getForce() == 0 || sensors.getSpeed() <= 800)
		return true;
		else return false;
	}
	

	public void setDownable(boolean downable) {
		this.downable = downable;
	}

	public void setGearSetStatus(GearStatus status){
		for (Gear g : this.gearSet) {
			 g.setStatus(status);
		}
	}
	public GearStatus getGearSetStatus(){
		if(gearSet.get(0).getStatus() == GearStatus.up && 
				gearSet.get(1).getStatus() == GearStatus.up &&
				gearSet.get(2).getStatus() == GearStatus.up )
			return GearStatus.up;
		else if (gearSet.get(0).getStatus() == GearStatus.down && 
				gearSet.get(1).getStatus() == GearStatus.down &&
				gearSet.get(2).getStatus() == GearStatus.down)
			return GearStatus.down;
		else if (gearSet.get(0).getStatus() == GearStatus.goingDown && 
				gearSet.get(1).getStatus() == GearStatus.goingDown &&
				gearSet.get(2).getStatus() == GearStatus.goingDown)
			return GearStatus.goingDown;
		else if (gearSet.get(0).getStatus() == GearStatus.goingUp && 
				gearSet.get(1).getStatus() == GearStatus.goingUp &&
				gearSet.get(2).getStatus() == GearStatus.goingUp)
			return GearStatus.goingUp;
		else return GearStatus.stuck;
	}
	
	public void setGearSetGeneralStatus(GearStatus status){
		this.generalStatus = status;
	}
	
	public void toggleGearSet(){
		Thread toggleg1 = new Thread(new ToggleThread(g1));
		Thread toggleg2 = new Thread(new ToggleThread(g2));
		Thread toggleg3 = new Thread(new ToggleThread(g3));
		if((getGearSetStatus() == GearStatus.up && this.isDownable() == true)){
			toggleg1.start();
			toggleg2.start();
			toggleg3.start();
		}
		else if(getGearSetStatus() == GearStatus.down && this.isUpable() == true){
			toggleg1.start();
			toggleg2.start();
			toggleg3.start();
		}
		
		setGearSetGeneralStatus(getGearSetStatus());
		setChanged();
		notifyObservers();
	}
}
