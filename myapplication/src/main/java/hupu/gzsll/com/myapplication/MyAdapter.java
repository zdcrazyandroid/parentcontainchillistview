package hupu.gzsll.com.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
     private Context context;
     private List<String> list;
    private ListView lvChild;

    public MyAdapter(Context context, List<String> list) {
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
            convertView=View.inflate(parent.getContext(),R.layout.parent_item,null);
        }
        String childtext = list.get(position);
        ((TextView) convertView.findViewById(R.id.tv)).setText(childtext);
        List list2 = new ArrayList<>();
        for (int i = 0; i <10; i++) {
            list2.add("儿子第"+i+"条");
        }
        lvChild = ((SubListView) convertView.findViewById(R.id.lv_child));
        lvChild.setAdapter(new MyAdapter2(context,list));
        return convertView;
    }
}
