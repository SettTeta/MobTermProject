package sett.teta.termproject.checklistpackage

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import database.ChecklistDatabase
import sett.teta.termproject.Check
import java.util.UUID
import java.util.concurrent.Executors

class ChecklistRepository private constructor(context: Context){

    private val database: ChecklistDatabase = Room.databaseBuilder(
        context.applicationContext,
        ChecklistDatabase::class.java,
        "checklist-database"
    ).build()

    private val checklistDao = database.checklistDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getChecklist(): LiveData<List<Check>> = checklistDao.getChecklist()

    fun getCheck(id: UUID): LiveData<Check> = checklistDao.getCheck(id)

    fun getChecked(): LiveData<List<Check>> = checklistDao.getChecked()

    fun getUnChecked(): LiveData<List<Check>> = checklistDao.getUnChecked()

    fun addCheck(check: Check){
        executor.execute{
            checklistDao.insertCheck(check)
        }
    }

    fun updateChecklist(check: Check){
        executor.execute{
            checklistDao.updateCheck(check)
        }
    }

    fun deleteChecklist(){
        executor.execute{
            checklistDao.deleteCheckList()
        }
    }

    fun deleteCheck(id: UUID){
        executor.execute {
            checklistDao.deleteCheck(id)
        }
    }

    companion object {
        private var instance: ChecklistRepository? = null

        fun initialize(context: Context){
            if (instance == null){
                instance = ChecklistRepository(context)
            }
        }

        fun get(): ChecklistRepository {
            return instance ?:
            throw java.lang.IllegalStateException("Repo must be initialized")
        }
    }
}