package hsdecktracker.ui;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import hsdecktracker.Configuration;

public class ClassSelectWindow {
	private static Shell shell = null;
	public static void showClassSelectWindow(Display display){
		if(shell!=null){
			return;
		}
		shell = new Shell(display, SWT.APPLICATION_MODAL);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		layout.numColumns = 3;
		layout.makeColumnsEqualWidth = true;
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
				
		Button druidButton = new Button(shell, SWT.PUSH);
//		druidButton.setText("Druid");
		druidButton.setImage(new Image(display, ClassSelectWindow.class.getResourceAsStream("/resources/classes/druid.png")));
		druidButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DeckSelectWindow.showDeckSelectWindow(display, "Druid");
				shell.dispose();				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button hunterButton = new Button(shell, SWT.PUSH);
//		hunterButton.setText("Hunter");
		hunterButton.setImage(new Image(display, ClassSelectWindow.class.getResourceAsStream("/resources/classes/hunter.png")));
		hunterButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DeckSelectWindow.showDeckSelectWindow(display, "Hunter");
				shell.dispose();				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button mageButton = new Button(shell, SWT.PUSH);
		mageButton.setImage(new Image(display, ClassSelectWindow.class.getResourceAsStream("/resources/classes/mage.png")));
//		mageButton.setText("Mage");
		mageButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DeckSelectWindow.showDeckSelectWindow(display, "Mage");
				shell.dispose();				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button paladinButton = new Button(shell, SWT.PUSH);
		paladinButton.setImage(new Image(display, ClassSelectWindow.class.getResourceAsStream("/resources/classes/paladin.png")));
//		paladinButton.setText("Paladin");
		paladinButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DeckSelectWindow.showDeckSelectWindow(display, "Paladin");
				shell.dispose();				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button priestButton = new Button(shell, SWT.PUSH);
		priestButton.setImage(new Image(display, ClassSelectWindow.class.getResourceAsStream("/resources/classes/priest.png")));
//		priestButton.setText("Priest");
		priestButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DeckSelectWindow.showDeckSelectWindow(display, "Priest");
				shell.dispose();				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button rogueButton = new Button(shell, SWT.PUSH);
		rogueButton.setImage(new Image(display, ClassSelectWindow.class.getResourceAsStream("/resources/classes/rogue.png")));
//		rogueButton.setText("Rogue");
		rogueButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DeckSelectWindow.showDeckSelectWindow(display, "Rogue");
				shell.dispose();				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button shamanButton = new Button(shell, SWT.PUSH);
		shamanButton.setImage(new Image(display, ClassSelectWindow.class.getResourceAsStream("/resources/classes/shaman.png")));
//		shamanButton.setText("Shaman");
		shamanButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DeckSelectWindow.showDeckSelectWindow(display, "Shaman");
				shell.dispose();				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button warlockButton = new Button(shell, SWT.PUSH);
		warlockButton.setImage(new Image(display, ClassSelectWindow.class.getResourceAsStream("/resources/classes/warlock.png")));
//		warlockButton.setText("Warlock");
		warlockButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DeckSelectWindow.showDeckSelectWindow(display, "Warlock");
				shell.dispose();				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button warriorButton = new Button(shell, SWT.PUSH);
		warriorButton.setImage(new Image(display, ClassSelectWindow.class.getResourceAsStream("/resources/classes/warrior.png")));
//		warriorButton.setText("Warrior");
		warriorButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DeckSelectWindow.showDeckSelectWindow(display, "Warrior");
				shell.dispose();				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		GridData data = new GridData();
		data.verticalAlignment = GridData.FILL;
		data.horizontalAlignment = GridData.FILL;
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		druidButton.setLayoutData(data);
		hunterButton.setLayoutData(data);
		mageButton.setLayoutData(data);
		paladinButton.setLayoutData(data);
		priestButton.setLayoutData(data);
		rogueButton.setLayoutData(data);
		shamanButton.setLayoutData(data);
		warlockButton.setLayoutData(data);
		warriorButton.setLayoutData(data);
		druidButton.pack();
		hunterButton.pack();
		mageButton.pack();
		paladinButton.pack();
		priestButton.pack();
		rogueButton.pack();
		shamanButton.pack();
		warlockButton.pack();
		warriorButton.pack();
//		ArrayList<File> classes = new ArrayList<>(Arrays.asList(f.listFiles()));
//		for(File playerClassFile : classes){
//			String playerClass = playerClassFile.getName();
//			Button b = new Button(shell, SWT.PUSH);
//			b.setText(playerClass);
//			b.addSelectionListener(new SelectionListener() {
//				
//				@Override
//				public void widgetSelected(SelectionEvent arg0) {
//					DeckSelectWindow.showDeckSelectWindow(display, playerClass);
//					shell.dispose();
//				}
//				
//				@Override
//				public void widgetDefaultSelected(SelectionEvent arg0) {
//					//empty
//				}
//			});
//			b.pack();
//		}
		shell.pack();
		shell.open();
	}
}
