package com.example.user.healthylifestyle;

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.media.MediaCas;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class valida_correo_java extends AppCompatActivity implements View.OnClickListener{

    private TextView aviso;
    private EditText confirmaClave;
    private Button aceptar;
    private String clave = "", confClave;
    private int idUsr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.valida_correo_xml);

        aviso = findViewById(R.id.aviso);
        confirmaClave = findViewById(R.id.confirmaClave);
        aceptar = findViewById(R.id.valida);
        aceptar.setOnClickListener(this);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/JosefinSans-Italic.ttf");
        aviso.setTypeface(face);

        Intent datos = getIntent();
        idUsr = Integer.parseInt(datos.getStringExtra("idUsr"));
        clave = datos.getStringExtra("validacion");
    }

    @Override
    public void onClick(View v) {
        confClave = confirmaClave.getText().toString();
        if(confClave.isEmpty()){
            Toast.makeText(this, "Campo vacío. Ingresa la clave.", Toast.LENGTH_SHORT).show();
        }
        else{
            if(confClave.equals(clave)){
                bDatos objBD  = new bDatos(this, "healthy", null, 1);
                SQLiteDatabase bd = objBD.getWritableDatabase();
                bd.execSQL("update usuario set validado = 'Si' where idUsr = "+idUsr);
                bd.close();
                Intent screen = new Intent(this, imc_java.class);
                screen.putExtra("idUsr", String.valueOf(idUsr));
                startActivity(screen);
                finish();
            }
            else{
                Toast.makeText(getApplicationContext(), "Las claves no son correctas. Inténtalo de nuevo.", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
