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
        buttonsUpdate()
        setTime()
    }

    fun enable(view: View){
        var intent = Intent(this, ChooseRobotActivity::class.java)
        intent.putExtra("messageKind", "Enable")
        Toast.makeText(this, "Choose the robot to enable", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    fun disable(view: View){
        var intent = Intent(this, ChooseRobotActivity::class.java)
        intent.putExtra("messageKind", "Disable")
        Toast.makeText(this, "Choose the robot to disable", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }


    fun setTime(){
        //this function sets the time text
        if(time > 15 && time <= 3.5 * 60){
            state = GameState.Tele
        } else if( time > 0 && time <= 15) {
            state = GameState.Auto
        }
        else if(time > 3.5*60){
            state = GameState.Ended
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
        } else if(state == GameState.Ended) {
            StateText.setText("State- Ended")
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
        if(butt1State){
            Butt1Text.setText("Button1 Pressed")
            Butt1Text.setBackgroundColor(Color.parseColor("#00ff00"))
        }
        if(butt2State){
            Butt2Text.setText("Button2 Pressed")
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
}

enum class GameState{
    Pre, Auto, Tele, Ended
}