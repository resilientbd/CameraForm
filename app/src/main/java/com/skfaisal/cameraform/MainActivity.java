package com.skfaisal.cameraform;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.faisal.basemodule.base.BaseActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.skfaisal.cameraform.databinding.ActivityMainBinding;
import com.skfaisal.cameraform.model.ItemChildModel;
import com.skfaisal.cameraform.model.ItemParentModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private ItemChildModel selecteChildModel;
    private ParentAdapter parentAdapter;
    private ActivityMainBinding mBinding;

    private Uri imageUri;

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void startUI() {
        parentAdapter = new ParentAdapter(MainActivity.this);
        mBinding = (ActivityMainBinding) getViewDataBinding();
        mBinding.mainRecycerView.setAdapter(parentAdapter);
        mBinding.mainRecycerView.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < 10; i++) {
            ItemParentModel itemParentModel = new ItemParentModel();
            List<ItemChildModel> itemChildModels = new ArrayList<>();
            itemChildModels.add(new ItemChildModel());
            itemChildModels.add(new ItemChildModel());
            itemChildModels.add(new ItemChildModel());
            itemParentModel.setItemChildModelArrayList(itemChildModels);

            parentAdapter.addItem(itemParentModel);
        }

    }

    public void OnSelectItem(ItemChildModel childModel) {
        this.selecteChildModel = childModel;
        Dexter.withContext(this)
                .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if (multiplePermissionsReport.areAllPermissionsGranted()) {

                            imageUri = ImagePickerUtil.GetSingleImageFromCamera(MainActivity.this, 101);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                    }
                }).check();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {

            if (imageUri != null) {
                Log.d("chk", "imgpath:" + imageUri.getPath());

                if (!imageUri.getPath().isEmpty()) {
                    onImagePicked(imageUri);

                }
            }

        }
    }

    private void onImagePicked(Uri uri) {
        if (selecteChildModel != null) {
            selecteChildModel.setBitmap(ImageUtil.AdjustImageRotation(MainActivity.this,uri));
            parentAdapter.notifyDataSetChanged();
        }

        selecteChildModel = null;
        imageUri = null;
    }
}