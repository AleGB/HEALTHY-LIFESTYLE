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
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class imc_java extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView indicaIMC, informa, recomendacion, indica, tituloTabla, saludo;
    private String nmbUsr, kcal, fecha, _imcS,progreso;
    private int idUsr, i = 0;
    private double _imc, _imcAnterior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imc_xml);
        tabla tabla = new tabla(this, (TableLayout)findViewById(R.id.tabla));
        tabla.agregarCabecera(R.array.cabecera_tabla);
        indicaIMC = findViewById(R.id.indicaIMC);
        informa = findViewById(R.id.informa);
        recomendacion = findViewById(R.id.recomendacion);
        indica = findViewById(R.id.indica);
        tituloTabla = findViewById(R.id.tituloTabla);
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/JosefinSans-Regular.ttf");
        indicaIMC.setTypeface(face);
        recomendacion.setTypeface(face);
        indica.setTypeface(face);
        face = Typeface.createFromAsset(getAssets(),"fonts/JosefinSans-Bold.ttf");
        informa.setTypeface(face);
        tituloTabla.setTypeface(face);

        Intent datos = getIntent();
        idUsr  = Integer.parseInt(datos.getStringExtra("idUsr"));

        bDatos objBD  = new bDatos(this, "healthy", null, 1);
        SQLiteDatabase bd = objBD.getWritableDatabase();

        Cursor nombre = bd.rawQuery("select nmbUsr from usuario where idUsr = "+idUsr, null);
        nombre.moveToFirst();
        nmbUsr = nombre.getString(0);

        Cursor sacaIMC = bd.rawQuery("select IMC from datosAlimenticios where idUsr1 = "+idUsr, null);
        sacaIMC.moveToLast();
        _imc = Double.parseDouble(sacaIMC.getString(0));

        Cursor sacaKcal = bd.rawQuery("select calxDia from usuario where idUsr = "+idUsr, null);
        sacaKcal.moveToFirst();
        kcal = sacaKcal.getString(0);

        indicaIMC.setText(nmbUsr + ", tu IMC es de "+_imc+", de acuerdo a esto, tú debes consumir "+kcal+" kcal por dia.");

        if (_imc < 18.5) {
            informa.setText("Estás un poco bajo de peso. :/");
            recomendacion.setText("Te recomendamos que consumas alimentos altos en calorías y hagas actividad física");
        }
        else{
            if (_imc < 25){
                informa.setText("Estás en tu peso ideal. :D");
                recomendacion.setText("Te recomendamos que continues con tu forma de alimentación y rutina diaria");
            }
            else{
                informa.setText("Estás un poco pasado de peso. :(");
                recomendacion.setText("Te recomendamos que controles diariamente tu consumo de calorías y realices actividad física diaria");
            }
        }

        sacaIMC = bd.rawQuery("select IMC, fecha, progreso from datosAlimenticios where idUsr1 = "+idUsr, null);
        sacaIMC.moveToFirst();


        do{
            _imcS = sacaIMC.getString(0);
            fecha = sacaIMC.getString(1);
            progreso = sacaIMC.getString(2);
            ArrayList<String> elementos = new ArrayList<String>();
            elementos.add(_imcS);
            elementos.add(fecha);
            elementos.add(progreso);
            tabla.agregarFilaTabla(elementos);
        }while(sacaIMC.moveToNext());


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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.agregarIMC);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), nuevo_imc_java.class);
                i.putExtra("idUsr",String.valueOf(idUsr));
                startActivity(i);
            }
        });
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
}
