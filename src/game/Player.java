package game;

public class Player {
	int score, choice, ace_count;
	
	Player() {
		this.score = 0;
		this.choice = 0; 	//0 for hit and 1 for stay.
		this.ace_count = 0;
	}
	
	public void initialize() {
		this.score = 0;
		this.choice = 0;
		this.ace_count = 0;
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
	
	
}
