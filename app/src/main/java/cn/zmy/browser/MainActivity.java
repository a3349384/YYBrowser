package cn.zmy.browser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.zmy.browser.widget.TemperatureView;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TemperatureView temperatureView = findViewById(R.id.temperatureView);
        temperatureView.setTemperature(18);
    }
}
