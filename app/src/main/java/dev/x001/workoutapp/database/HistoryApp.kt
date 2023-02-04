package dev.x001.workoutapp.database

import android.app.Application

class HistoryApp:Application() {
    val db by lazy {
        HistoryDatabase.getInstance(this)
    }
}
