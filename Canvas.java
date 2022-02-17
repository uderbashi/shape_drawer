import java.awt.Graphics;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Canvas extends JComponent {
	
	private int size;
	private Shape shapes[];
	
	public Canvas() {
		size = 0;
		shapes = new Shape[size];
	}
	
	public void add(Shape added) {
		size++;
		Shape temp[] = new Shape[size];
		
		for (int i = 0; i < size - 1; ++i) {
			temp[i] = shapes[i];
		}
		
		temp[size - 1] = added;
		shapes = temp;
	}
	
	public void paintComponent(Graphics g) {
		for (int i = 0; i < size; ++i)
			shapes[i].draw(g);
	}
}
