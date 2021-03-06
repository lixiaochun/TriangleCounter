/**
 * 
 * Copyright (C) Tyler Hackett 2016
 * 
 * Triangle Counter Project
 * 
 * A quickly-written program to determine all possible combinations of
 * valid triangles from a grid, allowing for certain coordinates of the
 * grid to be marked as unusable.
 * 
 * */
package trianglecounter;

import java.util.ArrayList;

public class Counter 
{
	public int gridSize = 4;
	
	public ArrayList<Point> invalidPoints = new ArrayList<Point>();
	public ArrayList<Point> validPoints = new ArrayList<Point>();
	
	public ArrayList<Triangle> triangles = new ArrayList<Triangle>();
	
	public Counter()
	{
		//Add any invalid coordinates on the grid.
		//In this case, all corners are unallowed.
		invalidPoints.add(new Point(0,0));
		invalidPoints.add(new Point(0,3));
		invalidPoints.add(new Point(3,0));
		invalidPoints.add(new Point(3,3));
		
		//Generate valid points (any coordinate not in invalidPoints.)
		for(int x = 0; x < gridSize; x++)
		{
			for(int y = 0; y < gridSize; y++)
			{
				Point p = new Point(x,y);
				
				if(!isInvalidPoint(p))
				{
					validPoints.add(p);
				}
			}
		}
		
		Point xPoint, yPoint;

		for(int x = 0; x < validPoints.size(); x++)
		{
			xPoint = validPoints.get(x);
			for(int y = 0; y < validPoints.size(); y++)
			{
				yPoint = validPoints.get(y);
				for(int z = 0; z < validPoints.size(); z++)
				{
					Triangle t = new Triangle(xPoint, yPoint, validPoints.get(z));
					
					if(t.isValid())
					{
						if(!hasTriangle(t, triangles))
						{
							triangles.add(t);
						}
					}
				}
			}
		}
		
		System.out.println(triangles.size() + " valid triangles found.");
		
	}
	
	public boolean hasTriangle(Triangle t, ArrayList<Triangle> triangles)
	{
		for(Triangle triangle : triangles)
		{
			if(triangle.isSameAs(t))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isInvalidPoint(Point p)
	{
		for(Point point : invalidPoints)
		{
			if(point.isSameAs(p))
				return true;
		}
		return false;
	}
	
}
