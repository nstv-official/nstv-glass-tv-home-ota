package com.gathin.tvhome;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Apple Weather Regional Precipitation Radar Map Card View.
 * Displays local geography, radar cloud reflectivity heatmaps, and location temperature badge.
 */
public class RadarMapView extends View {

    private final Paint bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint landPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint radarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint badgePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint badgeStroke = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int temp = 29;
    private String locationName = "Depok";

    public RadarMapView(Context context) {
        this(context, null);
    }

    public RadarMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setColor(0xFF131D2A);

        landPaint.setStyle(Paint.Style.FILL);
        landPaint.setColor(0xFF1C2B3C);

        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(0xCCFFFFFF);

        badgePaint.setStyle(Paint.Style.FILL);
        badgePaint.setColor(0xFF1E3A8A);

        badgeStroke.setStyle(Paint.Style.STROKE);
        badgeStroke.setStrokeWidth(1.5f);
        badgeStroke.setColor(0xFFFFFFFF);
    }

    public void setDetails(int temp, String loc) {
        this.temp = temp;
        this.locationName = loc;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int w = getWidth();
        int h = getHeight();
        if (w <= 0 || h <= 0) return;

        float density = getResources().getDisplayMetrics().density;

        // Clip to rounded corners
        Path clipPath = new Path();
        clipPath.addRoundRect(new RectF(0, 0, w, h), 14f * density, 14f * density, Path.Direction.CW);
        canvas.clipPath(clipPath);

        // 1. Sea / Ocean background (Laut Jawa / utara) — peta radar wilayah Jabodetabek
        canvas.drawRect(0, 0, w, h, bgPaint);

        // 2. Landmass Polygon (Pulau Jawa sisi barat: Banten, Jakarta, Jawa Barat)
        Path land = new Path();
        land.moveTo(w * 0.18f, 0);
        land.lineTo(w, 0);
        land.lineTo(w, h);
        land.lineTo(w * 0.52f, h);
        land.lineTo(w * 0.40f, h * 0.88f); // Cianjur / selatan Bogor
        land.lineTo(w * 0.30f, h * 0.70f); // Sukabumi sisi selatan
        land.lineTo(w * 0.22f, h * 0.52f); // Serang / pesisir Banten selatan
        land.lineTo(w * 0.12f, h * 0.30f); // Ujung Kulon arah barat
        land.lineTo(w * 0.06f, h * 0.10f); // Pesisir barat Banten
        land.close();
        canvas.drawPath(land, landPaint);

        // 3. Radar Precipitation Heatmap Overlays (Apple Weather Doppler return)
        // Storm cell 1 (Violet / Magenta core)
        RadialGradient cell1 = new RadialGradient(
                w * 0.38f, h * 0.45f, w * 0.32f,
                new int[]{0xDDDB2777, 0xAA7C3AED, 0x662563EB, 0x001E3A8A},
                new float[]{0f, 0.35f, 0.70f, 1f},
                Shader.TileMode.CLAMP
        );
        radarPaint.setShader(cell1);
        canvas.drawCircle(w * 0.38f, h * 0.45f, w * 0.32f, radarPaint);

        // Storm cell 2 (Amber / Orange / Crimson precipitation band)
        RadialGradient cell2 = new RadialGradient(
                w * 0.52f, h * 0.68f, w * 0.26f,
                new int[]{0xCCE11D48, 0x99F59E0B, 0x4410B981, 0x000D9488},
                new float[]{0f, 0.40f, 0.75f, 1f},
                Shader.TileMode.CLAMP
        );
        radarPaint.setShader(cell2);
        canvas.drawCircle(w * 0.52f, h * 0.68f, w * 0.26f, radarPaint);

        // 4. Map Geography Labels (Carefully positioned to avoid overlap)
        textPaint.setTextAlign(Paint.Align.LEFT);

        // Jakarta with city marker dot
        textPaint.setTextSize(9f * density);
        textPaint.setColor(0xFFFFFFFF);
        textPaint.setFakeBoldText(true);
        canvas.drawCircle(w * 0.42f, h * 0.22f, 2f * density, textPaint);
        canvas.drawText("Jakarta", w * 0.45f, h * 0.24f, textPaint);

        // Region & Town Labels (Jabodetabek & sekitarnya)
        textPaint.setTextSize(7f * density);
        textPaint.setColor(0xA0FFFFFF);
        textPaint.setFakeBoldText(false);
        canvas.drawText("Tangerang", w * 0.26f, h * 0.30f, textPaint);
        canvas.drawText("Bekasi", w * 0.62f, h * 0.18f, textPaint);
        canvas.drawText("JAWA BARAT", w * 0.68f, h * 0.50f, textPaint);
        canvas.drawText("Bogor", w * 0.50f, h * 0.62f, textPaint);
        canvas.drawText("Depok", w * 0.56f, h * 0.42f, textPaint);
        canvas.drawText("Serang", w * 0.18f, h * 0.12f, textPaint);
        canvas.drawText("Sukabumi", w * 0.30f, h * 0.78f, textPaint);

        // Sea label in top right corner (Laut Jawa)
        textPaint.setTextSize(6.5f * density);
        textPaint.setColor(0x70FFFFFF);
        canvas.drawText("LAUT JAWA", w * 0.74f, h * 0.06f, textPaint);

        // 5. My Location Badge (Circular disc: temp e.g. 29 + 'My Location')
        float badgeX = w * 0.38f;
        float badgeY = h * 0.58f;
        float bRadius = 9f * density;

        canvas.drawCircle(badgeX, badgeY, bRadius, badgePaint);
        canvas.drawCircle(badgeX, badgeY, bRadius, badgeStroke);

        textPaint.setColor(0xFFFFFFFF);
        textPaint.setTextSize(7.5f * density);
        textPaint.setFakeBoldText(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(String.valueOf(temp), badgeX, badgeY + (2.5f * density), textPaint);

        textPaint.setTextSize(7f * density);
        textPaint.setFakeBoldText(false);
        textPaint.setColor(0xEEFFFFFF);
        canvas.drawText("Lokasi Saya", badgeX, badgeY + (11.5f * density), textPaint);
        textPaint.setTextAlign(Paint.Align.LEFT);
    }
}
