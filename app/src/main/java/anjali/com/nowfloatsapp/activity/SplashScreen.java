package anjali.com.nowfloatsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import anjali.com.nowfloatsapp.R;
import anjali.com.nowfloatsapp.database.DatabaseHandler;
import anjali.com.nowfloatsapp.services.ConnectionDetector;

public class SplashScreen extends AppCompatActivity {
    DatabaseHandler databaseHandler;
    ConnectionDetector detector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        Button button=(Button)findViewById(R.id.fav);
        databaseHandler=new DatabaseHandler (SplashScreen.this);
        detector=new ConnectionDetector(this);
        if(detector.isConnectingToInternet()==true){
            button.setVisibility(View.GONE);
            Intent intent=new Intent(SplashScreen.this,MainActivity.class);
            startActivity(intent);
        }else {
            button.setVisibility(View.VISIBLE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(SplashScreen.this,FavActivity.class);
                    startActivity(intent);
                }
            });
        }

    }
}
