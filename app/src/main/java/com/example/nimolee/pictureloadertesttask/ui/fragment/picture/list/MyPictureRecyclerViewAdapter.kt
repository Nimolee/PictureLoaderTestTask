package com.example.nimolee.pictureloadertesttask.ui.fragment.picture.list


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.nimolee.pictureloadertesttask.R
import com.example.nimolee.pictureloadertesttask.data.`object`.PictureObject
import com.example.nimolee.pictureloadertesttask.ui.fragment.picture.list.PictureListFragment.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.fragment_picture.view.*

class MyPictureRecyclerViewAdapter(
        private val mListener: OnListFragmentInteractionListener?,
        private val viewModel: PictureListViewModel?,
        private val mValues: ArrayList<PictureObject>?)
    : RecyclerView.Adapter<MyPictureRecyclerViewAdapter.ViewHolder>() {
    // private var mValues: ArrayList<PictureObject>? = ArrayList()

    init {
        viewModel?.getAllImage()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_picture, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mValues != null) {
            val item = mValues!![position]
            holder.mURLView.text = item.url
            holder.mStatusView.text = when (item.status) {
                0 -> "In queue"
                1 -> "In progress"
                2 -> "Downloaded"
                3 -> "Error"
                4 -> "Internet error"
                else -> {
                    "Something wrong"
                }
            }
            when (item.status) {
                0 -> viewModel?.saveImageToDatabase(item.id, item.url)
                1 -> viewModel?.saveImageToDatabase(item.id, item.url)
                2 -> holder.mImageView.setImageBitmap(item.picture)
                4 -> viewModel?.saveImageToDatabase(item.id, item.url)
                else -> {
                }
            }
            holder.mURLView.text = item.url
            with(holder.mView) {
                setOnClickListener {
                    mListener?.openPicture(item.id)
                }
            }
        }
    }

    override fun getItemCount(): Int = mValues?.size ?: 0

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mURLView: TextView = mView.cardURL
        val mStatusView: TextView = mView.cardStatus
        val mImageView: ImageView = mView.cardImage

        override fun toString(): String {
            return super.toString() + " '" + mURLView.text + "'"
        }
    }


}
