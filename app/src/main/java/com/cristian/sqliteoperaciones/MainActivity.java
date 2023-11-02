package com.cristian.sqliteoperaciones;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    EditText usuario, password;

    DbAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        usuario = (EditText) findViewById(R.id.usuarioNombre);
        password = (EditText) findViewById(R.id.usuarioPassword);

        helper = new DbAdapter(this);
    }

    public void agregarUsuario(View view){
        String datoPassword = password.getText().toString();
        String datoUsuario = usuario.getText().toString();
        if(datoUsuario.isEmpty() || datoPassword.isEmpty()){
            Mensaje.aviso(this,"Ingrese tanto el nombre como la contraseña");
        }else{
            long resultado = helper.insertarDatos(datoUsuario, datoPassword);
            if(resultado <=0){
                Mensaje.aviso(this, "Inserción Fallida");
            usuario.setText("");
            password.setText("");
            }else{
                Mensaje.aviso(this, "Inserción Exitosa");
                usuario.setText("");
                password.setText("");
            }
        }
    }

    public void veDatos(View view){
        String datos = helper.getData();
        Mensaje.aviso(this, datos);
    }

}
