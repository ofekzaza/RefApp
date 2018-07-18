package com.greenblitz.refapp.feature

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class ChooseCargoActivity : AppCompatActivity() {

    var kindMes = MessageType.AddC

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_cargo)
        var kindStr = intent.getStringExtra("messageKind")
        if(kindStr == MessageType.RemC.toString()){
            kindMes = MessageType.RemC
        }
        else if(kindStr != MessageType.AddC.toString()){
            Toast.makeText(this, "Something wrong in ChooseCargoActivity", Toast.LENGTH_SHORT).show()
        }
    }

    fun Alliance(view: View){
        Toast.makeText(this, "Alliance Cargo have been added", Toast.LENGTH_SHORT).show()
        Communication.init().writeCargo(kindMes, Cargo.Alliance)
        finish()
    }
    fun Barrel(view: View){
        Toast.makeText(this, "Barrel have been added", Toast.LENGTH_SHORT).show()
        Communication.init().writeCargo(kindMes, Cargo.Barrel)
        finish()
    }
    fun Box(view: View){
        Toast.makeText(this, "Box have been added", Toast.LENGTH_SHORT).show()
        Communication.init().writeCargo(kindMes, Cargo.Box)
        finish()
    }
    fun Crate(view: View){
        Toast.makeText(this, "Crate have been added", Toast.LENGTH_SHORT).show()
        Communication.init().writeCargo(kindMes, Cargo.Crate)
        finish()
    }
    fun Treasure(view: View){
        Toast.makeText(this, "Treasure have been added", Toast.LENGTH_SHORT).show()
        Communication.init().writeCargo(kindMes, Cargo.Treasure)
        finish()
    }
}
