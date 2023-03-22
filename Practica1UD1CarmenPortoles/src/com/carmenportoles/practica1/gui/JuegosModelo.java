package com.carmenportoles.practica1.gui;

import com.carmenportoles.practica1.base.Abstracto;
import com.carmenportoles.practica1.base.Juego;
import com.carmenportoles.practica1.base.Tematico;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author carmen portolés
 * esta clase contiene los métodos relacionados con el arrayList de los juegos que se crean
 */
public class JuegosModelo {
    private ArrayList<Juego>listaJuegos;

    /**
     * constructor
     */
    public JuegosModelo(){
        listaJuegos = new ArrayList<Juego>();
    }

    /**
     * devuelve el arrayList de los juegos guardados
     * @return
     */
    public ArrayList<Juego> obtenerJuegos(){
        return listaJuegos;
    }

    /**
     * crea un objeto juego abstracto y lo guarda en el arrayList
     * @param nombre
     * @param editorial
     * @param nJugadores
     * @param fechaPublicacion
     * @param edadMinima
     */
    public void altaAbstracto(String nombre, String editorial, String nJugadores,
                              LocalDate fechaPublicacion, int edadMinima){
        Abstracto nuevoAbstracto = new Abstracto(nombre,editorial,nJugadores,fechaPublicacion,edadMinima);
        listaJuegos.add(nuevoAbstracto);
    }

    /**
     * crea un objeto juego tematico y lo guarda en el arrayList
     * @param nombre
     * @param editorial
     * @param nJugadores
     * @param fechaPublicacion
     * @param tematica
     */
    public void altaTematico(String nombre, String editorial, String nJugadores, LocalDate fechaPublicacion,
                             String tematica){
        Tematico nuevoTematico = new Tematico(nombre,editorial,nJugadores,fechaPublicacion,tematica);
        listaJuegos.add(nuevoTematico);

    }

    /**
     * si existe un juego en el arrayList con el nombre pasado por parámetro devuelve true, si no devuelve false
     * @param nombre
     * @return
     */
    public boolean existeNombre(String nombre){
        for(Juego juego: listaJuegos){
            if(juego.getNombre().equals(nombre)){
                return true;
            }
        }
        return false;
    }

    /**
     * crea un archivo xml con los elementos del arrayList
     * @param fichero
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null,"xml",null);

        Element raiz = documento.createElement("Juegos");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoJuego = null;
        Element nodoDatos = null;
        Text texto = null;

        for (Juego juego: listaJuegos){
            if(juego instanceof Abstracto){
                nodoJuego = documento.createElement("Abstracto");
            }else{
                nodoJuego = documento.createElement("Tematico");
            }
            raiz.appendChild(nodoJuego);

            nodoDatos = documento.createElement("nombre");
            nodoJuego.appendChild(nodoDatos);
            texto = documento.createTextNode(juego.getNombre());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("editorial");
            nodoJuego.appendChild(nodoDatos);
            texto = documento.createTextNode(juego.getEditorial());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("duracion");
            nodoJuego.appendChild(nodoDatos);
            texto = documento.createTextNode(juego.getNJugadores());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("fecha-publicacion");
            nodoJuego.appendChild(nodoDatos);
            texto = documento.createTextNode(juego.getFechaPublicacion().toString());
            nodoDatos.appendChild(texto);

            if(juego instanceof Abstracto){
                nodoDatos = documento.createElement("edad-minima");
                nodoJuego.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Abstracto) juego).getEdadMinima()));
                nodoDatos.appendChild(texto);
            }
            if(juego instanceof Tematico){
                nodoDatos = documento.createElement("tematica");
                nodoJuego.appendChild(nodoDatos);
                texto = documento.createTextNode(((Tematico) juego).getTematica());
                nodoDatos.appendChild(texto);
            }

            Source source = new DOMSource(documento);
            Result resultado = new StreamResult(fichero);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source,resultado);
        }
    }

    /**
     * rellena el arrayList con los elementos de un xml
     * @param fichero
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaJuegos = new ArrayList<Juego>();
        Abstracto nuevoAbstracto = null;
        Tematico nuevoTematico = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(fichero);
        NodeList listaElementos = document.getElementsByTagName("*");

        for (int i=0; i<listaElementos.getLength(); i++){
            Element nodoJuego = (Element) listaElementos.item(i);

            if(nodoJuego.getTagName().equals("Abstracto")){
                nuevoAbstracto = new Abstracto();
                nuevoAbstracto.setNombre(nodoJuego.getChildNodes().item(0).getTextContent());
                nuevoAbstracto.setEditorial(nodoJuego.getChildNodes().item(1).getTextContent());
                nuevoAbstracto.setNJugadores(nodoJuego.getChildNodes().item(2).getTextContent());
                nuevoAbstracto.setFechaPublicacion(LocalDate.parse(nodoJuego.getChildNodes().item(3).getTextContent()));
                nuevoAbstracto.setEdadMinima(Integer.parseInt(nodoJuego.getChildNodes().item(4).getTextContent()));

                listaJuegos.add(nuevoAbstracto);
            }else{
                if(nodoJuego.getTagName().equals("Tematico")){
                    nuevoTematico = new Tematico();
                    nuevoTematico.setNombre(nodoJuego.getChildNodes().item(0).getTextContent());
                    nuevoTematico.setEditorial(nodoJuego.getChildNodes().item(1).getTextContent());
                    nuevoTematico.setNJugadores(nodoJuego.getChildNodes().item(2).getTextContent());
                    nuevoTematico.setFechaPublicacion(LocalDate.parse(nodoJuego.getChildNodes().item(3).getTextContent()));
                    nuevoTematico.setTematica(nodoJuego.getChildNodes().item(4).getTextContent());

                    listaJuegos.add(nuevoTematico);
                }
            }
        }
    }

}
