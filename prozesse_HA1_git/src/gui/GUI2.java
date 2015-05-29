package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class GUI2 implements ActionListener {
	
	private JFrame frame = new JFrame("Prozesse");
	
	private JTree treeLeft = new JTree();
	private JTree treeRight = new JTree();
	
	private JButton button = new JButton("Relation!");
	
	private DefaultMutableTreeNode root = new DefaultMutableTreeNode("Mengen");
	
	private DefaultMutableTreeNode tools = new DefaultMutableTreeNode("Werkzeuge");
	private DefaultMutableTreeNode fruits = new DefaultMutableTreeNode("Früchte");
	
	private DefaultMutableTreeNode tool0 = new DefaultMutableTreeNode("Schraubenzieher");
	
	private DefaultMutableTreeNode fruit0 = new DefaultMutableTreeNode("Birne");
	private DefaultMutableTreeNode fruit1 = new DefaultMutableTreeNode("Apfel");
	
	private DefaultTreeModel treeModel = new DefaultTreeModel(root);
	
	
	public GUI2() {
		treeModel.insertNodeInto(tools, root, 0);
		treeModel.insertNodeInto(fruits, root, 0);
		
		treeModel.insertNodeInto(tool0, tools, 0);
		
		treeModel.insertNodeInto(fruit0, fruits, 0);
		treeModel.insertNodeInto(fruit1, fruits, 0);
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 500, 500);
		frame.setLayout(null);
		

		treeLeft.setModel(treeModel);
		treeLeft.setBounds(10, 10, 200, 300);
		
		treeRight.setModel(treeModel);
		treeRight.setBounds(220, 10, 200, 300);
		
		button.setBounds(10, 320, 100, 25);
		button.addActionListener(this);
		
		frame.add(treeLeft);
		frame.add(treeRight);
		frame.add(button);
		
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new GUI2();		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(button)) {
			TreeSelectionModel leftSelection = treeLeft.getSelectionModel();
			TreeSelectionModel rightSelection = treeRight.getSelectionModel();
			
			TreePath leftPath = leftSelection.getSelectionPath();
			TreePath rightPath = rightSelection.getSelectionPath();
			
			Object[] leftObjects = leftPath.getPath();
			Object[] rightObjects = rightPath.getPath();
			
			System.out.println(leftObjects[leftObjects.length-1] + "    " + rightObjects[rightObjects.length-1]);
		}
		
	}
	
	

}

















