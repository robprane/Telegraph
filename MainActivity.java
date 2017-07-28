package com.robprane.telegraph;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.R.attr.mode;
import static android.view.KeyEvent.KEYCODE_ENTER;


public class MainActivity extends AppCompatActivity {

    private ActionMode.Callback actionModeCallback;
    private EditText editText;
    private EditText editName;
    private Button publishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionModeCallback = new ActionMode.Callback() {

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // родные пункты меню "нумеруются" с 1, дополнительные - со 100
                while (menu.getItem(0).getOrder() < 100) {
                    MenuItem item = menu.getItem(0);
                    menu.removeItem(item.getItemId());
                    // теперь родные будут "нумероваться" с 200, то есть
                    // станут после дополнительных
                    menu.add(item.getGroupId(), item.getItemId(),
                            item.getOrder() + 200, item.getTitle());
                }
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_bold:
                        View view = getCurrentFocus();
                        toast("bold");
                        mode.finish();
                        return true;
                    case R.id.action_italic:
                        toast("italic");
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        };

        editText = (EditText) findViewById(R.id.editText);
        editName = (EditText) findViewById(R.id.editName);

        publishButton = (Button) findViewById(R.id.publishButton);

        editText.setCustomSelectionActionModeCallback(actionModeCallback);
        editName.setCustomSelectionActionModeCallback(actionModeCallback);

        editText.setHorizontallyScrolling(false);
        editText.setMaxLines(Integer.MAX_VALUE);

        editText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    toast("YEEEAAAH");
                    return true;
                }
                return false;
            }
        });

        View.OnClickListener PublishClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //---
            }
        };

        publishButton.setOnClickListener(PublishClick);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle item selection
//        switch (item.getItemId()) {
//            case R.id.action_bold:
//                toast("camera");
//                return true;
//            case R.id.action_italic:
//                toast("compass");
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    private void toast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }

}


