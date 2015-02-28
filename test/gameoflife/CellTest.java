package gameoflife;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class CellTest {

	Cell _cell;

	Cell _nw = new Cell(0, 0);
	Cell _n = new Cell(1, 0);
	Cell _ne = new Cell(2, 0);
	Cell _w = new Cell(0, 1);
	Cell _e = new Cell(2, 1);
	Cell _sw = new Cell(0, 2);
	Cell _s = new Cell(1, 2);
	Cell _se = new Cell(2, 2);

	Collection<Cell> _neighbors;

	@Before
	public void setUp() {
		_cell = new Cell(1, 1);

		_neighbors = new ArrayList<Cell>();
		_neighbors.add(_nw);
		_neighbors.add(_n);
		_neighbors.add(_ne);
		_neighbors.add(_w);
		_neighbors.add(_e);
		_neighbors.add(_sw);
		_neighbors.add(_s);
		_neighbors.add(_se);

		_cell.setNeighbors(_neighbors);
	}

	@Test
	public void test_deadToDead() {
		for (Cell c : _neighbors) {
			assertTrue(CellState.DEAD.equals(c.getState()));
		}
		assertTrue(CellState.DEAD.equals(_cell.getState()));
		assertEquals(8, _cell.getNeighbors().size());

		_cell.preUpdate();
		_cell.update();

		for (Cell c : _neighbors) {
			assertTrue(CellState.DEAD.equals(c.getState()));
		}
		assertTrue(CellState.DEAD.equals(_cell.getState()));
	}

	@Test
	public void test_aliveToAliveTwo() {
		_nw.setState(CellState.ALIVE);
		_sw.setState(CellState.ALIVE);
		_cell.setState(CellState.ALIVE);

		_cell.preUpdate();
		_cell.update();

		assertEquals(CellState.ALIVE, _cell.getState());
	}

	@Test
	public void test_aliveToAliveThree() {
		_nw.setState(CellState.ALIVE);
		_ne.setState(CellState.ALIVE);
		_sw.setState(CellState.ALIVE);
		_cell.setState(CellState.ALIVE);

		_cell.preUpdate();
		_cell.update();

		assertEquals(CellState.ALIVE, _cell.getState());
	}

	@Test
	public void test_aliveToOvercrowded() {
		_nw.setState(CellState.ALIVE);
		_ne.setState(CellState.ALIVE);
		_sw.setState(CellState.ALIVE);
		_se.setState(CellState.ALIVE);

		_cell.setState(CellState.ALIVE);

		_cell.preUpdate();
		_cell.update();

		assertEquals(CellState.OVERCROWDED, _cell.getState());
	}

	@Test
	public void test_deadToSpawning() {
		_nw.setState(CellState.ALIVE);
		_ne.setState(CellState.ALIVE);
		_sw.setState(CellState.ALIVE);

		_cell.preUpdate();
		_cell.update();

		assert (CellState.SPAWNING.equals(_cell.getState()));
	}
}
