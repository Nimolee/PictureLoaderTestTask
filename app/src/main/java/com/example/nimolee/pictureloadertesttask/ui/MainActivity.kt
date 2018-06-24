package com.example.nimolee.pictureloadertesttask.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.nimolee.pictureloadertesttask.R
import com.example.nimolee.pictureloadertesttask.ui.fragment.picture.list.PictureListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PictureListFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
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
