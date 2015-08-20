package hsdecktracker.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import hsdecktracker.Configuration;

public class SettingsWindow {
	private static Shell shell = null;
	
	public static void showSettingsWindow(Display display){
			if(shell!=null){
				return;
			}
			System.err.println("Open window");
			shell = new Shell(display, SWT.APPLICATION_MODAL);
			FormLayout layout = new FormLayout();
			layout.marginHeight=5;
			layout.marginWidth=5;
			shell.setLayout(layout);
			
			Composite settingsComp = new Composite(shell,SWT.NORMAL);
			FormLayout compLayout = new FormLayout();
			compLayout.marginHeight = 0;
			compLayout.marginWidth = 0;
			settingsComp.setLayout(compLayout);
			
			Label baseDirLabel = new Label(settingsComp, SWT.NORMAL);
			baseDirLabel.setText("Base directory for decks");
			Text baseDirText = new Text(settingsComp, SWT.BORDER);
			if(Configuration.getConfig().getProperty("deckDir") != null){
				baseDirText.setText(Configuration.getConfig().getProperty("deckDir"));
			}
			Button chooseBaseDir = new Button(settingsComp, SWT.PUSH);
			chooseBaseDir.setText("Choose...");
			chooseBaseDir.addSelectionListener(new SelectionListener(){

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					//empty
				}

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					DirectoryDialog directoryDialog = new DirectoryDialog(shell);
					directoryDialog.setFilterPath(baseDirText.getText());
					directoryDialog.setMessage("Select the directory where the decks are saved");
					String dir = directoryDialog.open();
					if(dir != null){
						baseDirText.setText(dir);						
					}
				}
				
			});
			Label hearthstoneDirLabel = new Label(settingsComp, SWT.NORMAL);
			hearthstoneDirLabel.setText("Hearthstone Installation directory");
			Text hearthstoneDirText = new Text(settingsComp, SWT.BORDER);
			if(Configuration.getConfig().getProperty("hearthstoneDir") != null){
				hearthstoneDirText.setText(Configuration.getConfig().getProperty("hearthstoneDir"));				
			}
			Button chooseHearthstoneDir = new Button(settingsComp, SWT.PUSH);
			chooseHearthstoneDir.setText("Choose...");
			chooseHearthstoneDir.addSelectionListener(new SelectionListener(){

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					//empty
				}

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					DirectoryDialog directoryDialog = new DirectoryDialog(shell);
					directoryDialog.setFilterPath(baseDirText.getText());
					directoryDialog.setMessage("Select the installation directory of Hearthstone");
					String dir = directoryDialog.open();
					if(dir != null){
						hearthstoneDirText.setText(dir);
					}
				}
				
			});
			
			Button closeButton = new Button(shell, SWT.PUSH);
			closeButton.setText("Close");
			closeButton.pack();
			closeButton.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					shell.dispose();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			
			
			//Formatting the window
			FormData data = new FormData();
			data.top = new FormAttachment(baseDirText,0, SWT.CENTER);
			baseDirLabel.setLayoutData(data);
			data = new FormData();
			data.left = new FormAttachment(hearthstoneDirText, 0, SWT.LEFT);
			data.right = new FormAttachment(chooseBaseDir, -5);
			baseDirText.setLayoutData(data);
			data = new FormData();
			data.right = new FormAttachment(100,0);
			chooseBaseDir.setLayoutData(data);
			
			data = new FormData();
			data.top = new FormAttachment(hearthstoneDirText, 0, SWT.CENTER);
			hearthstoneDirLabel.setLayoutData(data);
			data = new FormData();
			data.top = new FormAttachment(baseDirText, 5);
			data.left = new FormAttachment(hearthstoneDirLabel, 5);
			data.right = new FormAttachment(chooseHearthstoneDir, -5);
			data.width = 600;
			hearthstoneDirText.setLayoutData(data);
			data = new FormData();
			data.top = new FormAttachment(chooseBaseDir, 5);
			data.right = new FormAttachment(100,0);
			chooseHearthstoneDir.setLayoutData(data);
			
			data = new FormData();
			data.top = new FormAttachment(0,0);
			data.left = new FormAttachment(0,0);
			data.right = new FormAttachment(100,0);
			data.bottom = new FormAttachment(closeButton, -5);
			settingsComp.setLayoutData(data);
			
			data = new FormData();
			data.bottom = new FormAttachment(100,0);
			data.right = new FormAttachment(100,0);
			closeButton.setLayoutData(data);
					
					
			
			shell.addDisposeListener(new DisposeListener() {
				
				@Override
				public void widgetDisposed(DisposeEvent arg0) {
					Configuration.getConfig().setProperty("deckDir", baseDirText.getText());
					Configuration.getConfig().setProperty("hearthstoneDir", hearthstoneDirText.getText());
					Configuration.storeConfig();
					shell = null;
				}
			});
			
			shell.pack();
			shell.open();
			
	}
}
