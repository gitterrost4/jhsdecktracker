package hsdecktracker;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

public class Test {
	public static void main(String[] args){
        Display display = new Display();
		Shell testShell = new Shell(display);
		FillLayout testLayout = new FillLayout();
		testShell.setLayout(testLayout);
		for(int i = 1; i<20; i++){
			Label label = new Label(testShell, SWT.BORDER);
			label.setText("HELLO"+i);
			label.pack();
		}
		testShell.open();
		Thread updateThread = new Thread(new Runnable(){
			public void run(){
				try {Thread.sleep(5000);} catch (Exception e){};
				Display.getDefault().syncExec(new Runnable(){
					public void run(){
						for(Control control : testShell.getChildren()){
							control.dispose();
						}
						for(int i = 1; i<20; i++){
							Label label = new Label(testShell, SWT.BORDER);
							label.setText("HELLO"+i+i);
							label.pack();
						}
						testShell.layout(true);
						
					}
				});
			}
		});
		updateThread.start();
        while (!testShell.isDisposed()) {
        	if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
	}
}
