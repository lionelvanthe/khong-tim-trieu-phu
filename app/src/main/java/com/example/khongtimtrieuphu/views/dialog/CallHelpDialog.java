package com.example.khongtimtrieuphu.views.dialog;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.khongtimtrieuphu.R;
import com.example.khongtimtrieuphu.databinding.CallHelpDialogBinding;

public class CallHelpDialog extends BaseDialog<CallHelpDialogBinding>{

    private static final String text = "Tin tôi bạn ơi";
    @Override
    protected CallHelpDialogBinding initBinding(View mRootView) {
        return CallHelpDialogBinding.bind(mRootView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.call_help_dialog;
    }

    @Override
    protected void initViews() {
        addView(binding.imgTrump);
        addView(binding.imgPutin);
        addView(binding.imgObama);
        addView(binding.imgKimJongUn);
        binding.btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getDialog() != null){
                    getDialog().dismiss();
                    onDismissListener.onDismiss(getDialog());
                }
            }
        });
    }


    private void addView(View v){
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                );
                layoutParams.setMargins(0, 300, 0, 0);
                TextView textView = new TextView(getContext());

                textView.setText(sharedViewModel.getStringTrueCase() + text);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(26f);
                textView.setGravity(Gravity.CENTER);

                binding.layoutCallHelp.removeAllViews();
                binding.lnDialog.addView(textView, layoutParams);
            }
        });
    }
}
