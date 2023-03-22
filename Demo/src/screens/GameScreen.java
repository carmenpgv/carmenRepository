package screens;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;

import elements.Slider;
import elements.Soldado;
import elements.Element;
import elements.Enemy;
import elements.Platform;
import elements.Player;
import elements.Pocion;
import elements.Solid;
import elements.JefeBosque;
import elements.LittleThief;
import elements.Llave;
import game.Demo;
import game.Parametros;
import managers.AudioManager;
import managers.ResourceManager;
import ui.Barra;

public class GameScreen extends BScreen {

	Stage mainStage;

	public Array<Enemy> enemigos;
	public Array<Platform> sliders;
	public Array<Pocion> pociones;
	public Array<Llave> llaves;

	OrthographicCamera camara;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	public Array<Solid> solidos;
	private Array<Solid> pinchos;
	public Array<Solid> plataformasSolid;
	private Array<Solid> zonaMuerte;
	private Solid end;

	private int tileWidth, tileHeight, mapWidthInTiles, mapHeightInTiles, mapWidthInPixels, mapHeightInPixels;

	public Player player;
	Label lVida;
	public float duracionPocion = 25;
	public float tiempoPocion;
	public Barra bVida;
	public Barra bPocion;
	public LittleThief littleThief;

	public GameScreen(Demo game) {

		super(game);
		mainStage = new Stage();
		switch (Parametros.nivel) {
		case 1:
			AudioManager.playMusic("audio/music/ThiefOfTruth.mp3");
			map = ResourceManager.getMap("maps/1FrancisForever.tmx");
			break;
		case 2:
			map = ResourceManager.getMap("maps/2nobody.tmx");
			break;
		case 3:
			game.setScreen(new EndScreen(game));
			break;
		}
		Parametros.vida = Parametros.maxVida;
		renderer = new OrthogonalTiledMapRenderer(map, mainStage.getBatch());
		MapProperties props;
		props = map.getProperties();
		tileWidth = props.get("tilewidth", Integer.class);
		tileHeight = props.get("tileheight", Integer.class);
		mapWidthInTiles = props.get("width", Integer.class);
		mapHeightInTiles = props.get("height", Integer.class);
		mapWidthInPixels = tileWidth * mapWidthInTiles;
		mapHeightInPixels = tileHeight * mapHeightInTiles;
		ArrayList<MapObject> elementos;
		elementos = getRectangleList("solid");
		solidos = new Array<Solid>();
		pinchos = new Array<Solid>();
		zonaMuerte = new Array<Solid>();
		plataformasSolid = new Array<Solid>();

		Solid solido;
		for (MapObject solid : elementos) {

			props = solid.getProperties();
			solido = new Solid((float) props.get("x"), (float) props.get("y"), mainStage, (float) props.get("width"),
					(float) props.get("height"));
			solidos.add(solido);
		}
		elementos = getRectangleList("pinchos");
		for (MapObject solid : elementos) {
			props = solid.getProperties();
			solido = new Solid((float) props.get("x"), (float) props.get("y"), mainStage, (float) props.get("width"),
					(float) props.get("height"));
			pinchos.add(solido);
		}
		elementos = getRectangleList("platform");
		for (MapObject solid : elementos) {
			props = solid.getProperties();
			solido = new Solid((float) props.get("x"), (float) props.get("y"), mainStage, (float) props.get("width"),
					(float) props.get("height"));
			plataformasSolid.add(solido);
		}
		elementos = getRectangleList("muerte");
		for (MapObject solid : elementos) {
			props = solid.getProperties();
			solido = new Solid((float) props.get("x"), (float) props.get("y"), mainStage, (float) props.get("width"),
					(float) props.get("height"));
			zonaMuerte.add(solido);
		}

		pociones = new Array<Pocion>();
		for (MapObject pocion : getPotionsList()) {
			props = pocion.getProperties();
			switch (props.get("pocion").toString()) {
			case "pequena":
				Pocion pocionPequena = new Pocion((float) props.get("x"), (float) props.get("y"), mainStage,
						"objetos/pocionPequena.png", "pequena");
				pociones.add(pocionPequena);
				break;
			case "dano":
				Pocion pocionDano = new Pocion((float) props.get("x"), (float) props.get("y"), mainStage,
						"objetos/pocionDano.png", "dano");
				pociones.add(pocionDano);
				break;
			}
		}
		llaves = new Array<Llave>();
		for (MapObject objeto : getKeysList()) {
			props = objeto.getProperties();
			Llave llave = new Llave((float) props.get("x"), (float) props.get("y"), mainStage);
			llaves.add(llave);
		}

		elementos = getRectangleList("end");
		props = elementos.get(0).getProperties();
		end = new Solid((float) props.get("x"), (float) props.get("y"), mainStage, (float) props.get("width"),
				(float) props.get("height"));

		camara = (OrthographicCamera) mainStage.getCamera();
		camara.setToOrtho(false, Parametros.getAnchoPantalla() * Parametros.zoom,
				Parametros.getAltoPantalla() * Parametros.zoom);
		renderer.setView(camara);

		sliders = new Array<Platform>();
		for (MapObject slider : getSlidersList()) {

			props = slider.getProperties();
			switch (props.get("slider").toString()) {
			case "up":
				Slider slidUp = new Slider((float) props.get("x"), (float) props.get("y"), 0, mainStage, this);
				sliders.add(slidUp);
				break;
			case "down":
				Slider slidDown = new Slider((float) props.get("x"), (float) props.get("y"), 3.3f, mainStage, this);
				sliders.add(slidDown);
				break;
			case "medium":
				Slider slidMedium = new Slider((float) props.get("x"), (float) props.get("y"), 1.5f, mainStage, this);
				sliders.add(slidMedium);
				break;
			}
		}
		float inicioX;
		float inicioY;

		elementos = getRectangleList("start");
		props = elementos.get(0).getProperties();

		inicioX = (float) props.get("x");
		inicioY = (float) props.get("y");
		player = new Player(inicioX, inicioY, mainStage, this);

		enemigos = new Array<Enemy>();
		for (MapObject ene : getEnemyList()) {
			props = ene.getProperties();
			switch (props.get("enemigo").toString()) {
			case "jefeBosque":
				JefeBosque enemy = new JefeBosque((float) props.get("x"), (float) props.get("y"), mainStage, this, 8);
				enemigos.add(enemy);
				break;
			case "soldado":
				Soldado soldado = new Soldado((float) props.get("x"), (float) props.get("y"), mainStage, this);
				enemigos.add(soldado);
				break;
			}
		}

		uiStage = new Stage();
		bVida = new Barra(20, Parametros.getAltoPantalla() - 40, uiStage, 350, 20, Color.RED);
		bPocion = new Barra(20, Parametros.getAltoPantalla() - 100, uiStage, 350, 20, Color.RED);
		bPocion.setEnabled(false);
		tiempoPocion = 0;
		littleThief = new LittleThief(this.player.getX()+80, this.player.getY()-5, mainStage, this);
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
		mainStage.act();
		uiStage.act();
		if (Parametros.vida<=0) {
			game.setScreen(new GameScreen(game));
		}
		colide();
		if (tiempoPocion > 0) {
			tiempoPocion -= delta;
		} else {
			Parametros.reduccionEnemy = 1;
			player.pocion = "normal";
			player.setRectangle(40, 79, 9, 0);
			for (Pocion p : pociones) {
				p.setEnabled(true);
			}
			bPocion.setEnabled(false);
		}

		centrarCamara();
		// actualizarInterfaz();
		renderer.setView(camara);
		renderer.render();
		mainStage.draw();
		actualizarInterfaz();
		uiStage.draw();

	}

	public void colide() {
		/*
		 * for(Solid e: escaleras) {
		 * 
		 * if(e.getEnabled()&&e.overlaps(player)) { player.tocarEscaleras(true); break;
		 * } else { player.tocarEscaleras(false); } }
		 */
		this.player.tocoSuelo = false;
		this.player.tocoTecho = false;
		for (Solid s : solidos) {
			if (this.player.pies.overlaps(s)) {
				this.player.tocoSuelo = true;
			}
			if (this.player.cabeza.overlaps(s)) {
				this.player.tocoTecho = true;
			}
			if (s.getEnabled() && s.overlaps(player)) {
				player.preventOverlap(s);
			}
		}
		for (Solid s : zonaMuerte) {
			if (s.getEnabled() && s.overlaps(player) && !player.pocion.equals("pequena")) {
				Parametros.vida = 0;
			}
		}
		if (this.end.overlaps(this.player)) {
			if(Parametros.nivel==2) {
				 AudioManager.playSound("audio/sounds/victory.mp3");
				game.setScreen(new EndScreen(game));
			}else {
			if (llaves.get(0).found)
				avanzarNivel();
			}
		}
		for (Solid p : pinchos) {
			if (this.player.pies.overlaps(p)) {
				player.dano(3/Parametros.reduccionEnemy);
			}
		}
		for (Solid plat : plataformasSolid) {

			if (plat.getEnabled() && plat.overlaps(player)) {
				player.preventOverlap(plat);
			}
			if (this.player.getY() >= plat.getY() + plat.getHeight()) {
				plat.setEnabled(true);
			} else {
				plat.setEnabled(false);
			}
			if (this.player.pies.overlaps(plat) && plat.getEnabled()) {
				this.player.tocoSuelo = true;
			}
		}

		for (Platform plat : sliders) {
			if (plat.getEnabled()) {
				if (this.player.salta || plat.overlaps(this.player.cabeza)) {
					plat.rectangleEnabled = false;
				} else {
					if (!plat.rectangleEnabled && this.player.overlaps(plat)) {
						plat.rectangleEnabled = false;
					} else {
						plat.rectangleEnabled = true;
					}
				}
				if (plat.rectangleEnabled && this.player.pies.overlaps(plat)) {
					this.player.tocoSuelo = true;
				}
				if (plat.rectangleEnabled && plat.overlaps(player)) {
					player.preventOverlap(plat);
				}
			}
		}
		for (Pocion p : pociones) {
			if (p.getEnabled() && this.player.overlaps(p)) {
				 AudioManager.playSound("audio/sounds/potion.mp3");
				for (int i = 1; i < pociones.size; i++) {
					pociones.get(i).setEnabled(true);
				}
				p.setEnabled(false);
				switch (p.tipo) {
				case "pequena":
					this.player.pocion = "pequena";
					player.setRectangle(30, 62, 3, 0);
					this.tiempoPocion = this.duracionPocion;
					bPocion.color = Color.PINK;
					bPocion.setEnabled(true);
					Parametros.reduccionEnemy = 1;
					break;
				case "dano":
					this.player.pocion = "dano";
					Parametros.reduccionEnemy = 2;
					player.setRectangle(40, 79, 9, 0);
					this.tiempoPocion = this.duracionPocion;
					bPocion.color = Color.GOLD;
					bPocion.setEnabled(true);
					break;
				}
			}
		}
		for (Llave l : llaves) {
			if (l.getEnabled() && this.player.overlaps(l)) {
				 AudioManager.playSound("audio/sounds/rock.mp3");
				l.setEnabled(false);
				l.found = true;
			}
		}
		this.player.colocarPies();
		this.player.colocarCabeza();

	}

	public void avanzarNivel() {
		Parametros.nivel++;
		game.setScreen(new GameScreen(game));
	}

	public void centrarCamara() {
		this.camara.position.x = player.getX();
		this.camara.position.y = player.getY();

		this.camara.position.x = MathUtils.clamp(this.camara.position.x, this.camara.viewportWidth / 2,
				this.mapWidthInPixels - this.camara.viewportWidth / 2);
		this.camara.position.y = MathUtils.clamp(this.camara.position.y, this.camara.viewportHeight / 2,
				this.mapHeightInPixels - this.camara.viewportHeight / 2);
		camara.update();
	}

	public ArrayList<MapObject> getRectangleList(String propertyName) {
		ArrayList<MapObject> list = new ArrayList<MapObject>();
		for (MapLayer layer : map.getLayers()) {
			for (MapObject obj : layer.getObjects()) {
				if (!(obj instanceof RectangleMapObject))
					continue;
				MapProperties props = obj.getProperties();
				if (props.containsKey("name") && props.get("name").equals(propertyName)) {
					list.add(obj);
				}

			}

		}

		return list;
	}

	public ArrayList<Polygon> getPolygonList(String propertyName) {

		Polygon poly;
		ArrayList<Polygon> list = new ArrayList<Polygon>();
		for (MapLayer layer : map.getLayers()) {
			for (MapObject obj : layer.getObjects()) {

				if (!(obj instanceof PolygonMapObject))
					continue;
				MapProperties props = obj.getProperties();
				if (props.containsKey("name") && props.get("name").equals(propertyName)) {

					poly = ((PolygonMapObject) obj).getPolygon();
					list.add(poly);
				}

			}

		}

		return list;
	}

	public ArrayList<MapObject> getEnemyList() {
		ArrayList<MapObject> list = new ArrayList<MapObject>();
		for (MapLayer layer : map.getLayers()) {
			for (MapObject obj : layer.getObjects()) {
				if (!(obj instanceof TiledMapTileMapObject))
					continue;
				MapProperties props = obj.getProperties();

				TiledMapTileMapObject tmtmo = (TiledMapTileMapObject) obj;
				TiledMapTile t = tmtmo.getTile();
				MapProperties defaultProps = t.getProperties();
				if (defaultProps.containsKey("enemigo")) {
					list.add(obj);

				}

				Iterator<String> propertyKeys = defaultProps.getKeys();
				while (propertyKeys.hasNext()) {
					String key = propertyKeys.next();

					if (props.containsKey(key))
						continue;
					else {
						Object value = defaultProps.get(key);
						props.put(key, value);
					}

				}

			}

		}

		return list;
	}

	public ArrayList<MapObject> getPotionsList() {
		ArrayList<MapObject> list = new ArrayList<MapObject>();
		for (MapLayer layer : map.getLayers()) {
			for (MapObject obj : layer.getObjects()) {
				if (!(obj instanceof TiledMapTileMapObject))
					continue;
				MapProperties props = obj.getProperties();

				TiledMapTileMapObject tmtmo = (TiledMapTileMapObject) obj;
				TiledMapTile t = tmtmo.getTile();
				MapProperties defaultProps = t.getProperties();
				if (defaultProps.containsKey("pocion")) {
					list.add(obj);

				}

				Iterator<String> propertyKeys = defaultProps.getKeys();
				while (propertyKeys.hasNext()) {
					String key = propertyKeys.next();

					if (props.containsKey(key))
						continue;
					else {
						Object value = defaultProps.get(key);
						props.put(key, value);
					}

				}

			}

		}

		return list;
	}

	public ArrayList<MapObject> getSlidersList() {
		ArrayList<MapObject> list = new ArrayList<MapObject>();
		for (MapLayer layer : map.getLayers()) {
			for (MapObject obj : layer.getObjects()) {
				if (!(obj instanceof TiledMapTileMapObject))
					continue;
				MapProperties props = obj.getProperties();

				TiledMapTileMapObject tmtmo = (TiledMapTileMapObject) obj;
				TiledMapTile t = tmtmo.getTile();
				MapProperties defaultProps = t.getProperties();
				if (defaultProps.containsKey("slider")) {
					list.add(obj);

				}

				Iterator<String> propertyKeys = defaultProps.getKeys();
				while (propertyKeys.hasNext()) {
					String key = propertyKeys.next();

					if (props.containsKey(key))
						continue;
					else {
						Object value = defaultProps.get(key);
						props.put(key, value);
					}

				}

			}

		}

		return list;
	}

	public ArrayList<MapObject> getKeysList() {
		ArrayList<MapObject> list = new ArrayList<MapObject>();
		for (MapLayer layer : map.getLayers()) {
			for (MapObject obj : layer.getObjects()) {
				if (!(obj instanceof TiledMapTileMapObject))
					continue;
				MapProperties props = obj.getProperties();

				TiledMapTileMapObject tmtmo = (TiledMapTileMapObject) obj;
				TiledMapTile t = tmtmo.getTile();
				MapProperties defaultProps = t.getProperties();
				if (defaultProps.containsKey("objeto")) {
					list.add(obj);

				}

				Iterator<String> propertyKeys = defaultProps.getKeys();
				while (propertyKeys.hasNext()) {
					String key = propertyKeys.next();

					if (props.containsKey(key))
						continue;
					else {
						Object value = defaultProps.get(key);
						props.put(key, value);
					}

				}

			}

		}

		return list;
	}

	private void actualizarInterfaz() {
		if (((float) Parametros.vida) / ((float) Parametros.maxVida)>=0) {
			bVida.percent = ((float) Parametros.vida) / ((float) Parametros.maxVida);
		}
		
		bPocion.percent = tiempoPocion / duracionPocion;
	}
}
