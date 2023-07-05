package com.alvaro202102273.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;

public class ForexActivity extends AppCompatActivity {

    private ProgressBar loadingProgressBar;
    private SwipeRefreshLayout swipeRefreshLayout1;
    private TextView kwdTextView, twdTextView, hkdTextView, btcTextView, jpyTextView, kpwTextView, eurTextView, usdTextView, idrTextView, cadTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forex);
        swipeRefreshLayout1 = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout1);
        kwdTextView = (TextView) findViewById(R.id.kwdTextView);
        twdTextView = (TextView) findViewById(R.id.twdTextView);
        hkdTextView = (TextView) findViewById(R.id.hkdTextView);
        btcTextView = (TextView) findViewById(R.id.btcTextView);
        jpyTextView = (TextView) findViewById(R.id.jpyTextView);
        kpwTextView = (TextView) findViewById(R.id.kpwTextView);
        eurTextView = (TextView) findViewById(R.id.eurTextView);
        usdTextView = (TextView) findViewById(R.id.usdTextView);
        cadTextView = (TextView) findViewById(R.id.cadTextView);
        idrTextView = (TextView) findViewById(R.id.idrTextView);
        loadingProgressBar = (ProgressBar) findViewById(R.id.loadingProgressBar);

        initSwiperRefreshLayout();
        initForex();
    }
    private void initSwiperRefreshLayout(){
        swipeRefreshLayout1.setOnRefreshListener(()->{
            initForex();
            swipeRefreshLayout1.setRefreshing(false);
        });
    }
    public String formatNumber(double number, String format){
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(number);
    }
    private void initForex(){
        loadingProgressBar.setVisibility(TextView.VISIBLE);

        String url = "https://openexchangerates.org/api/latest.json?app_id=ea8c9aee7d254856b9954831065439b6";
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson = new Gson();
                ForexRootModel rootModel = gson.fromJson(new String(responseBody), ForexRootModel.class);
                ForexRatesModel ratesModel = rootModel.getRatesModel();

                double kwd = ratesModel.getIDR() / ratesModel.getKWD();
                double twd = ratesModel.getIDR() / ratesModel.getTWD();
                double hkd = ratesModel.getIDR() / ratesModel.getHKD();
                double jpy = ratesModel.getIDR() / ratesModel.getJPY();
                double btc = ratesModel.getIDR() / ratesModel.getBTC();
                double eur = ratesModel.getIDR() / ratesModel.getEUR();
                double kpw = ratesModel.getIDR() / ratesModel.getKPW();
                double cad = ratesModel.getIDR() / ratesModel.getCAD();
                double idr = ratesModel.getIDR();

                kwdTextView.setText(formatNumber(kwd, "###,##0.##"));
                twdTextView.setText(formatNumber(twd, "###,##0.##"));
                hkdTextView.setText(formatNumber(hkd, "###,##0.##"));
                jpyTextView.setText(formatNumber(jpy, "###,##0.##"));
                btcTextView.setText(formatNumber(btc, "###,##0.##"));
                eurTextView.setText(formatNumber(eur, "###,##0.##"));
                kpwTextView.setText(formatNumber(kpw, "###,##0.##"));
                usdTextView.setText(formatNumber(idr, "###,##0.##"));
                cadTextView.setText(formatNumber(cad, "###,##0.##"));
                idrTextView.setText(formatNumber(0, "###,##0.##"));

                loadingProgressBar.setVisibility(TextView.INVISIBLE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                loadingProgressBar.setVisibility(TextView.INVISIBLE);
            }
        });
    }
    }
