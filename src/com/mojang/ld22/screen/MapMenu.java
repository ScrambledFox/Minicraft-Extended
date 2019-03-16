package com.mojang.ld22.screen;

import com.mojang.ld22.entity.Player;
import com.mojang.ld22.gfx.Color;
import com.mojang.ld22.gfx.Font;
import com.mojang.ld22.gfx.Screen;
import com.mojang.ld22.level.Level;
import com.mojang.ld22.level.tile.Tile;

public class MapMenu extends Menu {
	private Level level;
	private Player player;
	private int[] colors;
	
	private static int pixelSize = 4;
	
	private static int mapScale = 20;
	
	private static int w = 40;
	private static int h = 40;

	public MapMenu(Level level, Player player) {
		this.level = level;
		this.player = player;
		
		colors = new int[w*h];
	}

	public void tick() {
		if (input.menu.clicked) game.setMenu(null);
	}

	public void render(Screen screen) {

		Font.renderFrame(screen, "Map", 4, 0, 15, 12);
		
		int colorindex = 0;
		for(int y = 0; y < w; y++) {
			for(int x = 0; x < h; x++) {
				
				mapScale = 20;
				
				int xt = ((int)(player.x / mapScale) * mapScale + (x - w/2) * mapScale ) >> 4;
				int yt = ((int)(player.y / mapScale) * mapScale + (y - h/2) * mapScale ) >> 4;
				
				if (level.getExploredTile(xt, yt) > 0) {
					if(player.level.getTile(xt, yt) == Tile.dirt) {
						colors[colorindex] = 200;	
					} else if(player.level.getTile(xt, yt) == Tile.grass) {
						colors[colorindex] = 60;
					} else if(player.level.getTile(xt, yt) == Tile.sand) {
						colors[colorindex] = 210;
					} else if(player.level.getTile(xt, yt) == Tile.rock) {
						colors[colorindex] = 100;
					} else if(player.level.getTile(xt, yt) == Tile.water) {
						colors[colorindex] = 53;
					} else if(player.level.getTile(xt, yt) == Tile.tree) {
						colors[colorindex] = 54;
					} else if(player.level.getTile(xt, yt) == Tile.cactus) {
						colors[colorindex] = 54;
					} else if(player.level.getTile(xt, yt) == Tile.flower) {
						colors[colorindex] = 54;
					} else if(player.level.getTile(xt, yt) == Tile.stairsDown) {
						colors[colorindex] = 110;
					} else if(player.level.getTile(xt, yt) == Tile.stairsUp) {
						colors[colorindex] = 110;
					} else {
						colors[colorindex] = 000;
					}
				} else {
					colors[colorindex] = 000;
				}
				
				colorindex++;
			}
		}
		
		for(int c = 0; c < colors.length; c++) {
			for (int s = 0; s < pixelSize * pixelSize; s++) {
				//screen.renderPixel(c % w, (int)(c / w), colors[c]);
				screen.renderPixel(40 + (c % w / 2 * pixelSize + s % pixelSize), 14 + ((int)(c / h / 2 * pixelSize + s / pixelSize)), colors[c]);
			}
		}
		
		Font.draw("x" + (int)player.x / 10, screen, 70, 0, Color.get(552));
		Font.draw("y" + (int)player.y / 10, screen, 98, 0, Color.get(552));
		
	}
}