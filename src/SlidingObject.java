public class SlidingObject extends MovingImage implements HitableObject {

	private boolean startedOnLeft;

	public SlidingObject() {
		super();
		int randomNum1 = 40 + (int) (Math.random() * 40) + 1;
		int randomNum2 = (int) (2 * Math.random()) + 1;
		setWidth(randomNum1);
		setHeight(getWidth());
		if (randomNum2 == 1) {
			startedOnLeft = true;
		} else {
			startedOnLeft = false;
		}
		setY(421 - getHeight());
		setVelocity(0);
		if (startedOnLeft) {
			setX(0 - getWidth());
		} else if (!startedOnLeft) {
			setX(ASSUMED_DRAWING_WIDTH);
		}
	}

	public SlidingObject(int w, int h, Soldier s) {
		super();
		int randomNum1 = (int) (2 * Math.random()) + 1;
		setWidth(w);
		setHeight(h);
		if (randomNum1 == 1) {
			startedOnLeft = true;
		} else {
			startedOnLeft = false;
		}
		setY(421 - getHeight());
		setVelocity(0);
		if (s.isOnLeftSideOfScreen() || (s.isOnLeftHalfOfScreen() && s.getWalking() < 0)) {
			startedOnLeft = false;
		} else if (s.isOnRightSideOfScreen() || (s.isOnRightHalfOfScreen() && s.getWalking() > 0)) {
			startedOnLeft = true;
		}
		if (startedOnLeft) {
			setX(0 - getWidth());
		} else {
			setX(ASSUMED_DRAWING_WIDTH);
		}
	}

	public void act(int t, double r) {
		if (startedOnLeft) {
			setX((int) (getX() + r * getVelocity()));
		} else {
			setX((int) (getX() - r * getVelocity()));
		}
	}

	public boolean isHit(Soldier soldier) {
		throw new RuntimeException();
	}
}