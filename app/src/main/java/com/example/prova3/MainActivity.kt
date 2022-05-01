package com.example.prova3

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var palina:Int = 968

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnCerca(v: View) {
        try {
            Log.d("aaa", "gino")
            Thread {
                val url = "https://gtt.to.it/cms/percorari/arrivi?palina=$palina"
                Scrap(Jsoup.connect(url).get())
            }.start()
            Log.d("aaa", "finish")
        }catch (e:Exception){
            Log.v("Thread btnCerca", "$e")
        }
    }

    fun Scrap(d:Document){
        var t = d.getElementsByClass("bg-warning")[0].getElementsByTag("td")
        var s: String = "";
        for(i in 1..t.size-1){
           s+=t[i].text()+"\n"
        }
        try {
            var a: TextView = findViewById(R.id.txb)
            a.setText(s)
        }
        catch (e:Exception){
            Log.w("Scrap","$e")
        }
    }
}