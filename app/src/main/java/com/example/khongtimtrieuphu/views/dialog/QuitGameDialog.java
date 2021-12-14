package com.example.khongtimtrieuphu.views.dialog;

import android.view.View;

import com.example.khongtimtrieuphu.R;
import com.example.khongtimtrieuphu.databinding.QuitGameDialogBinding;

public class QuitGameDialog extends BaseDialog<QuitGameDialogBinding>{
    @Override
    protected QuitGameDialogBinding initBinding(View mRootView) {
        return QuitGameDialogBinding.bind(mRootView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.quit_game_dialog;
    }

    @Override
    protected void initViews() {
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getDialog() != null){
                    getDialog().dismiss();
                }
            }
        });
        binding.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }
}
