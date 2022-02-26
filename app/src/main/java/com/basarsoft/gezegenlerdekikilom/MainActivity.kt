package com.basarsoft.gezegenlerdekikilom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.CheckBox
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

val KILO_TO_POUND = 2.2045
val Mars = 0.38
val JUPITER = 2.34
val Venus = 0.91
val POUND_TO_KILO = 0.45359237


class MainActivity : AppCompatActivity() ,View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Glide.with(this).load(R.drawable.uzay).into(imageView)

        cbMars.setOnClickListener(this)
        cbJupiter.setOnClickListener(this)
        cbVenüs.setOnClickListener(this)




/* Buton ile hesaplama işlemleri bu sekılde yapılmaktadır

        btnHesapla.setOnClickListener {
            var kullaniciAgirlikPound = kiloToPound(kullaniciKilo.toString().toDouble())
            var marstakiAgirlikPound = kullaniciAgirlikPound * Mars
            var marstakiAgirlikKilo = poundToKilo(marstakiAgirlikPound)

            tvSonuc.text = marstakiAgirlikKilo.formatla(2).toString()

        }

 */



    }
// kiloyu pounda çevirme fonksiyonu
    fun kiloToPound (kilo : Double) : Double {
        return kilo * KILO_TO_POUND
    }
// pound olarak verilen değeri kiloya çevirme fonksiyonu
    fun poundToKilo (pound : Double) : Double{

        return pound * POUND_TO_KILO
    }
// formatla fonksiyonu ile ekranda görülen değer ',' den sonra kac rakam ise onu belirler
    fun Double.formatla(kacTaneRakam:Int) = java.lang.String.format("%.${kacTaneRakam}f",this)


// buradaki onClick metodu ile checkboxlara basıldıgında hesaplama işlemleri yapılır
    override fun onClick(v: View?) {

        v as CheckBox
        var isChecked :Boolean = v.isChecked


        if (!TextUtils.isEmpty(etKilo.text.toString())){
            var kullaniciKilo = etKilo.text.toString().toDouble()
            var kullaniciPound = kiloToPound(kullaniciKilo)

            when (v.id) {

                R.id.cbMars -> if (isChecked ){
                    cbVenüs.isChecked = false
                    cbJupiter.isChecked = false
                    hesaplaAgirlikPound(kullaniciPound, v)
                }

                R.id.cbVenüs -> if (isChecked ){
                    cbMars.isChecked = false
                    cbJupiter.isChecked = false
                    hesaplaAgirlikPound(kullaniciPound, v)

                }

                R.id.cbJupiter -> if (isChecked ){
                    cbMars.isChecked = false
                    cbVenüs.isChecked= false
                    hesaplaAgirlikPound(kullaniciPound, v)
                }
            }
        }

    }

// bu fonksiyon ile agirligi pound olarak hesaplayabiliriz
    fun hesaplaAgirlikPound(pound: Double , checkBox: CheckBox){

        var sonuc : Double = 0.0

        when(checkBox.id){

            R.id.cbMars -> sonuc = pound * Mars
            R.id.cbVenüs -> sonuc = pound * Venus
            R.id.cbJupiter -> sonuc = pound * JUPITER

            else -> sonuc = 0.0
        }

        var sonucToKilo = poundToKilo(sonuc)
        tvSonuc.text = sonucToKilo.formatla(2)

    }
}