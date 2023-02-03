package dev.x001.workoutapp

class ExerciseModel (
    var id: Int,
    var name: String,
    var image: Int,
    var isCompleted: Boolean = false,
    var isSelected: Boolean = false
)