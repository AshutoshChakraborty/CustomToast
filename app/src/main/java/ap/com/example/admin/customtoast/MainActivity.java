package ap.com.example.admin.customtoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomToast.show(this,"Hello world",CustomToast.TYPE_INFO);
    }

    public void infoclick(View view) {
        CustomToast.show(this,"Hello world",CustomToast.TYPE_INFO);
    }

    public void successonclik(View view) {
        CustomToast.show(this,"Hello world",CustomToast.TYPE_SUCCESS);
    }

    public void warnOnClik(View view) {
        CustomToast.show(this,"Hello world",CustomToast.TYPE_ERROR);
    }

    public void longToastOnclick(View view) {
        CustomToast.show(this,"Hello world",Toast.LENGTH_LONG,CustomToast.TYPE_INFO);
    }
}
