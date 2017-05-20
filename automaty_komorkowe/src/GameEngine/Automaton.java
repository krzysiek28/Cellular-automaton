package GameEngine;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public abstract class Automaton {

	Map<CellCoordinates,CellState> cells;
	private CellNeighborhood neighborStrategy;
	private CellStateFactory stateFactory;
	
	public Automaton(CellStateFactory cellStateFactory,	CellNeighborhood cellNeighborhood) {
		//przekaujemy zmienne z klasy automaton
		stateFactory = cellStateFactory;
		neighborStrategy = cellNeighborhood;
	}
	public Automaton nextState(){
		//przekazuje nam nowy stan z game of life
		Automaton newAutomat = newInstance(stateFactory, neighborStrategy);
		CellIterator previousIter = cellIterator();
		CellIterator nextIter = newAutomat.cellIterator();
				
		//dziala dopoki jest nastepna komorka az do konca mapy
		while(previousIter.hasNext()){
			Cell cell = previousIter.next();
			//zapisuje s¹siadów .cellNeighbors a .next zapisuje stan komorki a nastepnie iteruje na nastepna 
			//cords publiczne zapamietywanie komorki
			Set<CellCoordinates> neighborsCoords = neighborStrategy.cellNeighbors(cell.coords);
			//ustawiamy do mapy
			Set<Cell> neighbors = mapCoordinates(neighborsCoords);
			//sprawdza nowy stan komórki z GameOfLife
			CellState newState = nextCellState(cell, neighbors);
			nextIter.next();
			nextIter.setState(newState);
			//System.out.println(previousIter.currentCoords);
		}
		
		return newAutomat;
	}
	public void insertStructure(Map<? extends CellCoordinates, ? extends CellState> structure){
		//dodaje wszystkie komorki do mapy lub je nadpisuje
		cells.putAll(structure);
	}

	//zaininjowac nowy celliterator
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
			//ustawia nam komórki na (-1,0)
			this.currentCoords = initialCoordinates;
		}		
		public boolean hasNext(){
			//czy przeiterowa³o nam wszystkie komórki
			return hasNextCoordinates(currentCoords);
		}
		public Cell next(){
			//zwiekszam currentCoords o 1 komorke
			currentCoords = nextCoordinates(currentCoords);
			//zapisuje wspolrzedne
			Cell cell = new Cell(cells.get(currentCoords),currentCoords);
			return cell;
		}
		public void setState(CellState cellState){
			//tworzy komorki do mapy
			cells.put(currentCoords, cellState);
		}
	}
	
	public void initialize(){
		cells = new HashMap<>();
		CellCoordinates coords = initialCoordinates();
		CellState state;
		while(hasNextCoordinates(coords)){
			coords = nextCoordinates(coords);
			state = stateFactory.initialState(coords);
			cells.put(coords, state);
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
