package com.example.nimolee.pictureloadertesttask.ui.fragment.picture.list


import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.nimolee.pictureloadertesttask.R
import com.example.nimolee.pictureloadertesttask.data.`object`.PictureObject
import com.example.nimolee.pictureloadertesttask.tools.Constant.Companion.loading
import com.example.nimolee.pictureloadertesttask.ui.fragment.picture.list.PictureListFragment.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.fragment_picture.view.*

class MyPictureRecyclerViewAdapter(
        private val mListener: OnListFragmentInteractionListener?,
        private val viewModel: PictureListViewModel?,
        private val mValues: ArrayList<PictureObject>?)
    : RecyclerView.Adapter<MyPictureRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_picture, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mValues != null) {
            val item = mValues[position]
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
            holder.mImageView.setImageBitmap(null)
            when (item.status) {
                0 -> {
                    if (!loading) {
                        viewModel?.saveImageToDatabase(item.id, item.url)
                        holder.mStatusView.text = "In progress"
                        viewModel?.changeSilenseStatus(item.id, 1)
                    } else {
                        holder.mStatusView.text = "In queue"
                        viewModel?.changeSilenseStatus(item.id, 0)
                    }
                }
                2 -> {
                    val newWidth = 768
                    val newHeight = item.picture?.height!! * newWidth / item.picture?.width!!
                    holder.mImageView.setImageBitmap(Bitmap.createScaledBitmap(item.picture, newWidth, newHeight, false))
                }
                3 -> {
                }
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
