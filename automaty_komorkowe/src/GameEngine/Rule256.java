package GameEngine;

import java.util.Set;

public class Rule256 extends Automaton1Dim{

	private int rule;
	public Rule256(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood, int size, int rule){
		super(cellStateFactory, cellNeighborhood, size);
		this.rule = rule;
	}

	@Override
	public Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood){
		return new Rule256(cellStateFactory, cellNeighborhood, size, rule);
	}

	@Override
	public CellState nextCellState(Cell currentCell, Set<Cell> neighborState) {
		int[] numberOfRule = new int[8];
		for(int i=0; i<8; i++)
			numberOfRule[i] = 0;
		int number = rule;
		//konwersja typu np 30 = 00011110
		int i = 7;
		while(number != 0){
			numberOfRule[i--] = number % 2;
			number /= 2;
		}
		
		CellState[] currentCellStates = new CellState[3];
		i = 0;
		for(Cell cell : neighborState){
			if(cell.state==BinaryState.ALIVE){
				currentCellStates[i] = BinaryState.ALIVE;
			} else{
				currentCellStates[i] = BinaryState.DEAD;
			}
			i++;	
		}
		
		//dla 1 bitu
		if(currentCellStates[0] == BinaryState.ALIVE && 
			currentCellStates[1] == BinaryState.ALIVE && 
			currentCellStates[2] == BinaryState.ALIVE)
		{
			if(numberOfRule[0]==1)
				return BinaryState.ALIVE;
			else
				return BinaryState.DEAD;			
		}
		//dla 2 bitu
		else if(currentCellStates[0] == BinaryState.ALIVE && 
			currentCellStates[1] == BinaryState.ALIVE && 
			currentCellStates[2] == BinaryState.DEAD)
		{
			if(numberOfRule[1]==1)
				return BinaryState.ALIVE;
			else
				return BinaryState.DEAD;			
		}	
		//dla 3 bitu
		else if(currentCellStates[0] == BinaryState.ALIVE && 
			currentCellStates[1] == BinaryState.DEAD && 
			currentCellStates[2] == BinaryState.ALIVE)
		{
			if(numberOfRule[2]==1)
				return BinaryState.ALIVE;
			else
				return BinaryState.DEAD;	
		}	
		//dla 4 bitu
		else if(currentCellStates[0] == BinaryState.ALIVE && 
			currentCellStates[1] == BinaryState.DEAD && 
			currentCellStates[2] == BinaryState.DEAD)
		{
			if(numberOfRule[3]==1)
				return BinaryState.ALIVE;
			else
				return BinaryState.DEAD;			
		}	
		//dla 5 bitu
		else if(currentCellStates[0] == BinaryState.DEAD && 
			currentCellStates[1] == BinaryState.ALIVE && 
			currentCellStates[2] == BinaryState.ALIVE)
		{
			if(numberOfRule[4]==1)
				return BinaryState.ALIVE;
			else
				return BinaryState.DEAD;			
		}	
		//dla 6 bitu
		else if(currentCellStates[0] == BinaryState.DEAD && 
			currentCellStates[1] == BinaryState.ALIVE && 
			currentCellStates[2] == BinaryState.DEAD)
		{
			if(numberOfRule[5]==1)
				return BinaryState.ALIVE;
			else
				return BinaryState.DEAD;	
		}	
		//dla 7 bitu
		else if(currentCellStates[0] == BinaryState.DEAD && 
			currentCellStates[1] == BinaryState.DEAD && 
			currentCellStates[2] == BinaryState.ALIVE)
		{
			if(numberOfRule[6]==1)
				return BinaryState.ALIVE;				
			else
				return BinaryState.DEAD;			
		}	
		//dla 8 bitu
		else /*if(currentCellStates[0] == BinaryState.DEAD && 
			currentCellStates[1] == BinaryState.DEAD && 
			currentCellStates[2] == BinaryState.DEAD) */
		{
			if(numberOfRule[7]==1)
				return BinaryState.ALIVE;
			else
				return BinaryState.DEAD;	
				
				
		}	
	}

}
