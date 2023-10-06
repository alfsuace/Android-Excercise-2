package com.alfsuace.androidexcercise2.data.local

import android.content.Context
import com.alfsuace.androidexcercise2.domain.SaveUserUseCase
import com.alfsuace.androidexcercise2.domain.User
import com.alfsuace.androidexcercise2.app.Either
import com.alfsuace.androidexcercise2.app.ErrorApp
import com.alfsuace.androidexcercise2.app.left
import com.alfsuace.androidexcercise2.app.right
import java.lang.Exception

class XmlLocalDataSource(private val context: Context) {

    private val sharedPref = context.getSharedPreferences("users", Context.MODE_PRIVATE)

    fun saveUser(input: SaveUserUseCase.Input): Either<ErrorApp, Boolean> {
        return try {
            with(sharedPref.edit()) {
                putInt("id", (1..100).random())
                putString("username", input.username)
                putString("surname", input.surname)
                apply()
            }
            true.right()
        } catch (ex: Exception) {
            ErrorApp.UnknowError.left()
        }
    }

    fun findUser(): Either<ErrorApp, User> {
        return try {
            User(
                sharedPref.getInt("id", 0),
                sharedPref.getString("username", "")!!,
                sharedPref.getString("surname", "")!!,
            ).right()
        } catch (ex: Exception) {
            return ErrorApp.UnknowError.left()
        }
    }
}