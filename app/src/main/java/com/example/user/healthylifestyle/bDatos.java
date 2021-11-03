package com.example.user.healthylifestyle;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class bDatos extends SQLiteOpenHelper{

    public static final String usuario = "create table usuario(idUsr integer not null primary key autoincrement unique, nmbUsr text not null, correo text not null, edad int not null, sexo text not null, contraseña text not null, calxDia numeric not null, calDesa numeric not null, calCola numeric not null, calComida numeric not null, calCena numeric not null, calDesaC numeric not null, calCol1C numeric not null, calComidaC numeric not null, calCol2 numeric not null, calCenaC numeric not null, validado text not null,  idTipo1 int not null,  foreign key (idTipo1) references tipoPer(idTipo))";
    public static final String tipoPer = "create table tipoPer(idTipo int not null primary key, tipoAct text not null, coefAct numeric not null)";
    public static final String agregaSeden = "insert into tipoPer values(1, 'Sedentaria', 1.2)";
    public static final String agregaPoca = "insert into tipoPer values(2, 'Poca actividad', 1.375)";
    public static final String agregaMode = "insert into tipoPer values(3, 'Moderada actividad', 1.55)";
    public static final String agregaInten = "insert into tipoPer values(4, 'Intensa actividad', 1.725)";
    public static final String alimentos = "create table alimentos(idAli integer not null primary key autoincrement unique, nombre text not null, calorias numeric not null)";
    public static final String datosAlimenticios = "create table datosAlimenticios(idDat integer not null primary key autoincrement unique, IMC numeric not null, fecha date not null, progreso text not null, idUsr1 int not null, foreign key (idUsr1) references usuario(idUsr))";
    public static final String relAliPer = "create table relAliPer(idUsr2 int not null, idAli1 int not null, foreign key (idUsr2) references usuario(idUsr), foreign key (idAli1) references alimentos(idAli))";
    String [] comandos = {usuario, tipoPer, agregaSeden, agregaPoca, agregaMode, agregaInten, alimentos, datosAlimenticios, relAliPer};


    public bDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase bd) {
       for(int i = 0; i<comandos.length; i++){
           bd.execSQL(comandos[i]);
       }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}