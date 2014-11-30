import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

public abstract class PowerUp extends MovingImage implements HitableObject {

	public PowerUp(String filename) {
		super(filename, (int) (Math.random() * (ASSUMED_DRAWING_WIDTH - 50)),
				-50, 50, 50);
		setVelocity(4.0);
	}

	public void draw(Graphics g, ImageObserver io) {
		super.draw(g, io);
	}

	public void act() {
		setY((int) (getY() + getVelocity()));
	}

	public boolean isHit(Soldier s) {
		Ellipse2D.Double e = new Ellipse2D.Double(getX(), getY(), getWidth(),
				getHeight());
		return (e.intersects(s.getHitBox()));
	}

	public abstract String getPowerUpName();
}