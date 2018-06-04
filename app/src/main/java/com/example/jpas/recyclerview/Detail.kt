package com.example.jpas.recyclerview

import android.content.Intent.getIntent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.support.v7.appcompat.R
import android.support.v7.appcompat.*
import android.os.Bundle
import com.example.jpas.recyclerview.R.id.*
import com.example.jpas.recyclerview.*
import kotlinx.android.synthetic.main.activity_detail.*

class Detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        txtNombre.text = getIntent().getExtras().getString("nombre")
        txtApellido.text = getIntent().getExtras().getString("apellido")
        txtCedula.text = getIntent().getExtras().getString("cedula")
        txtEstado.text = getIntent().getExtras().getString("like")

        if (txtEstado.text=="LIKE"){
            txtEstado.setTextColor(Color.BLUE)
        }
        else{
            txtEstado.setTextColor(Color.RED)
        }

    }




}
