package sett.teta.termproject

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "checklistDatabase")
data class Check(@PrimaryKey var id: UUID,
                 var room: String,
                 var date: String,
                 var checkout: String,
                 var notes: String,
                 var checked: Boolean)
