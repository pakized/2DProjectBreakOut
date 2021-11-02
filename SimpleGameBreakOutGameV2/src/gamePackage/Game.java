package gamePackage;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import com.sun.media.jfxmedia.events.PlayerStateEvent.PlayerState;

import gameAssets.Assets;

public class Game implements Runnable{

	private Display display;
	public int width, heigth;
	public String title;
	
	private Thread thread;
	private boolean running = false;
	Player player = new Player(300,300,80,15,this);
	float speed = 1.2f;
	Ball ball = new Ball(300,250, 15 ,speed,speed);
	
	private BufferStrategy bs;
	private Graphics g;
	
	private KeyManager keyManager;
	
	ArrayList<Brick> bricks = new ArrayList<Brick>();

	
	public Game(String title, int width, int height) 
	{
		this.width = width;
		this.heigth = height;
		this.title = title;
		keyManager = new KeyManager();
	}
	
	private void init() 
	{
		display = new Display(title, width, heigth);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
	}
	
	private void update() 
	{
		checkCollision();
		keyManager.update();
		player.update();
		ball.update();
	}
	
	private void render() 
	{
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) 
		{
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//clear
		g.clearRect(0,0, width, heigth);
		
		//Draw here 
	//evertything after this line = red

	/*	g.setColor(Color.red);
		g.fillRect(300, 50, 50, 70);
		g.setColor(Color.blue);
		g.fillRect(300, 200, 50, 70);*/
		
	//	g.drawImage(Assets.redBrick, 0,0 ,null);
		setWorld();
		player.render(g);
		ball.draw(g);
		//end drawing
		bs.show();
		g.dispose();
	}
	
	private void setWorld() 
	{
		int counter = 0; 
		for(int y = 0; y < heigth/2; y = y + 20) 
		{
			for(int x = 0; x < width; x = x + 60) 
			{
				if(counter == 0) 
				{
			        g.drawImage(Assets.redBrick, x, y,null);
			        bricks.add(new Brick(10, x, y));
				}
				if(counter == 1) 
				{
					g.drawImage(Assets.blueBrick, x, y,null);
			        bricks.add(new Brick(20, x, y));
				}
				if(counter == 2) 
				{
					g.drawImage(Assets.greenBrick, x, y,null);
			        bricks.add(new Brick(30, x, y));
				}
				counter++;
				if(counter == 3) 
				{
					counter = 0;
				}
			}
		}
	}
	
	public void checkCollision() 
	{
		ball.update();
		player.update();		
		
		//bounce from bricks
		if(ball.getBrickAt(bricks) != null)
		{
			System.out.println(" BRICK point(s): " + player.points);

			if(ball.getRect().intersects(ball.getBrickAt(bricks).getRect()))
			{	
				player.points = player.points+1;
				System.out.println(" BRICK point(s): " + player.points);
				ball.moveY = -ball.moveY;
			}
		}
		
		//bounce from player
		if(player.getRect().intersects(ball.getRect()))
		{	
			player.points = player.points+1;
			System.out.println("point(s): " + player.points);
			ball.moveY = -ball.moveY;
		}
		
		//stop at edge
		if(player.xMove <= 0) 
		{
			player.xMove=0;
		}
		//					window breite, paddle breite
		if(player.xMove >= (width-player.width)) 
		{
			player.xMove = width-player.width;
		}
		
		
		
		//bounce ball frame
		if(ball.posY <= 0 + ball.radius) 
		{
			ball.moveY = -ball.moveY;
			ball.update();
		}
		//			window h ball radius
		if(ball.posY >= heigth - ball.radius) 
		{
			//game lost
			System.out.println("You have achieved " + player.points + " points");
			player.points = 0;
			ball.moveY = -ball.moveY;
		}
		
		if(ball.posX <= 0 + ball.radius) 
		{
			ball.moveX = -ball.moveX;
			ball.update();

		}
		//			window h ball radius
		if(ball.posX >= width-ball.radius) 
		{
			ball.moveX = -ball.moveX;
			ball.update();
		}
		

	}
	
	public void run() 
	{
		init();
		
		int fps = 60;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) 
		{
			now = System.nanoTime();
			delta += (now-lastTime)/timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1) 
			{
				update();
				render();
				ticks++;
				delta--;
			}
			if(timer>=1000000000) 
			{
				//System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}
	
	public KeyManager getKeyManager() 
	{
		return keyManager;
	}
	
	public synchronized void start() 
	{
		if(running) {return;}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() 
	{
		if(!running) {return;}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
