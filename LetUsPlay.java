// -------------------------------------------------------
// Assignment 4
// Written by: Nicholas Yiphoiyen 40117237
// For COMP248 Section EC – Fall2019
// --------------------------------------------------------
//board size -> player names -> multiple rounds until winner
//check energy level first as no movement if no energy
//roll dices
//occupied or no? if so challenge/forfeit
//repeat until a player gets to the last square

import java.util.Scanner;
import java.util.Random;

public class LetUsPlay {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Random random = new Random();
		
		int defaultOrCustom;
		Board board = null;
		String player0Name;
		Player player0 = null;
		Player player1 = null;
		String player1Name;
		Dice dices = new Dice();
		
		System.out.println("*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*\n"
						+ "*                                           *\n" 
						+ "*   WELCOME To Nancy's 3D Warrior Game!     *\n"
						+ "*                                           *\n"
						+ "*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*\n");
		
		System.out.println("The default game board has 3 levels and each leve has a 4x4 board.\n"
				+ "You can use this default board size or change the size");

		System.out.println("\t0 to use the default board size" + "\n\t-1 to enter your own size");
		System.out.print("==> What do you want to do? ");

	
		do { //need to check user input
			defaultOrCustom = input.nextInt();
			if (defaultOrCustom != -1 && defaultOrCustom != 0)
				System.out.println("Sorry but " + defaultOrCustom + " is not a legal choice");
		} while (defaultOrCustom != -1 && defaultOrCustom != 0);

		if (defaultOrCustom == -1) { //user chose custom board
			System.out.print("How many levels would you like? (minimum size 3, max 10) ");

			int numberOfLevels;
			do { //need to check user input
				numberOfLevels = input.nextInt();
				if (numberOfLevels < Board.MIN_LEVEL || numberOfLevels > 10)
					System.out.println("Sorry but " + numberOfLevels + " is not a legal choice");
			} while (numberOfLevels < Board.MIN_LEVEL || numberOfLevels > 10);

			System.out.println(
					"\nWhat size do you want the nxn board on each level to be?\nMinimum size is 3x3, max is 10x10.");
			System.out.print("==> Enter the value of n: ");

			int sizeOfBoard;
			do { //need to check user input
				sizeOfBoard = input.nextInt();
				if (sizeOfBoard < Board.MIN_SIZE || sizeOfBoard > 10)
					System.out.println("Sorry but " + sizeOfBoard + " is not a legal choice");
			} while (sizeOfBoard < Board.MIN_SIZE || sizeOfBoard > 10);

			board = new Board(numberOfLevels, sizeOfBoard);
		}

		else if (defaultOrCustom == 0) { //user chose default board
			System.out.println("The default board size was chosen. There are 3 levels, each with a 4x4 board.");
			board = new Board();
		}

		System.out.println("\nYour 3D board has been set up and looks like this:" + board);

		String cancel = input.nextLine();
		System.out.print("\nWhat is player 0's name (one word only): ");
		
		do { //checking user input for 1 word
			player0Name = input.nextLine();
			player0Name = player0Name.trim();
			if (player0Name.contains(" "))
				System.out.println("Sorry but " + player0Name + " is not a legal choice.");
		} while (player0Name.contains(" "));

		System.out.print("\nWhat is player 1's name (one word only): ");
		do { //checking user input for 1 word
			player1Name = input.nextLine();
			player1Name = player1Name.trim();
			if (player1Name.contains(" "))
				System.out.println("Sorry but " + player1Name + " is not a legal choice.");
		} while (player1Name.contains(" "));

		int whoIsFirst = random.nextInt(2); //roll dice to see who's first and player0 and player1 are assigned consequently.
		
		if (whoIsFirst == 0) {
			player0 = new Player(player0Name);
			player1 = new Player(player1Name);
		} else if (whoIsFirst == 1) {
			player0 = new Player(player1Name);
			player1 = new Player(player0Name);
		}

		System.out.println("\nThe game has started " + player0.getName() + " goes first"
				+ "\n===================================");

		while (true) { //loop for multiple rounds
			//player0's turn
			Player copy2Player0 = new Player(player0);
			int challengeChoice = -1;
			int winnerOrLoser = 0;

			System.out.println("\nIt is " + player0.getName() + "'s turn");

			if (player0.getEnergy() < 1) { //give the player a change to roll 3 dices if no energy units
				int doubleCounter = 0;
				System.out.println("\t" + player0.getName() + " your energy level is too low to move. You get to roll the dices 3 times where getting a double rewards you with 2 energy units each time.");
				for (int count = 0; count < 3; count++) {
					dices.rollDice();
					if (dices.isDouble())
						doubleCounter++;
					System.out.println("\tRoll " + (count + 1) + ": " + dices);
				}
				player0.setEnergy(player0.getEnergy() + doubleCounter * 2);
				System.out
						.println("\t" + player0.getName() + " your energy level has increased by " + doubleCounter * 2);
			}

			if (player0.getEnergy() > 0) { //user needs at least 1 energy to move, no point in running potential movement if 0 energy
				int sum0 = dices.rollDice();
				System.out.println("\t" + player0.getName() + " you rolled " + dices);

				if (dices.isDouble()) {
					player0.setEnergy(player0.getEnergy() + 2);
					System.out.println("\tCongratulations you rolled double " + dices.getDie1() + ". Your energy level went up by 2");
				}
				Player copyPlayer0 = new Player(player0);

				if (player0.getLevel() == board.getLevel() - 1 && player0.getY() == board.getSize() - 1 && player0.getX() == board.getSize() - 2) { //need to go backwards if second to last square of top level is occupied so checking that
					System.out.println("Unfortunately you are at the second to last square of the last level. You will unfortunately be going backwards.");
					for (; sum0 != 0; sum0--) {
						if (copyPlayer0.getX() == 0 && copyPlayer0.getY() == 0) {
							copyPlayer0.setLevel(player0.getLevel() - 1);
							copyPlayer0.setX(board.getSize() - 1);
							copyPlayer0.setY(board.getSize() - 1);
						} else if (copyPlayer0.getX() == 0) {
							copyPlayer0.setY(copyPlayer0.getY() - 1);
							copyPlayer0.setX(board.getSize() - 1);
						} else
							copyPlayer0.setX(copyPlayer0.getX() - 1);
					}
				} else { //if not on second to last square, normal forward moving
					for (; sum0 != 0; sum0--) {
						if (copyPlayer0.getY() == board.getSize() - 1 && copyPlayer0.getX() == board.getSize() - 1) {
							copyPlayer0.setX(0);
							copyPlayer0.setY(0);
							copyPlayer0.setLevel(player0.getLevel() + 1);
						}

						else if (copyPlayer0.getX() == board.getSize() - 1) {
							copyPlayer0.setX(0);
							copyPlayer0.setY(copyPlayer0.getY() + 1);
						} else
							copyPlayer0.setX(copyPlayer0.getX() + 1);
					}
				}
				winnerOrLoser = 0;
				int offTheGrid = 0;
				challengeChoice = -1;
				if (copyPlayer0.equals(player1)) { //same location, so challenge or forfeit option needs to be offered
					System.out.println("Player " + player1.getName() + " is at your new location"
							+ "\n\t0 - Challenge and risk loosing 50% of your energy unit if you loose\n\t\tor move to new location and get 50% of other players energy units"
							+ "\n\t1 - to move down one level or move to (0,0) if at level 0 and love 2 energy units");

					challengeChoice = -1;
					while (challengeChoice != 0 && challengeChoice != 1) { //checking user input
						challengeChoice = input.nextInt();
						if (challengeChoice != 1 && challengeChoice != 0)
							System.out.println("Sorry but " + challengeChoice + " is not a legal choice.");
					}

					if (challengeChoice == 1) {
						if (player0.getLevel() >= 1) { 

							player0.setEnergy(player0.getEnergy() - 2);
						} else if (player0.getLevel() == 0) { //different movement if on level 0 so need another statement
							player0.setX(0);
							player0.setY(0);
							player0.setEnergy(player0.getEnergy() - 2);
						}
					}

					if (challengeChoice == 0) { //challenge and dice rolling with 2 cases
						winnerOrLoser = random.nextInt(11);
						if (winnerOrLoser < 6) { //lost so nobody moves
							System.out.println("Unfortunately, you lost the challenge.");
							player0.setEnergy(player0.getEnergy() / 2);
						}

						if (winnerOrLoser >= 6) { //win, some swaps need to be made and energy losses/gains
							System.out.println("Bravo!! You won the challenge.");
							player1.moveTo(player0);
							player1.setEnergy(player1.getEnergy() / 2);
							player0 = new Player(copyPlayer0);
							player0.setEnergy(player0.getEnergy() + player1.getEnergy());
						}
					}
				} else if (copyPlayer0.getLevel() > board.getLevel() - 1) { //no same location was encountered, but maybe player rolled too high, so checking
					player0 = new Player(player0);
					System.out.println("!!! Sorry you need to stay where you are - that throw takes you off the grid and you loose 2 energy points");
					offTheGrid++;
				} else //no issue so copy becomes real player
					player0 = new Player(copyPlayer0);

				if (offTheGrid == 1) { //off the grid, so energy loss and no movement, no changes in energy for landing on certain square
					player0.setEnergy(player0.getEnergy() - 2);
					offTheGrid--;
				} else {
					if (challengeChoice == 0 && winnerOrLoser < 6) { //challenge lost, so no changes but still affected by occupied square
						System.out.println("\tYour energy is adjusted by " + board.getEnergyAdj(copyPlayer0.getLevel(), copyPlayer0.getY(),copyPlayer0.getX()) 
										 + " for landing at (" + copyPlayer0.getY() + "," + copyPlayer0.getX() + ") at level " + copyPlayer0.getLevel());
						player0.setEnergy(player0.getEnergy() + board.getEnergyAdj(copyPlayer0.getLevel(), copyPlayer0.getX(), copyPlayer0.getY()));
					} else { //no challenge, just normal movement
						System.out.println("\tYour energy is adjusted by " + board.getEnergyAdj(player0.getLevel(), player0.getY(), player0.getX())
										 + " for landing at (" + player0.getY() + "," + player0.getX() + ") at level " + player0.getLevel());
						player0.setEnergy(player0.getEnergy()+ board.getEnergyAdj(player0.getLevel(), player0.getX(), player0.getY()));
					}
				}

				if (challengeChoice == 1 && player0.getLevel() >= 1) //level was not decremented before as the occupied square's energy value needs to be used before
					player0.setLevel(player0.getLevel() - 1);
			} else { //no energy so no movement, turn skips
				System.out.println("Unfortunately, your turn skips " + player0.getName() + " as your energy level is too low to move even after rolling 3 times. \nYou will get another 3 attempts next round");
			}

			if (player0.won(board)) { //checking if player0 won, no point in making player1 play if player0 already won
				System.out.println("\nAt the end of this round:" + "\n\t" + player0 + " \n\t" + player1);
				break;
			}

			// player1's turn
			System.out.println("It is " + player1.getName() + "'s turn");

			if (player1.getEnergy() < 1) { //give the player a change to roll 3 dices if no energy units
				int doubleCounter = 0;
				System.out.println("\t" + player1.getName() + " your energy level is too low to move. You get to roll the dices 3 times and getting a double rewards you with 2 energy units each time.");
				for (int count = 0; count < 3; count++) {
					dices.rollDice();
					if (dices.isDouble())
						doubleCounter++;
					System.out.println("\tRoll " + (count + 1) + ": " + dices);
				}
				player1.setEnergy(player1.getEnergy() + doubleCounter * 2);
				System.out
						.println("\t" + player1.getName() + " your energy level has increased by " + doubleCounter * 2);
			}

			if (player1.getEnergy() > 0) { //user needs at least 1 energy to move, no point in running potential movement if 0 energy
				int sum1 = dices.rollDice();
				System.out.println("\t" + player1.getName() + " you rolled " + dices);

				if (dices.isDouble()) {
					player1.setEnergy(player1.getEnergy() + 2);
					System.out.println("\tCongratulations you rolled double " + dices.getDie1()
							+ " . Your energy level went up by 2");
				}

				Player copyPlayer1 = new Player(player1);
				if (player1.getLevel() == board.getLevel() - 1 && player1.getY() == board.getSize() - 1 && player1.getX() == board.getSize() - 2) { //checking if player is on second to last square for backwards movement
					System.out.println("Unfortunately you are at the second to last square of the last level. You will unfortunately be going backwards.");
					for (; sum1 != 0; sum1--) {
						if (copyPlayer1.getX() == 0 && copyPlayer1.getY() == 0) {
							copyPlayer1.setLevel(player0.getLevel() - 1);
							copyPlayer1.setX(board.getSize() - 1);
							copyPlayer1.setY(board.getSize() - 1);
						} else if (copyPlayer1.getX() == 0) {
							copyPlayer1.setY(copyPlayer1.getY() - 1);
							copyPlayer1.setX(board.getSize() - 1);
						} else
							copyPlayer1.setX(copyPlayer1.getX() - 1);
					}
				} else { //not on second to last square, normal moving forward
					for (; sum1 != 0; sum1--) {
						if (copyPlayer1.getY() == board.getSize() - 1 && copyPlayer1.getX() == board.getSize() - 1) {
							copyPlayer1.setX(0);
							copyPlayer1.setY(0);
							copyPlayer1.setLevel(copyPlayer1.getLevel() + 1);
						}

						else if (copyPlayer1.getX() == board.getSize() - 1) {
							copyPlayer1.setX(0);
							copyPlayer1.setY(copyPlayer1.getY() + 1);
						} else
							copyPlayer1.setX(copyPlayer1.getX() + 1);
					}
				}

				winnerOrLoser = 0;
				int offTheGrid = 0;
				challengeChoice = -1;
				if (copyPlayer1.equals(player0)) { //checking is same square is occupied as challenge/forfeit needs to be issued if so
					System.out.println("Player " + player0.getName() + " is at your new location"
							+ "\n\t0 - Challenge and risk loosing 50% of your energy unit if you loose\n\t\tor move to new location and get 50% of other players energy units"
							+ "\n\t1 - to move down one level or move to (0,0) if at level 0 and love 2 energy units");

					challengeChoice = -1;
					while (challengeChoice != 0 && challengeChoice != 1) { //checking user input
						challengeChoice = input.nextInt();
						if (challengeChoice != 1 && challengeChoice != 0)
							System.out.println("Sorry but " + challengeChoice + " is not a legal choice.");
					}

					if (challengeChoice == 1) { //forfeit
						if (player1.getLevel() >= 1) {
							player1.setEnergy(player1.getEnergy() - 2);
						} else if (player1.getLevel() == 0) { //level 0 has different movement
							player1.setX(0);
							player1.setY(0);
							player1.setEnergy(player1.getEnergy() - 2);
						}
					}

					if (challengeChoice == 0) { //challenge lost 
						winnerOrLoser = random.nextInt(11);
						if (winnerOrLoser < 6) {
							System.out.println("Unfortunately, you lost the challenge.");
							player1.setEnergy(player1.getEnergy() / 2);
						}

						if (winnerOrLoser >= 6) { //challenge won, swapping of players needed
							System.out.println("Bravo!! You won the challenge.");
							player0.moveTo(player1);
							player0.setEnergy(player0.getEnergy() / 2);
							player1 = new Player(copyPlayer1);
							player1.setEnergy(player1.getEnergy() + player0.getEnergy());
						}
					}

				} else if (copyPlayer1.getLevel() > board.getLevel() - 1) { //now checking if movement is valid or off the grid
					player1 = new Player(player1);
					System.out.println(
							"!!! Sorry you need to stay where you are - that throw takes you off the grid and you loose 2 energy points");
					offTheGrid++;
				} else
					player1 = new Player(copyPlayer1);

				if (offTheGrid == 1) { //no energy ajustement for not moving because of off grid
					player1.setEnergy(player1.getEnergy() - 2);
					offTheGrid--;
				} else {
					if (challengeChoice == 0 && winnerOrLoser < 6) { //if challenge loss, occupied square's energy value still needs to be used
						System.out
								.println("\tYour energy is adjusted by " + board.getEnergyAdj(copyPlayer1.getLevel(), copyPlayer1.getY(), copyPlayer1.getX())
										+ " for landing at (" + copyPlayer1.getY() + "," + copyPlayer1.getX()
										+ ") at level " + copyPlayer1.getLevel());
						player1.setEnergy(player1.getEnergy() + board.getEnergyAdj(copyPlayer1.getLevel(), copyPlayer1.getX(), copyPlayer1.getY()));
					} else { //normal player movement, so normal energy adjustement
						System.out.println("\tYour energy is adjusted by " + board.getEnergyAdj(player1.getLevel(), player1.getY(), player1.getX())
										 + " for landing at (" + player1.getY() + "," + player1.getX() + ") at level " + player1.getLevel());
						player1.setEnergy(player1.getEnergy()+ board.getEnergyAdj(player1.getLevel(), player1.getX(), player1.getY()));
					}
				}

				if (challengeChoice == 1 && player1.getLevel() >= 1) //if challenge forfeit, decrementing of level is after as energy value of occupied square needs to be used before
					player1.setLevel(player1.getLevel() - 1);
			} else { //no player movement as energy is 0
				System.out.println("Unfortunately, your turn skips " + player1.getName()
						+ " as your energy level is too low to move even after rolling 3 times. \nYou will get another 3 attempts next round");
			}

			System.out.println("\nAt the end of this round:" + "\n\t" + player0 + " \n\t" + player1); 
			if (player1.won(board)) //second break if player 1 won
				break;
			System.out.print("Any key to continue to the next round ...");
			if (challengeChoice == 0 || challengeChoice == 1)
				System.out.print("\n");
			String continueOn = input.next();
		}

		if (player0.won(board))
			System.out.println("\nThe winner is player " + player0.getName() + ". Well done!!!");
		if (player1.won(board) && !player0.won(board))
			System.out.println("\nThe winner is player " + player1.getName() + ". Well done!!!");
		input.close();
	}
}
