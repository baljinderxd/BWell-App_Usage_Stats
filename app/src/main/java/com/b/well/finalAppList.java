//Class to store all the data associated with a app
//Objects of this class are used to find most used apps
package com.b.well;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

public class finalAppList {
    long hrs;//no of hours app is used
    long mins;//no of minutes app is used
    String appname,input;//input string is used to show display the usage
    long totaltime;//App total usage in milliseconds
    Context getApplicationContext;//Context to use appstatsmanager

    finalAppList(Context c) {
        hrs = 0;
        mins = 0;
        appname = input = null;
        totaltime = 0;
        this.getApplicationContext = c;

    }
//Function to extract name of the app from its package name
    //as usagestats store package name of app
    void setAppName(String a) {

        final PackageManager pm = getApplicationContext.getPackageManager();
        ApplicationInfo ai;
        try {
            ai = pm.getApplicationInfo(a, 0);
        } catch (final PackageManager.NameNotFoundException e) {
            ai = null;
        }
        appname = (String) (ai != null ? pm.getApplicationLabel(ai) : "Uninstalled :(");
        //Null is thrown when app is not found,thus we name it uninstalled 
   /* if (appname.length()>14)
        appname = appname.substring(0,14);*/
    }
//Function to set what to display according to digit
    //Eg hours with hrs = 2 and hour with hrs = 1
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
