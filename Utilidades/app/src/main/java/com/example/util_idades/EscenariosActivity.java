package com.example.util_idades;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EscenariosActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView persona1;
    ImageView persona2;
    ImageView action;
    ImageView lugar;
    TextView escenario;
    Button bCrear;

    ArrayList<Imagen>personas;
    ArrayList<Imagen>actions;
    ArrayList<Imagen>lugares;

    Imagen pers1;
    Imagen pers2;
    Imagen act;
    Imagen lug;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escenarios);

        persona1 = findViewById(R.id.ivEscenariosPersona1);
        persona2 = findViewById(R.id.ivEscenariosPersona2);
        action = findViewById(R.id.ivEscenariosAction);
        lugar = findViewById(R.id.ivEscenariosLugar);
        bCrear = findViewById(R.id.btnEscenariosCrear);
        bCrear.setOnClickListener(this);
        escenario = findViewById(R.id.tvEscenariosEscenario);

        prefs = getDefaultSharedPreferences(getApplicationContext());
        personas = new ArrayList<Imagen>();
        if(prefs.getBoolean("cbbb", false)) {
            personas.add(new Imagen(R.drawable.persona01, "Jesse Pinkman"));
            personas.add(new Imagen(R.drawable.persona02, "Walter White"));
            personas.add(new Imagen(R.drawable.persona03, "Skyler White"));
            personas.add(new Imagen(R.drawable.persona04, "Saul Goodman"));
            personas.add(new Imagen(R.drawable.persona05, "Gus Fring"));
            personas.add(new Imagen(R.drawable.persona06, "Jane Margolis"));
        }
        if(prefs.getBoolean("cbaa", false)) {
            personas.add(new Imagen(R.drawable.persona07, "Phoenix Wright"));
            personas.add(new Imagen(R.drawable.persona08, "Miles Edgeworth"));
            personas.add(new Imagen(R.drawable.persona09, "Maya Fey"));
            personas.add(new Imagen(R.drawable.persona10, "Mia Fey"));
            personas.add(new Imagen(R.drawable.persona11, "Diego Armando"));
            personas.add(new Imagen(R.drawable.persona12, "Franziska Von Karma"));
        }
        if(prefs.getBoolean("cbjojos", false)) {
            personas.add(new Imagen(R.drawable.persona13, "Joseph Joestar"));
            personas.add(new Imagen(R.drawable.persona14, "Muhammad Avdol"));
            personas.add(new Imagen(R.drawable.persona15, "Jotaro Kujo"));
            personas.add(new Imagen(R.drawable.persona16, "Jean-Pierre Polnareff"));
            personas.add(new Imagen(R.drawable.persona17, "Kakyoin"));
            personas.add(new Imagen(R.drawable.persona18, "Iggy"));
        }

        actions = new ArrayList<Imagen>();
        actions.add(new Imagen(R.drawable.action01, " recogen fruta "));
        actions.add(new Imagen(R.drawable.action02, " son acusados de asesinato "));
        actions.add(new Imagen(R.drawable.action03, " se toman unas cervezas "));
        actions.add(new Imagen(R.drawable.action04, " se pintan los labios "));
        actions.add(new Imagen(R.drawable.action05, " lideran un ejército de ranas "));
        actions.add(new Imagen(R.drawable.action06, " roban agua bendita "));
        actions.add(new Imagen(R.drawable.action07, " pelean a muerte "));
        actions.add(new Imagen(R.drawable.action08, " cocinan metanfetamina "));
        actions.add(new Imagen(R.drawable.action09, " comparten una tarta de manzana "));
        actions.add(new Imagen(R.drawable.action10, " tienen un combate pokemon "));
        actions.add(new Imagen(R.drawable.action11, " juegan al cuphead "));
        actions.add(new Imagen(R.drawable.action12, " se hacen las uñas "));
        actions.add(new Imagen(R.drawable.action13, " se hacen un selfie "));
        actions.add(new Imagen(R.drawable.action14, " se fuman un porro "));
        actions.add(new Imagen(R.drawable.action15, " bailan "));
        actions.add(new Imagen(R.drawable.action16, " montan un armario "));
        actions.add(new Imagen(R.drawable.action17, " venden sujetadores "));
        actions.add(new Imagen(R.drawable.action18, " se hacen un piercing "));

        lugares = new ArrayList<Imagen>();
        lugares.add(new Imagen(R.drawable.lugar01, "en el juzgado."));
        lugares.add(new Imagen(R.drawable.lugar02, "en la cafetería de Fígaro."));
        lugares.add(new Imagen(R.drawable.lugar03, "en el infierno."));
        lugares.add(new Imagen(R.drawable.lugar04, "en prisión."));
        lugares.add(new Imagen(R.drawable.lugar05, "en una nave espacial."));
        lugares.add(new Imagen(R.drawable.lugar06, "en una iglesia."));
        lugares.add(new Imagen(R.drawable.lugar07, "en la gatoteca."));
        lugares.add(new Imagen(R.drawable.lugar08, "en el parque de atracciones de Naruto."));
        lugares.add(new Imagen(R.drawable.lugar09, "en un hospital."));
        lugares.add(new Imagen(R.drawable.lugar10, "en el ambiente."));
        lugares.add(new Imagen(R.drawable.lugar11, "en la Casa Blanca."));
        lugares.add(new Imagen(R.drawable.lugar12, "en un botellón."));
        lugares.add(new Imagen(R.drawable.lugar13, "en Isla Ballena."));
        lugares.add(new Imagen(R.drawable.lugar14, "en la Tómbola Antojitos."));
        lugares.add(new Imagen(R.drawable.lugar15, "en los baños del Corte Inglés de cañones."));
        lugares.add(new Imagen(R.drawable.lugar16, "en un karaoke de Benidorm."));
        lugares.add(new Imagen(R.drawable.lugar17, "en en el instituto."));
        lugares.add(new Imagen(R.drawable.lugar18, "en en el desierto."));
    }

    private void reiniciar(){
        persona1.setImageResource(R.drawable.incognita);
        persona2.setImageResource(R.drawable.incognita);
        action.setImageResource(R.drawable.incognita);
        lugar.setImageResource(R.drawable.incognita);
    }

    private void crearEscenario(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pers1 = getRandomImage(personas);
                persona1.setImageResource(pers1.getImagen());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        do{
                            pers2 = getRandomImage(personas);
                        }while (pers1==pers2);
                        persona2.setImageResource(pers2.getImagen());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                act = getRandomImage(actions);
                                action.setImageResource(act.getImagen());
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        lug = getRandomImage(lugares);
                                        lugar.setImageResource(lug.getImagen());
                                        construirEscenario();
                                    }
                                },1000);
                            }
                        },1000);
                    }
                },1000);
            }
        },1000);
    }

    private Imagen getRandomImage(ArrayList<Imagen>imagenes){
        int imagen = (int) (Math.random()*imagenes.size());

        return imagenes.get(imagen);
    }

    private void construirEscenario(){
        String p1 = pers1.toString();
        String p2 = pers2.toString();
        String a = act.toString();
        String l = lug.toString();

        escenario.setText(p1 + " y " + p2 + a + l);
    }

    @Override
    public void onClick(View view) {
        if(R.id.btnEscenariosCrear==view.getId()){
            if(!(prefs.getBoolean("cbbb", false))&&!(prefs.getBoolean("cbaa", false))&&!(prefs.getBoolean("cbjojos", false))){
                Toast.makeText(this, "Activa personajes en el menu de preferencias", Toast.LENGTH_SHORT).show();
            }else {
                reiniciar();
                crearEscenario();
            }
        }
    }
}