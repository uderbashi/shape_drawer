import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.geom.Point2D;

public abstract class Polygon implements Shape {
	
	protected double area;
	protected double perimeter;
	protected boolean green;
	
	protected void calibrate() {
		double x_first = 0, y_first = 0;
		int i;
		ArrayList<Point2D.Double> arr = getPointsArray();

		for (i = 0; i < arr.size()-1; ++i) {
			x_first += arr.get(i).getX() * arr.get(i + 1).getY();
			y_first += arr.get(i).getY() * arr.get(i + 1).getX();
		}
		x_first += arr.get(arr.size()-1).getX() * arr.get(0).getY();
		y_first += arr.get(arr.size()-1).getY() * arr.get(0).getX();

		area = Math.abs(x_first - y_first) / 2;

		double total_lengths;
		for (i = 0; i < arr.size()-1; ++i)
			total_lengths = distance(arr.get(i), arr.get(i + 1));
		
		total_lengths = distance(arr.get(arr.size()-1), arr.get(0));
		perimeter = total_lengths;
	}
	
	private double distance(Point2D.Double point1, Point2D.Double point2){
		return Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));
	}
	
	abstract public ArrayList<Point2D.Double> getPointsArray();
	
	@Override
	public int compareTo(Shape that) {
		return Double.compare(this.area(), that.area());
	}

	public boolean isGreen() {
		return green;
	}

	public void setGreen(boolean green) {
		this.green = green;
	}
	
	@Override
	public double area() {
		return area;
	}

	@Override
	public double perimeter() {
		return perimeter;
	}

	@Override
	abstract public Polygon increment();
	
	@Override
	abstract public Polygon decrement();

	@Override
	abstract public int[] getCanvas();

	@Override
	abstract public void draw(Graphics canvas);
	
	abstract protected void push(Point2D p);
	
}
