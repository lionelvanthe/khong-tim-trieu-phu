package com.example.khongtimtrieuphu.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.example.khongtimtrieuphu.OnActionCallBack;
import com.example.khongtimtrieuphu.R;
import com.example.khongtimtrieuphu.views.fragment.BaseFragment;
import com.example.khongtimtrieuphu.views.fragment.GameOverFragment;
import com.example.khongtimtrieuphu.views.fragment.HighScoreFragment;
import com.example.khongtimtrieuphu.views.fragment.PlayFragment;
import com.example.khongtimtrieuphu.views.fragment.HomeFragment;
import com.example.khongtimtrieuphu.views.fragment.SplashFragment;

public class MainActivity extends AppCompatActivity implements OnActionCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = LayoutInflater.from(this).inflate(getLayoutId(), null);
        setContentView(rootView);
        initViews();
    }

    private void initViews(){
            SplashFragment splashFragment = new SplashFragment();
            splashFragment.setCallBack(this);
            showFragment(splashFragment, false,0,0);
    }

    private int getLayoutId(){
        return R.layout.activity_main;
    }

    private void showFragment(Fragment fragment, boolean addToBackStack, int anim_start, int anim_end){

        if(!isFinishing()){
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            if(anim_end != 0  && anim_start!= 0){
                transaction.setCustomAnimations(anim_start,anim_end);
            }
            transaction.replace(R.id.container_view,fragment);
            if(addToBackStack){
                transaction.addToBackStack("add");
            }
            transaction.commit();
        }
    }

    @Override
    public void callBack(String key, Object data) {
        switch (key){
            case SplashFragment.KEY_SHOW_HOME:
                HomeFragment homeFragment = new HomeFragment();
                homeFragment.setCallBack(this);
                showFragment(homeFragment, false, R.anim.anim_start, R.anim.anim_end);
                break;
            case HomeFragment.KEY_SHOW_PLAY:
                PlayFragment playFragment = new PlayFragment();
                playFragment.setCallBack(this);
                showFragment(playFragment, true, R.anim.anim_start, R.anim.anim_end);
                break;
            case PlayFragment.KEY_SHOW_GAME_OVER:
                GameOverFragment gameOverFragment = new GameOverFragment();
                gameOverFragment.setCallBack(this);
                showFragment(gameOverFragment, false, R.anim.anim_start, R.anim.anim_end);
                break;

            case HomeFragment.KEY_SHOW_HIGH_SCORE:
                HighScoreFragment highScoreFragment = new HighScoreFragment();
                highScoreFragment.setCallBack(this);
                showFragment(highScoreFragment, true, R.anim.anim_start, R.anim.anim_end);
                break;
        }
    }

    @Override
    public void onBackPressed() {

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container_view);
        if(fragment instanceof HomeFragment || fragment instanceof PlayFragment){
            ((BaseFragment<?, ?>) fragment).quitGame();
        }
        else{
            super.onBackPressed();
        }
    }
}