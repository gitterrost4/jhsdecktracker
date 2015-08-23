package hsdecktracker;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class CardCollection {
	public Map<String,DeckCardEntry> cards = new HashMap<>();
	
	public CardCollection(){
		for(Card card : AllCards.getCollectibleCards()){
			DeckCardEntry dce;
			if(card.getCardSet() == "Basic"){
				dce = new DeckCardEntry(card,card.getMaxAmount());
			} else {
				dce = new DeckCardEntry(card, card.getMinAmount());				
			}
			cards.put(card.getId(),dce);
		}		
	}
	
	public CardCollection(JSONObject jo){
		this();
		if(jo.has("cards")){
			JSONArray ja = jo.getJSONArray("cards");
			for(int i = 0; i < ja.length(); i++){
				JSONObject jsonCard = ja.getJSONObject(i);
				Card card = Card.findById(jsonCard.getString("cardId"));
				DeckCardEntry dce = new DeckCardEntry(card, jsonCard.getInt("amount"));
				cards.put(card.getId(), dce);
			}
		}
	}
		
	public JSONObject toJson(){
		JSONObject jo = new JSONObject();
		for(DeckCardEntry dce : cards.values()){
			JSONObject newCard = new JSONObject();
			newCard.put("cardId", dce.getCard().getId());
			newCard.put("amount", dce.getAmount());
			jo.append("cards",newCard);
		}
		return jo;
	}
	
	/**
	 * Adds 1 to the amount of the specified card or wraps it back to its minimum (usually zero)
	 * 
	 * @param cardId
	 * @return newAmount of the card in the collection
	 */
	public int addCardWithWrap(String cardId){
		DeckCardEntry dce = cards.get(cardId);
		int currentAmount = dce.getAmount();
		int newAmount = currentAmount + 1;
		if(currentAmount == dce.getCard().getMaxAmount()){
			newAmount = dce.getCard().getMinAmount();
		}		
		dce.setAmount(newAmount);
		return newAmount;
	}
	
	/**
	 * Adds 1 to the amount of the specified card or wraps it back to its minimum (usually zero)
	 * 
	 * @param card
	 * @return
	 */
	public int addCardWithWrap(Card card){
		return addCardWithWrap(card.getId());
	}
	
	public List<DeckCardEntry> getCardsForClass(PlayerClass playerClass){
		List<DeckCardEntry> result = new ArrayList<>();
		for(DeckCardEntry dce: cards.values()){
			if(dce.getCard().getPlayerClass() == playerClass){
				result.add(dce);
			}
		}
		return result;
	}
	
}
