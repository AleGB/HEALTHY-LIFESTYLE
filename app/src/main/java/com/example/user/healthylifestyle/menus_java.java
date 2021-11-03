package com.example.user.healthylifestyle;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

public class menus_java extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView pregunta, desayuno, comida, cena;
    private int idUsr;
    private String nmbUsr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menus_xml);
        pregunta = findViewById(R.id.pregunta);
        desayuno = findViewById(R.id.desayuno);
        comida = findViewById(R.id.comida);
        cena = findViewById(R.id.cena);

        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/JosefinSans-Italic.ttf");
        pregunta.setTypeface(face);
        face = Typeface.createFromAsset(getAssets(),"fonts/GrandHotel-Regular.ttf");
        desayuno.setTypeface(face);
        comida.setTypeface(face);
        cena.setTypeface(face);

        bDatos objBD  = new bDatos(this, "healthy", null, 1);
        SQLiteDatabase bd = objBD.getWritableDatabase();

        Intent datos = getIntent();
        idUsr = Integer.parseInt(datos.getStringExtra("idUsr"));
        Cursor nombre = bd.rawQuery("select nmbUsr from usuario where idUsr = "+idUsr, null);
        nombre.moveToFirst();
        nmbUsr = nombre.getString(0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView = navigationView.getHeaderView(0);
        TextView saludo = (TextView)hView.findViewById(R.id.saludo);
        saludo.setText("Hola, "+nmbUsr);
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
            screen.putExtra("idUsr", String.valueOf(idUsr));
            startActivity(screen);
        } else if (id == R.id.consultaCal) {
            Intent screen = new Intent(this, registroAlimentos_java.class);
            startActivity(screen);
        } else if (id == R.id.verDietas) {
            Intent screen = new Intent(this, menus_java.class);
            screen.putExtra("idUsr", String.valueOf(idUsr));
            startActivity(screen);

        } else if (id == R.id.rutEjer) {
            Intent screen = new Intent(this, ver_rut_java.class);
            startActivity(screen);

        } else if (id == R.id.salir) {
            Intent screen = new Intent(this, iniciosesion_java.class);
            startActivity(screen);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void verDesayunos(View view) {
        Intent screen = new Intent(this, menus_java.class);
        screen.putExtra("idUsr", String.valueOf(idUsr));
        screen.putExtra("ver", "Desayuno");
        startActivity(screen);
    }

    public void verComidas(View view) {
        Intent screen = new Intent(this, ver_comida_java.class);
        startActivity(screen);
    }

    public void verCenas(View view) {
        Intent screen = new Intent(this, ver_cenas_java.class);
        startActivity(screen);
    }
}

