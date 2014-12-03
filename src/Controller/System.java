package Controller;
import Model.Gear;
import Model.Sensors;
import Model.Gear.GearStatus;

public class System {
	private Gear gearSet;
	private Sensors sensorSet;
	
	public System(){
		gearSet = new Gear();
		sensorSet = new Sensors();
	}
	
	public boolean gearsRetracting(){
		boolean res = false;
		if(sensorSet.getForce() > -Sensors.planeWeight){
			if(gearSet.getStatus() != GearStatus.up){
				gearSet.setStatus(GearStatus.up);
				res =  true;
			}
		}
		return res;
	}
	
	public boolean gearsOutgoing(){
		boolean res = false;
		if(sensorSet.getForce() <= -Sensors.planeWeight){
			if(gearSet.getStatus() != GearStatus.down){
				gearSet.setStatus(GearStatus.up);
				res =  true;
			}
		}
		return res;
	}
	
	public Gear getGear(){return this.gearSet;}
	public Sensors getSensors(){return this.sensorSet;}

}
