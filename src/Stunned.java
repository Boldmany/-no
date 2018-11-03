
public class Stunned {
	
	private int duration = 0;
	private boolean stunned = false;
	
	public Stunned(boolean stunned) {
		this.stunned = stunned;
	}
	
	public int duration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public boolean stunned() {
		return stunned;
	}
	public void setStunned(boolean stunned) {
		this.stunned = stunned;
	}
	
}
