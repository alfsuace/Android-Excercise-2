package com.alfsuace.androidexcercise2.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer

import com.alfsuace.androidexcercise2.data.UserDataRepository
import com.alfsuace.androidexcercise2.data.local.XmlLocalDataSource
import com.alfsuace.androidexcercise2.domain.GetUserUseCase
import com.alfsuace.androidexcercise2.domain.SaveUserUseCase
import com.alfsuace.androidexcercise2.domain.User
import com.alfsuace.androidexcercise2.R


class MainActivity : AppCompatActivity() {

    //Para usar esta creación se ha añadido:  implementation "androidx.activity:activity-ktx:1.7.2"
    val viewModel: MainViewModel by lazy {
        MainViewModel(
            SaveUserUseCase(UserDataRepository(XmlLocalDataSource(this))),
            GetUserUseCase(UserDataRepository(XmlLocalDataSource(this)))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupObservers()
        viewModel.loadUser()
        setupViewCleanser()
    }

    private fun setupView() {
        val actionButton = findViewById<Button>(R.id.action_save)
        actionButton.setOnClickListener {
            viewModel.saveUser(getNameInput(), getSurnameInput())
        }
    }
    private fun setupViewCleanser(){
        val actionButton = findViewById<Button>(R.id.action_cleanse)
        actionButton.setOnClickListener {
            setNameInputToEmpty()
            setSurnameInputToEmpty()
        }
    }

    private fun getNameInput(): String =
        findViewById<EditText>(R.id.input_name).text.toString()

    private fun getSurnameInput(): String =
        findViewById<EditText>(R.id.input_surname).text.toString()

    private fun setSurnameInputToEmpty()=
        findViewById<EditText>(R.id.input_surname).text.clear()
    private fun setNameInputToEmpty()=
        findViewById<EditText>(R.id.input_name).text.clear()


    private fun setupObservers() {
        val observer = Observer<MainViewModel.UiState> {
            //Código al notificar el observador
            it.user?.apply {
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun bindData(user: User) {
        setNameInput(user.username)
        setSurnameInput(user.surname)
    }

    private fun setNameInput(name: String) {
        findViewById<EditText>(R.id.input_name).setText(name)
    }

    private fun setSurnameInput(surname: String) {
        findViewById<EditText>(R.id.input_surname).setText(surname)
    }

}