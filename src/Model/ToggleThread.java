package Model;

public class ToggleThread implements Runnable {
	
	private Gear g;
	
	public ToggleThread(Gear g){
		this.g = g;
	}
	
	@Override
	public void run() {
		this.g.toggleGear();
		
	}

}
