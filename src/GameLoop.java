import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;


public class GameLoop implements EventHandler<ActionEvent>{

	private Image background = new Image("file:resources/background.png");

	public GameLoop() {
		creatBrick(Game.bricks());
	}

	public void handle(ActionEvent ev) {
		Main.gc().clearRect(0, 0, 1000, 500);

		Main.gc().drawImage(background, 0, 0);

		for(int i = 0; i < Game.bricks().size(); i++) {
			Main.gc().drawImage(Game.bricks().get(i).image(),
					Game.bricks().get(i).coord().x(), Game.bricks().get(i).coord().y());
		}
		
		Main.gc().drawImage
		(Game.bar()[0], 0, 450, Game.bar()[0].getWidth(), Game.bar()[0].getHeight(),
				0, 450, Game.bar()[0].getWidth() - (Game.bar()[0].getWidth() * ((100 - Game.characters()[0].health()) / 100)), Game.bar()[0].getHeight());
		
		Main.gc().drawImage
		(Game.bar()[1], 1000, 450, -Game.bar()[1].getWidth(), Game.bar()[1].getHeight(),
				1000, 450, -Game.bar()[1].getWidth() + (Game.bar()[1].getWidth() * ((100 - Game.characters()[1].health()) / 100)), Game.bar()[1].getHeight());

		Main.gc().drawImage
		(Game.bar()[2], 0, 490, Game.bar()[2].getWidth(), Game.bar()[2].getHeight(),
				0, 490, Game.characters()[0].special().cooldown() * (Game.bar()[2].getWidth() / 240), Game.bar()[2].getHeight());
		
		Main.gc().drawImage
		(Game.bar()[2], 1000, 490, -Game.bar()[2].getWidth(), Game.bar()[2].getHeight(),
				1000, 490, -Game.characters()[1].special().cooldown() * (Game.bar()[2].getWidth() / 240), Game.bar()[2].getHeight());
		
		for(int i = 0; i < 2; i++) {
			Main.gc().drawImage(Game.characters()[i].sprites().currentSprite(), 
					Game.characters()[i].coord().x(), Game.characters()[i].coord().y());
			for(int p = 0; p < Game.characters()[i].projectiles().size(); p++) {
				Main.gc().drawImage(Game.characters()[i].projectiles().get(p).sprites().currentSprite(),
					Game.characters()[i].projectiles().get(p).coord().x(), Game.characters()[i].projectiles().get(p).coord().y());
			}

		}
		
		for(int i = 0; i < Game.characters().length; i++) { 
			Move.movement(Game.characters()[i], Game.characters(), i);
			for(int p = 0; p < Game.characters()[i].projectiles().size(); p++) {
				Move.projectileMovement(Game.characters()[i].projectiles().get(p), Game.characters(), i);
			}
		}
		
		if(Game.labelTimer() != 0) {
			Game.setLabelTimer(Game.labelTimer() - 1);
		}
		else {
			for(int i = 0; i < 2; i++) {
				Game.wins()[i].setVisible(false);
			}
		}
	}

	public void creatBrick(ArrayList<Bricks> bricks) {

		int currentBrick = 0;
		for(int i = 0; i < 20; i++) {
			bricks.add(new Bricks(new Coordinates(i* 50, 450)));
			currentBrick++;
		}
		try {
			FileReader fileReader = new FileReader("resources/level.txt");

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line;
			while((line = bufferedReader.readLine()) != null) {
				String[] info = line.split(",");
				bricks.add(new Bricks(new Coordinates(Integer.parseInt(info[0]), Integer.parseInt(info[1]))));
				currentBrick++;

				if(info.length == 4) {
					bricks.get(currentBrick - 1).setIgnoreRight(true);
					bricks.get(currentBrick - 1).setIgnoreLeft(true);
				}
				else if(info.length == 3) {
					if(info[2].equals("0")) {
						bricks.get(currentBrick - 1).setIgnoreRight(true);
					}
					else {
						bricks.get(currentBrick - 1).setIgnoreLeft(true);
					}
				}

			}   
			bufferedReader.close();         
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
