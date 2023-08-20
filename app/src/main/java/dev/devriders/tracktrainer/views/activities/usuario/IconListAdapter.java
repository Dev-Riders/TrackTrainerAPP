package dev.devriders.tracktrainer.views.activities.usuario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import dev.devriders.tracktrainer.R;

public class IconListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;
    private final int[] icons;

    public IconListAdapter(Context context, String[] values, int[] icons) {
        super(context, R.layout.list_item_with_icon, values);
        this.context = context;
        this.values = values;
        this.icons = icons;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_with_icon, parent, false);
        TextView textView = rowView.findViewById(R.id.item_text);
        ImageView imageView = rowView.findViewById(R.id.item_icon);

        textView.setText(values[position]);
        imageView.setImageResource(icons[position]);

        return rowView;
    }
}
