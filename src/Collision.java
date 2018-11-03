
public class Collision {

	public static void brick(Bricks brick, Character character) {
		//Character colliding with top side of the brick
		if(character.coord().y() + character.height() + character.velocity() < brick.coord().y() &&
				character.coord().y() + character.height() > brick.coord().y() &&
				character.coord().y() < brick.coord().y() &&
				character.coord().x() + character.width() > brick.coord().x() &&
				character.coord().x() < brick.coord().x() + brick.width()) {
			if(character.move()) {
				if(character.right() &&
						character.coord().x() + character.width() - character.xSpeed() > brick.coord().x()) {
					floor(brick, character);
				}
				else if(character.coord().x() + character.xSpeed() < brick.coord().x() + brick.width()) {
					floor(brick, character);
				}
			}
			else {
			floor(brick, character);
			}
		}
		//Character colliding with left side of the brick
		if(character.right() &&
				!brick.ignoreLeft() &&
				character.coord().y() < brick.coord().y() + brick.height() &&
				character.coord().y() + character.height() + character.velocity() > brick.coord().y() &&
				character.coord().x() - character.xSpeed() < brick.coord().x() &&
				character.coord().x() + character.width() - 2 * character.xSpeed() < brick.coord().x() &&
				character.coord().x() + character.width() > brick.coord().x()) {
			character.coord().setX(brick.coord().x() - character.width());
		}
		//Character colliding with right side of the brick
		if(!character.right() &&
				!brick.ignoreRight() &&
				character.coord().y() < brick.coord().y() + brick.height() &&
				character.coord().y() + character.height() + character.velocity() > brick.coord().y() &&
				character.coord().x() + character.width() + character.xSpeed() > brick.coord().x() + brick.width() &&
				character.coord().x() + 2 * character.xSpeed() > brick.coord().x() + brick.width() &&
				character.coord().x() < brick.coord().x() + brick.width()) {
			character.coord().setX(brick.coord().x() + brick.width());
		}
	}

	public static void gravity(Bricks brick, Character character) {	
		if(character.coord().y() + character.height() + character.velocity() == brick.coord().y() &&
				character.coord().y() + character.height() == brick.coord().y() &&
				character.coord().y() < brick.coord().y() &&
				character.coord().x() + character.width() > brick.coord().x() &&
				character.coord().x() < brick.coord().x() + brick.width()) {
			character.setColliding(true);
		}
	}
	
	public static void floor(Bricks brick, Character character) {
		character.coord().setY(brick.coord().y() - character.height());
		character.setJumps(0);
		character.setVelocity(0);
		character.setGravity(false);
		character.sprites().setCurrentSprite(character.sprites().stand()[(character.right() ? 0 : 1)]);
	}
	
	public static boolean brick(Bricks brick, Projectiles projectile, Character character) {
		boolean collision = false;
		if(projectile.coord().y() < brick.coord().y() + brick.height() &&
				projectile.coord().y() + projectile.height() > brick.coord().y() &&
				projectile.coord().x() + projectile.width() > brick.coord().x() &&
				projectile.coord().x() < brick.coord().x() + brick.width()) {
			collision = true;
			character.projectile().removeProjectile(projectile, character);
		}
		return collision;
	}
	
	public static boolean character(Character[] characters, Projectiles projectile, int enemy, int player) {
		boolean collision = false;
		if(projectile.coord().y() < characters[enemy].coord().y() + characters[enemy].height() &&
				projectile.coord().y() + projectile.height() > characters[enemy].coord().y() &&
				projectile.coord().x() + projectile.width() > characters[enemy].coord().x() &&
				projectile.coord().x() < characters[enemy].coord().x() + characters[enemy].width()) {
			collision = true;
			characters[player].projectile().removeProjectile(projectile, characters[player]);
			Character.looseHealth(characters[enemy]);
		}
		return collision;
	}
	
	public static void specialCollision(Character[] characters, Projectiles projectile, int enemy, int player) {
		if(characters[enemy].special().physics() &&
				projectile.coord().y() < characters[enemy].coord().y() + characters[enemy].height() &&
				projectile.coord().y() + projectile.height() > characters[enemy].coord().y() &&
				projectile.coord().x() + projectile.width() > characters[enemy].coord().x() &&
				projectile.coord().x() < characters[enemy].coord().x() + characters[enemy].width()) {
			characters[enemy].stunned().setStunned(true);
			characters[enemy].stunned().setDuration(80);
			characters[player].special().setHit(true);
			Character.specialLooseHealth(characters[enemy], projectile.damage());
		}
	}
	
	public static void specialCollision(Character[] characters, int enemy, int player, int speed) {
		boolean collision = false;
		
		if(characters[enemy].special().physics() && 
				characters[player].coord().y() < characters[enemy].coord().y() + characters[enemy].height() &&
				characters[player].coord().y() + characters[player].height() > characters[enemy].coord().y() &&
				characters[player].coord().x() < characters[enemy].coord().x() + characters[enemy].width() &&
				characters[player].coord().x() + characters[player].width() > characters[enemy].coord().x()) {
			if(characters[player].right() &&
					characters[player].coord().x() - speed < characters[enemy].coord().x()) {
				collision = true;
			}
			else if(!characters[player].right() &&
					characters[player].coord().x() + characters[player].width() + speed > characters[enemy].coord().x() + characters[enemy].width()) {
				collision = true;
			}
		}
		
		if(collision) {
			Character.specialLooseHealth(characters[enemy], 15);
			characters[player].special().setHit(true);
		}
	}
	
	public static boolean boundary(Projectiles projectile, Character character) {
		boolean collision = false;
		
		if(projectile.coord().x() + projectile.width() > 1000) {
			collision = true;
		}
		else if(projectile.coord().x() < 0) {
			collision = true;
		}
		
		if(collision) {
			character.projectile().removeProjectile(projectile, character);
		}
		return collision;
	}
	
	public static void boundary(Character character) {
		//Right boundary collision
		if(character.right() &&
				character.coord().x() + character.width() > 1000) {
			character.coord().setX(1000 - character.width());
		}
		if(!character.right() &&
			character.coord().x() < 0) {
			character.coord().setX(0);
		}
	}
}
