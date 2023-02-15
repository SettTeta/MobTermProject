package sett.teta.termproject.checklistpackage

import android.app.Application

class ChecklistApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        ChecklistRepository.initialize(this)
    }
}