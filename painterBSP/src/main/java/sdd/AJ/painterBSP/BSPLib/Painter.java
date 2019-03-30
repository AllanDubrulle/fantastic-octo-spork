package sdd.AJ.painterBSP.BSPLib;

import sdd.AJ.painterBSP.util.Eye;
import sdd.AJ.painterBSP.graphics.MyColor;
import sdd.AJ.painterBSP.BSPLib.Painter;

public interface Painter
{
    /**
     * Given a starting point and an ending point in the interval
     * [0, 1], paints the same proportion in the given colour.
     * @param start percentage at which the painting starts
     * @param end   percentage at which the painting stops
     * @param color the colour to be used for painting
     */
    public void draw(double start, double finish, MyColor color);

}
