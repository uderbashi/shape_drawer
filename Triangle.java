import java.awt.Color;
import java.awt.Graphics;

public class Triangle implements Shape{
	
	private double side;
	private double height;
	private double xCoordLeft;
	private double yCoordLeft;
	private double perimeter;
	private double area;
	private boolean green;
	private boolean sideUp;

	private void checker() {
		if (side <= 0 || height <= 0 || xCoordLeft < 0 || yCoordLeft < -0.1)
			throw new IllegalArgumentException("Bad Traiangle Parameters.");
	}
	
	private void calibrate() {
		height = side * Math.sin(Math.PI / 3);
		area = side * height / 2;
		perimeter = side * 3;
	}
	
	public Triangle() {
		this.side = 1;
		calibrate();
		this.xCoordLeft = 0;
		this.yCoordLeft = height;
		this.green = false;
		this.sideUp = true;
		checker();
	}

	public Triangle(double side) {
		this.side = side;
		calibrate();
		this.xCoordLeft = 0;
		this.yCoordLeft = height;
		this.green = false;
		this.sideUp = true;
		checker();
	}

	public Triangle(double side, double xCoordLeft, double yCoordLeft, boolean sideUp) {
		this.side = side;
		calibrate();
		this.xCoordLeft = xCoordLeft;
		this.yCoordLeft = yCoordLeft;
		this.green = true;
		this.sideUp = sideUp;
		checker();
	}

	public boolean isGreen() {
		return green;
	}

	public void setGreen(boolean green) {
		this.green = green;
	}

	public boolean isSideUp() {
		return sideUp;
	}

	public void setSideUp(boolean sideUp) {
		this.sideUp = sideUp;
	}

	public double getSide() {
		return side;
	}

	public void setSide(double side) {
		this.side = side;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getxCoordLeft() {
		return xCoordLeft;
	}

	public void setxCoordLeft(double xCoordLeft) {
		this.xCoordLeft = xCoordLeft;
	}

	public double getyCoordLeft() {
		return yCoordLeft;
	}

	public void setyCoordLeft(double yCoordLeft) {
		this.yCoordLeft = yCoordLeft;
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
	public Triangle increment() {
		xCoordLeft += 1.0;
		yCoordLeft += 1.0;
		return this;
	}

	@Override
	public Triangle decrement() {
		xCoordLeft -= 1.0;
		yCoordLeft -= 1.0;
		return this;
	}
	
	@Override
	public int[] getCanvas() {
		int canvas[] = new int[2];
		canvas[0] = (int)side;
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
		
		int Xs[] = new int[3];
		int Ys[] = new int[3];
		Xs[0] = (int) xCoordLeft;
		Ys[0] = (int) yCoordLeft;
		Xs[1] = (int) xCoordLeft + (int) side;
		Ys[1] = (int) yCoordLeft;
		Xs[2] = (int) xCoordLeft + (int) side / 2;
		if (sideUp)
			Ys[2] = (int) yCoordLeft - (int) height;
		else
			Ys[2] = (int) yCoordLeft + (int) height;
		
		canvas.fillPolygon(Xs, Ys, 3);
		canvas.setColor(Color.BLACK);
		canvas.drawPolygon(Xs, Ys, 3);
	}	

	
}
