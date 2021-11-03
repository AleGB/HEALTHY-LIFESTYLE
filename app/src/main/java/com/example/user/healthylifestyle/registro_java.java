package com.example.user.healthylifestyle;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class registro_java extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    private TextView bienvenida, sexo, tipoPer;
    private EditText usr, edad, peso, altura, correo, contraseña, confirmaContra;
    private CheckBox activa, sedentaria;
    private Button registrarme;
    private Spinner tipoAct;
    private RadioGroup rgsexo;
    private RadioButton sexoTomado;
    private String [] tiposAct = {"Seleccione cuánta actividad física realiza", "Poca actividad", "Moderada actividad", "Intensa actividad"};
    private String usrS = "", correoS = "", contraseñaS = "", confirmaContraS = "", tipoPerS = "", edadS = "", pesoS = "", alturaS = "", sexoS = "", clave = "", llave = "QWERTYUIOPASDFGHJKLÑZXCVBNM0123456789", correoEnvia, correoRecibe, contraCorreo;
    private float coefAct, imc, calorias, calDesa, calCola, calComida, calCena;
    private int idTipoPer, idUsr;
    private Date fecha;
    private boolean todoBien = false;


    ContentValues registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_xml);
        fecha = new Date();
        usr = findViewById(R.id.usr);
        edad = findViewById(R.id.edad);
        peso  =findViewById(R.id.peso);
        altura = findViewById(R.id.altura);
        correo = findViewById(R.id.correo);
        rgsexo = findViewById(R.id.rgsexo);
        contraseña = findViewById(R.id.contraseña);
        confirmaContra = findViewById(R.id.confirmaContra);
        activa = findViewById(R.id.activa);
        sedentaria = findViewById(R.id.sedentaria);
        tipoAct = findViewById(R.id.tipoAct);
        tipoAct.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tiposAct));
        bienvenida = findViewById(R.id.saludo);
        sexo = findViewById(R.id.sexo);
        tipoPer = findViewById(R.id.tipoPer);
        registrarme = findViewById(R.id.registrarme);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/JosefinSans-Regular.ttf");
        bienvenida.setTypeface(face);
        registrarme.setTypeface(face);
        face=Typeface.createFromAsset(getAssets(),"fonts/JosefinSans-Bold.ttf");
        sexo.setTypeface(face);
        tipoPer.setTypeface(face);
        tipoAct.setEnabled(false);
        registrarme.setOnClickListener(this);
        tipoAct.setOnItemSelectedListener(this);
        Intent datos = getIntent();

    }

    public void tipoPersona(View view) {
        boolean marcado =((CheckBox)view).isChecked();
        switch(view.getId()){
            case R.id.activa:
                if(marcado){
                    tipoAct.setEnabled(true);
                    sedentaria.setChecked(false);
                    sedentaria.setEnabled(false);
                }
                else{
                    tipoAct.setEnabled(false);
                    activa.setChecked(false);
                    sedentaria.setEnabled(true);
                    tipoPerS = "";
                }
                break;
            case R.id.sedentaria:
                if(marcado){
                    tipoAct.setEnabled(false);
                    activa.setChecked(false);
                    activa.setEnabled(false);
                    tipoPerS = "Sedentaria";
                    coefAct = (float) 1.2;
                    idTipoPer = 1;
                }
                else{
                    tipoAct.setEnabled(true);
                    sedentaria.setChecked(false);
                    activa.setEnabled(true);
                    tipoPerS = "";
                }
                break;
        }
    }
    public void sexo(View view) {
        int selected = rgsexo.getCheckedRadioButtonId();
        sexoTomado = findViewById(selected);
        sexoS = sexoTomado.getText().toString();
    }
    public float IMC(float peso, float altura){
        float IMC = 0;
        IMC = peso/(altura*altura);
        return IMC;
    }
    public float calo(float peso, float altura, String sexo, int edad, float coefAct){
        float calxdia = 0;
        if(sexo == "Mujer"){
            calxdia = (float) ((655+(9.6*peso))+((1.8*altura*100)-(4.7*edad))*coefAct);
        }else{
            calxdia = (float) ((66+(13.7*peso))+((5*altura*100)-(6.8*edad))*coefAct);
        }
        return calxdia;
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch(i){
            case 0:
                tipoPerS = "";
                break;
            case 1:
                tipoPerS = "Poca actividad";
                coefAct = (float)1.375;
                idTipoPer = 2;
                break;
            case 2:
                tipoPerS = "Moderada actividad";
                coefAct = (float)1.55;
                idTipoPer = 3;
                break;
            case 3:
                tipoPerS = "Intensa actividad";
                coefAct = (float)1.725;
                idTipoPer = 4;
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        bDatos objBD  = new bDatos(this, "healthy", null, 1);
        SQLiteDatabase bd = objBD.getWritableDatabase();
        ContentValues registro;
        usrS = usr.getText().toString();
        correoS = correo.getText().toString();
        contraseñaS = contraseña.getText().toString();
        confirmaContraS = confirmaContra.getText().toString();
        edadS = edad.getText().toString();
        pesoS = peso.getText().toString();
        alturaS = altura.getText().toString();
        String [] campos = {usrS, correoS, contraseñaS, confirmaContraS, tipoPerS,  edadS, pesoS, alturaS};
        for(int i = 0; i < 10; i++){
            clave += (llave.charAt((int)(Math.random() * llave.length())));
        }
        for(int i = 0; i<campos.length; i++) {
            if (campos[i].isEmpty()) {
                Toast.makeText(getApplicationContext(), "Ingrese todos los campos.", Toast.LENGTH_SHORT).show();
                todoBien = false;
                break;
            } else {
                todoBien = true;
            }
        }
        if(todoBien){
            if(Objects.equals(contraseñaS, confirmaContraS)){
                Cursor nombre = bd.rawQuery("select * from usuario where nmbUsr = '" +usrS+"'", null);
                if(nombre.moveToFirst()){
                    Toast.makeText(this, "Ya existe un usuario con ese nombre, por favor intente con otro.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Cursor email = bd.rawQuery("select * from usuario where correo = '"+correoS+"'", null);
                    if(email.moveToFirst()){
                        Toast.makeText(this, "Ya existe un usuario con ese correo, por favor intente con otro.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        registro = new ContentValues();
                        imc = IMC(Float.parseFloat(pesoS), Float.parseFloat(alturaS));
                        calorias = calo(Float.parseFloat(pesoS), Float.parseFloat(alturaS), sexoS, Integer.parseInt(edad.getText().toString()), coefAct);
                        calDesa = (26*calorias)/100;
                        calCola = (9*calorias)/100;
                        calComida = (42*calorias)/100;
                        calCena = (9*calorias)/100;
                        registro.put("nmbUsr", usrS);
                        registro.put("correo", correoS);
                        registro.put("edad", Integer.parseInt(edadS));
                        registro.put("sexo", sexoS);
                        registro.put("contraseña", contraseñaS);
                        registro.put("calxDia", calorias);
                        registro.put("calDesa", calDesa);
                        registro.put("calCola", calCola);
                        registro.put("calComida", calComida);
                        registro.put("calCena", calCena);
                        registro.put("validado", "N");
                        registro.put("idTipo1", idTipoPer);
                        bd.insert("usuario", null, registro);
                        Cursor id = bd.rawQuery("select idUsr from usuario where nmbUsr = '"+usrS+"'", null);
                        id.moveToFirst();
                        idUsr =  Integer.parseInt(id.getString(0));
                        registro = new ContentValues();
                        registro.put("IMC", imc);
                        registro.put("fecha", String.valueOf(fecha));
                        registro.put("progreso", "No existe progeso.");
                        registro.put("idUsr1", idUsr);
                        bd.insert("datosAlimenticios", null, registro);
                        bd.close();
                        correoRecibe = correoS;
                        correoEnvia = "lachinitaperuanafutbolera@gmail.com";
                        contraCorreo = "lachinitaperuanafutbolera";
                        StrictMode.ThreadPolicy policy =  new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                        Properties properties = new Properties();
                        properties.put("mail.smtp.host", "smtp.googlemail.com");
                        properties.put("mail.smtp.socketFactory.port", "465");
                        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                        properties.put("mail.smtp.auth", "true");
                        properties.put("mail.smtp.port", "465");
                        try{
                            Session sesion = Session.getDefaultInstance(properties, new Authenticator() {
                                @Override
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(correoEnvia, contraCorreo);
                                }
                            });
                            if(sesion != null){
                                Message msj = new MimeMessage(sesion);
                                msj.setFrom(new InternetAddress(correoEnvia));
                                msj.setSubject("Valida tu regsitro.");
                                msj.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(correoRecibe));
                                msj.setContent("Ya casi está tu registro, escribe esta clave para que puedas comenzar a usar nuestra aplicación. Clave: "+clave,"text/html; charset=utf-8");
                                Transport.send(msj);
                            }
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                        String idUsrS = String.valueOf(idUsr);
                        Intent screen = new Intent(this, valida_correo_java.class);
                        screen.putExtra("idUsr", idUsrS);
                        screen.putExtra("validacion", clave);
                        startActivity(screen);
                        finish();
                    }
                }
            }
            else{
                Toast.makeText(this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
            }

        }

    }
}