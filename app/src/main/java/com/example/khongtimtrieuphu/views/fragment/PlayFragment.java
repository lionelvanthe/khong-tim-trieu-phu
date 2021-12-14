package com.example.khongtimtrieuphu.views.fragment;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.Observer;
import com.ankushgrover.hourglass.Hourglass;
import com.example.khongtimtrieuphu.Music;
import com.example.khongtimtrieuphu.R;
import com.example.khongtimtrieuphu.adapter.AmountOfMoneyAdapter;
import com.example.khongtimtrieuphu.databinding.PlayFragmentBinding;
import com.example.khongtimtrieuphu.model.AmountOfMoney;
import com.example.khongtimtrieuphu.model.Question;
import com.example.khongtimtrieuphu.viewmodel.SharedViewModel;
import com.example.khongtimtrieuphu.views.dialog.AskAudienceHelpDialog;
import com.example.khongtimtrieuphu.views.dialog.CallHelpDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayFragment extends BaseFragment<PlayFragmentBinding, SharedViewModel> {

    public static final String KEY_SHOW_GAME_OVER = "KEY_SHOW_GAME_OVER";

    private AmountOfMoneyAdapter adapter;
    private int currentQuestion = 1;
    private final List<View> views = new ArrayList<>();
    private Hourglass hourglass = null;

    @Override
    protected PlayFragmentBinding initBinding(View mRootView) {
        return PlayFragmentBinding.bind(mRootView);
    }

    @Override
    protected Class<SharedViewModel> getViewModelClass() {
        return SharedViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.play_fragment;
    }

    @Override
    protected void initViews() {
        addToViews();
        initCountDown();
        playMusic(R.raw.luatchoi_c, false);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                binding.drawer.closeDrawers();
                hourglass.startTimer();
                mViewModel.changeIsSelected(0, false);
                mViewModel.changeIsSelected(14, true);
                playMusic(Music.questions[currentQuestion -1], false);

            }
        });
        binding.drawer.openDrawer(GravityCompat.START);
        setUpForRecyclerView();
        updateRecyclerView();
        mViewModel.getQuestion(currentQuestion);

    }

    private void addToViews(){
        views.add(binding.tvAnswerA);
        views.add(binding.tvAnswerB);
        views.add(binding.tvAnswerC);
        views.add(binding.tvAnswerD);
    }

    private void setUpForRecyclerView(){
        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(mContext, R.anim.layout_animation);
        binding.recyclerViewMoney.setLayoutAnimation(animationController);
        adapter = new AmountOfMoneyAdapter(mViewModel.getListAmountOfMoney());
        binding.recyclerViewMoney.setAdapter(adapter);
        mViewModel.getAmountOfMoney().observe(getViewLifecycleOwner(), new Observer<List<AmountOfMoney>>() {
            @Override
            public void onChanged(List<AmountOfMoney> amountOfMonies) {
                adapter = new AmountOfMoneyAdapter(amountOfMonies);
                binding.recyclerViewMoney.setAdapter(adapter);
            }
        });
    }

    private void updateRecyclerView() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 10;
                try {
                    Thread.sleep(4100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                do {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mViewModel.changeIsSelected(i, true);
                    i -= 5;

                } while (i >= 0);
            }
        });
        thread.start();
    }

    @Override
    protected void setUpListener() {

        binding.stopHelp.setOnClickListener(this);
        binding.changeQuestionHelp.setOnClickListener(this);
        binding.img5050HelpPlay.setOnClickListener(this);
        binding.askTheAudienceHelp.setOnClickListener(this);
        binding.callHelp.setOnClickListener(this);

        for(View v : views){
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hourglass.pauseTimer();
                    setBlinkEffect(v, R.drawable.backgrond_answer_selected);
                    checkAnswer(v);
                    playMusic(Music.answers[views.indexOf(v)], false);
                }
            });
        }


    }

    @Override
    protected void setUpObserver() {
        mViewModel.getResult().observe(getViewLifecycleOwner(), new Observer<Question>() {
            @Override
            public void onChanged(Question question) {

                mViewModel.setTrueCase(question.getTrueCase());
                for (View v : views){
                    v.setBackgroundResource(R.drawable.atp__play_answer_background_normal);
                    if(v.getX() != 195){
                        v.setX(195f);
                    }
                }

                String orderQuestion = "Câu " + currentQuestion;
                String answerA = "A. " + question.getCaseA();
                String answerB = "B. " + question.getCaseB();
                String answerC = "C. " + question.getCaseC();
                String answerD = "D. " + question.getCaseD();

                binding.tvOrderQuestion.setText(orderQuestion);

                binding.tvQuestion.setText(question.getQuestion());
                binding.tvAnswerA.setText(answerA);
                binding.tvAnswerB.setText(answerB);
                binding.tvAnswerC.setText(answerC);
                binding.tvAnswerD.setText(answerD);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == binding.stopHelp){
            stopHelp();
            binding.stopHelp.setOnClickListener(null);
        }
        else if(view == binding.changeQuestionHelp){
            binding.changeQuestionHelp.setImageDrawable(mContext.getDrawable(R.drawable.atp__image_help_change_question_x));
            changeQuestionHelp();

            binding.changeQuestionHelp.setOnClickListener(null);
        }
        else if(view == binding.img5050HelpPlay){

            binding.img5050HelpPlay.setImageDrawable(mContext.getDrawable(R.drawable.atp__image_help_5050_x));
            playMusic(Music._50_50, false);
            hourglass.pauseTimer();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    _50_50_help();
                    hourglass.resumeTimer();
                }
            });
            binding.img5050HelpPlay.setOnClickListener(null);
        }
        else if(view == binding.askTheAudienceHelp){
            binding.askTheAudienceHelp.setOnClickListener(null);
            binding.askTheAudienceHelp.setImageDrawable(mContext.getDrawable(R.drawable.atp__image_help_audience_x));
            hourglass.pauseTimer();
            askTheAudienceHelp();


        }
        else if(view == binding.callHelp){
            binding.callHelp.setOnClickListener(null);
            binding.callHelp.setImageDrawable(mContext.getDrawable(R.drawable.atp__image_help_call_x));
            hourglass.pauseTimer();
            callHelp();

        }
    }

    private void stopHelp(){
        gotoGameOver();
    }

    private void changeQuestionHelp(){
        mViewModel.getQuestion(currentQuestion);
    }
    private void _50_50_help(){
        List<View> tempList = new ArrayList<>();
        for(int i = 0 ; i < views.size() ; i++){
            if((i + 1) != mViewModel.getTrueCase()){
                tempList.add(views.get(i));
            }
        }

        int rand = new Random().nextInt(3);
        switch (rand){
            case 0:
                moveAnimation(tempList.get(0), 1500);
                moveAnimation(tempList.get(1), -1500);
                break;
            case 1:
                moveAnimation(tempList.get(0), 1500);
                moveAnimation(tempList.get(2), -1500);
                break;
            case 2:
                moveAnimation(tempList.get(1), 1500);
                moveAnimation(tempList.get(2), -1500);
                break;
        }
    }

    private void moveAnimation(View view, float value){
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "translationX", value);
        animation.setDuration(800);
        animation.start();
    }

    private void askTheAudienceHelp(){
        AskAudienceHelpDialog askAudienceHelpDialog = new AskAudienceHelpDialog();
        askAudienceHelpDialog.show(getActivity().getSupportFragmentManager(), "ask_the_audience_dialog");

        askAudienceHelpDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                hourglass.resumeTimer();
            }
        });
    }

    private void callHelp(){
        CallHelpDialog callHelpDialog = new CallHelpDialog();
        callHelpDialog.show(getActivity().getSupportFragmentManager(), "call_help_dialog");

        callHelpDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                hourglass.resumeTimer();
            }
        });
    }

    private void initCountDown(){
        hourglass = new Hourglass(30000, 1000) {
            @Override
            public void onTimerTick(long timeRemaining) {
                binding.tvCountDown.setText(String.valueOf(timeRemaining/1000));
            }

            @Override
            public void onTimerFinish() {
                gotoGameOver();
            }
        };
    }


    private void setBlinkEffect(View view, int background){
        view.setBackgroundResource(background);
        AnimationDrawable frameAnimation = (AnimationDrawable) view.getBackground();
        frameAnimation.start();
    }

    private void checkAnswer(View view){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(views.get(mViewModel.getTrueCase()-1) == view){
                    playMusic(Music.trues[mViewModel.getTrueCase() - 1 ], false);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            nextQuestion();
                            playMusic(Music.questions[currentQuestion -1], false);
                        }
                    });

                }
                else{
                    view.setBackgroundResource(R.drawable.atp__play_answer_background_wrong);
                    playMusic(Music.loses[mViewModel.getTrueCase() - 1 ], false);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            gotoGameOver();
                        }
                    });
                }
                setBlinkEffect(views.get(mViewModel.getTrueCase()-1), R.drawable.background_answer_true);

            }
        }, 3000);

    }

    private void nextQuestion(){
        if(currentQuestion == 15){
            gotoGameOver();
        }
        else{
            initCountDown();
            hourglass.startTimer();
            currentQuestion ++;
            binding.tvMoney.setText(mViewModel.getAmountOfMoney().getValue().get(15- currentQuestion).getMoney());
            mViewModel.changeIsSelected(15- currentQuestion, true);
            mViewModel.getQuestion(currentQuestion);
        }
    }

    private void gotoGameOver(){
        mViewModel.setLevel(currentQuestion);
        mViewModel.setMoney(binding.tvMoney.getText().toString());
        mViewModel.changeIsSelected(15- currentQuestion, false);
        callBack.callBack(KEY_SHOW_GAME_OVER, null);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
        if(hourglass.isPaused()){
            hourglass.resumeTimer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mediaPlayer.pause();
        if(hourglass.isRunning()){
            hourglass.pauseTimer();
        }
    }

}
