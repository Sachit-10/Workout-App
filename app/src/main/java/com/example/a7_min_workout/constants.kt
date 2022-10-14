package com.example.a7_min_workout

object constants {

    fun defaultexerciselist() :ArrayList<exercisemodel> {

        var exerciselist = ArrayList<exercisemodel>()

        var jumpingjacks = exercisemodel(
            1, "Jumping Jacks", R.drawable.ic_jumping_jacks,false,false
        )

        exerciselist.add(jumpingjacks)

        var abdominalcrunch = exercisemodel(
            2, "Abdominal crunch", R.drawable.ic_abdominal_crunch,false,false
        )

        exerciselist.add(abdominalcrunch)

        var highkneesrunning = exercisemodel(
            3, "High Knees Running", R.drawable.ic_high_knees_running_in_place,false,false
        )

        exerciselist.add(highkneesrunning)

        var lunges = exercisemodel(
            4, "Lunges", R.drawable.ic_lunge,false,false
        )

        exerciselist.add(lunges)

        var plank = exercisemodel(
            5, "Plank", R.drawable.ic_plank,false,false
        )

        exerciselist.add(plank)

        var pushup = exercisemodel(
            6, "Push ups", R.drawable.ic_push_up,false,false
        )

        exerciselist.add(pushup)

        var pushuprotation = exercisemodel(
            7, "Push up and rotation", R.drawable.ic_push_up_and_rotation,false,false
        )

        exerciselist.add(pushuprotation)

        var sideplank = exercisemodel(
            8, "Side plank", R.drawable.ic_side_plank,false,false
        )

        exerciselist.add(sideplank)

        var squat = exercisemodel(
            9, "Squats", R.drawable.ic_squat,false,false
        )

        exerciselist.add(squat)

        var stepupontochair = exercisemodel(
            10, "Step Up Onto Chair", R.drawable.ic_step_up_onto_chair,false,false
        )

        exerciselist.add(stepupontochair)

        var tricepsdiponchair = exercisemodel(
            11, "Triceps Dip On Chair", R.drawable.ic_triceps_dip_on_chair,false,false
        )

        exerciselist.add(tricepsdiponchair)


        var wallsit = exercisemodel(
            12, "Wall Sit", R.drawable.ic_wall_sit,false,false
        )

        exerciselist.add(wallsit)

        return exerciselist
    }
}