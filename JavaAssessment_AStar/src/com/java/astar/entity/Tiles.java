package com.java.astar.entity;

import java.util.ArrayList;
import java.util.List;

public class Tiles implements Comparable<Tiles>{
	private char symbol;
	private int xX;
	private int yY;
	private int cost;
	private Tiles parent;
	private List<Tiles> neighbours;
	
	public Tiles(char symbol, int xX, int yY, int cost) {
		this.symbol = symbol;
		this.xX = xX;
		this.yY = yY;
		this.cost = cost;
		neighbours = new ArrayList<Tiles>(); 
	}
	
	public char getSymbol() {
		return symbol;
	}
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	public int getxX() {
		return xX;
	}
	public void setxX(int xX) {
		this.xX = xX;
	}
	public int getyY() {
		return yY;
	}
	public void setyY(int yY) {
		this.yY = yY;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public Tiles getParent() {
		return parent;
	}
	public void setParent(Tiles parent) {
		this.parent = parent;
	}
	public List<Tiles> getNeighbours() {
		return neighbours;
	}

	@Override
	public int compareTo(Tiles o) {
		int diff = this.getCost()-o.getCost();
		if(diff == 0)
			return -1;
		else
			return diff; 
	}
	
}
