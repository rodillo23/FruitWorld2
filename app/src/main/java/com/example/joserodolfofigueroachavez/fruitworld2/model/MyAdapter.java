package com.example.joserodolfofigueroachavez.fruitworld2.model;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.example.joserodolfofigueroachavez.fruitworld2.R;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.joserodolfofigueroachavez.fruitworld2.adapter.Fruit;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {

    private List<Fruit> fruits;
    private int layout;
    private Activity activity;
    private OnItemClickListener listener;

    public MyAdapter(List<Fruit> fruits, int layout, Activity activity, OnItemClickListener listener) {
        this.fruits = fruits;
        this.layout = layout;
        this.activity = activity;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bind(fruits.get(position));
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        public TextView name;
        public TextView description;
        public TextView quantity;
        public ImageView imageBackground;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.fruitName);
            description = (TextView) itemView.findViewById(R.id.fruitDescription);
            quantity = (TextView) itemView.findViewById(R.id.fruitQuantity);
            imageBackground = (ImageView) itemView.findViewById(R.id.imageViewBackground);

            itemView.setOnCreateContextMenuListener(this);
        }

        public void bind(final Fruit fruit) {
            this.name.setText(fruit.getName());
            this.description.setText(fruit.getDescription());
            this.quantity.setText(fruit.getQuantity() + "");

            if (fruit.getQuantity() == Fruit.LIMIT_QUANTITY){
                quantity.setTextColor(ContextCompat.getColor(activity, R.color.colorAlert));
                quantity.setTypeface(null, Typeface.BOLD);
            }else{
                quantity.setTextColor(ContextCompat.getColor(activity, R.color.defaultTextColor));
                quantity.setTypeface(null, Typeface.NORMAL);
            }

            Picasso.get().load(fruit.getImgBackround()).fit().into(imageBackground);

            imageBackground.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(fruit, getAdapterPosition());
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            Fruit fruitSelected = fruits.get(this.getAdapterPosition());

            menu.setHeaderTitle(fruitSelected.getName());
            menu.setHeaderIcon(fruitSelected.getImgIcon());
            activity.getMenuInflater().inflate(R.menu.context_menu_fruit, menu);

            for (int i = 0; i < menu.size(); i++){
                menu.getItem(i).setOnMenuItemClickListener(this);
            }


        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()){

                case R.id.delte_fruit:
                    fruits.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;


                case R.id.reset_fruit_quantity:
                    fruits.get(getAdapterPosition()).resetQuanttity();
                    notifyItemChanged(getAdapterPosition());
                    return true;

                default:

                    return false;



            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Fruit fruit, int position);
    }
}
