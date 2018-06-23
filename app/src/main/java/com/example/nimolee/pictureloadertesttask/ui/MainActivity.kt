package com.example.nimolee.pictureloadertesttask.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.nimolee.pictureloadertesttask.R
import com.example.nimolee.pictureloadertesttask.ui.fragment.picture.list.PictureListFragment
import com.example.nimolee.pictureloadertesttask.ui.fragment.picture.list.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

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
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
