package com.darorman.gm3yaorman.ui.orman_acitivities_details;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.orman_activities.GalleryAct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Parsania Hardik on 23/04/2016.
 */
public class SlidingImage_Adapter extends PagerAdapter {


    private List<GalleryAct> galleryList;
    private LayoutInflater inflater;
    private Context context;


    public SlidingImage_Adapter(Context context, List<GalleryAct> galleryList) {
        this.context = context;
        this.galleryList = galleryList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return galleryList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);

        Glide.with(context).load(galleryList.get(position).getActivityImgDetUrl())
                .into(imageView);

        view.addView(imageLayout, 0);

//        imageLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(context.getClass().getName().equals("com.bit.apps.kanadra.View.Activity.ProjectDetails")){
//                    Intent intent = new Intent(context, FullImage.class);
//                    intent.putExtra("image", imageModelArrayList.get(position).getImage_drawable());
//                    context.startActivity(intent);
//                }
//
//            }
//        });


        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}