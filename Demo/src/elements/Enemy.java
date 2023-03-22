package elements;

import com.badlogic.gdx.scenes.scene2d.Stage;

import screens.GameScreen;

public class Enemy extends Element{
	protected GameScreen nivel;
	public float vida;
	public float dano;
	public boolean dana;
	public Enemy(float x, float y, Stage s, GameScreen nivel) {
		super(x,y,s);
		this.nivel = nivel;
		dana = true;
	}
	public void damage(int damage) {
		this.vida -= damage;
	}
	public void act(float delta) {
		super.act(delta);
		colide();
	}
	
	public void colide() {
		if(this.getEnabled() && this.overlaps(this.nivel.player)) {
			if(dana) {
				nivel.player.dano(this.dano);
			}
		}
	}
}
