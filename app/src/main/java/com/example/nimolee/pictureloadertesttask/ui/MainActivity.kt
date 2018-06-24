package com.example.nimolee.pictureloadertesttask.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.nimolee.pictureloadertesttask.R
import com.example.nimolee.pictureloadertesttask.ui.fragment.picture.list.PictureListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PictureListFragment.OnListFragmentInteractionListener {
    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
        viewModel?.init(this)
        fab.setOnClickListener { view ->
            viewModel?.openUrl(urlForLoad.text.toString())
        }
        supportFragmentManager.beginTransaction().replace(R.id.fragmentLocation, PictureListFragment.newInstance(1)).commitNow()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun openPicture(itemId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
