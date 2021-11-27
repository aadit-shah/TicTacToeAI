import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		TicTacToeGame g = new TicTacToeGame();

		/*Prints out directions and moves on the board player can make */
		System.out.println("Welcome to tic-tac-toe");
		Thread.sleep(1000);
		System.out.println("You will play first using the \"X\" \n");
		Thread.sleep(2000);
		g.printBoardPositions();

		/*Runs through game while there is no winner or tie*/
		while(g.status()==0 && !g.isTie()) {
			/*User Turn
			Make sure user enters a valid position, else give error*/
			System.out.println("Choose a square");
			int numInput = -1; 
			boolean isValid = false;
			while(!isValid) {
				numInput = sc.nextInt();
				if (!g.validMove(numInput)) {
	                System.out.println(
	                    "Invalid input; re-enter a valid position");
	            }
	            else
	            	isValid = true;
			}
			
			//adds move to board
			TicTacToeGame.addMove(numInput, "player");

				
			//If there is a winner or tie, game over and break out of loop
			if(g.status()!=0 || g.isTie()) {
				break;
			}
			
			
			
			/*Computer Move: Finds last optimal move using minimax algotithm*/
			int compMove = g.optimalMove();
			
			TicTacToeGame.addMove(compMove, "computer");
			g.printBoard();
				
			
		}
		//If user wins print board and display message
		if(g.status()==10) {
			System.out.println("Congrats You Have Won!");
			g.printBoard();
		
		}
		//If Computer wins print board and display message
		else if(g.status()==-10) {
			System.out.println("Congrats You Have Lost!");
			g.printBoard();
		}
		//If tie print board and display message
		else if(g.isTie()) {
			System.out.println("Tie Game!");
			g.printBoard();
		
		}
			
	}
}


