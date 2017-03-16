package gui.textWindow;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;


public class ButtonTabComponent extends JPanel{
	final TabbedFields tf;
	
	public ButtonTabComponent(final TabbedFields p){
		 super(new FlowLayout(FlowLayout.LEFT, 0, 0));
	        if (p == null) {
	            throw new NullPointerException("TabbedPane is null");
	        }
	        this.tf = p;
	        setOpaque(false);

	        JLabel label = new JLabel() {
	            public String getText() {
	                int i = tf.indexOfTabComponent(ButtonTabComponent.this);
	                if (i != -1) {
	                    return tf.getTitleAt(i);
	                }
	                return null;
	            }
	        };
	         
	        add(label);
	        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
	        JButton button = new TabButton(tf);
	        add(button);
	        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
	}

	private class TabButton extends JButton implements ActionListener{
		
		private TabbedFields tabField;
		
		public TabButton(TabbedFields t){
			tabField = t;
			setPreferredSize(new Dimension(15,15));
			setToolTipText("Close");
			
			setUI(new BasicButtonUI());
			setContentAreaFilled(false);
			setFocusable(false);
			
			addMouseListener(buttonMouseListener);
			setRolloverEnabled(true);
			addActionListener(this);	
		}
	
		@Override
		public void actionPerformed(ActionEvent e) {
			tabField.closeTab(tabField.indexOfTabComponent(ButtonTabComponent.this));
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g.create();
			
			if(getModel().isPressed()){
				g2.translate(1, 1);
			}
			
			g2.setStroke(new BasicStroke(2));
			g2.setColor(Color.BLACK);
			
			if(getModel().isRollover())
				g2.setColor(Color.RED);
			
			int del = 10;
			g2.drawLine(del,del,getWidth()-del-1,getHeight()-del-1);
			g2.drawLine(getWidth()-del-1,del,del,getHeight()-del-1);
			g2.dispose();
		}
	}

	private final static MouseListener buttonMouseListener = new MouseAdapter(){
		public void mouseEntered(MouseEvent e){
			Component c = e.getComponent();			
				if(c instanceof AbstractButton){
					AbstractButton b = (AbstractButton)c;
					b.setBorderPainted(true);
				}
		}
		
		public void mouseExited(MouseEvent e){
			Component c = e.getComponent();			
			if(c instanceof AbstractButton){
				AbstractButton b = (AbstractButton)c;
				b.setBorderPainted(false);
			}
		}
	};

}
