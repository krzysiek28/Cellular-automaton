package gui;

import cell.coordinates.CellCoordinates;
import cell.coordinates.Coords1D;
import cell.coordinates.Coords2D;
import cell.states.BinaryState;
import cell.states.CellState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class Board extends JPanel {

	private static final long serialVersionUID = 1L;

	GraphicUserInterface view;

	AddStructure addStructure;

	Map<CellCoordinates, CellState> putCells = new HashMap<>();
	
	private BinaryState[][] cells;
	private int xCells;
	private int yCells;
	
	private Color borderColor = Color.BLACK;
	private Color fillAliveColor = Color.BLACK;
	private Color fillDeadColor = Color.WHITE;
	public Map<CellCoordinates,CellState> oldMapState = new HashMap<>();

	public Board(int widthInCells,int heightInCells, GraphicUserInterface view, Structures struct) {
		this.resizeBoardSizeInCellsTo(widthInCells, heightInCells);
		this.view = view;
	}
	
	@Override 
	public void paintComponent(Graphics g) {
		Graphics2D canvas = (Graphics2D)g;
		for (int x = 0; x < xCells; x++) {
			for (int y = 0; y < yCells; y++) {
				Color fillColor = getColorOfCell(x,y);
				drawCell(canvas, x, y, fillColor);
			}
		}
	}
	
	private Color getColorOfCell(int x, int y) {
		BinaryState c = cells[x][y];
		if (c == BinaryState.DEAD) {
			return fillDeadColor;
		} else if (c == BinaryState.ALIVE) {
			return fillAliveColor;
		} else {
			throw new IllegalStateException(String.format("state of the cell(%d,%d) is: %d",x,y,c));
		}
	}

	private void drawCell(Graphics2D canvas, int x, int y, Color fillColor) {		
		int physicalCellWidht = physicalCellWidth();
		int physicalCellHeight = physicalCellHeight();
		int physicalX = physicalCellWidht * x;
		int physicalY = physicalCellHeight * y;
		canvas.setColor(fillColor);
		canvas.fillRect(physicalX, physicalY, physicalCellWidht, physicalCellHeight);
		canvas.setColor(borderColor);
		canvas.drawRect(physicalX, physicalY, physicalCellWidht, physicalCellHeight);
	}
	
	public void resizeBoardSizeInCellsTo(int xCells,int yCells) {
		this.xCells = xCells;
		this.yCells = yCells;
		this.cells = new BinaryState[xCells][yCells];

		for (int x = 0; x < xCells; x++) {
			for (int y = 0; y < yCells; y++) {
				cells[x][y] = BinaryState.DEAD;
			}
		}
		addStructure = new AddStructure(xCells,yCells);		
	}
	
	public void setCellTo(int xCell,int yCell, BinaryState state) {
		this.cells[xCell][yCell] = state;
	}

	public void oldMap(){
		Map<CellCoordinates,CellState> cell = new HashMap<>();
		for (int x = 0; x < xCells; x++) {
			for (int y = 0; y < yCells; y++) {
				if(cells[x][y] == BinaryState.ALIVE)
					cell.put(new Coords2D(x,y+1), BinaryState.ALIVE);
			}
		}
		oldMapState.putAll(cell);
	}
	
	public MouseListener createMouseListner(final Presenter p) {
		return new MouseAdapter() {

			@Override
		    public void mouseClicked(MouseEvent event) {
				int logicalX = toLogicalXCell(event.getX());
				int logicalY = toLogicalYCell(event.getY());
				
				CellCoordinates coord;
				CellState newState;
				if(view.isOneDimention()){
					coord = new Coords1D(logicalX);
					newState = nextCellState(logicalX,logicalY);
					p.changeAutomatCellState(coord, newState);
				}else{
					Structures struct = view.getStructure();
					coord = new Coords2D(logicalX,logicalY);
					if(struct == Structures.BOAT){
						putCells = addStructure.putBoat(coord);
						p.putStructure(putCells);
						view.resetStructure();
					}
					else if(struct == Structures.KITE){
						putCells = addStructure.putKite(coord);
						p.putStructure(putCells);
						view.resetStructure();
					}
					else if(struct == Structures.BLOCK){
						putCells = addStructure.putBlock(coord);
						p.putStructure(putCells);
						view.resetStructure();
					}
					else if(struct == Structures.GUN){
						putCells = addStructure.putGun(coord);
						p.putStructure(putCells);
						view.resetStructure();
					}else{
						newState = nextCellState(logicalX,logicalY);
						p.changeAutomatCellState(coord, newState);
					}
				}
				repaint();
			}
		};
	}	
	
	private CellState nextCellState(int logicalX, int logicalY) {
		if (cells[logicalX][logicalY] == BinaryState.DEAD) 
			cells[logicalX][logicalY] = BinaryState.ALIVE;
		else 
			cells[logicalX][logicalY] = BinaryState.DEAD;
		
		return cells[logicalX][logicalY];
	}

	private int toLogicalXCell(int x) {
		int physicalCellWidth = physicalCellWidth();
		return x/physicalCellWidth;
	}

	private int physicalCellWidth() {
		int physicalWidth = this.getWidth();
		int physicalCellWidth = physicalWidth/xCells;
		return physicalCellWidth;
	}
	
	private int toLogicalYCell(int y) {
		int physicalCellHeight = physicalCellHeight();
		return y/physicalCellHeight;
	}

	private int physicalCellHeight() {
		int physicalHeight = this.getHeight();
		int physicalCellHeight = physicalHeight/yCells;
		return physicalCellHeight;
	}
}
