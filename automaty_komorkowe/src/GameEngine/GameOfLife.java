package GameEngine;
import java.util.List;
import java.util.Set;

public class GameOfLife extends Automaton2Dim{

	private List<Integer> aliveRules;
	private List<Integer> deadRules;
	public GameOfLife(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood, int width, int height, List<Integer> aliveRules, List<Integer> deadRules){
		//wywolanie konstruktra z klasy nadrzednej
		super(cellStateFactory, cellNeighborhood, width, height);	
		this.aliveRules = aliveRules;
		this.deadRules = deadRules;
	}
	
	public Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood){
		//zwracam nowy stan gameOfLife
		return new GameOfLife(cellStateFactory, cellNeighborhood, width, height, aliveRules, deadRules);
	}

	@Override
	public CellState nextCellState(Cell currentCell, Set<Cell> neighborState){
		//przekazuje stan komorki i stan sasiadow
		//sprawdzamy ilosc zywych sasiadow
		//iteruje komorki po zbiorze neighborstate w 23/3 jest to 8 sasiadow
		int livingCount=0;
		for(Cell cell : neighborState){
			if(cell.state==BinaryState.ALIVE)
				livingCount++;
		}
		//sprawdzamy czy badana komorka jest zywa
		if(currentCell.state==BinaryState.ALIVE){
			for(Integer element : aliveRules){
				if(livingCount==element)
					return BinaryState.ALIVE;
			}
			return BinaryState.DEAD;
		}
		else {
			for(Integer element : deadRules){
				if(livingCount==element)
					return BinaryState.ALIVE;
			}
			return BinaryState.DEAD;
		}
	}
	
}

