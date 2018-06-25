package com.example.nimolee.pictureloadertesttask.ui.fragment.picture.list

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nimolee.pictureloadertesttask.R
import com.example.nimolee.pictureloadertesttask.tools.Constant
import kotlinx.android.synthetic.main.fragment_picture_list.view.*

class PictureListFragment : Fragment() {
    private var columnCount = 1
    private var viewModel: PictureListViewModel? = null

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this)[PictureListViewModel::class.java]
        viewModel?.init(context!!)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_picture_list, container, false)
        if (view.list is RecyclerView) {
            with(view.list) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                Constant.allImages.observeForever {
                    adapter = MyPictureRecyclerViewAdapter(listener, viewModel, it)
                }

            }
        }
        view.swipeToRefresh.setOnRefreshListener {
            viewModel?.refresh()
            view.swipeToRefresh.isRefreshing = false
        }
        viewModel?.getAllImage()
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        fun openPicture(itemId: Int)
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
                PictureListFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}
