package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
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
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import model.Element;
import model.Menge;
import model.Relation;
import model.Sense;

public class GUI2 implements ActionListener, TreeSelectionListener {
	
	private JFrame frame = new JFrame("Prozesse");
	
	private JTree treeLeft = new JTree();
	private JTree treeRight = new JTree();
	
	private JTextField textfieldLeft = new JTextField();
	private JTextField textfieldRight = new JTextField();
	
	private JButton button = new JButton("Relation!");
	
	private DefaultMutableTreeNode root = new DefaultMutableTreeNode("Mengen");
	
	private DefaultTreeModel treeModel = new DefaultTreeModel(root);
	
	
	public GUI2(Collection<Menge> mengen) {

		createTree(mengen);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 500, 500);
		frame.setLayout(new GridLayout(3, 2));
		

		treeLeft.setModel(treeModel);
		treeLeft.setBounds(10, 10, 200, 300);
		treeLeft.addTreeSelectionListener(this);
		
		treeRight.setModel(treeModel);
		treeRight.setBounds(220, 10, 200, 300);
		treeRight.addTreeSelectionListener(this);
		
		textfieldLeft.setBounds(10, 320, 200, 25);
		textfieldLeft.setEditable(false);
		textfieldLeft.setPreferredSize(new Dimension(100, 25));
		
		textfieldRight.setBounds(220, 320, 200, 25);
		textfieldRight.setEditable(false);
		textfieldRight.setPreferredSize(new Dimension(100, 25));
		
		button.setBounds(10, 355, 100, 25);
		button.addActionListener(this);
		
		frame.add(treeLeft);
		frame.add(treeRight);
		frame.add(textfieldLeft);
		frame.add(textfieldRight);
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
			
			
			
			
			if (leftPath != null && rightPath != null) {
			
				Object[] leftObjects = leftPath.getPath();
				Object[] rightObjects = rightPath.getPath();
				
				DefaultMutableTreeNode leftSel = (DefaultMutableTreeNode)leftObjects[leftObjects.length-1];
				DefaultMutableTreeNode rightSel = (DefaultMutableTreeNode)rightObjects[rightObjects.length-1];
				
				Element leftElement = (Element)leftSel.getUserObject();
				Element rightElement = (Element)rightSel.getUserObject();
				
				if (	leftElement instanceof Element
					 && rightElement instanceof Element) {
					
					
					
					
					Relation relation = new Relation(new Sense("test"), leftElement, rightElement);
					
					DefaultMutableTreeNode nodeRelation0 = new DefaultMutableTreeNode(relation);
					DefaultMutableTreeNode nodeRelation1 = new DefaultMutableTreeNode(relation);
					
					if (leftElement.equals(rightElement)) {
						treeModel.insertNodeInto(nodeRelation0, leftSel, 0);
					} else {
						treeModel.insertNodeInto(nodeRelation0, leftSel, 0);
						treeModel.insertNodeInto(nodeRelation1, rightSel, 0);
					}
					
					treeModel.nodeChanged(leftSel);
					treeModel.nodeChanged(rightSel);
					
					System.out.println("relation added: " + relation.toString());
				}
				
			}
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

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		
		TreePath treePath = e.getPath();
		Object[] path = treePath.getPath();
		Object selection = path[path.length-1];
		
		if (e.getSource().equals(treeLeft)) {
			textfieldLeft.setText(selection.toString());
		} else if (e.getSource().equals(treeRight)) {
			textfieldRight.setText(selection.toString());
		}	
	}
	

}

















