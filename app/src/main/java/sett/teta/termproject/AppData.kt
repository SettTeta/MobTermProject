package sett.teta.termproject

import java.util.*

object AppData {
    var checklists = mutableListOf(Check(UUID.randomUUID(),"A1","10-10-10","7AM", "CLEAN NOW", false),
                                   Check(UUID.randomUUID(),"B1","23-04-10","12PM", "Wash dishes", true),
                                   Check(UUID.randomUUID(),"A3","01-07-10","4AM", "Make bed", false),
                                   Check(UUID.randomUUID(),"C2","16-06-10","1PM", "Replace table", false))
}