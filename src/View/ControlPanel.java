package View;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class ControlPanel extends JPanel implements MouseListener, MouseMotionListener{
	
	private Box commandBox;
	private JPanel ledsPanel;
	private BufferedImage leverImage;
	private JLabel leverLabel;
	private BufferedImage caseImage;
	private JLabel caseLabel;
	private BufferedImage screenDownImage;
	private BufferedImage screenMovingImage;
	private BufferedImage screenUpImage;
	private JLabel screenLabel;
	private BufferedImage greenLedImage;
	private BufferedImage greenLedOnImage;
	private JLabel greenLedLabel;
	private BufferedImage orangeLedImage;
	private BufferedImage orangeLedOnImage;
	private JLabel orangeLedLabel;
	private BufferedImage redLedImage;
	private BufferedImage redLedOnImage;
	private JLabel redLedLabel;

	
	
	public ControlPanel(){
		addMouseListener(this);
		prepareInterface();
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	private void prepareInterface(){

		
		
		//CONTROL PANEL - MAIN PANE
		GridBagLayout grid = new GridBagLayout();
		this.setSize(1000,800);
		this.setLayout(grid);
		
		
		
		
		//INIT CONSTRAINTS - gbc
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		
		
		/*LEDS PANEL
		 * 
		 */
		
		ledsPanel = new JPanel();
		GridBagLayout ledsGrid = new GridBagLayout();
		ledsPanel.setPreferredSize(new Dimension(400,100));
		ledsPanel.setLayout(ledsGrid);

		//INIT CONSTRAINTS - ledsGbc
		GridBagConstraints ledsGbc = new GridBagConstraints();
		
		//GREEN LED LABEL
		try {
			greenLedImage = ImageIO.read(new File("images/greenOff2.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		greenLedLabel = new JLabel(new ImageIcon(greenLedImage));
		ledsGbc.gridx = 0; 
		ledsGbc.gridy = 0;
		ledsGbc.gridheight = 1;
		ledsGbc.weightx = 1;
		ledsGbc.anchor = GridBagConstraints.LINE_START;
		ledsPanel.add(greenLedLabel, ledsGbc);
				
				
		//ORANGE LED LABEL
		try {
			orangeLedImage = ImageIO.read(new File("images/orangeOff2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		orangeLedLabel = new JLabel(new ImageIcon(orangeLedImage));
		ledsGbc.gridx = 1; 
		ledsGbc.gridy = 0;
		ledsGbc.gridheight = 1;
		ledsGbc.weightx = 1;
		ledsGbc.anchor = GridBagConstraints.CENTER;
		ledsPanel.add(orangeLedLabel, ledsGbc);

				
		//RED LED LABEL
		try {
			redLedImage = ImageIO.read(new File("images/redOff2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		redLedLabel = new JLabel(new ImageIcon(redLedImage));
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
		
		/*LEDS PANEL - END
		 * 
		 */
		
		
		
		
		/*SCREEN PANEL
		 * 
		 */
		
		//SCREEN - LEFT PANEL
		try {
			screenDownImage = ImageIO.read(new File("images/gearIn2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		screenLabel = new JLabel(new ImageIcon(screenDownImage));
		screenLabel.setHorizontalAlignment(JLabel.LEFT);
		
		
		//ADDS SCREEN LABEL TO THE MAIN PANEL WITH CONSTRAINTS
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		this.add(screenLabel, gbc);
		
		/*SCREEN PANEL - END
		 * 
		 */
			
		
		
		/*COMMAND BOX
		 * 
		 */
		
		//COMMAND BOX - USED TO STACK TWO SHAPES
		commandBox = Box.createVerticalBox();
		
		try {
			leverImage = ImageIO.read(new File("images/lever2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		leverLabel = new JLabel(new ImageIcon(leverImage));
		leverLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		commandBox.add(leverLabel);
		
		/*MOUSE LISTENER & MOTIONLISTENER TO MOVE THE LEVER
		 * 
		 */


		final Point pos = new Point();
		
		leverLabel.addMouseListener(
			new MouseListener() {
				
				
			    @Override
			    public void mousePressed(MouseEvent e) {
			        pos.setLocation(leverLabel.getX(),e.getY());
			        
			        int posInit = 0;
					int posFinale = 400;
					
			    	if(leverLabel.getY()<=posInit){
			    		leverLabel.setLocation(leverLabel.getX(), posInit);
			    	}else if(leverLabel.getY()>=posFinale){
			    		leverLabel.setLocation(leverLabel.getX(), posFinale);
			    	}
			        
			    }
			    
			    @Override
			    public void mouseReleased(MouseEvent e) {
			    	
			    	int posInit = 0;
					int posMilieu = 200;
					int posFinale = 400;
					
			    	if(leverLabel.getY()>posInit && leverLabel.getY()<= posMilieu){
			    		leverLabel.setLocation(leverLabel.getX(), posInit);
			    	}else if(leverLabel.getY()>posMilieu && leverLabel.getY()<= posFinale){
			    		leverLabel.setLocation(leverLabel.getX(), posFinale);
			    	}
			    	
			    	if(leverLabel.getY()<=posInit){
			    		leverLabel.setLocation(leverLabel.getX(), posInit);
			    	}else if(leverLabel.getY()>=posFinale){
			    		leverLabel.setLocation(leverLabel.getX(), posFinale);
			    	}
			    	
			    }
			    
			    //Unused functions from MouseListener
			    public void mouseClicked(MouseEvent e) {}
			    public void mouseEntered(MouseEvent e) {}
			    public void mouseExited(MouseEvent e) {}            
			}
		);
				
		leverLabel.addMouseMotionListener(
			new MouseMotionListener(){
			    @Override
			    public void mouseDragged(MouseEvent e)
			    {
			    	
			    	int posInit = 0;
					int posFinale = 400;
			    	
			        leverLabel.setLocation(leverLabel.getX(), leverLabel.getY()+e.getY()-pos.y);
			        if(leverLabel.getY()<=posInit){
			    		leverLabel.setLocation(leverLabel.getX(), posInit);
			    	}else if(leverLabel.getY()>=posFinale){
			    		leverLabel.setLocation(leverLabel.getX(), posFinale);
			    	}
			    }
			    public void mouseMoved(MouseEvent e) {}            
			}
		);
		
		/*MOUSE LISTENER & MOTIONLISTENER TO MOVE THE LEVER - END
		 * 
		 */
				
		try {
			caseImage = ImageIO.read(new File("images/leverCase2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		caseLabel = new JLabel(new ImageIcon(caseImage));
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
		
		
		/*COMMAND BOX - END
		 * 
		 */
	
	}

	
	void eventOutput(String eventDescription, MouseEvent e){
		System.out.println("Mouse event : " + eventDescription);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		eventOutput("Mouse Pressed !" , e);
		eventOutput("Lever On Y "+ leverLabel.getY()  , e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		eventOutput("Mouse Released !" , e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}



}