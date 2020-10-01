package com.example.searchmovie.ui

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.searchmovie.R

class RecentQueryDialog : DialogFragment(), OnListItemSelectedListener {

    private lateinit var onListItemSelectedListener: OnListItemSelectedListener
    private lateinit var queryList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            queryList = it.getStringArrayList("query_list") as ArrayList<String>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_query_recent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnCancel = view.findViewById<Button>(R.id.btn_cancel)
        val rvQueryList = view.findViewById<RecyclerView>(R.id.rv_query_list)
        val queryAdapter = QueryAdapter(this)

        queryAdapter.clearAndAddQuery(queryList)

        rvQueryList.apply {
            setHasFixedSize(true)
            adapter = queryAdapter
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }

        btnCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onListItemSelectedListener = activity as OnListItemSelectedListener
    }

    //다이얼로그 크기 지정
    override fun onResume() {
        super.onResume()
        val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val deviceWidth = size.x
        params?.width = (deviceWidth * 0.9).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    override fun selectedItem(query: String) {
        onListItemSelectedListener.selectedItem(query)
        dismiss()
    }

    companion object {
        fun newInstance(queryList: ArrayList<String>): RecentQueryDialog =
            RecentQueryDialog().apply {
                arguments = Bundle().apply {
                    putStringArrayList("query_list", queryList)
                }
            }
    }
}
