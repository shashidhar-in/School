package com.example.school;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class AppInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
        getSupportActionBar().hide();
        ImageView linkedin=findViewById(R.id.linkedin);
        ImageView web=findViewById(R.id.website);
        ImageView gmail=findViewById(R.id.gmail);
        ImageView instagram=findViewById(R.id.instagram);
        ImageView facebook=findViewById(R.id.facebook);

        Button bug=findViewById(R.id.bug);
        Button update=findViewById(R.id.update);


        final Vibrator vibe=(Vibrator)AppInfo.this.getSystemService(Context.VIBRATOR_SERVICE);

        bug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibe.vibrate(40);
                try {

                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "manage.school2021@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT,"Bug Report");
                    intent.putExtra(Intent.EXTRA_TEXT, "your_text");
                    startActivity(intent);
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "Sorry...You don't have any mail app", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibe.vibrate(50);

                Toast.makeText(getApplicationContext(),"This feature will be available soon",Toast.LENGTH_SHORT).show();

            }
        });






        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibe.vibrate(40);
                String linkedId="shashidhar-mittapalli";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://add/%@" + linkedId));
                    final PackageManager packageManager = getApplication().getPackageManager();
                    final List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                    if (list.isEmpty()) {
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/profile/view?id=" + linkedId));
                    }
                    startActivity(intent);

            }
        });

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibe.vibrate(40);
                String url = "http://www.shashidhar.me";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibe.vibrate(40);
                try {

                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "shashidhar.mittapalli@outlook.in"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");
                    intent.putExtra(Intent.EXTRA_TEXT, "your_text");
                    startActivity(intent);
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "Sorry...You don't have any mail app", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibe.vibrate(40);

                Uri uri = Uri.parse("http://instagram.com/_u/shashidhar.in");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/shashidhar.in")));
                }

            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibe.vibrate(40);
                    String pageId="shashidhar.mittapalli.7";
                    final String urlFb = "fb://page/"+pageId;
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(urlFb));

                    // If a Facebook app is installed, use it. Otherwise, launch
                    // a browser
                    final PackageManager packageManager = getPackageManager();
                    List<ResolveInfo> list =
                            packageManager.queryIntentActivities(intent,
                                    PackageManager.MATCH_DEFAULT_ONLY);
                    if (list.size() == 0) {
                        final String urlBrowser = "https://www.facebook.com/"+pageId;
                        intent.setData(Uri.parse(urlBrowser));
                    }

                    startActivity(intent);
                }

        });



    }
}