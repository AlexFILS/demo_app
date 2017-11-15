package com.example.coco.demoapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Coco on 15.11.2017.
 */

public class ObjectAdapter extends RecyclerView.Adapter<ObjectAdapter.MyViewHolder>{
List<ObjectsMainList> object_list=  new ArrayList<>();

public ObjectAdapter(List objectList){
    this.object_list=objectList;

}


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);
    return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    ObjectsMainList listObject= object_list.get(position);
    holder.WO.setText(listObject.getWO());
    holder.type.setText(listObject.getType());
    holder.description.setText(listObject.getDescription());
    }

    @Override
    public int getItemCount() {
        return object_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView WO,description,type;
        public MyViewHolder(View itemView) {
            super(itemView);
            WO=itemView.findViewById(R.id.Wo);
            type=itemView.findViewById(R.id.type);
            description=itemView.findViewById(R.id.description);

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
