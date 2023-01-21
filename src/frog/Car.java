package frog;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Car {

	private int x; // the x position of Car
	private int y; // the y position of Car
	private int vx; // the movement of car
	private Image img;// the image of the Car
	private int width;
	private int height;

	public Car(String fileName, int startX, int startY) {
		// assignment statements for attributes

		x = startX;
		y = startY;
		width = 30;
		height = 50;
		vx = -1;
		img = getImage(fileName);
		init(x, y);

	}

	public Car(String fileName) {
		// assignment statements for attributes

		x = 0;
		y = 0;
		vx = 0;
		width = 50;
		height = 50;

		img = getImage(fileName);
		init(x, y);

	}

	// getters and setters

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setFile(String file) {
		img = getImage(file);
	}
	public void move() {
		tx.translate(vx, 0);
		x += vx;

		// wrap around if cross border
		if (x < -50) {
			x = 950;
			tx.setToTranslation(x, y);
		}

		if (x > 950) {
			x = -50;
			tx.setToTranslation(x, y);
		}
	}
	
	public static void updateCarsSkill(Car[] car1) { //For the right facing Marios
		for (int i = 0; i < car1.length; i++) {
		if (System.currentTimeMillis()%1000 < 250)
			car1[i].setFile("Mario.png");
		else if (System.currentTimeMillis()%1000 < 500)
			car1[i].setFile("NewMario2.png");
		else if (System.currentTimeMillis()%1000 < 750)
			car1[i].setFile("NewMario3.png");
		else car1[i].setFile("NewMario4.png");
		}
	}
	
	public static void updateCars(Car[] car1) { //For the left facing Marios
		for (int i = 0; i < car1.length; i++) {
			if (System.currentTimeMillis()%1000 < 250)
				car1[i].setFile("Marg1.png");
			else if (System.currentTimeMillis()%1000 < 500)
				car1[i].setFile("Marg2.png");
			else if (System.currentTimeMillis()%1000 < 750)
				car1[i].setFile("Marg3.png");
			else car1[i].setFile("Marg4.png");
		}
	}
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Car.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
}
