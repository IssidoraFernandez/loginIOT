package com.example.loginlumiere

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginlumiere.databinding.ActivityRegistrarBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegistrarActivity : AppCompatActivity() {


    //activar view binding
    private lateinit var binding: ActivityRegistrarBinding


    //auth firebase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //inicializar firebase auth
        auth = Firebase.auth

        //reconocer si se hace click en el boton de registrarse
        binding.btnRegistrar.setOnClickListener {
            //obtener los datos para el registro

            val correo = binding.etCorreoElectronico.text.toString()
            val pass = binding.etContrasena.text.toString()
            val pass2 = binding.etContrasena2.text.toString()

            //validar que los campos no esten vacios
            if(correo.isEmpty()){
                binding.etCorreoElectronico.error = "Por favor ingrese su correo"
                return@setOnClickListener
            }
            if(pass.isEmpty()){
                binding.etContrasena.error = "Por favor ingrese su contraseña"
                return@setOnClickListener
            }
            if(pass2.isEmpty()){
                binding.etContrasena2.error = "Por favor ingrese su contraseña"
                return@setOnClickListener
            }

            if(pass == pass2){
                registrarUsuario(correo, pass)
            }
        }


    }

    private fun registrarUsuario(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this, "Registro exitoso!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
    }
}