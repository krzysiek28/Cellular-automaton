package GameEngine;

public abstract class Automaton1Dim extends Automaton{

	protected int size;
	
	public Automaton1Dim(CellStateFactory cellStateFactory,	CellNeighborhood cellNeighborhood, int size) {
		//wywolanie konstruktra z klasy nadrzednej
		super(cellStateFactory, cellNeighborhood);
		this.size = size;
		initialize();
	}

	@Override
	protected boolean hasNextCoordinates(CellCoordinates cellCoordinates) {
		Coords1D coord = (Coords1D) cellCoordinates;
		if(coord.x>=size-1)
			return false;
		else 
			return true;
	}

	@Override
	protected CellCoordinates initialCoordinates() {
		return new Coords1D(-1);
	}

	@Override
	protected CellCoordinates nextCoordinates(CellCoordinates cellCoordinates) {
		Coords1D coord = (Coords1D) cellCoordinates;
		int x;
		x = coord.x + 1;
		return new Coords1D(x);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + size;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Automaton1Dim other = (Automaton1Dim) obj;
		if (size != other.size)
			return false;
		return true;
	}
	
	
}
