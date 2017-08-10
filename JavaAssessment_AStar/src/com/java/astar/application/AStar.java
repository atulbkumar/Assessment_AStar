package com.java.astar.application;

import java.util.Collections;

import com.java.astar.entity.Tiles;

public class AStar {

	static int goalX;
	static int goalY;
	static int arrLenX;
	static int arrLenY;
//	public static char[][] result;
	public static boolean[][] traversed;
	
	public static void main(String[] args) {
		String str = "@.~~"
				+ "^~*."
				+ "*^.~"
				+ "*^*X";
		String[] aStr = str.split("\r");
//		System.out.println(aStr.length + "--" + str.length());

//		char[][] graph = 	{
//								{'@', 	'.', 	'~', 	'~'},
//								{'^',	'~',	'*',	'.'},
//								{'*',	'^',	'.',	'~'},
//								{'*',	'^',	'*',	'X'}
//							};
		char[][] graph = 	{
				{'@', 	'~', 	'.', 	'~'},
				{'~',	'*',	'~',	'.'},
				{'~',	'.',	'~',	'^'},
				{'~',	'~',	'~',	'X'}
			};
		goalX = graph.length;
		goalY = graph[0].length;

		findMinimumCost(graph, 0, 0);
		System.out.println("\n\n");
		for(int i=0; i<graph.length; i++){
			for(int j=0; j<graph[i].length; j++){
				System.out.print(graph[i][j] +" ");
			}
			System.out.println();
		}
	}

	public static void findMinimumCost(char[][] graph, int pX, int pY){
		arrLenX = graph.length;
		arrLenY = graph[0].length;
		traversed = new boolean[arrLenX][arrLenY];
		
		char symbol = graph[pX][pY];
		Tiles tile = new Tiles(symbol, pX, pY, 0);
		tile.setParent(null);

		while(tile != null && tile.getSymbol() != 'X'){
			checkNeighbor(graph, tile);
			pX = tile.getxX();
			pY = tile.getyY();
			traversed[pX][pY] = true;
			
			System.out.println(tile.getSymbol() + " -> : " + tile.getNeighbours().size());
			for(Tiles t : tile.getNeighbours()){
				System.out.println(" --> : " + t.getSymbol() +" - " + t.getxX() +", " +t.getyY()); 
			}
			if(tile.getNeighbours().size() > 0){
				tile = tile.getNeighbours().remove(0);
				graph[pX][pY] = '#';
			}
			else{
				graph[pX][pY] = tile.getSymbol();
				tile = tile.getParent();
			}
		}

		graph[arrLenX-1][arrLenY-1] = '#';

	}
	
	public static void addNeighbor(int pX, int pY, Tiles tile, char[][] graph){
		int cost;
		char node;
		Tiles child = null;
		node = graph[pX][pY];
		if(node != '~' && !traversed[pX][pY]){
			cost = getCost(node) + (goalX-pX) + (goalY-pY);
			child = new Tiles(node, pX, pY, cost);
			child.setParent(tile);
			tile.getNeighbours().add(child);
		}
	}

	public static void checkNeighbor(char[][] graph, Tiles tile){
		int pX = tile.getxX();
		int pY = tile.getyY();
		
		if(pX > 0)
			addNeighbor(pX-1, pY, tile, graph);

		if(pY > 0){
			if(pX > 0){
				addNeighbor(pX-1, pY-1, tile, graph);
			}
			addNeighbor(pX, pY-1, tile, graph);
			if(pX < arrLenX-1){
				addNeighbor(pX+1, pY-1, tile, graph);
			}
		}
		if(pY < arrLenY-1){
			if(pX > 0){
				addNeighbor(pX-1, pY+1, tile, graph);
			}
			addNeighbor(pX, pY+1, tile, graph);
			
		}
		
		if(pX < arrLenX-1)
			addNeighbor(pX+1, pY, tile, graph);
		if(pY < arrLenY-1 && pX < arrLenX-1){
				addNeighbor(pX+1, pY+1, tile, graph);
		}
		
		Collections.sort(tile.getNeighbours());
	}

	public static int getCost(char node){
		if(node == '^')
			return 3;
		if(node == '*')
			return 2;
		if(node == '.' || node == 'X')
			return 1;

		return 0;
	}
}
