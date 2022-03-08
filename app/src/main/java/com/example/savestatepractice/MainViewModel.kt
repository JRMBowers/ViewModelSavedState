package com.example.savestatepractice

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.savestatepractice.recyler.RecyclerAdapter
import com.example.savestatepractice.recyler.RecyclerItem

class MainViewModel(private val state: SavedStateHandle) : ViewModel() {
    var recyclerItems = mutableListOf<RecyclerItem>()
    var recyclerAdapter = RecyclerAdapter(recyclerItems)
    var recyclerView = R.id.rvRecycler


}