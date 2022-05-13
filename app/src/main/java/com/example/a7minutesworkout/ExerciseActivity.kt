package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private var binding:ActivityExerciseBinding?=null
    private var myTimer:CountDownTimer?=null
    private var myTime=10
    private var exNo=1
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
        timer()
    }
    private fun timer() {
        myTimer=object:CountDownTimer(10000,1000) {
            override fun onTick(p0: Long) {
                myTime--
                binding?.progressbar?.progress=myTime
                binding?.tvTimer?.text=myTime.toString()
            }

            override fun onFinish() {
                binding?.exName?.text="Exercise No. ${exNo}"
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
                if(exNo<3){
                    binding?.exName?.text="Exercise No. ${exNo}"
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