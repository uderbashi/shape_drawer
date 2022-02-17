import java.awt.Graphics;

public class ComposedShape implements Shape {
	
	private double area;
	private double perimeter;
	private int fitting;
	private Shape[] shapes;
	
	private void add(Shape added) {
		int size = shapes.length;
		Shape temp[] = new Shape[size + 1];
		
		for (int i = 0; i < size; ++i) {
			temp[i] = shapes[i];
		}
		
		temp[size] = added;
		shapes = temp;
	}

	private double find_distance(double x0, double x1, double y0, double y1) {
		double distance_sqr;

		distance_sqr = Math.pow(x0 - x1, 2) + Math.pow(y0 - y1, 2);

		return Math.sqrt(distance_sqr);
	}
	
	private int RinR(Rectangle small) {
		int total = 0; // counter for the shapes that will be drawn
		double current_x = 0, current_y = 0; //the top left corner's coordinates
		double leftover_width, leftover_height;

		while (current_y + small.getHeight() <= shapes[0].getCanvas()[1]) { // straight forward placing
			current_x = 0;
			while (current_x + small.getWidth() <= shapes[0].getCanvas()[1]) {
				add(new Rectangle(small.getWidth(), small.getHeight(), current_x, current_y));
				++total;
				current_x += small.getWidth();
			}
			current_y += small.getHeight();
		}
		
		// the next part will check for leftovers and if we can fit anything in them if we rotated the shape 90 degrees
		leftover_height = shapes[0].getCanvas()[1] -current_y; 
		leftover_width = shapes[0].getCanvas()[0] - current_x;

		if (leftover_width >= small.getHeight() && small.getWidth() <= shapes[0].getCanvas()[1]) { 
			current_y = 0;
			while (current_y + small.getWidth() <= shapes[0].getCanvas()[1]) {
				while (current_x + small.getHeight() <= shapes[0].getCanvas()[0]) {
					add(new Rectangle(small.getHeight(), small.getWidth(), current_x, current_y));
					++total;
					current_x += small.getHeight();
				}
				current_x = shapes[0].getCanvas()[0] - leftover_width; // resets to the leftover's start
				current_y += small.getWidth();
			}
		}

		if (leftover_height >= small.getWidth() && small.getHeight() <= shapes[0].getCanvas()[0]) { // read loop above
			while (current_y + small.getWidth() <= shapes[0].getCanvas()[1]) {
				current_x = 0;
				while (current_x + small.getHeight() <= shapes[0].getCanvas()[0]) {
					add(new Rectangle(small.getHeight(), small.getWidth(), current_x, current_y));
					++total;
					current_x += small.getHeight();
				}
				current_y += small.getWidth();
			}
		}

		return total;
	}

	private int CinR(Circle small) {
		// I have found two ways to fit the circles and each with the cases where it shines, and decided to use both
		
		int total = 0;
		int line_no = 0;
		double x_coord, y_coord = small.getRadius();
		boolean triangular_packaging; // stores if triangular or square packing will be used
		double sqr_col, sqr_row, tp_col, tp_row; // to store square and triangular packing values to compare them later 

		//this is the mathematics counting of the implementations, google them and you'll find it 
		sqr_row = shapes[0].getCanvas()[1] / (2 * small.getRadius()); 
		sqr_col = shapes[0].getCanvas()[0] / (2 * small.getRadius());

		tp_row = (shapes[0].getCanvas()[1] / small.getRadius() - 1) / 2;
		tp_col = (shapes[0].getCanvas()[0] / small.getRadius() - 2) / (2 * Math.sin(Math.PI / 3)) + 1;

		triangular_packaging = (sqr_row * sqr_col < tp_row * tp_col + tp_col / 2) ? true : false; // check which one can fit more

		if (triangular_packaging) {
			double gap = small.getRadius() * 2 * Math.sin(Math.PI / 3);

			while (y_coord + small.getRadius() <= shapes[0].getCanvas()[1]) {
				x_coord = small.getRadius() + ((line_no % 2) * gap);
				while (x_coord + small.getRadius() <= shapes[0].getCanvas()[0]) {
					add(new Circle(small.getRadius(), x_coord, y_coord));
					++total;
					x_coord += gap * 2;
				}
				++line_no;
				y_coord += small.getRadius();
			}	
		} else {
			while (y_coord + small.getRadius() <= shapes[0].getCanvas()[1]) {
				x_coord = small.getRadius();
				while (x_coord + small.getRadius() <= shapes[0].getCanvas()[0]) {
					add(new Circle(small.getRadius(), x_coord, y_coord));
					++total;
					x_coord += small.getRadius() * 2;
				}
				y_coord += small.getRadius() * 2;
			}
		}
		return total;
	}

	private int TinR(Triangle small) {
		// in some narrow vertical rectangles this implementaion isn't the best
		double current_x;
		double current_height = small.getHeight(); 
		boolean even;
		int i = 0;

		while (current_height <= shapes[0].getCanvas()[1]) {
			current_x = 0;
			while (current_x + small.getSide() <= shapes[0].getCanvas()[0]) {
				even = (i % 2 == 0) ? true : false;
				add(new Triangle(small.getSide(), current_x, current_height - (i % 2) * small.getHeight(), even));
				++i;
				current_x += small.getSide() / 2;
			}
			current_height += small.getHeight();
		}

		return i;
	}
	
	private int RinC(Rectangle small) {
		/* 
		** this implementation SUCKS... But it is the best thing that I can think of
		** it literally just places them horizontally and doesn't consider vertical options 
		*/
		int total = 0;
		double current_x, current_y = 0, search_y;
		double conatiner_radius = shapes[0].getCanvas()[1] / 2;
		boolean swap_toggle = false;

		if (small.getWidth() < small.getHeight()){ // because I can't implement vertical rectangles 
			swap_toggle = true;
			double temp = small.getHeight();
			small.setHeight(small.getWidth());
			small.setWidth(temp);
		}

		current_x = conatiner_radius - small.getWidth() / 2; // first rectangle gets drawn on top in the middle of the circle

		while (find_distance(conatiner_radius, current_x, conatiner_radius, current_y += 0.5) > conatiner_radius && current_y < conatiner_radius); // to find the height where it is in bound

		if (current_y * 2 + small.getHeight() > shapes[0].getCanvas()[1]) // no rectangle could be placed
			return 0;

		add(new Rectangle(small.getWidth(), small.getHeight(), current_x, current_y));
		++total;
		current_y += small.getHeight();
		

		while (current_y <= shapes[0].getCanvas()[1]) {
			current_x = 0;
			search_y = current_y;

			if (search_y + small.getHeight() > conatiner_radius){
				search_y += small.getHeight();
			}

			while (find_distance(conatiner_radius, current_x += 0.5, conatiner_radius, search_y) > conatiner_radius && current_x < conatiner_radius);

			while (find_distance(conatiner_radius, current_x + small.getWidth(), conatiner_radius, search_y) < conatiner_radius) {
				add(new Rectangle(small.getWidth(), small.getHeight(), current_x, current_y));
				++total;
				current_x += small.getWidth();
			}

			current_y += small.getHeight();
		}
		
		if (swap_toggle) {
			double temp = small.getHeight();
			small.setHeight(small.getWidth());
			small.setWidth(temp);
		}

		return total;
	}
	
	private int CinC(Circle small, double big_radius) {
		/*
		** This implementation is GREAT unlike the one before 
		** it fills the circumference with circles and then moves in and fills it up again until it reaches the middle  
		*/
		if (big_radius < small.getRadius()) // if no circles could be drawn 
			return 0;

		if (big_radius < small.getRadius() * 2) { // if only one circle can be drawn in the middle 
			add(new Circle(small.getRadius(), shapes[0].getCanvas()[1] / 2, shapes[0].getCanvas()[1] / 2));
			return 1;
		}

		int i;
		
		double drawing_radius = big_radius - small.getRadius();
		int drawable = (int) ((2 * Math.PI * drawing_radius) / (2 * small.getRadius()));

		// fixing an issue with the centre iteration
		double x1 = drawing_radius * Math.cos(0 * 2 * Math.PI / drawable);
		double y1 = drawing_radius * Math.sin(0 * 2 * Math.PI / drawable);
		double x2 = drawing_radius * Math.cos(1 * 2 * Math.PI / drawable);
		double y2 = drawing_radius * Math.sin(1 * 2 * Math.PI / drawable);
		double distance = find_distance(x1, x2, y1, y2);
		if (distance < 2 * small.getRadius())
			--drawable;

		for (i = 0; i < drawable; ++i) {
			double current_x, current_y;
			current_x = shapes[0].getCanvas()[1] / 2 + drawing_radius * Math.cos(i * 2 * Math.PI / drawable); // multiplying by sin and cos gives us a value 
			current_y = shapes[0].getCanvas()[1] / 2 + drawing_radius * Math.sin(i * 2 * Math.PI / drawable); // that is added and substracted based on its place around the big circumference
			add(new Circle(small.getRadius(), current_x, current_y));
		}

		return i + CinC(small, drawing_radius - small.getRadius());
	}
	
	private int TinC(Triangle small) {
		/*
		** This one is also ugly and has a lot of similarities to the rect_in_circle
		** Still its results are much better compared to the former
		** if the former was understood this one should be easy to understand
		*/
		float radius = shapes[0].getCanvas()[1] / 2;

		if (small.getSide() > Math.sqrt(3) * radius) // if no triangle can be places 
			return 0;

		int i;
		int total = 0;
		double current_x = shapes[0].getCanvas()[1] / 2 - small.getSide() / 2, current_y = small.getHeight(), previous_x, search_y;
		boolean side_up, toggle; // the first is the orientation of the triangle, the latter is to check if the previous x was assigned its value

		add(new Triangle(small.getSide(), current_x, current_y, true));
		++total;
		previous_x = current_x;


		while (find_distance(radius, previous_x -= 0.5, radius, current_y) < radius && previous_x > 0);

		while (current_y + small.getHeight() <= shapes[0].getCanvas()[1]) {
			current_x = 0;
			search_y = current_y;
			i = 0;
			toggle = false;
			search_y += small.getHeight();

			while (find_distance(radius, current_x += 0.5, radius, search_y) > radius && current_x < radius);
			side_up = (current_y < radius) ? true: false;
			
			if (previous_x > current_x + small.getSide() / 2 && search_y < radius){
				double temp = current_x;
				current_x = previous_x;
				previous_x = temp;
				current_x -= small.getSide() / 2;
				toggle = true;
			} else if (current_x > previous_x + small.getSide() / 2 && current_y > radius){
				current_x -= small.getSide() / 2;
			}
			
			if (!toggle)
				previous_x = current_x;
			
			if (side_up)
				++i;
			
			for (; find_distance(radius, current_x + small.getSide(), radius, search_y - (( i+ 1) % 2) * small.getHeight()) <= radius; ++i) {
				add(new Triangle(small.getSide(), current_x, current_y + (i % 2) * small.getHeight(), side_up));
				++total;
				current_x += small.getSide() / 2;
				side_up = (side_up) ? false: true;
			}

			
			current_y += small.getHeight();
		}
		return total;
	}
	
	private int RinT(Rectangle small) {
		// another ugly resulting implementation
		int total = 0, line_no = 0;
		double gap; // between the left edge of the triangle and the left side of the retangle
		double current_x = 0;
		double current_y;
		boolean swap_toggle = false;

		if (small.getWidth() < small.getHeight()){ // because I can't implement vertical rectangles 
			swap_toggle = true;
			double temp = small.getHeight();
			small.setHeight(small.getWidth());
			small.setWidth(temp);
		}

		gap = small.getHeight() / Math.sin(Math.PI / 3) / 2;
		current_y = shapes[0].getCanvas()[1] - small.getHeight();

		while (current_y >= 0) { // starts filling from down up
			++line_no;
			current_x = line_no * gap;
			
			while(current_x + small.getWidth() <= shapes[0].getCanvas()[0] - line_no * gap) {
				add(new Rectangle(small.getWidth(), small.getHeight(), current_x, current_y));
				++total;
				current_x += small.getWidth();
			}

			current_y -= small.getHeight();
		}

		if (swap_toggle) {
			double temp = small.getHeight();
			small.setHeight(small.getWidth());
			small.setWidth(temp);
		}

		return total;
	}
	
	private int CinT(Circle small, double x_coord, double y_coord, int line_no) {
		// places a circle in the middle and then goes down recursively shifting to the right a bit until it reaches the bottom
		int i;

		if (y_coord + small.getRadius() > shapes[0].getCanvas()[1])
			return 0;

		for (i = 0; i < line_no; ++i)
			add(new Circle(small.getRadius(), x_coord + 2 * small.getRadius() * i, y_coord));

		return line_no + CinT(small, x_coord - small.getRadius(), y_coord + (small.getRadius() * 2 * Math.sin(Math.PI / 3)), line_no + 1);
	}
	
	private int TinT(Triangle small, double left_corner_x, double left_corner_y, int line_no) {
		/*
		** Similar implemetations to the one above it 
		** just has the difference which is the orientation of the triangles
		*/
		int i;
		boolean even;

		if (left_corner_y > shapes[0].getCanvas()[1])
			return 0;

		for (i = 0; i < 2 * line_no - 1; ++i) {
			even = (i % 2 == 0) ? true : false;
			add(new Triangle(small.getSide(), left_corner_x + i * small.getSide() / 2, left_corner_y - (i % 2) * small.getHeight(), even));
		}

		return line_no * 2 - 1 + TinT(small, left_corner_x - small.getSide() / 2, left_corner_y + small.getHeight(), line_no + 1);
	}
	
	public ComposedShape(Rectangle container, Rectangle small) {
		shapes = new Shape[1];
		shapes[0] = new Rectangle(container.getWidth(), container.getHeight());
		fitting = RinR(small);
		area = container.area() - fitting * small.area();
		perimeter = container.perimeter();
	}
	
	public ComposedShape(Rectangle container, Circle small) {
		shapes = new Shape[1];
		shapes[0] = new Rectangle(container.getWidth(), container.getHeight());
		fitting = CinR(small);
		area = container.area() - fitting * small.area();
		perimeter = container.perimeter();
	}
	
	public ComposedShape(Rectangle container, Triangle small) {
		shapes = new Shape[1];
		shapes[0] = new Rectangle(container.getWidth(), container.getHeight());
		fitting = TinR(small);
		area = container.area() - fitting * small.area();
		perimeter = container.perimeter();
	}
	
	public ComposedShape(Circle container, Rectangle small) {
		shapes = new Shape[1];
		shapes[0] = new Circle(container.getRadius());
		fitting = RinC(small);
		area = container.area() - fitting * small.area();
		perimeter = container.perimeter();
	}
	
	public ComposedShape(Circle container, Circle small) {
		shapes = new Shape[1];
		shapes[0] = new Circle(container.getRadius());
		fitting = CinC(small, container.getRadius());
		area = container.area() - fitting * small.area();
		perimeter = container.perimeter();
	}
	
	public ComposedShape(Circle container, Triangle small) {
		shapes = new Shape[1];
		shapes[0] = new Circle(container.getRadius());
		fitting = TinC(small);
		area = container.area() - fitting * small.area();
		perimeter = container.perimeter();
	}
	
	public ComposedShape(Triangle container, Rectangle small) {
		shapes = new Shape[1];
		shapes[0] = new Triangle(container.getSide());
		fitting = RinT(small);
		area = container.area() - fitting * small.area();
		perimeter = container.perimeter();
	}
	
	public ComposedShape(Triangle container, Circle small) {
		shapes = new Shape[1];
		shapes[0] = new Triangle(container.getSide());
		fitting = CinT(small, container.getSide() / 2, small.getRadius() * 2, 1);
		area = container.area() - fitting * small.area();
		perimeter = container.perimeter();
	}
	
	public ComposedShape(Triangle container, Triangle small) {
		shapes = new Shape[1];
		shapes[0] = new Triangle(container.getSide());
		fitting = TinT(small, container.getSide() / 2 - small.getSide() / 2, small.getHeight(), 1);
		area = container.area() - fitting * small.area();
		perimeter = container.perimeter();
	}
	
	public int getFitting() {
		return fitting;
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
	public ComposedShape increment() {
		// DOES NOTHING
		return this;
	}

	@Override
	public ComposedShape decrement() {
		// DOES NOTHING
		return this;
	}
	
	@Override
	public int[] getCanvas() {
		return shapes[0].getCanvas();
	}

	@Override
	public void draw(Graphics canvas) {
		for (int i = 0; i < shapes.length; ++i)
			shapes[i].draw(canvas);
	}

}
