package cn.zmy.browser.home;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.zmy.browser.R;
import cn.zmy.browser.databinding.ActivityMainBinding;
import cn.zmy.browser.home.viewmodel.HomeInputUrlViewModel;
import cn.zmy.browser.widget.TemperatureView;

public class MainActivity extends AppCompatActivity
{
    private HomeInputUrlViewModel mHomeInputUrlViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mHomeInputUrlViewModel = new HomeInputUrlViewModel();
        binding.includeHomeInputUrl.setHomeInputUrlViewModel(mHomeInputUrlViewModel);

        TemperatureView temperatureView = findViewById(R.id.temperatureView);
        temperatureView.setTemperature(18);
    }
}
