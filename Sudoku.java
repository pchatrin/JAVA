import java.util.Scanner;

public class Sudoku {
	
	public static int NUMBER_GRID = 2;
	public static int BLANK = 0;
	public static int SIZE = 9;
	public int[][] board;
	
	/*public static int[][] DATA_SET = {
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0}
	};*/
	
	/*public static int[][] DATA_SET1 = {
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 3, 8, 1, 0, 0, 0, 0},
	        {0, 0, 2, 0, 0, 7, 5, 6, 0},
	        {0, 0, 1, 0, 0, 0, 0, 8, 0},
	        {0, 4, 0, 0, 0, 0, 0, 3, 0},
	        {0, 5, 0, 0, 0, 0, 6, 0, 0},
	        {0, 6, 8, 9, 0, 0, 4, 0, 0},
	        {0, 0, 0, 0, 4, 5, 9, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0}
	};
	
	public static int[][] DATA_SET2 = {
			{0, 6, 0, 9, 0, 0, 0, 0, 0},
		    {0, 0, 4, 0, 0, 0, 3, 0, 5},
		    {0, 1, 0, 3, 0, 0, 0, 2, 0},
		    {0, 0, 0, 0, 0, 0, 1, 0, 4},
		    {0, 0, 0, 0, 2, 0, 0, 0, 0},
		    {8, 0, 3, 0, 0, 0, 0, 0, 0},
		    {0, 5, 0, 0, 0, 1, 0, 8, 0},
		    {2, 0, 8, 0, 0, 0, 9, 0, 0},
		    {0, 0, 0, 0, 0, 7, 0, 3, 0}
	};*/
	
	//Create empty board
	
	public Sudoku(int[][] board) {
		this.board = new int[SIZE][SIZE];
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				this.board[i][j] = board[i][j];
			}
		}
	}
	
	//Check if the number is already in the row
	
	private boolean dupRow(int row, int number) {
		for(int j = 0; j < SIZE; j++){
			if(board[row][j] == number)
				return false; //number is already in the row
		}
		return true; // number is not in the row
	}
	
	//Check if the number is already in the column
	
	private boolean dupCol(int col, int number) {
		for(int i = 0; i < SIZE; i++){
			if(board[i][col] == number)
				return false; //number is already in the column
		}
		return true; // number is not in the column
	}
	
	//Check the validity of 3x3 block
	
	private boolean dupBox(int row, int col, int number) {
		
		//move the box 3 steps at a time
		int boxR = (row - row % 3); 
		int boxC = (col - col % 3);
		
		for(int i = boxR; i < boxR + 3; i++) {
			for(int j = boxC; j < boxC + 3; j++) {
				if(board[i][j] == number)
					return false; //number is already in 3x3
			}
		}
		return true; // number is not in 3x3
	}
	
	//Check if the data is valid (number is not in the row, column, or 3x3) 
	
	private boolean validData(int row, int col, int number ) {
		
		return (dupRow(row, number) && dupCol(col, number) && dupBox(row, col, number));
		
	}
	
	//Solving the Sudoku using recursive backtracking
	
	public boolean solve() {
		//display();
		for(int row = 0; row < SIZE; row++) {
			for(int col = 0; col < SIZE; col++) {
				if(board[row][col] == BLANK) {
					for(int number = 1; number < SIZE + 1; number++) {
						if(validData(row, col, number)) {
							board[row][col] = number;
							if(solve()) {
								return true; //solution found
							}
							else {
								board[row][col] = BLANK;
							}
						}
					}
					
					return false; //No solution (Error)
				}
			}
		}
		return true; //solution found
	}
	
	//Check the number of blank cell not filled yet
	
	public void checkZero() {
		int NumZero = 0;
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if(board[i][j] == 0) {
					NumZero++;
				}
			}
		}
		System.out.println("Number of blank cell(0): " + NumZero);
	}
	
	//Display the end result
	
	public void display() {
		checkZero();
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				System.out.print(" " + board[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		Scanner numData = new Scanner(System.in);
		int num = numData.nextInt();
		System.out.println("Number of Sudoku: " + num);
		
		Scanner inputSudoku = new Scanner(System.in);
		
		int[][][] INPUT_DATA_SET = new int[SIZE][SIZE][num];
		int[][] DATA_SET = new int[SIZE][SIZE];
		
		//Input Sudoku data
		//Input one-by-bone
		
		System.out.println("Insert your data");
		for(int k = 0; k < num; k++) {
			for(int i = 0; i < SIZE; i++) {
				for(int j = 0; j < SIZE; j++) {
					INPUT_DATA_SET[i][j][k]= numData.nextInt();
				}
			}
		};
		
		//Print out Sudoka Data received
		
		System.out.println("Total Questions: " + num);
		for(int k = 0; k < num; k++) {
			System.out.println("Question Number: " + (k+1));
			for(int i = 0; i < SIZE; i++) {
				for(int j = 0; j < SIZE; j++) {
					System.out.print(" " + INPUT_DATA_SET[i][j][k]);
				}
				System.out.println();
			}
			System.out.println();
		};
		
		//Replace data receive in a new array and solve
		
		for(int k = 0; k < num; k++) {
			for(int i = 0; i < SIZE; i++) {
				for(int j = 0; j < SIZE; j++) {
					DATA_SET[i][j] = INPUT_DATA_SET[i][j][k];
				}
			}
			Sudoku sudoku = new Sudoku(DATA_SET);
			System.out.println("Solution Question: " + (k+1));
			if(sudoku.solve()) {
				sudoku.display();
			}
			else {
				System.out.println("ERROR");
			}
		}
		
		
		/*Sudoku sudoku = new Sudoku(DATA_SET);
		if(sudoku.solve()) {
			sudoku.display();
		}
		else {
			System.out.println("ERROR");
		}
		
		
		System.out.println(NUMBER_GRID);
		
		Sudoku sudoku1 = new Sudoku(DATA_SET1);
		Sudoku sudoku2 = new Sudoku(DATA_SET2);
		
		System.out.println(" Dataset 1");
		
		if(sudoku1.solve()) {
			System.out.println("Result (1): ");
			sudoku1.display();
		}
		else {
			System.out.print("Error");
		}
		
		System.out.println("Dataset 2"); 
		
		if(sudoku2.solve()) {
			System.out.println("Result (2): ");
			sudoku2.display();
		}
		else {
			System.out.print("Error");
		}*/
	}
}

