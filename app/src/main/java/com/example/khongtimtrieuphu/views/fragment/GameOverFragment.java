package com.example.khongtimtrieuphu.views.fragment;

import android.view.View;
import android.widget.Toast;
import com.example.khongtimtrieuphu.R;
import com.example.khongtimtrieuphu.databinding.GameOverFragmentBinding;
import com.example.khongtimtrieuphu.model.User;
import com.example.khongtimtrieuphu.viewmodel.SharedViewModel;

public class GameOverFragment extends BaseFragment<GameOverFragmentBinding, SharedViewModel>{

    public static final String KEY_SHOW_HOME = "KEY_SHOW_HOME";

    @Override
    protected GameOverFragmentBinding initBinding(View mRootView) {
        return GameOverFragmentBinding.bind(mRootView);
    }

    @Override
    protected Class<SharedViewModel> getViewModelClass() {
        return SharedViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.game_over_fragment;
    }

    @Override
    protected void initViews() {
        String level;
        String money;
        if(mViewModel.getLevel() == 0){
            level = "Bạn ngu vc khi không trả lời được câu nào";
        }
        else{
            level = "Bạn đã dừng lại ở câu " + mViewModel.getLevel();
        }
        if(mViewModel.getMoney() == null){
            money = "Ban nhận được 200.00đ";
        }
        else{
          money = "Bạn nhận được " + mViewModel.getMoney() + "đ";
        }

        binding.tvNumberQuestion.setText(level);
        binding.tvMoneyGameOver.setText(money);
    }

    @Override
    protected void setUpListener() {
        binding.btnSave.setOnClickListener(this);
    }

    @Override
    protected void setUpObserver() {

    }

    @Override
    public void onClick(View view) {
        if(binding.edtName.getText().toString().trim().isEmpty()){
            Toast.makeText(mContext, "Nhập tên của mày vào", Toast.LENGTH_SHORT).show();
        }
        else{
            String name = binding.edtName.getText().toString().trim();
            String money = mViewModel.getMoney();
            mViewModel.getPreference().getUsers().add(new User(name, money));
            mViewModel.getPreference().savePref();

            gotoHome();
        }
    }
    private void gotoHome(){
        callBack.callBack(KEY_SHOW_HOME, null);
    }
}
