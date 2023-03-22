package elements;

import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Parametros;
import screens.GameScreen;

public class EnemyBullet extends Element {
	public float duracionBala;
	public float tiempoActiva;
	private GameScreen nivel;
	public float damage;

	public EnemyBullet(float x, float y, Stage s, GameScreen nivel) {
		super(x, y, s);
		this.loadFullAnimation("enemies/bala.png", 1, 1, 1, false);
		this.setRectangle();
		this.duracionBala = 3;
		this.setEnabled(false);
		this.nivel = nivel;
		this.damage = 3;
	}

	public void act(float delta) {
		if (this.getEnabled()) {

			super.act(delta);
			this.applyPhysics(delta);
			if (this.tiempoActiva > this.duracionBala) {
				this.setEnabled(false);
			} else {
				this.tiempoActiva += delta;
			}
			colide();
			this.damage = 3/Parametros.reduccionEnemy;
		}
	}

	public void disparar(float x, float y, boolean direccion, int velocidad) {
		this.setEnabled(true);
		this.tiempoActiva = 0;
		if (direccion) {
			this.setPosition(x, y);
			this.velocity.x = velocidad;
		} else {
			this.setPosition(x, y);
			this.velocity.x = -velocidad;
		}
		this.velocity.y = 0;
	}

	private void colide() {
		if (this.overlaps(nivel.player)) {
			nivel.player.dano(this.damage);
			this.setEnabled(false);
		}
		for (Solid s : this.nivel.solidos) {
			if (this.overlaps(s)) {
				this.setEnabled(false);
			}
		}
	}
}

