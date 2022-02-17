import java.awt.Color;
import java.awt.Graphics;

public class Rectangle implements Shape {
	
	private double width;
	private double height;
	private double xCoord;
	private double yCoord;
	private double perimeter;
	private double area;
	private boolean green;
	
	private void checker() {
		if (width <= 0 || height <= 0 || xCoord < 0 || yCoord < 0)
			throw new IllegalArgumentException("Bad Rectangle Parameters.");
	}
	
	private void calibrate() {
		this.area = width * height;
		this.perimeter = (width + height) * 2;
	}
	
	public Rectangle() {
		this.width = 1;
		this.height = 1;
		this.xCoord = 0;
		this.yCoord = 0;
		this.green = false;
		checker();
		calibrate();
	}

	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
		this.xCoord = 0;
		this.yCoord = 0;
		this.green = false;
		checker();
		calibrate();
		
	}

	public Rectangle(double width, double height, double xCoord, double yCoord) {
		this.width = width;
		this.height = height;
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

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
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
	public Rectangle increment() {
		xCoord += 1.0;
		yCoord += 1.0;
		return this;
	}

	@Override
	public Rectangle decrement() {
		xCoord -= 1.0;
		yCoord -= 1.0;
		return this;
	}
	
	@Override
	public int[] getCanvas() {
		int canvas[] = new int[2];
		canvas[0] = (int)width;
		canvas[1] = (int)height;
		return canvas;
	}

	@Override
	public void draw(Graphics canvas) {
		if (green) {
			canvas.setColor(Color.GREEN);
		} else {
			canvas.setColor(Color.RED);
		}
		canvas.fillRect((int)xCoord, (int)yCoord, (int)width, (int)height);
		canvas.setColor(Color.BLACK);
		canvas.drawRect((int)xCoord, (int)yCoord, (int)width, (int)height);
	}	

}
