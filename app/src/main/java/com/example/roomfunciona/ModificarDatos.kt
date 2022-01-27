package com.example.roomfunciona

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.roomfunciona.databinding.ActivityBorrarDatosBinding
import com.example.roomfunciona.databinding.ActivityModificarDatosBinding
import kotlinx.coroutines.launch
import java.lang.Exception

class ModificarDatos : AppCompatActivity() {
    lateinit var binding: ActivityModificarDatosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModificarDatosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btIrAModificar.setOnClickListener {
            editar()
        }

    }

    fun editar() {
        lifecycleScope.launch {
            try {
                val amigo = RoomApp.db.misAmigosDao().getPorId( binding.etId.text.toString().toInt() )
                val email = binding.etEmail.text.toString()
                val nombre = binding.etNombre.text.toString()

                if(email.isNotEmpty()) {
                    amigo.email = email
                }
                if (nombre.isNotEmpty()) {
                    amigo.nombre = nombre
                }

                RoomApp.db.misAmigosDao().update(amigo)
            } catch (e: Exception) {
                Toast.makeText(this@ModificarDatos, e.toString(), Toast.LENGTH_LONG).show()
            }

        }
    }
}