package hupu.gzsll.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mLv;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getData();
    }

    private void getData() {
        list = new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
          list.add("第"+i+"条");
        }

        mLv.setAdapter(new MyAdapter(MainActivity.this, list));

    }

    private void initView() {
        mLv = ((ListView) findViewById(R.id.lv_parent));

    }
}
