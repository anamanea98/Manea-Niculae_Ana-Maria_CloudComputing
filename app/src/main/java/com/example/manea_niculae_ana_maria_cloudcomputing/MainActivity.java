package com.example.manea_niculae_ana_maria_cloudcomputing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public TextView tvTitluInfo;
    TextView tvAn;
    TextView tvDurata;
    TextView tvActori;
    TextView tvIntriga;
    TextView tvRatingIMDB;
    EditText search, anSearch;
    ImageView imageView;

    Dialog dlg = null;
    float marimeFont=18;

    public class GetMovieCode extends AsyncTask<String, Void, String> {
        String cod = null;
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                InputStream is = http.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String linie = null;
                StringBuilder builder = new StringBuilder();
                while((linie=reader.readLine())!=null){
                    builder.append(linie);
                }
                String textTotal =builder.toString();
                JSONObject obj = new JSONObject(textTotal);
                JSONArray vector =obj.getJSONArray("Search");
                JSONObject object = vector.getJSONObject(0);
                cod = object.getString("imdbID");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return cod;
        }
    }

    public class GetMovieInformation extends AsyncTask<String, Void, String[]>{

        @Override
        protected String[] doInBackground(String... strings) {
            String[] year_actors_runtime_plot_img = new String[7];

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                InputStream is = http.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String linie = null;
                StringBuilder builder = new StringBuilder();
                while((linie=reader.readLine())!=null){
                    builder.append(linie);
                }
                String textTotal =builder.toString();

                JSONObject obj = new JSONObject(textTotal);
                year_actors_runtime_plot_img[0] = obj.getString("Title");
                year_actors_runtime_plot_img[1] = obj.getString("Year");
                year_actors_runtime_plot_img[2] = obj.getString("Actors");
                year_actors_runtime_plot_img[3] = obj.getString("Runtime");
                year_actors_runtime_plot_img[4] = obj.getString("Plot");
                year_actors_runtime_plot_img[5] = obj.getString("Poster");
                year_actors_runtime_plot_img[6] = obj.getString("imdbRating");


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return year_actors_runtime_plot_img;
        }
    }

    private class GetImage extends AsyncTask<String, Void, Bitmap>{
        ImageView iv;

        public GetImage(ImageView img) {
            iv = img;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap =null;

            try{
                URL url = new URL(strings[0]);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                InputStream is = http.getInputStream();

                bitmap = BitmapFactory.decodeStream(is);
            }catch(Exception e){
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            iv.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitluInfo = findViewById(R.id.tvTitluInformatii);
        tvActori = findViewById(R.id.tvActori);
        tvIntriga = findViewById(R.id.tvIntriga);
        tvDurata = findViewById(R.id.tvDurata);
        tvAn = findViewById(R.id.tvAn);
        search = findViewById(R.id.etSearch);
        anSearch = findViewById(R.id.anSearch);
        tvRatingIMDB=findViewById(R.id.tvRatingIMDB);
        imageView = findViewById(R.id.imageView1);
    }

    String[] valori= null;

    public void metoda_afisare(final View view) {
        String titlu = search.getText().toString();
        String an = anSearch.getText().toString();
        search.setText("");
        anSearch.setText("");

        GetMovieCode getCode = new GetMovieCode(){
            @Override
            protected void onPostExecute(String s) {
                GetMovieInformation info = new GetMovieInformation(){
                    @Override
                    protected void onPostExecute(String[] strings) {
                        new GetImage(imageView).execute(strings[5]);
                        tvTitluInfo.setText(strings[0]);
                        tvAn.setText(strings[1]);
                        tvActori.setText(strings[2]);
                        tvDurata.setText(strings[3]);
                        tvIntriga.setText(strings[4]);
                        tvRatingIMDB.setText(strings[6]);

                    }
                };

                info.execute("http://www.omdbapi.com/?i="+s+"&apikey=c3ed8cba");

            }
        };
        if(anSearch != null){
            getCode.execute("http://www.omdbapi.com/?s="+titlu+"&apikey=c3ed8cba&y="+an);
        }else{
            getCode.execute("http://www.omdbapi.com/?s="+titlu+"&apikey=c3ed8cba");
        }


        try  {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }
    }

}
