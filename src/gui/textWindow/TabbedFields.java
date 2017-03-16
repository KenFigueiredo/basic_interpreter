package gui.textWindow;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import gui.Window;

public class TabbedFields extends JTabbedPane{
	
	private Map<Integer, TextPane> tabMap;
	private Window gui;
	
	public TabbedFields(Window gui){
		this.gui = gui;
		initialize();
	}
	
	private void initialize(){
		this.setTabPlacement(TabbedFields.TOP);
		tabMap = new HashMap<Integer, TextPane>();
	}
	
		public void addNewTab(TextPane pane){
			String s = pane.getTitle();
			
			int index = this.indexOfTab(s);
			
			if(index == -1){
				this.addTab(s, pane);
				index = this.indexOfTab(s);
				this.setTabComponentAt(index, new ButtonTabComponent(this));
				tabMap.put(index, pane);
				gui.allowInterpret(true);

			}
			
			else{
				this.setSelectedIndex(index);
			}
			
		}
		
		public void closeTab(int tab){
			if(tab > -1){
				this.remove(tab);
				tabMap.remove(tab);
				
				if(this.getTabCount() <= 0){
					gui.allowInterpret(false);
				}
			}
		}
		
		
		public TextPane getSelectedTab(){
			return tabMap.get(this.getSelectedIndex());
		}
}
