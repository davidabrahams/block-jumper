import java.awt.geom.*;

public class Soldier extends MovingImage {
	// FIELDS
	public double walkingSpeed;
	private boolean isFalling;
	private int walking;
	private int jumpsToBeExecuted;
	private int superJumpsToBeExecuted;
	private int timeElapsed;
	private int bullets;
	private int superJumpsRemaining;
	private boolean isLPressed;
	private boolean isRPressed;
	private boolean isUPressed;
	private boolean isDPressed;
	private boolean isSpacePressed;
	private boolean isInvincible;
	private boolean isUltimateInvincible;
	private int timeInvincibilityStarted;
	private int timeUltimateInvincibilityStarted;
	private int explosionsRemaining;

	public Soldier(int x, int y) {
		super("Cartoon Soldier alpha.png", x, y, 51, 84, 0, 2.0);
		timeElapsed = 0;
		walkingSpeed = 8;
		isFalling = true;
		walking = 0;
		jumpsToBeExecuted = 0;
		explosionsRemaining = 0;
		isLPressed = false;
		isRPressed = false;
		isUPressed = false;
		isDPressed = false;
		isSpacePressed = false;
		isInvincible = false;
		isUltimateInvincible = false;
	}

	private void update() {

		if ((isLPressed && isRPressed) || (!isLPressed && !isRPressed)) {
			walking = 0;
		} else if (isLPressed)
			walking = -1;
		else if (isRPressed)
			walking = 1;
		if (isUPressed)
			addJump();
		if (isSpacePressed)
			superJump();

		if (getY() == ASSUMED_DRAWING_HEIGHT - getHeight() - 179) {
			isFalling = false;
			setGravity(2.0);
		} else {
			isFalling = true;
		}
		if (!isFalling) {
			setVelocity(0);
		}
		if (isInvincible && (timeElapsed - timeInvincibilityStarted > 3000)) {
			isInvincible = false;
		}
		if (isUltimateInvincible
				&& (timeElapsed - timeUltimateInvincibilityStarted > 5000)) {
			isUltimateInvincible = false;
		}
		if (isInvincible)
			isUltimateInvincible = false;
		if (isUltimateInvincible)
			isInvincible = false;
	}

	public void setFalling(boolean b) {
		isFalling = b;
	}

	public void move() {
		if (!isFalling && superJumpsToBeExecuted > 0) {
			setVelocity(-18);
			setGravity(0.5);
			isFalling = true;
			superJumpsToBeExecuted = 0;
			jumpsToBeExecuted = 0;
			superJumpsRemaining--;
		} else if (isFalling && getVelocity() < 0 && superJumpsToBeExecuted > 0) {
			setGravity(0.42);
			setVelocity(Math.max(getVelocity() * .7, -14));
			isFalling = true;
			superJumpsToBeExecuted = 0;
			jumpsToBeExecuted = 0;
			superJumpsRemaining--;
		} else if (!isFalling && jumpsToBeExecuted > 0) {
			setVelocity(-28);
			isFalling = true;
			jumpsToBeExecuted = 0;
		}
		if (isFalling) {
			moveByAmount(0, (int) (getVelocity()));
			changeVelocity(getGravity());
		}
		moveByAmount((int) (walkingSpeed * walking), 0);
	}

	public void addJump() {
		if ((jumpsToBeExecuted < 1 && getVelocity() >= 15) || !isFalling) {
			jumpsToBeExecuted++;
		}
	}

	public void act() {
		move();
		update();
	}

	public void setLKey(boolean b) {
		isLPressed = b;
	}

	public void setRKey(boolean b) {
		isRPressed = b;
	}

	public void setUKey(boolean b) {
		isUPressed = b;
	}

	public void setDKey(boolean b) {
		isDPressed = b;
	}

	public void setSpaceKey(boolean b) {
		isSpacePressed = b;
	}

	public boolean isOnLeftSideOfScreen() {
		if ((int) (getX() + getWidth() / 2.0 + 0.5) < ASSUMED_DRAWING_WIDTH / 3) {
			return true;
		}
		return false;
	}

	public boolean isOnRightSideOfScreen() {
		if ((int) (getX() + getWidth() / 2.0 + 0.5) > 2 * ASSUMED_DRAWING_WIDTH / 3) {
			return true;
		}
		return false;
	}

	public boolean isOnLeftHalfOfScreen() {
		if ((int) (getX() + getWidth() / 2.0 + 0.5) < ASSUMED_DRAWING_WIDTH / 2) {
			return true;
		}
		return false;
	}

	public boolean isOnRightHalfOfScreen() {
		if ((int) (getX() + getWidth() / 2.0 + 0.5) > ASSUMED_DRAWING_WIDTH / 2) {
			return true;
		}
		return false;
	}

	public boolean isHit(HitableObject o) {
		if (o != null && o.isHit(this))
			return true;
		return false;
	}

	public Rectangle2D.Double getHitBox() {
		return new Rectangle2D.Double(9 + getX(), 4 + getY(), 30, 80);
	}

	public int getSuperJumpsRemaining() {
		return superJumpsRemaining;
	}

	public void addSuperJump() {
		superJumpsRemaining++;
	}

	public void superJump() {
		if (superJumpsToBeExecuted < 1 && superJumpsRemaining > 0
				&& getGravity() == 2.0) {
			superJumpsToBeExecuted++;
		}
	}

	public void setSuperSpeed(double d) {
		walkingSpeed = d;
	}

	public void increaseTimeElpased(int i) {
		timeElapsed += i;
		if (walkingSpeed > 8) {
			walkingSpeed -= i * .0024;
		} else
			walkingSpeed = 8;
	}

	public boolean isInvincibile() {
		if (isUltimateInvincible == true || isInvincible == true) {
			return true;
		}
		return false;
	}

	public void setInvincibility(boolean b) {
		isInvincible = b;
		timeInvincibilityStarted = timeElapsed;
	}

	public void setUltimateInvincibility(boolean b) {
		isUltimateInvincible = b;
		isInvincible = false;
		timeUltimateInvincibilityStarted = timeElapsed;
	}

	public int getTimeElapsed() {
		return timeElapsed;
	}

	public int ultimateInvincibilityRemaining() {
		if (isUltimateInvincible)
			return 5000 - timeElapsed + timeUltimateInvincibilityStarted;
		else
			return 0;
	}

	public void addBullets(int i) {
		bullets += i;
	}

	public int getBullets() {
		return bullets;
	}

	public void addExplosion(int i) {
		explosionsRemaining += i;
	}

	public int explosionsRemaining() {
		return explosionsRemaining;
	}

	public int getWalking() {
		return walking;
	}
}