package com.example.cyber_net.apps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.cyber_net.apps.login.Login;
import com.example.cyber_net.apps.session.Session;
import com.glide.slider.library.SliderLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.cyber_net.apps.helper.Constan.USER_LOGIN1;
import static com.example.cyber_net.apps.helper.Constan.USER_LOGIN2;
import static com.example.cyber_net.apps.helper.Constan.USER_NAME;
import static com.example.cyber_net.apps.helper.Constan.USER_PASS;

public class Home extends AppCompatActivity {

    @BindView(R.id.slider)
    SliderLayout slider;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        session = new Session(this);
        //cek apakah user sudah login

            if (session.getSessionBoolean(USER_LOGIN2)) {
                //cek login kedua
                startActivity(new Intent(this, MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
    }

    @OnClick({R.id.btn_sigin, R.id.btn_skip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sigin:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.btn_skip:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
