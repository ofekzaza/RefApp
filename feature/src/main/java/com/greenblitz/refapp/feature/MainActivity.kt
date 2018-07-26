package com.greenblitz.refapp.feature

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    /**
     * main activity which called in the start of the application
     */
    var rf: String = ""
    var butt1State = false
    var butt2State = false
    var state = GameState.Pre
    var time = 0
    var com = Communication.init()
    var updating = true;
    var areYouSure = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val teamRef = intent.getStringExtra("TeamRef")
        setRefTeamText(teamRef)
        rf = teamRef
        state = GameState.Pre
        time = 0
        println("start working faggot json")
        //var update = Updater(this@MainActivity, "updater")
        //update.start()
        main()
    }

    fun update(view: View){
        /**
         * for some stuiped reason if you are using kotlin there is no auto update so the update is manual when you are pressing on the buttons
         */
        main()

        if(com.getTimeSec() >= 3.5*60&&areYouSure){
            startPostGame()
        }else if(com.getTimeSec() >= 3.5*60){
            areYouSure = true
        }
    }

    fun main() {
        setTime()
        buttonsUpdate()

    }

    fun callCannon(view: View){
        main()
        var intent = Intent(this, CheckCannonActivity::class.java)
        Toast.makeText(this, "have they realy fired the cannon", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    fun addFoul(view: View){
        main()
        var intent = Intent(this, ChooseFoulActivity::class.java)
        intent.putExtra("messageKind", MessageType.AddF.toString())
        Toast.makeText(this, "Choose the Foul to add", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    fun removeFoul(view: View){
        main()
        var intent = Intent(this, ChooseFoulActivity::class.java)
        intent.putExtra("messageKind", MessageType.RemF.toString())
        Toast.makeText(this, "Choose the Foul to remove", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    fun addCargo(view: View){
        main()
        var intent = Intent(this, ChooseCargoActivity::class.java)
        intent.putExtra("messageKind", MessageType.AddC.toString())
        Toast.makeText(this, "Choose the cargo to add", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    fun removeCargo(view: View){
        main()
        var intent = Intent(this, ChooseCargoActivity::class.java)
        intent.putExtra("messageKind", MessageType.RemC.toString())
        Toast.makeText(this, "Choose the cargo to remove", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    fun enable(view: View){
        main()
        var intent = Intent(this, ChooseRobotActivity::class.java)
        intent.putExtra("messageKind", MessageType.Enable.toString())
        Toast.makeText(this, "Choose the robot to enable", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    fun disable(view: View){
        main()
        var intent = Intent(this, ChooseRobotActivity::class.java)
        intent.putExtra("messageKind", MessageType.Disable.toString())
        Toast.makeText(this, "Choose the robot to disable", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }


    fun setTime(){
        //this function sets the time text

        println("jsonis setting time")
        //time = com.updateTimeSec()
        state = com.getWorkingGameState()
        time = com.getTimeSec()
        TimeText.setText("Time- "+(time/60).toString()+":"+(time%60).toString())
        StateText.setText(state.toString())

        print("jsonis "+ time + " fdsjhursni  "+ state.toString())

        if(state == GameState.Pre){
            TimeText.setTextColor(Color.parseColor("#ff0000"))
            StateText.setTextColor(Color.parseColor("#ff0000"))
        } else if (state == GameState.Tele){
            StateText.setTextColor(Color.parseColor("#00ff00"))
            TimeText.setTextColor(Color.parseColor("#00ff00"))
        } else if (state == GameState.Auto){
            StateText.setTextColor(Color.parseColor("#00ff00"))
            TimeText.setTextColor(Color.parseColor("#00ff00"))
        } else if(state == GameState.Post) {
            StateText.setTextColor(Color.parseColor("#ff0000"))
        }
        println(" jsonis finished setting time")
    }

    fun buttonsUpdate(){
        //set the text of the text buttons

        println("jsonis updating buttons")

        butt1State = com.getAnchor1State()
        butt2State = com.getAnchor2State()
        println("" + butt1State + " edsfhybcrfevg "+ butt2State)

        if(butt1State){
            Butt1Text.setText("Anchor1 open")
            Butt1Text.setBackgroundColor(Color.parseColor("#00ff00"))
        }
        if(butt2State){            Butt2Text.setText("Anchor2 open")
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

    fun startPostGame (){
        /**
         * this is starting the pile choosing and then its send the data to the server
         */
        val intent = Intent(this, PostRun::class.java)
        startActivity(intent)
        updating = false //  sets the updating on off
        finish()
    }
}
