package sett.teta.termproject

import android.app.Application

class ChecklistApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        ChecklistRepository.initialize(this)
    }
}