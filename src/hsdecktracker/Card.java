package hsdecktracker;

public class Card extends Object {
	private String id;
	private String name;
	private String type;
	private String faction;
	private String rarity;
	private int cost;
	private String text;
	private String flavor;
	private String artist;
	private Boolean collectible;
	private String playerClass;
	private String howToGet;
	private String howToGetGold;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the faction
	 */
	public String getFaction() {
		return faction;
	}
	/**
	 * @param faction the faction to set
	 */
	public void setFaction(String faction) {
		this.faction = faction;
	}
	/**
	 * @return the rarity
	 */
	public String getRarity() {
		return rarity;
	}
	/**
	 * @param rarity the rarity to set
	 */
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}
	/**
	 * @param cost the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the flavor
	 */
	public String getFlavor() {
		return flavor;
	}
	/**
	 * @param flavor the flavor to set
	 */
	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}
	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}
	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	/**
	 * @return the collectible
	 */
	public Boolean getCollectible() {
		return collectible;
	}
	/**
	 * @param collectible the collectible to set
	 */
	public void setCollectible(Boolean collectible) {
		this.collectible = collectible;
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
	 * @return the howToGet
	 */
	public String getHowToGet() {
		return howToGet;
	}
	/**
	 * @param howToGet the howToGet to set
	 */
	public void setHowToGet(String howToGet) {
		this.howToGet = howToGet;
	}
	/**
	 * @return the howToGetGold
	 */
	public String getHowToGetGold() {
		return howToGetGold;
	}
	/**
	 * @param howToGetGold the howToGetGold to set
	 */
	public void setHowToGetGold(String howToGetGold) {
		this.howToGetGold = howToGetGold;
	}
	
	public static Card findByNameSane(String name){
		return AllCards.findCardByNameSane(name);
	}
	
	public static Card findByName(String name){
		return AllCards.findCardByName(name);
	}
	
	public static Card findById(String id){
		return AllCards.findCardById(id);
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj==null){
			return false;
		}
		if(getClass()!= obj.getClass()){
			return false;
		}
		final Card other = (Card) obj;
		return other.getId().equals(getId());
	}

}
