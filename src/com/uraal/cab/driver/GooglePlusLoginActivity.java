/*package com.uraal.cab;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GooglePlusLoginActivity extends AppCompatActivity {

    private static final String TAG = GooglePlusLoginActivity.class.getName();

    GooglePlusIdentityProvider provider;

    boolean authRequestInProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView textView = (TextView) findViewById(R.id.textView);

        Auth0 auth0 = new Auth0(getString(R.string.auth0_client_id), getString(R.string.auth0_domain_name));
        final AuthenticationAPIClient client = new AuthenticationAPIClient(auth0);

        provider = new GooglePlusIdentityProvider(MainActivity.this);
        provider.setCallback(new IdentityProviderCallback() {
            @Override
            public void onFailure(Dialog dialog) {
                Log.d(TAG, "onFailure, showing dialog...");

                textView.setText("");
                dialog.show();
            }

            @Override
            public void onFailure(int titleResource, int messageResource, Throwable cause) {
                Log.d(TAG, "onFailure, titleResource: " + titleResource + ", messageResource: " + messageResource + ", cause: " + cause);

                textView.setText("");
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle(titleResource)
                        .setMessage(messageResource)
                        .create();
                dialog.show();
            }

            @Override
            public void onSuccess(String serviceName, String accessToken) {
                Log.d(TAG, "onSuccess, serviceName: " + serviceName + ", accessToken: " + accessToken);

                textView.setText("Trying to log in with GooglePlus token: " + accessToken);

                AuthenticationRequest request = client.loginWithOAuthAccessToken(accessToken, serviceName);
                request.start(new AuthenticationCallback() {
                    @Override
                    public void onSuccess(UserProfile profile, Token token) {
                        textView.setText("Welcome " + profile.getName());
                    }

                    @Override
                    public void onFailure(Throwable error) {
                        textView.setText("Log in failed. " + error.getMessage());
                    }
                });
            }

            @Override
            public void onSuccess(Token token) {
                Log.d(TAG, "onSuccess, token: " + token);

                textView.setText("Logged in with token: " + token);
            }
        });

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        authRequestInProgress = true;
                        provider.start(MainActivity.this, Strategies.GooglePlus.getName());
                    }
                }
        );

        Button logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.setText("");
                        provider.clearSession();
                    }
                }
        );


    }

    @Override
    protected void onResume() {
        super.onResume();

        if (authRequestInProgress) {
            provider.authorize(this, GooglePlusIdentityProvider.GOOGLE_PLUS_TOKEN_REQUEST_CODE, 9876, getIntent());
            authRequestInProgress = false;
        }
    }
}

*/