package elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Parametros;
import managers.AudioManager;
import screens.GameScreen;

public class LittleThief extends Element{
	private Animation idle;
	private Animation salud;
	private GameScreen nivel;
	private float tiempoCura;
	private float cura = 3;
	private float duracionCura = 10;
	

	public LittleThief(float x, float y, Stage s, GameScreen nivel) {
		super(x, y, s);
		this.nivel = nivel;
		idle = loadFullAnimation("player/littleThief.png", 1, 1, 1, true);
		salud = loadFullAnimation("player/darDalud.png", 2, 4, 0.1f, false);
		tiempoCura = duracionCura;
	}
	public void act(float delta) {
		super.act(delta);
		applyPhysics(delta);
		if(this.getX()<nivel.player.getX()+80) {
			this.velocity.x = 50;
		} else if(this.getX()>nivel.player.getX()+80) {
			this.velocity.x = -50;
		}else {
			this.velocity.x = 0;
		}
		if(this.getY()<nivel.player.getY()+80) {
			this.velocity.y = 50;
		} else if(this.getY()>nivel.player.getY()-5) {
			this.velocity.y = -50;
		}else {
			this.velocity.y = 0;
		}
		if(tiempoCura<=0) {
			animationTime = 0;
			this.setAnimation(salud);
			AudioManager.playSound("audio/sounds/vida.mp3");
			Parametros.vida +=cura;
			if(Parametros.vida>Parametros.maxVida) {
				Parametros.vida = Parametros.maxVida;
			}
			tiempoCura = duracionCura;
		}else {
			if(tiempoCura<(duracionCura-2))
			this.setAnimation(idle);
			tiempoCura -= delta;
		}
	}
	
}
