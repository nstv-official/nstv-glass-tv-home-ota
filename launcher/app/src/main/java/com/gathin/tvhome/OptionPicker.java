package com.gathin.tvhome;

import android.app.AlertDialog;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

/**
 * Daftar pilihan bergaya kaca yang dipakai dialog pengaturan screensaver. Bentuknya sengaja
 * sama dengan pemilih interval wallpaper ( {@code MainActivity.showWallpaperIntervalDialog} ):
 * baris fokus jadi putih, baris aktif diberi garis biru, dan satu baris otomatis di-focus supaya
 * remote TV langsung bisa dipakai.
 */
final class OptionPicker {

    /** Satu baris pilihan. {@code key} yang dikembalikan ke listener, label hanya untuk tampilan. */
    static final class Option {
        final String key;
        final String title;
        final String subtitle;

        Option(String key, String title, String subtitle) {
            this.key = key;
            this.title = title;
            this.subtitle = subtitle;
        }
    }

    interface OnPick {
        void onPick(Option option, int index);
    }

    private OptionPicker() {}

    static void show(final MainActivity activity, String title, String subtitle,
                     List<Option> options, String currentKey, final OnPick onPick) {
        SoundEffects.playSelect();
        final float density = activity.getResources().getDisplayMetrics().density;
        Typeface font = activity.getResources().getFont(R.font.inter);

        LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding((int) (20 * density), (int) (16 * density), (int) (20 * density), (int) (12 * density));

        TextView titleView = new TextView(activity);
        titleView.setText(title);
        titleView.setTextSize(19);
        titleView.setTextColor(0xFFFFFFFF);
        titleView.setTypeface(font, Typeface.BOLD);
        titleView.setFontVariationSettings("'wght' 700");
        titleView.setShadowLayer(4 * density, 0, 1 * density, 0xDD000000);
        layout.addView(titleView);

        if (subtitle != null && !subtitle.isEmpty()) {
            TextView subView = new TextView(activity);
            subView.setText(subtitle);
            subView.setTextSize(13.5f);
            subView.setTextColor(0xE6FFFFFF);
            subView.setTypeface(font, Typeface.BOLD);
            subView.setFontVariationSettings("'wght' 600");
            subView.setShadowLayer(3 * density, 0, 1 * density, 0xCC000000);
            subView.setPadding(0, (int) (3 * density), 0, (int) (12 * density));
            layout.addView(subView);
        }

        ScrollView scroll = new ScrollView(activity);
        scroll.setVerticalScrollBarEnabled(false);
        LinearLayout list = new LinearLayout(activity);
        list.setOrientation(LinearLayout.VERTICAL);

        final AlertDialog[] holder = new AlertDialog[1];
        View[] toFocus = new View[1];

        for (int i = 0; i < options.size(); i++) {
            final Option opt = options.get(i);
            final int index = i;
            boolean isActive = opt.key.equals(currentKey);

            LinearLayout row = new LinearLayout(activity);
            row.setOrientation(LinearLayout.VERTICAL);
            row.setFocusable(true);
            row.setClickable(true);
            row.setPadding((int) (16 * density), (int) (9 * density), (int) (16 * density), (int) (9 * density));

            StateListDrawable bg = new StateListDrawable();
            GradientDrawable on = new GradientDrawable();
            on.setColor(0xFFFFFFFF);
            on.setCornerRadius(10 * density);
            GradientDrawable off = new GradientDrawable();
            off.setColor(isActive ? 0x334A90E2 : 0x1AFFFFFF);
            if (isActive) off.setStroke((int) (1.8f * density), 0xFF38BDF8);
            off.setCornerRadius(10 * density);
            bg.addState(new int[]{android.R.attr.state_focused}, on);
            bg.addState(new int[]{}, off);
            row.setBackground(bg);

            LinearLayout topRow = new LinearLayout(activity);
            topRow.setOrientation(LinearLayout.HORIZONTAL);
            topRow.setGravity(Gravity.CENTER_VERTICAL);

            TextView rowTitle = new TextView(activity);
            rowTitle.setText(opt.title);
            rowTitle.setTextSize(16);
            rowTitle.setTextColor(0xFFFFFFFF);
            rowTitle.setTypeface(font, Typeface.BOLD);
            rowTitle.setFontVariationSettings("'wght' 700");
            rowTitle.setShadowLayer(3 * density, 0, 1 * density, 0xCC000000);
            topRow.addView(rowTitle, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

            TextView activeTag = null;
            if (isActive) {
                activeTag = new TextView(activity);
                activeTag.setText("✓ Saat Ini");
                activeTag.setTextSize(13);
                activeTag.setTextColor(0xFF38BDF8);
                activeTag.setTypeface(font, Typeface.BOLD);
                activeTag.setFontVariationSettings("'wght' 700");
                topRow.addView(activeTag);
            }
            row.addView(topRow);

            TextView rowSub = new TextView(activity);
            rowSub.setText(opt.subtitle);
            rowSub.setTextSize(13);
            rowSub.setTextColor(0xD9FFFFFF);
            rowSub.setTypeface(font, Typeface.BOLD);
            rowSub.setFontVariationSettings("'wght' 600");
            rowSub.setShadowLayer(2 * density, 0, 1 * density, 0xAA000000);
            rowSub.setPadding(0, (int) (2 * density), 0, 0);
            row.addView(rowSub);

            final TextView fTitle = rowTitle, fSub = rowSub, fTag = activeTag;
            row.setOnFocusChangeListener((v, hasFocus) -> {
                if (hasFocus) {
                    fTitle.setTextColor(0xFF0F172A);
                    fTitle.setShadowLayer(0, 0, 0, 0);
                    fSub.setTextColor(0xFF334155);
                    fSub.setShadowLayer(0, 0, 0, 0);
                    if (fTag != null) {
                        fTag.setTextColor(0xFF0284C7);
                        fTag.setShadowLayer(0, 0, 0, 0);
                    }
                } else {
                    fTitle.setTextColor(0xFFFFFFFF);
                    fTitle.setShadowLayer(3 * density, 0, 1 * density, 0xCC000000);
                    fSub.setTextColor(0xD9FFFFFF);
                    fSub.setShadowLayer(2 * density, 0, 1 * density, 0xAA000000);
                    if (fTag != null) {
                        fTag.setTextColor(0xFF38BDF8);
                        fTag.setShadowLayer(2 * density, 0, 1 * density, 0xAA000000);
                    }
                }
            });

            row.setOnClickListener(v -> {
                if (holder[0] != null) holder[0].dismiss();
                SoundEffects.playSelect();
                onPick.onPick(opt, index);
            });

            LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            rlp.bottomMargin = (int) (5 * density);
            list.addView(row, rlp);

            if (isActive && toFocus[0] == null) {
                toFocus[0] = row;
                rowTitle.setTextColor(0xFF0F172A);
                rowTitle.setShadowLayer(0, 0, 0, 0);
                rowSub.setTextColor(0xFF334155);
                rowSub.setShadowLayer(0, 0, 0, 0);
                if (activeTag != null) {
                    activeTag.setTextColor(0xFF0284C7);
                    activeTag.setShadowLayer(0, 0, 0, 0);
                }
            }
        }

        scroll.addView(list);
        layout.addView(scroll, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f));

        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.Theme_TvHome_GlassDialog);
        builder.setView(layout);
        builder.setNegativeButton("Kembali", (d, w) -> {
            d.dismiss();
            SoundEffects.playNav();
        });
        AlertDialog dialog = builder.create();
        holder[0] = dialog;
        dialog.show();
        activity.styleGlassDialog(dialog);

        if (dialog.getWindow() != null) {
            int w = Math.min((int) (520 * density), activity.getResources().getDisplayMetrics().widthPixels - (int) (120 * density));
            int h = Math.min((int) (470 * density), (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.85f));
            dialog.getWindow().setLayout(w, h);
        }

        if (toFocus[0] != null) {
            toFocus[0].requestFocus();
        } else if (list.getChildCount() > 0) {
            list.getChildAt(0).requestFocus();
        }
    }
}
