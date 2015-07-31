package com.apy.sms;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TabHost;


public class MainActivity extends TabActivity implements View.OnClickListener {
    private TabHost mTabHost;
    private View llConversation;
    private View mSlidVview;
    private int basicWidth = 0;
    private int startX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initTabHost();
    }

    private void initTabHost() {
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mSlidVview = findViewById(R.id.slide_view);
        llConversation = findViewById(R.id.ll_conversation);
        llConversation.setOnClickListener(this);
        findViewById(R.id.ll_floder).setOnClickListener(this);
        findViewById(R.id.ll_group).setOnClickListener(this);

        //全局布局完成后的回调
        llConversation.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llConversation.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mSlidVview.getLayoutParams();
                layoutParams.width = llConversation.getWidth();
                layoutParams.height = llConversation.getHeight();
                layoutParams.leftMargin = llConversation.getLeft();
                mSlidVview.setLayoutParams(layoutParams);

                basicWidth = findViewById(R.id.rl_conversation).getWidth();
            }
        });


        addTabSpace("conversation", "会话", R.mipmap.tab_conversation, new Intent(this, ConversationUI.class));
        addTabSpace("floder", "文件夹", R.mipmap.tab_folder, new Intent(this, FolderUI.class));
        addTabSpace("group", "群组", R.mipmap.tab_group, new Intent(this, GroupUI.class));


    }

    private void addTabSpace(String tag, String lable, int icon, Intent intent) {
        TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tag);
        tabSpec.setIndicator(lable, getResources().getDrawable(icon));
        tabSpec.setContent(intent);
        mTabHost.addTab(tabSpec);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_conversation:
                if (!"conversation".equals(mTabHost.getCurrentTabTag())) {
                    mTabHost.setCurrentTabByTag("conversation");
                    startTranslateAnimation(startX, 0);
                    startX = 0;
                }
                break;
            case R.id.ll_floder:
                if(!"floder".equals(mTabHost.getCurrentTabTag())){
                mTabHost.setCurrentTabByTag("floder");
                startTranslateAnimation(startX, basicWidth);
                startX = basicWidth;}
                break;
            case R.id.ll_group:
                if(!"group".equals(mTabHost.getCurrentTabTag())){
                mTabHost.setCurrentTabByTag("group");
                startTranslateAnimation(startX, basicWidth * 2);
                startX = basicWidth * 2;}
                break;
        }
    }

    private void startTranslateAnimation(int fromXDelta, int toXDelta) {
        TranslateAnimation ta = new TranslateAnimation(fromXDelta, toXDelta, 0, 0);
        ta.setDuration(300);
        ta.setFillAfter(true);
        mSlidVview.startAnimation(ta);
    }

}
