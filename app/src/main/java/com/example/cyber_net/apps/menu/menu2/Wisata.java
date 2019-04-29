package com.example.cyber_net.apps.menu.menu2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.example.cyber_net.apps.MainActivity;
import com.example.cyber_net.apps.R;
import com.example.cyber_net.apps.adapter.MenuWisataAdapter;
import com.example.cyber_net.apps.adapter.NewsAdapter;
import com.example.cyber_net.apps.adapter.wisata.WisataPopulerAdapter;
import com.example.cyber_net.apps.model.MenuModel;
import com.example.cyber_net.apps.model.News;
import com.glide.slider.library.Animations.DescriptionAnimation;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderTypes.BaseSliderView;
import com.glide.slider.library.SliderTypes.TextSliderView;
import com.glide.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.cyber_net.apps.helper.ListMenuWisata.getMenuWisata;
import static com.example.cyber_net.apps.helper.ListMenuWisata.linkPop;
import static com.example.cyber_net.apps.helper.ListMenuWisata.linkPop2;

public class
Wisata extends AppCompatActivity implements ViewPagerEx.OnPageChangeListener{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.slider)
    SliderLayout slider;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;
    @BindView(R.id.rv_menu)
    RecyclerView rvMenu;
    @BindView(R.id.txt_area)
    TextView txtArea;
    @BindView(R.id.rv_populer)
    RecyclerView rvPopuler;
    @BindView(R.id.txt_kota)
    TextView txtKota;
    @BindView(R.id.rv_kota)
    RecyclerView rvKota;
    @BindView(R.id.txt_provinsi)
    TextView txtProvinsi;
    @BindView(R.id.rv_provinsi)
    RecyclerView rvProvinsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        //
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvMenu.setLayoutManager(new GridLayoutManager(this, 4));
        List<MenuModel> listMenu = new ArrayList<>();
        listMenu = getMenuWisata(listMenu);
        MenuWisataAdapter adapter = new MenuWisataAdapter(this, listMenu);
        rvMenu.setAdapter(adapter);

        //slider
        slider.setPresetTransformer(SliderLayout.Transformer.ZoomOut);

        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider.setCustomAnimation(new DescriptionAnimation());
        slider.setDuration(4000);
        slider.addOnPageChangeListener(Wisata.this);
        getSlider();

        //hide tiitle toolbar
        //hide tittle in bar
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    String title = "Wisata";
                    collapsingToolbarLayout.setTitle(title);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });

        //get kota populer
        getKota();
        getProvinsi();
    }

    private void getSlider() {
        try {

            for (int i = 0; i < linkPop.length; i++) {
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.centerCrop();

                TextSliderView sliderView = new TextSliderView(Wisata.this);
                // if you want show image only / without description text use DefaultSliderView instead

                // initialize SliderLayout
                sliderView
                        .image(linkPop[i])
                        .description(linkPop2[i])
                        .setRequestOption(requestOptions)
                        .setBackgroundColor(Color.WHITE)
                        .setProgressBarVisible(true)
                        .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                            @Override
                            public void onSliderClick(BaseSliderView slider) {
                                Toast.makeText(Wisata.this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();

                            }
                        });

                //add your extra information
                sliderView.bundle(new Bundle());
                sliderView.getBundle().putString("extra", linkPop2[i]);
                slider.addSlider(sliderView);
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getKota() {
        try {

            rvPopuler.setLayoutManager(new LinearLayoutManager(Wisata.this, LinearLayoutManager.HORIZONTAL, false));
            ArrayList<News> animalNames = new ArrayList<>();
            animalNames.add(new News("Google Chrome Untuk Ponsel Bakal Dapat Mode Gelap", "https://www.beritateknologi.com/wp-content/uploads/2019/02/google-chrome-640x427.jpg",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
            animalNames.add(new News("Samsung Galaxy Watch", "https://www.beritateknologi.com/wp-content/uploads/2019/02/Samsung-Galaxy-Watch-Active-1.jpg",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));

            // set up the RecyclerView
            WisataPopulerAdapter adapter = new WisataPopulerAdapter(this, animalNames);
            rvPopuler.setAdapter(adapter);
        } catch (Exception e) {

        }
    }

    private void getProvinsi() {
        try {

            rvProvinsi.setLayoutManager(new LinearLayoutManager(Wisata.this, LinearLayoutManager.HORIZONTAL, false));
            ArrayList<News> animalNames = new ArrayList<>();
            animalNames.add(new News("Google Chrome Untuk Ponsel Bakal Dapat Mode Gelap", "https://www.beritateknologi.com/wp-content/uploads/2019/02/google-chrome-640x427.jpg",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
            animalNames.add(new News("Samsung Galaxy Watch", "https://www.beritateknologi.com/wp-content/uploads/2019/02/Samsung-Galaxy-Watch-Active-1.jpg",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));

            // set up the RecyclerView
            WisataPopulerAdapter adapter = new WisataPopulerAdapter(this, animalNames);
            rvProvinsi.setAdapter(adapter);
        } catch (Exception e) {

        }
    }
}