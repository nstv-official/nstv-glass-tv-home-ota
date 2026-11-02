package com.gathin.tvhome;

import android.os.Handler;
import android.os.Looper;
import android.service.dreams.DreamService;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.Random;

/**
 * Screensaver sistem (DreamService) yang dipilih Android saat TV menganggur. Seluruh penyusunan
 * layar diserahkan ke {@link Nightstand} supaya dream dan hamparan Home menampilkan hal yang
 * sama, termasuk gaya, latar, keredupan, dan mode malam yang diatur pengguna.
 *
 * <p>Dismiss pada tombol remote apa pun; setelah jeda tidur yang dipilih, layar dimatikan.
 */
public class ClockDream extends DreamService {

    private static final String TAG = "TvHome.ClockDream";

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Random random = new Random();
    private View root;
    private View content;
    private long dreamStartTime = 0;

    private final Runnable tick = new Runnable() {
        @Override
        public void run() {
            long sleepMs = ScreensaverSettings.sleepMinutes(ClockDream.this) * 60_000L;
            if (dreamStartTime > 0
                    && android.os.SystemClock.elapsedRealtime() - dreamStartTime >= sleepMs) {
                Log.i(TAG, "Screensaver mencapai jeda tidur: mematikan layar TV");
                HomeKeyService.sleepScreen();
                try {
                    Runtime.getRuntime().exec("input keyevent 223");
                } catch (Exception ignored) {
                }
                finish();
                return;
            }
            Nightstand.bind(root, ClockDream.this);
            moveClock();
            int drift = ScreensaverSettings.driftSeconds(ClockDream.this);
            handler.postDelayed(this, Math.max(15, drift <= 0 ? 60 : drift) * 1000L);
        }
    };

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setInteractive(false);   // Tombol apa pun membangunkan TV ke tempat semula
        setFullscreen(true);
        setScreenBright(false);
        root = Nightstand.inflate(this, LayoutInflater.from(this), null);
        setContentView(root);
        if (root != null) root.setOnClickListener(v -> finish());
        content = root == null ? null : root.findViewById(R.id.nightstandContent);

        Nightstand.bind(root, this);
    }

    @Override
    public void onDreamingStarted() {
        super.onDreamingStarted();
        dreamStartTime = android.os.SystemClock.elapsedRealtime();
        if (content != null) {
            content.setAlpha(0f);
            content.post(() -> {
                Nightstand.bind(root, ClockDream.this);
                Nightstand.drift(root, random);
                content.animate().alpha(1f).setDuration(1000).start();
            });
        }
        int drift = ScreensaverSettings.driftSeconds(this);
        handler.postDelayed(tick, Math.max(15, drift <= 0 ? 60 : drift) * 1000L);
    }

    @Override
    public void onDreamingStopped() {
        dreamStartTime = 0;
        handler.removeCallbacks(tick);
        super.onDreamingStopped();
    }

    @Override
    public void onDetachedFromWindow() {
        dreamStartTime = 0;
        handler.removeCallbacks(tick);
        super.onDetachedFromWindow();
    }

    /** Pudar sebentar, geser, lalu pudar kembali; dilewati bila pengguna memilih posisi diam. */
    private void moveClock() {
        if (content == null || ScreensaverSettings.driftSeconds(this) <= 0) return;
        content.animate().alpha(0f).setDuration(600).withEndAction(() -> {
            Nightstand.drift(root, random);
            content.animate().alpha(1f).setDuration(800).start();
        }).start();
    }
}
