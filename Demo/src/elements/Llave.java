package elements;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Llave extends Element{
	public boolean found;

	public Llave(float x, float y, Stage s) {
		super(x, y, s);
		loadFullAnimation("objetos/llave.png", 1, 1, 1, false);
		found = false;
	}
}
