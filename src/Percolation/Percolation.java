
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;


public class Percolation {
	boolean[][] grid;
	int dim;
	int size;
	int top;
	int bottom;
	int numOfOpenSites;
	WeightedQuickUnionUF qf;
	

	public Percolation(int n) {
		size = n * n;
		numOfOpenSites = 0;
		top = 0;
		bottom = size + 1;
		dim = n;
		
		qf = new WeightedQuickUnionUF(size + 2); //+2 for the 'psuedo roots'
		grid = new boolean [n][n];
		
	}
	
	
	//Returns corresponding index from the grid

	public int quickFindArrayIndex(int row, int col){
		return col + ((row-1) * dim);
	}
	
	
	public void open(int row, int col) {
		if(!isOpen(row,col)){
			grid[row-1][col-1] = true;
			numOfOpenSites++;
		}
		
		//Connect to the 'pseduo roots' if first or last row
		if(row == 1) {
			qf.union(quickFindArrayIndex(row, col), top);
		}
		
		if(row == dim) {
			qf.union(quickFindArrayIndex(row, col), bottom);
		}
		

		//Adjacent square unions IF the site is open
		if( row < dim && isOpen(row+1, col)) {
			qf.union(quickFindArrayIndex(row, col), quickFindArrayIndex(row+1,col));
		}
		if(row > 1 && isOpen(row-1, col)) {
			qf.union(quickFindArrayIndex(row, col), quickFindArrayIndex(row-1,col));
		}
		
		if(col > 1 && isOpen(row, col-1)) {
			qf.union(quickFindArrayIndex(row, col), quickFindArrayIndex(row,col-1));

		}
		if(col < dim && isOpen(row, col+1)) {
			qf.union(quickFindArrayIndex(row, col), quickFindArrayIndex(row,col+1));

		}
		
		
	}
	
	public int size() {
		return this.size;
	}

	public int dimension() {
		return this.dim;
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
		return this.numOfOpenSites;
	}
	
	
	public boolean percolates() {
		return qf.connected(top,bottom);
	}

	//Test
	public static void main(String[] args){
		Percolation pc = new Percolation(50); //50x50 grid

		while(!pc.percolates()){
			//Row and col values must be between 1 - Size, as 0 and Size + 1 are roots to check for percolation
			pc.open(StdRandom.uniform(1,pc.dimension()+1), StdRandom.uniform(1, pc.dimension()+1));
		}

		System.out.println("NUMBER OF OPEN SITES: " + pc.numberOfOpenSites());

		for(int i = 1 ; i < pc.dimension()+1 ; i++){
			for(int j = 1 ; j < pc.dimension()+1 ; j++){
				if(pc.isOpen(i, j)){
					System.out.print("O"); //open
				}
				else{
					System.out.print(" "); //blocked
				}
			}
			System.out.print("\n");
		}
	}
	
}
