package juegosDeMesa.gui;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import juegosDeMesa.base.Disenador;
import juegosDeMesa.base.Juego;
import org.bson.Document;

import java.time.LocalDate;
import java.util.ArrayList;

public class Modelo {
    private MongoClient cliente;
    private MongoCollection<Document> juegos;
    private MongoCollection<Document> disenadores;

    public void conectar() {
        cliente = new MongoClient();
        String DATABASE = "Excelsior";
        MongoDatabase db = cliente.getDatabase(DATABASE);

        String COLECCION_JUEGOS = "Juegos";
        juegos = db.getCollection(COLECCION_JUEGOS);
        String COLECCION_DISENADORES = "Disenadores";
        disenadores = db.getCollection(COLECCION_DISENADORES);
    }

    public void desconectar() {
        cliente.close();
        cliente = null;
    }

    public MongoClient getCliente() {
        return cliente;
    }

    public ArrayList<Juego> getJuegos() {
        ArrayList<Juego> lista = new ArrayList<>();

        for (Document document : juegos.find()) {
            lista.add(documentToJuego(document));
        }
        return lista;
    }

    public ArrayList<Disenador> getDisenadores() {
        ArrayList<Disenador> lista = new ArrayList<>();

        for (Document document : disenadores.find()) {
            lista.add(documentToDisenador(document));
        }
        return lista;
    }

    public void guardarObjeto(Object obj) {
        if (obj instanceof Juego) {
            juegos.insertOne(objectToDocument(obj));
        } else if (obj instanceof Disenador) {
            disenadores.insertOne(objectToDocument(obj));
        }
    }

    public void modificarObjeto(Object obj) {
        if (obj instanceof Juego) {
            Juego juego = (Juego) obj;
            juegos.replaceOne(new Document("_id", juego.getId()), objectToDocument(juego));
        } else if (obj instanceof Disenador) {
            Disenador disenador = (Disenador) obj;
            disenadores.replaceOne(new Document("_id", disenador.getId()), objectToDocument(disenador));
        }
    }

    public void eliminarObjeto(Object obj) {
        if (obj instanceof Juego) {
            Juego juego = (Juego) obj;
            juegos.deleteOne(objectToDocument(juego));
        } else if (obj instanceof Disenador) {
            Disenador disenador = (Disenador) obj;
            disenadores.deleteOne(objectToDocument(disenador));
        }
    }

    public Juego documentToJuego(Document dc) {
        Juego juego = new Juego();

        juego.setId(dc.getObjectId("_id"));
        juego.setNombre(dc.getString("nombre"));
        juego.setFechapubli(LocalDate.parse(dc.getString("publicacion")));
        juego.setPrecio((Float.parseFloat(String.valueOf(dc.getDouble("precio")))));
        juego.setDisenador(dc.getObjectId("id_disenador"));
        return juego;
    }

    public Disenador documentToDisenador(Document dc) {
        Disenador disenador = new Disenador();

        disenador.setId(dc.getObjectId("_id"));
        disenador.setNombre(dc.getString("nombre"));
        disenador.setApellidos(dc.getString("apellidos"));
        disenador.setFechanac(LocalDate.parse(dc.getString("nacimiento")));
        return disenador;
    }

    public Document objectToDocument(Object obj) {
        Document dc = new Document();

        if (obj instanceof Juego) {
            Juego juego = (Juego) obj;

            dc.append("nombre", juego.getNombre());
            dc.append("publicacion", juego.getFechapubli().toString());
            dc.append("precio", juego.getPrecio());
            dc.append("id_disenador", juego.getDisenador());
        } else if (obj instanceof Disenador) {
            Disenador disenador = (Disenador) obj;

            dc.append("nombre", disenador.getNombre());
            dc.append("apellidos", disenador.getApellidos());
            dc.append("nacimiento", disenador.getFechanac().toString());

        } else {
            return null;
        }
        return dc;
    }
}
