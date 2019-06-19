package com.example.duoplus.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duoplus.R;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;
    private final int listView;
    private final String listviewName;
    private String[] apelido;
    private String[] tipoDisp;
    private Integer[] quantidadeDisp;

    public CustomListAdapter(Activity context, String[] itemname, Integer[] imgid, int listV, String name) {
        super(context, listV, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
        this.listView=listV;
        this.listviewName=name;
    }

    public CustomListAdapter(Activity context,
                             String[] itemname,
                             Integer[] imgid,
                             String[] apelido,
                             String[] tipoDisp,
                             Integer[] quantidadeDisp,
                             int listV,
                             String name) {
        super(context, listV, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
        this.listView=listV;
        this.listviewName=name;
        this.apelido=apelido;
        this.tipoDisp=tipoDisp;
        this.quantidadeDisp=quantidadeDisp;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(listView, null,true);

        if (listviewName.equals("environment")) {

            TextView txtTitle = (TextView) rowView.findViewById(R.id.itemName);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

            txtTitle.setText(itemname[position]);
            imageView.setImageResource(imgid[position]);

            return rowView;
        } else if (listviewName.equals("myenvironment")) {
            TextView txtMyEnvType = (TextView) rowView.findViewById(R.id.txtMyEnvType);
            TextView txtMyEnvName = (TextView) rowView.findViewById(R.id.txtMyEnvName);
            TextView txtMyDevType = (TextView) rowView.findViewById(R.id.txtMyDevType);
            TextView txtMyEnvQtd = (TextView) rowView.findViewById(R.id.txtMyEnvQtd);
            ImageView imgMyEnv = (ImageView) rowView.findViewById(R.id.imgMyEnv);

            txtMyEnvType.setText(itemname[position]);
            txtMyEnvName.setText(apelido[position]);
            txtMyDevType.setText(tipoDisp[position]);
            txtMyEnvQtd.setText(quantidadeDisp[position].toString());
            imgMyEnv.setImageResource(imgid[position]);

            return rowView;
        } else {
            return rowView;
        }
    };
}