package alex.fackintimer;

import android.os.AsyncTask;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by alex on 11.09.15.
 */
public  class EngineTimer {
    private static EngineTimer EngTim;
    private static long mlSec;

    public static EngineTimer  GetEt(){
        if (EngTim==null){EngTim=new EngineTimer();}
        return EngTim;
    }


    private EngineTimer(){

    }



    public void setStart (long ms){
        System.out.println(ms);
        this.mlSec=ms;

    }


//    public String getTime (long ms){
//        String res;
//        Integer hournow=new Time(ms).getHours();
//        if (hour>hournow) {
//            hournow=24 +hournow-hour;}
//        else{
//            hournow=hournow-hour;}
//
//       Time time =new Time(ms-mlSec);
//        res = Integer.toString(hournow)+":"+Integer.toString(time.getMinutes())+":"+Integer.toString(time.getSeconds());
//        return res;
//    }


    public String getTime (long ms){
        ms=ms-mlSec;
        long milsec;
        long sec;
        long min;
        long hour;
        String res;
        milsec= ms % 1000;
        sec=(ms/1000)%60;
        min=(ms/60000)%60;
        hour=(ms/3600000)%24;
        if (hour<10){res="0"+hour;}
        else{res=""+hour;}
        if (min<10){res=res+":0"+min;}
        else{res=res+":"+min;}
        if (sec<10){res=res+":0"+sec;}
        else{res=res+":"+sec;}
        res=res+":"+format(milsec);

        return res;
    }



    public String format(long ms){
        String res="";
        if (ms<100){res=res+0;
        if (ms<10){res=res+0;}}
        return res+ms;
    }
}
