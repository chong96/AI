package assignment3;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class main {
	
	public static void main(String args[]) {
		
		Random rand = new Random();
		Node[][] space = new Node[25][25];
		Line2D.Double[] lines = new Line2D.Double[25];
		int lineCount = 0;
		int numberOfPolygons = rand.nextInt(2) + 3;
		int count = 0;
		int name = 0;
	
		for(int i = 0; i< 25; i++) {
		
			for(int j = 0; j< 25; j++) {

				space[i][j] = null;
			}
	    
		}
	
		space[0][0] = new Node(new Point(0,0),name++,0,0);
		space[24][24] = new Node(new Point(24,24),100,24,24);
	
		while (count <= numberOfPolygons) {
			int numberOfSides = rand.nextInt(3) + 3;
			int randomX = rand.nextInt(25);
			int randomY = rand.nextInt(25);
			boolean polygonCreated = false;
		
			if (randomX != 0 && randomY != 0) {
			
				if (numberOfSides == 3 && randomX < 23 && randomY < 23) {
					if(space[randomX][randomY] == null && space[randomX + 2][randomY] == null 
							&& space[randomX][randomY + 2] == null) {
						space[randomX][randomY] = new Node(new Point(randomX,randomY),name++, randomX, randomY);
						space[randomX + 2][randomY] = new Node(new Point(randomX + 2, randomY), name++, randomX + 2, randomY);
						space[randomX][randomY + 2] = new Node(new Point2D.Double(randomX,randomY + 2),name++, randomX, randomY + 2);
						space[randomX + 1][randomY] = new Node(new Point2D.Double(randomX + 1, randomY), false, false, false);
						space[randomX][randomY + 1] = new Node(new Point2D.Double(randomX, randomY + 1),true, false, false);
						space[randomX + 1][randomY + 1] = new Node(new Point2D.Double(randomX + 1, randomY + 1), true, false, true);
						polygonCreated = true;
						
						lines[lineCount++] = new Line2D.Double((double)space[randomX][randomY].point.getX(),(double)space[randomX][randomY].point.getY(),
								  (double)space[randomX + 2][randomY].point.getX(), (double)space[randomX + 2][randomY].point.getY());
						lines[lineCount++] = new Line2D.Double((double)space[randomX][randomY].point.getX(),(double)space[randomX][randomY].point.getY(),
								  (double)space[randomX][randomY + 2].point.getX(), (double)space[randomX][randomY + 2].point.getY());
						lines[lineCount++] = new Line2D.Double((double)space[randomX + 2][randomY].point.getX(),(double)space[randomX + 2][randomY].point.getY(),
								  (double)space[randomX][randomY + 2].point.getX(), (double)space[randomX][randomY + 2].point.getY());
					} else {
						continue;
					}
				}
			
				if (numberOfSides == 4 && randomX < 23 && randomY < 23) {
					if(space[randomX][randomY] == null && space[randomX + 2][randomY] == null 
							&& space[randomX][randomY + 2] == null && space[randomX + 2][randomY + 2] == null) {
						space[randomX][randomY] = new Node(new Point(randomX,randomY),name++, randomX, randomY);
						space[randomX + 2][randomY] = new Node(new Point(randomX + 2, randomY),name++, randomX + 2, randomY);
						space[randomX][randomY + 2] = new Node(new Point(randomX, randomY + 2),name++, randomX, randomY + 2);
						space[randomX + 2][randomY + 2] = new Node(new Point(randomX + 2, randomY + 2),name++, randomX + 2, randomY + 2);
						space[randomX + 1][randomY] = new Node(new Point(randomX + 1, randomY), false, false, false);
						space[randomX][randomY + 1] = new Node(new Point(randomX, randomY + 1), true, false, false);
						space[randomX + 2][randomY + 1] = new Node(new Point(randomX + 2, randomY + 1), true, false, false);
						space[randomX + 1][randomY + 2] = new Node(new Point(randomX + 1, randomY + 2), false, false, false);
						space[randomX + 1][randomY + 1] = new Node(new Point(randomX + 1, randomY + 1), true, true, false);
						polygonCreated = true;
						
						lines[lineCount++] = new Line2D.Double((double)space[randomX][randomY].point.getX(),(double)space[randomX][randomY].point.getY(),
								  (double)space[randomX + 2][randomY].point.getX(), (double)space[randomX + 2][randomY].point.getY());
						lines[lineCount++] = new Line2D.Double((double)space[randomX][randomY].point.getX(),(double)space[randomX][randomY].point.getY(),
								  (double)space[randomX][randomY + 2].point.getX(), (double)space[randomX][randomY + 2].point.getY());
						lines[lineCount++] = new Line2D.Double((double)space[randomX + 2][randomY].point.getX(),(double)space[randomX + 2][randomY].point.getY(),
								  (double)space[randomX + 2][randomY + 2].point.getX(), (double)space[randomX + 2][randomY + 2].point.getY());
						lines[lineCount++] = new Line2D.Double((double)space[randomX][randomY + 2].point.getX(),(double)space[randomX][randomY + 2].point.getY(),
								  (double)space[randomX + 2][randomY + 2].point.getX(), (double)space[randomX + 2][randomY + 2].point.getY());
					} else {
						continue;
					}
				}
			
				if (numberOfSides == 5 && randomX < 20 && randomY < 23) {
					if(space[randomX][randomY] == null && space[randomX + 1][randomY] == null 
							&& space[randomX][randomY + 1] == null && space[randomX + 1][randomY + 1] == null 
							&& space[randomX][randomY + 2] == null) {
						space[randomX][randomY] = new Node(new Point(randomX,randomY),name++, randomX, randomY);
						space[randomX + 2][randomY] = new Node(new Point(randomX + 2, randomY),false,false,false);
						space[randomX][randomY + 2] = new Node(new Point(randomX, randomY + 2),name++, randomX, randomY + 2);
						space[randomX + 2][randomY + 2] = new Node(new Point(randomX + 2, randomY + 2),name++, randomX + 2, randomY + 2);
						space[randomX + 4][randomY] = new Node(new Point(randomX + 2, randomY),name++, randomX + 4, randomY);
						space[randomX][randomY + 1] = new Node(new Point(randomX, randomY + 1), true, false, false);
						space[randomX + 1][randomY] = new Node(new Point(randomX + 1, randomY + 1), false, false, false);
						space[randomX + 1][randomY + 2] = new Node(new Point(randomX + 1, randomY + 1), false, false, false);
						space[randomX + 3][randomY] = new Node(new Point(randomX + 1, randomY + 1), false, false, false);
						space[randomX + 3][randomY + 1] = new Node(new Point(randomX + 1, randomY + 1), true, false, true);						
						polygonCreated = true;
						
						lines[lineCount++] = new Line2D.Double((double)space[randomX][randomY].point.getX(),(double)space[randomX][randomY].point.getY(),
								  (double)space[randomX + 4][randomY].point.getX(), (double)space[randomX + 4][randomY].point.getY());
						lines[lineCount++] = new Line2D.Double((double)space[randomX][randomY].point.getX(),(double)space[randomX][randomY].point.getY(),
								  (double)space[randomX][randomY + 2].point.getX(), (double)space[randomX][randomY + 2].point.getY());
						//lines[lineCount++] = new Line2D.Double((double)space[randomX + 2][randomY].point.getX(),(double)space[randomX + 2][randomY].point.getY(),
								  //(double)space[randomX + 4][randomY].point.getX(), (double)space[randomX + 4][randomY].point.getY());
						lines[lineCount++] = new Line2D.Double((double)space[randomX][randomY + 2].point.getX(),(double)space[randomX][randomY + 2].point.getY(),
								  (double)space[randomX + 2][randomY + 2].point.getX(), (double)space[randomX + 2][randomY + 2].point.getY());
						lines[lineCount++] = new Line2D.Double((double)space[randomX + 4][randomY].point.getX(),(double)space[randomX + 4][randomY].point.getY(),
								  (double)space[randomX + 2][randomY + 2].point.getX(), (double)space[randomX + 2][randomY + 2].point.getY());
					} else {
						continue;
					}
				}
			
			} else {
				continue;
			}
			if (polygonCreated) {
				count++;
			}
		}
		for(int i = 0; i< 25; i++) {
		
			for(int j = 0; j< 25; j++) {

				if(space[i][j] == null) {
					System.out.print('_');
				} else if (space[i][j].isEdge){
					if (space[i][j].sideEdge && !space[i][j].isEmpty && space[i][j].diagonalEdge) {
						System.out.print('/');
					} else if (space[i][j].sideEdge && space[i][j].isEmpty) {
						System.out.print(' ');
					} else if (space[i][j].sideEdge){
						System.out.print('-');
					} else { 
						System.out.print('|');
					}
				} else {
					System.out.print('*');
				}
			}
	    
			System.out.println();
		}
		
		ArrayList<Node> openList = new ArrayList<Node>();
		ArrayList<Node> closedList = new ArrayList<Node>();
		ArrayList<Node> greedyOpenList = new ArrayList<Node>();
		ArrayList<Node> greedyClosedList = new ArrayList<Node>();
		boolean firstTime = true;
		boolean greedyFirstTime = true;
		Node startNode = space[0][0];
		Point2D startPoint = startNode.point;
		Node goalNode = space[24][24];
		Point2D goalPoint = goalNode.point;
		Node currentNode;
		Node greedyCurrentNode;
		Node previousSuccessor = null;
		Node greedyPreviousSuccessor = null;
		boolean foundGoal = false;
		boolean greedyFoundGoal = false;
		openList.add(startNode);
		double totalDistance = 0;
		double greedyTotalDistance = 0;
		
		do {
			
			if (firstTime) {
				currentNode = startNode;
				firstTime = false;
			} else {
				currentNode = openList.get(0);
			}
			
			closedList.add(currentNode);
			openList.remove(currentNode);
			
			if (closedList.contains(goalNode)) {
				foundGoal = true;
				break;
			}
			
			ArrayList<Node> possibleNodes = currentNode.findPossibleNodes(space,lines,lineCount,startPoint,goalPoint);
			double lowestHeuristicScore = 0;
			
			for (int nodeCounter = 0; nodeCounter < possibleNodes.size(); nodeCounter++) {
				Node possibleNode = possibleNodes.get(nodeCounter);
				
				if (possibleNode == goalNode) {
					foundGoal = true;
					closedList.add(possibleNode);
					break;
				}
				
				if (closedList.contains(possibleNode)) {
					continue;
				}
				
				if (!openList.contains(possibleNode) && previousSuccessor == null) {
					possibleNode.setPrevNode(currentNode);
					openList.add(possibleNode);
					lowestHeuristicScore = possibleNode.getHeuristicDistance();
					previousSuccessor = possibleNode;
				} else if (!openList.contains(possibleNode)) {
					if (possibleNode.getHeuristicDistance() < lowestHeuristicScore) {
						lowestHeuristicScore = possibleNode.getHeuristicDistance();
						possibleNode.setPrevNode(previousSuccessor);
						openList.add(possibleNode);
						closedList.add(previousSuccessor);
						openList.remove(previousSuccessor);
						previousSuccessor = possibleNode;
					}
				}
			}
			
		} while (!openList.isEmpty());
		
		greedyOpenList.add(startNode);
		do {
			
			if (greedyFirstTime) {
				greedyCurrentNode = startNode;
				greedyFirstTime = false;
			} else {
				greedyCurrentNode = greedyOpenList.get(0);
			}
			
			greedyClosedList.add(greedyCurrentNode);
			greedyOpenList.remove(greedyCurrentNode);
			
			if (greedyClosedList.contains(goalNode)) {
				greedyFoundGoal = true;
				break;
			}
			
			ArrayList<Node> greedyPossibleNodes = greedyCurrentNode.findPossibleNodes(space,lines,lineCount,startPoint,goalPoint);
			double highestGreedyScore = 0;
			
			for (int nodeCounter = 0; nodeCounter < greedyPossibleNodes.size(); nodeCounter++) {
				Node possibleNode = greedyPossibleNodes.get(nodeCounter);
				
				if (possibleNode == goalNode) {
					greedyFoundGoal = true;
					greedyClosedList.add(possibleNode);
					break;
				}
				
				if (greedyClosedList.contains(possibleNode)) {
					continue;
				}
				
				if (!greedyOpenList.contains(possibleNode) && previousSuccessor == null) {
					possibleNode.setPrevNode(greedyCurrentNode);
					greedyOpenList.add(possibleNode);
					highestGreedyScore = possibleNode.getDistance();
					greedyPreviousSuccessor = possibleNode;
				} else if (!greedyOpenList.contains(possibleNode)) {
					if (possibleNode.getDistance() > highestGreedyScore) {
						highestGreedyScore = possibleNode.getDistance();
						possibleNode.setPrevNode(greedyPreviousSuccessor);
						greedyOpenList.add(possibleNode);
						greedyClosedList.add(greedyPreviousSuccessor);
						greedyOpenList.remove(greedyPreviousSuccessor);
						greedyPreviousSuccessor = possibleNode;
					}
				}
			}
			
		} while (!greedyOpenList.isEmpty());
		
		String fullName = new String();
		while (currentNode.prevNode != null) {
			double nodeDistance = currentNode.getDistance();
			totalDistance += nodeDistance;
			fullName = fullName + " " + currentNode.getName();
			currentNode = currentNode.prevNode;
		}
		
		String greedyFullName = new String();
		while (greedyCurrentNode.prevNode != null) {
			double nodeDistance = greedyCurrentNode.getDistance();
			greedyTotalDistance += nodeDistance;
			greedyFullName = greedyFullName + " " + greedyCurrentNode.getName();
			greedyCurrentNode = greedyCurrentNode.prevNode;
		}
		
		String tempName = new String();
		double tempScore = 0;
		
		/*if (totalDistance > greedyTotalDistance) {
			tempScore = totalDistance;
			totalDistance = greedyTotalDistance;
			greedyTotalDistance = tempScore;
			tempName = fullName;
			fullName = greedyFullName;
			greedyFullName = tempName;
		}*/
		
		System.out.println(foundGoal);
		System.out.println("Total Score(A*): " + totalDistance);
		System.out.println("Path (Nodes): " + fullName);
		
		System.out.println(greedyFoundGoal);
		System.out.println("Total Score(Greedy): " + greedyTotalDistance);
		System.out.println("Path (Nodes): " + greedyFullName);
	}
}
