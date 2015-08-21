package hsdecktracker;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class CardCollection {
	public List<DeckCardEntry> cards = new ArrayList<>();
	
	public void toJson(){
		Card vc = Card.findByNameSane("Voidcaller");
		DeckCardEntry dce1 = new DeckCardEntry(vc, 1);
		cards.add(dce1);
		vc = Card.findByNameSane("Secretkeeper");
		dce1 = new DeckCardEntry(vc, 2);
		cards.add(dce1);
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		for(DeckCardEntry dce : cards){
			JSONObject newCard = new JSONObject();
			newCard.put("cardId", dce.getCard().getId());
			newCard.put("amount", dce.getAmount());
			jo.append("cards",newCard);
		}
		//jo.append("cards", ja);
		System.err.println(jo.toString());
	}
	
	
}
