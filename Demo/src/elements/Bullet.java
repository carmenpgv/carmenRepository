package elements;

import com.badlogic.gdx.scenes.scene2d.Stage;

import screens.GameScreen;

public class Bullet extends Element {
	public float duracionBala;
	public float tiempoActiva;
	private GameScreen nivel;
	public int damage;

	public Bullet(float x, float y, Stage s, GameScreen nivel) {
		super(x, y, s);
		this.loadFullAnimation("player/bala.png", 1, 1, 1, false);
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
		for (Enemy e : this.nivel.enemigos) {
			if (this.overlaps(e)) {
				e.damage(this.damage);
				this.setEnabled(false);
			}
		}
		for (Solid s : this.nivel.solidos) {
			if (this.overlaps(s)) {
				this.setEnabled(false);
			}
		}
	}
}
