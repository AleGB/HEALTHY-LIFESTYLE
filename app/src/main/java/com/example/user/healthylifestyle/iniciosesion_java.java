package com.example.user.healthylifestyle;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class iniciosesion_java extends AppCompatActivity implements View.OnClickListener{
    private TextView bienvenida, sinCuenta, recuperar;
    private Button iniciarSesion;
    private EditText usr, contra;
    private String usrS = "", contraS = "", validado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciosesion_xml);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/JosefinSans-Italic.ttf");
        bienvenida = findViewById(R.id.bienvenida);
        bienvenida.setTypeface(face);
        sinCuenta = findViewById(R.id.sinCuenta);
        sinCuenta.setTypeface(face);
        recuperar = findViewById(R.id.recuperar);
        recuperar.setOnClickListener(this);
        face = Typeface.createFromAsset(getAssets(), "fonts/JosefinSans-Regular.ttf");
        iniciarSesion = findViewById(R.id.inciarSesion);
        iniciarSesion.setTypeface(face);
        iniciarSesion.setOnClickListener(this);
        usr = findViewById(R.id.usr);
        contra = findViewById(R.id.contra);
        Intent datos = getIntent();

    }

    public void irRegistro(View view) {
        Intent screen = new Intent(this, registro_java.class);
        startActivity(screen);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.inciarSesion:
                boolean todoBien = true;
                usrS = usr.getText().toString();
                contraS = contra.getText().toString();
                String [] todos = {usrS, contraS};
                for(int i = 0; i<todos.length; i++){
                    if(todos[i].isEmpty()){
                        todoBien = false;
                    }
                }
                if(todoBien){
                    String contraChida;
                    String idUsrS;
                    bDatos objBD  = new bDatos(this, "healthy", null, 1);
                    SQLiteDatabase bd = objBD.getWritableDatabase();
                    Cursor nombre = bd.rawQuery("select idUsr, contraseña, validado from usuario where nmbUsr = '"+usrS+"'", null);
                    if(nombre.moveToFirst()){
                        contraChida = nombre.getString(1);
                        idUsrS = nombre.getString(0);
                        validado = nombre.getString(2);
                        if(contraChida.equals(contraS)){
                            if(validado.equals("Si")){
                                Intent screen = new Intent(this, imc_java.class);
                                screen.putExtra("idUsr", idUsrS);
                                startActivity(screen);
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "No has validado tu cuenta. Tu estado es: " +validado, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "La contraseña es incorrecta.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "No existe un usuario con ese nombre.", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Ingrese todos los campos.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.recuperar:
                this.deleteDatabase("healthy");
                Toast.makeText(getApplicationContext(), "Se eliminó la base.", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
