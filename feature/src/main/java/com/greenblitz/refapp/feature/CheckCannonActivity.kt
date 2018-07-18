package com.greenblitz.refapp.feature

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class CheckCannonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_cannon)
    }

    fun send(view: View){
        Communication.init().writeCannon()
        finish()
    }
    fun goBack(view: View){
        Toast.makeText(this, "Im Back!!! (he man spoilers)", Toast.LENGTH_SHORT).show()
        finish()
    }
}
