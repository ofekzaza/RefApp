package com.greenblitz.refapp.feature

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class PostRun : AppCompatActivity() {
    /**
     * only god and wifi knows whats doing on here
     */

    companion object {
        // the current amount of crago of the ref team
        var cargoArray = intArrayOf(Communication.init().getBarrel(), Communication.init().getAlliance(), Communication.init().getBox(), Communication.init().getCrate(), Communication.init().getTreasure()) // fucking kotlin
    }

    enum class Cargoid {
        Barrel, AllianceCargo, Box, Crate, Treasure
    }

    object AllStacks{
        var Stacks: ArrayList<ArrayList<Int>> = arrayListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_run)
        if (intent.getIntegerArrayListExtra("stack") != null) {
            AllStacks.Stacks.add(intent.getIntegerArrayListExtra("stack"))
            println("worked" + intent.getIntegerArrayListExtra("stack"))
            println("the stacks:" + AllStacks.Stacks)
        }
    }

    fun newStack (view: View){
        val intent = Intent(this, StackBase::class.java)
        startActivity(intent)
    }

    fun delStack (view: View) {
        val intent = Intent(this, DeleteStack::class.java)
        startActivity(intent)
    }

    fun endPostRun (view: View) {
        // sends the data to the server
        Communication.init().writePile(PostRun.AllStacks.Stacks)
        Toast.makeText(this, "this is alive", Toast.LENGTH_LONG).show()
        this.finish()
    }
}
