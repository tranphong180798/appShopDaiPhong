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
import tranphong.com.thuchanhandroidcuoikhoa.model.Comment;
import tranphong.com.thuchanhandroidcuoikhoa.model.Product;

public class CommentAdapter extends BaseAdapter {
    int x = 0;
    private ProductDetailActivity context;
    private int layout;
    private ArrayList<Comment> arrayComment;

    public CommentAdapter(ProductDetailActivity context, int layout, ArrayList<Comment> arrayComment) {
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

        public TextView textViewName, textViewnoidung;

    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        CommentAdapter.ViewHolder holder;
        if (view == null) {
            holder = new CommentAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.textViewName =  view.findViewById(R.id.tenuserdacomment);
            holder.textViewnoidung = view.findViewById(R.id.noidungdacomment);

            view.setTag(holder);
        } else {
            holder = (CommentAdapter.ViewHolder) view.getTag();
        }
        final Comment comment = arrayComment.get(i);

        holder.textViewName.setText(comment.getUsername());

        holder.textViewnoidung.setText(comment.getContent()+"");

        return view;
    }
}
