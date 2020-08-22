package com.sunil.agentapp.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindorks.retrofit.coroutines.utils.Status
import com.sunil.agentapp.R
import com.sunil.agentapp.model.Result
import com.sunil.agentapp.view.adapter.MovieListAdapter
import com.sunil.agentapp.viewmodel.MovieDataViewModel
import com.sunil.assignment.network.ApiHelper
import com.sunil.assignment.network.RetrofitBuilder
import com.sunil.assignment.utils.isNetworkAvailable
import com.sunil.assignment.view.base.BaseActivity
import com.sunil.assignment.view.base.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private lateinit var viewModel: MovieDataViewModel
    private lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
        if (isNetworkAvailable(this@MainActivity)) {
            setupObservers()
        } else {
            Toast.makeText(this, getString(R.string.msg_no_internate), Toast.LENGTH_LONG).show()
        }
        ///

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextChange(newText: String): Boolean {
                    adapter.getFilter().filter(newText)
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    adapter.getFilter().filter(query)
                    return false
                }

            })
        //

    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MovieDataViewModel::class.java)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MovieListAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getMovieList().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { aboutCanada ->
                            Log.e("MainActivity", aboutCanada.toString())
                            supportActionBar!!.title = getString(R.string.title_name)
                            retrieveList(aboutCanada.results)
                        }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(row: List<Result>) {
        adapter.apply {
            addUsers(row)
            notifyDataSetChanged()
        }
    }
}

