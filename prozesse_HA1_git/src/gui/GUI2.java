package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import model.Element;
import model.Menge;
import model.Relation;
import model.Sense;

public class GUI2 implements ActionListener {
	
	private JFrame frame = new JFrame("Prozesse");
	
	private JTree treeLeft = new JTree();
	private JTree treeRight = new JTree();
	
	private JTextField textfield = new JTextField();
	
	private JButton button = new JButton("Relation!");
	
	private DefaultMutableTreeNode root = new DefaultMutableTreeNode("Mengen");
	
	private DefaultTreeModel treeModel = new DefaultTreeModel(root);
	
	
	public GUI2(Collection<Menge> mengen) {

		createTree(mengen);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 500, 500);
		frame.setLayout(null);
		

		treeLeft.setModel(treeModel);
		treeLeft.setBounds(10, 10, 200, 300);
		
		treeRight.setModel(treeModel);
		treeRight.setBounds(220, 10, 200, 300);
		
		textfield.setBounds(10, 320, 400, 25);
		textfield.setEditable(false);
		
		button.setBounds(10, 355, 100, 25);
		button.addActionListener(this);
		
		frame.add(treeLeft);
		frame.add(treeRight);
		frame.add(textfield);
		frame.add(button);
		
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		ArrayList<Menge> mengen = new ArrayList<Menge>();
		
		
		
		Element tool0 = new Element(new Sense("Schraubenzieher"));
		Element tool1 = new Element(new Sense("Hammer"));
		Element tool2 = new Element(new Sense("Dings"));
		
		Element fruit0 = new Element(new Sense("Birne"));
		Element fruit1 = new Element(new Sense("Apfel"));
		
		new Relation(new Sense("rel0"), tool0, tool1);
		new Relation(new Sense("rel1"), tool2, fruit0);
		
		Menge tools = new Menge(new Sense("Werkzeuge"));
		tools.addElement(tool0);
		tools.addElement(tool1);
		tools.addElement(tool2);
		
		Menge fruits = new Menge(new Sense("Früchte"));
		fruits.addElement(fruit0);
		fruits.addElement(fruit1);
		
		
		
		mengen.add(tools);
		mengen.add(fruits);
		
		new GUI2(mengen);		
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
	
	
	
	private void createTree(Collection<Menge> mengen) {
		
		for (Menge menge : mengen) {
			DefaultMutableTreeNode nodeMenge = new DefaultMutableTreeNode(menge);
			treeModel.insertNodeInto(nodeMenge, root, 0);
			
			for (UUID uuid : menge.getElemente().keySet()) {
				Element element = menge.getElemente().get(uuid);
				
				DefaultMutableTreeNode nodeElement = new DefaultMutableTreeNode(element);
				treeModel.insertNodeInto(nodeElement, nodeMenge, 0);
				
				for (Relation relation : element.getRelations()) {
					DefaultMutableTreeNode nodeRelation = new DefaultMutableTreeNode(relation);
					treeModel.insertNodeInto(nodeRelation, nodeElement, 0);
				}
			}
		}
		
	}
	

}

















