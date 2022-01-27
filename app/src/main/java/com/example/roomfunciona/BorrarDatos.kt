package com.example.roomfunciona

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.roomfunciona.databinding.ActivityBorrarDatosBinding
import com.example.roomfunciona.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import java.lang.Exception

class BorrarDatos : AppCompatActivity() {
    lateinit var binding: ActivityBorrarDatosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBorrarDatosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBorrar.setOnClickListener {
            borrar()
        }

    }

    fun borrar() {
        lifecycleScope.launch {
            try {
                val amigo = RoomApp.db.misAmigosDao().getPorId( binding.idEt.text.toString().toInt() )
                RoomApp.db.misAmigosDao().delete(amigo)
            } catch (e: Exception) {
                Toast.makeText(this@BorrarDatos, e.toString(), Toast.LENGTH_LONG).show()
            }

        }
    }
}