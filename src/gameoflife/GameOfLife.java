package gameoflife;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class GameOfLife extends BasicGame {

	private static Logger logger = Logger.getLogger(GameOfLife.class.getName());
	private static final int INITIAL_TICK_TIME = 250; // milliseconds
	private static final int _tickTime = INITIAL_TICK_TIME;

	private long _pauseTime = 0;
	private long _lastTick = 0;
	private boolean _fullScreen = false;
	private boolean _paused = false;
	private boolean _statistics = false;
	private int _generations = 0;

	private GameOfLifeLogic _logic;
	private GridView _gridView;

	public GameOfLife(String gameName) {
		super(gameName);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		_logic = new GameOfLifeLogic(gc.getWidth(), gc.getHeight());
		_logic.init();
		_gridView = new GridView(_logic.getCellsWide(), _logic.getCellsHigh(), _logic.getCellWidth(),
				_logic.getCellHeight(), gc.getWidth(), gc.getHeight());
		_lastTick = System.currentTimeMillis();
		gc.setShowFPS(false);
	}

	@Override
	public void render(GameContainer gc, Graphics graphics) throws SlickException {
		long endTime = (_paused) ? _pauseTime : System.currentTimeMillis();
		float tickPercentage = ((float) (endTime - _lastTick) / (float) _tickTime);
		Map<String, Object> statistics = (_statistics) ? getStatistics() : new HashMap<String, Object>();
		_gridView.draw(graphics, _logic.getCellGrid(), tickPercentage, statistics);
	}

	private Map<String, Object> getStatistics() {
		Map<String, Object> statistics = new HashMap<String, Object>();
		statistics.put("Generations", _generations);
		statistics.put("Living cells", _logic.getLivingCellCount());
		return statistics;
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			gc.exit(); // Quick escape.
		} else if (input.isKeyPressed(Input.KEY_F)) {
			_fullScreen = !_fullScreen;
			gc.setFullscreen(_fullScreen);
		} else if (input.isKeyPressed(Input.KEY_R)) {
			gc.reinit();
		} else if (input.isKeyPressed(Input.KEY_P)) {
			_paused = !_paused;
			if (_paused) {
				_pauseTime = System.currentTimeMillis();
			}
		} else if (input.isKeyPressed(Input.KEY_S)) {
			_statistics = !_statistics;
		}

		if (!_paused) {
			long timeSinceLastTick = System.currentTimeMillis() - _lastTick;
			if (timeSinceLastTick > _tickTime) {
				_lastTick = System.currentTimeMillis();
				_logic.tick();
				_generations++;
			}
		}
	}

	/**
	 * Life begins.
	 * 
	 * @param args
	 *            Command-line arguments.
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
