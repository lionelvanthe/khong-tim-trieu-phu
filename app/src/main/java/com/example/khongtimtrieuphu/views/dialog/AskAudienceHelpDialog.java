package com.example.khongtimtrieuphu.views.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.khongtimtrieuphu.R;
import com.example.khongtimtrieuphu.databinding.AskAudienceHelpDialogBinding;
import com.skydoves.progressview.ProgressView;


public class AskAudienceHelpDialog extends BaseDialog<AskAudienceHelpDialogBinding>{


    @Override
    protected AskAudienceHelpDialogBinding initBinding(View mRootView) {
        return AskAudienceHelpDialogBinding.bind(mRootView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ask_audience_help_dialog;
    }

    @Override
    protected void initViews() {

        sharedViewModel.randomPercentCase();

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f
        );

        for (int i = 0; i < 4 ; i++) {

            View v = addProgress(i, sharedViewModel.percentCase[i]);

            v.setLayoutParams(param);
            binding.lnDialog.addView(v);
        }

        binding.btnCloseDialogAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getDialog() != null){
                    getDialog().dismiss();
                    onDismissListener.onDismiss(getDialog());
                }
            }
        });
    }

    private View addProgress(int i, float random) {

        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.custom_progress_bar, null, false);
        ProgressView progressView = view.findViewById(R.id.progress_view);
        TextView tvCase = view.findViewById(R.id.tv_case);

        TextView tvPercent = view.findViewById(R.id.tv_percent);
        tvPercent.setY(690 - random*6.9f);
        tvPercent.setText(String.format("%.1f", random) + "%");
        char a = (char) (i+65);
        tvCase.setText(String.valueOf(a));
        progressView.setBackgroundColor(0x00000000);
        progressView.setProgress(random);
        return view;
    }



}
