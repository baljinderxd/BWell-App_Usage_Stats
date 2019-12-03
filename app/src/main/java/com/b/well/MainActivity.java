/*
TODOs:
1) Add custom notification
2) Add on play store
3) Precise Details
4) Enable donation and adds
5) PieChart for every interval
6) Add custom and monthly interval
7) Add icons with name
8) Longer list of used apps
9) Custom list for tracking app
10) Settings menu to select above modifications
11) Add good app icon xD
12) Point wise usage quality

Known Issues:
1) Wrong stats for Yesterday
2) You Tell
*/

package com.b.well;

import android.app.Activity;
import android.app.usage.UsageStats;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import bot.box.appusage.handler.Monitor;

import static android.app.usage.UsageStatsManager.INTERVAL_WEEKLY;

public class MainActivity extends AppCompatActivity {
    Button prebtn;
    PieChart pieChart;
    TextView tud, filltt, an1, an2, an3, an4, an5;
    TextView fillah1, fillah2, fillah3, fillah4, fillah5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callme();//Function for working in main activity;called when activity is created
    }

    @Override
    protected void onResume() {
        super.onResume();
        callme();//Function invoked when activity is resumed from recent app list

    }
//Function to check for permission, ask for permission, and to display today's usage stats along with the piechart
    void callme()
    {
        String bb = String.valueOf(Monitor.hasUsagePermission());//Monitor.hasUsagePermission is used from external library which checks if usage permission is granted or not
        if (bb.equals("false")) {
            pdialog exampleDialog = new pdialog();
            exampleDialog.show(getSupportFragmentManager(), "Please Provide Usage Access Permission");
        //If permission is false, dialog is shown asking for permission which navigates to the settings
        }
        else {
//If permission was granted, normal functionality takes place
            prebtn = findViewById(R.id.pre_btn);
            pieChart = findViewById(R.id.pieChart);
            tud = findViewById(R.id.tud);
            filltt = findViewById(R.id.filltt);

            an1 = findViewById(R.id.an1);
            an2 = findViewById(R.id.an2);
            an3 = findViewById(R.id.an3);
            an4 = findViewById(R.id.an4);
            an5 = findViewById(R.id.an5);

            fillah1 = findViewById(R.id.fillah1);
            fillah2 = findViewById(R.id.fillah2);
            fillah3 = findViewById(R.id.fillah3);
            fillah4 = findViewById(R.id.fillah4);
            fillah5 = findViewById(R.id.fillah5);

            //Declaring long variables for saving time instances 
            long endTime, beginTime;
            Calendar c = Calendar.getInstance();
            endTime = c.getTimeInMillis();//Current date and time

            c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, 0);//Set time to midnight for begining of interval
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            beginTime = c.getTimeInMillis();

            DateFormat simple = DateFormat.getDateInstance();//Set format of date to display at top
            Date result = new Date(beginTime);
            tud.setText(String.valueOf("Today's Stats: " + simple.format(result)));

            Activity h = this;//To pass context to classes for usage stats

            usage_stats u = new usage_stats();//Usage stats object created


            Intent intent = new Intent(Intent.ACTION_MAIN);//New intent created
            intent.addCategory(Intent.CATEGORY_HOME);//Intent to know which app is current launcger app
            ResolveInfo resolveInfo = getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);//Tells only default launcher  
            String currentLauncherName = null;
            if (resolveInfo != null)
                currentLauncherName = resolveInfo.activityInfo.packageName;//Set string value to package name of current default launcher
            finalAppList ln = new finalAppList(h);//Object of finalapplist to store launcher name 
            ln.setAppName(currentLauncherName);//Function converts package name of launcher to App for future convinience 
            u.launchername = currentLauncherName;//Sets launcher name in usagestats object
            u.getUsageStats(INTERVAL_WEEKLY, beginTime, endTime, h);//Passes interval into object to get the usage stats list ready
            u.setinput();//Sets input for total time to be displayed

            finalAppList[] f = new finalAppList[u.showStats().size()];

            for (int p = 0; p < u.showStats().size(); p++) {
                f[p] = new finalAppList(h);

            }


            long max1 = 0;
            int maxp1 = 0;

            int z = 0;
            for (Map.Entry<String, UsageStats> entry : u.showStats().entrySet()) {

                f[z].totaltime = entry.getValue().getTotalTimeInForeground();
                f[z].setAppName(entry.getValue().getPackageName());
                f[z].hrs = (entry.getValue().getTotalTimeInForeground() / (1000 * 60 * 60));
                f[z].mins = (entry.getValue().getTotalTimeInForeground() / (1000 * 60)) % 60;
                f[z].setinput();
                z++;
            }


            long sum = 0;
            for (int l = 0; l < u.showStats().size(); l++) {
                if (f[l].totaltime > max1 && !f[l].appname.equals(ln.appname) && !f[l].appname.equals("Uninstalled :(")) {
                    max1 = f[l].totaltime;
                    maxp1 = l;

                }
            }


            filltt.setText(u.input);


            an1.setText(f[maxp1].appname);
            fillah1.setText(f[maxp1].input);

            long max2 = 0;
            int maxp2 = 0;
            for (int l = 0; l < u.showStats().size(); l++) {
                if (f[l].totaltime < max1 && f[l].totaltime > max2 && !f[l].appname.equals(f[maxp1].appname) &&
                        !f[l].appname.equals("Uninstalled :(") && !f[l].appname.equals(ln.appname)) {
                    max2 = f[l].totaltime;
                    maxp2 = l;

                }
            }
            an2.setText(f[maxp2].appname);
            fillah2.setText(f[maxp2].input);


            long max3 = 0;
            int maxp3 = 0;
            for (int l = 0; l < u.showStats().size(); l++) {
                if (f[l].totaltime < max2 && f[l].totaltime > max3 && !f[l].appname.equals(f[maxp1].appname)
                        && !f[l].appname.equals(f[maxp2].appname) && !f[l].appname.equals(ln.appname) &&
                        !f[l].appname.equals("Uninstalled :(")) {
                    max3 = f[l].totaltime;
                    maxp3 = l;

                }
            }
            an3.setText(f[maxp3].appname);
            fillah3.setText(f[maxp3].input);

            long max4 = 0;
            int maxp4 = 0;
            for (int l = 0; l < u.showStats().size(); l++) {
                if (f[l].totaltime < max3 && f[l].totaltime > max4 && !f[maxp1].appname.equals(f[l].appname)
                        && !f[maxp2].appname.equals(f[l].appname) && !f[maxp3].appname.equals(f[l].appname)
                        && !f[l].appname.equals(ln.appname) &&
                        !f[l].appname.equals("Uninstalled :(")) {
                    max4 = f[l].totaltime;
                    maxp4 = l;

                }
            }
            an4.setText(f[maxp4].appname);
            fillah4.setText(f[maxp4].input);

            long max5 = 0;
            int maxp5 = 0;
            for (int l = 0; l < u.showStats().size(); l++) {
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


            long remval = u.j - u.launcherTime - f[maxp1].totaltime - f[maxp2].totaltime
                    - f[maxp3].totaltime - f[maxp4].totaltime - f[maxp5].totaltime;


            pieChart.getDescription().setEnabled(false);
            pieChart.setExtraOffsets(5, 10, 5, 5);
            pieChart.setDragDecelerationFrictionCoef(1f);
            pieChart.setDrawHoleEnabled(false);
            pieChart.setHoleColor(Color.BLACK);
            pieChart.setTransparentCircleRadius(61f);
            pieChart.getLegend().setTextColor(Color.WHITE);
            pieChart.getLegend().setTextSize(8);


            final int[] MY_COLORS = {Color.rgb(176, 9, 9), Color.rgb(68, 138, 255),
                    Color.rgb(255, 193, 7), Color.rgb(179, 119, 98),
                    Color.rgb(46, 166, 46),
                    Color.rgb(123, 50, 168)};
            ArrayList<Integer> colors = new ArrayList<>();

            for (int v : MY_COLORS) colors.add(v);

            ArrayList<PieEntry> yValues = new ArrayList<>();

            yValues.add(new PieEntry(f[maxp1].totaltime, f[maxp1].appname));
            yValues.add(new PieEntry(f[maxp2].totaltime, f[maxp2].appname));
            yValues.add(new PieEntry(f[maxp3].totaltime, f[maxp3].appname));
            yValues.add(new PieEntry(f[maxp4].totaltime, f[maxp4].appname));
            yValues.add(new PieEntry(f[maxp5].totaltime, f[maxp5].appname));
            yValues.add(new PieEntry(remval, "Others"));

            PieDataSet dataSet = new PieDataSet(yValues, "<-- Applications");
            dataSet.setSliceSpace(3f);
            dataSet.setSelectionShift(5f);
            dataSet.setColors(colors);
            dataSet.setValueFormatter(new PercentFormatter(pieChart));
            pieChart.setUsePercentValues(true);

            PieData data = new PieData((dataSet));
            data.setValueTextSize(10f);
            data.setValueTextColor(Color.WHITE);
            pieChart.setData(data);

            final Handler handler = new Handler();

            prebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Loading Data",
                            Toast.LENGTH_LONG).show();
                    Toast.makeText(MainActivity.this, "Loading Data",
                            Toast.LENGTH_LONG).show();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            startActivity(new Intent(MainActivity.this, PreviousUsage.class));
                        }
                    }, 2000);


                }
            });
            Button don_btn;
            don_btn = findViewById(R.id.don_btn);
            don_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Will Add Soon :-D",
                            Toast.LENGTH_LONG).show();
                }});
        }
    }
}
