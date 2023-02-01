package sett.teta.termproject

import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {
    private val userBank = listOf(Users("Morg", "Kief", true),
                                  Users("Ming", "Teta", false)
    )

    private var userIndex = -1

    val currentUserType: Boolean
        get() = userBank[userIndex].type

    fun getUserIndex(userUsername: String, userPassword: String): Boolean{
        for (i in userBank.indices){
            if (userUsername == userBank[i].username && userPassword == userBank[i].password){
                userIndex = i
                return true
            }
        }
        return false
    }


}