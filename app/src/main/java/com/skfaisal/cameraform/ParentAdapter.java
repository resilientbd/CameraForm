package com.skfaisal.cameraform;

import android.view.ViewGroup;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.faisal.basemodule.base.BaseAdapter;
import com.faisal.basemodule.base.BaseViewHolder;
import com.skfaisal.cameraform.databinding.ItemParentBinding;
import com.skfaisal.cameraform.model.ItemParentModel;

public class ParentAdapter extends BaseAdapter<ItemParentModel> {
    private MainActivity activity;
    public ParentAdapter(MainActivity activity){
        this.activity = activity;
    }
    @Override
    public boolean isEqual(ItemParentModel left, ItemParentModel right) {
        return false;
    }

    @Override
    public BaseViewHolder<ItemParentModel> newViewHolder(ViewGroup parent, int viewType) {
        return new ParentAdapterViewHolder(inflate(parent,R.layout.item_parent));
    }

    private class ParentAdapterViewHolder extends BaseAdapterViewHolder<ItemParentModel>{
        ItemParentBinding mItemBinding;
        public ParentAdapterViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding);
            mItemBinding = (ItemParentBinding) viewDataBinding;
        }

        @Override
        public void bind(ItemParentModel item) {
            ChildAdapter childAdapter = new ChildAdapter();
            mItemBinding.itemParentRecyclerview.setAdapter(childAdapter);
            childAdapter.addItems(item.getItemChildModelArrayList());
            mItemBinding.itemParentRecyclerview.setLayoutManager(new LinearLayoutManager(mItemBinding.getRoot().getContext(), RecyclerView.HORIZONTAL,false));
            childAdapter.setItemClickListener((view, childModel) -> {
                activity.OnSelectItem(childModel);
            });
        }
    }
}
