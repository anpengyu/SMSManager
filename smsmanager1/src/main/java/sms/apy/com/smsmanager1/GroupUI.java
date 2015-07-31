package sms.apy.com.smsmanager1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by apy on 15/7/30.
 */
public class GroupUI extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("群组");
        setContentView(tv);
    }
}
