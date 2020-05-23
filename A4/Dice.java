// -------------------------------------------------------
// Assignment 4
// Written by: Nicholas Yiphoiyen 40117237
// For COMP248 Section EC – Fall2019
// --------------------------------------------------------
import java.util.Random;

public class Dice 
{
	Random random = new Random();
	private int die1;
	private int die2;
	
	public Dice()
	{
	}
	
	public int getDie1()
	{
		return die1;
	}
	
	public int getDie2()
	{
		return die2;
	}
	
	public int rollDice()
	{
		die1 = random.nextInt((6-1)+1)+1;
		die2 = random.nextInt((6-1)+1)+1;
		return die1+die2;			
	}
	
	public boolean isDouble()
	{
		return die1 == die2;
	}
	
	public String toString()
	{
		return "Die 1: " + die1 + " Die 2: " + die2;
	}
}

