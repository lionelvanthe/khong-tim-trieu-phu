package com.example.khongtimtrieuphu.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.khongtimtrieuphu.databinding.ItemHighScoreBinding;
import com.example.khongtimtrieuphu.model.User;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> list;

    public UserAdapter(List<User> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHighScoreBinding binding = ItemHighScoreBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );

        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.binding.tvName.setText(list.get(position).getName());
        holder.binding.tvMoney.setText(list.get(position).getMoney());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        ItemHighScoreBinding binding;

        public UserViewHolder(@NonNull ItemHighScoreBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
