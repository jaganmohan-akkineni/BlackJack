package game;

import java.util.ArrayList;
import java.util.Random;


public class Deck {
	ArrayList<Card> deck;
	
	Deck(){
		deck = new ArrayList<Card>();
	}
	
	public void initialize(){
		for(int i=2; i<15; i++) this.deck.add(new Card(i));
			
	}
	
	public int getCard() {
		int index = new Random().nextInt(this.deck.size());
		
		Card temp = this.deck.get(index);
				
		this.deck.get(index).count -= 1;
		
		// If a number is taken out 4 times, remove it from the deck.
		if(deck.get(index).count==0) {
			deck.set(index, deck.get(deck.size()-1)); //sets the current index with the last index card
			
			deck.remove(deck.size()-1); // Removes the last index
		}
		
		return temp.val;
	}	
}
