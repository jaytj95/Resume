package com.jasonjohn.resume;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends Activity {
    private TextView workTitle, workCompany;
    private ImageView workPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        WorkExpObject workExpObject = getIntent().getParcelableExtra("weo");

        workTitle = (TextView) findViewById(R.id.title);
        workCompany = (TextView) findViewById(R.id.company);
        workPic = (ImageView) findViewById(R.id.bgImg);

        workTitle.setText(workExpObject.getTitle());
        workCompany.setText(workExpObject.getCompany());
    }
}
