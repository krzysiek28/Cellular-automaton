package cell.states;

import cell.coordinates.CellCoordinates;

import java.util.Map;

public class GeneralStateFactory implements CellStateFactory {

	private Map<CellCoordinates, CellState> states;
	
	public GeneralStateFactory(Map<CellCoordinates, CellState> states){
		this.states = states;
	}
	
	@Override
	public CellState initialState(CellCoordinates cellCoordinates){
		return states.get(cellCoordinates);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((states == null) ? 0 : states.hashCode());
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
		GeneralStateFactory other = (GeneralStateFactory) obj;
		if (states == null) {
			if (other.states != null)
				return false;
		} else if (!states.equals(other.states))
			return false;
		return true;
	}
}
