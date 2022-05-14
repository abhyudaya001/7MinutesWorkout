package com.example.a7minutesworkout

object constants {
    fun exerciseList():ArrayList<ExerciseModel>{
        val exList=ArrayList<ExerciseModel>()
        val e1=ExerciseModel(
            1,"Jumping Jacks",R.drawable.jumpingjacks,false,false
        )
        exList.add(e1)
        val e2=ExerciseModel(
            2,"Jumping Squats",R.drawable.jumpsquats,false,false
        )
        exList.add(e2)
        val e3=ExerciseModel(
            3,"Climbers",R.drawable.climbers,false,false
        )
        exList.add(e3)
        return exList
    }
}