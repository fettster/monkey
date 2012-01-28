/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space_chimpanzees;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author christian
 */
public class Monkey
{
    int posX = 100;
    int posY = 100;
    int width = 100;
    int height = 125;
    Stroke stroke = new BasicStroke(.01f);
    AffineTransform identityTransform = new AffineTransform();
    Area monkeyArea;
    AffineTransform monkeyAreaTransform = new AffineTransform();
    Area monkeyLeftOutsideEyeArea;
    AffineTransform monkeyLeftOutsideTransform;
    Area monkeyRightOutsideEyeArea;
    AffineTransform monkeyRightOutsideTransform;
    Area monkeyRightInsideEyeArea;
    AffineTransform monkeyRightInsideTransform;
    Area monkeyLeftInsideEyeArea;
    AffineTransform monkeyLeftInsideTransform;
    Area monkeyLeftNoseArea;
    AffineTransform monkeyLeftTransform;
    Area monkeyRightNoseArea;
    AffineTransform monkeyRightTransform;
    Area monkeyMouthArea;
    AffineTransform monkeyMouthTransform;
    int monkeyX;
    int monkeyY;
    int deltaX;
    int deltaY;
    int speed;
    int direction;
    int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    AffineTransform monkeyTransform = new AffineTransform();

    public Monkey()
    {
        monkeyTransform.setToTranslation(-posX - width / 2, -posX - height / 2);
        monkeyArea = new Area(new Ellipse2D.Double(posX, posY, width, height));
        Area monkeyRightEar = new Area(new Ellipse2D.Double(posX - 25, posY + 13, width / 2, height / 2));
        monkeyArea.add(monkeyRightEar);
        Area monkeyLeftEar = new Area(new Ellipse2D.Double(posX + 75, posY + 13, width / 2, height / 2));
        monkeyArea.add(monkeyLeftEar);
        Area monkeyMouth = new Area(new Ellipse2D.Double(posX - 9, posY + 90, width * 1.2, height / 3));
        monkeyArea.add(monkeyMouth);
        monkeyArea.transform(monkeyTransform);

        monkeyLeftOutsideEyeArea = new Area(new Ellipse2D.Double(posX - 9, posY + 90, width / 5, height / 8.5));
        monkeyLeftOutsideTransform = new AffineTransform(monkeyTransform);
        monkeyLeftOutsideTransform.translate(0, -40);
        monkeyLeftOutsideEyeArea.transform(monkeyLeftOutsideTransform);

        monkeyRightOutsideEyeArea = new Area(new Ellipse2D.Double(posX - 9, posY + 90, width / 5, height / 8.5));
        monkeyRightOutsideTransform = new AffineTransform(monkeyTransform);
        monkeyRightOutsideTransform.translate(0, -40);
        monkeyRightOutsideEyeArea.transform(monkeyRightOutsideTransform);

        monkeyLeftInsideEyeArea = new Area(new Ellipse2D.Double(posX - 5, posY + 91, width / 9, height / 9));
        monkeyLeftInsideTransform = new AffineTransform(monkeyTransform);
        monkeyLeftInsideTransform.translate(0, -40);
        monkeyLeftInsideEyeArea.transform(monkeyLeftInsideTransform);

        monkeyRightInsideEyeArea = new Area(new Ellipse2D.Double(posX - 5, posY + 91, width / 9, height / 9));
        monkeyRightInsideTransform = new AffineTransform(monkeyTransform);
        monkeyRightInsideTransform.translate(0, -40);
        monkeyRightInsideEyeArea.transform(monkeyRightInsideTransform);

        monkeyLeftNoseArea = new Area(new Ellipse2D.Double(posX - 5, posY + 91, width / 10, height / 10));
        monkeyLeftTransform = new AffineTransform(monkeyTransform);
        monkeyLeftTransform.translate(0, -10);
        monkeyLeftNoseArea.transform(monkeyLeftTransform);

        monkeyRightNoseArea = new Area(new Ellipse2D.Double(posX - 5, posY + 91, width / 10, height / 10));
        monkeyRightTransform = new AffineTransform(monkeyTransform);
        monkeyRightTransform.translate(0, -10);
        monkeyRightNoseArea.transform(monkeyRightTransform);

        monkeyMouthArea = new Area(new Ellipse2D.Double(posX - 5, posY + 91, width, height / 20));
        monkeyMouthTransform = new AffineTransform(monkeyTransform);
        monkeyMouthTransform.translate(0, 17);
        monkeyMouthArea.transform(monkeyMouthTransform);

        monkeyX = 400;
        monkeyY = 100;
        speed = 0;
        direction = 0;

    }

    public void paintSelf(Graphics2D g2)
    {
//        g2.translate( monkeyX, monkeyY);
        g2.rotate(Math.toRadians(direction));
        g2.translate(monkeyX, monkeyY);

        g2.setStroke(stroke);
        g2.setColor(new Color(155, 108, 56));
        g2.fill(monkeyArea);
        g2.translate(25, 0);
        g2.setColor(Color.white);
        g2.fill(monkeyRightOutsideEyeArea);
        g2.setColor(Color.black);
        g2.fill(monkeyRightInsideEyeArea);
        g2.translate(47, 0);
        g2.setColor(Color.white);
        g2.fill(monkeyLeftOutsideEyeArea);
        g2.setColor(Color.black);
        g2.fill(monkeyLeftInsideEyeArea);
        g2.translate(-15, 0);
        g2.setColor(Color.black);
        g2.fill(monkeyLeftNoseArea);
        g2.translate(-15, 0);
        g2.setColor(Color.black);
        g2.fill(monkeyRightNoseArea);
        g2.translate(-33, 0);
        g2.setColor(Color.black);
        g2.fill(monkeyMouthArea);
        g2.setTransform(identityTransform);
    }

    public void moveSelf()
    {
        deltaX = (int) (speed * Math.sin(Math.toRadians(direction)));
        deltaY = (int) (-speed * Math.cos(Math.toRadians(direction)));
        monkeyX += deltaX;
        monkeyY += deltaY;
        if (monkeyX > screenWidth)
        {
            monkeyX -= screenWidth;
        }
        if (monkeyY > screenHeight)
        {
            monkeyY -= screenHeight;
        }
        if (monkeyX < 0)
        {
            monkeyX += screenWidth;
        }
        if (monkeyY < 0)
        {
            monkeyY += screenHeight;
        }
    }

    public void setMonkeySpeed(int newSpeed)
    {
        speed = newSpeed;
    }

    public int getMonkeySpeed()
    {
        return speed;
    }

    public void setMonkeyDirection(int newDirection)
    {
        direction = newDirection;
    }

    public int getMonkeyDirection()
    {
        return direction;
    }
}
