package com.example.khongtimtrieuphu.views.fragment;

import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;

import com.example.khongtimtrieuphu.R;
import com.example.khongtimtrieuphu.databinding.SplashFragmentBinding;
import com.example.khongtimtrieuphu.viewmodel.SplashViewModel;

public class SplashFragment extends BaseFragment<SplashFragmentBinding, SplashViewModel>{

    public static final String KEY_SHOW_HOME = "KEY_SHOW_HOME";

    @Override
    protected SplashFragmentBinding initBinding(View mRootView) {
        return SplashFragmentBinding.bind(mRootView);
    }

    @Override
    protected Class<SplashViewModel> getViewModelClass() {
        return SplashViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.splash_fragment;
    }

    @Override
    protected void initViews() {
        setAnim();
        new Handler().postDelayed(this::gotoHome, 5000);
    }

    private void setAnim(){
        Animation topAnim = AnimationUtils.loadAnimation(mContext, R.anim.top_splash_anim);
        Animation bottomAnim = AnimationUtils.loadAnimation(mContext, R.anim.bottom_splash_anim);
        Animation rotateAnim = AnimationUtils.loadAnimation(mContext, R.anim.rotate_anim);
        AnimationSet togetherAnim = new AnimationSet(false);
        togetherAnim.addAnimation(rotateAnim);
        togetherAnim.addAnimation(topAnim);

        binding.bgCircle.setAnimation(togetherAnim);
        binding.iconLogo.setAnimation(bottomAnim);
    }

    private void gotoHome() {
        callBack.callBack(KEY_SHOW_HOME, null);
    }

    @Override
    protected void setUpListener() {

    }

    @Override
    protected void setUpObserver() {

    }

    @Override
    public void onClick(View view) {

    }
}
