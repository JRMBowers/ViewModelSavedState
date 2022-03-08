package com.example.savestatepractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.savestatepractice.recyler.RecyclerAdapter
import com.example.savestatepractice.recyler.RecyclerItem


class MainActivity : AppCompatActivity() {

    private val vm by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null){
            Log.d("restored", vm.recyclerView.toString())
            val listOfStrings = savedInstanceState.getStringArrayList("vm")
            listOfStrings?.forEach {
                vm.recyclerItems.add(RecyclerItem(it))
            }
            vm.recyclerAdapter = RecyclerAdapter(vm.recyclerItems)

        }

        val submitButton = findViewById<Button>(R.id.btnSubmit)
        val recyclerView = findViewById<RecyclerView>(R.id.rvRecycler)
        val secondPage = findViewById<Button>(R.id.btnSecondPage)

        val adapter = vm.recyclerAdapter

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        submitButton.setOnClickListener {
            val textBox = findViewById<TextView>(R.id.etTodoItem).text.toString()
            if (textBox.isNotEmpty()){
                vm.recyclerItems.add(RecyclerItem(textBox))
                adapter.notifyItemInserted(vm.recyclerItems.size - 1)
            }
        }

        secondPage.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var outstateString = arrayListOf<String>()
        if (outState != null){
            vm.recyclerItems.forEach {
                outstateString.add(it.text)
            }
        }
            outState.putStringArrayList("vm", outstateString)
    }
}