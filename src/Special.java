import javafx.scene.image.Image;

public class Special {
	
	private Image[] image;
	private boolean physics = true;
	private boolean shoot;
	private boolean canSpecial = true;
	private double duration = 0;
	private double cooldown = 240;
	private boolean hit = false;
	
	public Special(Image[] image, boolean shoot) {
		this.setImage(image);
		this.setShoot(shoot);
	}
	
	public boolean physics() {
		return physics;
	}
	public void setPhysics(boolean physics) {
		this.physics = physics;
	}

	public boolean shoot() {
		return shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}

	public boolean canSpecial() {
		return canSpecial;
	}

	public void setCanSpecial(boolean canSpecial) {
		this.canSpecial = canSpecial;
	}

	public Image[] image() {
		return image;
	}

	public void setImage(Image[] image) {
		this.image = image;
	}

	public double duration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public double cooldown() {
		return cooldown;
	}

	public void setCooldown(double cooldown) {
		this.cooldown = cooldown;
	}

	public boolean hit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}
}
