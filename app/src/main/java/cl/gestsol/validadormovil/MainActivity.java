package cl.gestsol.validadormovil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagen = (ImageView) findViewById(R.id.imageView);
        imagen.setImageResource(R.drawable.ic_logo_inveb_header);

        IntentFilter filter = new IntentFilter();
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        filter.addAction(getResources().getString(R.string.activity_intent_filter_action));
        registerReceiver(myBroadcastReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcastReceiver);
    }
    //
    // After registering the broadcast receiver, the next step (below) is to define it.
    // Here it's done in the MainActivity.java, but also can be handled by a separate class.
    // The logic of extracting the scanned data and displaying it on the screen
    // is executed in its own method (later in the code). Note the use of the
    // extra keys defined in the strings.xml file.
    //
    private BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(getResources().getString(R.string.activity_intent_filter_action))) {

                try {
                    displayScanResult(intent, "via Broadcast");
                } catch (Exception e) {
                    //
                    // Catch if the UI does not exist when broadcast is received
                    //
                }
            }
        }
    };

    //
    // The section below assumes that a UI exists in which to place the data. A production
    // application would be driving much of the behavior following a scan.
    //
    private void displayScanResult(Intent initiatingIntent, String howDataReceived) {
        String decodedSource = initiatingIntent.getStringExtra(getResources().getString(R.string.datawedge_intent_key_source));
        final TextView lblScanSource = (TextView) findViewById(R.id.textView);
        lblScanSource.setText("Tu codigo es: " + decodedSource);

    }
}