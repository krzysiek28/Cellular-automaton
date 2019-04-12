package automaton;
import cell.Cell;
import cell.coordinates.CellCoordinates;
import cell.states.CellState;
import cell.states.CellStateFactory;
import neighbours.CellNeighborhood;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public abstract class Automaton {

	public Map<CellCoordinates, CellState> cells;

	private CellNeighborhood neighborStrategy;

	private CellStateFactory stateFactory;
	
	public Automaton(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
		stateFactory = cellStateFactory;
		neighborStrategy = cellNeighborhood;
	}

	protected void initialize(){
		cells = new HashMap<>();
		CellCoordinates coords = initialCoordinates();
		CellState state;
		while(hasNextCoordinates(coords)){
			coords = nextCoordinates(coords);
			state = stateFactory.initialState(coords);
			cells.put(coords, state);
		}
	}

	public Automaton nextState(){
		Automaton newAutomaton = newInstance(stateFactory, neighborStrategy);
		CellIterator previousIteration = cellIterator();
		CellIterator nextIteration = newAutomaton.cellIterator();

		while(previousIteration.hasNext()){
			Cell cell = previousIteration.next();
			Set<CellCoordinates> neighborsCoords = neighborStrategy.cellNeighbors(cell.getCoords());
			Set<Cell> neighbors = mapCoordinates(neighborsCoords);
			CellState newState = nextCellState(cell, neighbors);
			nextIteration.next();
			nextIteration.setState(newState);
		}
		return newAutomaton;
	}

	public void insertStructure(Map<? extends CellCoordinates, ? extends CellState> structure){
		cells.putAll(structure);
	}

	public CellIterator cellIterator(){
		return new CellIterator(initialCoordinates());
	}
	
	protected abstract Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood);

	protected abstract boolean hasNextCoordinates(CellCoordinates cellCoordinates);

	protected abstract CellCoordinates initialCoordinates();

	protected abstract CellCoordinates nextCoordinates(CellCoordinates cellCoordinates);

	protected abstract CellState nextCellState(Cell currentCell, Set<Cell> neighborsStates);

	private Set<Cell> mapCoordinates(Set<CellCoordinates> cellCoordinates){
		Set<Cell> mapCell = new HashSet<>();
		CellState state;
		
		for(CellCoordinates cellCoords : cellCoordinates){
			state = cells.get(cellCoords);
			mapCell.add(new Cell(state,cellCoords)); 
		}
		return mapCell;
	}
	
	public class CellIterator{
		private CellCoordinates currentCoords;
		
		public CellIterator(CellCoordinates initialCoordinates){
			this.currentCoords = initialCoordinates;
		}

		public boolean hasNext(){
			return hasNextCoordinates(currentCoords);
		}

		public Cell next(){
			currentCoords = nextCoordinates(currentCoords);
			return new Cell(cells.get(currentCoords),currentCoords);
		}

		public void setState(CellState cellState){
			cells.put(currentCoords, cellState);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cells == null) ? 0 : cells.hashCode());
		result = prime
				* result
				+ ((neighborStrategy == null) ? 0 : neighborStrategy.hashCode());
		result = prime * result
				+ ((stateFactory == null) ? 0 : stateFactory.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Automaton other = (Automaton) obj;
		if (cells == null) {
			if (other.cells != null)
				return false;
		} else if (!cells.equals(other.cells))
			return false;
		if (neighborStrategy == null) {
			if (other.neighborStrategy != null)
				return false;
		} else if (!neighborStrategy.equals(other.neighborStrategy))
			return false;
		if (stateFactory == null) {
			if (other.stateFactory != null)
				return false;
		} else if (!stateFactory.equals(other.stateFactory))
			return false;
		return true;
	}
}
