// -------------------------------------------------------
// Assignment 4
// Written by: Nicholas Yiphoiyen 40117237
// For COMP248 Section EC – Fall2019
// --------------------------------------------------------
public class Board 
{
	private int board[][][];
	public static final int MIN_LEVEL = 3;
	public static final int MIN_SIZE = 3;
	private int level;
	private int size;
	
	public Board()
	{
		level = 3;
		size = 4;
		createBoard(level, size);
	}
	
	public Board(int L, int x)
	{
		level = L;
		size = x;
		createBoard(level,size);
	}
	
	private void createBoard(int level, int size)
	{
		board = new int[level][size][size];
		
		for (int L = 0; L < level; L ++)
			for (int x = 0; x < size; x++)
				for (int y = 0; y < size; y++)
				{
					if ((L+x+y) % 3 == 0)
						board[L][x][y] = -3;
			
					else if ((L+x+y) % 5 == 0)
						board[L][x][y] = -2;
						
					else if ((L+x+y) % 7 == 0)
						board[L][x][y] = 2;
				
					else
						board[L][x][y] = 0;
				}
		board[0][0][0] = 0;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public int getEnergyAdj(int L, int x, int y)
	{
		return board[L][x][y];
	}
	
	public String toString() //concatenation of string to return printing of board
	{
		String energyValue = "";
		for (int L = 0; L < level; L ++)
		{
			energyValue = energyValue + "\n\nLevel " + L + "\n--------";
			for (int x = 0; x < size; x++)
			{
				energyValue = energyValue + "\n";
				for (int y = 0; y < size; y++)
					energyValue = energyValue + "\t" + Integer.toString(board[L][x][y]);
			}
		}
		
		return energyValue;				
	}
}
