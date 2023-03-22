package elements;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Stage;

import screens.GameScreen;

public class Platform extends Element{
	protected GameScreen nivel;
	public boolean rectangleEnabled;
	public Platform(float x, float y, Stage s, GameScreen nivel) {
		super(x,y,s);
		this.nivel = nivel;
	}
	public void act(float delta) {
		super.act(delta);
	}
}
