import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.image.Image;


public class Game {
	
	private static Mario mario = new Mario(30, 5, new Coordinates(0, 100));
	private static Sonic sonic = new Sonic(20, 7, new Coordinates(960, 100));
	private static ArrayList<Bricks> bricks = new ArrayList<Bricks>();
	private static Character[] characters = {mario, sonic};
	private static Image[] bar = {new Image("file:resources/greenBar.png"), new Image("file:resources/redBar.png"), new Image("file:resources/blueBar.png")};
	private static Label[] wins = {new Label(Integer.toString(mario.loses())), new Label(Integer.toString(sonic.loses()))};
	private static int labelTimer = 120;
	
	public static void restart() {
		Game.mario().setCoord(new Coordinates(0, 100));
		Game.sonic().setCoord(new Coordinates(950, 100));
		for(int i = 0; i < 2; i++) {
			characters[i].projectiles().clear();
			characters[i].special().setHit(false);
			characters[i].stunned().setStunned(false);
			characters[i].stunned().setDuration(0);
			characters[i].setHealth(100);
			characters[i].special().setDuration(0);
			characters[i].special().setPhysics(true);
			characters[i].special().setCanSpecial(true);
			characters[i].special().setCooldown(240);
			wins[i].setVisible(true);
		}
		wins[0].setText((Integer.toString(characters[1].loses())));
		wins[1].setText((Integer.toString(characters[0].loses())));
		setLabelTimer(120);
		//System.out.println(Game.characters[1].loses());
		//System.out.println(Game.characters[0].loses());
	}
	
	public static Mario mario() {
		return mario;
	}

	public static void setMario(Mario mario) {
		Game.mario = mario;
	}

	public static Character[] characters() {
		return characters;
	}

	public static void setCharacters(Character[] characters) {
		Game.characters = characters;
	}

	public static ArrayList<Bricks> bricks() {
		return bricks;
	}

	public static void setBricks(ArrayList<Bricks> bricks) {
		Game.bricks = bricks;
	}

	public static Sonic sonic() {
		return sonic;
	}
	
	public static void setSonic(Sonic sonic) {
		Game.sonic = sonic;
	}

	public static Image[] bar() {
		return bar;
	}

	public static void setBar(Image[] bar) {
		Game.bar = bar;
	}

	public static Label[] wins() {
		return wins;
	}

	public static void setWins(Label[] wins) {
		Game.wins = wins;
	}

	public static int labelTimer() {
		return labelTimer;
	}

	public static void setLabelTimer(int labelTimer) {
		Game.labelTimer = labelTimer;
	}
}
