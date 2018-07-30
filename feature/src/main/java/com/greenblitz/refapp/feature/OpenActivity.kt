package com.greenblitz.refapp.feature

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.view.View
import android.widget.Toast

class OpenActivity : AppCompatActivity() {
    var com = Communication.init()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open)
    }
    fun ChooseRed(view: View){
        println("dddd 1")
        com.writeTeam("Red")
        println("dddd 2")
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("TeamRef", "Red")
        Toast.makeText(this, "You are Red", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    fun ChooseBlue(view: View){
        com.writeTeam("Blue")
        //println("dddd blue have been chsoen")
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("TeamRef", "Blue")
        Toast.makeText(this, "You are Blue", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }
}
