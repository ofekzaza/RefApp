package com.greenblitz.refapp.feature

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainStacker : AppCompatActivity() {

    private var com = Communication.init()
    private var barrelBtn: Button? = null
    private var allianceCargoBtn: Button? = null
    private var boxBtn: Button? = null
    private var crateBtn: Button? = null
    private var treasureBtn: Button? = null
    private var currentStackTxt: TextView? = null
    private var currentStack: ArrayList<Int> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_stacker)
        barrelBtn = findViewById(R.id.Barrel)
        allianceCargoBtn = findViewById(R.id.AllianceCargo)
        boxBtn = findViewById(R.id.Box)
        crateBtn = findViewById(R.id.Crate)
        treasureBtn = findViewById(R.id.Treasure)
        currentStackTxt = findViewById(R.id.StackerText)
        currentStack = intent.getIntegerArrayListExtra("currentStack")
        barrelBtn?.text = "Barrel Left: " + PostRun.cargoArray[0]
        allianceCargoBtn?.text = "Alliance Cargoes Left: " + PostRun.cargoArray[1]
        boxBtn?.text = "Boxes Left: " + PostRun.cargoArray[2]
        crateBtn?.text = "Crates Left: " + PostRun.cargoArray[3]
        treasureBtn?.text = "Treasures Left: " + PostRun.cargoArray[4]
        currentStackTxt?.text = "Current stack:\n" + PostRun.Cargoid.values()[currentStack[0]] + "\n"
    }

    fun addBarrel(view: View) {
        val builder = AlertDialog.Builder(this@MainStacker)
        builder.setTitle("Confirm Selection")
        builder.setMessage("Are you sure you would like to add the Barrel in the " + (currentStack.lastIndex + 2) + " position")
        builder.setPositiveButton("Yes") { _, _ ->
            currentStack.add(0)
            PostRun.cargoArray[0]--
            if (PostRun.cargoArray[0] <= 0) {
                barrelBtn?.visibility = View.GONE
            }
            barrelBtn?.text = "Barrels Left: " + PostRun.cargoArray[0]
            currentStackTxt?.append("Barrel\n")
        }
        builder.setNegativeButton("No") { _, _ ->}
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun addAllianceCargo (view: View) {
        val builder = AlertDialog.Builder(this@MainStacker)
        builder.setTitle("Confirm Selection")
        builder.setMessage("Are you sure you would like to add the Alliance Cargo in the " + (currentStack.lastIndex + 2) + " position")
        builder.setPositiveButton("Yes") { _, _ ->
            currentStack.add(1)
            PostRun.cargoArray[1]--
            if (PostRun.cargoArray[1] <= 0){
                allianceCargoBtn?.visibility = View.GONE
            }
            allianceCargoBtn?.text = "Alliance Cargoes Left: " + PostRun.cargoArray[1]
            currentStackTxt?.append("Alliance Cargo\n")
        }
        builder.setNegativeButton("No") { _, _ ->}
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun addBox (view: View) {
        val builder = AlertDialog.Builder(this@MainStacker)
        builder.setTitle("Confirm Selection")
        builder.setMessage("Are you sure you would like to add the Box in the " + (currentStack.lastIndex + 2) + " position")
        builder.setPositiveButton("Yes") { _, _ ->
            currentStack.add(2)
            PostRun.cargoArray[2]--
            if (PostRun.cargoArray[2] <= 0) {
                boxBtn?.visibility = View.GONE
            }
            boxBtn?.text = "Boxes Left: " + PostRun.cargoArray[2]
            currentStackTxt?.append("Box\n")
        }
        builder.setNegativeButton("No") { _, _ ->}
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun addCrate (view: View) {
        val builder = AlertDialog.Builder(this@MainStacker)
        builder.setTitle("Confirm Selection")
        builder.setMessage("Are you sure you would like to add the Crate in the " + (currentStack.lastIndex + 2) + " position")
        builder.setPositiveButton("Yes") { _, _ ->
            currentStack.add(3)
            PostRun.cargoArray[3]--
            if (PostRun.cargoArray[3] <= 0) {
                crateBtn?.visibility = View.GONE
            }
            crateBtn?.text = "Crates Left: " + PostRun.cargoArray[3]
            currentStackTxt?.append("Crate\n")
        }
        builder.setNegativeButton("No") { _, _ ->}
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun addTreasure (view: View) {
        val builder = AlertDialog.Builder(this@MainStacker)
        builder.setTitle("Confirm Selection")
        builder.setMessage("Are you sure you would like to add the Crate in the " + (currentStack.lastIndex + 2) + " position? You will not be able to add any extra cargo to this stack!")
        builder.setPositiveButton("Yes") { _, _ ->
            currentStack.add(4)
            PostRun.cargoArray[4]--
            if (PostRun.cargoArray[4] <= 0) {
                treasureBtn?.visibility = View.GONE
            }
            treasureBtn?.text = "Treasures Left: " + PostRun.cargoArray[4]
            currentStackTxt?.append("Treasure\n")
            val intent = Intent(this, PostRun::class.java)
            intent.putIntegerArrayListExtra("stack", ArrayList(currentStack))
            startActivity(intent)
        }
        builder.setNegativeButton("No") { _, _ ->}
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun finishStack (view: View) {
        val builder = AlertDialog.Builder(this@MainStacker)
        builder.setTitle("Finish Stacking")
        builder.setMessage("Are you sure you want to finish this stack?")
        builder.setPositiveButton("Yes") { _, _ ->
            val intent = Intent(this, PostRun::class.java)
            intent.putIntegerArrayListExtra("stack", ArrayList(currentStack))
            startActivity(intent)
        }
        builder.setNegativeButton("No") { _, _ ->}
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
