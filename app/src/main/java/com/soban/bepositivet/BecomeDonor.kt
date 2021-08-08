package com.soban.bepositivet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class BecomeDonor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_become_donor)

        val edttxt_entername = findViewById<EditText>(R.id.edttxt_entername)
        val edttxt_entermobile = findViewById<EditText>(R.id.edttxt_entermobile)
        val edttxt_enteraddress = findViewById<EditText>(R.id.edttxt_enteraddress)
        val txt_cityselected = findViewById<TextView>(R.id.txt_cityselected)
        val txt_stateselected = findViewById<TextView>(R.id.txt_stateselected)
        val txt_countryselected = findViewById<TextView>(R.id.txt_countryselected)
        val txt_bloodgroupselected = findViewById<TextView>(R.id.txt_bloodgroupselected)
        val img_btn_dropdownmenu_city = findViewById<ImageView>(R.id.img_btn_dropdownmenu_city)
        val img_btn_dropdownmenu_state = findViewById<ImageView>(R.id.img_btn_dropdownmenu_state)
        val img_btn_dropdownmenu_forcountry = findViewById<ImageView>(R.id.img_btn_dropdownmenu_forcountry)
        val img_btn_dropdownmenu_bloodgroup = findViewById<ImageView>(R.id.img_btn_dropdownmenu_bloodgroup)
        val btn_done_bedonor = findViewById<Button>(R.id.btn_done_bedonor)

        img_btn_dropdownmenu_city.setOnClickListener {
            val popupMenu = PopupMenu(this,it)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.city_amravati -> {
                        txt_cityselected.text = "Amravati"
                    }
                    R.id.city_akola -> {
                        txt_cityselected.text = "Akola"
                    }
                    R.id.city_nagpur -> {
                        txt_cityselected.text = "Nagpur"
                    }
                    R.id.city_washim -> {
                        txt_cityselected.text = "Washim"
                    }
                }
                true
            }
            popupMenu.inflate(R.menu.menu_cityname)
            popupMenu.show()
        }
        img_btn_dropdownmenu_state.setOnClickListener {
            val popupMenu = PopupMenu(this,it)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.state_mh -> {
                        txt_stateselected.text = "Maharashtra"
                    }
                    R.id.state_up -> {
                        txt_stateselected.text = "Uttar Pradesh"
                    }
                    R.id.state_kl -> {
                        txt_stateselected.text = "Kerala"
                    }
                    R.id.state_ap -> {
                        txt_stateselected.text = "Andhra Pradesh"
                    }
                }
                true
            }
            popupMenu.inflate(R.menu.menu_statename)
            popupMenu.show()
        }
        img_btn_dropdownmenu_forcountry.setOnClickListener {
            val popupMenu = PopupMenu(this,it)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.country_india -> {
                        txt_countryselected.text = "India"
                    }
                }
                true
            }
            popupMenu.inflate(R.menu.menu_countryname)
            popupMenu.show()
        }
        img_btn_dropdownmenu_bloodgroup.setOnClickListener {
            val popupMenu = PopupMenu(this,it)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.grp_ap -> {
                        txt_bloodgroupselected.text = "A+"
                    }
                    R.id.grp_an -> {
                        txt_bloodgroupselected.text = "A-"
                    }
                    R.id.grp_bp -> {
                        txt_bloodgroupselected.text = "B+"
                    }
                    R.id.grp_bn -> {
                        txt_bloodgroupselected.text = "B-"
                    }
                    R.id.grp_abp -> {
                        txt_bloodgroupselected.text = "AB+"
                    }
                    R.id.grp_abn -> {
                        txt_bloodgroupselected.text = "AB-"
                    }
                    R.id.grp_op -> {
                        txt_bloodgroupselected.text = "O+"
                    }
                    R.id.grp_on -> {
                        txt_bloodgroupselected.text = "O-"
                    }
                }
                true
            }
            popupMenu.inflate(R.menu.menu_bloodgroups)
            popupMenu.show()
        }

        btn_done_bedonor.setOnClickListener {
            val beDonorMap = HashMap<String,Any>()
            beDonorMap["name"] = edttxt_entername.text.toString()
            beDonorMap["mobilenumber"] = edttxt_entermobile.text.toString()
            beDonorMap["city"] = edttxt_enteraddress.text.toString()

            val ref = FirebaseDatabase.getInstance().getReference(txt_bloodgroupselected.text.toString()).child(txt_countryselected.text.toString()).child(txt_stateselected.text.toString()).child(txt_cityselected.text.toString()).child(edttxt_entername.text.toString())
            ref.updateChildren(beDonorMap).addOnSuccessListener {
                Toast.makeText(this,"You are donor now",Toast.LENGTH_LONG).show()
            }
        }
    }
}