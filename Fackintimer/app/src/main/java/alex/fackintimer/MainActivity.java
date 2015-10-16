package alex.fackintimer;

import android.app.LoaderManager;
import android.content.Loader;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private EngineTimer et;
    private MyTask mt;
    private TextView tvLog ;
    private TextView tv ;
    private Button btStart;
    private Button btStop;
    private Button btPd;
    private static boolean flag;
    static String Savelog = new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
        tv=(TextView) findViewById(R.id.TvTimer);
        tvLog=(TextView) findViewById(R.id.tv_log);
        tvLog.setMovementMethod(new ScrollingMovementMethod());
        btStart= (Button)findViewById(R.id.bt_start);
        btStart.setOnClickListener(Startlistenerstart);
        btStop = (Button)findViewById(R.id.bt_stop);
        btStop.setOnClickListener(StartlistenerStop);
        btPd = (Button)findViewById(R.id.bt_pd);
        btPd.setOnClickListener(StartlistenerPd);
//        et= new EngineTimer(); //до того как сделал его статическим
        et = et.GetEt(); //Попытка создать синглтон
        mt = new MyTask();
        btStop.setEnabled(false);
//        Bundle bndl = new Bundle();
//        bndl.putString(loader.ARGS_TIME_FORMAT, loader.TIME_FORMAT_SHORT);
//        getLoaderManager().initLoader(LOADER_TIME_ID, bndl, this);
//        lastCheckedId = rgTimeFormat.getCheckedRadioButtonId();

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
            System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private View.OnClickListener Startlistenerstart = new View.OnClickListener() {
        public void onClick(View v)
        {

//            et.Obnul();
//        tv.setText(et.gettime());
            et.setStart(System.currentTimeMillis());
        mt.execute();
            btStart.setEnabled(false);
            btStop.setEnabled(true);
            flag=true;

        }
    };

    private View.OnClickListener StartlistenerStop = new View.OnClickListener() {
        public void onClick(View v) {
            mt.cancel(true);
            mt = new MyTask();
            btStart.setEnabled(true);
            btStop.setEnabled(false);
            flag=false;
        }
    };

    private View.OnClickListener StartlistenerPd = new View.OnClickListener() {
        public void onClick(View v) {
            tvLog.append("\n" + tv.getText());
        }




    };

    @Override
    protected void onStop() {
        super.onStop();
        mt.cancel(true);
        mt = new MyTask();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Savelog=tvLog.getText().toString();
    }

    @Override
    protected void onResume() {
        if (Savelog!=null){tvLog.setText(Savelog);}
        if (flag) {  mt.execute();
            btStart.setEnabled(false);
            btStop.setEnabled(true);}
        super.onResume();
    }

    final String LOG_TAG = "myLogs";
    static final int LOADER_TIME_ID = 1;

//   лоадер
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Loader<String> loader = null;
        if (id == LOADER_TIME_ID) {
            loader = new loader(this, args);
            Log.d(LOG_TAG, "onCreateLoader: " + loader.hashCode());
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String result) {
        Log.d(LOG_TAG, "onLoadFinished for loader " + loader.hashCode()
                + ", result = " + result);
//        tvTime.setText(result);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.d(LOG_TAG, "onLoaderReset for loader " + loader.hashCode());
    }






    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {do {

//                et.runTimer();

                publishProgress();
                TimeUnit.MILLISECONDS.sleep(1);
            }while(!isCancelled());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
//            tv.setText(et.gettime());
            tv.setText(et.getTime(System.currentTimeMillis()));
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

        }
    }
}
