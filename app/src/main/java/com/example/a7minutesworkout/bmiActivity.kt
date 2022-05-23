package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityBmiBinding

class bmiActivity : AppCompatActivity() {
    private var binding:ActivityBmiBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.tbBmi)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="CALCULATE BMI"
        }
        binding?.tbBmi?.setNavigationOnClickListener{
            onBackPressed()
        }
        binding?.btnCal?.setOnClickListener {
            if(binding?.tiHeight?.text?.length==0||binding?.tiWeight?.text?.length==0){
                Toast.makeText(this@bmiActivity, "Enter the field", Toast.LENGTH_SHORT).show()
            }else{
                var height=binding?.tiHeight?.text?.toString()?.toDouble()
                var weight=binding?.tiWeight?.text?.toString()?.toDouble()
                var ans=(weight!!*10000/(height!!*height!!))
                if(ans<18.5){
                    binding?.tvResult?.text="Your BMI is ${ans} \n You are Underweight"
                }else if(ans>=18.5&&ans<=24.9){
                    binding?.tvResult?.text="Your BMI is ${ans} \n You are Normalweight"
                }else{
                    binding?.tvResult?.text="Your BMI is ${ans} \n You are Overweight"
                }
            }
        }
    }
}