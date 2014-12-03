package Model;

public class Sensors {
	private float speed;
	private float force;
	public static final float planeWeight = 100000;
	
	public Sensors(){
		setSpeed(0);
		setForce(-planeWeight);			
	}
	
	public float getSpeed(){return this.speed;}
	public float getForce(){return this.force;}	
	public void setSpeed(float speed){this.speed = speed;}
	public void setForce(float force){this.force = force;}	
}
