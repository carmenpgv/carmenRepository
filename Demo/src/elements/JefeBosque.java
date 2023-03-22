package elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import game.Parametros;
import managers.AudioManager;
import screens.GameScreen;

public class JefeBosque extends Enemy {
	private Animation walk;
	private Animation run;
	private Animation death;
	private Animation idle;
	private Animation walkD;
	private Animation runD;
	private Animation deathD;
	private Animation idleD;

	private Element pie;
	private Element cabeza;
	private Element sensor;
	private Element cuerpo;
	private int direccion;
	private float velocidadWalk = 45;
	private float velocidadRun = 150;
	private float velocidad;
	private boolean pisa;
	private boolean choca;
	private boolean corro;
	private float duracionMuerte = 1.5f;
	private float tiempoMuerte;
	private float danoIni;
	private boolean overlap;

	public JefeBosque(float x, float y, Stage s, GameScreen nivel, int dano) {
		super(x, y, s, nivel);
		// TODO Auto-generated constructor stub
		walk = loadFullAnimation("enemies/jefeBosqueWalk.png", 1, 6, 0.15f, true);
		run = loadFullAnimation("enemies/jefeBosqueRun.png", 1, 6, 0.05f, true);
		death = loadFullAnimation("enemies/jefeBosqueDeath.png", 1, 6, 0.15f, false);
		idle = loadFullAnimation("enemies/jefeBosqueIdle.png", 1, 4, 0.15f, true);
		walkD = loadFullAnimation("enemies/jefeBosqueWalkD.png", 1, 6, 0.15f, true);
		runD = loadFullAnimation("enemies/jefeBosqueRunD.png", 1, 6, 0.05f, true);
		deathD = loadFullAnimation("enemies/jefeBosqueDeathD.png", 1, 6, 0.15f, false);
		idleD = loadFullAnimation("enemies/jefeBosqueIdleD.png", 1, 4, 0.15f, true);
		this.setPolygon(5, this.getWidth() * 4 / 5, this.getHeight() * 3 / 5, this.getWidth() / 10, 0);
		this.danoIni = dano;

		direccion = -1;

		pie = new Element(0, 0, s, this.getWidth() / 4, this.getHeight() / 4);
		pie.setRectangle();
		ajustarPie();

		cabeza = new Element(0, 0, s, this.getWidth() / 4, this.getHeight() / 4);
		cabeza.setRectangle();
		ajustarCabeza();
		sensor = new Element(0, 0, s, 500, 6);
		sensor.setRectangle();
		ajustarSensor();

		velocidad = velocidadWalk;
		vida = 15;
		corro = false;
		tiempoMuerte = -1;
		overlap = false;
	}

	public void act(float delta) {
		super.act(delta);

		moveBy(direccion * velocidad * delta, 0);
		ajustarPie();
		ajustarCabeza();
		ajustarSensor();
		comprobarColisiones();
		morir(delta);
		animaciones();
		moverse();
		this.dano = danoIni / Parametros.reduccionEnemy;
	}

	private void morir(float delta) {
		if (tiempoMuerte > duracionMuerte) {
			this.setEnabled(false);
			this.cabeza.setEnabled(false);
		} else if (tiempoMuerte >= 0) {
			tiempoMuerte += delta;
		}
		if (this.tiempoMuerte < 0) {
			if (this.vida <= 0) {
				this.animationTime = 0;
				this.tiempoMuerte = 0;
				this.dana = false;
			}
		}
	}

	private void moverse() {
		if (tiempoMuerte < 0) {
			if (corro) {
				this.velocidad = velocidadRun;
			} else {
				this.velocidad = velocidadWalk;
			}
		} else if (tiempoMuerte == 0) {
			this.velocidad = 0;
		}
	}

	private void comprobarColisiones() {
		pisa = false;
		choca = false;
		for (Solid solido : nivel.solidos) {
			if (pie.overlaps(solido)) {
				pisa = true;
			}
			if (cabeza.overlaps(solido)) {
				choca = true;
			}
		}
		for (Solid plat : nivel.plataformasSolid) {
			if (pie.overlaps(plat)) {
				pisa = true;
			}
			if (cabeza.overlaps(plat)) {
				choca = true;
			}
		}
		if (this.sensor.overlaps(nivel.player)) {
			if (!overlap) {
				AudioManager.playSound("audio/sounds/roar.mp3");
				overlap = true;
			}
			corro = true;
		} else {
			overlap = false;
		}
		if (!pisa || choca) {
			this.direccion *= -1;
			corro = false;
		}

	}

	private void animaciones() {
		if (this.getEnabled()) {
			if (direccion == -1) {
				if (tiempoMuerte < 0) {
					if (corro) {
						this.setAnimation(run);
					} else {
						this.setAnimation(walk);
					}
				} else if (tiempoMuerte > 0) {
					this.setAnimation(death);
				} else {
					this.setAnimation(idle);
				}
			} else {
				if (tiempoMuerte < 0) {
					if (corro) {
						this.setAnimation(runD);
					} else {
						this.setAnimation(walkD);
					}
				} else if (tiempoMuerte > 0) {
					this.setAnimation(deathD);
					AudioManager.playSound("audio/sounds/roardeath.mp3");
				} else {
					this.setAnimation(idleD);
					AudioManager.playSound("audio/sounds/roardeath.mp3");
				}
			}
		}
	}

	private void ajustarPie() {
		if (direccion == -1) {
			pie.setPosition(this.getX(), this.getY() - this.getHeight() / 8);
		} else {
			pie.setPosition(this.getX() + this.getWidth() * 3 / 4, this.getY() - this.getHeight() / 8);
		}

	}

	private void ajustarCabeza() {
		if (direccion == -1) {
			cabeza.setPosition(this.getX() + this.getWidth() * 1 / 4, this.getY() + this.getHeight() * 1 / 10);
		} else {
			cabeza.setPosition(this.getX() + this.getWidth() * 2 / 4, this.getY() + this.getHeight() * 1 / 10);
		}

	}

	private void ajustarSensor() {
		if (direccion == 1) {
			sensor.setPosition(this.getX() + this.getWidth() / 2, this.getY() + 45);
		} else {
			sensor.setPosition(this.getX() - 452, this.getY() + 45);
		}
	}

}
