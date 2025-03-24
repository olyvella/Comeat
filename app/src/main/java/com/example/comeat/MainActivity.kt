package com.example.comeat

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val saisieEmail : EditText = findViewById( R.id.editTextTextEmailAddress )
        val saisieMdp : EditText = findViewById( R.id.entermdp )

        val boutonNaviguer : Button = findViewById( R.id.naviguer )
        val boutonAnnuler : Button = findViewById( R.id.annuler )

        boutonNaviguer.setOnClickListener {

            val email : String = saisieEmail.text.toString()
            val mdp : String = saisieMdp.text.toString()

            Log.d( "ACT_CONN" , "Connexion : $email/$mdp" )

            val utilisateur = Modele.findUtilisateur( email, mdp )

            if( utilisateur != null ){
                Session.ouvrir(utilisateur)
                val intent = Intent( this , MenueRepasActivity::class.java )
                startActivity( intent )
            }
            else {
                Log.d("ACT_CONN", "Utilisateur introuvable")
                println("Pas trouvé")
            }

        }

        boutonAnnuler.setOnClickListener {
            saisieEmail.setText("")
            saisieMdp.setText("")
            Log.d( "ACT_CONN", "Annulation")
        }

    }
}