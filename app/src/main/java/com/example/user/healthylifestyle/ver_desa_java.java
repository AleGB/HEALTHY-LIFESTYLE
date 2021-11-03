package com.example.user.healthylifestyle;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

public class ver_desa_java extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView desayuno1, desayuno2, desayuno3, desayuno4, dd1, dd2, dd3, dd4;
    private int idUsr, tipoPer;
    private String nmbUsr, ver;
    private float _imc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_desa_xml);

        desayuno1 = findViewById(R.id.comida1);
        desayuno2 = findViewById(R.id.desayuno2);
        desayuno3 = findViewById(R.id.desayuno3);
        desayuno4 = findViewById(R.id.desayuno4);
        dd1 = findViewById(R.id.descripcionD1);
        dd2 = findViewById(R.id.descripcionC2);
        dd3 = findViewById(R.id.descripcionD3);
        dd4 = findViewById(R.id.descripcionC4);

        String [] titulos = {"Cuenco de avena con mantequilla de cacahuate", "Granola con leche entera", "Tortilla de huevo con papas", "Sándwich de pan francés y crema de cacahuate"};
        String [] descripciones = {
                "La avena sirve para la digestión, combate el colesterol y suma muchas proteínas. 100 gramos de este cereal aportan 400 calorías. Acompañarla con la energía, grasas saludables y los antioxidantes de la crema de cacahuate y de los arándanos, se gana un sabroso y efectivo desayuno para sumar masa muscular.",
                "Receta alta en calorías y con nutrientes, proteínas y grasas saludables. La granola elaborada en casa es una excelente alternativa para ganar peso en el desayuno. Para un snack saludable entre comidas se pueden hacer barritas individuales.",
                "Los huevos y las papas suman proteínas y almidones, respectivamente, ideales para construir músculo y masa corporal.",
                "Sándwich gustoso y completo en calorías saludables. Se acompañará con frutas para no descuidar el consumo de fibras."
        };

        bDatos objBD  = new bDatos(this, "healthy", null, 1);
        SQLiteDatabase bd = objBD.getWritableDatabase();

        Intent datos = getIntent();
        idUsr = Integer.parseInt(datos.getStringExtra("idUsr"));
        ver = datos.getStringExtra("ver");
        Cursor nombre = bd.rawQuery("select nmbUsr from usuario where idUsr = "+idUsr, null);
        nombre.moveToFirst();
        Cursor imc = bd.rawQuery("select IMC from datosAlimenticios where idUsr1 = "+idUsr, null);
        imc.moveToLast();
        _imc = Float.parseFloat(imc.getString(0));
        nmbUsr = nombre.getString(0);

        if (_imc < 18.5) {
            tipoPer = 1;
        }
        else{
            if (_imc < 25){
                tipoPer = 2;
            }
            else{
                tipoPer = 3;
            }
        }

        TextView [] titulosV = {desayuno1, desayuno2, desayuno3, desayuno4};
        TextView [] descripcionesV = {dd1, dd2, dd3, dd4};

        for(int i = 0; i<titulos.length; i++){
            Typeface face=Typeface.createFromAsset(getAssets(),"fonts/JosefinSans-Italic.ttf");
            titulosV[i].setTypeface(face);
            descripcionesV[i].setTypeface(face);
        }

        if(ver == "Desayuno" && tipoPer == 1){
            for (int k = 0; k<titulosV.length; k++){
                titulosV[k].setText(titulos[k]);
                descripcionesV[k].setText(descripciones[k]);
            }
        }
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

    public void verDesa1(View view) {
        Intent screen = new Intent(this, ver_desa_1_java.class);
        startActivity(screen);
    }

    public void verDesa2(View view) {
        Intent screen = new Intent(this, ver_desa_2_java.class);
        startActivity(screen);
    }

    public void verDesa3(View view) {
        Intent screen = new Intent(this, ver_desa_3_java.class);
        startActivity(screen);
    }

    public void verDesa4(View view) {
        Intent screen = new Intent(this, ver_desa_4_java.class);
        startActivity(screen);
    }


}
