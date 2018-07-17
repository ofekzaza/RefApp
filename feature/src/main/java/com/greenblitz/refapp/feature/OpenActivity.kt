package com.greenblitz.refapp.feature

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.view.View
import android.widget.Toast

class OpenActivity : AppCompatActivity() {
    //var com = Communication.init()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open)
        //var server = Server.init()
        //Thread(server.run()).start()
    }
    fun ChooseRed(view: View){
        //com.writeTeam("Red")

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("TeamRef", "Red")
        Toast.makeText(this, "You are Red", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    fun ChooseBlue(view: View){
        //com.writeTeam("Blue")

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("TeamRef", "Blue")
        Toast.makeText(this, "You are Blue", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }
}
