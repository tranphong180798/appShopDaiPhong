package tranphong.com.thuchanhandroidcuoikhoa.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import tranphong.com.thuchanhandroidcuoikhoa.R;
import tranphong.com.thuchanhandroidcuoikhoa.activity.MainActivity;
import tranphong.com.thuchanhandroidcuoikhoa.activity.ProductDetailActivity;
import tranphong.com.thuchanhandroidcuoikhoa.model.Product;

public class DetailProductAdapter extends BaseAdapter {
    int x = 0;
    private ProductDetailActivity context;
    private int layout;
    private ArrayList<Product> arrayProduct;

    public DetailProductAdapter(ProductDetailActivity context, int layout, ArrayList<Product> subjectsList) {
        this.context = context;
        this.layout = layout;
        this.arrayProduct = subjectsList;
    }

    @Override
    public int getCount() {
        return arrayProduct.size();
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
        public ImageView imageViewProduct;
        public TextView textViewNameProduct, textViewPriceProduct;

    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.imageViewProduct =  view.findViewById(R.id.imageViewProduct);
            holder.textViewNameProduct = view.findViewById(R.id.textViewNameProduct);
            holder.textViewPriceProduct =  view.findViewById(R.id.textViewPriceProduct);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final Product product = arrayProduct.get(i);
        int x=arrayProduct.get(i).getId();
        holder.textViewNameProduct.setText(product.getNameProduct());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.textViewPriceProduct.setText("Price : " + decimalFormat
                .format(product.getPriceProduct()) + "VND");
        Picasso.with(context).load(product.getImageProduct())
                .into(holder.imageViewProduct);

        return view;
    }
}
