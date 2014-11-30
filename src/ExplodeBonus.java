import java.awt.*;
import java.awt.image.*;

public class ExplodeBonus extends MovingImage {

	private int timeStarted;

	public ExplodeBonus(SlidingRect s, int startTime) {
		super("+250.png", s.getX() + s.getWidth() / 2 - 41, s.getY()
				+ s.getHeight() / 2 - 13, 82, 26);
		timeStarted = startTime;

	}

	public void draw(Graphics g, ImageObserver io) {
		super.draw(g, io);
	}

	public int elapsedSinceCreated(int currentTime) {
		return (currentTime - timeStarted);
	}

}