package gameoflife;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import gameoflife.initializer.GameOfLifeInitializer;
import gameoflife.initializer.GosperGliderInitializer;

import org.junit.Test;

public class GameOfLifeLogicTest {

	int _frame = 0;

	@Test
	public void test_gosperGlider() {
		GameOfLifeLogic logic = new GameOfLifeLogic(60, 60); // Room for a 5x5 grid.
		GameOfLifeInitializer initializer = new GosperGliderInitializer();
		logic.setInitializer(initializer);
		logic.init();

		_frame = 1;
		checkGrid(logic, new char[][] { //
				{ '.', 'S', '.', '.', '.' }, //
						{ '.', '.', 'S', '.', '.' }, //
						{ 'S', 'S', 'S', '.', '.' }, //
						{ '.', '.', '.', '.', '.' }, //
						{ '.', '.', '.', '.', '.' } }); //

		logic.tick();
		_frame++;

		checkGrid(logic, new char[][] { //
				{ '.', 'l', '.', '.', '.' }, //
						{ 'S', '.', 'A', '.', '.' }, //
						{ 'l', 'A', 'A', '.', '.' }, //
						{ '.', 'S', '.', '.', '.' }, //
						{ '.', '.', '.', '.', '.' } }); //

		logic.tick();
		_frame++;

		checkGrid(logic, new char[][] { //
				{ '.', '.', '.', '.', '.' }, //
						{ 'l', '.', 'A', '.', '.' }, //
						{ 'S', 'o', 'A', '.', '.' }, //
						{ '.', 'A', 'S', '.', '.' }, //
						{ '.', '.', '.', '.', '.' } }); //

		logic.tick();
		_frame++;

		checkGrid(logic, new char[][] { //
				{ '.', '.', '.', '.', '.' }, //
						{ '.', 'S', 'l', '.', '.' }, //
						{ 'l', '.', 'A', 'S', '.' }, //
						{ '.', 'A', 'A', '.', '.' }, //
						{ '.', '.', '.', '.', '.' } }); //

		logic.tick();
		_frame++;

		checkGrid(logic, new char[][] { //
				{ '.', '.', '.', '.', '.' }, //
						{ '.', 'l', 'S', '.', '.' }, //
						{ '.', '.', 'o', 'A', '.' }, //
						{ '.', 'A', 'A', 'S', '.' }, //
						{ '.', '.', '.', '.', '.' } }); //

		CellGrid grid = logic.getCellGrid();
	}

	private void checkGrid(GameOfLifeLogic logic, char[][] statuses) {
		for (int y = 0; y < logic.getCellGrid().getHeight(); y++) {
			for (int x = 0; x < logic.getCellGrid().getWidth(); x++) {
				char statusChar = statuses[y][x];
				CellState expectedState = getState(statusChar);
				Cell cell = logic.getCellGrid().getCell(x, y);
				assertEquals(String.format("Failed on frame %d, cell (%d, %d).", _frame, x, y), expectedState,
						cell.getState());
			}
		}
	}

	private CellState getState(char statusChar) {
		switch (statusChar) {
		case '.':
			return CellState.DEAD;
		case 'S':
			return CellState.SPAWNING;
		case 'A':
			return CellState.ALIVE;
		case 'o':
			return CellState.OVERCROWDED;
		case 'l':
			return CellState.LONELY;
		default:
			fail("Unexpected status character");
			return null;
		}
	}
}
