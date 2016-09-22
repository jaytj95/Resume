package com.jasonjohn.resume;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class DetailActivity extends Activity {
    private TextView workTitle, workCompany;
    private TextView roleHeader, roleContent1, roleContent2;
    private ImageView workPic;
    private CarouselView carouselView;
    int[] images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Object o = getIntent().getParcelableExtra("obj");

        if(o instanceof WorkExpObject) {
            setContentView(R.layout.activity_detail);
            WorkExpObject workExpObject = (WorkExpObject) o;

            workTitle = (TextView) findViewById(R.id.title);
            workCompany = (TextView) findViewById(R.id.date);
            workPic = (ImageView) findViewById(R.id.bgImg);

            roleHeader = (TextView) findViewById(R.id.role_header);
            roleContent1 = (TextView) findViewById(R.id.role_content1);
            roleContent2 = (TextView) findViewById(R.id.role_content2);

            workTitle.setText(workExpObject.getTitle());
            workCompany.setText(workExpObject.getCompany());
            Picasso.with(getApplicationContext()).load(workExpObject.getBgResId()).into(workPic);

            View[] textViews = new View[]{roleContent1, roleContent2};
            for(int i = 0; i < textViews.length; i++) {
                if(i < workExpObject.getTexts().length) {
                    ((TextView)textViews[i]).setText(createBulletString(getResources().getString(workExpObject.getTexts()[i])));
                } else {
                    textViews[i].setVisibility(View.GONE);
                }
            }
        } else {
            setContentView(R.layout.activity_detail_proj);
            ProjExpObject projExpObject = (ProjExpObject) o;
            images = ((ProjExpObject) o).getPics();

            workTitle = (TextView) findViewById(R.id.title);
            workCompany = (TextView) findViewById(R.id.date);
            workPic = (ImageView) findViewById(R.id.bgImg);

            roleHeader = (TextView) findViewById(R.id.role_header);
            roleContent1 = (TextView) findViewById(R.id.role_content1);

            workTitle.setText(projExpObject.getTitle());
            workCompany.setText(projExpObject.getDate());
            Picasso.with(getApplicationContext()).load(projExpObject.getBgResId()).into(workPic);
            roleContent1.setText(getResources().getString(projExpObject.getAbout()));


            carouselView = (CarouselView) findViewById(R.id.carouselView);
            carouselView.setVisibility(View.VISIBLE);
            if(images!= null && images.length > 0) {
                carouselView.setPageCount(images.length);
                carouselView.setImageListener(imageListener);
            }

        }


    }

    private Spanned createBulletString(String text) {
        return Html.fromHtml("&#8226; " + text);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(images[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        }
    };
}
