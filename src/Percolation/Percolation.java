
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
	boolean[][] grid;
	int dim;
	int size;
	int top;
	int bottom;
	int numOfOpenSites;
	WeightedQuickUnionUF qf;
	

	public Percolation(int n) {
		size = (n*n);
		numOfOpenSites = 0;
		top = 0;
		bottom = size + 1;
		dim = n;
		
		qf = new WeightedQuickUnionUF(size);
		grid = new boolean [n][n];
		
	}
	
	
	//Returns corresponding index from the grid
	public int quickFindArrayIndex(int row, int col){
		return ((dim*row) - (dim - col)) - 1 ;
	}
	
	
	//Row and Col inputs are >1 and <N
	
	public void open(int row, int col) {
		grid[row-1][col-1] = true;
		
		//Connect to the 'pseduo roots' of the grid
		if(row - 1 == 0) {
			qf.union(quickFindArrayIndex(row, col), top);
		}
		
		if(row + 1 == dim) {
			qf.union(quickFindArrayIndex(row, col), bottom);
		}
		

		//Adjacent square unions IF the site is open
		if( row < size && isOpen(row+1, col)) {
			qf.union(quickFindArrayIndex(row, col), quickFindArrayIndex(row+1,col));
		}
		if(row > 1 && isOpen(row, col)) {
			qf.union(quickFindArrayIndex(row, col), quickFindArrayIndex(row-1,col));

		}
		
		if(col > 1 && isOpen(row+1, col)) {
			qf.union(quickFindArrayIndex(row, col), quickFindArrayIndex(row,col-1));

		}
		if(col < size && isOpen(row+1, col)) {
			qf.union(quickFindArrayIndex(row, col), quickFindArrayIndex(row,col+1));

		}
		
		numOfOpenSites++;
		
	}
	
	
	public boolean isOpen(int row, int col) {
		return grid[row-1][col-1];
	}
	
	
	//If this square(index) connects to the top 'pseudo root'
	public boolean isFull(int row, int col) {
		
		if(row > 0 && col <=size && col > 0 && col<=size  )
			return qf.connected(quickFindArrayIndex(row,col), top);
		else
			throw new IndexOutOfBoundsException();
		
	}
	

	public int numberOfOpenSites() {
		return numOfOpenSites;
	}
	
	
	//If there is a path connecting the 'pseduo roots'
	//Since there is is a given 'id' for each square, 
	//.connected() will only check if the 'pseduo roots' 
	//share the same id
	public boolean percolates() {
		return qf.connected(top,bottom);
	}
	
}
