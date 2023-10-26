package com.cristian.sqliteoperaciones;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbAdapter {

    dBHelper helper;

    public DbAdapter(Context context){
        helper = new dBHelper(context);
    }

    public long insertarDatos(String usuario, String password){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contenidoValores = new ContentValues();
        contenidoValores.put(dBHelper.NAME, usuario);
        contenidoValores.put(dBHelper.MyPASSWORD, password);
        long id = db.insert(dBHelper.TABLE_NAME, null, contenidoValores);
        return id;
    }


    static class dBHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "registrosdb"; //Nombre de la Base de Datos
        private static final String TABLE_NAME = "usuarios"; //Nombre de la tabla
        private static final int DATABASE_version = 1; //Version de la base de datos
        private static final String UID = "_id"; //Columna 1 (PrimaryKey - Clave Primaria)
        private static final String NAME = "Nombre"; //Columna 2
        private static final String MyPASSWORD = "Password"; //Columna 3
        private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME +
                "("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+ " VARCHAR(255), "+MyPASSWORD+" VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        private Context context;
        public dBHelper (Context contextRecibido){
            super(contextRecibido, DATABASE_NAME, null, DATABASE_version);
            this.context = contextRecibido;
        }
        // En esta sección se crea la Base de Datos
        public void onCreate(SQLiteDatabase db){
            try {
                db.execSQL(CREATE_TABLE);
            }catch (Exception e){
                Mensaje.aviso(context, "Todo mal :( " + e);
            }
        }
        //Se actualiza la Base de Datos
        public void onUpgrade(SQLiteDatabase db, int viejaVersion, int nuevaVersion){
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e){
                Mensaje.aviso(context,"Mal ahí man :( "+ e);
            }
        }
    }

}
