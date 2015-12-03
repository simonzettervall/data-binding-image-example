package com.example.simon.rxdatabinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public class DefaultAdapter extends RecyclerView.Adapter<DefaultAdapter.DefaultHolder> {
    private List<DefaultModel> mModels;

    public DefaultAdapter(List<DefaultModel> models) {
        mModels = models;
    }

    @Override
    public DefaultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item, parent, false);
        DefaultHolder holder = new DefaultHolder(binding);

        return holder;
    }

    @Override
    public void onBindViewHolder(DefaultHolder holder, int position) {
        DefaultModel model = mModels.get(position);

        ViewDataBinding binding = holder.getBinding();
        binding.setVariable(com.example.simon.rxdatabinding.BR.model, model);
    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }

    public static class DefaultHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mBinding;

        public DefaultHolder(ViewDataBinding binding) {
            super(binding.getRoot());

            mBinding = binding;
        }

        public ViewDataBinding getBinding() {
            return mBinding;
        }
    }
}