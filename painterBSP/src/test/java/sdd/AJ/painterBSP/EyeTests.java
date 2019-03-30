package sdd.AJ.painterBSP;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.Assert;

import sdd.AJ.painterBSP.util.Eye;

public class EyeTests
{
    @Test
    public void visibilityTest00right()
    {
        // Everytime, we test both symetrical cases
        Eye eye = new Eye(0, 0, 0);
        Assert.assertEquals(Eye.INTERSECTS,
                     eye.isVisible(2, 0, 3, 0));
        Assert.assertEquals(Eye.INTERSECTS,
                     eye.isVisible(1, 1, 3, 0));
        Assert.assertEquals(Eye.COVERS,
                     eye.isVisible(1, 2, 1, -2));
        Assert.assertEquals(Eye.COVERS,
                            eye.isVisible(1, -2, 1, 2));
        Assert.assertEquals(Eye.OUT_OF_VIEW,
                            eye.isVisible(0, 1, -1, 3));
        Assert.assertEquals(Eye.OUT_OF_VIEW,
                            eye.isVisible(-1, 3, 0, 1));
    }

    @Test
    public void decenteredTest00()
    {
        Eye eye;
        for (int i = 0; i < 200; i++)
        {
            double offsetX = i * 50.;
            double offsetY = i * 100.;
            eye = new Eye(offsetX, offsetY, 0);
            Assert.assertEquals(Eye.INTERSECTS,
                                eye.isVisible(offsetX + 2,
                                              offsetY,
                                              offsetX + 3,
                                              offsetY));
            Assert.assertEquals(Eye.INTERSECTS,
                                eye.isVisible(offsetX + 1,
                                              offsetY + 1,
                                              offsetX + 3,
                                              offsetY));
            Assert.assertEquals(Eye.COVERS,
                                eye.isVisible(offsetX + 1,
                                              offsetY + 2,
                                              offsetX + 1,
                                              offsetY - 2));
            Assert.assertEquals(Eye.COVERS,
                                eye.isVisible(offsetX + 1,
                                              offsetY - 2,
                                              offsetX + 1,
                                              offsetY + 2));
            Assert.assertEquals(Eye.OUT_OF_VIEW,
                                eye.isVisible(offsetX,
                                              offsetY + 1,
                                              offsetX - 1,
                                              offsetY + 3));
            Assert.assertEquals(Eye.OUT_OF_VIEW,
                                eye.isVisible(offsetX - 1,
                                              offsetY + 3,
                                              offsetX,
                                              offsetY + 1));
        }
    }

    @Test
    public void farFromViewPointTest()
    {
        Eye eye = new Eye(-2000, -2000, 0);
        Assert.assertEquals(Eye.INTERSECTS,
                            eye.isVisible(1999, 1998, 0, 2000));
        Assert.assertEquals(Eye.OUT_OF_VIEW,
                            eye.isVisible(500, 620, 0, 2000));
        Assert.assertEquals(Eye.COVERS,
                            eye.isVisible(3000, 3001, -3000, -3001));

    }

    @Test
    public void visibilityTest00left()
    {
        Eye eye = new Eye(0, 0, Math.PI);
        Assert.assertEquals(Eye.INTERSECTS,
                     eye.isVisible(-2, 0, -3, 0));
        Assert.assertEquals(Eye.INTERSECTS,
                     eye.isVisible(-1, 1, -3, 0));
        Assert.assertEquals(Eye.COVERS,
                     eye.isVisible(-1, 2, -1, -2));
        Assert.assertEquals(Eye.COVERS,
                            eye.isVisible(-1, -2, -1, 2));
        Assert.assertEquals(Eye.OUT_OF_VIEW,
                            eye.isVisible(0, 1, 1, 3));
        Assert.assertEquals(Eye.OUT_OF_VIEW,
                            eye.isVisible(1, 3, 0, 1));
    }


}
