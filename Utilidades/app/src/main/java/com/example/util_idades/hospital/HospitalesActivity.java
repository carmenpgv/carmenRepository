package com.example.util_idades.hospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.util_idades.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class HospitalesActivity extends AppCompatActivity implements LocationListener, View.OnClickListener{
    ListView listaHospitales;
    ArrayList<Hospital>hospitales;
    TextView tvHospitalCercano;
    TextView tvHospitales;
    Button bHospitalCercano;
    final int SOLICITUD_PERMISO_LOCALIZACION=42;
    Location localizacion;
    LocationManager gestor;
    static final String MIURL="https://www.zaragoza.es/sede/servicio/equipamiento/basic/hospitales.json?srsname=utm30n_etrs89";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitales);
        listaHospitales = findViewById(R.id.lvHospitales);

        hospitales = new ArrayList<Hospital>();

        tvHospitalCercano=findViewById(R.id.tvHospitalesHospital);
        tvHospitales=findViewById(R.id.tvListaHospitales);
        bHospitalCercano = findViewById(R.id.btnHospitalesBuscar);
        bHospitalCercano.setOnClickListener(this);
        gestor = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Tarea tarea = new Tarea();
        tarea.execute(MIURL);
    }

    public void pintarHospitales(){
        ArrayAdapter<Hospital>adapter = new ArrayAdapter<Hospital>(this, android.R.layout.simple_list_item_1,hospitales);
        listaHospitales.setAdapter(adapter);
    }

    private void obtenerLocalizacion(){
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            if(gestor.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                localizacion = gestor.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if(localizacion==null){
                    Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                    tvHospitales.setVisibility(View.VISIBLE);
                    listaHospitales.setVisibility(View.VISIBLE);
                }else {
                    Hospital hosMasCerca = new Hospital();
                    Hospital unHospital;
                    Float distancia = 0f;
                    for (int i = 0;i<hospitales.size();i++){
                        unHospital = hospitales.get(i);
                        Location hospitalLocation = new Location("");
                        hospitalLocation.setLatitude(unHospital.getLatitud());
                        hospitalLocation.setLongitude(unHospital.getLongitud());
                        if(i==0){
                            hosMasCerca = unHospital;
                            distancia = Math.abs(localizacion.distanceTo(hospitalLocation));
                        }else {
                            if ((Math.abs(localizacion.distanceTo(hospitalLocation))<distancia)){
                                hosMasCerca = unHospital;
                                distancia = Math.abs(localizacion.distanceTo(hospitalLocation));
                            }
                        }
                    }
                    tvHospitalCercano.setText(hosMasCerca.toString());
                }
            }else{
                Toast.makeText(this, "Activa el GPS", Toast.LENGTH_SHORT).show();
            }
        }else {
            Util.solicitarPermiso(Manifest.permission.ACCESS_FINE_LOCATION,
                    "Este permiso es necesario para poder geolocalizarte",
                    SOLICITUD_PERMISO_LOCALIZACION,this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case SOLICITUD_PERMISO_LOCALIZACION:
                if(grantResults.length==1 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    obtenerLocalizacion();
                }else{
                    Toast.makeText(this,"Por favor activa el permiso de localización",Toast.LENGTH_LONG).show();
                    tvHospitales.setVisibility(View.VISIBLE);
                    listaHospitales.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        obtenerLocalizacion();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnHospitalesBuscar:
                obtenerLocalizacion();
                break;
        }
    }


    public class Tarea extends AsyncTask<String, Void, Void> {
        private boolean error = false;
        @Override
        protected Void doInBackground(String... strings) {
            String cadena;
            JSONObject json;
            JSONArray jsonArray;

            try{
                URL url = new URL(MIURL);
                HttpsURLConnection conexion=(HttpsURLConnection) url.openConnection();
                BufferedReader br= new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String linea = null;
                while((linea=br.readLine())!=null){
                    sb.append(linea + "\n");
                }
                conexion.disconnect();
                br.close();
                cadena = sb.toString();

                json = new JSONObject(cadena);
                jsonArray = json.getJSONArray("result");

                Hospital hospital;
                String nombre,telefono,direccion, latitud,longitud;
                for (int i=0;i<jsonArray.length();i++){
                    try {
                        nombre = jsonArray.getJSONObject(i).getString("title");
                        try {
                            telefono = jsonArray.getJSONObject(i).getString("telephone");
                        } catch (JSONException jse) {
                            jse.printStackTrace();
                            telefono = "Ningún teléfono disponible";
                        }
                        try {
                            direccion = jsonArray.getJSONObject(i).getString("streetAddress");
                        } catch (JSONException jse) {
                            jse.printStackTrace();
                            direccion = "Ninguna dirección disponible";
                        }
                        latitud = jsonArray.getJSONObject(i).getString("latitud");
                        longitud = jsonArray.getJSONObject(i).getString("longitud");
                        hospital = new Hospital();
                        hospital.setNombre(nombre);
                        hospital.setTelefono(telefono);
                        hospital.setDireccion(direccion);
                        System.out.println(hospital.getNombre());
                        hospital.setLatitud(Double.parseDouble(latitud));
                        hospital.setLongitud(Double.parseDouble(longitud));
                        hospitales.add(hospital);
                    }catch (JSONException jse){
                        jse.printStackTrace();
                    }
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
                error = true;
            }catch (JSONException jse){
                jse.printStackTrace();
                error = true;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if(error){
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                return;
            }
            pintarHospitales();
        }
    }
}