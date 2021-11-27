
public class TicTacToeGame {
	private static char board[];
	public static char player = 'x';
	public static char computer = 'o';
	
	//Creates new Board
	public TicTacToeGame() {
		board = new char[9];
		for(int i = 0; i<9; i++) 
			board[i] = ' ';
		
		
	}
	
	/*if user has won returns 10
	 * if computer has won returns -10 
	 * otherwise returns 0 */
	public int status() {
		int result = 0;
		
		//Check rows
		for(int r = 0; r<7; r+=3) {
			if(board[r]==board[r+1]&&board[r] == board[r+2]) {
				if(board[r]==player)
					result = 10;
				else if(board[r]==computer)
					result = -10;
			}	
		}
		
		//Check columns
		for(int c = 0; c<3; c++) {
			if(board[c]==board[c+3]&&board[c] == board[c+6]) {
				if(board[c]==player)
					result = 10;
				else if(board[c]==computer)
					result = -10;
			}	
		}
		
		// Check Diagonals 
	    if (board[0] == board[4] && board[0] == board[8]){
	        if (board[0] == player)
	            return 10;
	        else if (board[0] == computer)
	            return -10;
	    }
	 
	    if (board[2] == board[4] && board[2] == board[6]){
	        if (board[2] == player)
	            return 10;
	        else if (board[2] == computer)
	            return -10;
	    }
		
		
		return result;
	}
	
	/*Minimax Algorithm, recursively calculates the value of each move 
	 * depth-> height of the recursive tree
	 * returns the value of each move assuming the best move is played by the opponent
	 */
	public int minimax(int depth, boolean isMax) {
		int result = status();
		
		if(depth==0 || this.isTie() || result!=0)
			return result;
		
		if(isMax) {
			int maxVal = -1000;
			
			for(int i = 0; i<9; i++) {
				if(board[i]==' ') {
					board[i] = player;
					maxVal = Math.max(maxVal, minimax(depth-1, false));
					board[i] = ' ';
				}
			}
			return maxVal;
		}
		else {
			int minVal = 1000;
			for(int i =0; i<9; i++) {
				if(board[i]==' ') {
					board[i]= computer;
					minVal = Math.min(minVal, minimax(depth-1,true));
					board[i] = ' ';
				}
			}
			return minVal;
		}
	}
	
	/*Uses the values of each move using "minimax"
	 * minimizes these values to find the move that will result in the minimum value
	 * AKA the optimal value since the computer is the "minimizer"
	 */
	public int optimalMove() {
		int bestMove = 0;
		int eval;
		int minEval = 1000;
		for(int i = 0; i<9; i++) {
			if(board[i] == ' ') {
				board[i] = computer;
				eval = minimax(5, true);
				if(minEval>=eval) {
					minEval = eval;
					bestMove = i;
				}
				
				board[i] = ' ';
			}
		}
		return bestMove;
	}
	
	/*Checks if there are no moves left*/ 
	public boolean isTie() {
		boolean flag = true;
		for(int i = 0; i<9; i++) {
			if(board[i]==' ')
				flag = false;
		}
		return flag;
	}
	/*Checks if move is "valid"
	 * valid- move has not been played before and between [0,8] (range of possible moves) 
	 */
	public boolean validMove(int userMove) {
		boolean flag = false;
		
		if(board[userMove]==' ' && userMove<=8 && userMove>=0)
			flag = true;
		
		return flag;
	}
	
	/*Prints the moves played on the board */
	public void printBoard() {
		System.out.println("|---|---|---|");
        System.out.println("| " + board[0] + " | "
                           + board[1] + " | " + board[2]
                           + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | "
                           + board[4] + " | " + board[5]
                           + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | "
                           + board[7] + " | " + board[8]
                           + " |");
        System.out.println("|---|---|---|");
        System.out.println("");
	}
	
	/*Prints the possible move positions*/
	public void printBoardPositions() {
		System.out.println("|---|---|---|");
        System.out.println("| " + 0 + " | "
                           + 1 + " | " + 2
                           + " |");
        System.out.println("|-----------|");
        System.out.println("| " + 3 + " | "
                           + 4 + " | " + 5
                           + " |");
        System.out.println("|-----------|");
        System.out.println("| " + 6 + " | "
                           + 7 + " | " + 8
                           + " |");
        System.out.println("|---|---|---|");
        System.out.println("");
	}
	
	/*Adds move to the board
	 * Adds "x" if param-user = "player"
	 * Adds "o" if param-user = "computer"
	 */
	public static void addMove(int userMove, String user) {
		
		if(user.equals("player")) 
			board[userMove] = player;
		
			
		else if(user.equals("computer")) 
			board[userMove] = computer;
	}
	
}
