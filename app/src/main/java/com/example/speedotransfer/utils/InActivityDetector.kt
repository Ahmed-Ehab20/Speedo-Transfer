package com.example.speedotransfer.utils

import android.os.Handler
import android.os.Looper

class InactivityDetector(
    private val onTimeout: () -> Unit
) {
    private val handler = Handler(Looper.getMainLooper())
    private val inactivityDelay = 2 * 60 * 1000L // 2 minutes

    private val inactivityRunnable = Runnable {
        onTimeout()
    }

    fun reset() {
        handler.removeCallbacks(inactivityRunnable)
        handler.postDelayed(inactivityRunnable, inactivityDelay)
    }

    fun cancel() {
        handler.removeCallbacks(inactivityRunnable)
    }
}
