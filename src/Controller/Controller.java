package Controller;
import Model.*;
import Model.Gear.GearStatus;
import View.*;

public class Controller {
	//private Gear gearSet;
	private GearSet gearSet;
	private Sensors sensorSet;
	private GearLandingCommand glc;
	
	
	public Controller(){
		gearSet = new GearSet();
		sensorSet = new Sensors();
		glc = new GearLandingCommand(gearSet, this);
	}
	
	/*public void toggleGear(){
		if(getGear().getStatus() == GearStatus.down)
			getGear().goUp();
		else if(getGear().getStatus() == GearStatus.up)
			getGear().goDown();
		else getGear().goDown();
	}*/
	
	
	public GearSet getGear(){return this.gearSet;}
	public Sensors getSensors(){return this.sensorSet;}

}
