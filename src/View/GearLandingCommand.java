package View;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import Controller.Controller;
import Model.Gear.GearStatus;
import Model.GearSet;
import Model.Gear;

public class GearLandingCommand extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	
	private ControlPanel pan;
	public GearSet gearSet;
	public Controller sys;
		
	public GearLandingCommand(GearSet gearSet, Controller sys){		
		this.gearSet = gearSet;
		this.sys = sys;
		this.pan = new ControlPanel(sys);
		for (Gear g : this.gearSet.gearSet) {
			 g.addObserver(this);
		}
		gearSet.addObserver(this);
		this.setTitle("Gear Landing Control Panel");
		this.setSize(1000,800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(pan);
		this.setVisible(true);
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(arg0 == gearSet.gearSet.get(0))
		{
			if(gearSet.gearSet.get(0).getStatus() == GearStatus.doorOpen ){
				pan.maneuverState(pan.getLedGear1());
				pan.doorOpenState(pan.getScreenLabel());
			}
			if(gearSet.gearSet.get(0).getStatus() == GearStatus.up){
				pan.upStateGear(pan.getScreenLabel());
				pan.upState(pan.getLedGear1());
			}
			if(gearSet.gearSet.get(0).getStatus() == GearStatus.down){
				pan.downState(pan.getLedGear1());
				pan.downStateGear(pan.getScreenLabel());
			}
			if(gearSet.gearSet.get(0).getStatus() == GearStatus.goingUp || gearSet.gearSet.get(0).getStatus() == GearStatus.goingDown){
				pan.maneuverState(pan.getLedGear1());
				pan.maneuverStateGear(pan.getScreenLabel());
			}
			if(gearSet.gearSet.get(0).getStatus() == GearStatus.stuck)
				pan.failureState(pan.getLedGear1());
		}
		else if(arg0 == gearSet.gearSet.get(1))
		{			
			if(gearSet.gearSet.get(1).getStatus() == GearStatus.up)
				pan.upState(pan.getLedGear2());
			if(gearSet.gearSet.get(1).getStatus() == GearStatus.down)
				pan.downState(pan.getLedGear2());
			if(gearSet.gearSet.get(1).getStatus() == GearStatus.goingUp || gearSet.gearSet.get(1).getStatus() == GearStatus.goingDown)
				pan.maneuverState(pan.getLedGear2());
			if(gearSet.gearSet.get(1).getStatus() == GearStatus.stuck)
				pan.failureState(pan.getLedGear2());
		}
		else if(arg0 == gearSet.gearSet.get(2))
		{
			if(gearSet.gearSet.get(2).getStatus() == GearStatus.up){
				pan.upState(pan.getLedGear3());}
			if(gearSet.gearSet.get(2).getStatus() == GearStatus.down){
				pan.downState(pan.getLedGear3());}
			if(gearSet.gearSet.get(2).getStatus() == GearStatus.goingUp || gearSet.gearSet.get(0).getStatus() == GearStatus.goingDown){
				pan.maneuverState(pan.getLedGear3());}
			if(gearSet.gearSet.get(2).getStatus() == GearStatus.stuck){
				pan.failureState(pan.getLedGear3());}
		}
		
		
	}
}
	
	
