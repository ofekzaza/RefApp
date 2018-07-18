package com.greenblitz.refapp.feature

import android.content.Intent
import android.graphics.Color
import android.media.MediaExtractor
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
            DisableButton.setBackgroundColor(Color.parseColor("#00ff00"))
            kindMes = MessageType.RemF
        }
        else if(kindStr != MessageType.AddF.toString()){
            Toast.makeText(this, "something wrong with choose foul activity", Toast.LENGTH_SHORT).show()
        }
        FoulText.setText("Choose "+kindMes.toString())
    }

    fun disableFun(view: View){
        var helper = MessageType.Disable
        if(kindMes == MessageType.RemF){
            helper = MessageType.Enable
        }
        var intent2 = Intent(this,  ChooseRobotActivity::class.java)
        intent2.putExtra("messageKind", helper.toString())
        Toast.makeText(this, "Choose the robot to "+helper, Toast.LENGTH_SHORT).show()
        startActivity(intent2)
        finish()
    }

    fun foul(view: View){
        Toast.makeText(this, kindMes.toString(), Toast.LENGTH_SHORT).show()
        Communication.init().write(kindMes)
        finish()
    }

    fun penalty(view: View){
        var helper = MessageType.RemP
        var painting = "Removing Penalty"
        if(kindMes == MessageType.AddF){
            helper = MessageType.AddP
            painting = "Adding Penalty"
        }
        Communication.init().write(kindMes)
        Toast.makeText(this, painting, Toast.LENGTH_SHORT).show()
        finish()
    }
}
