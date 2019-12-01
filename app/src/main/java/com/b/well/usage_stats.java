//Class to get usage stats for a particular time interval
package com.b.well;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class usage_stats
{
    long hrj,minj,j,launcherTime;
    Map<String, UsageStats> usageStats = new HashMap<>();//UsageStats provide usage stats and is stored as hashmap
    String launchername,input;//Launcher name to find and exclude its usage from total usage 

    usage_stats(){

       hrj=0;
       minj=0;
        j=0;
        launchername=input = null;
        usageStats=null;

    }



    void getUsageStats(int a, long b, long c, Context d) {

        UsageStatsManager usageStatsManager = (UsageStatsManager) d.getSystemService(Context.USAGE_STATS_SERVICE);
//This declares usagestatmanager to get usage stats 
        usageStats = usageStatsManager.queryAndAggregateUsageStats(b,c);
//usageStats is assigned aggregated stats from time interval b to interval c in milliseconds


       j = 0;
       launcherTime=0;
//For each loop to iterate usageStats
        for (Map.Entry<String, UsageStats> entry : usageStats.entrySet()){

//Total time in foreground
                j += entry.getValue().getTotalTimeInForeground();

//Condition to calculate launcher time used
            if (entry.getValue().getPackageName().equals(launchername))
                launcherTime+=entry.getValue().getTotalTimeInForeground();

        }
//Total time converted into hours and minutes
         hrj = ((j-launcherTime) / (1000 * 60 * 60));
         minj = ((j-launcherTime) / (1000 * 60)) % 60;


    }
//Function to return usage stats
    Map<String, UsageStats> showStats(){

        return usageStats;
    }
//Function to set input according to digit
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
