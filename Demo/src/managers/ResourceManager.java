package managers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public final class ResourceManager {
	private ResourceManager() {}
	public static AssetManager assets=new AssetManager();
	public static LabelStyle buttonStyle;
	public static TextButtonStyle textButtonStyle;
	
	
	
	
	public static void loadAllResources(){

		//mapas
		assets.setLoader(TiledMap.class, new TmxMapLoader());
		
		assets.load("maps/prueba.tmx", TiledMap.class);
		assets.load("maps/prueba3.tmx", TiledMap.class);
		assets.load("maps/prueba4.tmx", TiledMap.class);
		assets.load("maps/pruebaBosque.tmx", TiledMap.class);
		assets.load("maps/1FrancisForever.tmx", TiledMap.class);
		assets.load("maps/2nobody.tmx", TiledMap.class);
        //elementos de mapa
        //assets.load("maps/Images/arbol.png", Texture.class);
        assets.load("maps/images/barrel.png", Texture.class);
        assets.load("maps/images/desplazante.png", Texture.class);
        assets.load("maps/images/disapearing.png", Texture.class);
        assets.load("maps/images/disapearingProcess.png", Texture.class);
        assets.load("player/bala.png",Texture.class);
        assets.load("enemies/bala.png",Texture.class);
        //enemigos
        assets.load("enemies/blob.png",Texture.class);
        assets.load("enemies/blob_jump.png",Texture.class);
        //jugador
        assets.load("player/espaldaWalk.png",Texture.class);
        assets.load("player/grande.png",Texture.class);
        assets.load("player/frenteWalk.png",Texture.class);
        assets.load("player/frenteGrande.png",Texture.class);
        assets.load("player/izquieredawalk.png",Texture.class);
        assets.load("player/derechawalk.png",Texture.class);
        assets.load("player/Bola.png",Texture.class);
        assets.load("player/kayQuietaIzda.png", Texture.class);
        assets.load("player/kayQuietaDcha.png", Texture.class);
        assets.load("player/kayCorreIzda.png", Texture.class);
        assets.load("player/kayCorreDcha.png", Texture.class);
        assets.load("player/kayParadaIzda.png", Texture.class);
        assets.load("player/kayParadaDcha.png", Texture.class);
        assets.load("player/kaySaltoIzda.png", Texture.class);
        assets.load("player/kaySaltoDcha.png", Texture.class);
        assets.load("player/kayCayendoIzda1.png", Texture.class);
        assets.load("player/kayCayendoDcha1.png", Texture.class);
        assets.load("player/kayCayendoIzda2.png", Texture.class);
        assets.load("player/kayCayendoDcha2.png", Texture.class);
        assets.load("player/kayCayendoIzda3.png", Texture.class);
        assets.load("player/kayCayendoDcha3.png", Texture.class);
        assets.load("player/kayCayendoIzda4.png", Texture.class);
        assets.load("player/kayCayendoDcha4.png", Texture.class);
        assets.load("player/kayCaidaBruscaDcha.png", Texture.class);
        assets.load("player/kayCaidaBruscaIzda.png", Texture.class);
        assets.load("player/kayDisparandoDcha.png", Texture.class);
        assets.load("player/kayDisparandoIzda.png", Texture.class);
        assets.load("player/kayCorreDisparaDcha.png", Texture.class);
        assets.load("player/kayCorreDisparaIzda.png", Texture.class);
        
        assets.load("player/miniKayCorreIzda.png", Texture.class);
        assets.load("player/miniKayCorreDcha.png", Texture.class);
        assets.load("player/miniKayParadaIzda.png", Texture.class);
        assets.load("player/miniKayParadaDcha.png", Texture.class);
        assets.load("player/miniKaySaltoIzda.png", Texture.class);
        assets.load("player/miniKaySaltoDcha.png", Texture.class);
        assets.load("player/miniKayCayendoIzda.png", Texture.class);
        assets.load("player/miniKayCayendoDcha.png", Texture.class);
        assets.load("player/miniKayDisparandoDcha.png", Texture.class);
        assets.load("player/miniKayDisparandoIzda.png", Texture.class);
        assets.load("player/miniKayCorreDisparaDcha.png", Texture.class);
        assets.load("player/miniKayCorreDisparaIzda.png", Texture.class);
        
        //jefeBosque

        assets.load("enemies/jefeBosqueDeath.png", Texture.class);
        assets.load("enemies/jefeBosqueRun.png", Texture.class);
        assets.load("enemies/jefeBosqueWalk.png", Texture.class);
        assets.load("enemies/jefeBosqueIdle.png", Texture.class);
        assets.load("enemies/jefeBosqueDeathD.png", Texture.class);
        assets.load("enemies/jefeBosqueRunD.png", Texture.class);
        assets.load("enemies/jefeBosqueWalkD.png", Texture.class);
        assets.load("enemies/jefeBosqueIdleD.png", Texture.class);
        assets.load("enemies/shotPieD.png", Texture.class);
        assets.load("enemies/shotPieI.png", Texture.class);
        assets.load("enemies/shotSitD.png", Texture.class);
        assets.load("enemies/shotSitI.png", Texture.class);
        assets.load("enemies/soldadoDeadD.png", Texture.class);
        assets.load("enemies/soldadoDeadI.png", Texture.class);
        assets.load("enemies/soldadoIdleD.png", Texture.class);
        assets.load("enemies/soldadoIdleI.png", Texture.class);

        assets.load("player/littleThief.png", Texture.class);
        assets.load("player/darDalud.png", Texture.class);
        
        //pociones
        assets.load("objetos/pocionPequena.png", Texture.class);
        assets.load("objetos/pocionDano.png", Texture.class);
        assets.load("objetos/llave.png", Texture.class);
        
        //objetos
        //assets.load("objects/bomb.png",Texture.class);
        //assets.load("objects/hookl.png",Texture.class);
        //assets.load("objects/sword.png",Texture.class);
        //assets.load("objects/swordA.png",Texture.class);
        
        
        //Audio
        assets.load("audio/sounds/jump.mp3", Sound.class);
        assets.load("audio/sounds/shot.mp3", Sound.class);
        assets.load("audio/sounds/enemyshot.mp3", Sound.class);
        assets.load("audio/sounds/fail.mp3", Sound.class);
        assets.load("audio/sounds/jump.mp3", Sound.class);
        assets.load("audio/sounds/potion.mp3", Sound.class);
        assets.load("audio/sounds/roar.mp3", Sound.class);
        assets.load("audio/sounds/vida.mp3", Sound.class);
        assets.load("audio/sounds/roardeath.mp3", Sound.class);
        assets.load("audio/sounds/rock.mp3", Sound.class);
        assets.load("audio/sounds/scream.mp3", Sound.class);
        assets.load("audio/sounds/victory.mp3", Sound.class);
      assets.load("audio/music/swing.mp3", Music.class);
      assets.load("audio/music/ThiefOfTruth.mp3", Music.class);
      assets.load("audio/music/intro.mp3", Music.class);
      assets.load("audio/music/victory.mp3", Music.class);
      
        //UI
        assets.load("ui/rojo.jpg", Texture.class);
        assets.load("ui/morado.jpg", Texture.class);
        assets.load("ui/intro.png", Texture.class);
        assets.load("ui/carga.jpg", Texture.class);
        assets.load("ui/boton.png", Texture.class);
        assets.load("ui/fin.png", Texture.class);
 
	//añadir más elementos
        
        
		
	
	}
	
	public static boolean update(){
		return assets.update();
	}
	public static void botones() {
		
		//estilo para botones
        FreeTypeFontGenerator ftfg= new FreeTypeFontGenerator(Gdx.files.internal("sans.ttf"));
		FreeTypeFontParameter ftfp= new FreeTypeFontParameter();
		ftfp.size=50;
		ftfp.color=Color.WHITE;
		ftfp.borderColor=Color.BLACK;
		ftfp.borderWidth=0;
		
		BitmapFont fuentePropia=ftfg.generateFont(ftfp);
		buttonStyle=new LabelStyle();
		buttonStyle.font=fuentePropia;
		textButtonStyle=new TextButtonStyle();
		Texture buttonText = ResourceManager.getTexture("ui/boton.png");
		NinePatch buttonPatch = new NinePatch(buttonText);
		textButtonStyle.up=new NinePatchDrawable(buttonPatch);
		textButtonStyle.font=fuentePropia;
		
		
	}
	
	/*public static TextureAtlas getAtlas(String path){
		return assets.get(path, TextureAtlas.class);
		
	}*/
	
	public static Texture getTexture(String path) {
		return assets.get(path, Texture.class);
	}
	
	public static Music getMusic(String path){
		return assets.get(path, Music.class);
	}
	
	public static Sound getSound(String path)
	{
		return assets.get(path, Sound.class);
	}
	
	public static TiledMap getMap(String path){
		return assets.get(path, TiledMap.class);
	}

	public static void dispose(){
		assets.dispose();
	}
}
