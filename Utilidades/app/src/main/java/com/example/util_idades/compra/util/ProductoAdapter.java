package com.example.util_idades.compra.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.util_idades.R;

import java.util.ArrayList;

public class ProductoAdapter extends BaseAdapter {

    private ArrayList<Producto> listaProductos;
    private Context context;
    private LayoutInflater inflater;

    static class ViewHolder{
        ImageView foto;
        TextView nombre;
        TextView seccion;
        TextView precio;
    }

    public ProductoAdapter(ArrayList<Producto> listaProductos, Activity context) {
        this.listaProductos = listaProductos;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listaProductos.size();
    }

    @Override
    public Object getItem(int i) {
        return listaProductos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            view = inflater.inflate(R.layout.fila,null);
            holder = new ViewHolder();
            holder.foto = view.findViewById(R.id.ivFila);
            holder.nombre = view.findViewById(R.id.tvFilaNombre);
            holder.seccion = view.findViewById(R.id.tvFilaSeccion);
            holder.precio = view.findViewById(R.id.tvFilaPrecio);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Producto producto = listaProductos.get(i);
        holder.foto.setImageBitmap(producto.getFoto());
        holder.nombre.setText(producto.getNombre());
        holder.seccion.setText(producto.getSeccion());
        holder.precio.setText(producto.getPrecio() + " €");

        return view;
    }
}
