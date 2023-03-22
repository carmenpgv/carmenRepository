package elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import game.Parametros;
import managers.AudioManager;
import screens.GameScreen;

public class Player extends Element {
	private Animation<TextureRegion> quietaDcha;
	private Animation<TextureRegion> quietaIzda;
	private Animation<TextureRegion> paradaDcha;
	private Animation<TextureRegion> paradaIzda;
	private Animation<TextureRegion> runDcha;
	private Animation<TextureRegion> runIzda;
	private Animation<TextureRegion> saltandoDcha;
	private Animation<TextureRegion> saltandoIzda;
	private Animation<TextureRegion> caidaD1;
	private Animation<TextureRegion> caidaD2;
	private Animation<TextureRegion> caidaD3;
	private Animation<TextureRegion> caidaD4;
	private Animation<TextureRegion> caidaI1;
	private Animation<TextureRegion> caidaI2;
	private Animation<TextureRegion> caidaI3;
	private Animation<TextureRegion> caidaI4;
	private Animation<TextureRegion> caidaBruscaI;
	private Animation<TextureRegion> caidaBruscaD;
	private Animation<TextureRegion> disparaDcha;
	private Animation<TextureRegion> disparaIzda;
	private Animation<TextureRegion> correDisparaIzda;
	private Animation<TextureRegion> correDisparaDcha;
	private Animation<TextureRegion> miniParadaDcha;
	private Animation<TextureRegion> miniParadaIzda;
	private Animation<TextureRegion> miniRunDcha;
	private Animation<TextureRegion> miniRunIzda;
	private Animation<TextureRegion> miniSaltandoDcha;
	private Animation<TextureRegion> miniSaltandoIzda;
	private Animation<TextureRegion> miniCaidaD;
	private Animation<TextureRegion> miniCaidaI;
	private Animation<TextureRegion> miniDisparaDcha;
	private Animation<TextureRegion> miniDisparaIzda;
	private Animation<TextureRegion> miniCorreDisparaDcha;
	private Animation<TextureRegion> miniCorreDisparaIzda;
	private float velocidadSalto = 200;
	private float velocidadSaltoPequena = 260;
	public boolean tocoSuelo = false;
	public boolean tocoTecho = false;
	public boolean correDcha = true;
	private float tiempoParaQuieta = 20;
	private float tiempoTocandoSuelo = 10;
	public boolean salta = false;
	private float tiempoCaida = 0;
	private float cambioCaida1 = 1;
	private float cambioCaida2 = 2;
	private boolean caidaBrusca = false;
	private boolean controlesHabilitados = true;
	private float cDeshabilitados = 0;
	private float habilitarControles = 1;
	public Array<Bullet> cargador;
	private int numBalas = 10;
	private int balaActual;
	private float tiempoDisparo = 6;
	private float cadencia;
	private float tCadencia;
	public boolean atravesar;
	public String pocion;
	public boolean invulnerable = false;
	public float duracionInvulnerable = 0.25f;
	public float tiempoInvulnerable;

	public Element pies;
	public Element cabeza;

	public Player(float x, float y, Stage s, GameScreen nivel) {
		super(x, y, s);

		// TODO Auto-generated constructor stub
		paradaDcha = loadFullAnimation("player/kayParadaDcha.png", 1, 1, 1, false);
		paradaIzda = loadFullAnimation("player/kayParadaIzda.png", 1, 1, 1, false);
		quietaDcha = loadFullAnimation("player/kayQuietaDcha.png", 1, 6, 0.2f, true);
		quietaIzda = loadFullAnimation("player/kayQuietaIzda.png", 1, 6, 0.2f, true);
		runDcha = loadFullAnimation("player/kayCorreDcha.png", 1, 6, 0.2f, true);
		runIzda = loadFullAnimation("player/kayCorreIzda.png", 1, 6, 0.2f, true);
		saltandoDcha = loadFullAnimation("player/kaySaltoDcha.png", 1, 1, 1, false);
		saltandoIzda = loadFullAnimation("player/kaySaltoIzda.png", 1, 1, 1, false);
		caidaD1 = loadFullAnimation("player/kayCayendoDcha1.png", 1, 1, 1, false);
		caidaD2 = loadFullAnimation("player/kayCayendoDcha2.png", 1, 1, 1, false);
		caidaD3 = loadFullAnimation("player/kayCayendoDcha3.png", 1, 1, 1, false);
		caidaD4 = loadFullAnimation("player/kayCayendoDcha4.png", 1, 1, 1, false);
		caidaI1 = loadFullAnimation("player/kayCayendoIzda1.png", 1, 1, 1, false);
		caidaI2 = loadFullAnimation("player/kayCayendoIzda2.png", 1, 1, 1, false);
		caidaI3 = loadFullAnimation("player/kayCayendoIzda3.png", 1, 1, 1, false);
		caidaI4 = loadFullAnimation("player/kayCayendoIzda4.png", 1, 1, 1, false);
		caidaBruscaD = loadFullAnimation("player/kayCaidaBruscaDcha.png", 1, 4, 0.3f, true);
		caidaBruscaI = loadFullAnimation("player/kayCaidaBruscaIzda.png", 1, 4, 0.3f, true);
		disparaDcha = loadFullAnimation("player/kayDisparandoDcha.png", 1, 1, 1, true);
		disparaIzda = loadFullAnimation("player/kayDisparandoIzda.png", 1, 1, 1, true);
		correDisparaIzda = loadFullAnimation("player/kayCorreDisparaIzda.png", 1, 6, 0.2f, true);
		correDisparaDcha = loadFullAnimation("player/kayCorreDisparaDcha.png", 1, 6, 0.2f, true);
		

		miniParadaDcha = loadFullAnimation("player/miniKayParadaDcha.png", 1, 1, 1, false);
		miniParadaIzda = loadFullAnimation("player/miniKayParadaIzda.png", 1, 1, 1, false);
		miniRunDcha = loadFullAnimation("player/miniKayCorreDcha.png", 1, 6, 0.2f, true);
		miniRunIzda = loadFullAnimation("player/miniKayCorreIzda.png", 1, 6, 0.2f, true);
		miniSaltandoDcha = loadFullAnimation("player/miniKaySaltoDcha.png", 1, 1, 1, false);
		miniSaltandoIzda = loadFullAnimation("player/miniKaySaltoIzda.png", 1, 1, 1, false);
		miniCaidaD = loadFullAnimation("player/miniKayCayendoDcha.png", 1, 1, 1, false);
		miniCaidaI = loadFullAnimation("player/miniKayCayendoIzda.png", 1, 1, 1, false);
		miniDisparaDcha = loadFullAnimation("player/miniKayDisparandoDcha.png", 1, 1, 1, true);
		miniDisparaIzda = loadFullAnimation("player/miniKayDisparandoIzda.png", 1, 1, 1, true);
		miniCorreDisparaDcha = loadFullAnimation("player/miniKayCorreDisparaDcha.png", 1, 6, 0.2f, true);
		miniCorreDisparaIzda = loadFullAnimation("player/miniKayCorreDisparaIzda.png", 1, 6, 0.2f, true);
		this.setAnimation(quietaDcha);

		// this.setRectangle();
		this.setRectangle(40, 79, 9, 0);

		pies = new Element(0, 0, s, this.getWidth() / 4, this.getHeight() / 10);
		pies.setRectangle();
		cabeza = new Element(0, 0, s, this.getWidth() / 4, this.getHeight() / 10);
		cabeza.setRectangle();
		colocarPies();

		this.cargador = new Array<Bullet>();
		for (int i = 0; i < numBalas; i++) {
			this.cargador.add(new Bullet(0, 0, s, nivel));
		}
		balaActual = 0;
		this.cadencia = 0.5f;
		this.tCadencia = this.cadencia;
		this.atravesar = true;
		this.pocion = "normal";
		tiempoInvulnerable = 0;

	}

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);

		controles();
		postura(delta);
		caer(delta);
		habilitarControles(delta);
		animaciones();
		if (!tocoSuelo) {
			if(!salta) {
				this.acceleration.y = -250;
			} else {
			this.acceleration.y = -200;
			}
		} else {
			this.velocity.y = -200;
		}
		if(tocoTecho) {
			this.velocity.y = 0;
		}
		this.applyPhysics(delta);
		if(this.tCadencia>0) {
			this.tCadencia -=delta;
		}
		this.colocarPies();
		this.colocarCabeza();
		if(tiempoInvulnerable>0) {
			tiempoInvulnerable -= delta;
		}
		
	}

	private void controles() {
		/*
		 * if (estoyEnEscalera) { if (Gdx.input.isKeyPressed(Keys.UP) ||
		 * Gdx.input.isKeyPressed(Keys.W)) { this.velocity.y = 20;
		 * this.setAnimation(espalda); } else if (Gdx.input.isKeyPressed(Keys.DOWN) ||
		 * Gdx.input.isKeyPressed(Keys.S)) { this.velocity.y = -20;
		 * this.setAnimation(espalda); } else { this.velocity.y = 0; } }
		 */
		if (controlesHabilitados) {
			if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
				if(pocion.equals("pequena")) {
					this.velocity.x = 180;
				} else {
					this.velocity.x = 100;
				}
				this.correDcha = true;
			} else if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
				if(pocion.equals("pequena")) {
					this.velocity.x = -180;
				} else {
					this.velocity.x = -100;
				}
				this.correDcha = false;
			} else {
				this.velocity.x = 0;
			}
			if (tocoSuelo && Gdx.input.isKeyJustPressed(Keys.SPACE)) {
				saltar();
			}
			if (this.tCadencia<0 && Gdx.input.isKeyPressed(Keys.E)) {
				this.disparar();
			}
		}
		/*
		 * if(saltosQuedan > 0 && Gdx.input.isKeyJustPressed(Keys.SPACE)) { saltar();
		 * saltosQuedan--; }
		 */

	}

	private void animaciones() {
		/*
		 * if (this.velocity.y != 0) { if (this.velocity.y > 0) {
		 * this.setAnimation(espalda); } else { this.setAnimation(frente); } } else
		 */
		switch(this.pocion) {
			case "pequena":
				if (Gdx.input.isKeyPressed(Keys.E)) {
					tiempoDisparo = 0;
					if (correDcha) {
						if(this.velocity.x==0) {
							this.setAnimation(miniDisparaDcha);
						} else {
							this.setAnimation(miniCorreDisparaDcha);
						}
					} else {
						if(this.velocity.x==0) {
							this.setAnimation(miniDisparaIzda);
						} else {
							this.setAnimation(miniCorreDisparaIzda);
						}
					}
				} else {
					if(tiempoDisparo<6) {
						tiempoDisparo++;
					}
					if (tocoSuelo) {
						if (this.velocity.x != 0) {
							if (this.velocity.x > 0) {
								if(!salta) {
									this.setAnimation(miniRunDcha);
								}
							} else {
								if(!salta) {
									this.setAnimation(miniRunIzda);
								}
							}
						} else {
							if (correDcha) {
								if(!salta) {
									this.setAnimation(miniParadaDcha);
								}
							} else {
								if(!salta) {
									this.setAnimation(miniParadaIzda);
								}
							}
						}
					} else {
						if (correDcha) {
							if (velocity.y <= 0) {
								if (salta) {
									salta = false;
								}
								this.setAnimation(miniCaidaD);
							} else {
								this.setAnimation(miniSaltandoDcha);
								salta = true;
							}
						} else {
							if (velocity.y <= 0) {
								if (salta) {
									salta = false;
								}
								this.setAnimation(miniCaidaI);
							} else {
								this.setAnimation(miniSaltandoIzda);
								salta = true;
							}
						}
					}
				}
				break;
			default:
				if (Gdx.input.isKeyPressed(Keys.E)) {
					tiempoDisparo = 0;
					if (correDcha) {
						if(this.velocity.x==0) {
							this.setAnimation(disparaDcha);
						} else {
							this.setAnimation(correDisparaDcha);
						}
					} else {
						if(this.velocity.x==0) {
							this.setAnimation(disparaIzda);
						} else {
							this.setAnimation(correDisparaIzda);
						}
					}
				} else {
					if(tiempoDisparo<6) {
						tiempoDisparo++;
					}
					if (tocoSuelo) {
						if (caidaBrusca) {
							if (correDcha) {
								this.setAnimation(caidaBruscaD);
							} else {
								this.setAnimation(caidaBruscaI);
							}
							caidaBrusca = false;
						} else if (controlesHabilitados) {
							if (this.velocity.x != 0) {
								if (this.velocity.x > 0) {
									if(!salta) {
										this.setAnimation(runDcha);
									}
								} else {
									if(!salta) {
										this.setAnimation(runIzda);
									}
								}
							} else {
								if (correDcha) {
									if (tiempoTocandoSuelo < tiempoParaQuieta) {
										if(!salta) {
											this.setAnimation(paradaDcha);
										}
									} else {
										this.setAnimation(quietaDcha);
									}
								} else {
									if (tiempoTocandoSuelo < tiempoParaQuieta) {
										if(!salta) {
											this.setAnimation(paradaIzda);
										}
									} else {
										this.setAnimation(quietaIzda);
									}
								}
							}
						}
					} else {
						if (correDcha) {
							if (velocity.y <= 0) {
								if (salta) {
									salta = false;
									this.setAnimation(caidaD1);
								} else {
									if (tiempoCaida < cambioCaida1) {
										this.setAnimation(caidaD2);
									} else if (tiempoCaida < cambioCaida2) {
										this.setAnimation(caidaD3);
									} else {
										this.setAnimation(caidaD4);
										this.caidaBrusca = true;
										this.controlesHabilitados = false;
									}
								}
							} else {
								this.setAnimation(saltandoDcha);
								salta = true;
							}
						} else {
							if (velocity.y <= 0) {
								if (salta) {
									salta = false;
									this.setAnimation(caidaI1);
								} else {
									if (tiempoCaida < cambioCaida1) {
										this.setAnimation(caidaI2);
									} else if (tiempoCaida < cambioCaida2) {
										this.setAnimation(caidaI3);
									} else {
										this.setAnimation(caidaI4);
										this.caidaBrusca = true;
										this.controlesHabilitados = false;
									}
								}
							} else {
								this.setAnimation(saltandoIzda);
								salta = true;
							}
						}
					}
				}
				break;
		}
		
	}

	public void colocarPies() {
		this.pies.setPosition(this.getX() + this.getWidth() / 2 - this.getWidth() / 8, this.getY());
	}
	
	public void colocarCabeza() {
		if(this.pocion.equals("pequena")) {
			this.cabeza.setPosition(this.getX() + this.getWidth() / 2 - this.getWidth() / 8, this.getY() +54);
		} else {
			this.cabeza.setPosition(this.getX() + this.getWidth() / 2 - this.getWidth() / 8, this.getY() +73);
		}
	}

	public void saltar() {
		 AudioManager.playSound("audio/sounds/jump.mp3");
		if(pocion.equals("pequena")) {
			this.velocity.y = velocidadSaltoPequena;
		} else {
			this.velocity.y = velocidadSalto;
		}
		tocoSuelo = false;
	}

	private void postura(float delta) {
		if (tocoSuelo && this.velocity.x == 0) {
			tiempoTocandoSuelo += delta;
		} else {
			tiempoTocandoSuelo = 0;
		}
	}

	private void caer(float delta) {
		if (velocity.y < 0 && !tocoSuelo) {
			tiempoCaida += delta;
		} else {
			tiempoCaida = 0;
		}
	}

	private void habilitarControles(float delta) {
		if (!controlesHabilitados) {
			if (cDeshabilitados < habilitarControles) {
				cDeshabilitados += delta;
			} else {
				cDeshabilitados = 0;
				controlesHabilitados = true;
			}
		}
	}

	public void disparar() {
		this.tCadencia = this.cadencia;
		 AudioManager.playSound("audio/sounds/shot.mp3");
		if(this.pocion.equals("pequena")) {
			if(this.correDcha) {
				this.cargador.get(balaActual).disparar(this.getX() + 53, this.getY() + 35, this.correDcha, 300);
			} else {
				this.cargador.get(balaActual).disparar(this.getX() + 1, this.getY() + 35, this.correDcha, 300);
			}
		} else {
			if(this.correDcha) {
				this.cargador.get(balaActual).disparar(this.getX() + 53, this.getY() + 52, this.correDcha, 200);
			} else {
				this.cargador.get(balaActual).disparar(this.getX() + 1, this.getY() + 52, this.correDcha, 200);
			}
		}
		this.balaActual = (this.balaActual + 1) % this.numBalas;
	}
	public void dano(float dano) {
		if(tiempoInvulnerable <=0) {
			Parametros.vida -= dano;
			invulnerable = true;
			tiempoInvulnerable = duracionInvulnerable;
		}
		if(Parametros.vida<=0) {
			 AudioManager.playSound("audio/sounds/fail.mp3");
		}
	}
}
