package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    lateinit private var username:TextInputEditText
    lateinit private var code:TextInputEditText
    lateinit private var btnsubmit:Button
    lateinit var adminCheck:CheckBox
    lateinit var personnelCheck: CheckBox

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

// recuperation des entrer de l'utilisateur
     username = findViewById(R.id.username)
     code = findViewById(R.id.code)
     btnsubmit= findViewById(R.id.btnsubmit)
     adminCheck = findViewById(R.id.admin)
        personnelCheck= findViewById(R.id.personnel)
        btnsubmit.setOnClickListener(){

         if(username.text?.isEmpty() == true ){
             Toast.makeText(this, "le champ nom d'utilisateur est vide", Toast.LENGTH_SHORT).show()

         }else{
             if(code.text?.isEmpty() == true ){
                 Toast.makeText(this, " le champ code est vide", Toast.LENGTH_SHORT).show()
             }else{
                 if(username.text?.isEmpty() == true && code.text?.isEmpty() == true){
                     Toast.makeText(this, "champ nom d'utilisateur et champ code est vide", Toast.LENGTH_SHORT).show()
                 }  else{
                     var isAdminChecked:Boolean = adminCheck.isChecked
                     var isPersonnelChecked : Boolean = personnelCheck.isChecked
                     if(isAdminChecked){
                         val intent = Intent(this,MainActivityJournaux::class.java)
                         startActivity(intent)
                     }
                     if (isPersonnelChecked) {

                         var namepersonel = username.text
                         var codepersonel =code.text
                         val user = userparcelable("$namepersonel","$codepersonel","personel")
                         val intent:Intent= Intent(this,Activity_personnelle::class.java)
                         intent.putExtra("personel",user)
                         startActivity(intent)



                     }
                     }

             }
         }


     }
    }
}