package com.example.jpas.recyclerview

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.support.v7.appcompat.R
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.*
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.example.jpas.recyclerview.Detail
import com.example.jpas.recyclerview.R.id.recycler_view
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var personas = ArrayList<Persona>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        crearDatosPrueba()

        val layoutManager = LinearLayoutManager(this)
        personas.addAll(crearDatosPrueba())
        val adaptador = PersonasAdaptador(personas)
        recycler_view.layoutManager = layoutManager
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.adapter = adaptador
        personas.addAll(crearDatosPrueba())
        adaptador.notifyDataSetChanged()
        Log.i("vista-principal", "Cambio personas ${personas.size}")
    }

    fun crearDatosPrueba(): ArrayList<Persona> {
        var personas = ArrayList<Persona>()
        personas.add(Persona("Johanna", "Arias", "1724072994"))
        personas.add(Persona("Andrés", "Ruiz", "1723291090"))
        personas.add(Persona("Judith", "Santillán", "1713943536"))
        personas.add(Persona("Jonathan", "Arias", "1725285769"))
        return personas
    }

    fun irADetalle() {
        val intent = Intent(this, Detail::class.java)
        startActivity(intent)
    }

}

class Persona(var nombre: String, var apellido: String, var cedula: String) {}

class PersonasAdaptador(private val listaPersonas: List<Persona>) : RecyclerView.Adapter<PersonasAdaptador.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombre: TextView
        var apellido: TextView
        var cedula: TextView
        var like: Button
        var detail: Button

        lateinit var persona: Persona
        init {
            nombre = view.findViewById(R.id.txtv_nombre) as TextView
            apellido = view.findViewById(R.id.txtv_apellido) as TextView
            cedula = view.findViewById(R.id.txtv_cedula) as TextView
            like = view.findViewById(R.id.boton_like) as Button
            detail = view.findViewById(R.id.boton_detalle) as Button
            val left = apellido.paddingLeft
            val top = apellido.paddingTop
            Log.i("vista-principal", "Hacia la izquierda es $left y hacia arriba es $top")

            val layout = view.findViewById(R.id.relative_layout) as RelativeLayout
            layout.setOnClickListener({ v ->
                val nombreActual = v.findViewById(R.id.txtv_nombre) as TextView

                Log.i("vista-principal", "El nombre actual es: ${nombreActual.text}")
                nombreActual.text = "Otro texto"


            })

            like.setOnClickListener({v ->
                val nombreActual = v.findViewById(R.id.boton_like) as Button

                    Log.i("vista-principal", "El nombre actual es: ${nombreActual.text}")
                if (nombreActual.text == "LIKE"){
                    nombreActual.text = "NO LIKE"
                    nombreActual.setBackgroundColor(Color.YELLOW)
                }
                else{
                    nombreActual.text = "LIKE"
                    nombreActual.setBackgroundColor(Color.BLUE)

                }
            })


            detail.setOnClickListener { v ->


                val intent = Intent(v.context, Detail::class.java)
                intent.putExtra("nombre","${nombre.text}")
                intent.putExtra("apellido", "${apellido.text}")
                intent.putExtra("cedula", "${cedula.text}")
                intent.putExtra("like", "${like.text}")

                startActivity(v.context, intent, null)


            }


            cedula.setOnClickListener { v ->
                val cedulaActual = v.findViewById(R.id.txtv_cedula) as TextView
                val toast = Toast.makeText(v.context, "Hola ${cedulaActual.text}", Toast.LENGTH_LONG)
                toast.show()
                val intent = Intent(v.context, ActividadLayouts::class.java)
                startActivity(v.context, intent, null)
            }



        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.lista_fila_usuario, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val personaActual = listaPersonas[position]
        holder.nombre.setText(personaActual.nombre)
        holder.apellido.setText(personaActual.apellido)
        holder.cedula.setText(personaActual.cedula)
        holder.persona = personaActual
    }

    override fun getItemCount(): Int {
        return listaPersonas.size
    }



}
