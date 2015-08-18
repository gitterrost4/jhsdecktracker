package hsdecktracker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hsdecktracker.Game.GameState;

public class HSLogHandler {

	private static Pattern gameFinishedPattern;
	private static Pattern gameStartedPattern;
	private static Pattern cardFromDeckPattern;
	
	static{
		gameStartedPattern = Pattern.compile("\\[Power\\] GameState.DebugPrintPower() - CREATE_GAME");
		gameFinishedPattern = Pattern.compile("\\[Power\\] GameState.DebugPrintPower\\(\\) - TAG_CHANGE Entity=GameEntity tag=STATE value=COMPLETE");
		cardFromDeckPattern = Pattern.compile("entity=\\[id=[0-9]* cardId=([^ ]*).*zone=DECK");

	}
	
	public static boolean handleLogLine(String logLine, Game game){
	//	System.err.println(logLine);
		if(isLineGameFinished(logLine)){
			game.setGameState(GameState.FINISHED);
			return true;
		}
		Matcher m;
		m = cardFromDeckPattern.matcher(logLine);
		if(m.find()){
			String cardId = m.group(1);
			Card card = Card.findById(cardId);
			if(card !=null){
				System.err.println("Removing Card "+card.getName());	
			}
			game.getFriendlyPlayer().getDeck().removeCard(card);
			return true;
		}
		return false;
	}
	
	public static boolean isLineGameStarted(String logLine){
		Matcher m = gameStartedPattern.matcher(logLine);
		return m.find();
	}
	
	public static boolean isLineGameFinished(String logLine){
		Matcher m = gameFinishedPattern.matcher(logLine);
		return m.find();
	}
}
