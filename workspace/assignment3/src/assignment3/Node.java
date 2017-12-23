package assignment3;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Node {

	Node prevNode;
	Node nextNode;
	int name = 0;
	boolean isEdge = false;
	boolean sideEdge = false;
	boolean isEmpty = false;
	boolean diagonalEdge = false;
	int x;
	int y;
	Point2D point;
	double greedyDistance;
	double heuristicDistance;
	double distance;
	boolean noIntersect = false;
	
	public Node(Point2D point, int name, int x, int y) {
		
		this.name = name;
		prevNode = null;
		this.point = point;
		this.x = x;
		this.y = y;
	}
	
	public Node(Point2D point, boolean isSideEdge, boolean isEmpty, boolean isDiagonalEdge) {
		
		this.point = point;
		prevNode = null;
		nextNode = null;
		isEdge = true;
		sideEdge = isSideEdge;
		this.isEmpty = isEmpty;
		diagonalEdge = isDiagonalEdge;
	}
	
	public void setPrevNode(Node previous) {
		this.prevNode = previous;
	}
	
	public void setNextNode(Node next) {
		this.nextNode = next;
	}
	
	public String getName() {
		String name = " ";
		name = String.valueOf(this.name);
		return name;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public void setGreedyDistance(double distance) {
		this.greedyDistance = distance;
	}
	
	public void setHeuristicDistance(double distance) {
		this.heuristicDistance = distance;
	}
	
	public double getDistance() {
		return this.distance;
	}
	
	public double getGreedyDistance() {
		return this.greedyDistance;
	}
	
	public double getHeuristicDistance() {
		return this.heuristicDistance;
	}

	public ArrayList<Node> findPossibleNodes(Node[][] space, Line2D.Double[] lines, int lineCount, Point2D start, Point2D goal) {

		ArrayList<Node> possibleNodes = new ArrayList<Node>();
		boolean intersects = false;
		Line2D.Double line = new Line2D.Double();
		for (int i = this.x; i < 25; i++) {
			
			for (int j = this.y + 1; j < 25; j++) {
				
				Node possibleNode = space[i][j];
				
				if (possibleNode != null) {
					
					if (!possibleNode.isEdge) {
						line = new Line2D.Double(this.point,possibleNode.point);
						intersects = false;
						
						for (int k = 0; k < lineCount; k++) {
							
							if(intersects == true) {
								break;
							}
							if (line.intersectsLine(lines[k])) {
								if(lines[k].getX1() == line.getX1() || lines[k].getX1() == line.getX2() 
										|| lines[k].getX2() == line.getX1() || lines[k].getX2() == line.getX2() 
										|| lines[k].getY1() == line.getY1() || lines[k].getY1() == line.getY2() 
										|| lines[k].getY2() == line.getY1() || lines[k].getY2() == line.getY2()) {
									continue;
								} else {
									intersects = true;
								}
							}
						}
						if (intersects == false) {
							double distanceFromGoal = line.ptSegDist(goal);
							double distanceFromStart = Math.sqrt((line.getX2()) + (line.getY2()));
							//possibleNode.setGreedyDistance(distanceFromStart);
							possibleNode.setHeuristicDistance(distanceFromGoal + distanceFromStart);
							possibleNode.setDistance(this.point.distance(possibleNode.point));
							possibleNodes.add(possibleNode);
						}
					}
				}
			}
		}
		return possibleNodes;
	}
}
