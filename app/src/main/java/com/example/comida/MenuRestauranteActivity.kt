package com.example.comida

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comida.entities.MenuRestaurante
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_menu_restaurante.*
import kotlinx.android.synthetic.main.footer_menu_restaurante.*
import kotlinx.android.synthetic.main.layout_menu_restaurante.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MenuRestauranteActivity : AppCompatActivity() {
    private var mNotificationManager: NotificationManager? = null
    private val NOTIFICATION_ID = 0
    private val PRIMARY_CHANNEL_ID = "primary_notification_channel"

    val onLongItemClickListener: (Int) -> Unit = { position ->
        var descripcion = "${Singleton.datasetMenuRestaurante.get(position).descripcion_producto}"
        Toast.makeText(this, "Producto: ${descripcion}", Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_restaurante)
        PeticionWS()

        loadData()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MenuRestauranteAdapter(onLongItemClickListener)

        val notifyIntent = Intent(this, AlarmReceiver::class.java)
        val notifyPendingIntent = PendingIntent.getBroadcast(
            this,
            NOTIFICATION_ID,
            notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager =
            getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val repeatInterval = 10000.toLong()
        val triggerTime = repeatInterval

        alarmManager?.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME,
            10000,
            repeatInterval,
            notifyPendingIntent
        )

        mNotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel()

    }

    fun createNotificationChannel() {
        mNotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID,
                "Stand up notification",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Notifies every 30 seconds to stand up and walk"
            mNotificationManager!!.createNotificationChannel(notificationChannel)
        }
    }

    private fun loadData() {
        Singleton.datasetMenuRestaurante.clear()
        val id_restaurante = intent.getIntExtra("id_restaurante", 0)

        val dbHelper = BDUsuarios(applicationContext)
        val db = dbHelper?.readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM Comida_restaurante WHERE id_restaurante = ?",
            arrayOf<String>("${id_restaurante}")
        )
        with(cursor) {
            while (moveToNext()) {
                Singleton.datasetMenuRestaurante.add(
                    MenuRestaurante(
                        cursor.getInt((getColumnIndex("id_restaurante"))),
                        cursor.getInt(getColumnIndex("id_producto")),
                        cursor.getString(getColumnIndex("descripcion_producto")),
                        cursor.getDouble(getColumnIndex("precio_producto"))
                    )
                )
            }
        }
        var restaurante: String = ""
        val cursor2 = db.rawQuery(
            "SELECT * FROM Restaurantes WHERE id_restaurante = ?",
            arrayOf<String>("${id_restaurante}")
        )
        with(cursor2) {
            while (moveToNext()) {
                restaurante = cursor2.getString(getColumnIndex("nombre_restaurante"))
            }
        }

        Snackbar.make(findViewById(R.id.layout_menu_restaurante), restaurante, Snackbar.LENGTH_LONG)
            .show()
    }

    fun PeticionWS(){
        var tipoCambio = 0.0
        val retrofit =  Retrofit.Builder()
            .baseUrl("https://tareatec.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        val TCWebService = retrofit.create(TipoCambio::class.java)
        val TipoCambioReal = TCWebService.getTCReal()

        var HiloTC = object:Thread() {
            override fun run() {
                try {
                    TipoCambioReal.enqueue(
                        object : Callback<String> {
                            override fun onFailure(call: Call<String>, t: Throwable) {
                                Snackbar.make(
                                    window.decorView.rootView,
                                    "Fallo la petición tipo cambio",
                                    Snackbar.LENGTH_LONG
                                )
                                    .setAction("Action", null).show()
                            }

                            override fun onResponse(
                                call: Call<String>,
                                response: Response<String>
                            ) {
                                var Valor:String = "${response.body()}"
                                tipoCambio = Valor.toDouble()
                                tvTipoCambio.text = "$1 USD = $tipoCambio"
                            }
                        })
                } catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
        HiloTC.start()
        HiloTC.join()
    }
}
