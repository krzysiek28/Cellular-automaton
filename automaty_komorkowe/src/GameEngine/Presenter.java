package GameEngine;

import java.util.HashMap;
import java.util.Map;

import GameEngine.Automaton.CellIterator;

public class Presenter {
	
	GraphicUserInterface view;
	Automaton currentAutomat;
	private int count = 1;
	private Map<CellCoordinates, CellState> oldCells;
	
	public Presenter(GraphicUserInterface view, Automaton automat) {
		this.view = view;
		currentAutomat = automat;
	}
	
	public void changeAutomaton(Automaton currentAutomat){
		this.currentAutomat = currentAutomat;
		updateView();
	}

	public void nextClicked() {
		if(count>=view.height2d && view.isOneDimention())
			return;
		currentAutomat = currentAutomat.nextState();
		updateView();
		count = count+1;
	}
	
	public void putStructure(Map<CellCoordinates,CellState> map){
		currentAutomat.insertStructure(map);
		updateView();
	}
	
	public void changeAutomatCellState(CellCoordinates coords, CellState state) {
		  Map<CellCoordinates, CellState> cell = new HashMap<>(1);
		  cell.put(coords, state);
		  currentAutomat.insertStructure(cell);
		 }
	
	private Coords2D changeCoords1DimTo2Dim(Coords1D coords1d){
		
		Coords2D coords = new Coords2D(coords1d.x,0);
		for(int j = 0; j < view.height2d; j++){
			for(int i = 0; i < view.width2d; i++ ){
				
			}
		}
		return coords;
	}

	private void updateView() {
		view.resizeBoardSizeInCellsTo(view.getMapWidth(), view.getMapHeight());
		CellIterator cellIter = currentAutomat.cellIterator();
		int y;
		
		while(cellIter.hasNext()){
			Cell cell = cellIter.next();
			Coords2D coords;
			if(view.isOneDimention()){
				coords = changeCoords1DimTo2Dim((Coords1D)cell.coords);
				y = count;
			}
			else{
				coords = (Coords2D) cell.coords;
				y = coords.y;
			}			
			BinaryState bstate = (BinaryState) cell.state;
			view.changeCell(coords.x, y, bstate);
			
		}
		if(view.isOneDimention()){
			oldCells=view.supportOneDim();
			currentAutomat.insertStructure(oldCells);
			for(Map.Entry<CellCoordinates,CellState> c : oldCells.entrySet()){
				CellCoordinates coord = c.getKey();
				CellState state = c.getValue();
				Coords2D coords = (Coords2D) coord;
				BinaryState bstate = (BinaryState) state;
				if(coords.y != count+1)
					view.changeCell(coords.x, coords.y, bstate);
			}
		}	
	}
}
	
/*		private void updateView() {
		view.resizeBoardSizeInCellsTo(view.getMapWidth(), view.getMapHeight());
		CellIterator cellIter = currentAutomat.cellIterator();
		int y;
			
		while(cellIter.hasNext()){
			Cell cell = cellIter.next();
			Coords2D coords;
			if(view.isOneDimention()){
				coords = changeCoords1DimTo2Dim((Coords1D)cell.coords);
				y = count;
			}
			else{
				coords = (Coords2D) cell.coords;
				y = coords.y;
			}			
			
			BinaryState bstate = (BinaryState) cell.state;
			view.changeCell(coords.x, y, bstate);
		}
	}
	

*/
