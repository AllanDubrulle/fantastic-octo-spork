package sdd.AJ.painterBSP.BSPLIb;

import java.util.ArrayList;
import java.util.List;

import sdd.AJ.painterBSP.BSPLIb.Heuristic.Heuristic;
import sdd.AJ.painterBSP.util.Equation;
import sdd.AJ.painterBSP.util.Segment;

public class BSPTree { //TODO Eliminer les methodes inutilisees
	private List<Segment> list;
	private BSPTree left, right;
	private Equation equation;
//constructeurs
	public BSPTree(List<Segment> list, BSPTree l, BSPTree r,Equation equation) {
		this.list = list;
		left = l;
		right = r;
		this.setEquation(equation);
	}
	public BSPTree() {
		this(null,null,null,null);
	}
	
	public BSPTree(List<Segment> list, Heuristic h) { //TODO Implémenter algo travail preparatif
		
	}

//get 
	public List<Segment> getList() {
		return list;
	}
	public BSPTree getLeft() {
		return left;
	}
	public BSPTree getRight() {
		return right;
	}

//set
	public void setData(List<Segment> list) {
		this.list = list;
	}
	public void setLeft(BSPTree l) {
		left = l;
	}
	public void setRight(BSPTree r) {
		right = r;
	}

public Equation getEquation() {
		return equation;
	}
	public void setEquation(Equation equation) {
		this.equation = equation;
	}
	//test de l'arbre vide, c'est-a-dire du "noeud vide"
	public boolean isEmpty() {
		if (list == null && left == null && right == null) 
			return true;
		else
			return false;
	}
	
//remplit un noeud vide avec la donnee d et 2 sous-arbres vides
	private void insertEmpty(Segment s) {
		list = new ArrayList<Segment>(); //TODO Choisir le type final de liste
		list.add(s);
		left = new BSPTree();
		right = new BSPTree();
	}
	
	public void insertSegment(Segment s) {
		if (this.isEmpty()){
			this.insertEmpty(s);
		}
		else
			list.add(s);
	}
	
	public boolean isLeaf()
	{
		return left == null && right == null; 
	}
	
	
//calcul de la hauteur
	public int height() {
		if (isEmpty()) 
			return 0;
		else
			return 1 + Math.max(left.height(),right.height());
	}
	
	public int size() {
		if(isEmpty())
			return 0;
		else
			return 1+left.size()+right.size();
	}

}

