package com.skfaisal.cameraform;

import android.net.Uri;
import android.view.ViewGroup;

import androidx.databinding.ViewDataBinding;

import com.faisal.basemodule.base.BaseAdapter;
import com.faisal.basemodule.base.BaseViewHolder;
import com.skfaisal.cameraform.databinding.ItemChildBinding;
import com.skfaisal.cameraform.model.ItemChildModel;
import com.skfaisal.cameraform.model.ItemParentModel;

import java.io.File;

public class ChildAdapter extends BaseAdapter<ItemChildModel> {
    @Override
    public boolean isEqual(ItemChildModel left, ItemChildModel right) {
        return false;
    }

    @Override
    public BaseViewHolder<ItemChildModel> newViewHolder(ViewGroup parent, int viewType) {
        return new ParentAdapterViewHolder(inflate(parent,R.layout.item_child));
    }

    private class ParentAdapterViewHolder extends BaseAdapterViewHolder<ItemChildModel>{
        ItemChildBinding mItemBinding;

        public ParentAdapterViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding);
            mItemBinding = (ItemChildBinding) viewDataBinding;
        }

        @Override
        public void bind(ItemChildModel item) {
            if(item.getBitmap()!=null)
            {

                mItemBinding.imgAdd.setImageBitmap(item.getBitmap());
            }
        }
    }
}
