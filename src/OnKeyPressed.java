import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class OnKeyPressed implements EventHandler<KeyEvent>{
	
	public void handle(KeyEvent key) {
		
		if(key.getCode() == KeyCode.W && Game.mario().jumps() < 2 && Game.mario().canJump()) {
			Game.mario().setJumps(Game.mario().jumps() + 1);
			Move.jump(Game.mario());
		}
		
		if(key.getCode() == KeyCode.D) {
			Game.mario().setMove(true);
			Game.mario().setRight(true);
		}
		
		if(key.getCode() == KeyCode.A) {
			Game.mario().setMove(true);
			Game.mario().setRight(false);
		}
		
		if(key.getCode() == KeyCode.H) {
			Game.mario().setShooting(true);
		}
		
		if(key.getCode() == KeyCode.G && Game.mario().special().canSpecial()) {
			Game.mario().special().setPhysics(false);
			Game.mario().special().setCooldown(0);
		}
		
		if(key.getCode() == KeyCode.UP && Game.sonic().jumps() < 2 && Game.sonic().canJump() && !Game.sonic().stunned().stunned()) {
			Game.sonic().setJumps(Game.sonic().jumps() + 1);
			Move.jump(Game.sonic());
		}
		
		if(key.getCode() == KeyCode.RIGHT && Game.sonic().special().physics()) {
			Game.sonic().setMove(true);
			Game.sonic().setRight(true);
		}
		
		if(key.getCode() == KeyCode.LEFT && Game.sonic().special().physics()) {
			Game.sonic().setMove(true);
			Game.sonic().setRight(false);
		}
		
		if(key.getCode() == KeyCode.SEMICOLON) {
			Game.sonic().setShooting(true);
		}
		
		if(key.getCode() == KeyCode.L && Game.sonic().special().canSpecial() && !Game.sonic().stunned().stunned()) {
			Game.sonic().special().setCooldown(0);
			Game.sonic().special().setPhysics(false);
		}
		if(key.getCode() == KeyCode.T) {
			System.out.println(Game.sonic().move());
		}
	}
}
