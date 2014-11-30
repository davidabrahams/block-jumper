import java.awt.*;
import java.awt.image.*;

public class ForceField extends MovingImage {

	private int timeStarted;
	private int timeElapsedSinceCreated;

	public ForceField(Soldier s) {
		super("ForceField.png", s.getX() + s.getWidth() / 2 - 60, s.getY()
				+ s.getHeight() / 2 - 60, 120, 120);
		timeStarted = s.getTimeElapsed();

	}

	public void draw(Graphics g, ImageObserver io) {
		super.draw(g, io);
	}

	public void update(Soldier s) {
		timeElapsedSinceCreated = s.getTimeElapsed() - timeStarted;
		setX(s.getX() + s.getWidth() / 2 - 60);
		setY(s.getY() + s.getHeight() / 2 - 60);
		if ((timeElapsedSinceCreated > 1400 && timeElapsedSinceCreated < 1500)
				|| (timeElapsedSinceCreated > 1700 && timeElapsedSinceCreated < 1800)
				|| (timeElapsedSinceCreated > 2000 && timeElapsedSinceCreated < 2100)
				|| (timeElapsedSinceCreated > 2200 && timeElapsedSinceCreated < 2300)
				|| (timeElapsedSinceCreated > 2400 && timeElapsedSinceCreated < 2500)
				|| (timeElapsedSinceCreated > 2550 && timeElapsedSinceCreated < 2600)
				|| (timeElapsedSinceCreated > 2650 && timeElapsedSinceCreated < 2700)
				|| (timeElapsedSinceCreated > 2750 && timeElapsedSinceCreated < 2800)
				|| (timeElapsedSinceCreated > 2850 && timeElapsedSinceCreated < 2900)
				|| (timeElapsedSinceCreated > 2950 && timeElapsedSinceCreated < 3000)) {
			setImg(null);
		} else {
			setImgString("ForceField.png");
		}
		if (timeElapsedSinceCreated > 3000) {
			setImg(null);
		}
	}

}