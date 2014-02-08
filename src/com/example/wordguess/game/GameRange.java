package com.example.wordguess.game;

/**
 * General datastruture to hold ranges (number of questions / alternatives) and number ranges
 * Usage depends
 */
public class GameRange {
	private int minX;
	private int maxX;
	private int minY;
	private int maxY;

	public GameRange( int minX, int maxX, int minY, int maxY ) {
		if( minX >= maxX ) {
			throw new IllegalArgumentException("minX (" + minX + ") must be less than maxX (" + maxX + ")");
		}
		if( minY >= maxY ) {
			throw new IllegalArgumentException("minY (" + minY + ") must be less than maxY (" + maxY + ")");
		}
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
	}

	public int getMinX() {
		return minX;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMinY() {
		return minY;
	}

	public int getMaxY() {
		return maxY;
	}
}