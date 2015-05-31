package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import model.Element;
import model.Menge;
import model.Relation;
import model.Sense;




public class GUI2 implements ActionListener, TreeSelectionListener {
	
	enum SelectedType {
		ROOT,
		MENGE,
		ELEMENT,
		RELATION
	}
	
	private SelectedType selectedType = SelectedType.ROOT;
	private DefaultMutableTreeNode root = new DefaultMutableTreeNode("Mengen");
	private DefaultMutableTreeNode selectedNodeLeft = root;
	private DefaultMutableTreeNode selectedNodeRight = root;
	
	private JFrame frame = new JFrame("Prozesse");

	private JTree treeLeft = new JTree();
	private JTree treeRight = new JTree();
	
	private JScrollPane paneLeft = new JScrollPane(treeLeft, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JScrollPane paneRight = new JScrollPane(treeRight, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	private JTextField textfieldLeft = new JTextField();
	private JTextField textfieldRight = new JTextField();

	private JButton btnRelation = new JButton("Erstelle Menge");
	private JButton btnGenerateMenge = new JButton("neue Menge");

	

	private DefaultTreeModel treeModel = new DefaultTreeModel(root);

	public GUI2(Collection<Menge> mengen) {
		
		createTree(mengen);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 500, 500);
		frame.setLayout(new GridBagLayout());
		
		

		// JToolBar toolBar = new JToolBar();
		// //frame.add(toolBar, BorderLayout.NORTH);
		//
		// JButton btnNeuemenge = new JButton("neueMenge");
		// toolBar.add(btnNeuemenge);
		//
		// JButton btnNeuerelation = new JButton("neueRelation");
		// toolBar.add(btnNeuerelation);
		//
		// JButton btnNeueselement = new JButton("neuesElement");
		// toolBar.add(btnNeueselement);
		

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1;
		c.weightx = 0.5;
		
		treeLeft.setModel(treeModel);
		treeLeft.setBounds(10, 10, 200, 300);
		treeLeft.addTreeSelectionListener(this);
		c.gridx = 0;
		c.gridy = 0;
		frame.add(paneLeft, c);

		treeRight.setModel(treeModel);
		treeRight.setBounds(220, 10, 200, 300);
		treeRight.addTreeSelectionListener(this);
		c.gridx = 1;
		c.gridy = 0;
		frame.add(paneRight, c);

		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		
		textfieldLeft.setBounds(10, 320, 200, 25);
		textfieldLeft.setEditable(false);
		textfieldLeft.setPreferredSize(new Dimension(100, 25));
		c.gridx = 0;
		c.gridy = 1;
		frame.add(textfieldLeft, c);

		textfieldRight.setBounds(220, 320, 200, 25);
		textfieldRight.setEditable(false);
		textfieldRight.setPreferredSize(new Dimension(100, 25));
		c.gridx = 1;
		c.gridy = 1;
		frame.add(textfieldRight, c);

		btnRelation.setBounds(10, 355, 100, 25);
		btnRelation.addActionListener(this);
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 2;
		frame.add(btnRelation, c);


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

		if (e.getSource().equals(btnGenerateMenge)) {
			// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// frame.setBounds(100, 100, 500, 500);
			// frame.setLayout(new GridLayout(2, 2));
			// frame.setVisible(true);
			// new GUI2(mengen);

		}

		if (e.getSource().equals(btnRelation)) {
			TreeSelectionModel leftSelection = treeLeft.getSelectionModel();
			TreeSelectionModel rightSelection = treeRight.getSelectionModel();

			TreePath leftPath = leftSelection.getSelectionPath();
			TreePath rightPath = rightSelection.getSelectionPath();
			
			
			
			
			switch (selectedType) {
			case ROOT:
				String name = JOptionPane.showInputDialog("Menge erstellen");
				addNode(new Menge(new Sense(name)), selectedNodeLeft);
				break;
			case MENGE:
				name = JOptionPane.showInputDialog("Element erstellen");
				addNode(new Element(new Sense(name)), selectedNodeLeft);
				break;
			case ELEMENT:
				if (selectedNodeRight.getUserObject() instanceof Element) {
					Element leftElement = (Element)selectedNodeLeft.getUserObject();
					Element rightElement = (Element)selectedNodeRight.getUserObject();
					
					Relation relation = new Relation(new Sense("test"), leftElement, rightElement);
					
					name = JOptionPane.showInputDialog("Relation erstellen");
					
					if (leftElement.equals(rightElement)) {
						addNode(relation, selectedNodeLeft);
					} else {
						addNode(relation, selectedNodeLeft);
						addNode(relation, selectedNodeRight);
					}
				}
				break;
			case RELATION:
				
				break;
			default:
				break;
			}
			

//			if (leftPath != null && rightPath != null) {
//
//				Object[] leftObjects = leftPath.getPath();
//				Object[] rightObjects = rightPath.getPath();
//
//				DefaultMutableTreeNode leftSel = (DefaultMutableTreeNode) leftObjects[leftObjects.length - 1];
//				DefaultMutableTreeNode rightSel = (DefaultMutableTreeNode) rightObjects[rightObjects.length - 1];
//
//				
//
//				if (leftSel.getUserObject() instanceof Element
//					&& rightSel.getUserObject() instanceof Element) {
//					
//					Element leftElement = (Element)leftSel.getUserObject();
//					Element rightElement = (Element)rightSel.getUserObject();
//
//					Relation relation = new Relation(new Sense("test"), leftElement, rightElement);
//
//					DefaultMutableTreeNode nodeRelation0 = new DefaultMutableTreeNode(relation);
//					DefaultMutableTreeNode nodeRelation1 = new DefaultMutableTreeNode(relation);
//
//					if (leftElement.equals(rightElement)) {
//						treeModel.insertNodeInto(nodeRelation0, leftSel, 0);
//					} else {
//						treeModel.insertNodeInto(nodeRelation0, leftSel, 0);
//						treeModel.insertNodeInto(nodeRelation1, rightSel, 0);
//					}
//
//					treeModel.nodeChanged(leftSel);
//					treeModel.nodeChanged(rightSel);
//
//					System.out.println("relation added: " + relation.toString());
//				}
//
//			}
		}

	}
	
	private void addNode(Object newObj, MutableTreeNode parent) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newObj);
		treeModel.insertNodeInto(newNode, parent, 0);
		treeModel.nodeChanged(parent);
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
		Object selection = path[path.length - 1];

		if (e.getSource().equals(treeLeft)) {
			textfieldLeft.setText(selection.toString());
			Object leftSel = ((DefaultMutableTreeNode)selection).getUserObject();
			
			if (leftSel instanceof Menge) {
				// menge selected
				selectedType = SelectedType.MENGE;
				btnRelation.setText("Erstelle Element");
			} else if (leftSel instanceof Element) {
				// element selected
				selectedType = SelectedType.ELEMENT;
				btnRelation.setText("Erstelle Relation");
			} else if (leftSel instanceof Relation) {
				// relation selected
				selectedType = SelectedType.RELATION;
				btnRelation.setText("?");
			} else {
				// root selected
				selectedType = SelectedType.ROOT;
				btnRelation.setText("Erstelle Menge");
			}
			
			selectedNodeLeft = (DefaultMutableTreeNode)selection;
		} else if (e.getSource().equals(treeRight)) {
			textfieldRight.setText(selection.toString());
			selectedNodeRight = (DefaultMutableTreeNode)selection;
		}
		
		
	}

}
