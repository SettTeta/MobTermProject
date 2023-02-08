package sett.teta.termproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Check( @PrimaryKey val id: UUID = UUID.randomUUID(),
                  @ColumnInfo val room: String,
                  @ColumnInfo val date: String,
                  @ColumnInfo val checkout: String,
                  @ColumnInfo val notes: String,
                  @ColumnInfo var checked: Boolean)
