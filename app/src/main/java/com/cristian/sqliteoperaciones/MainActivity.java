package com.cristian.sqliteoperaciones;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        Button btnActualizarUsuario = (Button) findViewById(R.id.btnActualizar);
        btnActualizarUsuario.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActualizarActivity();
            }
        });

        Button btnEliminarUsuario = (Button) findViewById(R.id.btnEliminar);
        btnEliminarUsuario.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startEliminarActivity();
            }
        });


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

    private void startActualizarActivity(){
        Intent intent = new Intent(this, UsuarioActualizarActivity.class);
        startActivity(intent);
        finish();
    }

    private void startEliminarActivity(){
        Intent intent = new Intent(this, UsuarioEliminarActivity.class);
        startActivity(intent);
        finish();
    }

}
