package com.example.util_idades.compra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.util_idades.R;
import com.example.util_idades.compra.bd.DataBase;
import com.example.util_idades.compra.util.Producto;
import com.example.util_idades.compra.util.ProductoAdapter;

import java.util.ArrayList;

public class CompraActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {

    Button btnAdd;
    Button btnDetail;
    DataBase db;
    ArrayList<Producto> listaProductos;
    ListView lvLista;
    ProductoAdapter adapter;
    Producto productoSeleccionado;
    EditText busqueda;
    ImageButton btnBuscar;
    boolean filtro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        btnAdd = findViewById(R.id.bCompraAdd);
        btnAdd.setOnClickListener(this);
        btnDetail = findViewById(R.id.bCompraDetalle);
        btnDetail.setOnClickListener(this);
        btnBuscar = (ImageButton) findViewById(R.id.bCompraBuscar);
        btnBuscar.setOnClickListener(this);
        busqueda = findViewById(R.id.etCompraBusqueda);


        db = new DataBase(this);

        lvLista = findViewById(R.id.lvCompraLista);
        lvLista.setOnItemClickListener(this);
        lvLista.setOnItemLongClickListener(this);
        listaProductos = new ArrayList<Producto>();
        registerForContextMenu(lvLista);

        filtro = false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()){
            case R.id.lvCompraLista:
                getMenuInflater().inflate(R.menu.lvcompramenu_contextual,menu);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (filtro){
            listaProductos = db.getProductosNombre(busqueda.getText().toString());
            filtro = false;
        }else {
            listaProductos = db.getProductos();
        }
        adapter = new ProductoAdapter(listaProductos, this);
        lvLista.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bCompraAdd:
                Intent i = new Intent(this, AddActivity.class);
                startActivity(i);
                break;
            case R.id.bCompraDetalle:
                detalle();
                break;
            case R.id.bCompraBuscar:
                if (busqueda.getText().toString().equals("")){
                    onResume();
                }else{
                    filtro = true;
                    onResume();
                }
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnCompraDetalle:
                detalle();
                break;
            case R.id.mnCompraBorrar:
                confirmarBorrado();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        productoSeleccionado = listaProductos.get(i);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        productoSeleccionado = listaProductos.get(i);
        return false;
    }

    public void detalle(){
        Intent intent = new Intent(this, DetailActivity.class);
        productoSeleccionado.putExtraProducto(intent);
        startActivity(intent);
    }

    public void confirmarBorrado(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Alerta de borrado")
                .setMessage("¿Estás seguro de que quieres borrar?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.eliminarProducto(productoSeleccionado);
                        onResume();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}