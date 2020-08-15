package com.grishma.searchimage.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.grishma.searchimage.R;
import com.grishma.searchimage.model.ImageSearch;
import com.grishma.searchimage.utils.Utilities;

/**
 * ImageDetail Screen Activity
 */
public class ImageDetailsActivity extends AppCompatActivity {

    private AppCompatImageView ivSearchImgDetail;
    private AppCompatEditText etAddComment;
    private AppCompatButton btnAddComment;
    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        ivSearchImgDetail = findViewById(R.id.ivSearchImgDetail);
        etAddComment = findViewById(R.id.etAddComment);
        btnAddComment = findViewById(R.id.btnAddComment);

        //get data from intent
        ImageSearch.Data data = (ImageSearch.Data) getIntent().getExtras().getSerializable(Utilities.DATA);
        position = getIntent().getExtras().getInt(Utilities.POSITION);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //check if comment is in db or not if it's there then set comment from db
        if (data.getComment() != null && !data.getComment().isEmpty()) {
            etAddComment.setText(data.getComment());
        }

        //check if images object is there
        if (data.getImages() != null) {
            //set action bar title
            getSupportActionBar().setTitle(data.getImages().get(0).getLink());
            //load image
            Glide.with(ImageDetailsActivity.this).load(data.getImages().get(0).getLink())
                    .placeholder(R.drawable.user_placeholder)
                    .into(ivSearchImgDetail);
        } else {
            //If images is not there then set ad url
            //set action bar title
            getSupportActionBar().setTitle(data.getAdUrl());
            //load image
            Glide.with(ImageDetailsActivity.this).load(data.getAdUrl())
                    .placeholder(R.drawable.user_placeholder)
                    .into(ivSearchImgDetail);
        }

        //btnAddComment click
        btnAddComment.setOnClickListener(v -> {
            if (etAddComment.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter some comment!!", Toast.LENGTH_SHORT).show();
            } else {

                //set comment
                String comment = etAddComment.getText().toString();
                data.setComment(comment);

                //finish activity with result
                Intent returnIntent = new Intent();
                returnIntent.putExtra(Utilities.RESULT, data);
                returnIntent.putExtra(Utilities.POSITION, position);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

    }

    //back arrow click
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
