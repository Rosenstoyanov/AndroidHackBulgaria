package com.example.rosen.sampleexpenselist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rosen on 22.11.14.
 */
public class ExpenceListAdapter extends BaseAdapter{
    private List<ExpenceProduct> mExpenceList;

    //ExpenceListAdapter(List<ExpenceProduct> expenceList)
    ExpenceListAdapter()
    {
        mExpenceList = new ArrayList<ExpenceProduct>();
    }
    public void add(ExpenceProduct expenceProduct)
    {
        this.mExpenceList.add(expenceProduct);
    }
    @Override
    public int getCount() {
        return mExpenceList.size();
    }

    @Override
    public Object getItem(int position) {
        return mExpenceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup viewGroup) {
        final ViewHolder holder;
        LinearLayout layout = null;
        if (convertView != null)
        {
	        layout = (LinearLayout) convertView;
            holder = (ViewHolder) convertView.getTag();
        }else {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            layout = (LinearLayout) inflater.inflate(R.layout.list_item_expence_product,viewGroup,false);

            holder  = new ViewHolder(layout);
            layout.setTag(holder);
        }

        ExpenceProduct expenceProduct = (ExpenceProduct) getItem(position);

        holder.mLabelTextView.setText(expenceProduct.getLabel());
        holder.mPriceTextView.setText(expenceProduct.getPrice());
        holder.mButtonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View layout) {
                AlertDialog.Builder builder = new AlertDialog.Builder(viewGroup.getContext());
                AlertDialog dialog = builder.setTitle("Dialog").setPositiveButton("Yess", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mExpenceList.remove(position);
                        notifyDataSetChanged();
                    }
                }).setNegativeButton("No", null).create();
                dialog.show();
            }
        });
        return layout;
    }
    private class ViewHolder
    {
        TextView mLabelTextView;
        TextView mPriceTextView;
        Button mButtonDel;

        ViewHolder(View view) {
            mLabelTextView = (TextView) view.findViewById(R.id.textViewLabel);
            mPriceTextView = (TextView) view.findViewById(R.id.textViewPrice);
            mButtonDel = (Button) view.findViewById(R.id.btnDel);
        }
    }
}
