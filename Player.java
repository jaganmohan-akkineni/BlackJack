import java.util.*;

public class Player{
	int score, choice, ace_count;
	
	Player() {
		this.score = 0;
		this.choice = 0; 	//0 for hit and 1 for stay.
		this.ace_count = 0;
	}
	
	// Re-initialize the object to play next round.
	public void initialize() {
		this.score = 0;
		this.choice = 0;
		this.ace_count = 0;
	}
	
	static class Card{
		int val, count;
		
		Card(int val){
			this.val = val;
			this.count = 4; // count is set as 4 to represent all 4 suites.
		}
	}
	
	
	// Initialize the deck with 52 cards. Each Card object has count=4 representing 4 suites.
	public static ArrayList<Card> initialize(ArrayList<Card> deck){
		for(int i=2; i<15; i++) deck.add(new Card(i));
			
		return deck;
	}
	
	// Calculate and update score each time a player gets a new card
	public void updateScore(int val) {
		if(val<11) {
			this.score += val; // add the card number if it is between 2 and 10.
		}
		else if(val>10 && val <14) {
			this.score += 10; // add 10 if the card is J or Q or K.
		}
		else {
			/* If the score exceeds 21, count ace as 1.
			 * Do not increment the ace_count if an ace is counted as 1.
			 * Increment the ace_count if it is counted as 11.
			 */
			if(this.score + 11>21) this.score += 1;
			else {
				this.score += 11;
				this.ace_count += 1;
			}
		}
		
		// If getting a new card makes score > 21, check if there are aces that are counted as 11.
		if(this.score>21 && this.ace_count>0) {
			this.score -= 10;
			this.ace_count -= 1;
		}
		
		// Make choice stay when the player gets perfect score.
		if(this.score==21) this.choice = 1;
	}
	
	// Get a random card from remaining cards.
		public int getCard(ArrayList<Card> deck) {
		int index = new Random().nextInt(deck.size());
		
		Card temp = deck.get(index);
		
		this.updateScore(temp.val);
		
		deck.get(index).count -= 1;
		
		// If a number is taken out 4 times, remove it from the deck.
		if(deck.get(index).count==0) {
			deck.set(index, deck.get(deck.size()-1)); //sets the current index with the last index card
			
			deck.remove(deck.size()-1); // Removes the last index
		}
		
		return temp.val;
	}
	
	// Give each player his/her first two cards.
	public void firstTwoCards(Player p1, Player p2, ArrayList<Card> deck) {
		for(int i=0; i<2; i++) {
			int card = p1.getCard(deck);
			System.out.print("Player 1's card " + (i+1) + " is " + card); 
			
			card = p2.getCard(deck);
			System.out.print(" Player 2's card " + (i+1) + " is " + card + "\n"); 
		}
	}
	
	public static void playGame(Player p1, Player p2, ArrayList<Card> deck) {

		Scanner reader = new Scanner(System.in);

		while((p1.score<=21 && p2.score<=21)) {
			
			//if both players chose to stay.
			if(p1.choice==1 && p2.choice==1) break; 
			
			int card;
			if(p1.choice == 0) {
				System.out.println("Enter choice for Player1: 0 for hit and 1 for stay");
				
				p1.choice = reader.nextInt(); 
				
				if(p1.choice == 0) {
					card = p1.getCard(deck);
					
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
					card = p2.getCard(deck);

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
		ArrayList<Card> deck = new ArrayList<>();
		
		Player p1 = new Player();
		Player p2 = new Player();
		
		int nextGame=1;
		Scanner reader = new Scanner(System.in);
		
		while(nextGame==1) {
			
			deck = initialize(deck);
			
			p1.initialize();
			p2.initialize();
			
			firstTwoCards(p1, p2, deck);
			
			System.out.println("\nPlayer 1 score: " + p1.score + " Player 2 score: " + p2.score + "\n");
			
			playGame(p1, p2, deck);
			
			System.out.println("\nEnter 1 to continue playing; 0 to exit");
			nextGame = reader.nextInt();
		}
		reader.close();
		
		System.out.println("Game Exited");
	}
}