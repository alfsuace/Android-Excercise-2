package com.alfsuace.androidexcercise2.domain

import com.alfsuace.androidexcercise2.app.Either
import com.alfsuace.androidexcercise2.app.ErrorApp

class GetUserUseCase(private val repository: UserRepository) {

    operator fun invoke(): Either<ErrorApp, User> {
        return repository.obtain()
    }
}