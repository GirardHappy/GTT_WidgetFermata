package com.example.prova3

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    //Metro: 8211 | Mia: 968
    var palina:Int = 675886

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnCerca(v: View) {
        val t: EditText = findViewById(R.id.txtNum)
        try {
            palina = t.text.toString().toInt()
        }catch(e:Exception){return;}

        Thread {
            try {
                Scrap(Jsoup.connect("https://gtt.to.it/cms/percorari/arrivi?palina=$palina").get())
            }catch(e:java.net.UnknownHostException){
                T("Connessione a Internet Assente")
            }
            catch(e:Exception){
                Log.w("Thread btnCerca", "$e")
            }
        }.start()
    }

    fun Scrap(d:Document){
        var s: String = "";
        val t = d.getElementsByTag("tr")
            if(t.size>0){
                for(i in 1..t.size-1) {
                    for (td in t[i].getElementsByTag("td")){
                        s += td.text() + " "
                    }
                    s += "\n"
                }
            }
            else {
                s = "Fermata Inesistente"
            }
        var a: TextView = findViewById(R.id.txb)
        if(s.replace("\n","").replace(" ","")=="")
            s="Nessun passaggio previsto"
            T(s)
    }

    fun T(s:String){
        var a: TextView = findViewById(R.id.txb)
        a.setText(s)
    }
}