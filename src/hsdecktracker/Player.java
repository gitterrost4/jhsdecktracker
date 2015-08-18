package hsdecktracker;

public class Player {
	private String name = null;
	private Deck deck = null;
	private boolean mulliganDone = false;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the deck
	 */
	public Deck getDeck() {
		return deck;
	}
	/**
	 * @param deck the deck to set
	 */
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	/**
	 * @return the mulliganDone
	 */
	public boolean isMulliganDone() {
		return mulliganDone;
	}
	/**
	 * @param mulliganDone the mulliganDone to set
	 */
	public void setMulliganDone(boolean mulliganDone) {
		this.mulliganDone = mulliganDone;
	}
	

}
