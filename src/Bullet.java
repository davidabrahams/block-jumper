import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.*;

public class Bullet extends MovingImage {

	public Bullet(Soldier s) {
		super((int) (s.getX() + 24), s.getY(), 3, 120);
		setVelocity(45.0);
	}

	public void act() {
		setY((int) (getY() + getVelocity()));
	}

	public void draw(Graphics g) {
		g.setColor(new Color(0, 255, 127));
		g.fillRect((int) (getRatioX() * getX()), (int) (getRatioY() * getY()),
				(int) (getRatioX() * getWidth()),
				(int) (getRatioY() * getHeight()));
	}
	
	public Rectangle2D.Double getHitBox () {
		return new Rectangle2D.Double(getX()-2, getY(), 7, getHeight());
	}
}