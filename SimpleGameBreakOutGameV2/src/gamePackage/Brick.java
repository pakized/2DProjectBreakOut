package gamePackage;

import java.awt.Rectangle;

public class Brick {
	
	boolean isDestroyed;
	int points;
	int posX;
	int posY;
	int w = 50;
	int h = 15;
	
	public Brick(int points, int x, int y)
	{
		this.points = points;
		posX = x;
		posY = y;
		isDestroyed = false;
	}
	
    Rectangle getRect() {
        return new Rectangle(posX, posY, w, h);
    }
}
