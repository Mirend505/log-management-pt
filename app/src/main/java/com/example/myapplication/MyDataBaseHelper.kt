package com.example.myapplication

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class MyDataBaseHelper(val context:Context) :SQLiteOpenHelper(context,"gestion_de_logs",null,1){
    private var TABLENAME="utilisateur"
    private var  COLUMNCODE="code"
    private var  COLUMNNAME="nom"
    private var  COLUMNSTATUT="statut"

    override fun onCreate(db: SQLiteDatabase?) {
        val query: String = "CREATE TABLE IF NOT EXISTS $TABLENAME " +
                "($COLUMNCODE TEXT PRIMARY KEY," +
                "$COLUMNNAME TEXT," +
                "$COLUMNSTATUT TEXT);"
        db?.execSQL(query)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLENAME")
        onCreate(db)
    }
    // ******* methode pour inserer les utilisateur dans la base de donnee**********


    fun addUtilisateur(utilisateur:Classe_personelle):Long{
        var db = this.writableDatabase
        var contentvalues = ContentValues()

        contentvalues.put(COLUMNCODE,utilisateur.codeP)
        contentvalues.put(COLUMNNAME,utilisateur.nomP)
        contentvalues.put(COLUMNSTATUT,utilisateur.status)

        // insertion dans la table
        val insertionUser =db.insert(TABLENAME,null,contentvalues)

        db.close()
        return  insertionUser
    }
    // methode de lecture des donnees dans la base de donnee

    @SuppressLint("Range", "Recycle")
    fun readUtilisateur(): ArrayList<Classe_personelle>{
        val utilisateurlist: ArrayList<Classe_personelle> = ArrayList<Classe_personelle>()
        val requetSelect = "SELECT * FROM $TABLENAME"

        val db = this.readableDatabase
        var cursor : Cursor
        try {
            cursor = db.rawQuery(requetSelect,null)

        }catch (e:SQLiteException){
            db.execSQL(requetSelect)
            return ArrayList()
        }
        var code: String
        var nom: String
        var statut: String
        if(cursor.moveToFirst()){
            do{
                    code = cursor.getString(cursor.getColumnIndex(COLUMNCODE))
                nom = cursor.getString(cursor.getColumnIndex(COLUMNNAME))
                statut = cursor.getString(cursor.getColumnIndex(COLUMNSTATUT))
                val isuser= Classe_personelle(codeP=code, nomP = nom, status = statut)
                utilisateurlist.add(isuser)
            }while (cursor.moveToNext())
        }


    return utilisateurlist
    }

    // fonction de supresion des utilisateur
     fun suprimeutilisateur(utilisateur:Classe_personelle):Int{
         val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMNCODE,utilisateur.codeP) // code de l'utiisateur
        // supression des ligne
        val success = db.delete(TABLENAME,COLUMNCODE+"="+ utilisateur.codeP,null )
        db.close()
        return success
     }


}