package com.bosh.module_mvp.injector.module;

import com.bosh.module_mvp.ui.base.BaseFragment;

import dagger.Module;
import dagger.Provides;

/**
 * @author bosh
 * @date 2019-07-17
 */
@Module
public class FragmentModule {
    private BaseFragment fragment;

    public FragmentModule(BaseFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    public BaseFragment provideFragment() {
        return fragment;
    }
}
