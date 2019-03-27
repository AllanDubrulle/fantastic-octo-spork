package sdd.AJ.painterBSP.BSPLib;

import sdd.AJ.painterBSP.util.Eye;
import sdd.AJ.painterBSP.graphics.MyColor;


public abstract class Painter
{ // TODO completer l'interface

    public abstract void scanConvert(double x, double y, Eye eye);

    public abstract void paint(double start, double finish, MyColor color);

    public void paint(BSPTree tree, double x, double y, Eye eye)
    {
        if (!tree.isEmpty())
        {
            if (tree.isLeaf())
            {
                scanConvert(x, y, eye);
            } else if (tree.getEquation().solve(x, y) > 1e10)
            {
                paint(tree.getLeft(), x, y, eye);
                scanConvert(x, y, eye);
                paint(tree.getRight(), x, y, eye);
            }

            else if (tree.getEquation().solve(x, y) < -1e10)
            {
                paint(tree.getRight(), x, y, eye);
                scanConvert(x, y, eye);
                paint(tree.getLeft(), x, y, eye);
            }

            else
            {
                paint(tree.getRight(), x, y, eye);
                paint(tree.getLeft(), x, y, eye);
            }
        }
    }
}
