package cell;

import cell.coordinates.CellCoordinates;
import cell.states.CellState;

public class Cell{

	private CellState state;

	private CellCoordinates coords;

	public Cell(CellState state, CellCoordinates coords){
		this.state = state;
		this.coords = coords;
	}

	public CellState getState() {
		return state;
	}

	public CellCoordinates getCoords() {
		return coords;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coords == null) ? 0 : coords.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		Cell other = (Cell) obj;
		if (coords == null) {
			if (other.coords != null)
				return false;
		} else if (!coords.equals(other.coords))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
}
