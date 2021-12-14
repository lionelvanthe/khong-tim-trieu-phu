package com.example.khongtimtrieuphu.views.dialog;

import android.view.View;
import com.example.khongtimtrieuphu.R;
import com.example.khongtimtrieuphu.databinding.TutorialDialogBinding;

public class TutorialDialog extends BaseDialog<TutorialDialogBinding>{
    @Override
    protected TutorialDialogBinding initBinding(View mRootView) {
        return TutorialDialogBinding.bind(mRootView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.tutorial_dialog;
    }

    @Override
    protected void initViews() {
        binding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getDialog() != null){
                    getDialog().dismiss();
                }
            }
        });
    }
}
