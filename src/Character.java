import java.util.ArrayList;


abstract class Character {
	
	private double health = 100;
	private double resistance;
	private double xSpeed;
	private double velocity = 0;
	private Sprites sprites;
	private Projectiles projectile;
	private ArrayList<Projectiles> projectiles = new ArrayList<Projectiles>();
	private Special special;
	private Stunned stunned = new Stunned(false);
	private boolean gravity = true;
	private boolean move = false;
	private boolean right = false;
	private int jumps = 0;
	private boolean canJump = true;
	private boolean shooting = false;
	private int shootDelay = 0;
	private Coordinates coord;
	private boolean colliding = false;
	private double height;
	private double width;
	private int loses = 0;
	
	public Character(double resistance, double xSpeed, Coordinates coord) {
		this.resistance = resistance;
		this.xSpeed = xSpeed;
		this.coord = coord;
	}

	public static void looseHealth(Character character) {
		character.setHealth(character.health() - (character.projectile.damage() / (character.resistance() / 10)));
		if(character.health() < 0) {
			death(character);
		}
	}
	
	public static void specialLooseHealth(Character character, double d) {
		character.setHealth(character.health() - d);
		if(character.health() < 0) {
			death(character);
		}
	}
	
	public static void death(Character character) {
		character.setLoses(character.loses() + 1);
		Game.restart();
	}
	
	public double health() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public double resistance() {
		return resistance;
	}

	public void setResistance(double resistance) {
		this.resistance = resistance;
	}
	
	public Sprites sprites() {
		return sprites;
	}

	public void setSprites(Sprites sprites) {
		this.sprites = sprites;
	}
	
	public double xSpeed() {
		return xSpeed;
	}

	public void setxSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}

	public boolean move() {
		return move;
	}

	public void setMove(boolean move) {
		this.move = move;
	}

	public boolean right() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public Coordinates coord() {
		return coord;
	}

	public void setCoord(Coordinates coord) {
		this.coord = coord;
	}
	
	public double velocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public int jumps() {
		return jumps;
	}

	public void setJumps(int jumps) {
		this.jumps = jumps;
	}

	public double height() {
		return height;
	}

	public void setHeight(double d) {
		this.height = d;
	}

	public double width() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public boolean canJump() {
		return canJump;
	}

	public void setCanJump(boolean canJump) {
		this.canJump = canJump;
	}

	public boolean gravity() {
		return gravity;
	}

	public void setGravity(boolean gravity) {
		this.gravity = gravity;
	}

	public boolean colliding() {
		return colliding;
	}

	public void setColliding(boolean colliding) {
		this.colliding = colliding;
	}

	public Projectiles projectile() {
		return projectile;
	}

	public void setProjectile(Projectiles projectile) {
		this.projectile = projectile;
	}

	public ArrayList<Projectiles> projectiles() {
		return projectiles;
	}

	public void setProjectiles(ArrayList<Projectiles> projectiles) {
		this.projectiles = projectiles;
	}

	public boolean shooting() {
		return shooting;
	}

	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}

	public int shootDelay() {
		return shootDelay;
	}

	public void setShootDelay(int shootDelay) {
		this.shootDelay = shootDelay;
	}

	public Special special() {
		return special;
	}

	public void setSpecial(Special special) {
		this.special = special;
	}

	public Stunned stunned() {
		return stunned;
	}

	public void setStunned(Stunned stunned) {
		this.stunned = stunned;
	}

	public int loses() {
		return loses;
	}

	public void setLoses(int loses) {
		this.loses = loses;
	}
}
