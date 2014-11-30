import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Window extends JPanel implements KeyListener {
	private MovingImage grass;
	private MovingImage sky;
	private Soldier soldier;
	private SlidingRect[] slidingRects;
	private PowerUp[] powerUps;
	private ExplodeBonus[] explodeBonuses;
	private DestroyBonus[] destroyBonuses;
	private ForceField forceField;
	private int bulletsInt;
	private int bonusInt;
	private int explodeBonusInt;
	private UltimateForceField ultimateForceField;
	private KaBoom kaBoom;
	private Bullet[] bullets;
	private static boolean isGameOver;
	private static boolean isGamePaused;
	private double score;
	private int blocksEncountered;
	private int timeElapsed;
	private double probability;
	private int highScore;
	private double rateOfTime;

	public Window() {
		super();
		grass = new MovingImage("GrassCropped.png", 0, 420, 800, 181);
		sky = new MovingImage("SkyCartoon.png", 0, 0, 800, 492);
		setBackground(Color.WHITE);
		forceField = null;
		ultimateForceField = null;
		kaBoom = null;
		soldier = new Soldier(390, 0);
		slidingRects = new SlidingRect[15];
		powerUps = new PowerUp[10];
		bullets = new Bullet[10];
		destroyBonuses = new DestroyBonus[10];
		explodeBonuses = new ExplodeBonus[10];
		isGameOver = false;
		isGamePaused = false;
		timeElapsed = 0;
		blocksEncountered = 0;
		score = 0;
		probability = 0.2;
		highScore = 0;
		rateOfTime = 1.0;
		bulletsInt = 0;
		bonusInt = 0;
		explodeBonusInt = 0;
	}

	public void resetGame() {
		soldier = new Soldier(390, 0);
		slidingRects = new SlidingRect[15];
		powerUps = new PowerUp[10];
		destroyBonuses = new DestroyBonus[10];
		explodeBonuses = new ExplodeBonus[10];
		isGameOver = false;
		isGamePaused = false;
		kaBoom = null;
		forceField = null;
		timeElapsed = 0;
		blocksEncountered = 0;
		score = 0;
		probability = 0.2;
		rateOfTime = 1.0;
		forceField = null;
		ultimateForceField = null;
		bulletsInt = 0;
		bonusInt = 0;
		explodeBonusInt = 0;
	}

	public void setHighScore() {
		if ((int) score > highScore) {
			highScore = (int) score;
		}
	}

	public double getRateOfTime() {
		return rateOfTime;
	}

	public void setRateOfTime(double d) {
		rateOfTime = d;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g); // Call JPanel's paintComponent method to paint
		// the background
		double ratioX = (getWidth() + 0.0) / MovingImage.ASSUMED_DRAWING_WIDTH;
		double ratioY = (getHeight() + 0.0)
				/ MovingImage.ASSUMED_DRAWING_HEIGHT;
		MovingImage.setActualPanelSize(getWidth(), getHeight());
		sky.draw(g, this);
		for (int i = 0; i < powerUps.length; i++) {
			if (powerUps[i] != null) {
				powerUps[i].draw(g, this);
			}
		}
		for (int i = 0; i < slidingRects.length; i++) {
			if (slidingRects[i] != null) {
				slidingRects[i].draw(g);
			}
		}
		for (int i = 0; i < bullets.length; i++) {
			if (bullets[i] != null) {
				bullets[i].draw(g);
			}
		}
		for (int i = 0; i < destroyBonuses.length; i++) {
			if (destroyBonuses[i] != null) {
				destroyBonuses[i].draw(g, this);
			}
		}
		for (int i = 0; i < explodeBonuses.length; i++) {
			if (explodeBonuses[i] != null) {
				explodeBonuses[i].draw(g, this);
			}
		}
		g.setColor(Color.BLACK);
		if (isGameOver) {
			g.drawImage((new ImageIcon("GameOver.png")).getImage(),
					(int) (449 * ratioX), (int) (8 * ratioY),
					(int) (340 * ratioX), (int) (112 * ratioY), this);

		}
		g.setFont(new Font("MONOSPACED", Font.PLAIN, (int) (20 * ratioX)));
		g.drawString("Score: " + (int) (score), (int) (10 * ratioX),
				(int) (30 * ratioY));
		g.drawString("Blocks faced: " + blocksEncountered, (int) (10 * ratioX),
				(int) (60 * ratioY));
		g.drawString("Current Session High Score: " + highScore,
				(int) (10 * ratioX), (int) (120 * ratioY));

		g.drawString("Time Elapsed: " + (int) (timeElapsed / 1000.0)
				+ " seconds", (int) (10 * ratioX), (int) (90 * ratioY));
		if (!isGameOver) {
			int q = 1;
			while (q <= Math.min(soldier.getSuperJumpsRemaining(), 8)) {
				g.drawImage((new ImageIcon("SuperJump.png")).getImage(),
						(int) ((761 - 35 * (q - 1)) * ratioX),
						(int) (46 * ratioY), (int) (30 * ratioX),
						(int) (30 * ratioY), this);
				q += 1;
			}
			if (soldier.getSuperJumpsRemaining() == 0) {
				g.drawString("Super Jumps (Space): ", (int) (561 * ratioX),
						(int) (70 * ratioY));
			} else {
				g.drawString("Super Jumps (Space): ",
						(int) ((556 - 35 * (q - 1)) * ratioX),
						(int) (70 * ratioY));
			}
			if (soldier.getBullets() == 0) {
				g.drawString("Bullets (X): " + soldier.getBullets(),
						(int) (654 * ratioX), (int) (100 * ratioY));
			} else if (soldier.getBullets() < 10) {
				g.drawString("Bullets (X): " + soldier.getBullets(),
						(int) (631 * ratioX), (int) (100 * ratioY));
			} else {
				g.drawString("Bullets (X): " + soldier.getBullets(),
						(int) (619 * ratioX), (int) (100 * ratioY));
			}
			int w = 1;
			while (w <= Math.min(soldier.explosionsRemaining(), 6)) {
				g.drawImage((new ImageIcon("Explosion.png")).getImage(),
						(int) ((760 - 35 * (w - 1)) * ratioX),
						(int) (106 * ratioY), (int) (30 * ratioX),
						(int) (30 * ratioY), this);
				w += 1;
			}
			if (soldier.explosionsRemaining() == 0) {
				g.drawString("Explosions (Z): ",
						(int) ((619 - 35 * (w - 1)) * ratioX),
						(int) (130 * ratioY));
			} else {
				g.drawString("Explosions (Z): ",
						(int) ((617 - 35 * (w - 1)) * ratioX),
						(int) (130 * ratioY));
			}
			if (!isGamePaused) {
				g.drawImage((new ImageIcon("PressPToPause.png")).getImage(),
						(int) (560 * ratioX), (int) (10 * ratioY),
						(int) (234 * ratioX), (int) (34 * ratioY), this);
			} else {
				g.drawImage((new ImageIcon("PressPToUnpause.png")).getImage(),
						(int) (536 * ratioX), (int) (10 * ratioY),
						(int) (259 * ratioX), (int) (34 * ratioY), this);
			}

		}
		if (kaBoom != null) {
			kaBoom.draw(g, this);
		}
		if (forceField != null) {
			forceField.draw(g, this);
		}
		if (ultimateForceField != null) {
			ultimateForceField.draw(g, this);
		}
		grass.draw(g, this);
		soldier.draw(g, this);

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			soldier.setLKey(true);
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			soldier.setRKey(true);
		if (e.getKeyCode() == KeyEvent.VK_UP)
			soldier.setUKey(true);
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			soldier.setDKey(true);
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			soldier.setSpaceKey(true);
		if (e.getKeyCode() == KeyEvent.VK_Z
				&& soldier.explosionsRemaining() > 0 && kaBoom == null
				&& !isGamePaused && !isGameOver) {
			kaBoom = new KaBoom(soldier);
			soldier.addExplosion(-1);
		}
		if (e.getKeyCode() == KeyEvent.VK_X) {
			if (soldier.getBullets() > 0 && !isGamePaused && !isGameOver) {
				if (bulletsInt >= bullets.length) {
					bulletsInt = 0;
				}
				bullets[bulletsInt] = new Bullet(soldier);
				bulletsInt++;
				soldier.addBullets(-1);
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			soldier.setLKey(false);
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			soldier.setRKey(false);
		if (e.getKeyCode() == KeyEvent.VK_UP)
			soldier.setUKey(false);
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			soldier.setDKey(false);
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			soldier.setSpaceKey(false);
		if (e.getKeyCode() == KeyEvent.VK_R && isGameOver)
			resetGame();
		if (e.getKeyCode() == KeyEvent.VK_P && !isGameOver) {
			isGamePaused = !isGamePaused;
		}
	}

	public void giveSoldierPowerUp(PowerUp p) {
		if (p.getPowerUpName().equals("SuperJump"))
			soldier.addSuperJump();
		else if (p.getPowerUpName().equals("PointsIncrease2500"))
			score += 2500;
		else if (p.getPowerUpName().equals("PointsIncrease5000"))
			score += 5000;
		else if (p.getPowerUpName().equals("HyperSpeed"))
			soldier.setSuperSpeed(20.0);
		else if (p.getPowerUpName().equals("Invincibility")) {
			if (soldier.ultimateInvincibilityRemaining() < 3000) {
				soldier.setInvincibility(true);
				forceField = new ForceField(soldier);
				ultimateForceField = null;
			}
		} else if (p.getPowerUpName().equals("UltimateInvincibility")) {
			soldier.setUltimateInvincibility(true);
			ultimateForceField = new UltimateForceField(soldier);
			forceField = null;
		} else if (p.getPowerUpName().equals("SlowDownTime")) {
			rateOfTime = 0.2;
		} else if (p.getPowerUpName().equals("DestroyAllBlocks")) {
			slidingRects = new SlidingRect[15];
		} else if (p.getPowerUpName().equals("ShrinkAllBlocks")) {
			for (int i = 0; i < slidingRects.length; i++) {
				if (slidingRects[i] != null)
					slidingRects[i].shrink();
			}
		} else if (p.getPowerUpName().equals("Bullets")) {
			soldier.addBullets(5);
		} else if (p.getPowerUpName().equals("Explosion")) {
			soldier.addExplosion(1);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void run() {
		while (true) {
			int i = 0;
			int l = 0;
			while (!isGameOver) {
				long startRunTime = System.currentTimeMillis();
				repaint();
				if (!isGamePaused) {
					if (timeElapsed % 300 == 0
							&& Math.random() < (rateOfTime * probability)
							&& timeElapsed > 2000) {
						if (i > slidingRects.length - 1) {
							i = 0;
						}
						slidingRects[i] = new SlidingRect(soldier);
						score += 500;
						blocksEncountered++;
						i++;
					}
					if (timeElapsed > 5000 && timeElapsed % 300 == 0
							&& Math.random() < .04) {
						powerUps[l] = new PointsIncrease2500();
						l++;
						if (l >= powerUps.length - 1) {
							l = 0;
						}
					}
					if (timeElapsed > 7500 && timeElapsed % 300 == 0
							&& Math.random() < .04) {
						powerUps[l] = new SuperJump();
						l++;
						if (l > powerUps.length - 1) {
							l = 0;
						}
					}
					if (timeElapsed > 10000 && timeElapsed % 300 == 0
							&& Math.random() < .025) {
						powerUps[l] = new Bullets();
						l++;
						if (l > powerUps.length - 1) {
							l = 0;
						}
					}
					if (timeElapsed > 12500 && timeElapsed % 300 == 0
							&& Math.random() < .02) {
						powerUps[l] = new PointsIncrease5000();
						l++;
						if (l > powerUps.length - 1) {
							l = 0;
						}
					}
					if (timeElapsed > 15000 && timeElapsed % 300 == 0
							&& Math.random() < .02) {
						powerUps[l] = new Invincibility();
						l++;
						if (l > powerUps.length - 1) {
							l = 0;
						}
					}
					if (timeElapsed > 17500 && timeElapsed % 300 == 0
							&& Math.random() < .025) {
						powerUps[l] = new HyperSpeed();
						l++;
						if (l > powerUps.length - 1) {
							l = 0;
						}
					}
					if (timeElapsed > 20000 && timeElapsed % 300 == 0
							&& Math.random() < .025) {
						powerUps[l] = new Explosion();
						l++;
						if (l >= powerUps.length - 1) {
							l = 0;
						}
					}
					if (timeElapsed > 22500 && timeElapsed % 300 == 0
							&& Math.random() < .02) {
						powerUps[l] = new SlowDownTime();
						l++;
						if (l > powerUps.length - 1) {
							l = 0;
						}
					}
					if (timeElapsed > 25000 && timeElapsed % 300 == 0
							&& Math.random() < .015) {
						powerUps[l] = new ShrinkAllBlocks();
						l++;
						if (l > powerUps.length - 1) {
							l = 0;
						}
					}
					if (timeElapsed > 27500 && timeElapsed % 300 == 0
							&& Math.random() < .015) {
						powerUps[l] = new DestroyAllBlocks();
						l++;
						if (l > powerUps.length - 1) {
							l = 0;
						}
					}
					if (timeElapsed > 30000 && timeElapsed % 300 == 0
							&& Math.random() < .01) {
						powerUps[l] = new UltimateInvincibility();
						l++;
						if (l > powerUps.length - 1) {
							l = 0;
						}
					}
					soldier.act();
					for (int j = 0; j < bullets.length; j++) {
						if (bullets[j] != null) {
							bullets[j].act();
						}
					}
					if (kaBoom != null) {
						kaBoom.update(soldier);
						for (int qxyz = 0; qxyz < slidingRects.length; qxyz++) {
							if (slidingRects[qxyz] != null
									&& kaBoom.getHitCircle().intersects(
											slidingRects[qxyz].getHitBox())) {
								if (explodeBonusInt > explodeBonuses.length - 1) {
									explodeBonusInt = 0;
								}
								explodeBonuses[explodeBonusInt] = new ExplodeBonus(
										slidingRects[qxyz], timeElapsed);
								explodeBonusInt++;
								slidingRects[qxyz] = null;
								score += 250;
							}
						}

					}
					if (kaBoom != null && kaBoom.getTimeSinceCreated() > 250) {
						kaBoom = null;
					}
					for (int y = 0; y < destroyBonuses.length; y++) {
						if (destroyBonuses[y] != null
								&& destroyBonuses[y]
										.elapsedSinceCreated(timeElapsed) > 220) {
							destroyBonuses[y] = null;
						}
					}
					for (int y = 0; y < explodeBonuses.length; y++) {
						if (explodeBonuses[y] != null
								&& explodeBonuses[y]
										.elapsedSinceCreated(timeElapsed) > 300) {
							explodeBonuses[y] = null;
						}
					}
					if (forceField != null) {
						forceField.update(soldier);
					}
					if (ultimateForceField != null) {
						ultimateForceField.update(soldier);
					}
					for (int j = 0; j < slidingRects.length; j++) {
						if (slidingRects[j] != null) {
							slidingRects[j].act(timeElapsed, rateOfTime);
						}
					}
					for (int k = 0; k < powerUps.length; k++) {
						if (powerUps[k] != null) {
							powerUps[k].act();
						}
					}
					for (int j = 0; j < slidingRects.length; j++) {
						for (int k = 0; k < bullets.length; k++) {
							if ((slidingRects[j] != null)
									&& (bullets[k] != null)) {
								if (slidingRects[j].isHit(bullets[k])) {
									if (bonusInt > destroyBonuses.length - 1) {
										bonusInt = 0;
									}
									destroyBonuses[bonusInt] = new DestroyBonus(
											slidingRects[j], timeElapsed);
									bonusInt++;
									slidingRects[j] = null;
									score += 500;
								}
							}
						}
					}
					for (int k = 0; k < powerUps.length; k++) {
						if (powerUps[k] != null) {
							if (soldier.isHit(powerUps[k])) {
								giveSoldierPowerUp(powerUps[k]);
								powerUps[k] = null;
							}
						}
					}
					if (!soldier.isInvincibile()) {
						for (int j = 0; j < slidingRects.length; j++) {
							if (slidingRects[j] != null) {
								if (soldier.isHit(slidingRects[j])) {
									isGameOver = true;
								}
							}
						}
					}
					if (!isGamePaused) {
						score += (6.6667 + (timeElapsed + 0.0) / 9000.0);
						timeElapsed += 20;
						soldier.increaseTimeElpased(20);
						if (probability < 0.4) {
							probability += .00006667;
						} else {
							probability = 0.4;
						}
					}
					if (rateOfTime < 1.0)
						rateOfTime += .005333;
					else
						rateOfTime = 1.0;
					if (System.currentTimeMillis() - startRunTime < 20) {
						try {
							Thread.sleep(20 - (System.currentTimeMillis() - startRunTime));
						} catch (InterruptedException e) {
						}
					} else
						Thread.yield();
				}
				else {
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
					}
				}

			}
			setHighScore();
			repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
		}
	}

}
