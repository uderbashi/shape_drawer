import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Driver {
	
	
	public static void main(String[] args) {
		JFrame menuFrame = new JFrame();
		menuFrame.setSize(575, 120);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setTitle("Main Menu");
		menuFrame.setResizable(false);
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setVisible(true);
		JPanel menuPanel = new JPanel();
		Dimension buttonSize = new Dimension(175, 25);
		
		
		JLabel menuLabel = new JLabel("     Select a shape to draw:");
		menuLabel.setPreferredSize(new Dimension(575, 25));
		JButton rectButton = createButton("Rectangle", buttonSize);
		JButton circleButton = createButton("Circle", buttonSize);
		JButton triButton = createButton("Triangle", buttonSize);
		JButton CSButton = createButton("Composed Shape", buttonSize);
		JButton PVButton = createButton("PolygonVect Circle", buttonSize);
		JButton PDButton = createButton("PolygonDyn Circle", buttonSize);
		
		rectButton.addActionListener( new ActionListener()
		 {
		     @Override
		     public void actionPerformed(ActionEvent e)
		     {
		 		JFrame rectDim = new JFrame();
		 		rectDim.setSize(300,100);
		 		rectDim.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		 		rectDim.setTitle("Rectangle Dimentions");
		 		rectDim.setResizable(false);
		 		rectDim.setLocationRelativeTo(null);
		 		rectDim.setVisible(true);
		 		
		 		JPanel rectPanel = new JPanel();
		 		JLabel widthLabel = new JLabel(" Width:");
		 		JLabel heightLabel = new JLabel("Height:");
		 		JTextField widthText = new JTextField(20);
		 		JTextField heightText = new JTextField(20);
		 		JButton button = new JButton("Draw");
		 		
		 		button.addActionListener( new ActionListener()
				 {
				     @Override
				     public void actionPerformed(ActionEvent e)
				     {
				    	rectDim.setVisible(false);
				    	try {
					    	JFrame display = new JFrame();
				    		int width = Integer.parseInt(widthText.getText());
				    		int height = Integer.parseInt(heightText.getText());
					 		display.setSize(width,height);
					 		display.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					 		display.setTitle("Rectangle");
					 		display.setResizable(false);
					 		display.setLocationRelativeTo(null);
					 		display.setVisible(true);
					 		Canvas rect = new Canvas();
					 		rect.add(new Rectangle(width, height));
					 		display.add(rect);
				    	} catch(NumberFormatException exception) {
				    		 JOptionPane.showMessageDialog(null, "Didn't insert numbers");
				    	}			 		
				     }
				 });
		 		
		 		rectPanel.add(widthLabel);
		 		rectPanel.add(widthText);
		 		rectPanel.add(heightLabel);
		 		rectPanel.add(heightText);
		 		rectPanel.add(button);
		 		rectDim.add(rectPanel);
		 		
		     }
		 });
		
		circleButton.addActionListener( new ActionListener()
		 {
		     @Override
		     public void actionPerformed(ActionEvent e)
		     {
		 		JFrame circleDim = new JFrame();
		 		circleDim.setSize(300,75);
		 		circleDim.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		 		circleDim.setTitle("Circle Dimentions");
		 		circleDim.setResizable(false);
		 		circleDim.setLocationRelativeTo(null);
		 		circleDim.setVisible(true);
		 		
		 		JPanel circlePanel = new JPanel();
		 		JLabel radiusLabel = new JLabel("Radius:");
		 		JTextField radiusText = new JTextField(20);
		 		JButton button = new JButton("Draw");
		 		
		 		button.addActionListener( new ActionListener()
				 {
				     @Override
				     public void actionPerformed(ActionEvent e)
				     {
				    	circleDim.setVisible(false);
				    	try {
					    	JFrame display = new JFrame();
				    		int radius = Integer.parseInt(radiusText.getText());
					 		display.setSize(radius * 2,radius * 2);
					 		display.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					 		display.setTitle("Circle");
					 		display.setResizable(false);
					 		display.setLocationRelativeTo(null);
					 		display.setVisible(true);
					 		Canvas circle = new Canvas();
					 		circle.add(new Circle(radius));
					 		display.add(circle);
				    	} catch(NumberFormatException exception) {
				    		 JOptionPane.showMessageDialog(null, "Didn't insert numbers");
				    	}			 		
				     }
				 });
		 		circlePanel.add(radiusLabel);
		 		circlePanel.add(radiusText);
		 		circlePanel.add(button);
		 		circleDim.add(circlePanel);
		 		
		     }
		 });
		
		triButton.addActionListener( new ActionListener()
		 {
		     @Override
		     public void actionPerformed(ActionEvent e)
		     {
		 		JFrame triDim = new JFrame();
		 		triDim.setSize(300,75);
		 		triDim.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		 		triDim.setTitle("Triangle Dimentions");
		 		triDim.setResizable(false);
		 		triDim.setLocationRelativeTo(null);
		 		triDim.setVisible(true);
		 		
		 		JPanel triPanel = new JPanel();
		 		JLabel sideLabel = new JLabel("Side:");
		 		JTextField sideText = new JTextField(25);
		 		JButton button = new JButton("Draw");
		 		
		 		button.addActionListener( new ActionListener()
				 {
				     @Override
				     public void actionPerformed(ActionEvent e)
				     {
				    	triDim.setVisible(false);

				    	try {
					    	JFrame display = new JFrame();
				    		int side = Integer.parseInt(sideText.getText());
					 		display.setSize(side, (int)(side * Math.sin(Math.PI / 3)) + 1);
					 		display.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					 		display.setTitle("Triangle");
					 		display.setResizable(false);
					 		display.setLocationRelativeTo(null);
					 		display.setVisible(true);
					 		Canvas triangle = new Canvas();
					 		triangle.add(new Triangle(side));
					 		display.add(triangle);
				    	} catch(NumberFormatException exception) {
				    		 JOptionPane.showMessageDialog(null, "Didn't insert numbers");
				    	}			 		
				     }
				 });
		 		triPanel.add(sideLabel);
		 		triPanel.add(sideText);
		 		triPanel.add(button);
		 		triDim.add(triPanel);
		 		
		     }
		 });
	
		CSButton.addActionListener( new ActionListener()
		 {
		     @Override
		     public void actionPerformed(ActionEvent e)
		     {
		 		JFrame CSMenu = new JFrame();
		 		CSMenu.setSize(500, 300);
		 		CSMenu.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		 		CSMenu.setTitle("Composed Shape Menu");
		 		CSMenu.setResizable(false);
		 		CSMenu.setLocationRelativeTo(null);
		 		CSMenu.setVisible(true);
		 		
		 		JPanel CSPanel = new JPanel();
		 		
		 		JLabel contLabel = new JLabel("Container:");
		 		contLabel.setPreferredSize(new Dimension(500, 25));
		 		JRadioButton contR = new JRadioButton("Rectangle", true);
		 		JRadioButton contC = new JRadioButton("Circle");
		 		JRadioButton contT = new JRadioButton("Triangle");
		 		ButtonGroup cont = new ButtonGroup();
		 		cont.add(contR);
		 		cont.add(contC);
		 		cont.add(contT);
		 		
		 		JLabel smallLabel = new JLabel("Small:");
		 		smallLabel.setPreferredSize(new Dimension(500, 25));
		 		JRadioButton smallR = new JRadioButton("Rectangle", true);
		 		JRadioButton smallC = new JRadioButton("Circle");
		 		JRadioButton smallT = new JRadioButton("Triangle");
		 		ButtonGroup small = new ButtonGroup();
		 		small.add(smallR);
		 		small.add(smallC);
		 		small.add(smallT);
		 		
		 		JLabel contLabelT = new JLabel("Container:");
		 		contLabelT.setPreferredSize(new Dimension(500, 25));
		 		JTextField contPrm1 = new JTextField("Width", 20);
		 		JTextField contPrm2 = new JTextField("Height",20);
		 		JLabel smallLabelT = new JLabel("Small:");
		 		smallLabelT.setPreferredSize(new Dimension(500, 25));
		 		JTextField smallPrm1 = new JTextField("Width",20);
		 		JTextField smallPrm2 = new JTextField("Height",20);
		 		
		 		contR.addActionListener(new ActionListener() {
		 	        @Override
		 	        public void actionPerformed(ActionEvent e) {
		 	        	contPrm1.setText("Width");
		 	        	contPrm2.setText("Height");
		 	        	contPrm2.setEditable(true);
		 	        }
		 	    });

		 		contC.addActionListener(new ActionListener() {
		 	        @Override
		 	        public void actionPerformed(ActionEvent e) {
		 	        	contPrm1.setText("Radius");
		 	        	contPrm2.setText("");
		 	        	contPrm2.setEditable(false);
		 	        }
		 	    });
		 		contT.addActionListener(new ActionListener() {
		 	        @Override
		 	        public void actionPerformed(ActionEvent e) {
		 	        	contPrm1.setText("Side");
		 	        	contPrm2.setText("");
		 	        	contPrm2.setEditable(false);
		 	        }
		 	    });

		 		smallR.addActionListener(new ActionListener() {
		 	        @Override
		 	        public void actionPerformed(ActionEvent e) {
		 	        	smallPrm1.setText("Width");
		 	        	smallPrm2.setText("Height");
		 	        	smallPrm2.setEditable(true);
		 	        }
		 	    });
		 		smallC.addActionListener(new ActionListener() {
		 	        @Override
		 	        public void actionPerformed(ActionEvent e) {
		 	        	smallPrm1.setText("Radius");
		 	        	smallPrm2.setText("");
		 	        	smallPrm2.setEditable(false);
		 	        }
		 	    });

		 		smallT.addActionListener(new ActionListener() {
		 	        @Override
		 	        public void actionPerformed(ActionEvent e) {
		 	        	smallPrm1.setText("Side");
		 	        	smallPrm2.setText("");
		 	        	smallPrm2.setEditable(false);
		 	        }
		 	    });
		 		
		 		
		 		JButton button = new JButton("Draw");
		 		
		 		button.addActionListener( new ActionListener()
				 {
				     @Override
				     public void actionPerformed(ActionEvent e)
				     {
				    	 CSMenu.setVisible(false);

				    	try {
					    	
				    		
				    		JFrame display = new JFrame();
				    		int cPrm1 = Integer.parseInt(contPrm1.getText());
				    		int cPrm2 = 0;
				    		if(contR.isSelected()) {
				    			cPrm2 = Integer.parseInt(contPrm2.getText());
				    			display.setSize(cPrm1, cPrm2);
				    		} else if (contC.isSelected()) {
				    			display.setSize(cPrm1 * 2, cPrm1 * 2);
				    		} else {
				    			display.setSize(cPrm1, (int)(cPrm1 * Math.sin(Math.PI / 3)) + 1);
				    		}
				    		
				    		int sPrm1 = Integer.parseInt(smallPrm1.getText());
				    		int sPrm2 = 0;
				    		if(smallR.isSelected())
				    			sPrm2 = Integer.parseInt(smallPrm2.getText());
					 		
				    		
				    		
					 		display.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					 		display.setTitle("Composed Shape");
					 		display.setResizable(false);
					 		display.setLocationRelativeTo(null);
					 		display.setVisible(true);
					 		
					 		Canvas Composed = new Canvas();
					 		
					 		if(contR.isSelected()) {
					 			if(smallR.isSelected()) {
					 				Composed.add(new ComposedShape(new Rectangle(cPrm1, cPrm2), new Rectangle(sPrm1, sPrm2)));
					    		} else if (smallC.isSelected()) {
					    			Composed.add(new ComposedShape(new Rectangle(cPrm1, cPrm2), new Circle(sPrm1)));
					    		} else {
					    			Composed.add(new ComposedShape(new Rectangle(cPrm1, cPrm2), new Triangle(sPrm1)));
					    		}
				    		} else if (contC.isSelected()) {
				    			if(smallR.isSelected()) {
				    				Composed.add(new ComposedShape(new Circle(cPrm1), new Rectangle(sPrm1, sPrm2)));
					    		} else if (smallC.isSelected()) {
					    			Composed.add(new ComposedShape(new Circle(cPrm1), new Circle(sPrm1)));
					    		} else {
					    			Composed.add(new ComposedShape(new Circle(cPrm1), new Triangle(sPrm1)));
					    		}
				    		} else {
				    			if(smallR.isSelected()) {
				    				Composed.add(new ComposedShape(new Triangle(cPrm1), new Rectangle(sPrm1, sPrm2)));
					    		} else if (smallC.isSelected()) {
					    			Composed.add(new ComposedShape(new Triangle(cPrm1), new Circle(sPrm1)));
					    		} else {
					    			Composed.add(new ComposedShape(new Triangle(cPrm1), new Triangle(sPrm1)));
					    		}
				    		}
					 		
					 		display.add(Composed);
				    	} catch(NumberFormatException exception) {
				    		 JOptionPane.showMessageDialog(null, "Didn't insert numbers");
				    	}			 		
				     }
				 });
		 		
		 		
		 		CSPanel.add(contLabel);
		 		CSPanel.add(contR);
		 		CSPanel.add(contC);
		 		CSPanel.add(contT);
		 		
		 		CSPanel.add(smallLabel);
		 		CSPanel.add(smallR);
		 		CSPanel.add(smallC);
		 		CSPanel.add(smallT);
		 		
		 		CSPanel.add(contLabelT);
		 		CSPanel.add(contPrm1);
		 		CSPanel.add(contPrm2);
		 		
		 		CSPanel.add(smallLabelT);
		 		CSPanel.add(smallPrm1);
		 		CSPanel.add(smallPrm2);
		 		
		 		CSPanel.add(button);
		 		CSMenu.add(CSPanel);
		 		
		     }
		 });

		PVButton.addActionListener( new ActionListener()
		 {
		     @Override
		     public void actionPerformed(ActionEvent e)
		     {
		 		JFrame PVDim = new JFrame();
		 		PVDim.setSize(300,75);
		 		PVDim.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		 		PVDim.setTitle("Circle Dimentions");
		 		PVDim.setResizable(false);
		 		PVDim.setLocationRelativeTo(null);
		 		PVDim.setVisible(true);
		 		
		 		JPanel PVCirclePanel = new JPanel();
		 		JLabel radiusLabel = new JLabel("Radius:");
		 		JTextField radiusText = new JTextField(20);
		 		JButton button = new JButton("Draw");
		 		
		 		button.addActionListener( new ActionListener()
				 {
				     @Override
				     public void actionPerformed(ActionEvent e)
				     {
				    	 PVDim.setVisible(false);
				    	try {
					    	JFrame display = new JFrame();
				    		int radius = Integer.parseInt(radiusText.getText());
					 		display.setSize(radius * 2,radius * 2);
					 		display.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					 		display.setTitle("PolygonVect Circle");
					 		display.setResizable(false);
					 		display.setLocationRelativeTo(null);
					 		display.setVisible(true);
					 		Canvas circle = new Canvas();
					 		circle.add(new PolygonVect(new Circle(radius)));
					 		display.add(circle);
				    	} catch(NumberFormatException exception) {
				    		 JOptionPane.showMessageDialog(null, "Didn't insert numbers");
				    	}			 		
				     }
				 });
		 		PVCirclePanel.add(radiusLabel);
		 		PVCirclePanel.add(radiusText);
		 		PVCirclePanel.add(button);
		 		PVDim.add(PVCirclePanel);
		 		
		     }
		 });
		
		PDButton.addActionListener( new ActionListener()
		 {
		     @Override
		     public void actionPerformed(ActionEvent e)
		     {
		 		JFrame PDDim = new JFrame();
		 		PDDim.setSize(300,75);
		 		PDDim.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		 		PDDim.setTitle("Circle Dimentions");
		 		PDDim.setResizable(false);
		 		PDDim.setLocationRelativeTo(null);
		 		PDDim.setVisible(true);
		 		
		 		JPanel PDCirclePanel = new JPanel();
		 		JLabel radiusLabel = new JLabel("Radius:");
		 		JTextField radiusText = new JTextField(20);
		 		JButton button = new JButton("Draw");
		 		
		 		button.addActionListener( new ActionListener()
				 {
				     @Override
				     public void actionPerformed(ActionEvent e)
				     {
				    	 PDDim.setVisible(false);
				    	try {
					    	JFrame display = new JFrame();
				    		int radius = Integer.parseInt(radiusText.getText());
					 		display.setSize(radius * 2,radius * 2);
					 		display.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					 		display.setTitle("PolygonVect Circle");
					 		display.setResizable(false);
					 		display.setLocationRelativeTo(null);
					 		display.setVisible(true);
					 		Canvas circle = new Canvas();
					 		circle.add(new PolygonDyn(new Circle(radius)));
					 		display.add(circle);
				    	} catch(NumberFormatException exception) {
				    		 JOptionPane.showMessageDialog(null, "Didn't insert numbers");
				    	}			 		
				     }
				 });
		 		PDCirclePanel.add(radiusLabel);
		 		PDCirclePanel.add(radiusText);
		 		PDCirclePanel.add(button);
		 		PDDim.add(PDCirclePanel);
		 		
		     }
		 });
		
		menuPanel.add(menuLabel);
		menuPanel.add(rectButton);
		menuPanel.add(circleButton);
		menuPanel.add(triButton);
		menuPanel.add(CSButton);
		menuPanel.add(PVButton);
		menuPanel.add(PDButton);
		menuFrame.add(menuPanel);

	}
	
	private static JButton createButton(String text, Dimension size) {
		JButton button = new JButton(text);
		button.setPreferredSize(size);
		button.setMinimumSize(size);
		button.setMaximumSize(size);
		return button;
	}
	
	public void drawAll(Shape arr[], JPanel panel) {
		Canvas canvas = new Canvas();
		for(Shape current : arr)
			canvas.add(current);
		panel.add(canvas);
	}
	
	public PolygonVect[] convertAll(Shape arr[]) {
		PolygonVect temp[] = new PolygonVect[arr.length];
		for(int i = 0; i < arr.length; ++i) {
			if (arr[i] instanceof Rectangle)
				temp[i] = new PolygonVect((Rectangle)arr[i]);
			else if (arr[i] instanceof Circle)
				temp[i] = new PolygonVect((Circle)arr[i]);
			else if (arr[i] instanceof Triangle)
				temp[i] = new PolygonVect((Triangle)arr[i]);
			else
				temp[i] = new PolygonVect((Polygon)arr[i]);
		}
			
		return temp;
	}
	
	public Shape[] sortShapes(Shape arr[]) {
		Shape temp[] = new Shape[arr.length];
		for(int i = 0; i < arr.length; ++i)
			temp[i] = arr[i];
		Arrays.sort(temp);
		return temp;
	}
}
