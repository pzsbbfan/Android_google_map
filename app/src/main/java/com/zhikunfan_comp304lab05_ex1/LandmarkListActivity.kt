package com.zhikunfan_comp304lab05_ex1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhikunfan_comp304lab05_ex1.adapter.LandmarkAdapter
import com.zhikunfan_comp304lab05_ex1.model.Landmark

class LandmarkListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LandmarkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landmark_list)

        recyclerView = findViewById(R.id.recycler_view_landmarks)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val landmarks = intent.getParcelableArrayListExtra<Landmark>("landmarks") ?: emptyList()

        adapter = LandmarkAdapter(landmarks) { item ->
            val intent = Intent(this, LandmarkDetailActivity::class.java)
            intent.putExtra("landmark", item)
            startActivity(intent)
        }
        recyclerView.adapter = adapter


        val goBackButton = findViewById<Button>(R.id.button_go_back)
        goBackButton.setOnClickListener {
            finish()
        }
    }
}
