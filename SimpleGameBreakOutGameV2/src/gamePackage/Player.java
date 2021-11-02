package gamePackage;

import java.awt.Graphics;
import java.awt.Rectangle;


public class Player extends Rectangle{

	int points; 
	double xMove;
	double yMove;
	int width;
	int height;
	Game game;
	double speed = 2.5;
	
	public Player(int x, int y, int w, int h, Game g) 
	{
		game = g;
		xMove = x;
		yMove = y;
		width = w;
		height = h;
	}
	
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(gameAssets.Assets.racket, (int)xMove, (int)yMove, width, height,null);
	}
	
	public void update() {
		if(game.getKeyManager().left) {
			xMove = xMove-speed;
		}		
		if(game.getKeyManager().right) {
			xMove = xMove+speed;
		}
	}
    Rectangle getRect() {

        return new Rectangle((int)xMove, (int)yMove, width, height);
    }
}
