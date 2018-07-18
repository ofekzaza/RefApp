package com.greenblitz.refapp.feature

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class ChooseRobotActivity : AppCompatActivity() {

    var kind =  MessageType.Enable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chooserobot)
        val kindStr = intent.getStringExtra("messageKind")

        if(kindStr == MessageType.Disable.toString()){
            kind = MessageType.Disable
        }
        else if(kindStr != MessageType.Enable.toString()){
            Toast.makeText(this, "something wrong with choose robot", Toast.LENGTH_SHORT).show()
        }
    }

    fun red1(view: View){
        val intent = Intent(this, ConfirmationActivity::class.java)
        intent.putExtra("messageKind", kind.toString())
        intent.putExtra("whichRobot", "Red1")
        startActivity(intent)
        finish()
    }
    fun red2(view: View){
        val intent = Intent(this, ConfirmationActivity::class.java)
        intent.putExtra("messageKind", kind.toString())
        intent.putExtra("whichRobot", "Red2")
        startActivity(intent)
        finish()
    }
    fun blue1(view: View){
        val intent = Intent(this, ConfirmationActivity::class.java)
        intent.putExtra("messageKind", kind.toString())
        intent.putExtra("whichRobot", "Blue1")
        startActivity(intent)
        finish()
    }
    fun blue2(view: View){
        val intent = Intent(this, ConfirmationActivity::class.java)
        intent.putExtra("messageKind", kind.toString())
        intent.putExtra("whichRobot", "Blue2")
        startActivity(intent)
        finish()
    }

}
