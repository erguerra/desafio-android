package com.picpay.desafio.android.ui

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.ui.userList.UserListAdapter
import com.picpay.desafio.android.ui.userList.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    private val userListViewModel: UserListViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        initializeViews()
        setStateListener()
        userListViewModel.fetchUsers()
    }

    private fun initializeViews() {
        progressBar = findViewById(R.id.user_list_progress_bar)
        recyclerView = findViewById(R.id.recyclerView)
        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setStateListener() {
        lifecycleScope.launch {
            userListViewModel.viewState.collect { state ->
                when (state) {
                    is UserListViewModel.ViewState.Loading -> showProgressBar()
                    is UserListViewModel.ViewState.Retry -> showError()
                    is UserListViewModel.ViewState.Success -> showList(state.userList)
                }
            }
        }
    }

    private fun showList(userList: List<User>) {
        progressBar.visibility = View.GONE
        adapter.users = userList
    }

    private fun showError() {
        val message = getString(R.string.error)

        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE

        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
            .show()
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }
}
