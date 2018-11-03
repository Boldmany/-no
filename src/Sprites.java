import javafx.scene.image.Image;


public class Sprites {
	
	private Image right[];
	private Image left[];
	private Image stand[];
	private Image jump[];
	private Image currentSprite;
	private int spriteDelay;
	private Image special[];
	
	public Sprites(Image right[], Image left[], Image stand[], Image jump[]) {
		this.right = right;
		this.left = left;
		this.stand = stand; 
		this.jump = jump;
	}
	
	public Sprites(Image right[], Image left[]) {
		this.right = right;
		this.left = left;
	}
	
	public Sprites(Image special[]) {
		this.setSpecial(special);
	}

	public Image[] right() {
		return right;
	}

	public void setRight(Image right[]) {
		this.right = right;
	}

	public Image[] left() {
		return left;
	}

	public void setLeft(Image left[]) {
		this.left = left;
	}

	public Image[] stand() {
		return stand;
	}

	public void setStand(Image stand[]) {
		this.stand = stand;
	}

	public Image[] jump() {
		return jump;
	}

	public void jump(Image jump[]) {
		this.jump = jump;
	}

	public Image currentSprite() {
		return currentSprite;
	}

	public void setCurrentSprite(Image currentSprite) {
		this.currentSprite = currentSprite;
	}

	public int spriteDelay() {
		return spriteDelay;
	}

	public void setSpriteDelay(int spriteDelay) {
		this.spriteDelay = spriteDelay;
	}

	public Image[] special() {
		return special;
	}

	public void setSpecial(Image special[]) {
		this.special = special;
	}
}
