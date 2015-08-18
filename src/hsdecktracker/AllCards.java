package hsdecktracker;

import java.io.*;
import java.util.*;

import org.json.*;

public class AllCards {
	
	private static List<Card> cardList;
	
	static {
		cardList = loadCardsFromJsonFile();
	}

	private static List<Card> loadCardsFromJsonFile(){
		String cardsFile = AllCards.class.getResource("/resources/AllSets.json").getFile();
		StringBuilder sb = new StringBuilder();
		List<Card> result = new LinkedList<>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(cardsFile));
			String line;
			while((line = br.readLine())!=null){
				sb.append(line);
			}
			br.close();
		} catch (IOException e){
			
		}
		String jsonString = sb.toString();
		try{
			JSONObject rootNode = new JSONObject(jsonString);
			for(Iterator<String> iterator = rootNode.keys(); iterator.hasNext();){
				JSONArray cardArray = rootNode.getJSONArray(iterator.next());
				for(int i = 0; i<cardArray.length(); i++){
					JSONObject jsonCard = cardArray.getJSONObject(i);
					Card card = new Card();
                    if(jsonCard.has("id")) card.setId(jsonCard.getString("id"));
                    if(jsonCard.has("name")) card.setName(jsonCard.getString("name"));
                    if(jsonCard.has("type")) card.setType(jsonCard.getString("type"));
                    if(jsonCard.has("faction")) card.setFaction(jsonCard.getString("faction"));
                    if(jsonCard.has("rarity")) card.setRarity(jsonCard.getString("rarity"));
                    if(jsonCard.has("cost")) card.setCost(jsonCard.getInt("cost"));
                    if(jsonCard.has("text")) card.setText(jsonCard.getString("text"));
                    if(jsonCard.has("flavor")) card.setFlavor(jsonCard.getString("flavor"));
                    if(jsonCard.has("artist")) card.setArtist(jsonCard.getString("artist"));
                    if(jsonCard.has("collectible")) card.setCollectible(jsonCard.getBoolean("collectible"));
                    if(jsonCard.has("playerClass")) card.setPlayerClass(jsonCard.getString("playerClass"));
                    if(jsonCard.has("howToGet")) card.setHowToGet(jsonCard.getString("howToGet"));
                    if(jsonCard.has("howToGetGold")) card.setHowToGetGold(jsonCard.getString("howToGetGold"));


					result.add(card);
				}
			}
		} catch (JSONException e){
			System.err.println(e);
		}
		return result;
	}
	
	public static Card findCardByNameSane(String name){
		for(Card card: cardList){
			System.err.println("Loading Card "+card.getName());
			if(card.getName().equals(name) && (card.getType().equals("Minion") || card.getType().equals("Spell"))){
				return card;
			}
		}
		return null;
	}
	
	public static Card findCardByName(String name){
		for(Card card: cardList){
			if(card.getName().equals(name)){
				return card;
			}
		}
		return null;
	}
	
	public static Card findCardById(String id){
		for(Card card: cardList){
			if(card.getId().equals(id)){
				return card;
//			} else {
//				System.err.println("'"+id+"'"+" '"+card.getId()+"'");
			}
		}
		return null;
	}
}
