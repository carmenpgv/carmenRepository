package elements;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Pocion extends Element{
	public String tipo;

	public Pocion(float x, float y, Stage s, String sprite, String tipo) {
		super(x, y, s);
		this.loadFullAnimation(sprite, 1, 1, 1, false);
		this.tipo = tipo;
	}

}
