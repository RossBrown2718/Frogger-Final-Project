
//https://github.com/domingodavid/froggerEclipse

package frog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Driver extends JPanel implements ActionListener, KeyListener {
	// make variables
	Froggy froggy;
	
	Car[] car1 = new Car[8];
	Car[] car2 = new Car[8];
	Car[] car3 = new Car[8];
	Car[] car4 = new Car[8];

	Log[] log1 = new Log[2];
	Log[] log2 = new Log[2];
	Log[] log3 = new Log[2];
	Log[] log4 = new Log[2];

	Background bg;
	BackgroundWinner winBg;
	BackgroundLoser loseBg;

	int my_variable = 3;
	int totalLosses = 0;
	int totalWins = 0;

	String lose = "";
	String win = "";
	String lost = "";

	Music hop = new Music("sm64_spotted_by_goomba.wav", false);
	Music sound1 = new Music("sm64_happy_message.wav", false);
	Music sound2 = new Music("sm64_goomba_flattened.wav", false);
	
	public void paint(Graphics g) {

		super.paintComponent(g);
		bg.paint(g);

		g.setFont(font);
		g.setColor(Color.white);
		g.drawString(("Lives:") + Integer.toString(my_variable), 10, 50);
		g.drawString(("Wins:") + Integer.toString(totalWins), 150, 50);
		g.drawString(("Losses:") + Integer.toString(totalLosses), 280, 50);
		g.setFont(font2);

		// paint sprites for cars and logs
		Car.updateCarsSkill(car1);
		Car.updateCars(car2);
		Car.updateCars(car3);
		Car.updateCarsSkill(car4);
		Froggy.updateGoomb(froggy);
		
		for (int i = 0; i < car1.length; i++) {
			car1[i].paint(g);
		}
		for (int i = 0; i < car2.length; i++) {
			car2[i].paint(g);
		}
		for (int i = 0; i < car3.length; i++) {
			car3[i].paint(g);
		}
		for (int i = 0; i < car4.length; i++) {
			car4[i].paint(g);
		}
		for (int i = 0; i < log1.length; i++) {
			log1[i].paint(g);
		}
		for (int i = 0; i < log2.length; i++) {
			log2[i].paint(g);
		}
		for (int i = 0; i < log3.length; i++) {
			log3[i].paint(g);
		}
		for (int i = 0; i < log4.length; i++) {
			log4[i].paint(g);
		}

		froggy.paint(g);

		// froggy and car collision
		for (int i = 0; i < car1.length; i++) {
			if (froggy.collided(car1[i].getX(), car1[i].getY(), car1[i].getWidth(), car1[i].getHeight())) {
				my_variable--;
				froggy.setX(300);
				froggy.setY(905);
			}
		}
		for (int i = 0; i < car2.length; i++) {

			if (froggy.collided(car2[i].getX(), car2[i].getY(), car2[i].getWidth(), car2[i].getHeight())) {
				my_variable--;
				froggy.setX(300);
				froggy.setY(905);
			}
		}

		for (int i = 0; i < car3.length; i++) {
			if (froggy.collided(car3[i].getX(), car3[i].getY(), car3[i].getWidth(), car3[i].getHeight())) {
				my_variable--;
				froggy.setX(300);
				froggy.setY(905);
			}
		}
		for (int i = 0; i < car4.length; i++) {
			if (froggy.collided(car4[i].getX(), car4[i].getY(), car4[i].getWidth(), car4[i].getHeight())) {
				my_variable--;
				froggy.setX(300);
				froggy.setY(905);
			}
		}

		// log movement, creates a death zone unless froggy is on log
		boolean on = false;
		if (froggy.getY() < 350 && froggy.getY() > 100) {
			for (int i = 0; i < log1.length; i++) {
				if (log1[i].isOnLog(froggy.getX(), froggy.getY()) && log1[i].isLogSurfaced()) {
					froggy.setVx(log1[i].getVx() - 1);
					on = true;
				}
			}
			for (int i = 0; i < log2.length; i++) {
				if (log2[i].isOnLog(froggy.getX(), froggy.getY()) && log2[i].isLogSurfaced()) {
					froggy.setVx(log2[i].getVx() + 1);
					on = true;
				}
			}
			for (int i = 0; i < log1.length; i++) {
				if (log3[i].isOnLog(froggy.getX(), froggy.getY()) && log3[i].isLogSurfaced()) {
					froggy.setVx(log3[i].getVx() - 1);
					on = true;
				}
			}
			for (int i = 0; i < log1.length; i++) {
				if (log4[i].isOnLog(froggy.getX(), froggy.getY()) && log4[i].isLogSurfaced()) {
					froggy.setVx(log4[i].getVx() + 1);
					on = true;
				}
			}
			if (on == false) {
				froggy.setX(300);
				froggy.setY(905);
				my_variable--;
			}
		} else {
			froggy.setVx(0);
		}

		if (my_variable <= 0) {
			my_variable = 3;
			totalLosses++;
			loseBg.setVisible(true);
		}
		loseBg.paint(g);

		// resetting
		if (my_variable > 0 && froggy.getY() <= 10) {
			on = false;
			froggy.setX(300);
			froggy.setY(905);
			totalWins++;
			winBg.setVisible(true);
			sound1.play();
		}
		winBg.paint(g);

	}

	Font font = new Font("Courier New", 1, 30);
	Font font2 = new Font("Courier New", 1, 30);

	public void update() {
		// update positions
		froggy.move();
		for (int i = 0; i < 8; i++) {
			car1[i].setVx(2);
			car1[i].move();

			car2[i].setVx(-2);
			car2[i].move();

			car3[i].setVx(-2);
			car3[i].move();

			car4[i].setVx(2);
			car4[i].move();
		}

		for (int i = 0; i < log1.length; i++) {
			log1[i].move();
			log2[i].move();
			log3[i].move();
			log4[i].move();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}

	public static void main(String[] arg) {
		Driver d = new Driver();
	}

	/*
	 * * Used to setup all of the objects on the screen
	 */
	public Driver() {
		JFrame f = new JFrame();
		f.setTitle("Frogger");
		f.setSize(640, 1000);
		f.setResizable(false);
		f.addKeyListener(this); // listen for keypresses on this frame

		// sprite instantiation
		froggy = new Froggy("GoombaChill.png");

		for (int i = 0; i < car1.length; i++) {
			car1[i] = new Car("Mario.png", i * 175, 820);
		}
		for (int i = 0; i < car3.length; i++) {
			car3[i] = new Car("Marg1.png", i * 175, 730);
		}

		for (int i = 0; i < car2.length; i++) {
			car2[i] = new Car("Marg1.png", i * 175, 485);
		}
		for (int i = 0; i < car4.length; i++) {
			car4[i] = new Car("Mario.png", i * 175, 575);
		}

		for (int i = 0; i < log1.length; i++) {
			log1[i] = new Log("TripleLoogi1.png", "TripleLoogi2.png", "TripleLoogi3.png", "TripleLoogi4.png", i * 200,
					325, 0, 2);
		}
		for (int i = 0; i < log2.length; i++) {
			log2[i] = new Log("TripleLoogi1.png", "TripleLoogi2.png", "TripleLoogi3.png", "TripleLoogi4.png", i * 200,
					250, 160, -2);
		}
		for (int i = 0; i < log3.length; i++) {
			log3[i] = new Log("TripleLoogi1.png", "TripleLoogi2.png", "TripleLoogi3.png", "TripleLoogi4.png", i * 200,
					175, 320, 2);
		}
		for (int i = 0; i < log4.length; i++) {
			log4[i] = new Log("TripleLoogi1.png", "TripleLoogi2.png", "TripleLoogi3.png", "TripleLoogi4.png", i * 200,
					100, 0, -2);
		}

		// different backgrounds for win or lose
		bg = new Background("Oi there me laddy we've 'ought the roads now.png");
		winBg = new BackgroundWinner("Fortnite Dubs frfr.png");
		loseBg = new BackgroundLoser("L (1).png");

		f.add(this);
		t = new Timer(17, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {
		// detect up, down, left, right arrow keypresses
		// call setters for volovety attributes accordingly
		// 37 <- ,
		// 38 up ,
		// 40 down,
		// 39 ->
		// froggy movement
		hop.play();
		switch (e.getKeyCode()) {

		case KeyEvent.VK_W: // up
			froggy.hop(0);
			break;

		case KeyEvent.VK_S: // down\
			froggy.hop(1);
			break;

		// handle going left and right
		// A and D keys
		case KeyEvent.VK_A:
			froggy.hop(2);
			break;

		case KeyEvent.VK_D:
			froggy.hop(3);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		/*
		 * turn off velocity for Frog if you don't want it moving when you have stopped
		 * pressing the keys
		 */
		if (e.getKeyCode() == 37) {
			froggy.setVy(0);
			froggy.setVx(0);
		}
		if (e.getKeyCode() == 38) {
			froggy.setVy(0);
			froggy.setVx(0);
		}
		if (e.getKeyCode() == 39) {
			froggy.setVy(0);
			froggy.setVx(0);
		}
		if (e.getKeyCode() == 40) {
			froggy.setVy(0);
			froggy.setVx(0);
		}

		// do the same thing for the other keys
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}