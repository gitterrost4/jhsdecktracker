package hsdecktracker;

public class Game {
	private Player friendlyPlayer = null;
	private Player opposingPlayer = null;
	private GameState gameState = GameState.MULLIGAN;
	
	public Game(){
		friendlyPlayer = new Player();
		opposingPlayer = new Player();
	}
	
	/**
	 * @return the friendlyPlayer
	 */
	public Player getFriendlyPlayer() {
		return friendlyPlayer;
	}
	/**
	 * @param friendlyPlayer the friendlyPlayer to set
	 */
	public void setFriendlyPlayer(Player friendlyPlayer) {
		this.friendlyPlayer = friendlyPlayer;
	}
	/**
	 * @return the opposingPlayer
	 */
	public Player getOpposingPlayer() {
		return opposingPlayer;
	}
	/**
	 * @param opposingPlayer the opposingPlayer to set
	 */
	public void setOpposingPlayer(Player opposingPlayer) {
		this.opposingPlayer = opposingPlayer;
	}
	/**
	 * @return the state
	 */
	public GameState getGameState() {
		return gameState;
	}
	/**
	 * @param state the state to set
	 */
	public void setGameState(GameState state) {
		this.gameState = state;
	}
	
	public enum GameState{
		MULLIGAN,
		NORMAL,
		FINISHED
	}
	
}
