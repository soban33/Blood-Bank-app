package com.soban.bepositivet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView

class FillTheForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill_the_form)

        val donorOrbank = intent.getStringExtra("donorORbank")

        val txt_selectBloodGrp = findViewById<TextView>(R.id.txt_selectBloodGrp)
        val txt_selectCountry = findViewById<TextView>(R.id.txt_selectCountry)
        val txt_selectState = findViewById<TextView>(R.id.txt_selectState)
        val txt_selectCity = findViewById<TextView>(R.id.txt_selectCity)
        val txt_bloodGroup = findViewById<TextView>(R.id.txt_bloodGroup)
        val txt_countryname = findViewById<TextView>(R.id.txt_countryname)
        val txt_statename = findViewById<TextView>(R.id.txt_statename)
        val txt_cityname = findViewById<TextView>(R.id.txt_cityname)
        val btn_showdonorslist = findViewById<Button>(R.id.btn_showdonorslist)


        //selecting blood group
        txt_selectBloodGrp.setOnClickListener {
            val popupMenu = PopupMenu(this,it)
            popupMenu.setOnMenuItemClickListener {

                when(it.itemId){
                    R.id.grp_ap -> {
                        txt_bloodGroup.text = "A+"
                    }
                    R.id.grp_an -> {
                        txt_bloodGroup.text = "A-"
                    }
                    R.id.grp_bp -> {
                        txt_bloodGroup.text = "B+"
                    }
                    R.id.grp_bn -> {
                        txt_bloodGroup.text = "B-"
                    }
                    R.id.grp_abp -> {
                        txt_bloodGroup.text = "AB+"
                    }
                    R.id.grp_abn -> {
                        txt_bloodGroup.text = "AB-"
                    }
                    R.id.grp_op -> {
                        txt_bloodGroup.text = "O+"
                    }
                    R.id.grp_on -> {
                        txt_bloodGroup.text = "O-"
                    }
                }

                true
            }
            popupMenu.inflate(R.menu.menu_bloodgroups)
            popupMenu.show()
        }

        //selecting country name
        txt_selectCountry.setOnClickListener {
            val popupMenu = PopupMenu(this,it)
            popupMenu.setOnMenuItemClickListener {

                when(it.itemId){
                    R.id.country_india -> {
                        txt_countryname.text = "India"
                    }
                }

                true
            }
            popupMenu.inflate(R.menu.menu_countryname)
            popupMenu.show()
        }

        //selecting state name
        txt_selectState.setOnClickListener {
            val popupMenu = PopupMenu(this,it)
            popupMenu.setOnMenuItemClickListener {

                when(it.itemId){
                    R.id.state_mh -> {
                        txt_statename.text = "Maharashtra"
                    }
                    R.id.state_up -> {
                        txt_statename.text = "Uttar Pradesh"
                    }
                    R.id.state_kl -> {
                        txt_statename.text = "Kerala"
                    }
                    R.id.state_ap -> {
                        txt_statename.text = "Andhra Pradesh"
                    }
                }

                true
            }
            popupMenu.inflate(R.menu.menu_statename)
            popupMenu.show()
        }

        //selecting city name
        txt_selectCity.setOnClickListener {
            val popupMenu = PopupMenu(this,it)
            popupMenu.setOnMenuItemClickListener {

                when(it.itemId){
                    R.id.city_amravati -> {
                        txt_cityname.text = "Amravati"
                    }
                    R.id.city_akola -> {
                        txt_cityname.text = "Akola"
                    }
                    R.id.city_nagpur -> {
                        txt_cityname.text = "Nagpur"
                    }
                    R.id.city_washim -> {
                        txt_cityname.text = "Washim"
                    }
                }

                true
            }
            popupMenu.inflate(R.menu.menu_cityname)
            popupMenu.show()
        }

        if (donorOrbank == "donor"){
            btn_showdonorslist.setOnClickListener {
                val intent = Intent(this,ListOfDonorsAvailable::class.java)
                intent.putExtra("donorOrbank","donor")
                intent.putExtra("bloodgroup", txt_bloodGroup.text.toString())
                intent.putExtra("country",txt_countryname.text.toString())
                intent.putExtra("state",txt_statename.text.toString())
                intent.putExtra("city",txt_cityname.text.toString())
                startActivity(intent)
            }
        }else{
            btn_showdonorslist.setOnClickListener {
                val intent = Intent(this,BankAvailability::class.java)
                intent.putExtra("donorOrbank","donor")
                intent.putExtra("bloodgroup", txt_bloodGroup.text.toString())
                intent.putExtra("country",txt_countryname.text.toString())
                intent.putExtra("state",txt_statename.text.toString())
                intent.putExtra("city",txt_cityname.text.toString())
                startActivity(intent)
            }
        }


    }
}