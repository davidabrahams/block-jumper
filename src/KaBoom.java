import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

public class KaBoom extends MovingImage {

	private int timeStarted;
	private int timeElapsedSinceCreated;

	public KaBoom(Soldier s) {
		super("KaBoom.png", s.getX() + s.getWidth() / 2 - 275, s.getY()
				+ s.getHeight() / 2 - 275, 550, 550);
		timeStarted = s.getTimeElapsed();

	}

	public void draw(Graphics g, ImageObserver io) {
		super.draw(g, io);
	}

	public void update(Soldier s) {
		timeElapsedSinceCreated = s.getTimeElapsed() - timeStarted;
		setX(s.getX() + s.getWidth() / 2 - 275);
		setY(s.getY() + s.getHeight() / 2 - 275);
		if ((timeElapsedSinceCreated > 25 && timeElapsedSinceCreated < 50)
				|| (timeElapsedSinceCreated > 75 && timeElapsedSinceCreated < 100)
				|| (timeElapsedSinceCreated > 125 && timeElapsedSinceCreated < 150)
				|| (timeElapsedSinceCreated > 175 && timeElapsedSinceCreated < 200)
				|| (timeElapsedSinceCreated > 225 && timeElapsedSinceCreated < 250)) {
			setImg(null);
		} else {
			setImgString("KaBoom.png");
		}
		if (timeElapsedSinceCreated > 250) {
			setImg(null);
		}
	}

	public int getTimeSinceCreated() {
		return timeElapsedSinceCreated;
	}

	public Ellipse2D.Double getHitCircle() {
		return new Ellipse2D.Double(getX(), getY(), getWidth(), getHeight());
	}
}