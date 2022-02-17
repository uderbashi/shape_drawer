import java.awt.Color;
import java.awt.Graphics;

public class Circle implements Shape{

	private double radius;
	private double xCoord;
	private double yCoord;
	private double perimeter;
	private double area;
	private boolean green;
	
	private void checker() {
		if (radius < 0 || xCoord < radius || yCoord < radius)
			throw new IllegalArgumentException("Bad Circle Parameters.");
	}
	
	private void calibrate() {
		area = radius * radius * Math.PI;
		perimeter = 2 * radius * Math.PI;	
	}

	public Circle() {
		this.radius = 1;
		this.xCoord = 2;
		this.yCoord = 2;
		this.green = false;
		checker();
		calibrate();
	}
	
	public Circle(double radius) {
		this.radius = radius;
		this.xCoord = radius;
		this.yCoord = radius;
		this.green = false;
		checker();
		calibrate();
	}
	
	public Circle(double radius, double xCoord, double yCoord) {
		this.radius = radius;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.green = true;
		checker();
		calibrate();
	}

	public boolean isGreen() {
		return green;
	}

	public void setGreen(boolean green) {
		this.green = green;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getxCoord() {
		return xCoord;
	}

	public void setxCoord(double xCoord) {
		this.xCoord = xCoord;
	}

	public double getyCoord() {
		return yCoord;
	}

	public void setyCoord(double yCoord) {
		this.yCoord = yCoord;
	}

	@Override
	public int compareTo(Shape that) {
		return Double.compare(this.area(), that.area());
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
	public Circle increment() {
		xCoord += 1.0;
		yCoord += 1.0;
		return this;
	}

	@Override
	public Circle decrement() {
		xCoord -= 1.0;
		yCoord -= 1.0;
		return this;
	}
	
	@Override
	public int[] getCanvas() {
		int canvas[] = new int[2];
		canvas[0] = (int)radius * 2;
		canvas[1] = (int)radius * 2;
		return canvas;
	}

	@Override
	public void draw(Graphics canvas) {
		if (green) {
			canvas.setColor(Color.GREEN);
		} else {
			canvas.setColor(Color.RED);
		}
		canvas.fillOval((int)xCoord - (int)radius, (int)yCoord - (int)radius, 2 * (int)radius, 2 * (int)radius);
		canvas.setColor(Color.BLACK);
		canvas.drawOval((int)xCoord - (int)radius, (int)yCoord - (int)radius, 2 * (int)radius, 2 * (int)radius);
		
	}

}
