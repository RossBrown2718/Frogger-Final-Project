package frog;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class BackgroundWinner {
	private int x, y;
	private String fileName;
	private Image img;// the image of the log
	long time = 3000;
	boolean visible = false;
	
	
	public BackgroundWinner(String fileName) {
		// assignment statements for attributes
		x = 0;
		y = 0;
		img = getImage(fileName);
		init(x, y);

	}

	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (visible) {
			g2.drawImage(img, tx, null);
			time -= 16;
		}

		if (time <= 0) {
			visible = false;
			time = 3000;
		}
	}
	public boolean getVisible() {
		return visible;
	}
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1.28, 1.28);
	}

	public void setVisible(boolean flag) {
		visible = flag;
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
