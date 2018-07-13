package com.greenblitz.refapp.feature

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_choosefoul.*

class ChooseFoulActivity : AppCompatActivity() {

    var kindMes = MessageType.AddF

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choosefoul)
        var kindStr = intent.getStringExtra("messageKind")
        if(kindStr == MessageType.RemF.toString()){
            DisableButton.setText("Enable")
            kindMes = MessageType.RemF
        }
        else if(kindStr != MessageType.AddF.toString()){
            Toast.makeText(this, "something wrong with choose foul activity", Toast.LENGTH_SHORT).show()
        }
        FoulText.setText("Choose "+kindMes.toString())
    }

    fun disableFun(view: View){

    }

    fun foul(view: View){

    }

    fun penelty(view: View){

    }
}
