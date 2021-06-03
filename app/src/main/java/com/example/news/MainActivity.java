package com.example.news;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.image.SmartImageView;

import java.nio.charset.StandardCharsets;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private LinearLayout loading;
    private ListView lvNews;
    private List<NewsInfo> newsInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fillData();
    }

    //初始化控件
    private void initView() {
        loading = findViewById(R.id.loading);
        lvNews = findViewById(R.id.lv_news);
    }

    //使用AsyncHttpClient访问网络
    private void fillData() {
        //创建AsyncHttpClient实例
        AsyncHttpClient client = new AsyncHttpClient();
        //使用GET方式请求
        client.get("https://raw.fastgit.org/fan1138612367/News/master/News.json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //请求成功
                try {
                    String json = new String(responseBody, StandardCharsets.UTF_8);
                    newsInfos = JsonParse.getNewsInfo(json);
                    if (newsInfos == null) {
                        Toast.makeText(MainActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
                    } else {
                        //更新界面
                        loading.setVisibility(View.INVISIBLE);
                        lvNews.setAdapter(new NewsAdapter());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    //ListView适配器
    private class NewsAdapter extends BaseAdapter {
        //listview的item数
        @Override
        public int getCount() {
            return newsInfos.size();
        }

        //得到listview条目视图
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            @SuppressLint("ViewHolder") View view = View.inflate(MainActivity.this, R.layout.news_item, null);
            SmartImageView siv = view.findViewById(R.id.siv_icon);
            TextView tv_title = view.findViewById(R.id.tv_title);
            TextView tv_description = view.findViewById(R.id.tv_description);
            NewsInfo newsInfo = newsInfos.get(position);
            //SmartImageView加载指定路径图片
            siv.setImageUrl(newsInfo.getIcon(), R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground);
            //设置新闻标题
            tv_title.setText(newsInfo.getTitle());
            //设置新闻描述
            tv_description.setText(newsInfo.getContent());
            //1.一般新闻 2.专题 3.live
            int type = newsInfo.getType();
            //设置每项图片标记
            siv.setTag(type);
            siv.setOnClickListener(v -> {
                int tag = (Integer) siv.getTag();
                //点击不同项进入到不同页面
                switch (tag) {
                    case 1:
                        startActivity(new Intent(MainActivity.this, MusicActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, VideoActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, PhotoActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, ClassActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, TweenActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, FrameActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, DrawerActivity.class));
                        break;
                }
            });
            return view;
        }

        //条目对象
        @Override
        public Object getItem(int position) {
            return null;
        }

        //条目id
        @Override
        public long getItemId(int position) {
            return 0;
        }
    }
}