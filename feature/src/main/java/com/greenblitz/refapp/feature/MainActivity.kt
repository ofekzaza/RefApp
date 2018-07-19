package com.greenblitz.refapp.feature

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var rf: String = ""
    var butt1State = false
    var butt2State = false
    var state = GameState.Pre
    var time = 0
    var com = Communication.init()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val teamRef = intent.getStringExtra("TeamRef")
        setRefTeamText(teamRef)
        rf = teamRef
        state = GameState.Pre
        time = 0

       main()
    }

    fun main() {
        setTime()
        buttonsUpdate()
        var update = Updating();
        update.execute(MainActivity())
    }

    fun callCannon(view: View){
        var intent = Intent(this, CheckCannonActivity::class.java)
        Toast.makeText(this, "have they realy fired the cannon", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    fun addFoul(view: View){
        var intent = Intent(this, ChooseFoulActivity::class.java)
        intent.putExtra("messageKind", MessageType.AddF.toString())
        Toast.makeText(this, "Choose the Foul to add", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    fun removeFoul(view: View){
        var intent = Intent(this, ChooseFoulActivity::class.java)
        intent.putExtra("messageKind", MessageType.RemF.toString())
        Toast.makeText(this, "Choose the Foul to remove", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    fun addCargo(view: View){
        var intent = Intent(this, ChooseCargoActivity::class.java)
        intent.putExtra("messageKind", MessageType.AddC.toString())
        Toast.makeText(this, "Choose the cargo to add", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    fun removeCargo(view: View){
        var intent = Intent(this, ChooseCargoActivity::class.java)
        intent.putExtra("messageKind", MessageType.RemC.toString())
        Toast.makeText(this, "Choose the cargo to remove", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    fun enable(view: View){
        var intent = Intent(this, ChooseRobotActivity::class.java)
        intent.putExtra("messageKind", MessageType.Enable.toString())
        Toast.makeText(this, "Choose the robot to enable", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    fun disable(view: View){
        var intent = Intent(this, ChooseRobotActivity::class.java)
        intent.putExtra("messageKind", MessageType.Disable.toString())
        Toast.makeText(this, "Choose the robot to disable", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }


    fun setTime(){
        //this function sets the time text

        //time = com.updateTimeSec()
        state = com.getGameState()
        time = com.getTimeSec()

        if(state == GameState.Post){
            startPostGame()
        }

        if(state == GameState.Pre){
            TimeText.setTextColor(Color.parseColor("#ff0000"))
            StateText.setText("State- Pre")
            StateText.setTextColor(Color.parseColor("#ff0000"))
        } else if (state == GameState.Tele){
            StateText.setText("State- Tele")
            StateText.setTextColor(Color.parseColor("#00ff00"))
            TimeText.setText("Time- "+(time/60).toString()+":"+(time%60).toString())
            TimeText.setTextColor(Color.parseColor("#00ff00"))
        } else if (state == GameState.Auto){
            StateText.setText("State- Auto")
            StateText.setTextColor(Color.parseColor("#00ff00"))
            TimeText.setText("Time- "+(time/60).toString()+":"+(time%60).toString())
            TimeText.setTextColor(Color.parseColor("#00ff00"))
        } else if(state == GameState.Post) {
            StateText.setText("State- Post")
            StateText.setTextColor(Color.parseColor("#ff0000"))
            TimeText.setTextColor(Color.parseColor("#ff0000"))
            if (time / 60 > 9) {
                TimeText.setText("Time- XXXX")
            } else {
                TimeText.setText("Time- " + (time / 60).toString() + ":" + (time % 60).toString())

            }
        }
    }

    fun buttonsUpdate(){
        //set the text of the text buttons

        //butt1State = com.updateAnchor1()
        //butt2State = com.updateAnchor2()

        if(butt1State){
            Butt1Text.setText("Anchor1 open")
            Butt1Text.setBackgroundColor(Color.parseColor("#00ff00"))
        }
        if(butt2State){
            Butt2Text.setText("Anchor2 open")
            Butt2Text.setBackgroundColor(Color.parseColor("#00ff00"))
        }
    }

    fun setRefTeamText(teamRef: String){
        //set the text of the refferee text
        RefTeamText.text = ("Team- " + teamRef)
        if(teamRef == "Blue") {
            RefTeamText.setTextColor(Color.parseColor("#0000ff"))
        }
        else{
            RefTeamText.setTextColor(Color.parseColor("#ff0000"))
        }
    }

    fun startPostGame(){

    }

}

