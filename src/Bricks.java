import javafx.scene.image.Image;


public class Bricks {
	private Image image = new Image("file:resources/brick.png");
	private Coordinates coord;
	private boolean ignoreRight = false;
	private boolean ignoreLeft = false;
	private double height = image.getHeight();
	private double width = image.getWidth();
	
	public Bricks(Coordinates coord) {
		this.coord = coord;
	}
	
	public Image image() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image; 
	}
	
	public Coordinates coord() {
		return coord;
	}

	public void setCoord(Coordinates coord) {
		this.coord = coord;
	}

	public double width() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double height() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public boolean ignoreLeft() {
		return ignoreLeft;
	}

	public void setIgnoreLeft(boolean ignoreLeft) {
		this.ignoreLeft = ignoreLeft;
	}

	public boolean ignoreRight() {
		return ignoreRight;
	}

	public void setIgnoreRight(boolean ignoreRight) {
		this.ignoreRight = ignoreRight;
	}
}
