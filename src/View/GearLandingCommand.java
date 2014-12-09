package View;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import Controller.Controller;
import Model.Gear;

public class GearLandingCommand extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	
	private ControlPanel pan;
	public Gear gear;
	public Controller sys;
		
	public GearLandingCommand(Gear gear, Controller sys){		
		this.gear = gear;
		this.sys = sys;
		this.pan = new ControlPanel(sys);		
		
		this.gear.addObserver(this);
		this.setTitle("Gear Landing Control Panel");
		this.setSize(1000,800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(pan);
		this.setVisible(true);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 == gear)
		{			
			switch(gear.getStatus()){
				case up:
					pan.upState();
					break;
				case down:
					pan.downState();
					break;
				case goingUp:
					pan.maneuverState();
					break;
				case goingDown:
					pan.maneuverState();
					break;
				case stuck:
					pan.failureState();
					break;
			}
		}
	}
}
	
	
