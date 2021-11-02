package gamePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Ball extends Rectangle{
	
	float posX;
	float posY;
	float moveX;
	float moveY;
	int radius;
	
	public Ball(float x, float y, int radius, float mx, float my) 
	{
		posX = x;
		posY = y;
		moveX = mx;
		moveY = my;
		this.radius = radius;
	}
	
	//add move every frame
	public void update() 
	{
		posX = posX + moveX;
		posY = posY+ moveY;
	}
	
	public void draw(Graphics g) 
	{
		g.setColor(Color.yellow);
		g.fillOval((int)posX, (int)posY, radius, radius);
	}
    Rectangle getRect() { 
        return new Rectangle((int)posX, (int)posY, radius, radius);
    }
    public Brick getBrickAt(ArrayList<Brick> bricks)
    {
    	for(int i = 0; i< bricks.size(); i++) 
    	{
    		if((int)this.posX == bricks.get(i).posX && (int)this.posY == bricks.get(i).posY) 
    		{
        		System.out.print("TEST");

        		//return new Rectangle((int)posX, (int)posY, bricks.get(i).w, bricks.get(i).h);		
    			return bricks.get(i);
    		}
    	}
    	return null;
    }
}
