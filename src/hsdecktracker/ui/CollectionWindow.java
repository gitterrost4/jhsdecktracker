package hsdecktracker.ui;

import java.io.*;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.json.JSONObject;

import hsdecktracker.*;

public class CollectionWindow {
	private static Shell shell = null;
	private static CardCollection collection = null;
	private static int currentPage = 0;
	private static PlayerClass playerClass = PlayerClass.Druid;
	public static void showCollectionWindow(Display display){
		if(shell!=null){
			return;
		}
		shell = new Shell(display);
		
		shell.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				shell = null;
			}
		});
		
		FormLayout shellLayout = new FormLayout();
		shellLayout.marginHeight = 5;
		shellLayout.marginWidth = 5;
		shell.setLayout(shellLayout);
		
		
		String jsonString=null;
		if(Configuration.getConfig().containsKey("deckDir")){
			
			File collectionFile = new File(Configuration.getConfig().getProperty("deckDir"));
			if(collectionFile.exists()){
				try{
					FileInputStream collectionFileStream = new FileInputStream(collectionFile);
					StringBuilder sb = new StringBuilder();
					BufferedReader br = new BufferedReader(new InputStreamReader(collectionFileStream));
					String line;
					while((line = br.readLine())!=null){
						sb.append(line);
					}
					br.close();
					jsonString = sb.toString();
				} catch (IOException e){
					
				}
			}
		}

		if(jsonString == null){
			collection = new CardCollection();
		} else {
			JSONObject jo = new JSONObject(jsonString);
			collection = new CardCollection(jo);
		}
		
		Composite cardComposite = new Composite(shell, SWT.BORDER);
		

		GridLayout layout = new GridLayout();
		layout.numColumns = 4;
		layout.makeColumnsEqualWidth = true;
		cardComposite.setLayout(layout);
		
		Button prevButton = new Button(shell, SWT.PUSH);
		prevButton.setText("<<");
		prevButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(currentPage>0){
					currentPage--;
				}
				prevButton.setEnabled(currentPage != 0);
				showCardsForClass(display, cardComposite, playerClass, currentPage);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// empty
			}
		});
		
		Button nextButton = new Button(shell, SWT.PUSH);
		nextButton.setText(">>");
		nextButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(currentPage<99){
					currentPage++;
				}
				prevButton.setEnabled(currentPage != 0);
				showCardsForClass(display, cardComposite, playerClass, currentPage);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// empty
			}
		});
		
		showCardsForClass(display, cardComposite, playerClass, currentPage);
		
		Button exitButton = new Button(shell, SWT.PUSH);
		exitButton.setText("Finish");
		
		FormData data = new FormData();
		data.left = new FormAttachment(prevButton,0);
		data.right = new FormAttachment(nextButton, -5);
		data.top = new FormAttachment(0,0);
		data.bottom = new FormAttachment(100,0);
		cardComposite.setLayoutData(data);
		
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.top = new FormAttachment(cardComposite, 0, SWT.TOP);
		data.bottom = new FormAttachment(cardComposite, 0, SWT.BOTTOM);
		prevButton.setLayoutData(data);
		
		data = new FormData();
		data.right = new FormAttachment(exitButton, -5);
		data.top = new FormAttachment(cardComposite, 0, SWT.TOP);
		data.bottom = new FormAttachment(cardComposite, 0, SWT.BOTTOM);
		nextButton.setLayoutData(data);
		
		
		
		data = new FormData();
		data.top = new FormAttachment(0,0);
		data.right = new FormAttachment(100,0);
		exitButton.setLayoutData(data);
		
		shell.pack();
		shell.open();
	}
	
	private static void showCardsForClass(Display display, Composite cardComposite, PlayerClass playerClass, int page){
		if(cardComposite == null || collection == null){
			return;
		}
		
		List<DeckCardEntry> dceList = collection.getCardsForClass(playerClass);
		
		Collections.sort(dceList, new DeckCardEntry.DCECostComparator());
		
		//clear the old composite contents
		
		for(Control comp:cardComposite.getChildren()){
			comp.dispose();
		}
		
		for(int i = page*8; i<(page+1)*8 && i<dceList.size(); i++){
			DeckCardEntry dce = dceList.get(i);
			Button cardButton = new Button(cardComposite, SWT.PUSH);
			System.err.println(dce.getCard().getFileName());
			InputStream cardStream = CollectionWindow.class.getResourceAsStream("/resources/fullcards/"+dce.getCard().getFileName()+".png");
			if(cardStream != null){
				cardButton.setImage(new Image(display, cardStream));
				cardButton.setText(String.valueOf(dce.getAmount()));
			} else {
				cardButton.setText(dce.getCard().getName()+" - "+dce.getAmount());
			}
			cardButton.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					collection.addCardWithWrap(dce.getCard());
					cardButton.setText(String.valueOf(dce.getAmount()));
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			cardButton.pack();
		}
		cardComposite.layout();
				
	}
}
