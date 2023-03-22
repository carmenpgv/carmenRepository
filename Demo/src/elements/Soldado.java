package elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import game.Parametros;
import managers.AudioManager;
import screens.GameScreen;

public class Soldado extends Enemy {
	private Animation pieD;
	private Animation pieI;
	private Animation sitD;
	private Animation sitI;
	private Animation deathD;
	private Animation deathI;
	private Animation iddleD;
	private Animation iddleI;
	private boolean direccion;
	private float velocidad;
	private GameScreen nivel;
	public Array<EnemyBullet> cargador;
	private int numBalas = 7;
	private int balaActual;
	private float tiempoDisparo = 5;
	private float disparando;
	private float cadencia;
	private float tCadencia;
	private float duracionMuerte = 1.5f;
	private float tiempoMuerte;
	private float danoIni;

	public Soldado(float x, float y, Stage s, GameScreen nivel) {
		super(x, y, s, nivel);
		pieD = loadFullAnimation("enemies/shotPieD.png", 1, 4, 0.05f, true);
		pieI = loadFullAnimation("enemies/shotPieI.png", 1, 4, 0.05f, true);
		sitD = loadFullAnimation("enemies/shotSitD.png", 1, 4, 0.05f, true);
		sitI = loadFullAnimation("enemies/shotSitI.png", 1, 4, 0.05f, true);
		iddleD = loadFullAnimation("enemies/soldadoIdleD.png", 1, 9, 0.05f, true);
		iddleI = loadFullAnimation("enemies/soldadoIdleI.png", 1, 9, 0.05f, true);
		deathD = loadFullAnimation("enemies/soldadoDeadD.png", 1, 4, 0.09f, false);
		deathI = loadFullAnimation("enemies/soldadoDeadI.png", 1, 4, 0.09f, false);
		this.setRectangle(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 7, 0);

		this.nivel = nivel;
		if (nivel.player.getX() < (this.getX() + this.getWidth() / 2)) {
			direccion = false;
		} else {
			direccion = true;
		}
		vida = 10;

		this.cargador = new Array<EnemyBullet>();
		for (int i = 0; i < numBalas; i++) {
			this.cargador.add(new EnemyBullet(0, 0, s, nivel));
		}
		balaActual = 0;
		this.cadencia = 0.3f;
		this.tCadencia = this.cadencia;
		disparando = (int) (Math.random() * 5);
		this.danoIni = 8;
		tiempoMuerte = -1;
	}

	public void act(float delta) {
		super.act(delta);
		if (nivel.player.getX() < (this.getX() + this.getWidth() / 2)) {
			direccion = false;
		} else {
			direccion = true;
		}
		animaciones();
		if (this.tCadencia > 0) {
			this.tCadencia -= delta;
		}
		if (disparando > 0) {
			disparando -= delta;
		} else {
			disparar();
		}
		morir(delta);
		this.dano = danoIni / Parametros.reduccionEnemy;
	}

	private void animaciones() {
		if (tiempoMuerte < 0) {
			if (this.tCadencia > 0) {
				switch (nivel.player.pocion) {
				case "pequena":
					if (direccion) {
						this.setAnimation(sitD);
					} else {
						this.setAnimation(sitI);
					}
					break;
				default:
					if (direccion) {
						this.setAnimation(pieD);
					} else {
						this.setAnimation(pieI);
					}
					break;
				}
			} else {
				if (direccion) {
					this.setAnimation(iddleD);
				} else {
					this.setAnimation(iddleI);
				}
			}
		} else {
			if (tiempoMuerte > 0) {
				if (direccion) {
					this.setAnimation(deathD);
				} else {
					this.setAnimation(deathI);
				}
			}
		}
	}

	public void disparar() {
		if (this.tCadencia < 0 && this.getEnabled() && tiempoMuerte < 0) {
			if (Math.abs(this.getX() - nivel.player.getX()) < 500
					&& Math.abs(this.getY() - nivel.player.getY()) < 500) {
				AudioManager.playSound("audio/sounds/enemyshot.mp3");
			}
			this.tCadencia = this.cadencia;
			if (nivel.player.pocion.equals("pequena")) {
				if (this.direccion) {
					this.cargador.get(balaActual).disparar(this.getX() + 100, this.getY() + 35, this.direccion, 300);
				} else {
					this.cargador.get(balaActual).disparar(this.getX() + 19, this.getY() + 35, this.direccion, 300);
				}
			} else {
				if (this.direccion) {
					this.cargador.get(balaActual).disparar(this.getX() + 100, this.getY() + 45, this.direccion, 200);
				} else {
					this.cargador.get(balaActual).disparar(this.getX() + 19, this.getY() + 45, this.direccion, 200);
				}
			}
			this.balaActual = (this.balaActual + 1) % this.numBalas;
			if (this.balaActual == this.numBalas - 1) {
				disparando = tiempoDisparo;
			}
		}
	}

	private void morir(float delta) {
		if (tiempoMuerte > duracionMuerte) {
			this.setEnabled(false);
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
}
