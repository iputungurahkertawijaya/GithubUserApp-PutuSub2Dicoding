package com.example.githubuserapp_putusub2dicoding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp_putusub2dicoding.Respons.ModelUser
import com.example.githubuserapp_putusub2dicoding.ViewModel.UserViewModel
import com.example.githubuserapp_putusub2dicoding.adapter.AdapterUser
import com.example.githubuserapp_putusub2dicoding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var vieModel : UserViewModel
    private lateinit var userAdapter : AdapterUser
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title="Github User App"
        binding.apply {
            bgSearch.visibility = View.VISIBLE
            bgTxtsearch.visibility = View.VISIBLE
        }
        userAdapter = AdapterUser()
        userAdapter.notifyDataSetChanged()
        userAdapter.setOnItemClickCallBack(object : AdapterUser.OnItemClickCallBack{
            override fun onItemClick(data: ModelUser) {
                Intent(this@MainActivity, DetailActivityUsers::class.java).also {
                    it.putExtra(DetailActivityUsers.EXTRA_USERNAME, data.login)
                    startActivity(it)
                }
            }

        })
        vieModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)


        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = userAdapter

            search.setOnClickListener {
                searcUser()
                binding.apply {
                    bgSearch.visibility = View.GONE
                    bgTxtsearch.visibility = View.GONE
                }
            }

            query.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    searcUser()
                    binding.apply {
                        bgSearch.visibility = View.GONE
                        bgTxtsearch.visibility = View.GONE
                    }
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        vieModel.getSearchUser().observe(this,{
            if (it!=null) {
                userAdapter.setUserList(it)
                showLoading(false)
                binding.apply {
                    bgSearch.visibility = View.GONE
                    bgTxtsearch.visibility = View.GONE
                }
            }
        })
    }

    private fun searcUser(){
        binding.apply {
            val queryKey = query.text.toString()
            if (queryKey.isEmpty()) return
            showLoading(true)
            vieModel.setSearchUser(queryKey)
        }
    }

    private fun showLoading(state: Boolean){
        binding.apply {
            if (state){
                progressBar.visibility = View.VISIBLE
            }else{
                progressBar.visibility = View.GONE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settings) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}