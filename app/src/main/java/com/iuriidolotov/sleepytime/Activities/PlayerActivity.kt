package com.iuriidolotov.sleepytime.Activities

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.iuriidolotov.sleepytime.R
import com.iuriidolotov.sleepytime.Fragments.audioUrlPass
import kotlinx.android.synthetic.main.player_layout.*
import kotlinx.android.synthetic.main.toolbar.*


class PlayerActivity: AppCompatActivity() {

    private val handler = Handler()
    private val url = audioUrlPass

    private val mediaPlayer: MediaPlayer? = MediaPlayer().apply {
        setAudioStreamType(AudioManager.STREAM_MUSIC)
        setDataSource(url)
        prepare()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player_layout)

        val bundle: Bundle? = intent.extras
        val titleText: String? = bundle!!.getString("Title")

        titleProgramm.text = titleText
//        timerText.isVisible = false

        menuButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            mediaPlayer!!.stop()
        }

        playerSetUp()
        timerUpdate()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        mediaPlayer!!.stop()
    }

    private fun timerUpdate() {
        handler.post(object : Runnable {
            override fun run() {
                val timeLeft = (mediaPlayer!!.duration - mediaPlayer.currentPosition)
                handler.postDelayed(this, 1000)
                timerText.text = "${(timeLeft % ((1000 * 60 * 60)) / (1000 * 60))}:${(timeLeft % (1000 * 60 * 60) % (1000 * 60) / 1000)}"

                if (timeLeft == 0) {
                }
            }
        })
    }

    private fun playerSetUp() {
        Log.d("!---AUDIO DURATION---!", mediaPlayer!!.duration.toString())

        playButton.setOnClickListener {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop()
                mediaPlayer.prepare()
                playButton.setImageResource(R.mipmap.playbutton)
            } else {
                mediaPlayer.start()
                playButton.setImageResource(R.mipmap.pausebutton)
            }
        }

        mediaPlayer.setVolume(0.5f,0.5f)

        playerSeekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{

                override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser){
                        val volumeNum = progress / 100.0f
                        mediaPlayer.setVolume(volumeNum, volumeNum)
                    }
                }
                override fun onStartTrackingTouch(seekbar: SeekBar?) { }
                override fun onStopTrackingTouch(seekbar: SeekBar?) { }
            })
    }
}