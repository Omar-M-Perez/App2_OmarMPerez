package com.example.pizza_create

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_settings.*

class settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val pref = "App_pre"
        val sp = getSharedPreferences(pref, 0)

        val musickey = "music"
        var music = sp.getBoolean(musickey, true)

        var mp = MediaPlayer.create(this, R.raw.background_loop_app_2)
        if (music)
        {
            mp.isLooping = true
            mp.start()
        }

        switch_music.isChecked = music
        switch_music.setOnClickListener {
            music = sp.getBoolean(musickey, true)
            if (music)
            {
                music = false
                mp.stop()
            }
            else
            {
                mp.isLooping = true
                mp.stop()
                mp.start()
                music = true
            }

            val editor = getSharedPreferences(pref, 0).edit()
            editor.putBoolean(musickey, music)
            editor.commit()
        }

        back_button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            mp.stop()
        }
    }
}