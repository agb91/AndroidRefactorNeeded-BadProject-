package info.infosity.Milan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.net.*;


public class Info extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.info);

        ImageButton facebook = (ImageButton) findViewById((R.id.facebook));
        facebook.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0)
            {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Infosity"));
                startActivity(browserIntent);

            }
        });

        ImageButton twitter = (ImageButton) findViewById((R.id.twitter));
        twitter.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0)
            {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/InfoSity_app"));
                startActivity(browserIntent);

            }
        });
        ImageButton wordpress = (ImageButton) findViewById((R.id.wordpress));
        wordpress.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0)
            {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.infosity.info/blog/"));
                startActivity(browserIntent);

            }
        });
        ImageButton back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(cliccaBack);

    }

    private View.OnClickListener cliccaBack = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), Preambolo.class);
            startActivity(intent);
        }
    };
}
