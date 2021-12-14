package com.example.khongtimtrieuphu.views.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.khongtimtrieuphu.viewmodel.SharedViewModel;

public abstract class BaseDialog<K extends ViewDataBinding> extends DialogFragment {

    protected View mRootView;
    protected K binding;
    protected SharedViewModel sharedViewModel;
    protected DialogInterface.OnDismissListener onDismissListener;

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater,
                                   @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(getLayoutId(), container, false);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        binding = initBinding(mRootView);
        initViews();
        return mRootView;
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener listener){
        this.onDismissListener = listener;
    }

    protected abstract K initBinding(View mRootView);

    protected abstract int getLayoutId();

    protected abstract void initViews();

}
