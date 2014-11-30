import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

public class MovingImage {

	public static final int ASSUMED_DRAWING_WIDTH = 800;
	public static final int ASSUMED_DRAWING_HEIGHT = 600;
	private static double ratioX, ratioY;
	private double gravity;
	private double velocity;

	private Image img;
	private boolean isVisible;
	private int xCoord, yCoord;
	private int width, height;

	public MovingImage() {
		gravity = 0;
		velocity = 0;
		img = null;
		isVisible = true;
		xCoord = 0;
		yCoord = 0;
		width = 0;
		height = 0;
	}

	public MovingImage(String filename, int x, int y, int w, int h) {
		img = (new ImageIcon(filename)).getImage();
		isVisible = true;
		xCoord = x;
		yCoord = y;
		width = w;
		height = h;
		gravity = 0.9;
		velocity = 0;
	}

	public MovingImage(int x, int y, int w, int h) {
		img = null;
		xCoord = x;
		yCoord = y;
		width = w;
		height = h;
		gravity = 0;
		velocity = 0;
	}

	public MovingImage(String filename, int x, int y, int w, int h, double vel,
			double grav) {
		img = (new ImageIcon(filename)).getImage();
		isVisible = true;
		xCoord = x;
		yCoord = y;
		width = w;
		height = h;
		gravity = grav;
		velocity = vel;
	}

	// STATIC METHODS
	/*
	 * Call this method any time there is a possibility that the window has
	 * changed size!
	 */
	public static void setActualPanelSize(int width, int height) {
		ratioX = (double) width / ASSUMED_DRAWING_WIDTH;
		ratioY = (double) height / ASSUMED_DRAWING_HEIGHT;
	}

	public static Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int) (assumed.getX() * ratioX),
				(int) (assumed.getY() * ratioY));
	}

	public static Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int) (actual.getX() / ratioX),
				(int) (actual.getY() / ratioY));
	}

	public void moveToLocation(int x, int y) {
		xCoord = x;
		yCoord = y;
	}

	public void moveByAmount(int x, int y) {
		xCoord += x;
		yCoord += y;
		if (xCoord < 0)
			xCoord = 0;
		if (yCoord < 0)
			yCoord = 0;

		if (xCoord + width > ASSUMED_DRAWING_WIDTH)
			xCoord = ASSUMED_DRAWING_WIDTH - width;
		if (yCoord + height > ASSUMED_DRAWING_HEIGHT - 179)
			yCoord = ASSUMED_DRAWING_HEIGHT - height - 179;
	}

	public void setWidth(int w) {
		width = w;
	}

	public void setHeight(int h) {
		height = h;
	}

	public int getX() {
		return xCoord;
	}

	public int getY() {
		return yCoord;
	}

	public void setX(int x) {
		xCoord = x;
	}

	public void setY(int y) {
		yCoord = y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void swapVisibility() {
		isVisible = !isVisible;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setGravity(double g) {
		gravity = g;
	}

	public double getGravity() {
		return gravity;
	}

	public void changeVelocity(double v) {
		velocity += v;
	}

	public void setVelocity(double v) {
		velocity = v;
	}

	public double getVelocity() {
		return velocity;
	}

	public double getRatioX() {
		return ratioX;
	}

	public double getRatioY() {
		return ratioY;
	}

	public void draw(Graphics g, ImageObserver io) {
		if (isVisible) {
			g.drawImage(img, (int) (xCoord * ratioX), (int) (yCoord * ratioY),
					(int) (width * ratioX), (int) (height * ratioY), io);
		}
	}

	public boolean isPointInsideImage(double x, double y) {
		if (x >= xCoord && x <= xCoord + width && y >= yCoord
				&& y <= yCoord + height)
			return true;
		return false;
	}

	public void setImgString(String filename) {
		img = (new ImageIcon(filename)).getImage();
	}

	public void setImg(Image i) {
		img = i;
	}

}