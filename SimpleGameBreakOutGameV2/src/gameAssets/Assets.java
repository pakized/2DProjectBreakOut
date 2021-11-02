package gameAssets;

import java.awt.image.BufferedImage;

public class Assets {

	public static BufferedImage redBrick, blueBrick, greenBrick, racket;
	private static final int rectHeight = 15;
	private static final int racetHeight = 15;
	private static final int rectWith = 50;
	private static final int racetWith = 80;
	
	public static void init() 
	{
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/Bricks/SpriteSheet.png"));
		
		racket = sheet.crop(100, 0, racetWith, racetHeight);
		redBrick = sheet.crop(50, 0, rectWith, rectHeight);
		blueBrick = sheet.crop(180, 0, rectWith, rectHeight);
		greenBrick = sheet.crop(0, 0, rectWith, rectHeight);
	}
	
}
