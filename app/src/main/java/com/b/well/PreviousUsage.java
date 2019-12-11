//Similar to MainActivity code , just different time intervals are used 
package com.b.well;

import android.app.Activity;
import android.app.usage.UsageStats;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Map;

import static android.app.usage.UsageStatsManager.INTERVAL_DAILY;
import static android.app.usage.UsageStatsManager.INTERVAL_WEEKLY;

public class PreviousUsage extends AppCompatActivity {

    TextView filltt, filltt7;
    TextView an1, an2, an3, an4, an5, an17, an27, an37, an47, an57;
    TextView fillah1, fillah2, fillah3, fillah4, fillah5, fillah17, fillah27, fillah37, fillah47, fillah57;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.previous_usage);

        filltt = findViewById(R.id.filltty);
        filltt7 = findViewById(R.id.filltt7);


        an1 = findViewById(R.id.an1);
        an2 = findViewById(R.id.an2);
        an3 = findViewById(R.id.an3);
        an4 = findViewById(R.id.an4);
        an5 = findViewById(R.id.an5);
        an17 = findViewById(R.id.an17);
        an27 = findViewById(R.id.an27);
        an37 = findViewById(R.id.an37);
        an47 = findViewById(R.id.an47);
        an57 = findViewById(R.id.an57);

        fillah1 = findViewById(R.id.fillah1);
        fillah2 = findViewById(R.id.fillah2);
        fillah3 = findViewById(R.id.fillah3);
        fillah4 = findViewById(R.id.fillah4);
        fillah5 = findViewById(R.id.fillah5);
        fillah17 = findViewById(R.id.fillah17);
        fillah27 = findViewById(R.id.fillah27);
        fillah37 = findViewById(R.id.fillah37);
        fillah47 = findViewById(R.id.fillah47);
        fillah57 = findViewById(R.id.fillah57);

        long endTimey, beginTimey;

        Calendar c = Calendar.getInstance();
        c.roll(Calendar.DAY_OF_MONTH, -1);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        endTimey = c.getTimeInMillis();

        c = Calendar.getInstance();
        c.roll(Calendar.DAY_OF_MONTH, -1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        beginTimey = c.getTimeInMillis();

        Activity h = this;

        usage_stats uy = new usage_stats();


        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        ResolveInfo resolveInfo = getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);

        String currentLauncherName = null;
        if (resolveInfo != null)
            currentLauncherName = resolveInfo.activityInfo.packageName;

        finalAppList ln = new finalAppList(h);
        ln.setAppName(currentLauncherName);

        uy.launchername = currentLauncherName;
        uy.getUsageStats(INTERVAL_DAILY, beginTimey, endTimey, h);



        finalAppList[] f = new finalAppList[uy.showStats().size()];
        for (int p = 0; p < uy.showStats().size(); p++) {
            f[p] = new finalAppList(h);

        }

        int zz = 0;

        uy.setinput();

        filltt.setText(uy.input);

        for (Map.Entry<String, UsageStats> entry : uy.showStats().entrySet()) {

            f[zz].totaltime = entry.getValue().getTotalTimeInForeground();
            f[zz].setAppName(entry.getValue().getPackageName());
            f[zz].hrs = ((entry.getValue().getTotalTimeInForeground() / (1000 * 60 * 60)));
            f[zz].mins = ((entry.getValue().getTotalTimeInForeground() / (1000 * 60)) % 60);
            f[zz].setinput();
            zz++;
        }

        long max1 = 0;
        int maxp1 = 0;

        for (int l = 0; l < uy.showStats().size(); l++) {
            if (f[l].totaltime > max1 && !f[l].appname.equals(ln.appname) &&
                    !f[l].appname.equals("Uninstalled :(")) {
                max1 = f[l].totaltime;
                maxp1 = l;

            }
        }
        an1.setText(f[maxp1].appname);
        fillah1.setText(f[maxp1].input);

        long max2 = 0;
        int maxp2 = 0;
        for (int l = 0; l < uy.showStats().size(); l++) {
            if (f[l].totaltime < max1 && f[l].totaltime > max2 && !f[l].appname.equals(f[maxp1].appname) &&
                    !f[l].appname.equals("Uninstalled :(")
                    && !f[l].appname.equals(ln.appname)) {
                max2 = f[l].totaltime;
                maxp2 = l;

            }
        }
        an2.setText(f[maxp2].appname);
        fillah2.setText(f[maxp2].input);

        long max3 = 0;
        int maxp3 = 0;
        for (int l = 0; l < uy.showStats().size(); l++) {
            if (f[l].totaltime < max2 && f[l].totaltime > max3 && !f[l].appname.equals(f[maxp1].appname)
                    && !f[l].appname.equals(f[maxp2].appname) &&
                    !f[l].appname.equals("Uninstalled :(")&& !f[l].appname.equals(ln.appname)) {
                max3 = f[l].totaltime;
                maxp3 = l;

            }
        }
        an3.setText(f[maxp3].appname);
        fillah3.setText(f[maxp3].input);

        long max4 = 0;
        int maxp4 = 0;
        for (int l = 0; l < uy.showStats().size(); l++) {
            if (f[l].totaltime < max3 && f[l].totaltime > max4 && !f[maxp1].appname.equals(f[l].appname)
                    && !f[maxp2].appname.equals(f[l].appname) && !f[maxp3].appname.equals(f[l].appname)
                    && !f[l].appname.equals("Uninstalled :(")&& !f[l].appname.equals(ln.appname)) {
                max4 = f[l].totaltime;
                maxp4 = l;

            }
        }
        an4.setText(f[maxp4].appname);
        fillah4.setText(f[maxp4].input);

        long max5 = 0;
        int maxp5 = 0;
        for (int l = 0; l < uy.showStats().size(); l++) {
            if (f[l].totaltime < max4 && f[l].totaltime > max5 && !f[maxp1].appname.equals(f[l].appname)
                    && !f[maxp2].appname.equals(f[l].appname) && !f[maxp3].appname.equals(f[l].appname)
                    && !f[maxp4].appname.equals(f[l].appname) && !f[l].appname.equals(ln.appname) &&
                    !f[l].appname.equals("Uninstalled :(")) {
                max5 = f[l].totaltime;
                maxp5 = l;

            }
        }
        an5.setText(f[maxp5].appname);
        fillah5.setText(f[maxp5].input);

        //--------------------------Week Details-----------------------------//

        long endTimew, beginTimew;

        c = Calendar.getInstance();
        endTimew = c.getTimeInMillis();

        c = Calendar.getInstance();
        c.roll(Calendar.DAY_OF_MONTH, -6);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        beginTimew = c.getTimeInMillis();

        usage_stats uw = new usage_stats();
        uw.launchername = currentLauncherName;
        uw.getUsageStats(INTERVAL_WEEKLY, beginTimew, endTimew, h);
        uw.setinput();


        finalAppList[] fw = new finalAppList[uw.showStats().size()];

        for (int i = 0; i < uw.showStats().size(); i++) {
            fw[i] = new finalAppList(h);
        }

        filltt7.setText(uw.input);

        int z = 0;
        for (Map.Entry<String, UsageStats> entry : uw.showStats().entrySet()) {

            fw[z].totaltime = entry.getValue().getTotalTimeInForeground();
            fw[z].setAppName(entry.getValue().getPackageName());
            fw[z].hrs = ((entry.getValue().getTotalTimeInForeground() / (1000 * 60 * 60)));
            fw[z].mins = ((entry.getValue().getTotalTimeInForeground() / (1000 * 60)) % 60);
            fw[z].setinput();
            z++;
        }
        long max17 = 0;
        int maxp17 = 0;
        for (int l = 0; l < uw.showStats().size(); l++) {
            if (fw[l].totaltime > max17 && !fw[l].appname.equals(ln.appname) &&
                    !fw[l].appname.equals("Uninstalled :(")) {
                max17 = fw[l].totaltime;
                maxp17 = l;

            }
        }
        an17.setText(fw[maxp17].appname);
        fillah17.setText(fw[maxp17].input);

        long max27 = 0;
        int maxp27 = 0;

        for (int l = 0; l < uw.showStats().size(); l++) {
            if (fw[l].totaltime < max17 && fw[l].totaltime > max27
                    && !fw[l].appname.equals(fw[maxp17].appname)
                    && !fw[l].appname.equals(ln.appname) &&
                    !fw[l].appname.equals("Uninstalled :(")) {
                max27 = fw[l].totaltime;
                maxp27 = l;

            }
        }
        an27.setText(fw[maxp27].appname);
        fillah27.setText(fw[maxp27].input);

        long max37 = 0;
        int maxp37 = 0;
        for (int l = 0; l < uw.showStats().size(); l++) {
            if (fw[l].totaltime < max27 && fw[l].totaltime > max37 && !fw[l].appname.equals(fw[maxp17].appname)
                    && !fw[l].appname.equals(fw[maxp27].appname) && !fw[l].appname.equals(ln.appname) &&
                    !fw[l].appname.equals("Uninstalled :(")) {
                max37 = fw[l].totaltime;
                maxp37 = l;

            }
        }
        an37.setText(fw[maxp37].appname);
        fillah37.setText(fw[maxp37].input);

        long max47 = 0;
        int maxp47 = 0;
        for (int l = 0; l < uw.showStats().size(); l++) {
            if (fw[l].totaltime < max37 && fw[l].totaltime > max47 && !fw[maxp17].appname.equals(fw[l].appname)
                    && !fw[maxp27].appname.equals(fw[l].appname) && !fw[maxp37].appname.equals(fw[l].appname)
                    && !fw[l].appname.equals("Uninstalled :(") && !fw[l].appname.equals(ln.appname)) {
                max47 = fw[l].totaltime;
                maxp47 = l;

            }
        }
        an47.setText(fw[maxp47].appname);
        fillah47.setText(fw[maxp47].input);

        long max57 = 0;
        int maxp57 = 0;
        for (int l = 0; l < uw.showStats().size(); l++) {
            if (fw[l].totaltime < max47 && fw[l].totaltime > max57 && !fw[maxp17].appname.equals(fw[l].appname)
                    && !fw[maxp27].appname.equals(fw[l].appname) && !fw[maxp37].appname.equals(fw[l].appname)
                    && !fw[maxp47].appname.equals(fw[l].appname) && !fw[l].appname.equals(ln.appname)
                    && !fw[l].appname.equals("Uninstalled :(")) {
                max57 = fw[l].totaltime;
                maxp57 = l;

            }
        }
        an57.setText(fw[maxp57].appname);
        fillah57.setText(fw[maxp57].input);


    }
}
