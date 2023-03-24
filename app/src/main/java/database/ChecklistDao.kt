package database

import androidx.lifecycle.LiveData
import androidx.room.*
import sett.teta.termproject.Check
import java.util.UUID

@Dao
interface ChecklistDao {

    @Query("SELECT * FROM checklistDatabase")
    fun getChecklist(): LiveData<List<Check>>

    @Query("SELECT * FROM checklistDatabase where id = (:id)")
    fun getCheck(id: UUID): LiveData<Check>

    @Query("SELECT * FROM checklistDatabase where checked = true")
    fun getChecked(): LiveData<List<Check>>

    @Query("SELECT * FROM checklistDatabase where checked = false")
    fun getUnChecked(): LiveData<List<Check>>

    @Insert
    fun insertCheck(c: Check)

    @Update
    fun updateCheck(c: Check)

    @Query("DELETE FROM checklistDatabase")
    fun deleteCheckList()

    @Query("DELETE FROM checklistDatabase where id = (:id)")
    fun deleteCheck(id: UUID)
}