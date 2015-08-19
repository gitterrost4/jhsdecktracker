package hsdecktracker.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

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
		File f = new File("/home/gitterrost4/hsdecks");
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
		shell.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				shell=null;
			}
		});
	}
}
