package gameAssets;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage spriteSheet;
	
	public SpriteSheet(BufferedImage sheet) 
	{
		spriteSheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int width, int height) 
	{
		return spriteSheet.getSubimage(x, y, width, height);
	}
}
