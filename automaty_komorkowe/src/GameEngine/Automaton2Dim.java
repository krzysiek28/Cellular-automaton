package GameEngine;


public abstract class Automaton2Dim extends Automaton{
	protected int width;
	protected int height;
	
	public Automaton2Dim(CellStateFactory cellStateFactory,	CellNeighborhood cellNeighborhood, int width, int height) {
		//wywolanie konstruktra z klasy nadrzednej
		super(cellStateFactory, cellNeighborhood);
		this.width = width;
		this.height = height;
		initialize();
	}

	//sprawdza czy przeiterowalo nam cala plansze
	//przy ostatnim elemencie wyrzuca false
	@Override
	protected boolean hasNextCoordinates(CellCoordinates cellCoordinates){
		Coords2D coords = (Coords2D) cellCoordinates;
		
		if(coords.x>=width-1 && coords.y>=height-1)
			return false;
		else
			return true;
			
	}

	@Override
	protected CellCoordinates initialCoordinates() {
		//przekazuje konstruktor coords2d ustawiam plansze na 0,0
		return new Coords2D(-1,0);
	}

	//przekazuje nam kolejna komorke
	@Override
	protected CellCoordinates nextCoordinates(CellCoordinates cellCoordinates) {
		Coords2D coords = (Coords2D) cellCoordinates;
		int x,y;
		
		if(coords.x==width-1){
			x = 0;
			y = coords.y+1;
		}
		else{
			x = coords.x+1;
			y = coords.y;
		}
		
		return new Coords2D(x, y);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + height;
		result = prime * result + width;
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
		Automaton2Dim other = (Automaton2Dim) obj;
		if (height != other.height)
			return false;
		if (width != other.width)
			return false;
		return true;
	}
}
