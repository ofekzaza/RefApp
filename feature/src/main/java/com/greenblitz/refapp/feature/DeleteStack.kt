package com.greenblitz.refapp.feature
import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.EditText
import android.widget.TextView


class DeleteStack : AppCompatActivity() {
    private var stackNumSelect: EditText? = null
    private var stackShow: TextView? = null
    private var stackShowcaseNum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_stack)
        stackNumSelect = findViewById(R.id.stackNum)
        stackShow = findViewById(R.id.stackShowcase)
    }

    @SuppressLint("SetTextI18n")
    fun dispStack(view: View) {
        val height: String = stackNumSelect?.text.toString()
        if (height == "") {
            val builder = AlertDialog.Builder(this@DeleteStack)
            builder.setTitle("Couldn't display stack!")
            builder.setMessage("Please enter a proper stack number")
            builder.setPositiveButton("OK") { _, _ ->}
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        else {
            stackShowcaseNum = Integer.parseInt(height)
        }
        when {
            stackShowcaseNum >= PostRun.AllStacks.Stacks.size -> {
                val builder = AlertDialog.Builder(this@DeleteStack)
                builder.setTitle("Couldn't display stack!")
                builder.setMessage("Unable to display a stack that doesn't exist")
                builder.setPositiveButton("OK") { _, _ ->}
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
            PostRun.AllStacks.Stacks[stackShowcaseNum].size == 1 -> stackShow?.text = "Selected Stack: \n" + PostRun.Cargoid.values()[PostRun.AllStacks.Stacks[stackShowcaseNum][0]]
            else -> stackShow?.text = "Selected Stack: \n" + PostRun.Cargoid.values()[PostRun.AllStacks.Stacks[stackShowcaseNum][0]] + "\n" + PostRun.Cargoid.values()[PostRun.AllStacks.Stacks[stackShowcaseNum][1]] + "\n ... \n" + PostRun.Cargoid.values()[PostRun.AllStacks.Stacks[stackShowcaseNum][PostRun.AllStacks.Stacks[stackShowcaseNum].size - 1]]
        }
    }

    fun deleteStack(view: View) {
        val builder = AlertDialog.Builder(this@DeleteStack)
        builder.setTitle("Confirm Deletion")
        builder.setMessage("Are you sure you would like to delete this stack? This action is irreversible")
        builder.setPositiveButton("Yes") { _, _ ->
            if (PostRun.AllStacks.Stacks.size == 0) {
                val builder = AlertDialog.Builder(this@DeleteStack)
                builder.setTitle("Couldn't delete stack!")
                builder.setMessage("There are no stacks to delete!")
                builder.setPositiveButton("OK") { _, _ ->}
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
            else {
                PostRun.AllStacks.Stacks.removeAt(stackShowcaseNum)
            }
        }
        builder.setNegativeButton("No") { _, _ ->}
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun returnToMainMenu(view: View) {
        //val intent = Intent(this, PostRun::class.java)
        //startActivity(intent)
        finish()
    }
}
