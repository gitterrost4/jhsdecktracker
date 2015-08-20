package hsdecktracker.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

import hsdecktracker.Configuration;

public class ClassSelectWindow {
	private static Shell shell = null;
	public static void showClassSelectWindow(Display display){
		if(shell!=null){
			return;
		}
		shell = new Shell(display);
		FillLayout layout = new FillLayout();
		layout.type = SWT.VERTICAL;
		shell.setLayout(layout);
		shell.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				shell=null;
			}
		});

		File f = new File(Configuration.getConfig().getProperty("deckDir"));
		if(!f.exists()){
			MessageBox message = new MessageBox(shell,SWT.ICON_ERROR| SWT.OK);
			message.setMessage("Deck directory is not configured properly or not writable. Please reconfigure.");
			message.setText("Configuration Error");
			message.open();
			shell.dispose();
			return;
		}
		ArrayList<File> classes = new ArrayList<>(Arrays.asList(f.listFiles()));
		for(File playerClassFile : classes){
			String playerClass = playerClassFile.getName();
			Button b = new Button(shell, SWT.BUTTON1);
			b.setText(playerClass);
			b.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					DeckSelectWindow.showDeckSelectWindow(display, playerClass);
					shell.dispose();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					//empty
				}
			});
			b.pack();
		}
		shell.open();
	}
}
