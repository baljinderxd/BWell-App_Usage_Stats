package com.b.well;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

public class finalAppList {
    long hrs;
    long mins;
    String appname,input;
    long totaltime;
    Context getApplicationContext;

    finalAppList(Context c) {
        hrs = 0;
        mins = 0;
        appname = input = null;
        totaltime = 0;
        this.getApplicationContext = c;

    }

    void setAppName(String a) {

        final PackageManager pm = getApplicationContext.getPackageManager();
        ApplicationInfo ai;
        try {
            ai = pm.getApplicationInfo(a, 0);
        } catch (final PackageManager.NameNotFoundException e) {
            ai = null;
        }
        appname = (String) (ai != null ? pm.getApplicationLabel(ai) : "Uninstalled :(");
   /* if (appname.length()>14)
        appname = appname.substring(0,14);*/
    }

    void setinput()
    {
        if (hrs!=1) {
        if (mins!=1)
            input = String.valueOf(hrs) + "  Hours  " + String.valueOf(mins) + "  Minutes";
        else
            input = String.valueOf(hrs) + "  Hours  " + String.valueOf(mins) + "  Minute";
        }
        else {
            if (mins!=1)
                input = String.valueOf(hrs) + "  Hour  " + String.valueOf(mins) + "  Minutes";
            else
                input = String.valueOf(hrs) + "  Hour  " + String.valueOf(mins) + "  Minute";
        }
    }

}