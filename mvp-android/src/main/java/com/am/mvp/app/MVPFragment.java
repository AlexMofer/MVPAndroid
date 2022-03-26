/*
 * Copyright (C) 2020 AlexMofer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.am.mvp.app;

import android.content.Context;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.am.mvp.core.MVPView;
import com.am.mvp.core.MVPViewHolder;

/**
 * MVP基础Fragment
 * Created by Alex on 2020/2/28.
 */
public abstract class MVPFragment extends Fragment implements MVPView {

    private final MVPViewHolder<MVPView> mViewHolder = new MVPViewHolder<>();
    private final Observer<LifecycleOwner> mObserver =
            this::onLifecycleOwnerChanged;

    public MVPFragment() {
    }

    @ContentView
    public MVPFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        getViewLifecycleOwnerLiveData().observeForever(mObserver);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getViewLifecycleOwnerLiveData().removeObserver(mObserver);
    }

    private void onLifecycleOwnerChanged(LifecycleOwner source) {
        if (source != null) {
            mViewHolder.setView(this);
        } else {
            mViewHolder.setView(null);
        }
    }

    /**
     * 获取ViewHolder
     *
     * @return ViewHolder
     */
    @NonNull
    protected MVPViewHolder<? extends MVPView> getViewHolder() {
        return mViewHolder;
    }

    /**
     * 设置View
     * 便于在View已创建后手动调用
     *
     * @param view View
     */
    protected void setView(MVPView view) {
        mViewHolder.setView(view);
    }
}