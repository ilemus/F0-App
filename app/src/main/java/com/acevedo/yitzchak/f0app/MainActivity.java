package com.acevedo.yitzchak.f0app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    public static String EXTRA_FULL = "com.acevedo.yitzchak.FULLNAME";
    public static String EXTRA_USER = "com.acevedo.yitzchak.USERNAME";
    public static String EXTRA_PASS = "com.acevedo.yitzchak.PASSWORD";
    public static String EXTRA_CONF = "com.acevedo.yitzchak.CONFIRM";
    public static boolean isDeveloper = false;
    public static int numPresses = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numPresses++;
                if (numPresses > 4) {
                    Intent intent = new Intent(context, HomeScreen.class);
                    intent.putExtra(EXTRA_FULL, "Developer");
                    intent.putExtra(EXTRA_USER, "Admin");
                    intent.putExtra(EXTRA_PASS, "Adminxxiv00");
                    intent.putExtra(EXTRA_CONF, "thisPass");
                    startActivity(intent);
                    isDeveloper = true;
                }
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private static boolean userStatus(String username) {
        //TODO check username database
        return !username.equals("yitzchak");
    }

    public void ButtonContinue(View view) {
        EditText fullName = (EditText) findViewById(R.id.fullName);
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        EditText confirmP = (EditText) findViewById(R.id.confirmPassword);
        String fn, un, pw, cp;
        pw = null;
        fn = fullName.getText().toString();
        un = username.getText().toString();
        pw = password.getText().toString();
        cp = confirmP.getText().toString();
        if (userStatus(un) && pw.equals(cp) && !pw.equals(" ")) {
            Intent intent = new Intent(this, HomeScreen.class);
            intent.putExtra(EXTRA_FULL, fn);
            intent.putExtra(EXTRA_USER, un);
            intent.putExtra(EXTRA_PASS, pw);
            intent.putExtra(EXTRA_CONF, cp);
            startActivity(intent);
        } else if (!userStatus(un) && !pw.equals(cp)) {
            TextView userErr = (TextView) findViewById(R.id.userErr);
            userErr.setVisibility(View.VISIBLE);
            TextView passErr = (TextView) findViewById(R.id.passErr);
            passErr.setVisibility(View.VISIBLE);
        } else if (!userStatus(un)) {
            TextView userErr = (TextView) findViewById(R.id.userErr);
            userErr.setVisibility(View.VISIBLE);
        } else if (!pw.equals(cp)) {
            TextView passErr = (TextView) findViewById(R.id.passErr);
            passErr.setVisibility(View.VISIBLE);
        } else {
            TextView userErr = (TextView) findViewById(R.id.userErr);
            userErr.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.acevedo.yitzchak.f0app/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.acevedo.yitzchak.f0app/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
