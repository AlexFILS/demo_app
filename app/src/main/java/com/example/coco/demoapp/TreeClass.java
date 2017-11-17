package com.example.coco.demoapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Coco on 16.11.2017.
 */


    public class TreeClass<T> {
        private List<TreeClass<T>> children = new ArrayList<TreeClass<T>>();
        private TreeClass<T> parent = null;
        private T data = null;

        public TreeClass(T data) {
            this.data = data;
        }

        public TreeClass(T data, TreeClass<T> parent) {
            this.data = data;
            this.parent = parent;
        }

        public List<TreeClass<T>> getChildren() {
            return children;
        }

        public void setParent(TreeClass<T> parent) {
            parent.addChild(this);
            this.parent = parent;
        }

        public void addChild(T data) {
            TreeClass<T> child = new TreeClass<T>(data);
            child.setParent(this);
            this.children.add(child);
        }

        public void addChild(TreeClass<T> child) {
            child.setParent(this);
            this.children.add(child);
        }

        public T getData() {
            return this.data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public boolean isRoot() {
            return (this.parent == null);
        }

        public boolean isLeaf() {
            if(this.children.size() == 0)
                return true;
            else
                return false;
        }

        public void removeParent() {
            this.parent = null;
        }

}
