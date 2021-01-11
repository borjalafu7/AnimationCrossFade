package com.borjalapa.animationcrossfade;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar pbLoading;
    TextView tvContenido;

    int duracionAnimacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pbLoading = findViewById(R.id.pbLoading);
        tvContenido = findViewById(R.id.tvContenido);

        //ajustar la duracion de la animacion
        duracionAnimacion = getResources().getInteger(android.R.integer.config_longAnimTime);

        //hacemos que el texto se vaya
        tvContenido.setVisibility(View.GONE);

        //hacer un timeout de 3 segundos para simular la carga del texto con la animacion
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                crossFadeAnimation();
            }
        }, 3000);

    }

    private void crossFadeAnimation() {
        //hacemos el texto transparente pero ocupa sitio por el visible
        tvContenido.setAlpha(0f);
        tvContenido.setVisibility(View.VISIBLE);

        //animamos el texto
        tvContenido.animate()
                //quitamos el transparente y le ponemos una duracion
                .alpha(1f)
                .setDuration(duracionAnimacion);

        pbLoading.animate()
                .alpha(0f)
                .setDuration(duracionAnimacion)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        pbLoading.setVisibility(View.GONE);
                    }
                });

    }
}