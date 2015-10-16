package alex.fackintimer;

import android.content.Context;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by alex on 20.09.15.
 */

class loader extends Loader<String> {

        final String LOG_TAG = "myLogs";
        final int PAUSE = 10;
        EngineTimer et;

        public loader(Context context, Bundle args) {
            super(context);
            Log.d(LOG_TAG, hashCode() + " create TimeLoader");
            Log.d(LOG_TAG, hashCode() + " create TimeLoader");

        }

        @Override
        protected void onStartLoading() {
            et=et.GetEt();
            super.onStartLoading();
            //вызывается при старте (onStart) Activity или фрагмента, к которому будет привязан Loader.
            Log.d(LOG_TAG, hashCode() + " onStartLoading");
        }

        @Override
        protected void onStopLoading() {
            super.onStopLoading();
            //вызывается при остановке (onStop) Activity или фрагмента, к которому будет привязан Loader.
            Log.d(LOG_TAG, hashCode() + " onStopLoading");
        }

        @Override
        protected void onForceLoad() {
            super.onForceLoad();
            Log.d(LOG_TAG, hashCode() + " onForceLoad");
//            if (getTimeTask != null)
//                getTimeTask.cancel(true);
//            getTimeTask = new GetTimeTask();
//            getTimeTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, format);
        }

        @Override
        protected void onAbandon() {
            super.onAbandon();
            Log.d(LOG_TAG, hashCode() + " onAbandon");
        }

        @Override
        protected void onReset() {
            super.onReset();
            Log.d(LOG_TAG, hashCode() + " onReset");
        }

        void getResultFromTask(String result) {
            deliverResult(result);
        }


            class GetTimeTask extends AsyncTask<String, Void, String> {


                @Override
                protected String doInBackground(String... params) {
                    try {do {
                        getResultFromTask(et.getTime(System.currentTimeMillis()));
                        TimeUnit.MILLISECONDS.sleep(1);
                    }while(!isCancelled());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
                @Override
                 protected void onPostExecute(String result) {
                    super.onPostExecute(result);
                     Log.d(LOG_TAG, loader.this.hashCode() + " onPostExecute "
                        + result);
                    getResultFromTask(result);
            }

            }






    }




//        class GetTimeTask extends AsyncTask<String, Void, String> {
//            @Override
//            protected String doInBackground(String... params) {
//                Log.d(LOG_TAG, loader.this.hashCode() + " doInBackground");
//                try {
//                    TimeUnit.SECONDS.sleep(PAUSE);
//                } catch (InterruptedException e) {
//                    return null;
//                }
//
//                SimpleDateFormat sdf = new SimpleDateFormat(params[0],
//                        Locale.getDefault());
//                return sdf.format(new Date());
//            }
//
//            @Override
//            protected void onPostExecute(String result) {
//                super.onPostExecute(result);
//                Log.d(LOG_TAG, loader.this.hashCode() + " onPostExecute "
//                        + result);
//                getResultFromTask(result);
//            }
//
//        }



