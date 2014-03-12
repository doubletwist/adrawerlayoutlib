/*
* Copyright (C) 2014 doubleTwist Corporation.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.doubleTwist.drawerlib.exampleapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.doubleTwist.drawerlib.ADrawerLayout;

/**
 * Created by fabrantes on 2/24/14.
 */
public class ExampleFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private static final String ARG_EXAMPLE_ID = "ArgExampleId";

    public static ExampleFragment newInstance(int layoutId) {
        ExampleFragment f = new ExampleFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_EXAMPLE_ID, layoutId);
        f.setArguments(b);
        return f;
    }

    public ExampleFragment() {}

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        int exampleId = getArguments().getInt(ARG_EXAMPLE_ID);
        int layoutId = getLayoutForExample(exampleId);

        View v = inflater.inflate(layoutId, null);
        setupExample(v, exampleId);

        return v;
    }

    private int getLayoutForExample(int exampleId) {
            switch(exampleId) {
                case R.id.example1: return R.layout.fragment_example;
                case R.id.example2: return R.layout.fragment_example_2;
                default: return -1;
            }
    }

    ADrawerLayout mDrawerLayout;

    private void setupExample(View v, int exampleId) {
        int contentPadding = getResources().getDimensionPixelSize(R.dimen.content_padding);
        mDrawerLayout = (ADrawerLayout) v.findViewById(R.id.drawer);
        mDrawerLayout.setRestrictTouchesToArea(ADrawerLayout.LEFT_DRAWER, contentPadding);
        mDrawerLayout.setRestrictTouchesToArea(ADrawerLayout.RIGHT_DRAWER, contentPadding);
        mDrawerLayout.setRestrictTouchesToArea(ADrawerLayout.TOP_DRAWER, contentPadding);
        mDrawerLayout.setRestrictTouchesToArea(ADrawerLayout.BOTTOM_DRAWER, contentPadding);

        setupViews(v);

        readDrawerValues(mDrawerLayout, v);
    }

    private void setupViews(View v) {
        TextView label;
        View view;

        label = (TextView) v.findViewById(R.id.center_settings_label);
        label.setText("Content settings");
        view = v.findViewById(R.id.center_settings);
        setupSettingsPanel(ADrawerLayout.NO_DRAWER, view);

        label = (TextView) v.findViewById(R.id.left_settings_label);
        label.setText("Left drawer animation params");
        view = v.findViewById(R.id.left_settings);
        setupSettingsPanel(ADrawerLayout.LEFT_DRAWER, view);

        label = (TextView) v.findViewById(R.id.right_settings_label);
        label.setText("Right drawer animation params");
        view = v.findViewById(R.id.right_settings);
        setupSettingsPanel(ADrawerLayout.RIGHT_DRAWER, view);

        label = (TextView) v.findViewById(R.id.top_settings_label);
        label.setText("Top drawer animation params");
        view = v.findViewById(R.id.top_settings);
        setupSettingsPanel(ADrawerLayout.TOP_DRAWER, view);

        label = (TextView) v.findViewById(R.id.bottom_settings_label);
        label.setText("Bottom drawer animation params");
        view = v.findViewById(R.id.bottom_settings);
        setupSettingsPanel(ADrawerLayout.BOTTOM_DRAWER, view);
    }

    private void readDrawerValues(ADrawerLayout adl, View v) {
        View subView1 = v.findViewById(R.id.center_settings);
        View subView2 = v.findViewById(R.id.left_settings);
        View subView3 = v.findViewById(R.id.right_settings);
        View subView4 = v.findViewById(R.id.top_settings);
        View subView5 = v.findViewById(R.id.bottom_settings);

        readDrawerValues(ADrawerLayout.NO_DRAWER, adl, subView1);
        readDrawerValues(ADrawerLayout.LEFT_DRAWER, adl, subView2);
        readDrawerValues(ADrawerLayout.RIGHT_DRAWER, adl, subView3);
        readDrawerValues(ADrawerLayout.TOP_DRAWER, adl, subView4);
        readDrawerValues(ADrawerLayout.BOTTOM_DRAWER, adl, subView5);
    }

    private void readDrawerValues(int drawer, ADrawerLayout adl, View v) {
        SeekBar seek1 = (SeekBar) v.findViewById(R.id.progress1);
        SeekBar seek2 = (SeekBar) v.findViewById(R.id.progress2);
        SeekBar seek3 = (SeekBar) v.findViewById(R.id.progress3);
        SeekBar seek4 = (SeekBar) v.findViewById(R.id.progress4);

        if (drawer == ADrawerLayout.NO_DRAWER) {
            float paralaxX = adl.getParalaxFactorX();
            float paralaxY = adl.getParalaxFactorY();
            int dimColor = adl.getDimmingColor();
            float dimFraction = Color.alpha(dimColor) / 255.f;
            seek1.setProgress(Math.round((paralaxX - (-1)) * .5f * 1000));
            seek2.setProgress(Math.round((paralaxY - (-1)) * .5f * 1000));
            seek3.setProgress(Math.round(dimFraction * 1000));
        } else {
            ADrawerLayout.AnimationParameters params = adl.getAnimationParameters(drawer);

            float scale = params.mScale;
            float alpha = params.mAlpha;
            float rotX = params.mRotX;
            float rotY = params.mRotY;

            seek1.setProgress(Math.round(scale / .5f * 1000));
            seek2.setProgress(Math.round(alpha / 1.f * 1000));
            seek3.setProgress(Math.round((rotX + 45f) / 90f * 1000));
            seek4.setProgress(Math.round((rotY + 45f) / 90f * 1000));
        }
    }

    private void updateDrawerValues(ADrawerLayout adl, View v) {
        View subView1 = v.findViewById(R.id.center_settings);
        View subView2 = v.findViewById(R.id.left_settings);
        View subView3 = v.findViewById(R.id.right_settings);
        View subView4 = v.findViewById(R.id.top_settings);
        View subView5 = v.findViewById(R.id.bottom_settings);

        updateDrawerValues(ADrawerLayout.NO_DRAWER, adl, subView1);
        updateDrawerValues(ADrawerLayout.LEFT_DRAWER, adl, subView2);
        updateDrawerValues(ADrawerLayout.RIGHT_DRAWER, adl, subView3);
        updateDrawerValues(ADrawerLayout.TOP_DRAWER, adl, subView4);
        updateDrawerValues(ADrawerLayout.BOTTOM_DRAWER, adl, subView5);
    }

    private void updateDrawerValues(int drawer, ADrawerLayout adl, View v) {
        SeekBar seek1 = (SeekBar) v.findViewById(R.id.progress1);
        SeekBar seek2 = (SeekBar) v.findViewById(R.id.progress2);
        SeekBar seek3 = (SeekBar) v.findViewById(R.id.progress3);
        SeekBar seek4 = (SeekBar) v.findViewById(R.id.progress4);

        if (drawer == ADrawerLayout.NO_DRAWER) {
            float paralaxX = (seek1.getProgress() - 500f) / 500.f;
            float paralaxY = (seek2.getProgress() - 500f) / 500.f;
            float dimFraction = (seek3.getProgress() / 1000.f);
            int dimColor = Color.argb(Math.round(0xff * dimFraction), 0x00, 0x00, 0x00);
            adl.setParalaxFactorX(paralaxX);
            adl.setParalaxFactorY(paralaxY);
            adl.setDimmingColor(dimColor);
        } else {
            ADrawerLayout.AnimationParameters params = adl.getAnimationParameters(drawer);
            float scale = seek1.getProgress() / 1000.f * .5f;
            float alpha = seek2.getProgress() / 1000.f * 1.f;
            float rotX = seek3.getProgress() / 1000.f * 1f - .5f;
            float rotY = seek4.getProgress() / 1000.f * 1f - .5f;
            params.mScale = scale;
            params.mAlpha = alpha;
            params.mRotX = rotX;
            params.mRotY = rotY;
        }
    }

    private void setupSettingsPanel(int panel, View v) {
        TextView label1 = (TextView) v.findViewById(R.id.progress1_label);
        TextView label2 = (TextView) v.findViewById(R.id.progress2_label);
        TextView label3 = (TextView) v.findViewById(R.id.progress3_label);
        TextView label4 = (TextView) v.findViewById(R.id.progress4_label);

        SeekBar seek1 = (SeekBar) v.findViewById(R.id.progress1);
        SeekBar seek2 = (SeekBar) v.findViewById(R.id.progress2);
        SeekBar seek3 = (SeekBar) v.findViewById(R.id.progress3);
        SeekBar seek4 = (SeekBar) v.findViewById(R.id.progress4);

        boolean centerContent = panel == ADrawerLayout.NO_DRAWER;

        if (label1 != null) label1.setText(centerContent ? "X axis paralax [-1, 1]" : "Scale");
        if (seek1 != null) {
            seek1.setMax(1000);
            seek1.setOnSeekBarChangeListener(this);
        }

        if (label2 != null) label2.setText(centerContent ? "Y axis paralax [-1, 1]" : "Alpha");
        if (seek2 != null) {
            seek2.setMax(1000);
            seek2.setOnSeekBarChangeListener(this);
        }

        if (label3 != null) label3.setText(centerContent ? "Content dim" : "Rot X");
        if (seek3 != null) {
            seek3.setMax(1000);
            seek3.setOnSeekBarChangeListener(this);
        }

        if (centerContent) {
            label4.setVisibility(View.GONE);
            seek4.setVisibility(View.GONE);
        } else {
            if (label4 != null) label4.setText("Rot Y");
            if (seek4 != null) {
                seek4.setMax(1000);
                seek4.setOnSeekBarChangeListener(this);
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser && getView() != null)
            updateDrawerValues(mDrawerLayout, getView());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
