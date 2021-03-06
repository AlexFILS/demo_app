package com.example.coco.demoapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.coco.demoapp.Model.WoOverview;
import com.example.coco.demoapp.Objects.ObjectsMainList;
import com.example.coco.demoapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Coco on 15.11.2017.
 */

public class ObjectAdapter extends RecyclerView.Adapter<ObjectAdapter.MyViewHolder> {
    List<WoOverview> object_list = new ArrayList<>();


    public ObjectAdapter(List objectList) {
        this.object_list = objectList;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        WoOverview listObject = object_list.get(position);
        holder.WO.setText(listObject.get_id());
        holder.type.setText(listObject.getStatus());
        holder.description.setText(listObject.getDescription());
    }

    @Override
    public int getItemCount() {
        return object_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView WO, description, type;

        private MyViewHolder(View itemView) {
            super(itemView);
            WO = itemView.findViewById(R.id.Wo);
            type = itemView.findViewById(R.id.type);
            description = itemView.findViewById(R.id.description);

        }

        @Override
        public String toString() {
            return super.toString();
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
        }
    }

}
