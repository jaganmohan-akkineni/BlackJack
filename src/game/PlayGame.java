package game;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayGame {
	
	public static void firstTwoCards(Player p1, Player p2, Deck d) {
		for(int i=0; i<2; i++) {
			int card = d.getCard();
			p1.updateScore(card);
			
			System.out.print("Player 1's card " + (i+1) + " is " + card); 
			
			card = d.getCard();
			p2.updateScore(card);
			
			System.out.print(" Player 2's card " + (i+1) + " is " + card + "\n"); 
		}
	}
	
	public static void playGame(Player p1, Player p2, Deck d)
	{
		Scanner reader = new Scanner(System.in);

		while((p1.score<=21 && p2.score<=21)) {
			
			//if both players chose to stay.
			if(p1.choice==1 && p2.choice==1) break; 
			
			int card;
			if(p1.choice == 0) {
				System.out.println("Enter choice for Player1: 0 for hit and 1 for stay");
				
				p1.choice = reader.nextInt(); 
				
				if(p1.choice == 0) {
					card = d.getCard();
					p1.updateScore(card);
					
					System.out.println("Card for Player1: " + card );
					System.out.println("Player1 score: " + p1.score + " Player2 score: " + p2.score);
					
					if(p1.score>21) break; // If score exceed 21 then player is busted
				}
			}
			//break if player 2's score is greater than player1's score when player1 chooses to stay.
			else {
				if(p2.score>p1.score) break;
			}
			
			if(p2.choice == 0) {
				System.out.println("Enter choice for Player2: 0 for hit and 1 for stay");
				
				p2.choice = reader.nextInt(); 
				
				if(p2.choice == 0) {
					card = d.getCard();
					p2.updateScore(card);

					System.out.println("Card for Player2: " + card);
					System.out.println("Player1 score: " + p1.score + " Player2 score: " + p2.score);
				}
			}
			else {
				if(p1.score>p2.score) break;
			}
		}

		if(p1.score<=21 && p2.score<=21) {
			if(p1.score>p2.score) System.out.println("Player 1 wins!");
			else if(p1.score<p2.score) System.out.println("Player 2 wins!");
			else System.out.println("Game tied!");
		}
		else {
			if(p1.score>21) System.out.println("Player 1 busted Player 2 wins!");
			else System.out.println("Player 1 wins Player 2 busted");
			
		}
	}	
	
	public static void main(String[] args) {
		
		Player p1 = new Player();
		Player p2 = new Player();
		
		Deck d = new Deck();
		
		int nextGame=1;
		Scanner reader = new Scanner(System.in);
		
		while(nextGame==1) {
			
			d.initialize();;
			
			p1.initialize();
			p2.initialize();
			
			firstTwoCards(p1, p2, d);
			
			System.out.println("\nPlayer 1 score: " + p1.score + " Player 2 score: " + p2.score + "\n");
			
			playGame(p1, p2, d);
			
			System.out.println("\nEnter 1 to continue playing; 0 to exit");
			nextGame = reader.nextInt();
		}
		reader.close();
		
		System.out.println("Game Exited");
		
	}

}
