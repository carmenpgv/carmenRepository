package com.example.util_idades.compra.bd;

import static android.provider.BaseColumns._ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.NetworkOnMainThreadException;

import com.example.util_idades.compra.util.Producto;
import com.example.util_idades.compra.util.Util;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    private static final int VERSION = 1;

    public DataBase(Context contexto){
        super(contexto, Constantes.BASE_DATOS, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Constantes.TABLA_PRODUCTOS + " (" + _ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constantes.NOMBRE + " TEXT, " +
                Constantes.MARCA + " TEXT, " +
                Constantes.SECCION + " TEXT, " +
                Constantes.PRECIO + " REAL, " +
                Constantes.FOTO + " BLOB) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Constantes.TABLA_PRODUCTOS);
        onCreate(db);
    }

    public void nuevoProducto(Producto producto){

        ContentValues values= new ContentValues();
        values.put(Constantes.NOMBRE, producto.getNombre());
        values.put(Constantes.MARCA, producto.getMarca());
        values.put(Constantes.SECCION, producto.getSeccion());
        values.put(Constantes.PRECIO, producto.getPrecio());

        values.put(Constantes.FOTO, Util.getBytes(producto.getFoto()));
        SQLiteDatabase db=getWritableDatabase();
        db.insertOrThrow(Constantes.TABLA_PRODUCTOS,null, values);
        db.close();

    }

    public void eliminarProducto(Producto producto){
        String[] argumentos = new String[]{String.valueOf(producto.getId())};
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Constantes.TABLA_PRODUCTOS, _ID + "=?",argumentos);
        db.close();
    }

    public ArrayList<Producto> getProductos(){
        ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        Producto producto = null;

        final String[] SELECT = {_ID, Constantes.NOMBRE, Constantes.MARCA, Constantes.SECCION,
        Constantes.PRECIO, Constantes.FOTO};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Constantes.TABLA_PRODUCTOS, SELECT, null, null,
                null, null, Constantes.NOMBRE);

        while (cursor.moveToNext()){
            producto = new Producto();
            producto.setId(cursor.getLong(0));
            producto.setNombre(cursor.getString(1));
            producto.setMarca(cursor.getString(2));
            producto.setSeccion(cursor.getString(3));
            producto.setPrecio(cursor.getFloat(4));
            producto.setFoto(Util.getBitmap(cursor.getBlob(5)));
            listaProductos.add(producto);
        }
        cursor.close();
        db.close();
        return listaProductos;
    }
    public ArrayList<Producto> getProductosNombre(String nombre){
        ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        Producto producto = null;

        final String[] SELECT = {_ID, Constantes.NOMBRE, Constantes.MARCA, Constantes.SECCION,
                Constantes.PRECIO, Constantes.FOTO};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Constantes.TABLA_PRODUCTOS, SELECT, Constantes.NOMBRE + "=?", new String[]{nombre},
                null, null, Constantes.NOMBRE);

        while (cursor.moveToNext()){
            producto = new Producto();
            producto.setId(cursor.getLong(0));
            producto.setNombre(cursor.getString(1));
            producto.setMarca(cursor.getString(2));
            producto.setSeccion(cursor.getString(3));
            producto.setPrecio(cursor.getFloat(4));
            producto.setFoto(Util.getBitmap(cursor.getBlob(5)));
            listaProductos.add(producto);
        }
        cursor.close();
        db.close();
        return listaProductos;
    }
}
