package vip.cdms.maimaihelper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/** @noinspection deprecation*/
public class NetworkImage extends AsyncTask<String, Void, Bitmap> {
    private static final int CONNECT_TIMEOUT = 5 * 1000;
    private static final int READ_TIMEOUT = 20 * 1000;

    public static void fetch(String url, ImageCallback callback) {
        new NetworkImage(callback).execute(url);
    }

    public interface ImageCallback {
        void onImageDownloaded(Bitmap bitmap);
    }

    private final ImageCallback callback;
    private NetworkImage(ImageCallback callback) {
        this.callback = callback;
    }

    /** @noinspection deprecation*/
    @Override
    protected Bitmap doInBackground(String... strings) {
        String image = strings[0];
        Bitmap bitmap = null;
        try {
            URL url = new URL(image);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(CONNECT_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setDoInput(true);
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (Exception ignored) {}
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        callback.onImageDownloaded(result);
    }
}
