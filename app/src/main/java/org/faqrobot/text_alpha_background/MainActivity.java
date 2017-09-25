package org.faqrobot.text_alpha_background;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.app.hubert.library.Controller;
import com.app.hubert.library.HighLight;
import com.app.hubert.library.NewbieGuide;
import com.app.hubert.library.OnGuideChangedListener;

public class MainActivity extends AppCompatActivity {

    TextView textView1,textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        clickView();
    }

    private void clickView() {
        findViewById(R.id.button_show_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewbieGuide.with(MainActivity.this)//传入activity
                        .setLabel("guide1")//设置引导层标示，用于区分不同引导层，必传！否则报错
                        .addHighLight(textView1, HighLight.Type.RECTANGLE)//添加需要高亮的view
                        .setLayoutRes(R.layout.layout)//自定义的提示layout，不要添加背景色，引导层背景色通过setBackgroundColor()设置
                        .show();//显示引导层
            }
        });
        findViewById(R.id.button_show_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**显示2个*/
                add_two_guide();
            }
        });
    }

    private void add_two_guide() {
        NewbieGuide.with(MainActivity.this)//传入activity
                .setLabel("guide2")//设置引导层标示，用于区分不同引导层，必传！否则报错
                .addHighLight(textView1, HighLight.Type.RECTANGLE)//添加需要高亮的view
                .setLayoutRes(R.layout.layout)//自定义的提示layout，不要添加背景色，引导层背景色通过setBackgroundColor()设置
                .setOnGuideChangedListener(new OnGuideChangedListener() {
                    //引导层显示
                    @Override
                    public void onShowed(Controller controller) {

                    }
                    //引导层消失
                    @Override
                    public void onRemoved(Controller controller) {
                        NewbieGuide.with(MainActivity.this)//传入activity
                                .setLabel("guide3")//设置引导层标示，用于区分不同引导层，必传！否则报错
                                .addHighLight(textView2, HighLight.Type.RECTANGLE)//添加需要高亮的view
                                .setLayoutRes(R.layout.layout1)//自定义的提示layout，不要添加背景色，引导层背景色通过setBackgroundColor()设置
                                .show();//显示引导层
                    }
                })
                .setBackgroundColor(0xb2000000)//设置引导层背景色，建议有透明度，默认背景色为：0xb2000000
                .setEveryWhereCancelable(true)//设置点击任何区域消失，默认为true
                .alwaysShow(false)//是否每次都显示引导层，默认false
//                .setLayoutRes(R.layout.layout, R.id.textview1)//自定义的提示layout,第二个可变参数为点击隐藏引导层view的id
                .show();
                 /**隐藏引导层*/
                 //controller.remove();
    }

    private void initView() {
        textView1 = (TextView) findViewById(R.id.textview1);
        textView2 = (TextView) findViewById(R.id.textview2);
    }
}
