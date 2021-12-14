package com.example.khongtimtrieuphu.views.fragment;

import android.view.View;

import com.example.khongtimtrieuphu.R;
import com.example.khongtimtrieuphu.adapter.UserAdapter;
import com.example.khongtimtrieuphu.databinding.HighScoreFragmentBinding;
import com.example.khongtimtrieuphu.viewmodel.SharedViewModel;

import java.util.Collections;

public class HighScoreFragment extends BaseFragment<HighScoreFragmentBinding, SharedViewModel>{
    @Override
    protected HighScoreFragmentBinding initBinding(View mRootView) {
        return HighScoreFragmentBinding.bind(mRootView);
    }

    @Override
    protected Class<SharedViewModel> getViewModelClass() {
        return SharedViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.high_score_fragment;
    }

    @Override
    protected void initViews() {
        Collections.sort(mViewModel.getPreference().getPref());
        binding.recyclerViewHighScore.setHasFixedSize(true);
        binding.recyclerViewHighScore.setAdapter(new UserAdapter(mViewModel.getPreference().getPref()));
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
