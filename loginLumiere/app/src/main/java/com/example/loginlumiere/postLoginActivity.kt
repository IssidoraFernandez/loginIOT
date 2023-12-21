package com.example.loginlumiere

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loginlumiere.databinding.ActivityMainBinding
import com.example.loginlumiere.databinding.ActivityPostLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class PostLoginActivity : AppCompatActivity() {

    //view binding
    private lateinit var binding: ActivityPostLoginBinding

    //firebase auth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //inicializar firebase auth
        auth = Firebase.auth

        //buscar boton de cerrar sesion

        binding.btnLogOut.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("¿Quieres salir?")
                .setMessage("Su presionas aceptar se cerrara la sesion")
                .setNeutralButton("Cancelar") { dialog, which ->
                    // Respond to neutral button press
                }
                .setPositiveButton("Cerrar sesión") { dialog, which ->
                    // Respond to positive button press
                    //cerrar sesion
                    auth.signOut()
                    finish()
                }
                .show()
        }
    }
}