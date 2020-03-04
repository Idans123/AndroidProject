package com.example.hireyouski;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PriceAndRatingActivity extends AppCompatActivity {
    private final int SNOWBOARD_PRICE_PER_DAY=20;
    private final int SKI_PRICE_PER_DAY=15;
    private int price;
    private EditText rateUsET;
    private Button submitBtn;
    private WebView webView;
    private TextView selectedResort;
    private final String URL="https://www.snow-forecast.com/resorts/Val-Thorens/6day/mid";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_and_rating_activity);
        int userChoice = getIntent().getIntExtra("userChoice",3);
        price=calculatePrice(getIntent().getIntExtra("numberOfDays",3),userChoice);
        TextView priceTextView = findViewById(R.id.priceTV);
        priceTextView.setText(priceTextView.getText()+" "+price+" €");
        rateUsET = findViewById(R.id.rateUsET);
        submitBtn=findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a;
                String numOfStarsStr = rateUsET.getText().toString();
                if(numOfStarsStr.matches("[1-5]")) {
                    int numOfStars = Integer.parseInt(numOfStarsStr);
                    LinearLayout starsLayout = findViewById(R.id.starsLLayout);
                    starsLayout.removeAllViews();
                    for(int i=0;i<numOfStars;i++) {
                        ImageButton imageButton = new ImageButton(PriceAndRatingActivity.this);
                        imageButton.setBackgroundResource(R.drawable.star_icon);
                        imageButton.setScaleType(ImageView.ScaleType.FIT_XY);
                        starsLayout.addView(imageButton,100,100);
                    }
                    Toast.makeText(PriceAndRatingActivity.this,R.string.thank_you_toast,Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(PriceAndRatingActivity.this,R.string.stars_error,Toast.LENGTH_SHORT).show();
                }
            }
        });
        selectedResort = findViewById(R.id.selectedResortTV);
        selectedResort.setText(getString(R.string.enjoy)+" : "+getIntent().getStringExtra("resort"));

        webView = findViewById(R.id.webView);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(URL);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }

    private int calculatePrice(int numberOfDays, int choice){
        choice= choice==1 ? SKI_PRICE_PER_DAY : SNOWBOARD_PRICE_PER_DAY;
        return choice*numberOfDays;
    }
}
