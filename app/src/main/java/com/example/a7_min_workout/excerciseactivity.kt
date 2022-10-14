package com.example.a7_min_workout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7_min_workout.databinding.ActivityExcerciseactivityBinding
import com.example.a7_min_workout.databinding.ActivityMainBinding
import com.example.a7_min_workout.databinding.DialogCustomBackConfirmationBinding
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class excerciseactivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    var binding: ActivityExcerciseactivityBinding?= null

    var resttimer : CountDownTimer?=null   // Variable for Rest Timer and later on we will initialize it.
    var restprogress = 0                   // Variable for timer progress. As initial value the rest progress is set to 0. As we are about to start.



    var exercisetimer : CountDownTimer?=null        // Variable for Exercise Timer and later on we will initialize it.
    var exerciseprogress = 0                        // Variable for the exercise timer progress. As initial value the exercise progress is set to 0. As we are about to start.

    var exerciselist = ArrayList<exercisemodel>()
    var currentexerciseposition = -1

    var restdurationtimer :Long = 1
    var exercisetimerduration :Long = 1              // The Variable for the exercise list and current position of exercise here it is -1 as the list starting element is 0

    var tts: TextToSpeech?=null

    var player: MediaPlayer? = null


    var exerciseadapter : exercisestatusadapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExcerciseactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)


//        setSupportActionBar(binding?.toolbarExercise)


        tts= TextToSpeech(this,this)


//        if(supportActionBar !=null){
//            supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        }


        binding?.toolbarExercise?.setNavigationOnClickListener() {
           customdialogforbackbutton()
        }

        exerciselist = constants.defaultexerciselist()

        setrestprogressbar()
        setupexercisestatusrecyclerview()

    }







    private fun customdialogforbackbutton() {

        val customdialog = Dialog(this)

        //we are creating a seperate binding so that we can acces things from dialog_custom_back_confirmation xml file
        val dialogbinding = DialogCustomBackConfirmationBinding.inflate(layoutInflater)

        customdialog.setContentView(dialogbinding.root)
        customdialog.setCanceledOnTouchOutside(false)

        dialogbinding.btnYes.setOnClickListener() {
            this@excerciseactivity.finish()
            customdialog.dismiss()
        }
        dialogbinding.btnNo.setOnClickListener(){
            customdialog.dismiss()
        }

        customdialog.show()


    }




    // jab hum phn waala back button press krnege tb bhi ye ana chahiye
    override fun onBackPressed() {
        customdialogforbackbutton()
    }





    fun setupexercisestatusrecyclerview(){

        // Defining a layout manager for the recycle view
        // Here we have used a LinearLayout Manager with horizontal scroll.
        binding?.rvExerciseststatus?.layoutManager =LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        // As the adapter expects the exercises list and context so initialize it passing it.
        exerciseadapter = exercisestatusadapter(exerciselist)

        // Adapter class is attached to recycler view
        binding?.rvExerciseststatus?.adapter = exerciseadapter
    }








    //Setting up the Get Ready View with 10 seconds of timer
    //START
    /**
     * Function is used to set the timer for REST.
     */

    fun setrestprogressbar() {
        /**
         * Here the sound file is added in to "raw" folder in resources.
         * And played using MediaPlayer. MediaPlayer class can be used to control playback
         * of audio/video files and streams.
         */

        try {
            var sounduri = Uri.parse("android.resource://com.example.a7_min_workout/" + R.raw.app_src_main_res_raw_press_start)
            player = MediaPlayer.create(applicationContext, sounduri)
            player?.isLooping = false         // Sets the player to be looping or non-looping.
            player?.start()
        }
        catch (e: Exception){
            e.printStackTrace()
        }

        binding?.upcomingexercise?.visibility= View.VISIBLE
        binding?.upcomingexercisename?.visibility= View.VISIBLE
        binding?.flrestview?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility= View.VISIBLE
        binding?.tvexercisename?.visibility  =View.INVISIBLE
        binding?.ivimage?.visibility = View.INVISIBLE
        binding?.flProgressBarforexercise?.visibility = View.INVISIBLE
        binding?.upcomingexercisename?.text = exerciselist[currentexerciseposition + 1].getname()

        restprogress=0

        binding?.progressbar?.progress = restprogress

        /**
         * @param millisInFuture The number of millis in the future from the call
         *   to {#start()} until the countdown is done and {#onFinish()}
         *   is called.
         * @param countDownInterval The interval along the way to receive
         *   {#onTick(long)} callbacks.
         */
        // Here we have started a timer of 10 seconds so the 10000 is milliseconds is 10 seconds and the countdown interval is 1 second so it 1000.

        resttimer = object : CountDownTimer(restdurationtimer * 10000,1000) {
            override fun onTick(p0: Long) {
                restprogress++
                binding?.progressbar?.progress = 10-restprogress
                binding?.tvtimer?.text = (10 -restprogress).toString()
            }

            override fun onFinish() {
                // When the 10 seconds will complete this will be executed.
                Toast.makeText(this@excerciseactivity,"Here now we will start the exercise",Toast.LENGTH_LONG).show()

                currentexerciseposition++



                setexcerciseprogress()
            }


        }.start()
    }









    fun setexcerciseprogress() {

        // Here according to the view make it visible as this is Exercise View so exercise view is visible and rest view is not.

        binding?.flrestview?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility= View.INVISIBLE
        binding?.tvexercisename?.visibility  =View.VISIBLE
        binding?.ivimage?.visibility = View.VISIBLE
        binding?.flProgressBarforexercise?.visibility = View.VISIBLE
        binding?.upcomingexercise?.visibility= View.INVISIBLE
        binding?.upcomingexercisename?.visibility= View.INVISIBLE


        exerciseprogress =0


        // TODO(Step 9 - Setting up the current exercise name and image to view to the UI element.)
        // START
        /**
         * Here current exercise name and image is set to exercise view.
         */
        binding?.ivimage?.setImageResource(exerciselist[currentexerciseposition].getimage())
        binding?.tvexercisename?.text = exerciselist[currentexerciseposition].getname()

        exerciselist[currentexerciseposition].setisselected(true)
        exerciseadapter?.notifyDataSetChanged()


        speakout(binding?.tvexercisename?.text.toString())

        binding?.progressbarforexercise?.progress = exerciseprogress

       exercisetimer= object : CountDownTimer(exercisetimerduration * 30000,1000) {
            override fun onTick(p0: Long) {
                exerciseprogress++
                binding?.progressbarforexercise?.progress = 30-exerciseprogress
                binding?.tvtimerforexercise?.text = (30 -exerciseprogress).toString()
            }

            override fun onFinish() {

                if (currentexerciseposition < exerciselist?.size!! ) {

                    exerciselist[currentexerciseposition].setisselected(false)
                    exerciselist[currentexerciseposition].setiscompleted(true)
                    exerciseadapter?.notifyDataSetChanged()

                    if (currentexerciseposition == exerciselist?.size!! - 1) {
                        Toast.makeText(this@excerciseactivity,"We have finished the exercise",Toast.LENGTH_LONG).show()

                        val intent = Intent(this@excerciseactivity,finish_activity::class.java)
                        startActivity(intent)
                    }

                    setrestprogressbar()
                }



            }


        }.start()

    }

    // Destroying the timer when closing the activity or app
    //START
    /**
     * Here in the Destroy function we will reset the rest timer if it is running.
     */

    override fun onDestroy() {
        super.onDestroy()

        if(resttimer!=null) {
            resttimer?.cancel()
            restprogress=0
        }
        if(exercisetimer!=null) {
            exercisetimer?.cancel()
            exerciseprogress=0
        }
        // Shutting down the Text to Speech feature when activity is destroyed
        // START
        if(player!=null){
            player?.stop()
        }

        binding=null
    }



    // START
    /**
     * This the TextToSpeech override function
     *
     * Called to signal the completion of the TextToSpeech engine initialization.
     */
    override fun onInit(status: Int) {

        if(status == TextToSpeech.SUCCESS){
            // set US English as language for tts
            var result = tts?.setLanguage(Locale.ENGLISH)

            if(result==TextToSpeech.LANG_NOT_SUPPORTED || result==TextToSpeech.LANG_MISSING_DATA){
                Log.e("tts","Language not supported")
            }
            else {
                Log.e("tts","Initialization failed")
            }
        }
    }








    /**
     * Function is used to speak the text that we pass to it.
     */
    fun speakout(text:String){
        tts?.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }





}



