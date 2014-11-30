import java.awt.*;
import java.awt.image.*;

public class UltimateForceField extends MovingImage {

	private int timeStarted;
	private int timeElapsedSinceCreated;

	public UltimateForceField(Soldier s) {
		super("UltimateForceField.png", s.getX() + s.getWidth() / 2 - 85, s
				.getY() + s.getHeight() / 2 - 85, 170, 170);
		timeStarted = s.getTimeElapsed();

	}

	public void draw(Graphics g, ImageObserver io) {
		super.draw(g, io);
	}

	public void update(Soldier s) {
		timeElapsedSinceCreated = s.getTimeElapsed() - timeStarted;
		setX(s.getX() + s.getWidth() / 2 - 85);
		setY(s.getY() + s.getHeight() / 2 - 85);
		if ((timeElapsedSinceCreated > 3000 && timeElapsedSinceCreated < 3100)
				|| (timeElapsedSinceCreated > 3200 && timeElapsedSinceCreated < 3300)
				|| (timeElapsedSinceCreated > 3400 && timeElapsedSinceCreated < 3500)
				|| (timeElapsedSinceCreated > 3600 && timeElapsedSinceCreated < 3700)
				|| (timeElapsedSinceCreated > 3800 && timeElapsedSinceCreated < 3900)
				|| (timeElapsedSinceCreated > 4000 && timeElapsedSinceCreated < 4050)
				|| (timeElapsedSinceCreated > 4100 && timeElapsedSinceCreated < 4150)
				|| (timeElapsedSinceCreated > 4200 && timeElapsedSinceCreated < 4250)
				|| (timeElapsedSinceCreated > 4300 && timeElapsedSinceCreated < 4350)
				|| (timeElapsedSinceCreated > 4400 && timeElapsedSinceCreated < 4450)
				|| (timeElapsedSinceCreated > 4500 && timeElapsedSinceCreated < 4550)
				|| (timeElapsedSinceCreated > 4600 && timeElapsedSinceCreated < 4650)
				|| (timeElapsedSinceCreated > 4700 && timeElapsedSinceCreated < 4750)
				|| (timeElapsedSinceCreated > 4800 && timeElapsedSinceCreated < 4850)
				|| (timeElapsedSinceCreated > 4900 && timeElapsedSinceCreated < 4950)) {
			setImg(null);
		} else {
			setImgString("UltimateForceField.png");
		}
		if (timeElapsedSinceCreated > 5000) {
			setImg(null);
		}
	}

}