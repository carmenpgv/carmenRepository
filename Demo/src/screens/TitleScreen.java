package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import game.Demo;
import game.Parametros;
import managers.AudioManager;
import managers.ResourceManager;

public class TitleScreen extends BScreen {

	public TitleScreen(Demo game) {
		super(game);
		// TODO Auto-generated constructor stub


		AudioManager.playMusic("audio/music/intro.mp3");


		TextButton boton = new TextButton("Jugar", ResourceManager.textButtonStyle);
		boton.addListener((Event e) -> {
			if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(Type.touchDown))
				return false;
			Parametros.nivel = 1;
			this.dispose();
			game.setScreen(new GameScreen(game));
			return false;
		});
		this.uiStage.addActor(boton);
		boton.setPosition(1140, 475);
		boton.setSize(250, 70);
		
		TextButton botonSalir = new TextButton("Salir", ResourceManager.textButtonStyle);
		botonSalir.addListener((Event e) -> {
			if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(Type.touchDown))
				return false;
			this.dispose();
			Gdx.app.exit();
			return false;
		});
		botonSalir.setPosition(1140, 375);
		botonSalir.setSize(250, 70);
		this.uiStage.addActor(botonSalir);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
		this.batch.begin();
		this.batch.draw(fondoTitulo, 0, 0);
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
