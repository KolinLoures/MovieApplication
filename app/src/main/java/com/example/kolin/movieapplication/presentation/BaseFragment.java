package com.example.kolin.movieapplication.presentation;

import android.support.v4.app.Fragment;

import com.example.kolin.movieapplication.presentation.di.HasComponent;

/**
 * Created by kolin on 01.07.2016.
 */
public abstract class BaseFragment extends Fragment{

    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
