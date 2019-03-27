package sdd.AJ.painterBSP.BSPLib;

import sdd.AJ.painterBSP.util.Eye;
import sdd.AJ.painterBSP.graphics.MyColor;
import sdd.AJ.painterBSP.BSPLib.Painter;

public abstract class Painter
{ // TODO completer l'interface

    //public abstract void scanConvert(double x, double y, Eye eye);

    public abstract void draw(double start, double finish, MyColor color);

    public void paint(BSPTree tree, Eye eye)
    {
        if (!tree.isEmpty())
        {
            if (tree.isLeaf())
            {
                eye.drawList(tree.getList(), this);
            }

            else if (tree.getEquation().solve(eye.getX(), eye.getY()) > 1e10)
            {
                paint(tree.getLeft(), eye);
                eye.drawList(tree.getList(), this);
                paint(tree.getRight(), eye);
            }

            else if (tree.getEquation().solve(eye.getX(), eye.getY()) < -1e10)
            {
                paint(tree.getRight(), eye);
                eye.drawList(tree.getList(), this);
                paint(tree.getLeft(), eye);
            }

            else
            {
                paint(tree.getRight(), eye);
                paint(tree.getLeft(), eye);
            }
        }
    }
}
