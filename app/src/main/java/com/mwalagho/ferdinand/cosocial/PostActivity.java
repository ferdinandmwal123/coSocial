package com.mwalagho.ferdinand.cosocial;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostActivity extends AppCompatActivity {


    @BindView(R.id.imageSelect) ImageButton mSelectImage;
    @BindView(R.id.nameField) EditText mPostTitle;
    @BindView(R.id.descField) EditText mPostDesc;
    @BindView(R.id.buttonPost) Button mSubmitBtn;
    private ProgressDialog mProgress;

    private  Uri mImageUri = null;
    private static final int GALLERY_REQUEST = 1;
    private StorageReference mStorage;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

        mProgress = new ProgressDialog(this);
        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Post");


        mSelectImage.setOnClickListener(v -> {
            Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
            galleryIntent.setType("image/*");
            startActivityForResult(galleryIntent,GALLERY_REQUEST);


        });

        mSubmitBtn.setOnClickListener(v -> startPosting());
    }

    private void startPosting() {

        mProgress.setMessage("Posting.....");
        mProgress.show();
        String titleValue = mPostTitle.getText().toString().trim();
        String descValue = mPostDesc.getText().toString().trim();

        if(!TextUtils.isEmpty(titleValue) && !TextUtils.isEmpty(descValue) && mImageUri != null){

            StorageReference filePath = mStorage.child("Post_images").child(mImageUri.getLastPathSegment());
            filePath.putFile(mImageUri).addOnSuccessListener(taskSnapshot -> {

                Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                task.addOnSuccessListener(uri -> {
                    String photoLink = uri.toString();


                    DatabaseReference newPost = mDatabase.push();//create unique random id

                    newPost.child("title").setValue(titleValue);
                    newPost.child("desc").setValue(descValue);
                    newPost.child("image").setValue(photoLink);

                    mProgress.dismiss();
                    startActivity(new Intent(PostActivity.this,MainActivity.class));
                });

            });
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            mImageUri =  data.getData();

            mSelectImage.setImageURI(mImageUri);
        }
    }
}
