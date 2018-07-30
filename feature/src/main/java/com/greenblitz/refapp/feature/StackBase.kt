package com.greenblitz.refapp.feature

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button

class StackBase : AppCompatActivity() {

    private var barrelBtn: Button? = null
    private var allianceCargoBtn: Button? = null
    private var boxBtn: Button? = null
    private var crateBtn: Button? = null
    private var treasureBtn: Button? = null
    private var currentStack: ArrayList<Int> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack_base)
        barrelBtn = findViewById(R.id.Barrel)
        allianceCargoBtn = findViewById(R.id.AllianceCargo)
        boxBtn = findViewById(R.id.Box)
        crateBtn = findViewById(R.id.Crate)
        treasureBtn = findViewById(R.id.Treasure)
        barrelBtn?.text = "Barrels Left: " + PostRun.cargoArray[0]
        allianceCargoBtn?.text = "Alliance Cargoes Left: " + PostRun.cargoArray[1]
        boxBtn?.text = "Boxes Left: " + PostRun.cargoArray[2]
        crateBtn?.text = "Crates Left: " + PostRun.cargoArray[3]
        treasureBtn?.text = "Treasures Left: " + PostRun.cargoArray[4]
    }

    fun barrelStackBase(view: View) {
        val builder = AlertDialog.Builder(this@StackBase)
        builder.setTitle("Confirm Selection")
        builder.setMessage("Are you sure you would like to select the Barrel as the stack base?")
        builder.setPositiveButton("Yes") { _, _ ->
            currentStack.add(0)
            PostRun.cargoArray[0]--
            val intent = Intent(this, MainStacker::class.java)
            intent.putIntegerArrayListExtra("currentStack", ArrayList(currentStack))
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("No") { _, _ ->}
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun allianceCargoStackBase(view: View) {
        val builder = AlertDialog.Builder(this@StackBase)
        builder.setTitle("Confirm Selection")
        builder.setMessage("Are you sure you would like to select the Alliance Cargo as the stack base?")
        builder.setPositiveButton("Yes") { _, _ ->
            currentStack.add(1)
            PostRun.cargoArray[1]--
            val intent = Intent(this, MainStacker::class.java)
            intent.putIntegerArrayListExtra("currentStack", ArrayList(currentStack))
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("No") { _, _ ->}
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    fun boxStackBase(view: View) {
        val builder = AlertDialog.Builder(this@StackBase)
        builder.setTitle("Confirm Selection")
        builder.setMessage("Are you sure you would like to select the Box as the stack base?")
        builder.setPositiveButton("Yes") { _, _ ->
            currentStack.add(2)
            PostRun.cargoArray[2]--
            val intent = Intent(this, MainStacker::class.java)
            intent.putIntegerArrayListExtra("currentStack", ArrayList(currentStack))
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("No") { _, _ ->}
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun crateStackBase(view: View) {
        val builder = AlertDialog.Builder(this@StackBase)
        builder.setTitle("Confirm Selection")
        builder.setMessage("Are you sure you would like to select the Crate as the stack base?")
        builder.setPositiveButton("Yes") { _, _ ->
            currentStack.add(3)
            PostRun.cargoArray[3]--
            val intent = Intent(this, MainStacker::class.java)
            intent.putIntegerArrayListExtra("currentStack", ArrayList(currentStack))
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("No") { _, _ ->}
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun treasureStackBase(view: View) {
        val builder = AlertDialog.Builder(this@StackBase)
        builder.setTitle("Confirm Selection")
        builder.setMessage("Are you sure you would like to select the Treasure as your stack? You will not be able to add any extra cargo to this stack!")
        builder.setPositiveButton("Yes") { _, _ ->
            currentStack.add(4)
            PostRun.cargoArray[4]--
            val intent = Intent(this, PostRun::class.java)
            intent.putIntegerArrayListExtra("stack", ArrayList(currentStack))
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("No") { _, _ ->}
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
