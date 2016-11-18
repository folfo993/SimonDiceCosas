package com.example.amiguezotero.botonescolores;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    Button Start,Bgreen, Byellow, Bred,Bblue;
    Timer timer;
    TimerTask JobTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /*
        El fichero de sonidos que descargue no puede decomprimirlo en el pc de clase por el formato del archivo.

        sonido1 = MediaPlayer.create(this, R.raw.green);
        sonido2 = MediaPlayer.create(this, R.raw.red);
        sonido3 = MediaPlayer.create(this, R.raw.blue);
        sonido4 = MediaPlayer.create(this, R.raw.yellow);
        sonidovictoria = MediaPlayer.create(this, R.raw.win);
        sonidoderrota = MediaPlayer.create(this, R.raw.loose);*/

    Start = (Button) findViewById(R.id.bstart);
    Bgreen = (Button) findViewById(R.id.bgreen);
    Byellow = (Button) findViewById(R.id.byellow);
    Bred = (Button) findViewById(R.id.bred);
    Bblue = (Button) findViewById(R.id.bblue);

    Bgreen.setEnabled(false);
    Byellow.setEnabled(false);
    Bred.setEnabled(false);
    Bblue.setEnabled(false);
    }

    ArrayList<Integer> randomColor = new ArrayList();
    ArrayList<Integer> ChosenColor = new ArrayList();
    protected static int LVL=3; // Indica el nivel
    protected static int CONT=0;// Cuenta las veces que pulsamos un botón.

    int Level=1;
    void StartEvent(View a){ //Ejecutará el juego

        CONT =0;
        StartTimer();

    }
    void InitTimer () { // Este metodo hará que los parpadeos de los botones se respeten y no se colapsen.
        JobTimer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int numero = Random();
                        randomColor.add(numero);

                        if (numero == 0) {
                            Bgreen.setBackgroundColor(Color.GREEN);
                            //sonido1.start();
                            Bgreen.postDelayed(new Runnable() {
                                public void run() {
                                    Bgreen.setBackgroundColor(Color.parseColor("#1EA307"));
                                }
                            }, 500);

                        }

                        if (numero == 1) {
                            Bred.setBackgroundColor(Color.RED);
                            //sonido2.start();
                            Bred.postDelayed(new Runnable() {
                                public void run() {
                                    Bred.setBackgroundColor(Color.parseColor("#CD3813"));
                                }
                            }, 500);


                        }
                        if (numero == 2) {
                            Bblue.setBackgroundColor(Color.BLUE);
                            //sonido3.start();
                            Bblue.postDelayed(new Runnable() {
                                public void run() {
                                    Bblue.setBackgroundColor(Color.parseColor("#136CF1"));
                                }
                            }, 500);

                        }
                        if (numero == 3) {
                            Byellow.setBackgroundColor(Color.YELLOW);
                            // sonido4.start();
                            Byellow.postDelayed(new Runnable() {
                                public void run() {
                                    Byellow.setBackgroundColor(Color.parseColor("#D4E113"));
                                }
                            }, 500);


                        }
                        CONT++;
                        if (CONT == LVL) {
                            StopTimer();
                            CONT = 0;
                        }

                    }
                });

            }
        };
        LVL++;
    }
    void eventoverde(View gr){
        Bgreen = (Button) findViewById(R.id.bgreen);
        //sonido1.start();
        ChosenColor.add(0);
        Bgreen.setBackgroundColor(Color.GREEN);

        final long changeTime = 200L;
        Bgreen.postDelayed(new Runnable() {
            @Override
            public void run() {
                Bgreen.setBackgroundColor(Color.parseColor("#1EA307"));

            }
        }, changeTime);
        CONT++;
        comprobar();

    }
    void eventorojo(View r){
        final Button Bred = (Button) findViewById(R.id.bred);
        //sonido2.start();
        ChosenColor.add(1);
        Bred.setBackgroundColor(Color.RED);
        final long changeTime = 200L;
        Bred.postDelayed(new Runnable() {
            @Override
            public void run() {
                Bred.setBackgroundColor(Color.parseColor("#CD3813"));

            }
        }, changeTime);
        CONT++;
        comprobar();

    }
    void eventoazul(View bl){
        final Button Bblue = (Button) findViewById(R.id.bblue);
        //sonido3.start();
        ChosenColor.add(2);
        Bblue.setBackgroundColor(Color.BLUE);
        final long changeTime = 200L;
        Bblue.postDelayed(new Runnable() {
            @Override
            public void run() {
                Bblue.setBackgroundColor(Color.parseColor("#136CF1"));

            }
        }, changeTime);
        CONT++;
        comprobar();

    }

    void eventoamarillo(View ye){
        final Button ButtonYellow = (Button) findViewById(R.id.byellow);
        //sonido4.start();
        ChosenColor.add(3);
        ButtonYellow.setBackgroundColor(Color.YELLOW);
        final long changeTime = 200L;
        ButtonYellow.postDelayed(new Runnable() {
            @Override
            public void run() {
                ButtonYellow.setBackgroundColor(Color.parseColor("#D4E113"));

            }
        }, changeTime);
        CONT++;
        comprobar();

    }

        public void StartTimer(){ // Llama a InitTimer y asigna el tiempo de ejecucion de cada boton.
            timer = new Timer();
            InitTimer();
            timer.schedule(JobTimer, 100, 1000);
        }
        public void StopTimer(){ // Metodo para parar el timer.
            if (timer !=null){
                timer.cancel();
                timer= null;
            }
        }

    void comprobar() { // Comprueba los Arrays de los colores generados aleatoriamente y los escogidos.
        if (CONT == LVL) { //Quita los colores generados anteriormente para dejar paso a los nuevos.
            String ran = randomColor.toString();
            String chos = ChosenColor.toString();
            if (ran.equals(chos)) {
                Toast.makeText(getApplicationContext(), "HAS GANADO", Toast.LENGTH_SHORT).show();
                //sonidovictoria.start();
                int numColor = (int) Math.floor(Math.random()*4);
                randomColor.add(numColor);
                randomColor.clear();
                ChosenColor.clear();
                Level++;
            } else { // En caso de perder se restablece la dificultad al primer nivel.
                Toast.makeText(getApplicationContext(), "HAS PERDIDO", Toast.LENGTH_SHORT).show();
                //sonidoderrota.start();
                CONT=0;

                LVL=3;
                Level=1;
            }
            ChosenColor.clear();

        }
    }

    public int Random(){ // Metodo que genera numeros aleatorios a los cuales asignaremos un color.
        int numColor = (int) Math.floor(Math.random()*4);
        return numColor;



    }
}
