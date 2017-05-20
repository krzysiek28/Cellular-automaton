package GameEngine;

//klasa do tworzenia poczatkowego stanu planszy
public class UniformStateFactory implements CellStateFactory{
	private CellState state;
	
	public UniformStateFactory(CellState state){
		this.state = state;
	}
	
	@Override
	public CellState initialState(CellCoordinates cellCoordinates){
		//zwraca nam ten sam stan
		return state;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		UniformStateFactory other = (UniformStateFactory) obj;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	
	
}
