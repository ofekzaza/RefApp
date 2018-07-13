package com.greenblitz.refapp.feature

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_confirmation.*

class ConfirmationActivity : AppCompatActivity() {

    var kind = MessageType.Enable
    var robot = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)
        var kindStr = intent.getStringExtra("messageKind")
        robot = intent.getStringExtra("whichRobot")
        if(kindStr == "Disable"){
            kind = MessageType.Disable
        }
        else if(kindStr != "Enable"){
            Toast.makeText(this, "something wrong with choose robot", Toast.LENGTH_SHORT).show()
        }
        ConText.setText(("do you want to "+kind.toString()+" robot "+robot).toString())
    }

    fun send(view: View){
        Toast.makeText(this, robot+" is getting "+kind.toString(), Toast.LENGTH_SHORT).show()
        finish()
    }
    fun goBack(view: View){
        Toast.makeText(this, "Im Back!!! (he man spoilers)", Toast.LENGTH_SHORT).show()
        finish()
    }
}
