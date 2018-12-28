package com.mospro.cafehouse.LiveActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mospro.cafehouse.R;

import java.util.ArrayList;

public class TablesAdapter extends RecyclerView.Adapter <TablesAdapter.TableViewHolder> {
    private ArrayList<Integer> integers;
    private Context context ;
    TablesAdapter(ArrayList<Integer> numbers , Context context)
    {
        this.integers = numbers ;
        this.context = context ;
        Log.i("adapter", "TablesAdapter: ");
    }
    @NonNull
    @Override
    public TablesAdapter.TableViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater= LayoutInflater.from(context) ;
        View view = layoutInflater.inflate(R.layout.tables_layout , null );
        return new TableViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TablesAdapter.TableViewHolder tableViewHolder, int i) {
        String table ="table "+ integers.get(i).intValue();
        tableViewHolder.tv.setText(table);
    }
    @Override
    public int getItemCount() {
        return integers.size();
    }
    class TableViewHolder extends RecyclerView.ViewHolder {
       // RecyclerView recyclerView ;
        TextView tv ;
        public TableViewHolder(@NonNull View itemView) {
            super(itemView);
          //  recyclerView=(RecyclerView)itemView.findViewById(R.id.recycler_table_TablesLayout);
            tv=(TextView)itemView.findViewById(R.id.tv_table_tablesLayout);
        }
    }
}
