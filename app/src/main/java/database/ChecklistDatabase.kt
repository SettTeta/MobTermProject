package database

import androidx.room.Database
import androidx.room.RoomDatabase
import sett.teta.termproject.Check

@Database(entities = [Check::class], version = 1)
abstract class ChecklistDatabase: RoomDatabase() {
    abstract fun checklistDao(): ChecklistDao
}