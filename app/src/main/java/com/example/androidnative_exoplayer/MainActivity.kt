package com.example.androidnative_exoplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidnative_exoplayer.databinding.ActivityMainBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSource

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var simpleExoPlayer: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        initPlayer()
    }

    override fun onStop() {
        super.onStop()
        simpleExoPlayer.release()
    }

    private fun initPlayer() {
        val mediaDataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(this)
        val mediaSource = ProgressiveMediaSource.Factory(mediaDataSourceFactory)
            .createMediaSource(MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"))
        val mediaSourceFactory = DefaultMediaSourceFactory(mediaDataSourceFactory)
        simpleExoPlayer = ExoPlayer.Builder(this).setMediaSourceFactory(mediaSourceFactory).build()
        simpleExoPlayer.addMediaSource(mediaSource)
        simpleExoPlayer.playWhenReady = true
        binding.playerView.player = simpleExoPlayer
        binding.playerView.requestFocus()
    }

}