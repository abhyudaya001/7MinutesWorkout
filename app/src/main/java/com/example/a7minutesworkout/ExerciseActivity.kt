package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private var binding:ActivityExerciseBinding?=null
    private var myTimer:CountDownTimer?=null
    private var myTime=10
    private var exNo=1
    private var exList:ArrayList<ExerciseModel>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarExercise)
        binding?.tvTimer?.text=myTime.toString()
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }
        exList=constants.exerciseList()
        timer()
    }
    private fun timer() {
        binding?.exImg?.visibility=View.INVISIBLE
        myTimer=object:CountDownTimer(10000,1000) {
            override fun onTick(p0: Long) {
                myTime--
                binding?.progressbar?.progress=myTime
                binding?.tvTimer?.text=myTime.toString()
            }

            override fun onFinish() {
                binding?.exImg?.visibility=View.VISIBLE
                binding?.exImg?.setImageResource(exList!![exNo-1].getImage())
                binding?.tvTitle?.text=exList!![exNo-1].getName()
                binding?.progressbar?.max=25
                exerciseTimer()
            }
        }.start()
    }
    private fun exerciseTimer() {
        var cd:CountDownTimer?=null
        var cdTime=25;
        cd=object :CountDownTimer(25000,1000){
            override fun onTick(p0: Long) {
                cdTime--
                binding?.progressbar?.progress=cdTime
                binding?.tvTimer?.text=cdTime.toString()
            }

            override fun onFinish() {
                exNo++
                if(exNo<=3){
                    binding?.exImg?.setImageResource(exList!![exNo-1].getImage())
                    binding?.tvTitle?.text=exList!![exNo-1].getName()
                    exerciseTimer()
                }else{
                    Toast.makeText(this@ExerciseActivity,"Finished",Toast.LENGTH_SHORT).show()
                }
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}