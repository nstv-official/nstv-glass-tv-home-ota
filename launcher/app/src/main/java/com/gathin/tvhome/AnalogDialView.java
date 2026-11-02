package com.gathin.tvhome;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

/**
 * Jam analog untuk gaya screensaver {@code ANALOG}. Sengaja digambar sendiri (bukan
 * {@link android.widget.AnalogClock}) supaya tipis, seirama dengan tipografi Inter, dan bisa
 * ikut berubah jadi monokrom merah pada mode malam.
 *
 * <p>Cincin luar menunjukkan kemajuan hari: busur tipis bergerak dari tengah malam ke tengah
 * malam berikutnya. Detik digambar sebagai jarum halus, dan view hanya menggambar ulang satu
 * kali per detik — penting karena TV rumah hanya punya 1 GB RAM.
 */
public class AnalogDialView extends View {

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final RectF ring = new RectF();
    private final Handler ticker = new Handler(Looper.getMainLooper());

    private int inkColor = 0xFFFFFFFF;
    private int accentColor = 0xFF38BDF8;
    private int veilColor = 0x24FFFFFF;
    private boolean showSeconds = true;
    private boolean nightMode = false;

    private final Runnable tick = new Runnable() {
        @Override
        public void run() {
            invalidate();
            // Dijajarkan ke detik berikutnya supaya jarum tidak melompat-lompat.
            long delay = 1000L - (System.currentTimeMillis() % 1000L);
            ticker.postDelayed(this, delay);
        }
    };

    public AnalogDialView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /** Warna jarum, angka, dan cincin. Mode malam memakai merah lembut seperti Wind Down iPhone. */
    void setInk(int ink, int accent, boolean night) {
        inkColor = ink;
        accentColor = accent;
        nightMode = night;
        veilColor = night ? 0x24FF453A : 0x24FFFFFF;
        invalidate();
    }

    void setShowSeconds(boolean value) {
        showSeconds = value;
        invalidate();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ticker.postDelayed(tick, 1000L - (System.currentTimeMillis() % 1000L));
    }

    @Override
    protected void onDetachedFromWindow() {
        ticker.removeCallbacks(tick);
        super.onDetachedFromWindow();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float w = getWidth(), h = getHeight();
        if (w <= 0 || h <= 0) return;
        float cx = w / 2f, cy = h / 2f;
        float radius = Math.min(w, h) / 2f;
        float density = getResources().getDisplayMetrics().density;

        float face = radius - 2f * density;

        // 1) Cincin luar: garis tipis penuh, lalu busur kemajuan hari dengan warna aksen.
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1.4f * density);
        paint.setColor(veilColor);
        canvas.drawCircle(cx, cy, face, paint);

        Calendar now = Calendar.getInstance();
        int minuteOfDay = now.get(Calendar.HOUR_OF_DAY) * 60 + now.get(Calendar.MINUTE);
        float daySweep = 360f * minuteOfDay / (24f * 60f);
        paint.setColor(accentColor);
        paint.setStrokeWidth(2.6f * density);
        paint.setStrokeCap(Paint.Cap.ROUND);
        float arc = face - 2.2f * density;
        ring.set(cx - arc, cy - arc, cx + arc, cy + arc);
        canvas.drawArc(ring, -90f, daySweep, false, paint);
        paint.setStrokeCap(Paint.Cap.BUTT);

        // 2) Tanda jam: lebih panjang dan tebal tiap kuartir (12, 3, 6, 9).
        for (int i = 0; i < 12; i++) {
            boolean quarter = (i % 3) == 0;
            paint.setColor(quarter ? inkColor : (nightMode ? 0x80FF453A : 0x66FFFFFF));
            paint.setStrokeWidth(quarter ? 3.2f * density : 1.6f * density);
            double a = Math.toRadians(i * 30f - 90f);
            float outer = face - 10f * density;
            float inner = outer - (quarter ? 16f * density : 9f * density);
            canvas.drawLine(cx + (float) Math.cos(a) * outer, cy + (float) Math.sin(a) * outer,
                    cx + (float) Math.cos(a) * inner, cy + (float) Math.sin(a) * inner, paint);
        }

        float hourLen = face * 0.46f;
        float minuteLen = face * 0.72f;
        float secondLen = face * 0.80f;

        // 3) Jarum jam dan menit, ujungnya bulat supaya terlihat lembut di jarak duduk.
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(inkColor);

        float hourAngle = (now.get(Calendar.HOUR_OF_DAY) % 12 + now.get(Calendar.MINUTE) / 60f) * 30f - 90f;
        paint.setStrokeWidth(6.4f * density);
        drawHand(canvas, cx, cy, hourAngle, hourLen);

        float minuteAngle = now.get(Calendar.MINUTE) * 6f + now.get(Calendar.SECOND) / 10f - 90f;
        paint.setStrokeWidth(3.6f * density);
        drawHand(canvas, cx, cy, minuteAngle, minuteLen);

        // 4) Jarum detik halus dengan warna aksen.
        if (showSeconds) {
            float secondAngle = now.get(Calendar.SECOND) * 6f - 90f;
            paint.setColor(accentColor);
            paint.setStrokeWidth(1.6f * density);
            drawHand(canvas, cx, cy, secondAngle, secondLen);
        }

        // 5) Poros: cincin kecil agar jarum tidak terlihat "tempel".
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(nightMode ? 0xFFFF453A : 0xFFFFFFFF);
        canvas.drawCircle(cx, cy, 4.2f * density, paint);
        paint.setColor(0xFF000000);
        canvas.drawCircle(cx, cy, 1.8f * density, paint);
        paint.setStyle(Paint.Style.STROKE);
    }

    private void drawHand(Canvas canvas, float cx, float cy, float degrees, float length) {
        double a = Math.toRadians(degrees);
        canvas.drawLine(cx, cy, cx + (float) Math.cos(a) * length, cy + (float) Math.sin(a) * length, paint);
    }
}
