package hsdecktracker.ui;

import java.io.*;
import java.net.URL;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
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
		shell = new Shell(display, SWT.TOOL | SWT.ON_TOP | SWT.TRANSPARENT);
		FillLayout layout = new FillLayout();
		layout.type = SWT.VERTICAL;
		shell.setLayout(layout);
		shell.setAlpha(255);
		shell.setLocation(display.getBounds().width-500,display.getBounds().height/2-400);
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
        			}	while(shell!=null && !shell.isDisposed());
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
					Composite comp = new Composite(shell, SWT.TRANSPARENT);
					

					if(dce.getAmount() == 0){
						ImageData frameOverlayImgData = new ImageData(DeckWindow.class.getResource("/resources/frame_overlay.png").getFile());
		//				frameOverlayImgData.alpha = 128;
						Image frameOverlayImg = new Image(display, frameOverlayImgData);						
						Label frameLabel = new Label(comp, SWT.NORMAL);
						frameLabel.setImage(frameOverlayImg);
						frameLabel.pack();
					}
					
					GridData layoutData = new GridData(GridData.FILL_BOTH);
					layoutData.verticalAlignment = SWT.CENTER;
					layoutData.horizontalAlignment = SWT.CENTER;
					layoutData.grabExcessVerticalSpace = true;
					

					Label label = new Label(comp,SWT.NORMAL);
					label.setText(dce.getCard().getName());
					Font labelFont = new Font(display, new FontData( "Arial", 10, SWT.BOLD));
					label.setFont(labelFont);
					label.setForeground(new Color(display, 255, 255, 255));
					label.setBackground(new Color(display, 62, 69, 82));
					label.setLocation(40, 12);
					label.setLayoutData(layoutData);					
					label.pack();

					Image frameImg = new Image(display, DeckWindow.class.getResource("/resources/frame_rarity_common.png").getFile());
					
					Label manaCostLabel = new Label(comp,SWT.NORMAL);
					manaCostLabel.setBounds(4,5,30,29);
					manaCostLabel.setText(dce.getCard().getCost()+"");
					manaCostLabel.setForeground(new Color(display, 255, 255, 255));
					Font manaCostFont = new Font(display, new FontData( "Arial", 16, SWT.BOLD));
					manaCostLabel.setFont(manaCostFont);
					manaCostLabel.setLayoutData(layoutData);
					manaCostLabel.setAlignment(SWT.CENTER);
					
					if(dce.getAmount()>1 && dce.getAmount()<10 || dce.getCard().getRarity().equals("Legendary")){
						
							Label cardAmountLabel = new Label(comp,SWT.NORMAL);
							cardAmountLabel.setLocation(193,7);
							Image amountImage;
							if(dce.getAmount()>1){
								amountImage = new Image(display, DeckWindow.class.getResource("/resources/frame_"+dce.getAmount()+".png").getFile());
							} else {
								//Legendary
								amountImage = new Image(display, DeckWindow.class.getResource("/resources/frame_legendary.png").getFile());	
							}
							cardAmountLabel.setImage(amountImage);
							cardAmountLabel.pack();
						


						Label cardCountboxLabel = new Label(comp,SWT.NORMAL);
						cardCountboxLabel.setLocation(187,6);
						Image countboxImage = new Image(display,DeckWindow.class.getResource("/resources/frame_countbox.png").getFile());
						cardCountboxLabel.setImage(countboxImage);
						cardCountboxLabel.pack();

					}
					
					
					
					Label frameLabel = new Label(comp, SWT.NORMAL);
					frameLabel.setImage(frameImg);
					frameLabel.pack();
					
					Label imageLabel = new Label(comp,SWT.NORMAL);
					String fileName = dce.getCard().getName().toLowerCase().replaceAll("[^a-z0-9]", "-")+".png";
					URL url = DeckWindow.class.getResource("/resources/cards/"+fileName);
					if(url != null){
						Image img = new Image(display, url.getFile());
						imageLabel.setImage(img);
						imageLabel.setLocation(frameImg.getBounds().width-img.getBounds().width-6,0);
					}
					imageLabel.pack();
					


					comp.pack();
				}
				shell.layout(true);
				shell.pack();
				shell.setLocation(display.getBounds().width-shell.getBounds().width, (display.getBounds().height-shell.getBounds().height)/2);
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
