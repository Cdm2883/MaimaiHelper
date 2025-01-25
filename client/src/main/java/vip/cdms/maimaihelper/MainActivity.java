package vip.cdms.maimaihelper;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    ServerConfig config;

    ImageView imageView;
    LinearLayout configLayout;
    EditText urlInput;
    EditText tokenInput;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        config = new ServerConfig(getPreferences(MODE_PRIVATE));

        imageView = findViewById(R.id.image);
        configLayout = findViewById(R.id.config);
        urlInput = findViewById(R.id.url);
        tokenInput = findViewById(R.id.token);
        saveButton = findViewById(R.id.save);

        imageView.setOnClickListener(v -> refreshQr());
        imageView.setOnLongClickListener(v -> {
            imageView.setVisibility(ImageView.GONE);
            configLayout.setVisibility(LinearLayout.VISIBLE);
            return true;
        });

        urlInput.setText(config.getUrl());
        tokenInput.setText(config.getToken());

        saveButton.setOnClickListener(v -> {
            config.setUrl(urlInput.getText().toString());
            config.setToken(tokenInput.getText().toString());
            configLayout.setVisibility(LinearLayout.GONE);
            imageView.setVisibility(ImageView.VISIBLE);
        });

        refreshQr();
    }

    void refreshQr() {
        String url = config.getUrl() + "?token=" + config.getToken();
        imageView.setImageDrawable(new ColorDrawable(Color.YELLOW));
        NetworkImage.fetch(url, bitmap -> {
            if (bitmap == null) imageView.setImageDrawable(new ColorDrawable(Color.RED));
            else imageView.setImageBitmap(bitmap);
        });
    }
}
