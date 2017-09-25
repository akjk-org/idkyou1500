package uci.wifiproxy.profile.profileDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import uci.wifiproxy.R;
import uci.wifiproxy.util.ActivityUtils;

/**
 * Created by daniel on 18/09/17.
 */

public class ProfileDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_PROFILE_ID = "TASK_ID";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profile_details_act);

        //Set up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        String profileId = getIntent().getStringExtra(EXTRA_PROFILE_ID);

        ProfileDetailsFragment profileDetailsFragment = (ProfileDetailsFragment) getSupportFragmentManager().
                findFragmentById(R.id.contentFrame);

        if (profileDetailsFragment == null){
            profileDetailsFragment = ProfileDetailsFragment.newInsatnce();
            Bundle bundle = new Bundle();
            bundle.putString(ProfileDetailsFragment.ARGUMENT_PROFILE_ID, profileId);
            profileDetailsFragment.setArguments(bundle);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    profileDetailsFragment, R.id.contentFrame);
        }

        new ProfileDetailsPresenter(profileDetailsFragment, profileId);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
