public class Projectiles {
	
	private double damage;
	private double speed = 3;
	private Sprites sprites;
	private double width = 25;
	private double height = 25;
	private boolean right;
	private Coordinates coord;
	private boolean special = false;
	
	public Projectiles(double damage, double speed, Sprites sprites) {
		this.damage = damage;
		this.speed = speed;
		this.sprites = sprites;
	}
	
	public Projectiles(double damage, double speed, Sprites sprites, Boolean special, int width, int height) {
		this.damage = damage;
		this.speed = speed;
		this.sprites = sprites;
		this.special = special;
		this.width = width;
		this.height = height;
	}
	public void generateProjectile(Character character) {
		
		character.projectiles().add(new Projectiles(character.projectile().damage(), 
				character.projectile().speed(), new Sprites(character.projectile().sprites().right(), character.projectile().sprites().left())));
		
		int number = character.projectiles().size() - 1;
		double xPos;
		
		if(character.right()) {
			character.projectiles().get(number).sprites().setCurrentSprite(character.projectile().sprites().right()[0]);
			xPos = character.coord().x() + character.width();
			character.projectiles().get(number).setRight(true);
		}
		else {
			character.projectiles().get(number).sprites().setCurrentSprite(character.projectile().sprites().left()[0]);
			xPos = character.coord().x() - character.projectiles().get(number).width();
			character.projectiles().get(number).setRight(false);
		}
		character.projectiles().get(number).setCoord(new Coordinates(xPos, character.coord().y() + (character.height() / 2)));
	}
	
	public void removeProjectile(Projectiles projectile, Character character) {
		character.projectiles().remove(character.projectiles().indexOf(projectile));
	}

	public double damage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public double speed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Sprites sprites() {
		return sprites;
	}

	public void setSprites(Sprites sprites) {
		this.sprites = sprites;
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

	public boolean right() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean special() {
		return special;
	}

	public void setSpecial(boolean special) {
		this.special = special;
	}
}
