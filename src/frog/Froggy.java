package frog;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Froggy {

	// attributes of a frog

	private int x, y; // Position of frog
	private int vx, vy; // velocity variables
	private boolean alive; // lives
	private int width; // the size of frog
	private int height;
	private String fileName;
	private Image img; // image of the frog

	public Froggy(String fileName) {
		x = 300;
		y = 905;
		vx = 0;
		vy = 0;
		width = 50;
		height = 60;

		img = getImage(fileName);
		init(x, y);

	}

	// needed getters and setters
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

	public int getVx() {
		return this.vx;
	}
	
	public void setFile(String file) {
		img = getImage(file);
	}

	public boolean collided(int ox, int oy, int ow, int oh) {
		// determines collision
		Rectangle obs = new Rectangle(ox, oy, ow, oh);
		Rectangle froggy = new Rectangle(x, y, width, height);
		return obs.intersects(froggy);
	}

	// movement
	public void move() {
		if (x + vx > 640 || x + vx < 0) {
			x = x;
		} else {
			x += vx;
		}

		if (y + vy > 920 || y + vy < -10) {
			y = y;
		} else {
			y += vy;
		}

		tx.setToTranslation(x, y);

	}

	public void hop(int dir) {

		switch (dir) {
		case 0: // up
			if (this.y - 30 >= -10) {
				this.y -= 82;
			}
			break;

		case 1: // down
			if (this.y + 30 <= 940) {
				this.y += 82;
			}
			break;

		case 2: // left
			if (this.x - 30 >= 0) {
				this.x -= 25;
			}
			break;

		case 3: // right
			if (this.x + 30 <= 590) {
				this.x += 25;
			}
			break;
		}

		tx.setToTranslation(x, y);

	}

	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	// draw the affine transform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		move(); // ask frog to update its location variables
		g2.drawImage(img, tx, null);

	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}
	
	public static void updateGoomb(Froggy f) {
		if (System.currentTimeMillis()%1000 < 250)
			f.setFile("GoombaStill.png");
		else if (System.currentTimeMillis()%1000 < 500)
			f.setFile("GoombaVibe.png");
		else if (System.currentTimeMillis()%1000 < 750)
			f.setFile("GoombaStill.png");
		else f.setFile("GoombaChill.png");
	}
	
	// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Froggy.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	// setters and getters

	public void setVx(int vx) {
		this.vx = vx;
	}

	public void setVy(int vy) {
		this.vy = vy;

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		tx.setToTranslation(x, y);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		tx.setToTranslation(x, y);
	}

}
