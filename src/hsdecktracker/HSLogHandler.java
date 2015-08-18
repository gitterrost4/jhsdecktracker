package hsdecktracker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hsdecktracker.Game.GameState;

public class HSLogHandler {

	private static Pattern gameFinishedPattern;
	private static Pattern gameStartedPattern;
	private static Pattern cardFromDeckPattern;
	private static Pattern startFriendlyDrawPattern;
	private static Pattern startOpposingDrawPattern;
	private static Pattern mulliganStartPattern;
	private static Pattern mulliganDonePattern;
	private static Pattern drawStartingCardPattern;
	private static Pattern cardIntoDeckPattern;
	private static Pattern normalGameStartPattern;
	private static Pattern gangUpPlayedPattern;
	
	static{
		gameStartedPattern = Pattern.compile("\\[Power\\] GameState.DebugPrintPower() - CREATE_GAME");
		gameFinishedPattern = Pattern.compile("\\[Power\\] GameState.DebugPrintPower\\(\\) - TAG_CHANGE Entity=GameEntity tag=STATE value=COMPLETE");
		//cardFromDeckPattern = Pattern.compile("entity=\\[id=[0-9]* cardId=([^ ]*).*zone=DECK");
		startFriendlyDrawPattern = Pattern.compile("\\[Zone\\].*-> FRIENDLY PLAY \\(Hero\\)");
		startOpposingDrawPattern = Pattern.compile("\\[Zone\\].*-> OPPOSING PLAY \\(Hero\\)");
		cardFromDeckPattern = Pattern.compile("\\[Zone\\].*cardId=([^ ]*).* zone from FRIENDLY DECK -> ");
	//	mulliganStartPattern = Pattern.compile("\\[Power\\] PowerTaskList.*value=BEGIN_MULLIGAN");
		mulliganStartPattern = Pattern.compile("\\[Zone\\] ZoneChangeList.UpdateDirtyZones");
	//	mulliganDonePattern = Pattern.compile("\\[Power\\] ");
		drawStartingCardPattern = Pattern.compile("TRANSITIONING card.*cardId=([^ ]*).* to FRIENDLY HAND");
		cardIntoDeckPattern = Pattern.compile("\\[Zone\\].*cardId=([^ ]*).* zone from .* -> FRIENDLY DECK");
		normalGameStartPattern = Pattern.compile("\\[Power\\].*TAG_CHANGE Entity=GameEntity tag=STEP value=MAIN_READY");
		gangUpPlayedPattern = Pattern.compile("\\[Power\\] GameState.*ACTION_START Entity=\\[[^\\]]*cardId=BRM_007[^\\]]*player=([1-2])[^\\]]*][^\\[]*BlockType=POWER[^\\[]*Target=\\[[^\\]]*cardId=([^ ]*)");
	}
	
	public static boolean handleLogLine(String logLine, Game game){
//		System.err.println("call handlelog");
	//	System.err.println(logLine);
		if(logLine.startsWith("###")){
			System.err.println(logLine);
			return false;
		}
		if(!logLine.startsWith("[")){
			return false;
		}
		if(isLineGameFinished(logLine)){
			game.setGameState(GameState.FINISHED);
			return true;
		}
		Matcher m;
		if(game.getGameState().equals(GameState.NORMAL)|| game.getGameState().equals(GameState.MULLIGAN)){
			m = cardFromDeckPattern.matcher(logLine);
			if(m.find()){
				String cardId = m.group(1);
				Card card = Card.findById(cardId);
				if(card !=null){
					System.err.println("Removing Normal Card "+card.getName());
					System.err.println(logLine);
				}
				game.getFriendlyPlayer().getDeck().removeCard(card);
				return true;
			}
			
			m = gangUpPlayedPattern.matcher(logLine);
			if(m.find()){
				String player = m.group(1);
				String cardId = m.group(2);
				if(player.equals("1")){
					Card card = Card.findById(cardId);
					if(card !=null){
						System.err.println("Adding Card "+card.getName());	
						game.getFriendlyPlayer().getDeck().addCard(card);
						game.getFriendlyPlayer().getDeck().addCard(card);
						game.getFriendlyPlayer().getDeck().addCard(card);
					}
					return true;									
				}
				return false;
			}
		}
		
//		m = mulliganDonePattern.matcher(logLine);
//		if(m.find()){
//			String playerName = m.group(1);
//			if(playerName.equals(game.getFriendlyPlayer().getName())){
//				game.getFriendlyPlayer().setMulliganDone(true);
//			} else if(playerName.equals(game.getOpposingPlayer().getName())){
//				game.getOpposingPlayer().setMulliganDone(true);
//			}
//			return true;
//		}
		
		if(game.getGameState().equals(GameState.START) || game.getGameState().equals(GameState.STARTING_OPPOSING_DRAW)){
			m = startFriendlyDrawPattern.matcher(logLine);
			if(m.find()){
				System.err.println("Gamestate: START->START_FRIENDLY_DRAW");
				game.setGameState(GameState.STARTING_FRIENDLY_DRAW);
				return true;
			}
		}
		if(game.getGameState().equals(GameState.START) || game.getGameState().equals(GameState.STARTING_FRIENDLY_DRAW)){
			m = startOpposingDrawPattern.matcher(logLine);
			if(m.find()){
				System.err.println("Gamestate: START->START_OPPOSING_DRAW");
				game.setGameState(GameState.STARTING_OPPOSING_DRAW);
				return true;
			}
		}
		if(game.getGameState().equals(GameState.STARTING_FRIENDLY_DRAW) || game.getGameState().equals(GameState.STARTING_OPPOSING_DRAW)){
			m = mulliganStartPattern.matcher(logLine);
			if(m.find()){
				System.err.println("Gamestate: START->MULLIGAN");
				game.setGameState(GameState.MULLIGAN);
				return true;
			}
		}
		
		if(game.getGameState().equals(GameState.MULLIGAN)){
			m = normalGameStartPattern.matcher(logLine);
			if(m.find()){
				System.err.println("Gamestate: MULLIGAN->NORMAL");
				game.setGameState(GameState.NORMAL);
				return true;
			}
		}
		
		if(game.getGameState().equals(GameState.MULLIGAN) || game.getGameState().equals(GameState.NORMAL)){
			m = cardIntoDeckPattern.matcher(logLine);
			if(m.find()){
				String cardId = m.group(1);
				Card card = Card.findById(cardId);
				if(card !=null){
					System.err.println("Adding Card "+card.getName());	
					game.getFriendlyPlayer().getDeck().addCard(card);
				}
				return true;				

			}
		}
		
		if(game.getGameState().equals(GameState.STARTING_FRIENDLY_DRAW)){
			m = drawStartingCardPattern.matcher(logLine);
			if(m.find()){
				String cardId = m.group(1);
				Card card = Card.findById(cardId);
				if(card !=null){
					System.err.println("Removing Mulligan Card "+card.getName());	
					System.err.println(logLine);
				}
				game.getFriendlyPlayer().getDeck().removeCard(card);
				return true;				
			}
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
