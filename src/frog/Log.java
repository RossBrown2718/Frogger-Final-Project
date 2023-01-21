package frog;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Log {
	private int distance;
	private int x, y;
	private int vx, vy;
	private String file1, file2, file3, file4;
	private Image img1, img2, img3, img4;
	private int width, height;
	private boolean onLog, logIsSurface;

	public Log(String file1, String file2, String file3, String file4, int startX, int startY, int stagger, int vX) {
		x = startX;
		y = startY;
		width = 225;
		height = 75;
		vx = vX;
		vy = 0;
		distance = stagger;
		onLog = false;
		logIsSurface = true;
		
		img1 = getImage(file1);
		img2 = getImage(file2);
		img3 = getImage(file3);
		img4 = getImage(file4);

		init(x, y);
	}

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

	public int getVx() {
		return this.vx;
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
	public boolean isLogSurfaced() {
		return logIsSurface;
	}
	public boolean isOnLog(int fx, int fy) {
		if (fx >= x && fx <= x + width && fy >= y && fy <= y + height) {
			return true;
		}
		return false;
	}

	public void move() {
		tx.translate(vx, 0);
		x += vx;
		distance += Math.abs(vx);

		if (x < -190) {
			x = 650;
			tx.setToTranslation(x, y);
		}

		if (x > 660) {
			x = -190;
			tx.setToTranslation(x, y);
		}
	}

	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (distance <= 160 && distance > 0) {
			g2.drawImage(img1, tx, null);
			logIsSurface = true;
		}
		else if (distance > 160 && distance <= 320) {
			g2.drawImage(img2, tx, null);
			logIsSurface = true;
		}
		else if (distance > 320 && distance <= 480) {
			g2.drawImage(img3, tx, null);
			logIsSurface = true;
		}
		else if (distance > 480 && distance <= 640) {
			g2.drawImage(img4, tx, null);
			logIsSurface = true;
		}
		else if (distance > 640) {
			g2.drawImage(img4, tx, null);
			distance = -160;
			logIsSurface = false;
		}
		else {
			g2.drawImage(img4, tx, null);
			logIsSurface = false;
		}
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
