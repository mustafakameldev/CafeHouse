package com.mospro.cafehouse.MenuActivity;
import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.mospro.cafehouse.DataTypes.Item;
import com.mospro.cafehouse.R;
import java.util.ArrayList;
import java.util.List;
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ProductViewHolder> {
    private Context context ;
    private ArrayList<Item> items ;
    public MenuAdapter(Context mCntx, ArrayList<Item> productList) {
        this.context = mCntx;
        this.items = productList;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater= LayoutInflater.from(context) ;
        View view = layoutInflater.inflate(R.layout.product_layout , null );
        return new ProductViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder productViewHolder, int i) {
        Item item = items.get(i);
        productViewHolder.name.setText(item.getName());
        productViewHolder.price.setText(item.getPrice().toString());
        productViewHolder.incr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(productViewHolder.itemsNo.getText().toString()))
                {
                    Double no = Double.valueOf(productViewHolder.itemsNo.getText().toString());
                    no =no+1 ;
                    productViewHolder.itemsNo.setText(no.toString());
                }
            }
        });

        productViewHolder.decr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(productViewHolder.itemsNo.getText().toString()))
                {
                    Double no = Double.valueOf(productViewHolder.itemsNo.getText().toString());
                    if (no == 0)
                    {
                    }else if(no > 0) {
                        no = no - 1;
                    }
                    productViewHolder.itemsNo.setText(no.toString());
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {


        ImageButton incr , decr ;
        TextView name , price ;
        EditText itemsNo ;
        ImageView img ;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            incr =(ImageButton)itemView.findViewById(R.id.incrBtn_ProductLayout);
            decr =(ImageButton)itemView.findViewById(R.id.decrBtn_ProductLayout);
            name =(TextView) itemView.findViewById(R.id.tv_name_ProductLayout);
            price =(TextView) itemView.findViewById(R.id.tv_price_ProductLayout);
            itemsNo =(EditText)itemView.findViewById(R.id.editText_ProductNo_ProductLayout) ;

        }
    }
}
