package dev.devriders.tracktrainer.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.views.fragments.EjerciciosFragment;
import dev.devriders.tracktrainer.views.fragments.MisionesFragment;
import dev.devriders.tracktrainer.views.fragments.UsuarioFragment;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private InterstitialAd mInterstitialAd;
    private Handler mHandler = new Handler();
    private EjerciciosFragment ejerciciosFragment;
    private MisionesFragment misionesFragment;
    private UsuarioFragment usuarioFragment;
    private static final int MENU_EJERCICIOS = R.id.nav_ejercicios;
    private static final int MENU_MISIONES = R.id.nav_misiones;
    private static final int MENU_PERFIL = R.id.nav_perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        ejerciciosFragment = new EjerciciosFragment();
        misionesFragment = new MisionesFragment();
        usuarioFragment = new UsuarioFragment();

        // Mostrar el fragmento de ejercicios por defecto
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, ejerciciosFragment)
                .commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == MENU_EJERCICIOS) {
                showEjerciciosFragment();
                return true;
            } else if (itemId == MENU_MISIONES) {
                showMisionesFragment();
                return true;
            } else if (itemId == MENU_PERFIL) {
                showUsuarioFragment();
                return true;
            }
            return false;
        });

        // Inicializar Mobile Ads SDK
        MobileAds.initialize(this, initializationStatus -> {});

        // Cargar un anuncio intersticial
        loadInterstitialAd();

        // Mostrar un anuncio intersticial después de 20 segundos
        mHandler.postDelayed(() -> {
            if (mInterstitialAd != null) {
                mInterstitialAd.show(HomeActivity.this);
            } else {
                Log.d("TAG", "El anuncio intersticial aún no estaba listo.");
            }
        }, 20000); // 20 segundos
    }

    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        Log.d("TAG", "El anuncio intersticial está listo.");
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Vuelve a cargar el anuncio cuando se cierra
                                loadInterstitialAd();
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                                Log.d("TAG", "El anuncio falló al mostrarse.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                mInterstitialAd = null;
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.d("TAG", loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });
    }

    private void showEjerciciosFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, ejerciciosFragment)
                .commit();
    }

    private void showMisionesFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, misionesFragment)
                .commit();
    }

    private void showUsuarioFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, usuarioFragment)
                .commit();
    }
}