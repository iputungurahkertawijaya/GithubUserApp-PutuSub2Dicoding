package com.example.githubuserapp_putusub2dicoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuserapp_putusub2dicoding.ViewModel.DetailUserViewModel
import com.example.githubuserapp_putusub2dicoding.adapter.SectionsPagerAdapter
import com.example.githubuserapp_putusub2dicoding.databinding.ActivityDetailUsersBinding
import com.example.githubuserapp_putusub2dicoding.transformpage.ZoomOutPageTransformer
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivityUsers : AppCompatActivity() {
    companion object {
        const val EXTRA_USERNAME = "extra_username"
        private val TITLE_TABS = intArrayOf(R.string.followers, R.string.following)
    }

    private lateinit var binding: ActivityDetailUsersBinding
    private lateinit var vieModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val username = intent.getStringExtra(EXTRA_USERNAME)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        vieModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailUserViewModel::class.java)
        if (username != null) {
            vieModel.setUserDetail(username)
            vieModel.getUserDetails().observe(this, {
                if (it != null) {
                    binding.apply {
                        if (it.name == null){
                            tvNama.text = getString(R.string.name_not_found)
                        }else{
                            tvNama.text = it.name
                        }
                        tvUsername.text ="@${it.login}"
                        if (it.location == null){
                            tvLocation.text = "-"
                        }else{
                            tvLocation.text = it.location

                        }
                        tvCompany.text ="@${it.login}"
                        if (it.company == null){
                            tvCompany.text = "-"
                        }else{

                            tvCompany.text  = it.company
                        }
                        tvFollwers.text = "${it.followers}"
                        tvFollwing.text = "${it.following}"
                        tvRepos.text = "${it.public_repos}"
                        Glide.with(this@DetailActivityUsers)
                            .load(it.avatar_url)
                            .apply(RequestOptions.circleCropTransform().placeholder(R.drawable.ic_baseline_account_circle_24))
                            .into(img)
                    }
                }
            })
            val sectionsPagerAdapter = SectionsPagerAdapter(this, bundle)
            binding.apply {
                viewPager.adapter = sectionsPagerAdapter
                viewPager.setPageTransformer(ZoomOutPageTransformer())
                TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
                    tab.text = resources.getString(TITLE_TABS[position])
                }.attach()
            }
        }
    }
}
