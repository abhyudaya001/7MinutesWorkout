package com.example.a7minutesworkout

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minutesworkout.databinding.ActivityExerciseBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener{
    private var binding:ActivityExerciseBinding?=null
    private var myTimer:CountDownTimer?=null
    private var myTime=10
    private var exNo=1
    private var exList:ArrayList<ExerciseModel>?=null
    private var player:MediaPlayer?=null
    private var tts:TextToSpeech?=null
    private var exerciseAdapter:exNoAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarExercise)
        tts= TextToSpeech(this,this)
        binding?.tvTimer?.text=myTime.toString()
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }
        exList=constants.exerciseList()
        timer()
        setupExStatusRV()
    }
    private fun setupExStatusRV(){
        binding?.rvList?.layoutManager=
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        exerciseAdapter=exNoAdapter(exList!!)
        binding?.rvList?.adapter=exerciseAdapter
    }
    private fun timer() {
        binding?.upcExName?.text="${exList!![exNo-1].getName()}"
        speak("Get ready for ${exList!![exNo-1].getName()}")
        myTime=10;
        myTimer=object:CountDownTimer(10000,1000) {
            override fun onTick(p0: Long) {
                myTime--
//                if(myTime<=3)speak(myTime.toString())
                binding?.restpb?.progress=myTime
                binding?.resttv?.text=myTime.toString()
            }

            override fun onFinish() {
                binding?.flrestpb?.visibility=View.INVISIBLE
                binding?.rvtitle?.visibility=View.INVISIBLE
                binding?.upcex?.visibility=View.INVISIBLE
                binding?.upcExName?.visibility=View.INVISIBLE
                binding?.flprogressbar?.visibility=View.VISIBLE
                binding?.tvTitle?.visibility=View.VISIBLE
                binding?.exImg?.visibility=View.VISIBLE
                binding?.exImg?.setImageResource(exList!![exNo-1].getImage())
                binding?.tvTitle?.text=exList!![exNo-1].getName()
                binding?.progressbar?.max=25
                exList!![exNo-1].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
                exerciseTimer()
            }
        }.start()
    }
    private fun exerciseTimer() {
        var cdTime=25;
        speak("Start")
        myTimer=object :CountDownTimer(25000,1000){
            override fun onTick(p0: Long) {
                cdTime--
            if(cdTime==5){
                try{
                    val soundURI= Uri.parse("android.resource://com.example.a7minutesworkout/"+R.raw.count_down)
                    player=MediaPlayer.create(applicationContext,soundURI)
                    player?.isLooping=false
                    player?.start()
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
//                speak(cdTime.toString())
                binding?.progressbar?.progress=cdTime
                binding?.tvTimer?.text=cdTime.toString()
            }

            override fun onFinish() {
                exList!![exNo-1].setIsComplete(true)
                exerciseAdapter!!.notifyDataSetChanged()
                exNo++
                if(exNo<=3){
                    binding?.flrestpb?.visibility=View.VISIBLE
                    binding?.rvtitle?.visibility=View.VISIBLE
                    binding?.upcex?.visibility=View.VISIBLE
                    binding?.upcExName?.visibility=View.VISIBLE
                    binding?.flprogressbar?.visibility=View.INVISIBLE
                    binding?.tvTitle?.visibility=View.INVISIBLE
                    binding?.exImg?.visibility=View.INVISIBLE
                    binding?.progressbar?.max=10
                    timer()
                }else{
                    Toast.makeText(this@ExerciseActivity,"Finished",Toast.LENGTH_SHORT).show()
                }
            }

        }.start()
    }
    private fun speak(text:String){
        tts?.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    override fun onInit(status: Int) {
       if(status==TextToSpeech.SUCCESS){
           val result=tts!!.setLanguage(Locale.US)
           if(result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED){
               Log.e("TTS","Initialization failed")
           }
       }else{
           Log.e("TTS","Initialize failed")
       }
    }
    override fun onDestroy() {
        super.onDestroy()
        if(player!=null){
            player!!.stop()
        }
        if(myTimer!=null){
            myTimer!!.cancel()
            myTimer=null
        }
        if(tts!=null){
            tts!!.stop()
            tts!!.shutdown()
        }
        binding=null
    }
}