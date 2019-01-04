package com.example.joserodolfofigueroachavez.fruitworld2.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.joserodolfofigueroachavez.fruitworld2.R;
import com.example.joserodolfofigueroachavez.fruitworld2.adapter.Fruit;
import com.example.joserodolfofigueroachavez.fruitworld2.model.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Fruit> fruits;
    private MyAdapter adapter;
    private int counter = 0;
    LinearLayoutManager llm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fruits = getAllFruits();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        adapter = new MyAdapter(fruits, R.layout.recyclerview_item, this, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Fruit fruit, int position) {
                fruit.addQuantity(1);
                adapter.notifyItemChanged(position);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.add_fruit:
                int position = adapter.getItemCount();
                fruits.add(position, new Fruit("New Fruit No. " + (++counter), "Added by user", R.drawable.plum_bg, R.mipmap.plum, 0, this));
                adapter.notifyItemInserted(position);
                llm.scrollToPosition(position);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private List<Fruit> getAllFruits() {
        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit("Apple", "Apple Description", R.drawable.apple_bg, R.mipmap.apple, 0, this));
        fruits.add(new Fruit("Banana", "Banana Description", R.drawable.banana_bg, R.mipmap.ic_banana, 0, this));
        fruits.add(new Fruit("Cherry", "Cherry Description", R.drawable.cherry_bg, R.mipmap.ic_cherry, 0, this));
        fruits.add(new Fruit("Orange", "Orange Description", R.drawable.orange_bg, R.mipmap.ic_orange, 0, this));
        fruits.add(new Fruit("Pear", "Pear Description", R.drawable.pear_bg, R.mipmap.ic_pear, 0, this));
        fruits.add(new Fruit("Plum", "Plum Description", R.drawable.plum_bg, R.mipmap.ic_plum, 0, this));
        fruits.add(new Fruit("Raspberry", "Raspberry Description", R.drawable.raspberry_bg, R.mipmap.ic_raspberry, 0, this));
        fruits.add(new Fruit("Strawberry", "Strawberry Description", R.drawable.strawberry_bg, R.mipmap.ic_strawberry, 0, this));
        return fruits;
    }
}
