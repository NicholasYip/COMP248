// -------------------------------------------------------
// Assignment 4
// Written by: Nicholas Yiphoiyen 40117237
// For COMP248 Section EC – Fall2019
// --------------------------------------------------------
public class Player
{
	private String name;
	private int level;
	private int x;
	private int y;
	private int energy;
	
	public Player()
	{
		name = "";
		energy = 10;
		x = 0; y = 0; level = 0;
	}
	
	public Player(String desired)
	{
		name = desired;
		energy = 10;
		x = 0; y = 0; level = 0;
	}
	
	public Player(int one, int two, int three)
	{
		name = "";
		energy = 10;
		level = one;
		x = two;
		y = three;
	}
	
	public Player(Player original)
	{
		name = original.name;
		level = original.level;
		x = original.x;
		y = original.y;
		energy = original.energy;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getEnergy()
	{
		return energy;
	}
	
	public void setName(String desired)
	{
		name = desired;
	}
	
	public void setLevel(int desired)
	{
		level = desired;
	}
	
	public void setX(int desired)
	{
		x = desired;
	}
	
	public void setY(int desired)
	{
		y = desired;
	}
	
	public void setEnergy(int desired)

	{
		energy = desired;
	}
	
	public void moveTo(Player p)
	{
		Player temporary = new Player(this.level,this.x,this.y);
		this.level = p.level;
		this.x = p.x;
		this.y = p.y;
		
		p.level = temporary.level;
		p.x = temporary.level;
		p.y = temporary.y;
	}
	
	public boolean won(Board b)
	{
		return (this.level == b.getLevel()-1 && this.x == b.getSize()-1 && this.y == b.getSize()-1);
	}
	
	public boolean equals(Player p)
	{
		return (this.level == p.level && this.x == p.x && this.y == p.y);
	}
	
	public String toString()
	{
		return this.name + " is on level " + this.level + " at location (" + this.y + "," + this.x + ") and has " + this.energy + " units of energy.";
	}
}

