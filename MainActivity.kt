package com.example.timeshifttoggle

import android.os.Bundle
import android.text.format.DateFormat
import androidx.appcompat.app.AppCompatActivity
import com.example.timeshifttoggle.databinding.ActivityMainBinding
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var toggled = false // false = normal, true = +5h

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateUI()

        binding.btnToggle.setOnClickListener {
            toggled = !toggled
            updateUI()
        }
    }

    private fun updateUI() {
        val zone = ZoneId.systemDefault()
        val now = ZonedDateTime.ofInstant(Instant.now(), zone)
        val shown = if (toggled) now.plusHours(5) else now
        val use24 = DateFormat.is24HourFormat(this)
        val pattern = if (use24) "dd MMM yyyy, HH:mm:ss (zzz)" else "dd MMM yyyy, hh:mm:ss a (zzz)"
        val fmt = DateTimeFormatter.ofPattern(pattern)
        binding.txtTime.text = shown.format(fmt)
        binding.btnToggle.text = if (toggled) "Normale Dön" else "5 Saat İleri Al"
    }
}
