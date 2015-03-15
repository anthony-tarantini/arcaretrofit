package io.tony.arcaretrofit.binders;

import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import io.pivotal.arca.adapters.Binding;
import io.pivotal.arca.adapters.ViewBinder;
import io.tony.arcaretrofit.R;
import io.tony.arcaretrofit.utils.ImageUtil;

public class CardListFragmentViewBinder implements ViewBinder {

    @Override
    public boolean setViewValue(View view, Cursor cursor, Binding binding) {
        switch (view.getId()) {
            case R.id.list_item_card_image: {
                final ImageView imageView = (ImageView) view;
                final int id = cursor.getInt(binding.getColumnIndex());
                Picasso.with(view.getContext()).load(ImageUtil.getImageUrl(id)).into(imageView);
                return true;
            }
        }
        return false;
    }
}
