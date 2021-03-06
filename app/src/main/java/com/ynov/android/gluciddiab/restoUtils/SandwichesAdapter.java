package com.ynov.android.gluciddiab.restoUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.ynov.android.gluciddiab.R;

/**
 * Created by Malo on 05/04/2017.
 */

public class SandwichesAdapter {
    private Context kContext;

    public SandwichesAdapter(Context c) {
        kContext = c;
    }

    public int getCount() {
        return kSandwichIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(kContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(kSandwichIds[position]);
        return imageView;
    }

    private Integer[] kSandwichIds = {
            R.drawable.kfckrunchy,R.drawable.kfctwistertenders,
            R.drawable.kfcbossbacon,R.drawable.kfcbrazer,
            R.drawable.kfctower,R.drawable.kfctower,
            R.drawable.kfcboxmaster,R.drawable.kfcboxmaster,
            R.drawable.kfcboxmaster,R.drawable.kfcboxmaster,
            R.drawable.kfcboxmaster,R.drawable.kfcitwist,
            R.drawable.kfcitwist,R.drawable.nopicture,
            R.drawable.kfcdoublestacker,R.drawable.kfcdoublestacker
    };

}
