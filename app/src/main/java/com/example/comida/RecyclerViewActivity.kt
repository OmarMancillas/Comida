package com.example.comida

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {

    val onLongItemClickListener: (Int) -> Unit = { position->
        var telefono = "${Singleton.dataset.get(position).telefono}"
        Toast.makeText(this, "Telefono: ${telefono}", Toast.LENGTH_LONG).show()
        startActivity(Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", telefono, null)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        LoadData()

        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter=RestauranteAdapter(onLongItemClickListener)
    }

    override fun onResume() {
        super.onResume()

        recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun LoadData(){
        for(i in 0..20)
        {
            Singleton.dataset.add(
                Restaurante("Restaurante ${i}",
                    "8677270${i.toString().padStart(3,'0')}",
                    "Calle inventada ${i}",
                    ""))
        }
    }
}