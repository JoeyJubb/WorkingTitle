package uk.co.bubblebearapps.workingtitle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import uk.co.bubblebearapps.workingtitle.list.ListFragment;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector, Navigator {

    public static final String LIST_FRAGMENT_TAG = "LIST_FRAGMENT";

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment listFragment = getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT_TAG);
        if (listFragment == null) {
            listFragment = ListFragment.newInstance("query");
            getSupportFragmentManager().beginTransaction().add(R.id.container, listFragment, "LIST_FRAGMENT").commitNow();
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentInjector;
    }

    @Override
    public void navigateToItemDetail() {
        Toast.makeText(this, "Opening web browser", Toast.LENGTH_SHORT).show();

        Uri webpage = Uri.parse("https://www.lloydsbankinggroup.com/");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
