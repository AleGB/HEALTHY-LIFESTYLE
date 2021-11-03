package com.example.user.healthylifestyle;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class registroAlimentos_java extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView saludo, indica, desayuno, primeraColasion, comida, segundaColasion, cena;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_alimentos_xml);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/GrandHotel-Regular.ttf");
        saludo = findViewById(R.id.saludo);
        indica = findViewById(R.id.indica);
        desayuno = findViewById(R.id.desayuno);
        primeraColasion = findViewById(R.id.primeraColasion);
        comida = findViewById(R.id.comida);
        segundaColasion = findViewById(R.id.segundaColasion);
        cena = findViewById(R.id.cena);
        TextView [] todos = {saludo, indica, desayuno, primeraColasion, comida, segundaColasion, cena};
        for(int i = 0; i<todos.length; i++){
            todos[i].setTypeface(face);
        }

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


    public void registraDesa(View view) {
        Intent screen = new Intent(this, registraDesayuno_java.class);
        startActivity(screen);
    }

    public void registraColUno(View view){
        Intent screen = new Intent(this, registra_coluno_java.class);
        startActivity(screen);
    }

    public void registraComida(View view){
        Intent screen = new Intent(this, registra_comida_java.class);
        startActivity(screen);
    }

    public void registraColDos(View view){
        Intent screen = new Intent(this, registra_coldos_java.class);
        startActivity(screen);
    }

    public void registraCena(View view){
        Intent screen = new Intent(this, registra_cena_java.class);
        startActivity(screen);
    }

}