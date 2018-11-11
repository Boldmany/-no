import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class OnKeyReleased implements EventHandler<KeyEvent>{

	public void handle(KeyEvent key) {
		
		if(key.getCode() == KeyCode.W && Game.mario().jumps() < 3) {
			Game.mario().setCanJump(true);
		}
		
		if(key.getCode() == KeyCode.D && Game.mario().right()) {
			Game.mario().setMove(false);
			if(!Game.mario().gravity()) {
				Game.mario().sprites().setCurrentSprite(Game.mario().sprites().stand()[0]);
			}
		}
		
		if(key.getCode() == KeyCode.A && !Game.mario().right()) {
			Game.mario().setMove(false);
			if(!Game.mario().gravity()) {
				Game.mario().sprites().setCurrentSprite(Game.mario().sprites().stand()[1]);
			}
		}
		
		if(key.getCode() == KeyCode.H) {
			Game.mario().setShooting(false);
		}
		
		if(key.getCode() == KeyCode.UP && Game.sonic().jumps() < 3) {
			Game.sonic().setCanJump(true);
		}
		
		if(key.getCode() == KeyCode.RIGHT && Game.sonic().right()) {
			Game.sonic().setMove(false);
			if(!Game.sonic().gravity() && !Game.sonic().stunned().stunned() && Game.sonic().special().physics()) {
				Game.sonic().sprites().setCurrentSprite(Game.sonic().sprites().stand()[0]);
			}
		}
		
		if(key.getCode() == KeyCode.LEFT && !Game.sonic().right()) {
			Game.sonic().setMove(false);
			if(!Game.sonic().gravity() && !Game.sonic().stunned().stunned() && Game.sonic().special().physics()) {
				Game.sonic().sprites().setCurrentSprite(Game.sonic().sprites().stand()[1]);
			}
		}
		
		if(key.getCode() == KeyCode.SEMICOLON && !Game.sonic().right()) {
			Game.sonic().setShooting(false);
		}
		
		if(key.getCode() == KeyCode.L) {
			Game.sonic().setShooting(false);
		}
	}

}
