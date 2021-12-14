package com.example.khongtimtrieuphu.views.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.khongtimtrieuphu.OnActionCallBack;
import com.example.khongtimtrieuphu.views.dialog.QuitGameDialog;

public abstract class BaseFragment<K extends ViewDataBinding, V extends ViewModel> extends Fragment implements View.OnClickListener {

    protected Context mContext;
    protected View mRootView;
    protected OnActionCallBack callBack;
    protected MediaPlayer mediaPlayer = new MediaPlayer();

    public void setCallBack(OnActionCallBack callBack) {
        this.callBack = callBack;
    }

    protected V mViewModel;
    protected K binding;

    @Override
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater,
                                   @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState) {


        mRootView = inflater.inflate(getLayoutId(), container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(getViewModelClass());
        binding = initBinding(mRootView);

        initViews();
        setUpListener();
        setUpObserver();
        return mRootView;
    }

    protected void playMusic(int musicId, boolean loop){
        mediaPlayer = MediaPlayer.create(mContext, musicId);
        mediaPlayer.setLooping(loop);
        mediaPlayer.start();
    }

    public void quitGame(){
        QuitGameDialog quitGameDialog = new QuitGameDialog();
        quitGameDialog.show(getActivity().getSupportFragmentManager(), "quit_game");
    }

    protected abstract K initBinding(View mRootView);

    protected abstract Class<V> getViewModelClass();

    protected abstract int getLayoutId();

    protected abstract void initViews();

    protected abstract void setUpListener();

    protected abstract void setUpObserver();
}
