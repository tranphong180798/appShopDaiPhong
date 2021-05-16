package tranphong.com.thuchanhandroidcuoikhoa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import tranphong.com.thuchanhandroidcuoikhoa.R;
import tranphong.com.thuchanhandroidcuoikhoa.activity.CartActicity;
import tranphong.com.thuchanhandroidcuoikhoa.activity.DiscountActivity;
import tranphong.com.thuchanhandroidcuoikhoa.activity.ProductDetailActivity;
import tranphong.com.thuchanhandroidcuoikhoa.model.Comment;
import tranphong.com.thuchanhandroidcuoikhoa.model.Discount;

public class DiscountAdapter extends BaseAdapter {
    int x = 0;
    private DiscountActivity context;
    private int layout;
    private ArrayList<Discount> arrayComment;

    public DiscountAdapter(DiscountActivity context, int layout, ArrayList<Discount> arrayComment) {
        this.context = context;
        this.layout = layout;
        this.arrayComment = arrayComment;
    }

    @Override
    public int getCount() {
        return arrayComment.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {

        public CheckBox textViewName;

    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        DiscountAdapter.ViewHolder holder;
        if (view == null) {
            holder = new DiscountAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.textViewName =  view.findViewById(R.id.checkboxDiscount);


            view.setTag(holder);
        } else {
            holder = (DiscountAdapter.ViewHolder) view.getTag();
        }
        final Discount discount = arrayComment.get(i);

        holder.textViewName.setText(discount.getName());

        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.textViewName.isChecked())
                {
                    context.discountSanpham(discount.getGiadiscount());
                }
            }
        });


        return view;
    }
}
