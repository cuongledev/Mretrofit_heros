package com.hstc.lengoccuong.mretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HeroAdapter  extends BaseAdapter {

    private Context mContext;

    private ArrayList<Hero> listHero;
    private LayoutInflater inflater;

    public HeroAdapter(Context context, ArrayList<Hero> listHero){
        this.listHero = listHero;
        this.mContext = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return listHero.size();
    }

    @Override
    public Hero getItem(int position) {
        return listHero.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_heroes,parent,false);
            viewHolder.ivAvatar = convertView.findViewById(R.id.img_hero);
            viewHolder.tvName = convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Hero hero = listHero.get(position);
        Picasso.with(mContext).load(hero.getImageurl()).into(viewHolder.ivAvatar);
        viewHolder.tvName.setText(hero.getName());




        return convertView;
    }
    private class ViewHolder{
        ImageView ivAvatar;
        TextView tvName;
    }


}
