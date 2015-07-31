package sms.apy.com.smsmanager2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by apy on 15/7/31.
 */
public class FolderUI extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setText("文件夹");
        setContentView(tv);
    }
}
