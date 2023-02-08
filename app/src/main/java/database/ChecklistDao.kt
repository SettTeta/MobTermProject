package database

import androidx.lifecycle.LiveData
import androidx.room.*
import sett.teta.termproject.Check
import java.util.UUID

@Dao
interface ChecklistDao {

    @Query("SELECT * FROM checkdatabase")
    fun getChecklist(): LiveData<List<Check>>

    @Query("SELECT * FROM Check where id = (:id)")
    fun getCheck(id:UUID): LiveData<Check>

    @Insert
    fun insertCheck(c: Check)

    @Update
    fun updateCheck(c: Check)

    @Query("DELETE FROM Check")
    fun deleteCheckList()

    @Query("DELETE FROM Check where id = (:id)")
    fun deleteCheck(id: UUID)
}