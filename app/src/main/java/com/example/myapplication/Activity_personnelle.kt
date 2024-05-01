package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class Activity_personnelle : AppCompatActivity(), View.OnClickListener{

   lateinit var recyclerview:RecyclerView
   lateinit var datalist:ArrayList<Classe_personelle>
   lateinit var nomperso: Array<String>
    lateinit var codeperso:Array<String>
   // lateinit var adresseIPperso:Array<String>
    lateinit var statutPerso:Array<String>
    lateinit var editcode:EditText
    lateinit var editnom:EditText
    lateinit var editstatut:EditText






    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personnelle)

        // declaration des variable
        recyclerview = findViewById(R.id.recycler)
        var layoutEditPersonel = findViewById<LinearLayout>(R.id.layoutEditPerso)
        var texte:TextView = findViewById(R.id.text1)
        val btnAjouter : Button = findViewById(R.id.btnAjouter)
         editcode= findViewById(R.id.editcodePerso)
         editnom= findViewById(R.id.editnomperso)
        editstatut= findViewById(R.id.editstatutperso)


        btnAjouter.setOnClickListener{view->


            addReccord(view)

        }
                setuplistofDataIntoRecyclerview()





    // verifier s'utilisateur est un personel , sioui on cache la parti d'ajout d'un nouveau utilisateur

//        val personelle = intent.getParcelableExtra<userparcelable>("personel")
//
//        var nom = personelle?.namp
//        var code = personelle?.code
//        var statut = personelle?.status
//        if (statut == "personel"){
//                layoutEditPersonel.visibility= View.GONE
//
//            }

//fonction pour charger la liste des personnes dans le recyclerview


    }
    override fun onClick(view: View?) {
        if (view?.tag != null){
            val index = view?.tag as Int
            val user = nomperso[index]
            Toast.makeText(this, "utilisateur ${user}", Toast.LENGTH_SHORT).show()
        }


    }

    //// fnction de verification des entrer et de l'insertion dans le recyclerview

    private  fun addReccord(view:View) {
        val code1 = editcode.text.toString()
        val nom1 = editnom.text.toString()
        val statut1 = editstatut.text.toString()
        val databaseHhelper: MyDataBaseHelper = MyDataBaseHelper(this)
        if (!code1.isEmpty() && !nom1.isEmpty() && !statut1.isEmpty()) {
            val ajouter = databaseHhelper.addUtilisateur(Classe_personelle(code1, nom1, statut1))
            if (ajouter > -1) {
                Toast.makeText(applicationContext, "utilisateur ajouter avec success", Toast.LENGTH_SHORT).show()
                editcode.text.clear()
                editnom.text.clear()
                editstatut.text.clear()

            setuplistofDataIntoRecyclerview()
            }
        }else{
            Toast.makeText(applicationContext, "les champs ne doivent pas etre vide", Toast.LENGTH_SHORT).show()
        }

    }
    private fun getItemListUser():ArrayList<Classe_personelle>{
        //creation d'une instance de la classe MyDataBaseHelper

        val dataBaseHelper:MyDataBaseHelper= MyDataBaseHelper(this)

        // appel de la methode readutilisateur de la classe MyDataBaseHelper

        return dataBaseHelper.readUtilisateur()
    }
// fonction pour mettre les donnees das le recyclerview

    private fun setuplistofDataIntoRecyclerview(){
        if (getItemListUser().size >0){
            recyclerview.visibility=View.VISIBLE
            //
            recyclerview.layoutManager = LinearLayoutManager(this)

            val itemAdapter = AdapterPersonnelle(getItemListUser(),this)
            recyclerview.adapter=itemAdapter

        }else{
            recyclerview.visibility= View.GONE
        }
    }


    // fonction get data pour les listes
    fun getdata(){
        for(i in nomperso.indices){
            val dataclass = Classe_personelle(nomperso[i],codeperso[i],statutPerso[i])
            datalist.add(dataclass)
        }
        recyclerview.adapter = AdapterPersonnelle(datalist,this)

    }


}