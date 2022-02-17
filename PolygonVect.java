import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class PolygonVect extends Polygon {
	
	ArrayList<Point2D.Double> points;
	
	public PolygonVect() {
		points = new ArrayList<Point2D.Double>();
	}
	
	public PolygonVect(Polygon added) {
		points = new ArrayList<Point2D.Double>();
		points = added.getPointsArray();
		calibrate();
		green = added.isGreen();
	}
	
	public PolygonVect(Rectangle added) {
		points = new ArrayList<Point2D.Double>();
		green = added.isGreen();

		push(new Point2D.Double(added.getxCoord(), added.getyCoord()));
		push(new Point2D.Double(added.getxCoord() + added.getWidth(), added.getyCoord()));
		push(new Point2D.Double(added.getxCoord() + added.getWidth(), added.getyCoord() + added.getHeight()));
		push(new Point2D.Double(added.getxCoord(), added.getyCoord() + added.getHeight()));
		calibrate();
	}
	
	public PolygonVect(Circle added) {
		points = new ArrayList<Point2D.Double>();
		green = added.isGreen();
		
		int CIRCLE_PARTS = 100;

		for (int i = 0; i < CIRCLE_PARTS; ++i) {
			double current_x, current_y;
			current_x = added.getxCoord() + added.getRadius() * Math.cos(i * 2 * Math.PI / CIRCLE_PARTS); 
			current_y = added.getyCoord() + added.getRadius() * Math.sin(i * 2 * Math.PI / CIRCLE_PARTS); 
			push(new Point2D.Double(current_x, current_y));
		}
		calibrate();
	}
	
	public PolygonVect(Triangle added) {
		points = new ArrayList<Point2D.Double>();
		green = added.isGreen();
		
		push(new Point2D.Double(added.getxCoordLeft(), added.getyCoordLeft()));
		push(new Point2D.Double(added.getxCoordLeft() + added.getSide(), added.getyCoordLeft()));
		
		if (added.isSideUp())
			push(new Point2D.Double(added.getxCoordLeft() + (added.getSide() / 2.0), added.getyCoordLeft() - added.getHeight()));
		else	
			push(new Point2D.Double(added.getxCoordLeft() + (added.getSide() / 2.0), added.getyCoordLeft() + added.getHeight()));
		calibrate();
	}
	
	public PolygonVect(ArrayList<Point2D> added) {
		points = new ArrayList<Point2D.Double>();
		for (Point2D current : added)
			push(current);
		calibrate();
		green = true;
	}

	@Override
	public ArrayList<Point2D.Double> getPointsArray() {
		return points;
	}

	@Override
	public PolygonVect increment() {
		ArrayList<Point2D.Double> temp = new ArrayList<Point2D.Double>();

		for (Point2D.Double current : points)
			temp.add(new Point2D.Double(current.getX() + 1, current.getY() + 1));

		points = temp;

		return this;
	}

	@Override
	public PolygonVect decrement() {
		ArrayList<Point2D.Double> temp = new ArrayList<Point2D.Double>();

		for (Point2D.Double current : points)
			temp.add(new Point2D.Double(current.getX() - 1, current.getY() - 1));

		points = temp;

		return this;
	}

	@Override
	public int[] getCanvas() {
		int canvas[] = new int[2];
		canvas[0] = 0;
		canvas[1] = 0;
		
		for (Point2D.Double current : points) {
			if(current.getX() > canvas[0])
				canvas[0]= (int) current.getX();
			if(current.getY() > canvas[1])
				canvas[1]= (int) current.getY(); 
		}
		return canvas;
	}

	@Override
	public void draw(Graphics canvas) {
		if (green) {
			canvas.setColor(Color.GREEN);
		} else {
			canvas.setColor(Color.RED);
		}
		
		int Xs[] = new int[points.size()];
		int Ys[] = new int[points.size()];
		for (int i = 0; i < points.size(); ++i) {
			Xs[i]= (int) points.get(i).getX();
			Ys[i]= (int) points.get(i).getY(); 
		}
		
		canvas.fillPolygon(Xs, Ys, points.size());
		canvas.setColor(Color.BLACK);
		canvas.drawPolygon(Xs, Ys, points.size());

	}

	@Override
	protected void push(Point2D p) {
		points.add(new Point2D.Double(p.getX(), p.getY()));
	}

}
