package de.fhpotsdam.unfolding.examples.animation;

import processing.core.PApplet;
import de.fhpotsdam.unfolding.Map;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.GeoUtils;
import de.fhpotsdam.unfolding.utils.MapUtils;

/**
 * Load two maps and fade between.
 */
public class FadeTwoMapsApp extends PApplet {
	
	Map map1;
	Map map2;
	
	// Create and set variable we need to fade between our two maps.
	boolean fadeOnce = false;
	boolean fadeAlways = false;
	int fadeVal = 255;
	int fadeDelta = 1;
	int fadeMin = 0;
	int fadeMax = 255;
	
	
	public void setup() {
		size(600, 400);
		
		// Set the position and size of our two maps.
		int mapXposition = 0;
		int mapYposition = 30;
		int mapWidth = width;
		int mapHeight = height-mapYposition;
		// Set our location of the maps
		float lon = 52.5f;
		float lat = 13.4f;
		
		// Initialize two maps
		map1 = new Map(this, mapXposition, mapYposition, mapWidth, mapHeight);
		map1.zoomAndPanTo(new Location(lon, lat), 10);
		map2 = new Map(this, mapXposition, mapYposition, mapWidth, mapHeight, new Microsoft.AerialProvider());
		map2.zoomAndPanTo(new Location(lon, lat), 10);
		MapUtils.createDefaultEventDispatcher(this, map1, map2);
	}
	
	
	public void draw() {
		background(0);
		
		// Calculate Fade Value
		if(fadeAlways == true){
			fadeOnce = false;
			if(fadeVal == 0 || fadeVal == 255)
				fadeDelta = -fadeDelta;
			fadeVal+= fadeDelta;
		}
		
		if(fadeOnce == true){
			if(fadeVal == 0 || fadeVal == 255){
				fadeDelta = -fadeDelta;
				fadeOnce = false;
			}
			fadeVal+= fadeDelta;
		}
		
		// Draw maps
		tint(255);
		map1.draw();
		tint(255, fadeVal);
		map2.draw();
		
		// Description at the Top
		fill(255);
		text("Press key '1' to fade once   |   Press key '2' to fade always", 10, 20);
	}
	
	public void keyPressed(){
		switch(key){
		case '1':
			fadeAlways = false;
			fadeOnce = true;
			break;
		case '2':
			fadeAlways = true;
			fadeOnce = false;
			break;
		}
		/*if(key == '1' && fadeOnce == false){
			fadeOnce = true;
		}*/
	}
	
	
}