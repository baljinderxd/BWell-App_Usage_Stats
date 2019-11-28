package com.b.well;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class usage_stats
{
    long hrj,minj,j,launcherTime;
    Map<String, UsageStats> usageStats = new HashMap<>();
    String launchername,input;

    usage_stats(){

       hrj=0;
       minj=0;
        j=0;
        launchername=input = null;
        usageStats=null;

    }



    void getUsageStats(int a, long b, long c, Context d) {

        UsageStatsManager usageStatsManager = (UsageStatsManager) d.getSystemService(Context.USAGE_STATS_SERVICE);

        usageStats = usageStatsManager.queryAndAggregateUsageStats(b,c);



       j = 0;
       launcherTime=0;

        for (Map.Entry<String, UsageStats> entry : usageStats.entrySet()){


                j += entry.getValue().getTotalTimeInForeground();


            if (entry.getValue().getPackageName().equals(launchername))
                launcherTime+=entry.getValue().getTotalTimeInForeground();

        }

         hrj = ((j-launcherTime) / (1000 * 60 * 60));
         minj = ((j-launcherTime) / (1000 * 60)) % 60;


    }

    Map<String, UsageStats> showStats(){

        return usageStats;
    }

    void setinput()
    {
        if (hrj!=1) {
            if (minj!=1)
                input = String.valueOf(hrj) + "  Hours  " + String.valueOf(minj) + "  Minutes";
            else
                input = String.valueOf(hrj) + "  Hours  " + String.valueOf(minj) + "  Minute";
        }
        else {
            if (minj!=1)
                input = String.valueOf(hrj) + "  Hour  " + String.valueOf(minj) + "  Minutes";
            else
                input = String.valueOf(hrj) + "  Hour  " + String.valueOf(minj) + "  Minute";
        }
    }

}