package com.example.khongtimtrieuphu.views.fragment;

import android.content.DialogInterface;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.khongtimtrieuphu.R;
import com.example.khongtimtrieuphu.databinding.HomeFragmentBinding;
import com.example.khongtimtrieuphu.viewmodel.SharedViewModel;
import com.example.khongtimtrieuphu.views.dialog.ConfirmDialog;
import com.example.khongtimtrieuphu.views.dialog.TutorialDialog;

public class HomeFragment extends BaseFragment<HomeFragmentBinding, SharedViewModel> {

    public static final String KEY_SHOW_PLAY = "KEY_SHOW_PLAY";
    public static final String KEY_SHOW_HIGH_SCORE = "KEY_SHOW_HIGH_SCORE";
    private int flag = 0;

    @Override
    protected HomeFragmentBinding initBinding(View mRootView) {
        return HomeFragmentBinding.bind(mRootView);
    }

    @Override
    protected Class<SharedViewModel> getViewModelClass() {
        return SharedViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initViews() {
        setAnim();
        playMusic(R.raw.bgmusic, true);

    }

    @Override
    protected void setUpListener() {
        binding.btnPlay.setOnClickListener(this);
        binding.imgInfo.setOnClickListener(this);
        binding.imgHighScore.setOnClickListener(this);
        binding.imgSound.setOnClickListener(this);
    }

    @Override
    protected void setUpObserver() {

    }

    private void setAnim(){
        Animation rotateAnim = AnimationUtils.loadAnimation(mContext, R.anim.rotate_anim);
        binding.bgCircle.setAnimation(rotateAnim);
    }

    private void gotoPlay(){
        callBack.callBack(KEY_SHOW_PLAY, null);
    }

    private void showTutorialDialog(){
        TutorialDialog customDialogInfo = new TutorialDialog();
        customDialogInfo.show(getActivity().getSupportFragmentManager(), "tutorial_dialog");

    }

    private void onOrOffMusic(){
        if(flag == 0){
            binding.imgSound.setImageDrawable(mContext.getDrawable(R.drawable.atp__button_mute));
            mediaPlayer.setVolume(0, 0);
            flag = 1;
        }
        else{
            binding.imgSound.setImageDrawable(mContext.getDrawable(R.drawable.atp__button_voice));
            int maxVolume = 50;
            float log1=(float)(Math.log(maxVolume)/Math.log(maxVolume));
            mediaPlayer.setVolume(log1,log1);
            flag = 0;
        }
    }

    public void quitGame(){
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.show(getActivity().getSupportFragmentManager(), "quit_game");
        confirmDialog.setText(getString(R.string.quit_game));
        confirmDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                getActivity().finish();
            }
        });
    }

    private void gotoHighScore(){
        callBack.callBack(KEY_SHOW_HIGH_SCORE, null);
    }

    @Override
    public void onClick(View view) {

        if(view == binding.btnPlay){
            gotoPlay();
        }
        else if(view == binding.imgInfo){
            showTutorialDialog();
        }
        else if(view == binding.imgHighScore){
            gotoHighScore();
        }
        else if(view == binding.imgSound){
            onOrOffMusic();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mediaPlayer.pause();

    }
}
