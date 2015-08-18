package hsdecktracker;

import java.io.*;
import java.util.*;

public class Deck {
	public static final String BASEPATH = "/home/gitterrost4/hsdecks";  
	private String name;
	private String playerClass; //TODO: make this an enum
	private List<DeckCardEntry> cards;
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
	 * @return the playerClass
	 */
	public String getPlayerClass() {
		return playerClass;
	}
	/**
	 * @param playerClass the playerClass to set
	 */
	public void setPlayerClass(String playerClass) {
		this.playerClass = playerClass;
	}
	/**
	 * @return the cards
	 */
	public List<DeckCardEntry> getCards() {
		return cards;
	}
	/**
	 * @param cards the cards to set
	 */
	public void setCards(List<DeckCardEntry> cards) {
		this.cards = cards;
	}
	
	public Deck(){
		cards = new LinkedList<>();
	}
	
	public void addCard(Card card, int amount){
		DeckCardEntry newCard = new DeckCardEntry(card, amount);
		cards.add(newCard);
	}
	
	public static Deck loadDeckFromFile(String fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;
		Deck newDeck = new Deck();
		while ((line = br.readLine()) != null){
			String[] lineArray = line.split(" ",2);
			int amount = Integer.parseInt(lineArray[0]);
			
			String name = lineArray[1].trim();
			System.err.println(name);
			Card newCard = Card.findByNameSane(name);
			newDeck.addCard(newCard, amount);
		}
		br.close();
		return newDeck;
	}
	
	public void removeCard(Card card){
		for(DeckCardEntry dce: cards){
			if(dce.getCard().equals(card)){
				if(dce.getAmount()>0){
					dce.setAmount(dce.getAmount()-1);
				}
			}
		}
	}
	
	public Deck sortByManaCost(){
		Collections.sort(cards, new DeckCardEntry.DCECostComparator());
		return this;
	}
}
