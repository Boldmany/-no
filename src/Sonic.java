import javafx.scene.image.Image;

public class Sonic extends Character{

	public Sonic(double resistance, double xSpeed, Coordinates coord) {
		super(resistance, xSpeed, coord);

		setHealth(100);
		
		Image[] right = new Image[9];	
		Image[] left = new Image[9];
		Image[] projectileRight = {new Image("file:resources/Sonic/Projectile/Right.png")};
		Image[] projectileLeft = {new Image("file:resources/Sonic/Projectile/Left.png")};
		Image[] special = {new Image("file:resources/Sonic/Movement/specialRight.png"), new Image("file:resources/Sonic/Movement/specialLeft.png")};
		
		for(int i = 0; i < 8; i++) {
			right[i] = new Image("file:resources/Sonic/Movement/Right" + (i + 1) + ".png");
			left[i] = new Image("file:resources/Sonic/Movement/left" + (i + 1) + ".png");
		}
		right[8] = new Image("file:resources/Sonic/Movement/Right6.png");
		left[8] = new Image("file:resources/Sonic/Movement/Left6.png");
		
		Image[] jump = {new Image("file:resources/Sonic/Movement/jumpRight.png"), new Image("file:resources/Sonic/Movement/jumpLeft.png")};
		Image[] stand = {new Image("file:resources/Sonic/Movement/standRight.png"), new Image("file:resources/Sonic/Movement/standLeft.png")};
		setSprites(new Sprites(right, left, stand, jump));
		sprites().setCurrentSprite(right[0]);
		setHeight(sprites().currentSprite().getHeight());
		setWidth(sprites().currentSprite().getWidth());
		setProjectile(new Projectiles(10, 10, new Sprites(projectileRight, projectileLeft)));
		setSpecial(new Special(special, false));
	}

}
