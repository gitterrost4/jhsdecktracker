package hsdecktracker.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import hsdecktracker.CardCollection;
import hsdecktracker.Deck;

public class MainWindow {
	
	protected static Shell shell;
	protected static Label label;
    protected static Deck deck = null;
    private static Button stopGameButton;
    private static Button newGameButton;

    public static void printDeck(Deck deck, Label label){
    	
    }
    
	public static void main(String[] args) {
        Display display = new Display();
        shell = new Shell(display);
        // the layout manager handle the layout
        // of the widgets in the container
        shell.setSize(300,800);
        FormLayout layout = new FormLayout();
        layout.marginHeight = 5;
        layout.marginWidth = 5;
        shell.setLayout(layout);
        
       // shell.setAlpha(100);
        newGameButton = new Button(shell, SWT.PUSH);
        newGameButton.setText("New Game");
        newGameButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent event) {
				ClassSelectWindow.showClassSelectWindow(display);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent event) {
				System.err.println("THIS SHOULD NOT BE CALLED");
			}
		});
        newGameButton.pack();
        
        stopGameButton = new Button(shell, SWT.PUSH);
        stopGameButton.setVisible(false);
        stopGameButton.setText("Stop Game");
        stopGameButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DeckWindow.stopGame();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// empty
				
			}
		});
        
        Button settingsButton = new Button(shell, SWT.PUSH);
        settingsButton.setText("Settings");
        settingsButton.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SettingsWindow.showSettingsWindow(display);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        	
        });
        
        Button exitButton = new Button(shell, SWT.PUSH);
        exitButton.setText("Exit");
        exitButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				//empty
			}
		});
        
        Button testButton = new Button(shell, SWT.PUSH);
        testButton.setText("Test");
        testButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				CardCollection cc = new CardCollection();
				cc.toJson();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
        //Formatting the controls
        
        FormData formData = new FormData();
        formData.top = new FormAttachment(0, 0);
        formData.left = new FormAttachment(0,0);
        formData.right = new FormAttachment(100,0);
        newGameButton.setLayoutData(formData);
        
        formData = new FormData();
        formData.left = new FormAttachment(0, 0);
        formData.top = new FormAttachment(0,0);
        formData.right = new FormAttachment(100, 0);
        stopGameButton.setLayoutData(formData);
        
        formData = new FormData();
        formData.top = new FormAttachment(newGameButton, 5);
        formData.left = new FormAttachment(0, 0);
        formData.right = new FormAttachment(100,0);
        settingsButton.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(settingsButton, 5);
        formData.left = new FormAttachment(0, 0);
        formData.right = new FormAttachment(100,0);
        exitButton.setLayoutData(formData);

        formData = new FormData();
        formData.bottom = new FormAttachment(100, 0);
        formData.left = new FormAttachment(0, 0);
        testButton.setLayoutData(formData);

        
        
        shell.open();
        
        while (!shell.isDisposed()) {
        	if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
	}
	public static void show(){
		System.err.println("ShowWindow");
		shell.setVisible(true);
	}
	
	public static void setStopGameButtonEnabled(boolean isEnabled){
		stopGameButton.setVisible(isEnabled);
	}
	public static void setNewGameButtonEnabled(boolean isEnabled){
		newGameButton.setVisible(isEnabled);
	}
	
	
}
