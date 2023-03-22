package elements;

import com.badlogic.gdx.scenes.scene2d.Stage;

import screens.GameScreen;

public class Slider extends Platform{
	float velocidad = 75;
	float tiempoMovimiento = 3.3f;
	float tiempoMoviendo;
	
	public Slider(float x, float y, float tiempo, Stage s, GameScreen nivel) {
		super(x, y, s,nivel);
		this.loadFullAnimation("maps/images/desplazante.png", 1, 1, 1, false);
		this.polyHigh = this.getHeight()/2;
		this.setRectangle();
		this.velocity.y = velocidad;
		this.rectangleEnabled = false;
		this.tiempoMoviendo = tiempo;
	}
	public void act(float delta) {
		super.act(delta);
		if(tiempoMoviendo>tiempoMovimiento) {
			this.velocity.y = this.velocity.y*(-1);
			tiempoMoviendo = 0;
		} else {
			tiempoMoviendo = tiempoMoviendo + delta;
		}
		this.applyPhysics(delta);
	}

}
