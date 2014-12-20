package View;

import javax.imageio.ImageIO;
import javax.swing.*;

import Controller.Controller;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class ControlPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	public static int posInit = 0;
	public static int posMilieu = 200;
	public static int posFinale = 400;
	
	public Box commandBox;
	public JButton buttonControl;
	private JPanel ledsPanel;
	private JPanel sensorsPanel;
	private JLabel caseLabel;
	private JLabel leverLabel;
	private JLabel screenLabel;	
	private JLabel LedGear1;
	private JLabel LedGear2;
	private JLabel LedGear3;
	private JTextField speedTextField;
	private JTextField forceTextField;	
	private BufferedImage LedOffImage;
	private BufferedImage LedGreenImage;
	private BufferedImage LedOrangeImage;
	private BufferedImage LedRedImage;
	private BufferedImage leverImage;
	private BufferedImage caseImage;
	private BufferedImage screenDownImage;
	private BufferedImage screenMovingImage;
	private BufferedImage screenUpImage;
	private BufferedImage screenDoorOpenImage;
	protected Controller sys;
	
	public ControlPanel(Controller sys){
		this.ledsPanel = new JPanel();
		this.sensorsPanel = new JPanel();
		this.buttonControl = new JButton("Toggle Gear");
		try {
			this.caseImage = ImageIO.read(new File("images/leverCase2.png"));
			this.leverImage = ImageIO.read(new File("images/lever2.png"));
			this.LedOffImage = ImageIO.read(new File("images/up.png"));
			this.LedRedImage = ImageIO.read(new File("images/failure.png"));
			this.LedOrangeImage = ImageIO.read(new File("images/moving.png"));
			this.LedGreenImage = ImageIO.read(new File("images/down.png"));
			this.screenDownImage = ImageIO.read(new File("images/gearOut2.png"));
			this.screenMovingImage = ImageIO.read(new File("images/gearMoving2.png"));
			this.screenUpImage = ImageIO.read(new File("images/gearIn2.png"));
			this.screenDoorOpenImage = ImageIO.read(new File("images/doorOpen.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		this.speedTextField = new JTextField(String.valueOf(sys.getSensors().getSpeed()));
		this.forceTextField = new JTextField(String.valueOf(sys.getSensors().getForce()));
		
		this.leverLabel = new JLabel(new ImageIcon(leverImage));
		this.caseLabel = new JLabel(new ImageIcon(caseImage));
		this.LedGear1 = new JLabel(new ImageIcon(LedGreenImage));
		this.LedGear2 = new JLabel(new ImageIcon(LedGreenImage));
		this.LedGear3 = new JLabel(new ImageIcon(LedGreenImage));
		this.screenLabel = new JLabel(new ImageIcon(screenDownImage));
		this.commandBox = Box.createVerticalBox();
		
		this.sys = sys;
		prepareInterface();
		downState(LedGear1);
		downState(LedGear2);
		downState(LedGear3);
		downStateGear(screenLabel);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		this.actionHandler();
	}

	private void prepareInterface(){
		//CONTROL PANEL - MAIN PANE
		GridBagLayout grid = new GridBagLayout();
		this.setSize(1000,800);
		this.setLayout(grid);
		
		//INIT CONSTRAINTS
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		/*
		 * LEDS PANEL
		 */
		GridBagLayout ledsGrid = new GridBagLayout();
		ledsPanel.setPreferredSize(new Dimension(400,100));
		ledsPanel.setLayout(ledsGrid);

		//INIT CONSTRAINTS - ledsGbc
		GridBagConstraints ledsGbc = new GridBagConstraints();
		
		//Gear1 Led
		ledsGbc.gridx = 0; 
		ledsGbc.gridy = 0;
		ledsGbc.gridheight = 1;
		ledsGbc.weightx = 1;
		ledsGbc.anchor = GridBagConstraints.LINE_START;
		ledsPanel.add(LedGear1, ledsGbc);
					
		//Oear2 Led
		ledsGbc.gridx = 1; 
		ledsGbc.gridy = 0;
		ledsGbc.gridheight = 1;
		ledsGbc.weightx = 1;
		ledsGbc.anchor = GridBagConstraints.CENTER;
		ledsPanel.add(LedGear2, ledsGbc);

		//Gear3 Led
		ledsGbc.gridx = 2; 
		ledsGbc.gridy = 0;
		ledsGbc.gridheight = 1;
		ledsGbc.gridwidth = 1;
		ledsGbc.weightx = 1;
		ledsGbc.anchor = GridBagConstraints.LINE_END;
		ledsPanel.add(LedGear3, ledsGbc);
		
		//ADDS LEDS PANEL TO THE MAIN PANEL WITH CONSTRAINTS
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(ledsPanel);
		/*
		 * LEDS PANEL - END
		 */
		
		/*
		 * SCREEN PANEL
		 */
		//SCREEN - LEFT PANEL
		screenLabel.setHorizontalAlignment(JLabel.LEFT);
		
		//ADDS SCREEN LABEL TO THE MAIN PANEL WITH CONSTRAINTS
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		this.add(screenLabel, gbc);
		/*
		 * SCREEN PANEL - END
		 */
		
		
		/*
		 * SENSORS PANEL 
		 */
		GridLayout gridl = new GridLayout(2,2);
		speedTextField.setPreferredSize(new Dimension(20,20));
		sensorsPanel.setLayout(gridl);
		sensorsPanel.add(new JLabel("Speed: "));
		sensorsPanel.add(speedTextField);
		sensorsPanel.add(new JLabel("Force: "));
		sensorsPanel.add(forceTextField);
		/*
		 * SENSORS PANEL - END
		 */
		
		/*
		 * COMMAND BOX
		 */
		//COMMAND BOX - USED TO STACK TWO SHAPES
		leverLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		leverLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		commandBox.add(leverLabel);
		//commandBox.add(this.buttonControl);
		caseLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		caseLabel.setSize(caseLabel.getWidth(), 400);
		commandBox.add(caseLabel);
		commandBox.add(sensorsPanel);
		
		
		
		//ADDS COMMAND BOX TO THE MAIN PANEL WITH CONSTRAINTS
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1;
		this.add(commandBox, gbc);
		/*
		 * COMMAND BOX - END
		 */
	}
	
	public void actionHandler(){
		
		leverLabel.addMouseListener(
			new MouseListener() {
				public void mouseClicked(MouseEvent e) {
			    	toggleLever();
			    	sys.getGear().toggleGearSet();
			    }
			    public void mousePressed(MouseEvent e) {}
			    public void mouseReleased(MouseEvent e) {}			    
			    public void mouseEntered(MouseEvent e) {}
			    public void mouseExited(MouseEvent e) {}            
			}
		);
	}
	public void toggleLever(){
		if(leverLabel.getY() == posInit){
			leverLabel.setLocation(leverLabel.getX(), posFinale);
		}
		else leverLabel.setLocation(leverLabel.getX(), posInit);

		
	}
	
	public void upState(JLabel led){
		led.setIcon(new ImageIcon(LedOffImage));
	}
	public void upStateGear(JLabel image){
		image.setIcon(new ImageIcon(screenUpImage));
	}
	
	public void downState(JLabel led){		
		led.setIcon(new ImageIcon(LedGreenImage));
	}
	public void downStateGear(JLabel image){
		image.setIcon(new ImageIcon(screenDownImage));
	}
	
	public void maneuverState(JLabel led){
		led.setIcon(new ImageIcon(LedOrangeImage));
	}
	public void maneuverStateGear(JLabel image){
		image.setIcon(new ImageIcon(screenMovingImage));
	}
	
	public void failureState(JLabel led){
		led.setIcon(new ImageIcon(LedRedImage));
	}
	
	public void doorOpenState(JLabel image){
		image.setIcon(new ImageIcon(screenDoorOpenImage));
	}
	public JLabel getLever(){
		return this.leverLabel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public JLabel getScreenLabel() {
		return screenLabel;
	}

	public void setScreenLabel(JLabel screenLabel) {
		this.screenLabel = screenLabel;
	}

	public JLabel getLedGear1() {
		return LedGear1;
	}

	public void setLedGear1(JLabel ledGear1) {
		LedGear1 = ledGear1;
	}

	public JLabel getLedGear2() {
		return LedGear2;
	}

	public void setLedGear2(JLabel ledGear2) {
		LedGear2 = ledGear2;
	}

	public JLabel getLedGear3() {
		return LedGear3;
	}

	public void setLedGear3(JLabel ledGear3) {
		LedGear3 = ledGear3;
	}

}