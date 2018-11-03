import java.util.Arrays;

import javafx.scene.image.Image;

public class Move {

	public static void movement(Character character, Character[] characters, int player) {

		if(character.special().physics()) {
			if(character.move() && !character.stunned().stunned()) {
				if(character.right()) {
					right(character);
				}
				else {
					left(character);
				}
				Collision.boundary(character);
				character.sprites().setSpriteDelay((character.sprites().spriteDelay() + 1) % 3);
			}
			//stunned duration
			else if(character.stunned().stunned()) {
				character.stunned().setDuration(character.stunned().duration() - 1);
				if(character.stunned().duration() == 0) {
					character.stunned().setStunned(false);
				}
			}
			else if(character.sprites().spriteDelay() != 0 && character.jumps() == 0){
				character.sprites().setSpriteDelay(0);
			}

			if(character.gravity()) {
				character.setVelocity(character.velocity() - 0.3);
				gravity(character);
			}

			character.setColliding(false);

			for(int b = 0; b < Game.bricks().size(); b++) {
				Collision.brick(Game.bricks().get(b), character);
				Collision.gravity(Game.bricks().get(b), character);
			}
			if(!character.colliding()) {
				if(character.jumps() == 0) {
					character.setJumps(1);
				}
				character.setGravity(true);
			}

			//projectile shooting
			if(character.special().physics() && !character.stunned().stunned()) {
				if(character.shootDelay() != 0) {
					character.setShootDelay((character.shootDelay() + 1) % 30);
				}
				if(character.shooting() && character.shootDelay() == 0) {
					character.setShootDelay(1);
					character.projectile().generateProjectile(character);
				}
			}
		}
		//special moves
		else {
			character.special().setDuration((character.special().duration() + 1));
			//mario gets physics back
			if(character.special().duration() == 10 && character.special().shoot()) {
				character.special().setPhysics(true);
				character.special().setHit(false);
				character.special().setDuration(0);
				character.projectile().removeProjectile(character.projectiles().get(character.projectiles().size() - 1), character);
			}
			//sonic mad dash
			else if(!character.special().shoot()) {
				if(character.right()) {
					specialRight(character, 13);
				}
				else {
					specialLeft(character, 13);
				}
				int enemy = (player == 1) ? 0 : 1;
				Collision.boundary(character);
				if(!character.special().hit()) {
					Collision.specialCollision(characters, enemy, player, 20);
				}
			}
			//sonic gets physics back
			if(character.special().duration() == 14 && !character.special().shoot()) {
				character.special().setPhysics(true);
				character.special().setHit(false);
				character.special().setDuration(0);
				if(character.jumps() == 0) {
					if(character.right()) {
						character.sprites().setCurrentSprite(character.sprites().stand()[0]);
					}
					else {
						character.sprites().setCurrentSprite(character.sprites().stand()[1]);
					}
				}
			}

			if(character.special().canSpecial() && character.special().cooldown() == 0) {
				specialMove(character);
			}
		}

		if(!character.special().canSpecial()){
			character.special().setCooldown(character.special().cooldown() + 1);
			if(character.special().cooldown() == 240) {
				character.special().setCanSpecial(true);
			}
		}
	}

	public static void specialMove(Character character) {
		character.special().setCanSpecial(false);
		boolean right = character.right();
		//creating lightning
		if(character.special().shoot()) {
			character.projectiles().add(new Projectiles(5, 0, new Sprites(character.special().image()), true, 150, 40));
			Projectiles lightning = character.projectiles().get(character.projectiles().size() - 1);

			if(right) {
				character.sprites().setCurrentSprite(character.sprites().stand()[0]);
				lightning.sprites().setCurrentSprite(character.special().image()[0]);
				lightning.setCoord(new Coordinates
						((character.coord().x() + character.width()), character.coord().y() + lightning.height()/2));
			}
			else {
				character.sprites().setCurrentSprite(character.sprites().stand()[1]);
				lightning.sprites().setCurrentSprite(character.special().image()[1]);
				lightning.setCoord(new Coordinates
						(character.coord().x() - character.special().image()[1].getWidth(), character.coord().y() + lightning.height()/2));
			}
		}
		else {
			if(right) {
				character.sprites().setCurrentSprite(character.special().image()[0]);
			}
			else {
				character.sprites().setCurrentSprite(character.special().image()[1]);
			}
		}
	}

	public static void projectileMovement(Projectiles projectile, Character[] characters, int player) {
		int enemy = (player == 1) ? 0 : 1;

		if(!projectile.special()) {
			if(projectile.right()) {
				projectile.coord().setX(projectile.coord().x() + projectile.speed());
				changeSprite(projectile, projectile.sprites().right());
			}
			else {
				projectile.coord().setX(projectile.coord().x() - projectile.speed());
				changeSprite(projectile, projectile.sprites().left());
			}

			for(int b = 0; b < Game.bricks().size(); b++) {
				if(Collision.brick(Game.bricks().get(b), projectile, characters[player])) {
					return;
				}
			}
			if(Collision.boundary(projectile, characters[player]) || Collision.character(characters, projectile, enemy, player)) {
				return;
			}
			projectile.sprites().setSpriteDelay((projectile.sprites().spriteDelay() + 1) % 3);
		}
		else {
			if(!characters[player].special().hit()) {
				Collision.specialCollision(characters, projectile, enemy, player);
			}
		}
	}

	public static void right(Character character) {
		character.coord().setX(character.coord().x() + character.xSpeed());
		changeSprite(character, character.sprites().right());
	}

	public static void specialRight(Character character, double speed) {
		character.coord().setX(character.coord().x() + speed);
	}

	public static void left(Character character) {
		character.coord().setX(character.coord().x() - character.xSpeed());
		changeSprite(character, character.sprites().left());
	}

	public static void specialLeft(Character character, double speed) {
		character.coord().setX(character.coord().x() - speed);
	}

	public static void gravity(Character character) {
		if(character.jumps() > 0) {
			character.sprites().setCurrentSprite(character.sprites().jump()[(character.right() ? 0 : 1)]);
		}
		character.coord().setY(character.coord().y() - character.velocity());
	}

	public static void jump(Character character) {
		character.setVelocity(character.jumps() == 1 ? 8 : 7);
		character.setCanJump(false);
		character.setGravity(true);
	}

	public static void changeSprite(Character character, Image[] dir) {
		if(character.sprites().spriteDelay() == 0 && character.jumps() == 0) {
			character.sprites().setCurrentSprite(dir
					[(Arrays.asList(dir).indexOf(character.sprites().currentSprite()) + 1) % dir.length]);
		}
	}

	public static void changeSprite(Projectiles projectile, Image[] dir) {
		if(projectile.sprites().spriteDelay() == 0) {
			projectile.sprites().setCurrentSprite(dir[(Arrays.asList(dir).indexOf(projectile.sprites().currentSprite()) + 1) % dir.length]);
		}
	}
}
