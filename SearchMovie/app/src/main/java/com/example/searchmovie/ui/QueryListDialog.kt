package com.example.searchmovie.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.searchmovie.R

class QueryListDialog : DialogFragment(), OnItemSelectedListener {

    private lateinit var queryList: ArrayList<String>
    private lateinit var onItemSelectedListener: OnItemSelectedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { queryList = it.getStringArrayList("Query_List") as ArrayList<String> }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_list_query, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnCancel = view.findViewById<Button>(R.id.btn_cancel)
        val rvQuery = view.findViewById<RecyclerView>(R.id.rv_query)

        val queryAdapter = QueryAdapter(this)
        queryAdapter.clearAndAddQuery(queryList)
        rvQuery.apply {
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
        onItemSelectedListener = activity as OnItemSelectedListener
    }

    override fun selectItem(query: String) {
        onItemSelectedListener.selectItem(query)
        dismiss()
    }

    companion object {
        fun newInstance(queryList: ArrayList<String>) = QueryListDialog().apply {
            arguments = Bundle().apply {
                putStringArrayList("Query_List", queryList)
            }
        }
    }
}
