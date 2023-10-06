package com.alfsuace.androidexcercise2.domain

import com.alfsuace.androidexcercise2.app.Either
import com.alfsuace.androidexcercise2.app.ErrorApp


class SaveUserUseCase(private val repository: UserRepository) {

    operator fun invoke(input: Input): Either<ErrorApp, Boolean> {
        return repository.save(input);
    }

    data class Input(val username: String, val surname: String)
}