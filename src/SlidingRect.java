import java.awt.*;
import java.awt.geom.*;

public class SlidingRect extends SlidingObject {
	private int area;
	private Color color;

	public SlidingRect(Soldier s) {
		super(40 + (int) (Math.random() * 40) + 1,
				40 + (int) (Math.random() * 40) + 1, s);
		setY(421 - getHeight());
		area = getWidth() * getHeight();
		setVelocity((int) (11 - 9 * ((area - 1600.0) / 4800.0)));
		if (area < 2849)
			color = new Color(238, 238, 0);
		else if (area < 4444)
			color = new Color(255, 24, 24);
		else
			color = new Color(0, 0, 230);
	}

	public boolean isHit(Soldier soldier) {
		boolean isXCollision = false;
		int soldierMidX = (int) (soldier.getX() + 24);
		if (soldierMidX > getX() && soldierMidX < getX() + getWidth()) {
			isXCollision = true;
		}
		boolean isYCollision = false;
		int soldierBottomY = soldier.getY() + 84;
		if (soldierBottomY > getY()) {
			isYCollision = true;
		}
		if (isXCollision && isYCollision) {
			return true;
		}
		return false;
	}

	public boolean isHit(Bullet b) {
		if (getHitBox().intersects(b.getHitBox())) {
			return true;
		}
		return false;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect((int) (getRatioX() * getX()), (int) (getRatioY() * getY()),
				(int) (getRatioX() * getWidth()),
				(int) (getRatioY() * getHeight()));
	}

	public void shrink() {
		setWidth(getWidth() / 2);
		setHeight(getHeight() / 2);
		setY(421 - getHeight());
		setX((int) (getX() + getWidth() / 2));
	}

	public Rectangle2D.Double getHitBox() {
		return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
	}
}