package com.example.ikhan.procrastinationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

//import android.support.v7.app.AppCompactActivity;


public class UserActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user);

        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String username=intent.getStringExtra("username");
        String message=name + "'s Apps";
        TextView welcome=(TextView)findViewById(R.id.welcome);
        //welcome.setText(message);

        //GridView gridView = (GridView) findViewById(R.id.gridview);
        //gridView.setAdapter(new ImageAdapter(this));

        //switches

        final Button bSave=(Button)findViewById(R.id.bSave);
        //bSave.setOnClickListener(new View.OnClickListener(){}
    }

    /*public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c){
            mContext = c;
        }

        public int getCount(){
            return mThumbIds.length;
        }

        public Object getItem(int position){
            return null;
        }

        public long getItemId(int position){
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(3, 3, 3, 3);
            imageView.setImageDrawable(getResources().getDrawable(mThumbIds[position]));
            return imageView;
        }

        private Integer[] mThumbIds = {
                R.drawable.facebook, R.drawable.toggle,
                R.drawable.twitter2, R.drawable.toggle,
                R.drawable.instagram, R.drawable.toggle,
                R.drawable.snapchat, R.drawable.toggle,
                R.drawable.pinterest, R.drawable.toggle,
                R.drawable.reddit, R.drawable.toggle,
                R.drawable.tumblr, R.drawable.toggle
        };


    }*/
}
