package hsdecktracker.ui;

import java.io.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

import hsdecktracker.*;
import hsdecktracker.Game.GameState;

public class DeckWindow {
	private static Shell shell = null;
	public static void showDeckWindow(Display display, Deck deck){
		if(shell!=null){
			return;
		}

		Game game = new Game();
		deck.sortByManaCost();
		game.getFriendlyPlayer().setDeck(deck);
		shell = new Shell(display, SWT.TOOL | SWT.ON_TOP);
		FillLayout layout = new FillLayout();
		layout.type = SWT.VERTICAL;
		shell.setLayout(layout);
		shell.setSize(200,800);
		shell.setLocation(display.getBounds().width-200,display.getBounds().height/2-400);
		updateShell(display, shell, deck);
		shell.open();
		shell.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				shell = null;
				MainWindow.show();				
			}
		});
        Thread updateThread = new Thread(){
        	public void run(){
        		
        		try {
        			BufferedReader br = new BufferedReader(new FileReader("/home/gitterrost4/.wine/drive_c/Program Files/Hearthstone/Hearthstone_Data/output_log.txt"));
        			int lastIndex = findLastGameStartOrEndIndex();
        			for(int i = 0; i<=lastIndex; i++){
        				br.readLine();
        			}
        			
        			System.err.println("####READY####");
	
        			do {
    					if(game.getGameState() == GameState.FINISHED){
    						display.syncExec(new Runnable(){
    							public void run(){
        							shell.dispose();        								
    							}
    						});
    					}
    					String nextLine=br.readLine();
        				if(nextLine == null){
        					Thread.sleep(500);
        				} else {
        					if(HSLogHandler.handleLogLine(nextLine, game)){
            					updateShell(display, shell, game.getFriendlyPlayer().getDeck());        						
        					}
//        					p = Pattern.compile("entity=\\[id=[0-9]* cardId=([^ ]*).*zone=DECK");
//        					m = p.matcher(nextLine);
//        					if(m.find()){
//        						System.err.println(nextLine);
//        						String cardId = m.group(1);
//        						Card card = Card.findById(cardId);
//        						if(card !=null){
//        							System.err.println("Removing Card "+card.getName());	
//        						}
//        						deck.removeCard(card);
//            					updateShell(display, shell, deck);
//        					}
        				}
        			}	while(!shell.isDisposed());
        			br.close();
        		} catch (Exception e1) {
        			e1.printStackTrace();
        		}
        		try{
        			Thread.sleep(1000);
        		} catch (InterruptedException e){
        			e.printStackTrace();
        		}
        	}
        };
        updateThread.start();
        

	}
	private static void updateShell(Display display, Shell shell, Deck deck){
		display.syncExec(new Runnable(){
			public void run(){
				for(Control control : shell.getChildren()){
					control.dispose();
				}
				for(DeckCardEntry dce : deck.getCards()){
					Label label = new Label(shell,SWT.BORDER);

					label.setText(dce.getCard().getCost()+" "+dce.getCard().getName()+" "+dce.getAmount()+"\n");
					label.pack();
				}
				shell.layout(true);
			}
		});

	}
	
	private static int findLastGameStartOrEndIndex() throws IOException{
		System.err.println("Start Finding Current Match");
		int result = -1;
		int index = 0;
		FileReader fr = new FileReader("/home/gitterrost4/.wine/drive_c/Program Files/Hearthstone/Hearthstone_Data/output_log.txt");
		BufferedReader br = new BufferedReader(fr);
		String line;
		while((line = br.readLine())!=null){
			if(HSLogHandler.isLineGameStarted(line)){
				result = index;
			}
			if(HSLogHandler.isLineGameFinished(line)){
				result = index;
			}
			index++;
		}
		br.close();
		System.err.println("End Finding Current Match");
		return result;
	}
}
