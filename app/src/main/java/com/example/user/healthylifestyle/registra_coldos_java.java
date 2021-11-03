package com.example.user.healthylifestyle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class registra_coldos_java extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView colasionDos, indica;
    ViewGroup layout;
    boolean abiertoC = false, abiertoF = false, abiertoV = false, abiertoL = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registra_coldos_xml);
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/GrandHotel-Regular.ttf");

        colasionDos = findViewById(R.id.colasionDos);
        indica = findViewById(R.id.indica);
        colasionDos.setTypeface(face);
        face = Typeface.createFromAsset(getAssets(),"fonts/JosefinSans-Italic.ttf");
        indica.setTypeface(face);

        Intent datos = getIntent();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.inicio) {
            // Handle the camera action
        } else if (id == R.id.IMC) {
            Intent screen = new Intent(this, imc_java.class);
            startActivity(screen);

        } else if (id == R.id.consultaCal) {
            Intent screen = new Intent(this, registroAlimentos_java.class);
            startActivity(screen);
        } else if (id == R.id.verDietas) {
            Intent screen = new Intent(this, menus_java.class);
            startActivity(screen);

        } else if (id == R.id.rutEjer) {
            Intent screen = new Intent(this, ver_rut_java.class);
            startActivity(screen);

        } else if (id == R.id.salir) {
            Intent screen = new Intent(this, iniciosesion_java.class);
            startActivity(screen);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @SuppressLint("Inlined Api")
    public void seleccionarCereales(View view) {
        layout = (ViewGroup) findViewById(R.id.grupoCereales);
        if(abiertoC){
            layout.removeAllViews();
            abiertoC= false;
        }
        else{
            LayoutInflater inflater = LayoutInflater.from(this);
            int id = R.layout.grupos_cereales;

            ConstraintLayout relativeLayout = (ConstraintLayout) inflater.inflate(id, null, false);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.topMargin = 15;
            relativeLayout.setPadding(5, 3, 5, 3);
            relativeLayout.setLayoutParams(params);
            layout.addView(relativeLayout);
            abiertoC = true;
        }
    }

    public void seleccionarFrutas(View view) {
        layout = (ViewGroup) findViewById(R.id.grupoFrutas);
        if(abiertoF){
            layout.removeAllViews();
            abiertoF = false;
        }
        else{
            LayoutInflater inflater = LayoutInflater.from(this);
            int id = R.layout.grupo_frutas;

            ConstraintLayout relativeLayout = (ConstraintLayout) inflater.inflate(id, null, false);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.topMargin = 15;
            relativeLayout.setPadding(5, 3, 5, 3);
            relativeLayout.setLayoutParams(params);
            layout.addView(relativeLayout);
            abiertoF = true;
        }
    }

    public void seleccionarVerduras(View view) {
        layout = (ViewGroup) findViewById(R.id.grupoVerduras);
        if(abiertoV){
            layout.removeAllViews();
            abiertoV = false;
        }
        else{
            LayoutInflater inflater = LayoutInflater.from(this);
            int id = R.layout.grupo_verduras;

            ConstraintLayout relativeLayout = (ConstraintLayout) inflater.inflate(id, null, false);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.topMargin = 15;
            relativeLayout.setPadding(5, 3, 5, 3);
            relativeLayout.setLayoutParams(params);
            layout.addView(relativeLayout);
            abiertoV = true;
        }
    }

    public void seleccionarLegumi(View view) {
        layout = (ViewGroup) findViewById(R.id.grupoLegumi);
        if(abiertoL){
            layout.removeAllViews();
            abiertoL = false;
        }
        else{
            LayoutInflater inflater = LayoutInflater.from(this);
            int id = R.layout.grupo_legumi;

            ConstraintLayout relativeLayout = (ConstraintLayout) inflater.inflate(id, null, false);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.topMargin = 15;
            relativeLayout.setPadding(5, 3, 5, 3);
            relativeLayout.setLayoutParams(params);
            layout.addView(relativeLayout);
            abiertoL = true;
        }
    }
}
