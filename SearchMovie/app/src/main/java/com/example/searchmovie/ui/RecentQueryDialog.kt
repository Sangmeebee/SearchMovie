package com.example.searchmovie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.searchmovie.R
import com.example.searchmovie.databinding.DialogQueryRecentBinding
import com.example.searchmovie.observer.MainViewModel

class RecentQueryDialog : DialogFragment() {

    lateinit var binding: DialogQueryRecentBinding
    private val vm by activityViewModels<MainViewModel>()
    private val queryAdapter = QueryAdapter {
        vm.query.value = it
        dismiss()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_query_recent, container, false).apply {
            binding = DataBindingUtil.bind(this)!!
            binding.vm = vm
            vm.callQueryList()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observerCallback()

        //최근검색 목록 불러오기
        vm.queryList.value?.let { queryAdapter.clearAndAddQuery(it) }

        binding.rvQueryList.apply {
            setHasFixedSize(true)
            adapter = queryAdapter
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
    }

    private fun observerCallback() {
        vm.showDialog.observe(viewLifecycleOwner, Observer {
            if (!it) {
                dismiss()
            }
        })
    }
}
