package View;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	private BufferedImage screenImage;
	private JLabel screenLabel;
	private BufferedImage greenLedImage;
	private JLabel greenLedLabel;
	private BufferedImage orangeLedImage;
	private JLabel orangeLedLabel;
	private BufferedImage redLedImage;
	private JLabel redLedLabel;
	
	
	//INIT VARIABLES FOR LEVER MOVEMENTS
	
	private int mousePosX;
	private int mousePosY;
	private int leverPosX;
	private int leverPosY;
	
	
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
			screenImage = ImageIO.read(new File("images/gearOut2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		screenLabel = new JLabel(new ImageIcon(screenImage));
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
		commandBox.setBackground(Color.BLUE);
		try {
			leverImage = ImageIO.read(new File("images/lever2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		leverLabel = new JLabel(new ImageIcon(leverImage));
		leverLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		commandBox.add(leverLabel);
		
		final Point pos = new Point();
		
		leverLabel.addMouseListener(new MouseListener() {
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
		    
		    public void mouseClicked(MouseEvent e) {}
		    
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
		    	
		
		    	
		    }
		    public void mouseEntered(MouseEvent e) {}
		    public void mouseExited(MouseEvent e) {}            
		});
		
				
				
		leverLabel.addMouseMotionListener(new MouseMotionListener()
		{
		    @Override
		    public void mouseDragged(MouseEvent e)
		    {
		        leverLabel.setLocation(leverLabel.getX(), leverLabel.getY()+e.getY()-pos.y);
		    }

		    public void mouseMoved(MouseEvent e) {}            
		});
		
		
				
				
		
		try {
			caseImage = ImageIO.read(new File("images/leverCase2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		caseLabel = new JLabel(new ImageIcon(caseImage));
		caseLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		commandBox.add(caseLabel);
		
		
		
		//ADDS COMMAND BOX TO THE MAIN PANEL WITH CONSTRAINTS
		gbc.fill = GridBagConstraints.HORIZONTAL;
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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		eventOutput("Mouse Clicked !" , e);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		eventOutput("Mouse Entered !" , e);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		eventOutput("Mouse Exited !" , e);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		eventOutput("Mouse Pressed !" , e);
		
		if((mousePosX >= leverPosX) || (mousePosX <= (leverPosX + leverLabel.getPreferredSize().getWidth())) 
				&& ((mousePosY >= leverPosY) || (mousePosY <= (leverPosY + leverLabel.getPreferredSize().getHeight())))){
			
			eventOutput("Lever On Y "+ leverLabel.getY()  , e);
			
			mousePosY = e.getY();
			leverPosY = mousePosY;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		eventOutput("Mouse Released !" , e);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



}