package hupu.gzsll.com.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter2 extends BaseAdapter {
     private Context context;
     private List<String> list;
    private ListView lvChild;

    public MyAdapter2(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(parent.getContext(),R.layout.child_item,null);
        }
        String childtext = list.get(position);
        ((TextView) convertView.findViewById(R.id.tv)).setText(childtext);
        return convertView;
    }
}
