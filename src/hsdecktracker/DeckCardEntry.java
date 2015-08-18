package hsdecktracker;

import java.util.Comparator;

public class DeckCardEntry {
	private Card card;
	private int amount;
	/**
	 * @return the card
	 */
	public Card getCard() {
		return card;
	}
	/**
	 * @param card the card to set
	 */
	public void setCard(Card card) {
		this.card = card;
	}
	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public DeckCardEntry(Card card, int amount){
		this.card = card;
		this.amount = amount;
	}
	
	public static class DCECostComparator implements Comparator<DeckCardEntry>{

		@Override
		public int compare(DeckCardEntry dce1, DeckCardEntry dce2) {
			if(dce1 == null) return -1;
			if(dce2 == null) return 1;
					
			return dce1.getCard().getCost()-dce2.getCard().getCost();
		}
		
	}
}
