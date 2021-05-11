package com.am.mvp.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.am.mvp.app.MVPActivity;

public class MainActivity extends MVPActivity implements MainView {

    private final MainPresenter mPresenter = new MainPresenter().setViewHolder(getViewHolder());
    private TextView mVText;

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVText = findViewById(R.id.text);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mPresenter.load();
    }

    // View
    @Override
    public void onDataLoaded() {
        mVText.setText(mPresenter.getText());
    }
}