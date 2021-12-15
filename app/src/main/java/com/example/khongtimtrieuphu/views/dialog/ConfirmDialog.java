package com.example.khongtimtrieuphu.views.dialog;

import android.view.View;

import com.example.khongtimtrieuphu.R;
import com.example.khongtimtrieuphu.databinding.ConfirmDialogBinding;

public class ConfirmDialog extends BaseDialog<ConfirmDialogBinding> {

    public String text;

    @Override
    protected ConfirmDialogBinding initBinding(View mRootView) {
        return ConfirmDialogBinding.bind(mRootView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.confirm_dialog;
    }

    @Override
    protected void initViews() {

        if(text != null){
            binding.tvAskUser.setText(text);
        }
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
                if (getDialog() != null){
                    getDialog().dismiss();
                    onDismissListener.onDismiss(getDialog());
                }
            }
        });
    }

    public void setText(String text) {
        this.text = text;
    }
}
