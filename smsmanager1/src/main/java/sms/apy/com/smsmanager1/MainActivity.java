package sms.apy.com.smsmanager1;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TabHost;


public class MainActivity extends TabActivity implements View.OnClickListener {
    private TabHost mTabHost;
    private View slideView;
    private int basicWidth = 0;
    private int startX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initTabHost();
    }

    private void initTabHost() {
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        final View llConversation = findViewById(R.id.ll_conversation);
        llConversation.setOnClickListener(this);
        findViewById(R.id.ll_folder).setOnClickListener(this);
        findViewById(R.id.ll_group).setOnClickListener(this);

        slideView = findViewById(R.id.slide_view);

        llConversation.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llConversation.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) slideView.getLayoutParams();
                layoutParams.width = llConversation.getWidth();
                layoutParams.height = llConversation.getHeight();
                layoutParams.leftMargin = llConversation.getLeft();

                slideView.setLayoutParams(layoutParams);

                basicWidth = findViewById(R.id.rl_conversation).getWidth();
            }
        });


        addTabSpace("conversation", "会话", R.mipmap.tab_conversation, new Intent(this, ConversationUI.class));
        addTabSpace("folder", "文件夹", R.mipmap.tab_folder, new Intent(this, FolderUI.class));
        addTabSpace("group", "群组", R.mipmap.tab_group, new Intent(this, GroupUI.class));

    }

    private void addTabSpace(String tag, String label, int icon, Intent intent) {
        TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tag);
        tabSpec.setIndicator(label, getResources().getDrawable(icon));
        tabSpec.setContent(intent);

        mTabHost.addTab(tabSpec);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_conversation:
                if (!"conversation".equals(mTabHost.getTag())) {
                    mTabHost.setCurrentTabByTag("conversation");
                    animation(startX, 0);
                    startX = 0;
                }

                break;
            case R.id.ll_folder:
                if (!"folder".equals(mTabHost.getTag())) {
                    mTabHost.setCurrentTabByTag("folder");
                    animation(startX, basicWidth);
                    startX = basicWidth;
                }

                break;
            case R.id.ll_group:
                if (!"group".equals(mTabHost.getTag())) {
                    mTabHost.setCurrentTabByTag("group");
                    animation(startX, basicWidth * 2);
                    startX = basicWidth * 2;
                }
                break;
        }
    }

    private void animation(int fromXDelta, int toXDelta) {
        TranslateAnimation animation = new TranslateAnimation(fromXDelta, toXDelta, 0, 0);
        animation.setDuration(300);
        animation.setFillAfter(true);
        slideView.startAnimation(animation);
    }
}
