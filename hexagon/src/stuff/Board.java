package stuff;

public class Board {
	
	Tile[][] tiles;
	
	int width;
	int height;
	
	int cursorX;
	int cursorY;
	
	int highlightX;
	int highlightY;
	
	//top row has more hexes
	boolean topMore;
	
	public Board() {
		this(14, 37);
	}
	
	public Board(int width, int height) {
		tiles = new Tile[height][width];
		
		for (int i=0; i<height; i++)
			for (int j=0; j<width; j++)
				tiles[i][j] = new Tile();
		
		tiles[4][5].occupant = new Unit("basic", 40);
		
		this.width = width;
		this.height= height;
		
		cursorX = 0;
		cursorY = 0;
		
		highlightX = -1;
		highlightY = -1;
		
		topMore = true;
	}

	public boolean moveSelection(int direction) {
		int[] deltaX = new int[]{-1, 0, 0, -1, 0, 0};
		if ((topMore && cursorY % 2 == 1) || (!topMore && cursorY % 2 == 0)) {
			deltaX[0] = 0;
			deltaX[2] = 1;
			deltaX[3] = 0;
			deltaX[5] = 1;
		}
		int[] deltaY = new int[]{-1, -2, -1, 1, 2, 1};
		
		int newX = cursorX + deltaX[direction];
		int newY = cursorY + deltaY[direction];
		
		if (0 <= newX && newX < width && 0 <= newY && newY < height) {
			cursorX = newX;
			cursorY = newY;
			
			return true;
		} else
			return false;
	}
	
	public void highlight() {
		highlightX = cursorX;
		highlightY = cursorY;
	}

	public void movePiece() {
		if (tiles[cursorY][cursorX].occupant == null) {
			tiles[cursorY][cursorX].occupant = tiles[highlightY][highlightX].occupant;
			
			tiles[highlightY][highlightX].occupant = null;
		}
	}
}
