package com.greenblitz.refapp.feature

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import java.io.IOException

@Suppress("DEPRECATION")
class PostRun : AppCompatActivity() {
    companion object {
        var cargoArray = intArrayOf(16, 12, 16, 6, 7)
    }

    enum class Cargoid {
        Barrel, AllianceCargo, Box, Crate, Treasure
    }

    object AllStacks{
        var Stacks: MutableList<ArrayList<Int>> = mutableListOf()
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
        // need to send stack data
        this.finish()
    }
}

