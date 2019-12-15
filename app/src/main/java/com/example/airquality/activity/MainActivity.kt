package com.example.airquality.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.airquality.R
import com.example.airquality.model.City
import com.example.airquality.model.TestStand
import com.example.airquality.retrofit.AirQualityApiService
import com.example.todo.AdapterOnClick
import com.example.todo.RecyclerViewAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), AdapterOnClick {

  private lateinit var recyclerView: RecyclerView
  private lateinit var viewAdapter: RecyclerView.Adapter<*>
  private lateinit var viewManager: RecyclerView.LayoutManager


  private var dataList = ArrayList<TestStand>()
  private var cityList = ArrayList<City>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)

    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show()
    }



    viewManager = LinearLayoutManager(this)
    viewAdapter = RecyclerViewAdapter(this.cityList, this)

    recyclerView = findViewById<RecyclerView>(R.id.list).apply {
      // use this setting to improve performance if you know that changes
      // in content do not change the layout size of the RecyclerView
      setHasFixedSize(true)

      // use a linear layout manager
      layoutManager = viewManager

      // specify an viewAdapter (see also next example)
      adapter = viewAdapter

    }

    getCurrentData()


  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    return when (item.itemId) {
      R.id.action_settings -> true
      else -> super.onOptionsItemSelected(item)
    }
  }

  private fun getCurrentData() {
    val retrofit = Retrofit.Builder()
      .baseUrl(BaseUrl)
      .addConverterFactory(GsonConverterFactory.create())
      .build()

    val service = retrofit.create(AirQualityApiService::class.java)
    val call = service.findAll()

    call.enqueue(object : Callback<List<TestStand>> {

      override fun onResponse(call: Call<List<TestStand>>?, response: Response<List<TestStand>>?) {
        /*progerssProgressDialog.dismiss()
        dataList.addAll(response!!.body()!!)
        recyclerView.adapter.notifyDataSetChanged()*/
        println(1)

        dataList.addAll(response!!.body()!!)

        for (testStand: TestStand in dataList) {
          cityList.add(testStand.city)
        }

        recyclerView.adapter!!.notifyDataSetChanged()

      }

      override fun onFailure(call: Call<List<TestStand>>?, t: Throwable) {
        /* progerssProgressDialog.dismiss()*/
        //tv!!.text = t.message
      }

    })

  }

  override fun onClick(item: TextView) {
    /*val intent = Intent(this, MainActivity::class.java)
    intent.putExtra("id", item.text.toString())
    startActivity(intent)*/
  }

  companion object {
    var BaseUrl = "http://api.gios.gov.pl/pjp-api/rest/station/"
  }

}
