package com.soban.bepositivet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BankAvailability : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_availability)

        val donorORbank = intent.getStringExtra("donorOrbank")
        val bloodgroup = intent.getStringExtra("bloodgroup")
        val country = intent.getStringExtra("country")
        val state = intent.getStringExtra("state")
        val city = intent.getStringExtra("city")

        //hooking views with variables
        val ll_bloodavail = findViewById<LinearLayout>(R.id.ll_bloodavail)
        val ll_bloodnotavail = findViewById<LinearLayout>(R.id.ll_bloodnotavail)
        val ll_placeorder = findViewById<LinearLayout>(R.id.ll_placeorder)
        val txt_unitsavail = findViewById<TextView>(R.id.txt_tottalunitsavail)
        val btn_proceedtoorder = findViewById<Button>(R.id.btn_proceedtoorder)
        val edttxt_entername = findViewById<EditText>(R.id.edttxt_entername)
        val edttxt_enteraddress = findViewById<EditText>(R.id.edttxt_enteraddress)
        val btn_placeorder = findViewById<Button>(R.id.btn_placeorder)
        val img_btn_dropdownmenu_forunits = findViewById<ImageView>(R.id.img_btn_dropdownmenu_forunits)
        val txt_unitsselected = findViewById<TextView>(R.id.txt_unitsselected)

        val ref = FirebaseDatabase.getInstance().getReference("/bloodbank").child("$bloodgroup").child("$country").child("$state").child("$city")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val unitsAvail = snapshot.getValue(ModelBloodAvail::class.java)
                if (unitsAvail != null){
                    ll_bloodavail.visibility = View.VISIBLE

                    txt_unitsavail.text = unitsAvail.unitsavail
                }else{
                    ll_bloodnotavail.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {  }

        })

        btn_proceedtoorder.setOnClickListener {
            ll_placeorder.visibility = View.VISIBLE
        }

        btn_placeorder.setOnClickListener {
            val orderMap = HashMap<String, Any>()
            orderMap["name"] = edttxt_entername.text.toString()
            orderMap["address"] = edttxt_enteraddress.text.toString()
            orderMap["units"] = txt_unitsselected.text.toString()
            orderMap["group"] = bloodgroup.toString()

            val ref = FirebaseDatabase.getInstance().getReference("orders").child(edttxt_entername.text.toString())
            ref.updateChildren(orderMap).addOnSuccessListener {
                Toast.makeText(this,"Order Placed",Toast.LENGTH_LONG).show()
            }
        }

        img_btn_dropdownmenu_forunits.setOnClickListener {
            val popupMenu = PopupMenu(this,it)
            popupMenu.setOnMenuItemClickListener{
                when(it.itemId){
                    R.id.one -> {
                        txt_unitsselected.text = "one"
                    }
                    R.id.two -> {
                        txt_unitsselected.text = "two"
                    }
                    R.id.three -> {
                        txt_unitsselected.text = "three"
                    }
                    R.id.four -> {
                        txt_unitsselected.text = "four"
                    }
                    R.id.five -> {
                        txt_unitsselected.text = "five"
                    }
                }
                true
            }
            popupMenu.inflate(R.menu.menu_units)
            popupMenu.show()
        }

    }
}