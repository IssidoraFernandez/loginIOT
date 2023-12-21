package com.example.loginlumiere

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginlumiere.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class MainActivity : AppCompatActivity() {

    //view binding
    private lateinit var binding: ActivityMainBinding

    //firebase auth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //autenticar firebase con auth
        auth = Firebase.auth

        //el login se activa cuando se presiona el boton
        binding.btnIniciarSesion.setOnClickListener {
            // obtener correo y contraseña
            val correo = binding.etCorreoElectronico.text.toString()
            val contrasena = binding.etContrasena.text.toString()

            if(correo.isEmpty()){
                binding.etCorreoElectronico.error = "Por favor ingrese su correo"
                return@setOnClickListener
            }

            if(contrasena.isEmpty()){
                binding.etContrasena.error = "Por favor ingrese su contraseña"
                return@setOnClickListener
            }
        }

        //reconocer si se hace click en el boton de registrarse
        binding.tvRegistro.setOnClickListener {
            val intent = Intent(this, RegistrarActivity::class.java)
            startActivity(intent)
        }
    }

    private fun singIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this, "Inicio de sesion correcto!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, PostLoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Error al iniciar sesion, por favor intentelo de nuevo", Toast.LENGTH_SHORT).show()
                }
            }

    }
}