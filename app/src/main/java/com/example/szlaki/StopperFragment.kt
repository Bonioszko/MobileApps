package com.example.szlaki
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Button


class StopperFragment : Fragment() {
    private var trail: Trail? = null
    private var handler = Handler(Looper.getMainLooper())
    private lateinit var timeTextView: TextView

    private val runnable = object : Runnable {
        override fun run() {
            if (trail?.isRunning == true) {
                trail?.seconds = (trail?.seconds ?: 0) + 1
                if (trail?.seconds == 60){
                    trail?.seconds = 0
                    trail?.minutes = (trail?.minutes ?: 0) + 1
                }
                if (trail?.minutes == 60){
                    trail?.minutes = 0
                    trail?.hours = (trail?.hours ?: 0) + 1
                }

                timeTextView.text =  String.format("%02d:%02d:%02d", trail?.hours, trail?.minutes, trail?.seconds)
            }
            handler.postDelayed(this, 1000)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stopper, container, false)
        timeTextView = view.findViewById(R.id.timer)
        val startButton = view.findViewById<Button>(R.id.start_button)
        val stopButton = view.findViewById<Button>(R.id.stop_button)
        val resetButton = view.findViewById<Button>(R.id.reset_button)
        timeTextView = view.findViewById(R.id.timer)
        timeTextView.text = String.format("%02d:%02d:%02d", trail?.hours, trail?.minutes, trail?.seconds)
        startButton.setOnClickListener {
            trail?.isRunning = true
        }
        stopButton.setOnClickListener {
            trail?.isRunning = false
        }
        resetButton.setOnClickListener {
            trail?.times?.add(String.format("%02d:%02d:%02d", trail?.hours, trail?.minutes, trail?.seconds))
            trail?.seconds = 0
            trail?.minutes = 0
            trail?.hours = 0
            timeTextView.text = String.format("%02d:%02d:%02d", trail?.hours, trail?.minutes, trail?.seconds)
            trail?.isRunning = false
        }

        handler.post(runnable)
        return view
    }

    fun setTrail(trail: Trail?) {
        this.trail = trail
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(runnable)
    }}