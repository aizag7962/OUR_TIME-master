package com.example.calendar.adapters;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendar.R;
import com.example.calendar.models.ModelPost;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdapterPosts extends RecyclerView.Adapter<AdapterPosts.MyHolder> {

    Context context;
    List<ModelPost> postList;

    public AdapterPosts(Context context, List<ModelPost> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //inflate layout row_post.xml
        View view = LayoutInflater.from(context).inflate(R.layout.row_posts, viewGroup, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
    //get data
        String uid = postList.get(i).getUid();
        String uEmail = postList.get(i).getuEmail();
        String uName = postList.get(i).getuName();
        String uDp = postList.get(i).getuDp();
        String pId = postList.get(i).getpId();
        String pTitle = postList.get(i).getpTitle();
        String pDescription = postList.get(i).getpDescription();
        String pImage = postList.get(i).getpImage();
        String pTimeStamp = postList.get(i).getpTime();

            //convert timestamp to dd/mm/yyyy hh:mm am/pm
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(pTimeStamp));
        String pTime = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();

        //set data
        myHolder.NameTv.setText(uName);
        myHolder.TimeTv.setText(pTime);
        myHolder.TitleTv.setText(pTitle);
        myHolder.DescriptionTv.setText(pDescription);



        //set user dp
        try {
            Picasso.with(context).load(uDp).placeholder(R.drawable.ic_baseline_face_24).into(myHolder.PictureIv);
        }
        catch (Exception e) {

        }
        //set post image
        // if no image then hide imageView
        if (pImage.equals("noImage")) {
            //hide imageview
            myHolder.ImageIv.setVisibility(View.GONE);
        }
        else {
            try {
                Picasso.with(context).load(pImage).into(myHolder.ImageIv);
            }
            catch (Exception e) {

            }
        }


        //handle button clicks
        myHolder.moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "More", Toast.LENGTH_SHORT).show();

            }
        });
        myHolder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Like", Toast.LENGTH_SHORT).show();

            }
        });
        myHolder.commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Comment", Toast.LENGTH_SHORT).show();

            }
        });
        myHolder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Share", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    //view holder class
    class MyHolder extends RecyclerView.ViewHolder{

        //views from row_post.xml
        ImageView PictureIv, ImageIv;
        TextView NameTv, TimeTv, TitleTv, DescriptionTv, LikesTv;
        ImageButton moreButton;
        Button likeButton, commentButton, shareButton;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //init views
            PictureIv = itemView.findViewById(R.id.PictureIv);
            ImageIv = itemView.findViewById(R.id.ImageIv);
            NameTv = itemView.findViewById(R.id.NameTv);
            TimeTv = itemView.findViewById(R.id.TimeTv);
            TitleTv = itemView.findViewById(R.id.TitleTv);
            DescriptionTv = itemView.findViewById(R.id.DescriptionTv);
            LikesTv = itemView.findViewById(R.id.LikesTv);
            moreButton = itemView.findViewById(R.id.moreButton);
            likeButton = itemView.findViewById(R.id.likeButton);
            commentButton = itemView.findViewById(R.id.commentButton);
            shareButton = itemView.findViewById(R.id.shareButton);









        }
    }
}
