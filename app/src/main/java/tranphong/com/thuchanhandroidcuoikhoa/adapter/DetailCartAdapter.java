package tranphong.com.thuchanhandroidcuoikhoa.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import tranphong.com.thuchanhandroidcuoikhoa.R;
import tranphong.com.thuchanhandroidcuoikhoa.activity.CartActicity;
import tranphong.com.thuchanhandroidcuoikhoa.activity.HistoryActivity;
import tranphong.com.thuchanhandroidcuoikhoa.model.Cart;
import tranphong.com.thuchanhandroidcuoikhoa.model.DetailCart;

public class DetailCartAdapter extends BaseAdapter {
    int x = 0;
    private HistoryActivity context;
    private int layout;
    private List<DetailCart> arraListProduct;

    public DetailCartAdapter(HistoryActivity context, int layout, List<DetailCart> subjectsList) {
        this.context = context;
        this.layout = layout;
        this.arraListProduct = subjectsList;
    }

    @Override
    public int getCount() {
        return arraListProduct.size();
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
        CheckBox checkBox;
        public TextView textViewNameSanPham, textViewPrice, textViewngaymuahang,txtsoluongdamua;
        public ImageView imageViewSanPham;
        public Button btnCong,btnTru;
        LinearLayout dongcartne;

    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        DetailCartAdapter.ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new DetailCartAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);

            viewHolder.imageViewSanPham = view.findViewById(R.id.imageHinhSanPhamne);
            viewHolder.textViewNameSanPham = view.findViewById(R.id.textViewNameSanPhamne);
            viewHolder.textViewngaymuahang =  view.findViewById(R.id.textviewngaymuahang);
            viewHolder.textViewPrice = view.findViewById(R.id.textviewGiaSanPhamne);

            viewHolder.txtsoluongdamua      =view.findViewById(R.id.textviewSoluongdamuane);

            viewHolder.dongcartne       =view.findViewById(R.id.dongcartnene);
            view.setTag(viewHolder);
        }else {
            viewHolder = (DetailCartAdapter.ViewHolder) view.getTag();
        }
        final DetailCart cart = (DetailCart) arraListProduct.get(i);
        viewHolder.textViewNameSanPham.setText("Sản phẩm : "+cart.getTensanpham());
        viewHolder.textViewngaymuahang.setText("Ngày mua hàng : "+cart.getNgaymuahang());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.textViewPrice.setText("Price : " + decimalFormat
                .format(cart.getGiasanpham()) + " VND");

        viewHolder.txtsoluongdamua.setText( "Số lượng đã mua : "+cart.getSoluong()+"");

        Picasso.with(context).load(cart.getHinhanhsanpham())
                .into(viewHolder.imageViewSanPham);



//        if(context.checkCheckBoxTatCa(1))
//        {
//            viewHolder.checkBox.setChecked(false);
//        }











        return view;
    }


}
