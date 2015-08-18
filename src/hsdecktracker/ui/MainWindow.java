package hsdecktracker.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

import hsdecktracker.Deck;

public class MainWindow {
	
	protected static Shell shell;
	protected static Label label;
    protected static Deck deck = null;

    public static void printDeck(Deck deck, Label label){
    	
    }
    
	public static void main(String[] args) {
        Display display = new Display();
        shell = new Shell(display);
        // the layout manager handle the layout
        // of the widgets in the container
        shell.setSize(300,800);
        shell.setLayout(new RowLayout());
        shell.setAlpha(100);
        label = new Label(shell, SWT.BORDER);
        label.setText("Test");
        label.pack();
        Button newGameButton = new Button(shell, SWT.PUSH);
        newGameButton.setText("New Game");
        newGameButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent event) {
				ClassSelectWindow.showClassSelectWindow(display);
				shell.setVisible(false);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent event) {
				System.err.println("THIS SHOULD NOT BE CALLED");
			}
		});
        newGameButton.pack();
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
}
