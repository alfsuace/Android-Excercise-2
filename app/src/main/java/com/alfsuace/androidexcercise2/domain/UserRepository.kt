package com.alfsuace.androidexcercise2.domain

import com.alfsuace.androidexcercise2.app.Either
import com.alfsuace.androidexcercise2.app.ErrorApp

interface UserRepository {

    fun save(input: SaveUserUseCase.Input): Either<ErrorApp, Boolean>
    fun obtain(): Either<ErrorApp, User>
}