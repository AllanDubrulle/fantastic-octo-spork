package sdd.AJ.painterBSP.BSPLib;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import sdd.AJ.painterBSP.BSPLib.Heuristic.Heuristic;
import sdd.AJ.painterBSP.BSPLib.Heuristic.LinearHeuristic;
import sdd.AJ.painterBSP.util.Equation;
import sdd.AJ.painterBSP.util.Segment;
import java.util.Collections;

public class BSPTree
{ // TODO Eliminer les methodes inutilisees
    private List<Segment> list;
    private BSPTree left, right;
    private Equation equation;

    // constructeurs
    public BSPTree(List<Segment> list, Heuristic heuristic)
    {
        // Algorithme de création du BSP
        // Si la liste est vide, on crée un arbre
        this.list = new ArrayList<>();
        left = null;
        right = null;
        equation = null;
        if (!list.isEmpty())
        {
            Segment pivot = heuristic.selectSegment(list);
            list.remove(pivot);
            equation = pivot.lineEquation();
            List<Segment> leftList = new ArrayList<>();
            List<Segment> rightList = new ArrayList<>();
            double d = equation.solve(pivot.x1, pivot.x2);
            double eps = 10e-9;
            this.list.add(pivot);
            for (Segment s : list)
            {
                if (Math.abs(equation.solve(s.x1, s.x2) - d) < eps)
                {
                    // On teste si le segment est inclus dans la droite
                    if (Math.abs(equation.solve(s.y1, s.y2) - d) < eps)
                    {
                        this.list.add(s);
                    }
                    // teste si le segment est inclus dans le plan positif
                    else if (equation.solve(s.y1, s.y2) > d)
                    {
                        rightList.add(s);
                    } else
                    {
                        leftList.add(s);
                    }
                }

                else if (Math.abs(equation.solve(s.y1, s.y2) - d) < eps)
                {
                    if (equation.solve(s.x1, s.x2) > d)
                    {
                        rightList.add(s);
                    } else
                    {
                        leftList.add(s);
                    }
                }

                else if (equation.solve(s.x1, s.x2) > d)
                {
                    if (equation.solve(s.y1, s.y2) > d)
                    {
                        rightList.add(s);
                    } else
                    {
                        Segment[] brokenS = s.breakSegment(equation, d);
                        rightList.add(brokenS[0]);
                        leftList.add(brokenS[1]);
                    }
                }

                else if (equation.solve(s.x1, s.x2) < d)
                {
                    if (equation.solve(s.y1, s.y2) < d)
                    {
                        leftList.add(s);
                    }

                    else
                    {
                        Segment[] brokenS = s.breakSegment(equation, d);
                        rightList.add(brokenS[1]);
                        leftList.add(brokenS[0]);
                    }
                }
            }
            left = new BSPTree(leftList, heuristic);
            right = new BSPTree(rightList, heuristic);
        }
    }

    public static BSPTree RandomBSPTree(List<Segment> list)
    {
        Collections.shuffle(list);
        return new BSPTree(list, new LinearHeuristic());
    }

    public BSPTree(List<Segment> list, BSPTree l, BSPTree r, Equation equation)
    {
        this.list = list;
        left = l;
        right = r;
        this.setEquation(equation);
    }

    public BSPTree()
    {
        this(null, null, null, null);
    }

    // get
    public List<Segment> getList()
    {
        return list;
    }

    public BSPTree getLeft()
    {
        return left;
    }

    public BSPTree getRight()
    {
        return right;
    }

    // set
    public void setData(List<Segment> list)
    {
        this.list = list;
    }

    public void setLeft(BSPTree l)
    {
        left = l;
    }

    public void setRight(BSPTree r)
    {
        right = r;
    }

    public Equation getEquation()
    {
        return equation;
    }

    public void setEquation(Equation equation)
    {
        this.equation = equation;
    }

    // test de l'arbre vide, c'est-a-dire du "noeud vide"
    public boolean isEmpty()
    {
        if (list == null && left == null && right == null)
            return true;
        else
            return false;
    }

    // remplit un noeud vide avec la donnee d et 2 sous-arbres vides
    private void insertEmpty(Segment s)
    {
        list = new ArrayList<Segment>(); // TODO Choisir le type final de liste
        list.add(s);
        left = new BSPTree();
        right = new BSPTree();
    }

    public void insertSegment(Segment s)
    {
        if (this.isEmpty())
        {
            this.insertEmpty(s);
        } else
            list.add(s);
    }

    public boolean isLeaf()
    {
        return left == null && right == null;
    }

    // calcul de la hauteur
    public int height()
    {
        if (isEmpty())
            return 0;
        else
            return 1 + Math.max(left.height(), right.height());
    }

    public int size()
    {
        if (isEmpty())
            return 0;
        else
            return 1 + left.size() + right.size();
    }
}
