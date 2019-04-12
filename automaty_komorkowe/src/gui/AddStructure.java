package gui;

import cell.coordinates.CellCoordinates;
import cell.coordinates.Coords2D;
import cell.states.BinaryState;
import cell.states.CellState;

import java.util.HashMap;
import java.util.Map;

public class AddStructure {
	
	private int width;

	private int height;

	public AddStructure(int width, int height){
		this.width = width;
		this.height = height;
	}

	public Map<CellCoordinates, CellState> putBoat(CellCoordinates cellCoordinates){
		Map<CellCoordinates,CellState> cells = new HashMap<>();	
		Coords2D coords = (Coords2D) cellCoordinates;
		int x = coords.getX();
		int y = coords.getY();
		
		cells.put(new Coords2D(x,y), BinaryState.ALIVE);
		if(x+1<width)
			cells.put(new Coords2D(x+1,y), BinaryState.ALIVE);
		if(y+1<height)
			cells.put(new Coords2D(x,y+1), BinaryState.ALIVE);
		if(x+2<width && y+1<height)
			cells.put(new Coords2D(x+2,y+1), BinaryState.ALIVE);
		if(x+1<width && y+2<height)
			cells.put(new Coords2D(x+1,y+2), BinaryState.ALIVE);
		return cells;
	}
	
	public Map<CellCoordinates,CellState> putKite(CellCoordinates cellCoordinates){
		Map<CellCoordinates,CellState> cells = new HashMap<>();	
		Coords2D coords = (Coords2D) cellCoordinates;
		int x = coords.getX();
		int y = coords.getY();
		
		cells.put(new Coords2D(x,y), BinaryState.ALIVE);
		if(x+1<width)
			cells.put(new Coords2D(x+1,y), BinaryState.ALIVE);
		if(x+2<width)
			cells.put(new Coords2D(x+2,y), BinaryState.ALIVE);
		if(y+1<height)
			cells.put(new Coords2D(x,y+1), BinaryState.ALIVE);
		if(x+1<width && y+2<height)
			cells.put(new Coords2D(x+1,y+2), BinaryState.ALIVE);
		return cells;
	}
	

	
	public Map<CellCoordinates,CellState> putBlock(CellCoordinates cellCoordinates){
		Map<CellCoordinates,CellState> cells = new HashMap<>();	
		Coords2D coords = (Coords2D) cellCoordinates;
		int x = coords.getX();
		int y = coords.getY();
		
		cells.put(new Coords2D(x,y), BinaryState.ALIVE);
		if(x+1<width)
			cells.put(new Coords2D(x+1,y), BinaryState.ALIVE);
		if(y+1<height)
			cells.put(new Coords2D(x,y+1), BinaryState.ALIVE);
		if(x+1<width && y+1<height)
			cells.put(new Coords2D(x+1,y+1), BinaryState.ALIVE);
		return cells;
	}

	public Map<CellCoordinates,CellState> putGun(CellCoordinates cellCoordinates){
		Map<CellCoordinates,CellState> cells = new HashMap<>();	
		Coords2D coords = (Coords2D) cellCoordinates;
		int x = coords.getX();
		int y = coords.getY();
		
		cells.put(new Coords2D(x,y+4), BinaryState.ALIVE);
		cells.put(new Coords2D(x,y+5), BinaryState.ALIVE);
		cells.put(new Coords2D(x+1,y+4), BinaryState.ALIVE);
		cells.put(new Coords2D(x+1,y+5), BinaryState.ALIVE);
		cells.put(new Coords2D(x+10,y+4), BinaryState.ALIVE);
		cells.put(new Coords2D(x+10,y+5), BinaryState.ALIVE);
		cells.put(new Coords2D(x+10,y+6), BinaryState.ALIVE);
		cells.put(new Coords2D(x+11,y+3), BinaryState.ALIVE);
		cells.put(new Coords2D(x+12,y+2), BinaryState.ALIVE);
		cells.put(new Coords2D(x+13,y+2), BinaryState.ALIVE);
		cells.put(new Coords2D(x+11,y+7), BinaryState.ALIVE);
		cells.put(new Coords2D(x+12,y+8), BinaryState.ALIVE);
		cells.put(new Coords2D(x+13,y+8), BinaryState.ALIVE);
		cells.put(new Coords2D(x+14,y+5), BinaryState.ALIVE);
		cells.put(new Coords2D(x+15,y+3), BinaryState.ALIVE);
		cells.put(new Coords2D(x+16,y+4), BinaryState.ALIVE);
		cells.put(new Coords2D(x+16,y+5), BinaryState.ALIVE);
		cells.put(new Coords2D(x+16,y+6), BinaryState.ALIVE);
		cells.put(new Coords2D(x+15,y+7), BinaryState.ALIVE);
		cells.put(new Coords2D(x+17,y+5), BinaryState.ALIVE);
		cells.put(new Coords2D(x+20,y+2), BinaryState.ALIVE);
		cells.put(new Coords2D(x+20,y+3), BinaryState.ALIVE);
		cells.put(new Coords2D(x+20,y+4), BinaryState.ALIVE);
		cells.put(new Coords2D(x+21,y+2), BinaryState.ALIVE);
		cells.put(new Coords2D(x+21,y+3), BinaryState.ALIVE);
		cells.put(new Coords2D(x+21,y+4), BinaryState.ALIVE);
		cells.put(new Coords2D(x+22,y+1), BinaryState.ALIVE);
		cells.put(new Coords2D(x+22,y+5), BinaryState.ALIVE);
		cells.put(new Coords2D(x+24,y), BinaryState.ALIVE);
		cells.put(new Coords2D(x+24,y+1), BinaryState.ALIVE);
		cells.put(new Coords2D(x+24,y+5), BinaryState.ALIVE);
		cells.put(new Coords2D(x+24,y+6), BinaryState.ALIVE);
		cells.put(new Coords2D(x+34,y+2), BinaryState.ALIVE);
		cells.put(new Coords2D(x+34,y+3), BinaryState.ALIVE);
		cells.put(new Coords2D(x+35,y+2), BinaryState.ALIVE);
		cells.put(new Coords2D(x+35,y+3), BinaryState.ALIVE);
		
		return cells;
	}
}
