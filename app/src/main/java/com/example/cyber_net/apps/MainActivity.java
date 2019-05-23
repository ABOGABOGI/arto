package com.example.cyber_net.apps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cyber_net.apps.adapter.MenuLakuPandaiAdapter;
import com.example.cyber_net.apps.adapter.MenuPPOBAdapter;
import com.example.cyber_net.apps.adapter.NewsAdapter;
import com.example.cyber_net.apps.adapter.home.MenuAdapter;
import com.example.cyber_net.apps.adapter.home.ProdukAdapter;
import com.example.cyber_net.apps.adapter.home.VideoAdapter;
import com.example.cyber_net.apps.fragment.Account;
import com.example.cyber_net.apps.fragment.CallUs;
import com.example.cyber_net.apps.fragment.History;
import com.example.cyber_net.apps.helper.BottomNavigationViewHelper;
import com.example.cyber_net.apps.model.Akun;
import com.example.cyber_net.apps.model.MenuModel;
import com.example.cyber_net.apps.model.News;
import com.example.cyber_net.apps.model.Videos;
import com.example.cyber_net.apps.network.ApiClient;
import com.example.cyber_net.apps.network.ApiInterface;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.cyber_net.apps.helper.ListMenu.getLakuPandai;
import static com.example.cyber_net.apps.helper.ListMenu.getMenu;
import static com.example.cyber_net.apps.helper.ListMenu.getPPOB;
import static com.example.cyber_net.apps.helper.ListMenu.link;

public class MainActivity extends AppCompatActivity implements MenuAdapter.OnSelect {

    @BindView(R.id.rv_menu)
    RecyclerView rvMenu;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    TextView txtJudul;
    RecyclerView rvMenuItem;
    ImageView imgClose;
    @BindView(R.id.rv_news)
    RecyclerView rvNews;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    FragmentManager fragmentManager = getSupportFragmentManager();
    @BindView(R.id.contrains)
    ConstraintLayout contrains;
    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.ns)
    NestedScrollView ns;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.txt_notif)
    TextView txtNotif;
    @BindView(R.id.txt_nama)
    TextView txtNama;
    @BindView(R.id.txt_nominal)
    TextView txtNominal;
    @BindView(R.id.wrapper)
    LinearLayout wrapper;
    @BindView(R.id.rv_produk)
    RecyclerView rvProduk;
    @BindView(R.id.rv_video)
    RecyclerView rvVideo;
    SharedPreferences sharedPreferences;
    String id, base;
    @BindView(R.id.slider_iklan)
    CarouselView sliderIklan;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        BottomNavigationViewHelper.removeShiftMode(navigation);
        sharedPreferences = getSharedPreferences(Config.SHARED_NAME, Context.MODE_PRIVATE);
        //cek apakah user sudah login
        base = Config.BASE_URL;
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 0 && timeOfDay < 12) {
            txtNotif.setText("Selamat Pagi");
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            txtNotif.setText("Selamat Siang");
        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            txtNotif.setText("Selamat Sore");
        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            txtNotif.setText("Selamat Malam");
        }

        //toolbar
        setSupportActionBar(toolbar);

        //MENU GRID
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        rvMenu.setLayoutManager(gridLayoutManager);

        List<MenuModel> listMenu = new ArrayList<>();
        listMenu = getMenu(listMenu);
        MenuAdapter adapter = new MenuAdapter(this, listMenu, this);
        rvMenu.setAdapter(adapter);

        //bottom
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //news
        getIklan();
        getBerita();
        getVideo();
        getProduk();
//        get_user();

        sliderIklan.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MainActivity.this, "Clicked item: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //news
                getIklan();
                getBerita();
                getVideo();
                getProduk();
                refresh.setRefreshing(false);
            }
        });
    }

    public void topup(View v) {
        Intent intent = new Intent(MainActivity.this, Topup.class);
        startActivity(intent);
    }

    public void get_user() {
        ApiInterface apiInterface = ApiClient.getRetrofit(base).create(ApiInterface.class);
        Call<Akun> call = apiInterface.UserGet(id);
        call.enqueue(new Callback<Akun>() {
            @Override
            public void onResponse(Call<Akun> call, Response<Akun> response) {
                Log.i("status", "response");
                String nama = response.body().getNama();
                String id = String.valueOf(response.body().getId());
                NumberFormat formatter = new DecimalFormat("#,###");
                String saldo = formatter.format(response.body().getSaldo());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("id", id);
                editor.putString("nama", nama);
                editor.putString("saldo", saldo);
                editor.commit();
                txtNama.setText(nama);
                txtNominal.setText(saldo);
            }

            @Override
            public void onFailure(Call<Akun> call, Throwable t) {
                Log.i("error", t.toString());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        id = sharedPreferences.getString("id", "0");
        get_user();
//        Toast.makeText(MainActivity.this, "Kembali", Toast.LENGTH_LONG).show();
    }

    private void getIklan() {
        try {
            sliderIklan.setPageCount(link.length);
            sliderIklan.setImageListener(imageListener);
        } catch (Exception e) {

        }
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
//            imageView.setImageResource(link[position]);
            Glide.with(MainActivity.this)
                    .load(link[position])
                    .into(imageView);
        }
    };

    @Override
    public void onPpob() {
        View view = getLayoutInflater().inflate(R.layout.item_settings, null);

        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        txtJudul = view.findViewById(R.id.txt_judul);
        rvMenuItem = view.findViewById(R.id.rv_menu_item);
        imgClose = view.findViewById(R.id.img_close);

        txtJudul.setText("Menu PPOB");

        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 4);
        rvMenuItem.setLayoutManager(gridLayoutManager1);

        List<MenuModel> listMenu1 = new ArrayList<>();
        listMenu1 = getPPOB(listMenu1);
        MenuPPOBAdapter adapter1 = new MenuPPOBAdapter(this, listMenu1);
        rvMenuItem.setAdapter(adapter1);

        dialog.setContentView(view);
        dialog.show();

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onBanking() {
        //Toast.makeText(this, "klil", Toast.LENGTH_SHORT).show();
        View view = getLayoutInflater().inflate(R.layout.item_settings, null);

        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        txtJudul = view.findViewById(R.id.txt_judul);
        rvMenuItem = view.findViewById(R.id.rv_menu_item);
        imgClose = view.findViewById(R.id.img_close);

        txtJudul.setText("Branchless Banking");

        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 4);
        rvMenuItem.setLayoutManager(gridLayoutManager1);

        List<MenuModel> listMenu1 = new ArrayList<>();
        listMenu1 = getLakuPandai(listMenu1);
        MenuLakuPandaiAdapter adapter1 = new MenuLakuPandaiAdapter(this, listMenu1);
        rvMenuItem.setAdapter(adapter1);

        dialog.setContentView(view);
        dialog.show();

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void getBerita() {
        try {

            rvNews.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            ArrayList<News> animalNames = new ArrayList<>();
            animalNames.add(new News("ARTOMORO Mengunjungi panti asuhan cacat ganda", "https://bimg.antaranews.com/cache/bali/730x487/2013/07/boediono.jpg",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
            animalNames.add(new News("Gubernur Jateng Meresmikan BPR ARTO MORO Semarang", "https://insedprod.files.wordpress.com/2008/08/dsc_0068_resize.jpg",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));

            // set up the RecyclerView
            NewsAdapter adapter = new NewsAdapter(this, animalNames);
            rvNews.setAdapter(adapter);

            rvNews.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (dy > 0 && navigation.getVisibility() == View.VISIBLE) {
                        navigation.setVisibility(View.GONE);
                    } else if (dy < 0 && navigation.getVisibility() != View.VISIBLE) {
                        navigation.setVisibility(View.VISIBLE);
                    }
                }
            });
        } catch (Exception e) {

        }
    }

    private void getProduk() {
        try {

            rvProduk.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
            ArrayList<News> animalNames = new ArrayList<>();
            animalNames.add(new News("kredit", "http://cgblogassets.s3-ap-northeast-1.amazonaws.com/wp-content/uploads/sites/2/2016/09/30042741/00.-Header-Kartu-Kredit-Virtual.jpg", ""));
            animalNames.add(new News("deposito", "http://bprwm.co.id/wp-content/uploads/2018/03/Pengertian-Deposito-dan-Perhitungan-Bunganya.jpg", ""));
            animalNames.add(new News("tabungan", "https://www.bankmandiri.co.id/documents/20143/32416/perseorangan_simpanan_tabunganrupiah.jpg", ""));
            animalNames.add(new News("tasbam", "http://cgblogassets.s3-ap-northeast-1.amazonaws.com/wp-content/uploads/sites/2/2016/09/30042741/00.-Header-Kartu-Kredit-Virtual.jpg", ""));

            // set up the RecyclerView
            ProdukAdapter adapter = new ProdukAdapter(this, animalNames);
            rvProduk.setAdapter(adapter);
        } catch (Exception e) {

        }
    }

    private void getVideo() {
        try {
            Vector<Videos> youtubeVideos = new Vector<Videos>();

            rvVideo.setHasFixedSize(true);
            rvVideo.setLayoutManager(new LinearLayoutManager(this));

            youtubeVideos.add(new Videos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/eWEF1Zrmdow\" frameborder=\"0\" allowfullscreen></iframe>"));
            youtubeVideos.add(new Videos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/KyJ71G2UxTQ\" frameborder=\"0\" allowfullscreen></iframe>"));

            VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);

            rvVideo.setAdapter(videoAdapter);
        } catch (Exception e) {

        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                    frame.setVisibility(View.GONE);
                    finish();
                    return true;
                case R.id.navigation_dashboard:
                    fragmentManager.beginTransaction().replace(R.id.frame, new CallUs()).commit();
                    ns.setVisibility(View.GONE);
                    appBarLayout.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_history:
                    fragmentManager.beginTransaction().replace(R.id.frame, new History()).commit();
                    ns.setVisibility(View.GONE);
                    appBarLayout.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_notifications:
                    fragmentManager.beginTransaction().replace(R.id.frame, new Account()).commit();
                    ns.setVisibility(View.GONE);
                    appBarLayout.setVisibility(View.GONE);
                    return true;
            }
            return false;
        }
    };
}