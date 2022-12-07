package com.example.nontonime.activity

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.example.nontonime.databinding.ActivityWatchBinding

class WatchActivity : AppCompatActivity() {

    private var _binding: ActivityWatchBinding? = null
    private val binding get() = _binding as ActivityWatchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWatchBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val mediaController = MediaController(this)
//        mediaController.setAnchorView(binding.videoView)
//
//        val uri = Uri.parse("https://joy.videvo.net/videvo_files/video/free/2021-04/large_watermarked/210329_06B_Bali_1080p_013_preview.mp4")
//
//        binding.videoView.apply {
//            setMediaController(mediaController)
//            setVideoURI(uri)
//            requestFocus()
//            start()
//        }

        binding.webView.loadUrl("")

    }

    companion object {
        const val WATCH_DATA = "DATA"
    }
}