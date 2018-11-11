import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application{
	
	private static Canvas canvas = new Canvas(1000, 500);
	private static GraphicsContext gc = canvas.getGraphicsContext2D();
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage window) throws Exception {
		try {
		
		Group group = new Group();
		group.getChildren().addAll(canvas, Game.wins()[0], Game.wins()[1]);
		
		Scene scene = new Scene(group);
		scene.setOnKeyPressed(new OnKeyPressed());
		scene.setOnKeyReleased(new OnKeyReleased());
		
		window.setScene(scene);
		window.setTitle("Super Smash Bros");
		window.show();
	;	
		for(int i = 0; i < 2; i++) {
			Game.wins()[i].setTextFill(Color.WHITE);
			Game.wins()[i].setFont(new Font("Comicsans", 100));
		}
		Game.wins()[0].relocate(0, -30);
		Game.wins()[1].relocate(1000 - Game.wins()[1].getWidth() * 10, -30);
		
		Timeline gameLoop = new Timeline();
	    gameLoop.setCycleCount(Timeline.INDEFINITE);
	    double interval = (double) (Math.round(((double) 1 / 50) * 1000000)) / 1000;
	    KeyFrame keyframe = new KeyFrame(Duration.millis(interval), new GameLoop());
	    gameLoop.getKeyFrames().add(keyframe);
	    gameLoop.play();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static GraphicsContext gc() {
		return gc;
	}

	public static void setGc(GraphicsContext gc) {
		Main.gc = gc;
	}
}
