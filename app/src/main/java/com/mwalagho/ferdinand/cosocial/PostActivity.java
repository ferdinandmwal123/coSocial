package com.mwalagho.ferdinand.cosocial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostActivity extends AppCompatActivity {

    @BindView(R.id.imageSelect) ImageButton mSelectImage;
    @BindView(R.id.nameField) EditText mPostTitle;
    @BindView(R.id.descField) EditText mPostDesc;
    @BindView(R.id.buttonPost) Button mSubmitBtn;

    private  Uri mImageUri = null;
    private static final int GALLERY_REQUEST = 1;
    private StorageReference mStorage;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

        mStorage = FirebaseStorage.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        mSelectImage.setOnClickListener(v -> {
            Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
            galleryIntent.setType("image/*");
            startActivityForResult(galleryIntent,GALLERY_REQUEST);


        });

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting();
            }
        });
    }

    private void startPosting() {
        String titleValue = mPostTitle.getText().toString().trim();
        String descValue = mPostDesc.getText().toString().trim();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            Uri imageUri = data.getData();

            mSelectImage.setImageURI(imageUri);
        }
    }
}
