package com.example.roomfunciona

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.roomfunciona.RoomApp.Companion.db
import com.example.roomfunciona.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btGuardar.setOnClickListener {
            if (binding.etNombre.text.isNotBlank() &&
                binding.etEmail.text.isNotBlank()) {
                    addAmigo(MisAmigos(nombre = binding.etNombre.text.toString(), email = binding.etEmail.text.toString()))
            }

        }
        binding.btConsultar.setOnClickListener {
            lifecycleScope.launch {
                val todos = db.misAmigosDao().getTodo()
//                var i = 0;
//                var guardado = "";
////                while(i < todos.size){
////                  guardado  +=   todos[i].nombre +" "+todos[i].email+" \n"
////                    i++
////                }
                binding.contactosLl.removeAllViews()
                for (contacto in todos) {
                    crearContacto(contacto.nombre, contacto.email, contacto.id)
                }
            }
        }
        binding.btIrABorrar.setOnClickListener{
            val intent = Intent(this, BorrarDatos::class.java)
            startActivity(intent)
        }
        binding.btIrAModificar.setOnClickListener{
            val intent = Intent(this, ModificarDatos::class.java)
            startActivity(intent)
        }

    }

    private fun crearContacto(nombre: String, email: String, id: Int) {
        val vView = LinearLayout(this)
        vView.orientation = LinearLayout.HORIZONTAL
        vView.weightSum = 7F
        vView.setPadding(30, 2, 10, 2)

        val idCont = TextView(this)
        val emailCont = TextView(this)
        val contactoTv = TextView(this)

        val LlParam = LinearLayout.LayoutParams(idCont.width, 100)
        LlParam.weight = 1F
        idCont.layoutParams = LlParam

        val LlParam2 = LinearLayout.LayoutParams(emailCont.width, 100)
        LlParam2.weight = 3F
        emailCont.layoutParams = LlParam2
        contactoTv.layoutParams = LlParam2

        idCont.text = id.toString()
        emailCont.text = email
        contactoTv.text = nombre

        vView.addView(idCont)
        vView.addView(contactoTv)
        vView.addView(emailCont)


        binding.contactosLl.addView(vView)
    }

    private fun addAmigo(miAmigo: MisAmigos) {
        lifecycleScope.launch {
            db.misAmigosDao().insertar(miAmigo)
        }
    }
}