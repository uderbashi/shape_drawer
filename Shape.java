import java.awt.Graphics;

public interface Shape extends Comparable<Shape> {
	public double area();
	public double perimeter();
	public Shape increment();
	public Shape decrement();
	public int[] getCanvas();
	public void draw(Graphics canvas);
}
