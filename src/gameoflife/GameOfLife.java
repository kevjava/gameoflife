package gameoflife;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class GameOfLife extends BasicGame {
	
	private static Logger logger = Logger.getLogger(GameOfLife.class.getName());

	public GameOfLife(String gameName) {
		super(gameName);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
	}

	@Override
	public void render(GameContainer gc, Graphics graphics) throws SlickException {
		graphics.drawString("Howdy!", 100, 100);
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		
	}

	/**
	 * THE BEGINNING OF ALL THINGS (incipio omnium rerum)
	 * @param args Command-line arguments.
	 */
	public static void main(String... args) {
		try {
			AppGameContainer appGameContainer = new AppGameContainer(new GameOfLife("The Game of Life"));
			appGameContainer.setDisplayMode(640, 480, false);
			appGameContainer.start();
		} catch (SlickException e) {
			logger.log(Level.SEVERE, "Whoops: " + e.getMessage(), e);
		}
	}
}
