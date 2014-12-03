package View;



import javax.swing.JFrame;

public class GearLandingCommand  extends JFrame{

private static final long serialVersionUID = 1L;

private ControlPanel pan = new ControlPanel();
	
	public GearLandingCommand(){

		this.setTitle("Gear Landing Control Panel");
		this.setSize(1000,800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(pan);
		this.setVisible(true);

	}		

}
