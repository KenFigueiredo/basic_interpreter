package gui.explorer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import gui.Window;

public class FolderExplorer extends JPanel implements MouseListener {
	
	private JFileChooser fileBrowser;
	private Window gui;

	private JTree tree;
	private JLabel lab;
	private JScrollPane scroll;
	private DefaultTreeModel model;
	private DefaultMutableTreeNode root;
	private boolean isTree;
	
	public FolderExplorer(Window gui){
		this.gui = gui;		
		initialize();		
	}
	
	private void initialize(){
		isTree = false;
		initPanel();
		initFileBrowser();
		initTree();
	}
	
	private void initPanel(){
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(200, this.getPreferredSize().height));
		this.setPreferredSize(new Dimension(300, this.getPreferredSize().height));
		this.setVisible(true);
		
		lab = new JLabel("<html>No Directory selected.<br>Click here to open a directory</html>");
		this.add(lab, BorderLayout.NORTH);
		this.addMouseListener(this);
	}
	
	private void initFileBrowser(){
		fileBrowser = new JFileChooser();
		fileBrowser.setCurrentDirectory(new File("."));
		fileBrowser.setDialogTitle("Open Directory...");
		fileBrowser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}
	
	private void initTree(){
		tree = new JTree();
		tree.setCellRenderer(new CellRenderer());
		tree.addMouseListener(this);
			
		model = (DefaultTreeModel)tree.getModel();
		root = (DefaultMutableTreeNode) model.getRoot();
		root.removeAllChildren();
		tree.setShowsRootHandles(true);
	}
	
	public void reloadTree(File dir){
		
		if(dir != null){
			
			model.setRoot(addNodes(dir));
			
				if(!isTree){
					lab.setVisible(false);
					scroll = new JScrollPane();
					scroll.getViewport().add(tree);
					this.add(scroll,BorderLayout.CENTER);
					isTree = true;
				}

			model = (DefaultTreeModel)tree.getModel();
			root = (DefaultMutableTreeNode) model.getRoot();
			
			model.reload();
		}
		
		else{
			JLabel lab = new JLabel("No Directory selected. \n Click here to open a directory");
			this.add(lab, BorderLayout.CENTER);
		}
	}
	
	private DefaultMutableTreeNode addNodes(File dir){
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(dir);
		
		for(File f: dir.listFiles()){
			if(f.isDirectory())
				node.add(addNodes(f));
			else{
				String s = f.getName();
				if(s.contains(".")){
					s = s.substring(s.lastIndexOf('.'),s.length());
					
					if(gui.fileSupported(s))
						node.add(new DefaultMutableTreeNode(f));
				}

			}
		}
		
		return node;
	}
		
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(gui.getOpenDir() == null){
			int val = fileBrowser.showOpenDialog(this);
			
			if(val == JFileChooser.APPROVE_OPTION){
				gui.setOpenDir(fileBrowser.getSelectedFile());
			}	
		}
		
		else if(e.getClickCount() == 2){
			TreePath path = tree.getPathForLocation(e.getX(), e.getY());
			if(path != null){
				File f = new File(path.getLastPathComponent().toString());
				
				if(!f.isDirectory()){
					gui.openNewTab(f);
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {	
	}

	
 public class CellRenderer extends DefaultTreeCellRenderer {

        private FileSystemView fsv = FileSystemView.getFileSystemView();

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
           super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            if (value instanceof DefaultMutableTreeNode) {
                value = ((DefaultMutableTreeNode)value).getUserObject();
                if (value instanceof File) {
                    File file = (File) value;

                    if (file.isFile()) {
                        setIcon(fsv.getSystemIcon(file));
                        setText(file.getName());                       
                    } else {
                        setIcon(fsv.getSystemIcon(file));
                        setText(file.getName());
                    }
                }
            }
            return this;
        }
    }

}
