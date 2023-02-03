package dev.x001.workoutapp

object Constants {

    fun greetings(): String{

        val message = arrayListOf(
            "Glad that you're here.",
            "Say hello to your best self.",
            "Change starts here.",
            "Ready to rock the day?",
            "It's not too late to begin again.",
            "We're happy you're here.",
            "It's a new  day, Let's do this.",
            "So good to see you!",
            "Success comes a step at a time."
        )

        return message[(0 until message.size).random()]
    }

    fun defaultExerciseList(): ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()

        val exercise1 = ExerciseModel(
            1,
            "Shoulder Stretch",
            R.raw.shoulder_stretch,
            isSelected = false,
            isCompleted = false
        )

        val exercise2 = ExerciseModel(
            2,
            "Single Leg Hip Rotation",
            R.raw.single_leg_hip_rotation,
            isSelected = false,
            isCompleted = false
        )

        val exercise3 = ExerciseModel(
            3,
            "Punches",
            R.raw.punches,
            isSelected = false,
            isCompleted = false
        )

        val exercise4 = ExerciseModel(
            4,
            "Squat Kicks",
            R.raw.squat_kicks,
            isSelected = false,
            isCompleted = false
        )

        val exercise5 = ExerciseModel(
            5,
            "Squat Reach",
            R.raw.squat_reach,
            isSelected = false,
            isCompleted = false
        )

        val exercise6 = ExerciseModel(
            6,
            "T Plank",
            R.raw.t_plank_excercise,
            isSelected = false,
            isCompleted = false
        )

        val exercise7 = ExerciseModel(
            7,
            "Military Push Ups",
            R.raw.military_push_ups,
            isSelected = false,
            isCompleted = false
        )

        val exercise8 = ExerciseModel(
            8,
            "Reverse Crunches",
            R.raw.reverse_crunches,
            isSelected = false,
            isCompleted = false
        )

        val exercise9 = ExerciseModel(
            9,
            "Staggered Push Ups",
            R.raw.staggered_push_ups,
            isSelected = false,
            isCompleted = false
        )

        val exercise10 = ExerciseModel(
            10,
            "Seated Abs Circles",
            R.raw.seated_abs_circles,
            isSelected = false,
            isCompleted = false
        )

        val exercise11 = ExerciseModel(
            11,
            "Inchworm",
            R.raw.inchworm,
            isSelected = false,
            isCompleted = false
        )

        val exercise12 = ExerciseModel(
            12,
            "Jumping Jack",
            R.raw.jumping_jack,
            isSelected = false,
            isCompleted = false
        )

        exerciseList.add(exercise1)
        exerciseList.add(exercise2)
        exerciseList.add(exercise3)
        exerciseList.add(exercise4)
        exerciseList.add(exercise5)
        exerciseList.add(exercise6)
        exerciseList.add(exercise7)
        exerciseList.add(exercise8)
        exerciseList.add(exercise9)
        exerciseList.add(exercise10)
        exerciseList.add(exercise11)
        exerciseList.add(exercise12)


        return exerciseList
    }



}