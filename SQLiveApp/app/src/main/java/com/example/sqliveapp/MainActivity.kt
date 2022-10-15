package com.example.sqliveapp

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var etnCodigo: EditText
    private lateinit var etnDescripcion: EditText
    private lateinit var etnPrecio: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etnCodigo = findViewById(R.id.etn_Codigo)
        etnDescripcion = findViewById(R.id.etn_Descripcion)
        etnPrecio = findViewById(R.id.etn_Precio)
    }

    fun Registrar(v: View){
        //Se crea la conexion
        val admin = AdminSQLLite(this, "Tienda", null, 1)
        val baseDeDatos:SQLiteDatabase = admin.writableDatabase

        val codigo = etnCodigo.text.toString()
        val descripcion = etnDescripcion.text.toString()
        val precio = etnPrecio.text.toString()

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            val registro = ContentValues()
            registro.put("codigo", codigo)
            registro.put("descripcion", descripcion)
            registro.put("precio", precio)

            baseDeDatos.insert("articulos", null, registro)
            baseDeDatos.close()

            etnCodigo.setText("")
            etnDescripcion.setText("")
            etnPrecio.setText("")

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show()

        }else{
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_LONG).show()
        }
    }
}