package com.soban.bepositivet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ListOfDonorsAvailable : AppCompatActivity() {

    lateinit var recyclerViewDonorsList: RecyclerView
    lateinit var donorlist : ArrayList<ModelDonorsList>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_donors_available)

        val ll_donorslist = findViewById<LinearLayout>(R.id.ll_donorslist)
        val ll_donorsnotavail = findViewById<LinearLayout>(R.id.ll_donorsnotavail)
        val donorORbank = intent.getStringExtra("donorOrbank")
        val bloodgroup = intent.getStringExtra("bloodgroup")
        val country = intent.getStringExtra("country")
        val state = intent.getStringExtra("state")
        val city = intent.getStringExtra("city")


        recyclerViewDonorsList = findViewById(R.id.recyclerview_donorslist)
        //recyclerViewOurServices.layoutManager = LinearLayoutManager(this)
        recyclerViewDonorsList.setHasFixedSize(true)

        donorlist = arrayListOf<ModelDonorsList>()

        val ref = FirebaseDatabase.getInstance().getReference("$bloodgroup").child("$country").child("$state").child("$city")
        ref.addListenerForSingleValueEvent(object :ValueEventListener, (ModelDonorsList) -> Unit {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach{
                    val data: ModelDonorsList = it.getValue(ModelDonorsList::class.java)!!
                    Log.d("donorlist","$data")

                    if (data.equals(null)){
                        ll_donorsnotavail.visibility = View.VISIBLE
                    }else{
                        ll_donorslist.visibility = View.VISIBLE
                        donorlist.add(data)
                    }
                }
                recyclerViewDonorsList.adapter = AdapterDonorsList(donorlist,this)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun invoke(p1: ModelDonorsList) {
                TODO("Not yet implemented")
            }

        })

    }
}