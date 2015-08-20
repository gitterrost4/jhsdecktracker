package hsdecktracker.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

import hsdecktracker.Configuration;
import hsdecktracker.Deck;

public class DeckSelectWindow {
	private static Shell shell = null;
	public static void showDeckSelectWindow(Display display, String playerClass){
		if(shell!=null){
			return;
		}
		shell = new Shell(display);
		FillLayout layout = new FillLayout();
		layout.type = SWT.VERTICAL;
		shell.setLayout(layout);
		File f = new File(Configuration.getConfig().getProperty("deckDir")+"/"+playerClass);
		ArrayList<File> decks = new ArrayList<>(Arrays.asList(f.listFiles()));
		for(File deckFile : decks){
			String deckName = deckFile.getName();
			Button b = new Button(shell, SWT.BUTTON1);
			b.setText(deckName);
			b.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					try{
						Deck deck = Deck.loadDeckFromFile(deckFile.getAbsolutePath());
						DeckWindow.showDeckWindow(display, deck);
						shell.dispose();
					} catch(IOException e){
						System.err.println(e);
					}
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					//empty
				}
			});
			b.pack();
		}
		shell.open();
		shell.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				shell=null;
			}
		});
		
	}
}
