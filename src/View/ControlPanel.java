package View;

import javax.imageio.ImageIO;
import javax.swing.*;

import Controller.Controller;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;



public class ControlPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	public static int posInit = 0;
	public static int posMilieu = 200;
	public static int posFinale = 400;
	
	public Box commandBox;
	private JPanel ledsPanel;
	private BufferedImage leverImage;
	private JLabel leverLabel;
	private BufferedImage caseImage;
	private JLabel caseLabel;
	private BufferedImage screenDownImage;
	private BufferedImage screenMovingImage;
	private BufferedImage screenUpImage;
	public JButton buttonControl;
	private JLabel screenLabel;
	public BufferedImage greenLedImage;
	public BufferedImage greenLedOnImage;
	public JLabel greenLedLabel;
	private BufferedImage orangeLedImage;
	private BufferedImage orangeLedOnImage;
	private JLabel orangeLedLabel;
	private BufferedImage redLedImage;
	private BufferedImage redLedOnImage;
	private JLabel redLedLabel;
	
	protected Controller sys;
	
	
	public ControlPanel(Controller sys){
		this.ledsPanel = new JPanel();
		this.buttonControl = new JButton("Toggle Gear");
		try {
			this.caseImage = ImageIO.read(new File("images/leverCase2.png"));
			this.leverImage = ImageIO.read(new File("images/lever2.png"));
			this.redLedImage = ImageIO.read(new File("images/redOff2.png"));
			this.redLedOnImage = ImageIO.read(new File("images/redOn.png"));
			this.orangeLedImage = ImageIO.read(new File("images/orangeOff2.png"));
			this.orangeLedOnImage = ImageIO.read(new File("images/orangeOn.png"));
			this.greenLedImage = ImageIO.read(new File("images/greenOff2.png"));
			this.greenLedOnImage = ImageIO.read(new File("images/greenOn.png"));
			this.screenDownImage = ImageIO.read(new File("images/gearIn2.png"));
			this.screenMovingImage = ImageIO.read(new File("images/gearMoving2.png"));
			this.screenUpImage = ImageIO.read(new File("images/gearOut2.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		this.leverLabel = new JLabel(new ImageIcon(leverImage));
		this.caseLabel = new JLabel(new ImageIcon(caseImage));
		this.greenLedLabel = new JLabel(new ImageIcon(greenLedImage));
		this.redLedLabel = new JLabel(new ImageIcon(redLedImage));
		this.orangeLedLabel = new JLabel(new ImageIcon(orangeLedImage));
		this.screenLabel = new JLabel(new ImageIcon(screenDownImage));
		this.commandBox = Box.createVerticalBox();
		
		this.sys = sys;
		prepareInterface();
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		this.actionHandler();
		downState();
	}

	private void prepareInterface(){
		//CONTROL PANEL - MAIN PANE
		GridBagLayout grid = new GridBagLayout();
		this.setSize(1000,800);
		this.setLayout(grid);
		
		//INIT CONSTRAINTS - gbc
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		/*
		 * LEDS PANEL
		 */
		GridBagLayout ledsGrid = new GridBagLayout();
		ledsPanel.setPreferredSize(new Dimension(400,100));
		ledsPanel.setLayout(ledsGrid);

		//INIT CONSTRAINTS - ledsGbc
		GridBagConstraints ledsGbc = new GridBagConstraints();
		
		//GREEN LED LABEL
		ledsGbc.gridx = 0; 
		ledsGbc.gridy = 0;
		ledsGbc.gridheight = 1;
		ledsGbc.weightx = 1;
		ledsGbc.anchor = GridBagConstraints.LINE_START;
		ledsPanel.add(greenLedLabel, ledsGbc);
					
		//ORANGE LED LABEL
		ledsGbc.gridx = 1; 
		ledsGbc.gridy = 0;
		ledsGbc.gridheight = 1;
		ledsGbc.weightx = 1;
		ledsGbc.anchor = GridBagConstraints.CENTER;
		ledsPanel.add(orangeLedLabel, ledsGbc);

		//RED LED LABEL
		ledsGbc.gridx = 2; 
		ledsGbc.gridy = 0;
		ledsGbc.gridheight = 1;
		ledsGbc.gridwidth = 1;
		ledsGbc.weightx = 1;
		ledsGbc.anchor = GridBagConstraints.LINE_END;
		ledsPanel.add(redLedLabel, ledsGbc);
		
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
			    	sys.toggleGear();
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
	
	public void upState(){
		greenLedLabel.setIcon(new ImageIcon(greenLedImage));
		redLedLabel.setIcon(new ImageIcon(redLedImage));
		orangeLedLabel.setIcon(new ImageIcon(orangeLedImage));
		screenLabel.setIcon(new ImageIcon(screenUpImage));
	}
	
	public void downState(){
		greenLedLabel.setIcon(new ImageIcon(greenLedOnImage));
		redLedLabel.setIcon(new ImageIcon(redLedImage));
		orangeLedLabel.setIcon(new ImageIcon(orangeLedImage));
		screenLabel.setIcon(new ImageIcon(screenDownImage));
	}
	
	public void maneuverState(){
		greenLedLabel.setIcon(new ImageIcon(greenLedImage));
		redLedLabel.setIcon(new ImageIcon(redLedImage));
		orangeLedLabel.setIcon(new ImageIcon(orangeLedOnImage));
		screenLabel.setIcon(new ImageIcon(screenMovingImage));
	}
	
	public void failureState(){
		greenLedLabel.setIcon(new ImageIcon(greenLedImage));
		redLedLabel.setIcon(new ImageIcon(redLedOnImage));
		orangeLedLabel.setIcon(new ImageIcon(orangeLedImage));
		screenLabel.setIcon(new ImageIcon(screenMovingImage));
	}
	
	public JLabel getLever(){
		return this.leverLabel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}