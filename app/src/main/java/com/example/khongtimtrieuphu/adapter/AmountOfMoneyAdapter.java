package com.example.khongtimtrieuphu.adapter;

import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.khongtimtrieuphu.databinding.ItemAmountOfMoneyBinding;
import com.example.khongtimtrieuphu.model.AmountOfMoney;
import java.util.List;

public class AmountOfMoneyAdapter extends RecyclerView.Adapter<AmountOfMoneyAdapter.ViewHolder> {

    private List<AmountOfMoney> list;

    public AmountOfMoneyAdapter(List<AmountOfMoney>list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AmountOfMoneyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemAmountOfMoneyBinding binding = ItemAmountOfMoneyBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AmountOfMoneyAdapter.ViewHolder holder, int position) {
       holder.binding.tvItemMoney.setText(list.get(position).getMoney());
        if(position % 5 == 0){
            holder.binding.tvItemMoney.setTextColor(Color.parseColor("#ffe200"));
        }

        if(list.get(position).isSelected()){
            holder.binding.backgroundItemMoney.setVisibility(View.VISIBLE);
        }
        else{
            holder.binding.backgroundItemMoney.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemAmountOfMoneyBinding binding;

        public ViewHolder(@NonNull ItemAmountOfMoneyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
