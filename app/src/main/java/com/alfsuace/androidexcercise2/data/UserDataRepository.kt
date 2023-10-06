package com.alfsuace.androidexcercise2.data

import com.alfsuace.androidexcercise2.data.local.XmlLocalDataSource
import com.alfsuace.androidexcercise2.domain.SaveUserUseCase
import com.alfsuace.androidexcercise2.domain.User
import com.alfsuace.androidexcercise2.domain.UserRepository
import com.alfsuace.androidexcercise2.app.Either
import com.alfsuace.androidexcercise2.app.ErrorApp


class UserDataRepository(private val localDataSource: XmlLocalDataSource) : UserRepository {

    override fun save(input: SaveUserUseCase.Input): Either<ErrorApp, Boolean> {
        return localDataSource.saveUser(input)
    }

    override fun obtain(): Either<ErrorApp, User> {
        return localDataSource.findUser()
    }
}