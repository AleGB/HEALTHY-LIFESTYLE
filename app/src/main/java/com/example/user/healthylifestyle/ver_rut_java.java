package com.example.user.healthylifestyle;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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

public class ver_rut_java extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //El código que aquí se les presenta es propiedad exclusiva de Ángel Alavez Dávila y Karla Alejandra Gracia Barajas,
    //cualquier alteración o plagió será castigado por la ley y nos reservamos los derechos de todo el código desarrollado del proyecto Healthy LifeStyle.

    TextView desayuno1, desayuno2, desayuno3, desayuno4, dd1, dd2, dd3, dd4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_rut_xml);

        desayuno1 = findViewById(R.id.comida1);
        desayuno2 = findViewById(R.id.desayuno2);
        desayuno3 = findViewById(R.id.desayuno3);
        desayuno4 = findViewById(R.id.desayuno4);
        dd1 = findViewById(R.id.descripcionD1);
        dd2 = findViewById(R.id.descripcionC2);
        dd3 = findViewById(R.id.descripcionD3);
        dd4 = findViewById(R.id.descripcionC4);


        TextView [] titulos = {desayuno1, desayuno2, desayuno3, desayuno4};
        TextView [] descripciones = {dd1, dd2, dd3, dd4};

        for(int i = 0; i<titulos.length; i++){
            Typeface face=Typeface.createFromAsset(getAssets(),"fonts/JosefinSans-Italic.ttf");
            titulos[i].setTypeface(face);
            descripciones[i].setTypeface(face);

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

    public void verEstira(View view) {
        Intent screen = new Intent(this, estiramientos_java.class);
        startActivity(screen);
    }

    public void verCardio(View view) {
        Intent screen = new Intent(this, cardio_java.class);
        startActivity(screen);
    }

    public void verAbdomen(View view) {
        Intent screen = new Intent(this, abdomen_java.class);
        startActivity(screen);
    }

    public void verPesas(View view) {
        Intent screen = new Intent(this, pesas_java.class);
        startActivity(screen);
    }
}
