package uk.co.bubblebearapps.workingtitle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.widget.Toast;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import uk.co.bubblebearapps.workingtitle.list.ListFragment;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector, Navigator {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject DispatchingAndroidInjector<Fragment> mFragmentInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentInjector;
    }

    @Override
    public void navigateToItemDetail(String itemName) {
        Toast.makeText(this, "Opening web browser", Toast.LENGTH_SHORT).show();

        Uri webpage = Uri.parse("https://www.lloydsbankinggroup.com/");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
