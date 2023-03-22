package ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Parametros;

public class Barra extends Actor {
	protected ShapeRenderer shapeRenderer;
	protected float width;
	protected float height;
	public float percent;
	public Polygon barra;
	public Color color;
	public boolean enabled;

	public Barra(float x, float y, Stage ui, float w, float h, Color color) {
		this.setPosition(x, y);
		ui.addActor(this);
		shapeRenderer = new ShapeRenderer();
		this.width = w;
		this.height = h;
		this.setRectangle();
		this.color = color;
		this.enabled = true;
	}

	public float getWidth() {
		return this.width;
	}

	public float getHeight() {
		return this.height;
	}
	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean e) {
		this.enabled = e;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		if(this.getEnabled()) {
			pintar(batch);
		}
		super.draw(batch, parentAlpha);
	}

	public void pintar(Batch batch) {
		batch.end();
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.WHITE);
		if (this.getBoundaryPolygon() != null) {
			float[] vertices = new float[this.getBoundaryPolygon().getTransformedVertices().length];

			for (int i = 0; i < vertices.length / 2; i++) {
				vertices[2 * i] = (this.getBoundaryPolygon().getTransformedVertices()[2 * i]);
				vertices[2 * i + 1] = (this.getBoundaryPolygon().getTransformedVertices()[2 * i + 1]);

			}

			shapeRenderer.polygon(vertices);

		}
		shapeRenderer.end();
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(color);
		if (this.getBoundaryPolygon() != null) {
			float[] vertices = new float[this.getBoundaryPolygon().getTransformedVertices().length];

			for (int i = 0; i < vertices.length / 2; i++) {
				vertices[2 * i] = (this.getBoundaryPolygon().getTransformedVertices()[2 * i]);
				vertices[2 * i + 1] = (this.getBoundaryPolygon().getTransformedVertices()[2 * i + 1]);

			}

			shapeRenderer.rect(this.getX()+1, this.getY()+1,(this.getWidth()*percent)-2,this.getHeight()-2);

		}
		shapeRenderer.end();
		batch.begin();
	}
	

	public Polygon getBoundaryPolygon() {
		barra.setPosition(getX(), getY());
		barra.setOrigin(getOriginX(), getOriginY());
		barra.setRotation(getRotation());
		barra.setScale(getScaleX(), getScaleY());
		return barra;
	}

	public void setRectangle() {
		float w, h;
		if (this.width != getWidth() && this.width > 0) {
			w = this.width;
		} else {
			w = this.getWidth();
		}
		if (this.height != this.getHeight() && this.height > 0) {
			h = this.height;
		} else {
			h = getHeight();
		}
		float[] vertices = { 0, 0, w, 0, w, h, 0, h };
		barra = new Polygon(vertices);
		this.setOrigin(w / 2, h / 2);
	}

	public void setRectangle(float width, float height) {
		this.width = width;
		this.height = height;
		setRectangle();
	}

}
