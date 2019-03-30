package sdd.AJ.painterBSP.BSPLib;

import sdd.AJ.painterBSP.util.Eye;
import sdd.AJ.painterBSP.graphics.MyColor;
import sdd.AJ.painterBSP.BSPLib.Painter;

public abstract class Painter
{
    /**
     * Given a starting point and an ending point in the interval
     * [0, 1], paints the same proportion in the given colour.
     * @param start percentage at which the painting starts
     * @param end   percentage at which the painting stops
     * @param color the colour to be used for painting
     */
    public abstract void draw(double start, double finish, MyColor color);

    /**
     * Given a binary space partition tree and an eye,
     * applies the painter's algorithm to depict what is
     * seen by the eye.
     * @param tree the tree containing the scene to be processed
     * @param eye  the viewpoint from which the scene is to be processed
     */
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
