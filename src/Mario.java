import javafx.scene.image.Image;


public class Mario extends Character {
	
	public Mario(double resistance, double xSpeed, Coordinates coord) {
		super(resistance, xSpeed, coord);
		
		setHealth(100);
		
		Image[] right = new Image[9];	
		Image[] left = new Image[9];
		Image[] projectileRight = new Image[4];
		Image[] projectileLeft = new Image[4];
		Image[] special = {new Image("file:resources/Mario/Projectile/specialRight.png"), new Image("file:resources/Mario/Projectile/specialLeft.png")};
		
				
		for(int i = 0; i < 4; i++) {
			projectileRight[i] = new Image("file:resources/Mario/Projectile/Right" + (i + 1) + ".png");
			projectileLeft[i] = new Image("file:resources/Mario/Projectile/Left" + (i + 1) + ".png");
		}
		
		for(int i = 0; i < 8; i++) {
			right[i] = new Image("file:resources/Mario/Movement/Right" + (i + 1) + ".png");
			left[i] = new Image("file:resources/Mario/Movement/left" + (i + 1) + ".png");
		}
		right[8] = new Image("file:resources/Mario/Movement/Right6.png");
		left[8] = new Image("file:resources/Mario/Movement/Left6.png");
		
		Image[] jump = {new Image("file:resources/Mario/Movement/jumpRight.png"), new Image("file:resources/Mario/Movement/jumpLeft.png")};
		Image[] stand = {new Image("file:resources/Mario/Movement/standRight.png"), new Image("file:resources/Mario/Movement/standLeft.png")};
		setSprites(new Sprites(right, left, stand, jump));
		sprites().setCurrentSprite(right[0]);
		setHeight(sprites().currentSprite().getHeight());
		setWidth(sprites().currentSprite().getWidth());
		setProjectile(new Projectiles(10, 8, new Sprites(projectileRight, projectileLeft)));
		setRight(true);
		setSpecial(new Special(special, true));
	}
}
