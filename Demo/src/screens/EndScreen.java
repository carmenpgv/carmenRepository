package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import game.Demo;
import managers.AudioManager;
import managers.ResourceManager;

public class EndScreen extends BScreen{

	public EndScreen(Demo game) {
		super(game);

		AudioManager.playMusic("audio/music/victory.mp3");
		TextButton boton = new TextButton("Volver a la pantalla de título", ResourceManager.textButtonStyle);
		boton.addListener((Event e) -> {
			if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(Type.touchDown))
				return false;
			this.dispose();
			game.setScreen(new TitleScreen(game));
			return false;
		});
		this.uiStage.addActor(boton);
		boton.setPosition(800, 600);
		boton.setSize(700, 70);
		
		TextButton botonSalir = new TextButton("Salir", ResourceManager.textButtonStyle);
		botonSalir.addListener((Event e) -> {
			if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(Type.touchDown))
				return false;
			this.dispose();
			Gdx.app.exit();
			return false;
		});
		botonSalir.setPosition(1025, 500);
		botonSalir.setSize(250, 70);
		this.uiStage.addActor(botonSalir);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
		this.batch.begin();
		this.batch.draw(pantallaFin, 0, 0);
		this.batch.end();

		uiStage.act();
		uiStage.draw();
		comenzar();

	}

	private void comenzar() {
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			this.dispose();
			game.setScreen(new GameScreen(game));
		}
	}

}
