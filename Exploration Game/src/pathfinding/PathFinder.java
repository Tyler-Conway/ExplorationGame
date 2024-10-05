package pathfinding;

import java.util.ArrayList;

import main.GamePanel;

public class PathFinder {

	GamePanel gp;
	Node[][] nodes;
	ArrayList<Node> openList = new ArrayList<>();
	public ArrayList<Node> pathList = new ArrayList<>();
	Node startNode, goalNode, currentNode;
	boolean goalReached = false;
	int step = 0;
	
	public PathFinder(GamePanel gp) {
		this.gp = gp;
		
		instantiateNodes();
		
	}
	
	
	public void instantiateNodes() {
		//Create a Node for every tile on the map:
		nodes = new Node[gp.maxWorldCol][gp.maxWorldRow];
		int col = 0, row = 0;
		
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			nodes[col][row] = new Node(col,row);
			
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
		
	}
	
	
	public void resetNodes() {
		//Reset previous results for next use of A* algorithm: (otherwise, the algorithm wont work right)
		int col = 0, row = 0;
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			//Reset open, checked, and solid state:
			nodes[col][row].open = false;
			nodes[col][row].checked = false;
			nodes[col][row].solid = false;
			
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
		
		//Reset other settings: (List of open Nodes, Pathlist nodes, goalReached boolean, step = 0)
		openList.clear();
		pathList.clear();
		goalReached = false;
		step = 0;
		
	}
	
	public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
		resetNodes();
		
		//Set start and goal nodes, and add current node to openList of Nodes:
		startNode = nodes[startCol][startRow];
		currentNode = startNode;
		goalNode = nodes[goalCol][goalRow];
		openList.add(currentNode);
		
		
		int col = 0, row = 0;
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			//Set Solid Nodes by checking all tiles for their collision status:
			int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
			if(gp.tileM.tile[tileNum].collision == true) {
				nodes[col][row].solid = true;
				
			}
			//Check Interactive Tiles (to setup solid Nodes): 
			for(int i = 0; i < gp.iTile[0].length; i++) {
				if(gp.iTile[gp.currentMap][i] != null && gp.iTile[gp.currentMap][i].destructable == true){
					int itCol = gp.iTile[gp.currentMap][i].worldX/gp.tileSize; // get Column number of the interactive tile.
					int itRow = gp.iTile[gp.currentMap][i].worldY/gp.tileSize; // get Row number of the interactive tile.
					
					//use these col and row values to setup corresponding Nodes as solid:
					if(!(gp.iTile[gp.currentMap][i].name.equals("Trunk"))) {
						nodes[itCol][itRow].solid = true;

					}
				}
			}
			
			//Set Node Costs:
			getCost(nodes[col][row]);
			
			
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
		
	}
	
	public void getCost(Node node) {
		//G cost (Horiz + Vert displacement from starting Node):
		int xDistance = Math.abs(node.col - startNode.col);
		int yDistance = Math.abs(node.row - startNode.row);
		node.gCost = xDistance + yDistance;
		
		//H Cost (horiz + vert displacement from goal Node):
		xDistance = Math.abs(node.col - goalNode.col);
		yDistance = Math.abs(node.row - goalNode.row);
		node.hCost = xDistance + yDistance;
		
		//F Cost (H + G):
		node.fCost = node.gCost + node.hCost;
		
	}
	//Where the magic of A* happens:
	public boolean search() {
		
		while(goalReached == false && step < 500) {
			int col = currentNode.col;
			int row = currentNode.row;
			
			//Check the current Node:
			currentNode.checked = true;
			openList.remove(currentNode);
			
			//Open the Up Node:
			if(row - 1 >= 0) {
				openNode(nodes[col][row-1]);
			}
			//Open the Left Node:
			if(col - 1 >= 0) {
				openNode(nodes[col-1][row]);
			}
			//Open the Down Node:
			if(row + 1 <= gp.maxWorldRow) {
				openNode(nodes[col][row+1]);
			}
			//Open the Right Node:
			if(col + 1 <= gp.maxWorldCol) {
				openNode(nodes[col+1][row]);
			}
			
			//Find the best Node:
			int bestNodeIndex = 0;
			int bestNodeFCost = 999;
			
			for(int i = 0; i < openList.size(); i++) {
				//Check if this Node's F-Cost is better:
				if(openList.get(i).fCost < bestNodeFCost) {
					bestNodeIndex = i;
					bestNodeFCost = openList.get(i).fCost;
					
				}
				//If FCost is equal Check the GCost
				else if(openList.get(i).fCost == bestNodeFCost) {
					if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
						bestNodeIndex = i;
						
					}
				}
			}
			//If there is no node in the openList, end loop:
			if(openList.size() == 0) {
				break;
			}
			
			//After the Loop, set openList[bestNodeIndex] as the next step ( = currentNode)
			currentNode = openList.get(bestNodeIndex);
			if(currentNode == goalNode) {
				goalReached = true;
				trackThePath();
			}
			
			//Increase step after every full execution of while loop:
			step++;
		}
		
		return goalReached;
	}
	
	public void openNode(Node node) {
		if(node.open == false && node.checked == false && node.solid == false) {
			
			node.open = true;
			node.parent = currentNode;
			openList.add(node);
		}
	}
	
	public void trackThePath() {
		Node current = goalNode;
		
		while(current != startNode) {
			pathList.add(0,current);
			current = current.parent;
		}
	}

}
