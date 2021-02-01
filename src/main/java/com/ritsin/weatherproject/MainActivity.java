package com.ritsin.weatherproject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import org.json.JSONObject;

import org.json.JSONException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;



public class MainActivity extends AppCompatActivity {
    String zipcode = "";
    
    /* Declaring all widgets */
    
    EditText zip;
    Button go;

    ImageView WeatherCurrentImage;
    TextView WeatherCurrentMain;
    TextView WeatherCurrentDesc;
    TextView WeatherCurrentTemp;

    TextView WeatherMainHour1;
    ImageView weatherImage1;
    TextView WeatherHour1Desc;
    TextView WeatherHour1Date;
    TextView WeatherHour1Low;
    TextView WeatherHour1High;

    TextView WeatherMainHour2;
    ImageView weatherImage2;
    TextView WeatherHour2Desc;
    TextView WeatherHour2Date;
    TextView WeatherHour2Low;
    TextView WeatherHour2High;

    TextView WeatherMainHour3;
    ImageView weatherImage3;
    TextView WeatherHour3Desc;
    TextView WeatherHour3Date;
    TextView WeatherHour3Low;
    TextView WeatherHour3High;

    TextView WeatherMainHour4;
    ImageView weatherImage4;
    TextView WeatherHour4Desc;
    TextView WeatherHour4Date;
    TextView WeatherHour4Low;
    TextView WeatherHour4High;

    TextView WeatherMainHour5;
    ImageView weatherImage5;
    TextView WeatherHour5Desc;
    TextView WeatherHour5Date;
    TextView WeatherHour5Low;
    TextView WeatherHour5High;

    TextView Location;
    ArrayList<String> quotes;
    TextView quote;
    ArrayList<String> artistList;
    TextView artist;

    TextView HrForecast;
    ImageView EZCImage;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Connecting all widget objects with the actual widget on the UI */
        
        zip = findViewById(R.id.id_editZip);
        go = findViewById(R.id.id_go);

        WeatherCurrentImage = findViewById(R.id.id_weatherImageCurrent);
        WeatherCurrentMain = findViewById(R.id.id_weatherCurrentText);
        WeatherCurrentDesc = findViewById(R.id.id_weatherCurrentDesc);
        WeatherCurrentTemp = findViewById(R.id.id_weatherCurrentTemp);

        WeatherMainHour1 = findViewById(R.id.id_weatherhour1);
        weatherImage1 = findViewById(R.id.id_weatherimagehour1);
        WeatherHour1Desc = findViewById(R.id.id_weatherhour1desc);
        WeatherHour1Date = findViewById(R.id.id_weatherHour1Time);
        WeatherHour1Low = findViewById(R.id.id_weatherhour1low);
        WeatherHour1High = findViewById(R.id.id_weatherhour1high);

        WeatherMainHour2 = findViewById(R.id.id_weatherhour2);
        weatherImage2 = findViewById(R.id.id_weatherimagehour2);
        WeatherHour2Desc = findViewById(R.id.id_weatherhour2desc);
        WeatherHour2Date = findViewById(R.id.id_weatherhour2date);
        WeatherHour2Low = findViewById(R.id.id_weatherhour2low);
        WeatherHour2High = findViewById(R.id.id_weatherhour2high);

        WeatherMainHour3 = findViewById(R.id.id_weatherhour3);
        weatherImage3 = findViewById(R.id.id_weatherimagehour3);
        WeatherHour3Desc = findViewById(R.id.id_weatherhour3desc);
        WeatherHour3Date = findViewById(R.id.id_weatherhour3date);
        WeatherHour3Low = findViewById(R.id.id_weatherhour3low);
        WeatherHour3High = findViewById(R.id.id_weatherhour3high);

        WeatherMainHour4 = findViewById(R.id.id_weatherhour4);
        weatherImage4 = findViewById(R.id.id_weatherimagehour4);
        WeatherHour4Desc =  findViewById(R.id.id_weatherhour4desc);
        WeatherHour4Date = findViewById(R.id.id_weatherhour4date);
        WeatherHour4Low = findViewById(R.id.id_weatherhour4low);
        WeatherHour4High = findViewById(R.id.id_weatherhour4high);

        WeatherMainHour5 = findViewById(R.id.id_weatherhour5);
        weatherImage5 = findViewById(R.id.id_weatherimagehour5);
        WeatherHour5Desc =  findViewById(R.id.id_weatherhour5desc);
        WeatherHour5Date = findViewById(R.id.id_weatherhour5date);
        WeatherHour5Low = findViewById(R.id.id_weatherhour5low);
        WeatherHour5High = findViewById(R.id.id_weatherhour5high);

        Location = findViewById(R.id.id_location);
        
        /* Creating an arraylist of quotes for different weathers */
        quotes = new ArrayList<String>();
        quotes.add("The girl told me Take off your jacket, I said Babes, man's not hot");//Sunny 0
        quotes.add("My lies right now are falling like the rain, So let the river run");//Rain 1
        quotes.add("So cold when it drop, it's gonna be a snow day");//Snow 2
        quotes.add("Rain on 'em, thunderstorm, rain on 'em");//Thunderstorm 3
        quotes.add("I see the clouds from my window, I pray the sun gon' shine this way");//Cloudy 4
        quotes.add("Legend says Syre still exists in the mist, in the fog");//Fog/Haze/Mist 5
        
        /* Creating an arrayList which contains the people who said the corresponding quote */
        artistList = new ArrayList<String>();
        artistList.add("(Mans Not Hot - Big Shaq)");//0
        artistList.add("(River - Eminem)");//1
        artistList.add("(iSpy - Kyle)");//2
        artistList.add("(XO Tour Life - Lil Uzi Vert)");//3
        artistList.add("(I Get Up - J. Cole)");//4
        artistList.add("(Ninety - Jaden Smith)");//5

        quote = findViewById(R.id.id_quote);
        artist = findViewById(R.id.id_artist);

        HrForecast = findViewById(R.id.id_15hourforecast);
        EZCImage = findViewById(R.id.EZCImage);



        /* Setting all weather images to invisible since 
        they are not required for the starting screen */
        
        WeatherCurrentImage.setVisibility(View.INVISIBLE);
        weatherImage1.setVisibility(View.INVISIBLE);
        weatherImage2.setVisibility(View.INVISIBLE);
        weatherImage3.setVisibility(View.INVISIBLE);
        weatherImage4.setVisibility(View.INVISIBLE);
        weatherImage5.setVisibility(View.INVISIBLE);
        HrForecast.setVisibility(View.INVISIBLE);
    
        
        /* When the go button is clicked */
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zipcode = zip.getText().toString();
                /* Creating a thread to the weather API */
                AsyncThread weaThread = new AsyncThread();
                weaThread.execute(zipcode);
            }
        });



    }
     
     /* The class cotaining the code for connecting and retriving data from the OpenWeatherAPI */    
     public class AsyncThread extends AsyncTask<String,Void,Void>{

         JSONObject weather;
         JSONObject weatherCurrent;

         @Override
         protected Void doInBackground(String... strings) {

             try {
                 /* Opening a connecting to the correct weather forcast URL for the zipcode entered */
                 URL keyForecast = new URL("http://api.openweathermap.org/data/2.5/forecast?zip="+strings[0]+"&APPID=5b6b4c7e300358ddb78ad9704d4d9e2d");
                 URLConnection connection = keyForecast.openConnection();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                 /* Reading the data from the corresponding weather forecast JSON object */
                 weather = new JSONObject(reader.readLine());
                 reader.close();
                 
                 /* Opening a connecting to the correct current weather URL for the zipcode entered */
                 URL keyCurrent = new URL("http://api.openweathermap.org/data/2.5/weather?zip="+strings[0]+"&APPID=5b6b4c7e300358ddb78ad9704d4d9e2d");
                 URLConnection connectionCurrent = keyCurrent.openConnection();
                 BufferedReader readerCurrent = new BufferedReader(new InputStreamReader(connectionCurrent.getInputStream()));
                 /* Reading the data from the corresponding current weather JSON object */
                 weatherCurrent = new JSONObject(readerCurrent.readLine());
                 readerCurrent.close();

             } catch (MalformedURLException e) {
                 e.printStackTrace();
             } catch (IOException e) {
                 e.printStackTrace();
             } catch (JSONException e) {
                 e.printStackTrace();
             }

             return null;
         }

         @Override
         protected void onPostExecute(Void aVoid) {
             super.onPostExecute(aVoid);



         try{
             Location.setText("Current Location: "+weatherCurrent.getString("name")+", "+weatherCurrent.getJSONObject("sys").getString("country"));
             HrForecast.setVisibility(View.VISIBLE);
             EZCImage.setVisibility(View.INVISIBLE);
             try {
                 WeatherCurrentMain.setText(weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main"));
                 WeatherCurrentDesc.setText(weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("description"));
                 WeatherCurrentTemp.setText(getTemp(weatherCurrent.getJSONObject("main").getDouble("temp"))+" F");
                 /* Setting the correct widgets for each different weather type (Clouds, Haze, Mist, Clear, Rain, Thunderstorm, Fog) */
                 
                 if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")) {
                     WeatherCurrentImage.setImageResource(R.drawable.cloudy);
                     WeatherCurrentImage.setVisibility(View.VISIBLE);
                     quote.setText("\""+quotes.get(4)+"\"");
                     artist.setText(artistList.get(4));
                 }
                 if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Haze")) {
                     WeatherCurrentImage.setImageResource(R.drawable.fog);
                     WeatherCurrentImage.setVisibility(View.VISIBLE);
                     quote.setText("\""+quotes.get(5)+"\"");
                     artist.setText(artistList.get(5));
                 }
                 if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Mist")) {
                     WeatherCurrentImage.setImageResource(R.drawable.fog);
                     WeatherCurrentImage.setVisibility(View.VISIBLE);
                     quote.setText("\""+quotes.get(5)+"\"");
                     artist.setText(artistList.get(5));
                 }
                 if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")) {
                     WeatherCurrentImage.setImageResource(R.drawable.sunny);
                     WeatherCurrentImage.setVisibility(View.VISIBLE);
                     quote.setText("\""+quotes.get(0)+"\"");
                     artist.setText(artistList.get(0));
                 }
                 if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")) {
                     WeatherCurrentImage.setImageResource(R.drawable.rain);
                     WeatherCurrentImage.setVisibility(View.VISIBLE);
                     quote.setText("\""+quotes.get(1)+"\"");
                     artist.setText(artistList.get(1));
                 }
                 if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")) {
                     WeatherCurrentImage.setImageResource(R.drawable.snow);
                     WeatherCurrentImage.setVisibility(View.VISIBLE);
                     quote.setText("\""+quotes.get(2)+"\"");
                     artist.setText(artistList.get(2));
                 }
                 if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunderstorm")) {
                     WeatherCurrentImage.setImageResource(R.drawable.thunderstorms);
                     WeatherCurrentImage.setVisibility(View.VISIBLE);
                     quote.setText("\""+quotes.get(3)+"\"");
                     artist.setText(artistList.get(3));
                 }
                 if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Fog")) {
                     WeatherCurrentImage.setImageResource(R.drawable.fog);
                     WeatherCurrentImage.setVisibility(View.VISIBLE);
                     quote.setText("\""+quotes.get(5)+"\"");
                     artist.setText(artistList.get(5));
                 }
             } catch (JSONException e) {
                 e.printStackTrace();
             }

             //Forecast 1 ( The first box in the 15 hour forecast scroll )
             try {
                 WeatherMainHour1.setText(weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main"));
                 WeatherHour1Desc.setText(weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("description"));
                 WeatherHour1Date.setText(getUnixTime(weatherCurrent.getInt("dt")));
                 WeatherHour1Low.setText("Low: "+getTemp(weatherCurrent.getJSONObject("main").getDouble("temp_min"))+" F");
                 WeatherHour1High.setText("High: "+getTemp(weatherCurrent.getJSONObject("main").getDouble("temp_max"))+" F");
                 //Log.d("TAAAG",weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main"));
                 if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")) {
                     weatherImage1.setImageResource(R.drawable.cloudy);
                     weatherImage1.setVisibility(View.VISIBLE);
                 }
                 if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Haze")) {
                     weatherImage1.setImageResource(R.drawable.fog);
                     weatherImage1.setVisibility(View.VISIBLE);
                 }
                 if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Mist")) {
                     weatherImage1.setImageResource(R.drawable.fog);
                     weatherImage1.setVisibility(View.VISIBLE);
                 }
                 if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")) {
                     weatherImage1.setImageResource(R.drawable.sunny);
                     weatherImage1.setVisibility(View.VISIBLE);
                 }
                 if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")) {
                     weatherImage1.setImageResource(R.drawable.rain);
                     weatherImage1.setVisibility(View.VISIBLE);
                 }
                 if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")) {
                     weatherImage1.setImageResource(R.drawable.snow);
                     weatherImage1.setVisibility(View.VISIBLE);
                 }
                 if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunderstorm")) {
                     weatherImage1.setImageResource(R.drawable.thunderstorms);
                     weatherImage1.setVisibility(View.VISIBLE);
                 }
                 if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Fog")) {
                     weatherImage1.setImageResource(R.drawable.fog);
                     weatherImage1.setVisibility(View.VISIBLE);
                 }
             } catch (JSONException e) {
                 e.printStackTrace();
             }
             //Forecast 1 End

             //Forecast 2 ( The second box in the 15 hour forecast scroll )
             try {
                 WeatherMainHour2.setText(weather.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main"));
                 //First is array second is obj, third is an array, 4th is an object, then String/Int/etc.
                 WeatherHour2Desc.setText(weather.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("description"));
                 WeatherHour2Date.setText(getDate(weather.getJSONArray("list").getJSONObject(1).getString("dt_txt"))+" "+getTime(weather.getJSONArray("list").getJSONObject(1).getString("dt_txt")));
                 WeatherHour2Low.setText("Low: "+getTemp(weather.getJSONArray("list").getJSONObject(1).getJSONObject("main").getDouble("temp_min"))+" F");
                 WeatherHour2High.setText("High: "+getTemp(weather.getJSONArray("list").getJSONObject(1).getJSONObject("main").getDouble("temp_max"))+" F");
                 if (weather.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")) {
                     weatherImage2.setImageResource(R.drawable.cloudy);
                     weatherImage2.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Haze")) {
                     weatherImage2.setImageResource(R.drawable.fog);
                     weatherImage2.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Mist")) {
                     weatherImage2.setImageResource(R.drawable.fog);
                     weatherImage2.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")) {
                     weatherImage2.setImageResource(R.drawable.sunny);
                     weatherImage2.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")) {
                     weatherImage2.setImageResource(R.drawable.rain);
                     weatherImage2.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")) {
                     weatherImage2.setImageResource(R.drawable.snow);
                     weatherImage2.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunderstorm")) {
                     weatherImage2.setImageResource(R.drawable.thunderstorms);
                     weatherImage2.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Fog")) {
                     weatherImage2.setImageResource(R.drawable.fog);
                     weatherImage2.setVisibility(View.VISIBLE);
                 }
             } catch (JSONException e) {
                 e.printStackTrace();
             }
             //Forecast 2 End

             //Forecast 3 ( The third box in the 15 hour forecast scroll )
             try {
                 WeatherMainHour3.setText(weather.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main"));
                 //First is array second is obj, third is an array, 4th is an object, then String/Int/etc.
                 WeatherHour3Desc.setText(weather.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("description"));
                 WeatherHour3Date.setText(getDate(weather.getJSONArray("list").getJSONObject(2).getString("dt_txt"))+" "+getTime(weather.getJSONArray("list").getJSONObject(2).getString("dt_txt")));
                 WeatherHour3Low.setText("Low: "+getTemp(weather.getJSONArray("list").getJSONObject(2).getJSONObject("main").getDouble("temp_min"))+" F");
                 WeatherHour3High.setText("High: "+getTemp(weather.getJSONArray("list").getJSONObject(2).getJSONObject("main").getDouble("temp_max"))+" F");
                 if (weather.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")) {
                     weatherImage3.setImageResource(R.drawable.cloudy);
                     weatherImage3.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Haze")) {
                     weatherImage3.setImageResource(R.drawable.fog);
                     weatherImage3.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Mist")) {
                     weatherImage3.setImageResource(R.drawable.fog);
                     weatherImage3.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")) {
                     weatherImage3.setImageResource(R.drawable.sunny);
                     weatherImage3.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")) {
                     weatherImage3.setImageResource(R.drawable.rain);
                     weatherImage3.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")) {
                     weatherImage3.setImageResource(R.drawable.snow);
                     weatherImage3.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunderstorm")) {
                     weatherImage3.setImageResource(R.drawable.thunderstorms);
                     weatherImage3.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Fog")) {
                     weatherImage3.setImageResource(R.drawable.fog);
                     weatherImage3.setVisibility(View.VISIBLE);
                 }
             } catch (JSONException e) {
                 e.printStackTrace();
             }
             //Forecast 3 End

             //Forecast 4 ( The fourth box in the 15 hour forecast scroll )
             try {
                 WeatherMainHour4.setText(weather.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main"));
                 //First is array second is obj, third is an array, 4th is an object, then String/Int/etc.
                 WeatherHour4Desc.setText(weather.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("description"));
                 WeatherHour4Date.setText(getDate(weather.getJSONArray("list").getJSONObject(3).getString("dt_txt"))+" "+getTime(weather.getJSONArray("list").getJSONObject(3).getString("dt_txt")));
                 WeatherHour4Low.setText("Low: "+getTemp(weather.getJSONArray("list").getJSONObject(3).getJSONObject("main").getDouble("temp_min"))+" F");
                 WeatherHour4High.setText("High: "+getTemp(weather.getJSONArray("list").getJSONObject(3).getJSONObject("main").getDouble("temp_max"))+" F");
                 if (weather.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")) {
                     weatherImage4.setImageResource(R.drawable.cloudy);
                     weatherImage4.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Haze")) {
                     weatherImage4.setImageResource(R.drawable.fog);
                     weatherImage4.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Mist")) {
                     weatherImage4.setImageResource(R.drawable.fog);
                     weatherImage4.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")) {
                     weatherImage4.setImageResource(R.drawable.sunny);
                     weatherImage4.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")) {
                     weatherImage4.setImageResource(R.drawable.rain);
                     weatherImage4.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")) {
                     weatherImage4.setImageResource(R.drawable.snow);
                     weatherImage4.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunderstorm")) {
                     weatherImage4.setImageResource(R.drawable.thunderstorms);
                     weatherImage4.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Fog")) {
                     weatherImage4.setImageResource(R.drawable.fog);
                     weatherImage4.setVisibility(View.VISIBLE);
                 }
             } catch (JSONException e) {
                 e.printStackTrace();
             }
             //Forecast 4 End

             //Forecast 5 ( The fifth box in the 15 hour forecast scroll )
             try {
                 WeatherMainHour5.setText(weather.getJSONArray("list").getJSONObject(4).getJSONArray("weather").getJSONObject(0).getString("main"));
                 //First is array second is obj, third is an array, 4th is an object, then String/Int/etc.
                 WeatherHour5Desc.setText(weather.getJSONArray("list").getJSONObject(4).getJSONArray("weather").getJSONObject(0).getString("description"));
                 WeatherHour5Date.setText(getDate(weather.getJSONArray("list").getJSONObject(4).getString("dt_txt"))+" "+getTime(weather.getJSONArray("list").getJSONObject(4).getString("dt_txt")));
                 WeatherHour5Low.setText("Low: "+getTemp(weather.getJSONArray("list").getJSONObject(4).getJSONObject("main").getDouble("temp_min"))+" F");
                 WeatherHour5High.setText("High: "+getTemp(weather.getJSONArray("list").getJSONObject(4).getJSONObject("main").getDouble("temp_max"))+" F");
                 if (weather.getJSONArray("list").getJSONObject(4).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")) {
                     weatherImage5.setImageResource(R.drawable.cloudy);
                     weatherImage5.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(4).getJSONArray("weather").getJSONObject(0).getString("main").equals("Haze")) {
                     weatherImage5.setImageResource(R.drawable.fog);
                     weatherImage5.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(4).getJSONArray("weather").getJSONObject(0).getString("main").equals("Mist")) {
                     weatherImage5.setImageResource(R.drawable.fog);
                     weatherImage5.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(4).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")) {
                     weatherImage5.setImageResource(R.drawable.sunny);
                     weatherImage5.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(4).getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")) {
                     weatherImage5.setImageResource(R.drawable.rain);
                     weatherImage5.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(4).getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")) {
                     weatherImage5.setImageResource(R.drawable.snow);
                     weatherImage5.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(4).getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunderstorm")) {
                     weatherImage5.setImageResource(R.drawable.thunderstorms);
                     weatherImage5.setVisibility(View.VISIBLE);
                 }
                 if (weather.getJSONArray("list").getJSONObject(4).getJSONArray("weather").getJSONObject(0).getString("main").equals("Fog")) {
                     weatherImage5.setImageResource(R.drawable.fog);
                     weatherImage5.setVisibility(View.VISIBLE);
                 }
             } catch (JSONException e) {
                 e.printStackTrace();
             }
             //Forecast 5 End


            }catch (Exception e){
             // Error Catch for invalid zip code
             Toast.makeText(getApplicationContext(),"Enter A Valid Zip Code",Toast.LENGTH_LONG).show();
         }
         }
        
         /* Method to get the current time */
         public String getTime(String dtText){
         
             String time = dtText.substring(11,13);
             String finalTime = "";
             if(time.equals("00")){
                finalTime = "7 PM";
             }
             if(time.equals("03")){
                 finalTime = "10 PM";
             }
             if(time.equals("06")){
                 finalTime = "1 AM";
             }
             if(time.equals("09")){
                 finalTime = "4 AM";
             }
             if(time.equals("12")){
                 finalTime = "7 AM";
             }
             if(time.equals("15")){
                 finalTime = "10 AM";
             }
             if(time.equals("18")){
                 finalTime = "1 PM";
             }
             if(time.equals("21")){
                 finalTime = "4 PM";
             }
             return finalTime;
         }
         
         /* Method to get the Unix Time */
         public String getUnixTime(int dtText){
             
             java.util.Date time=new java.util.Date((long)dtText*1000);
             String Time = time.toString();

             String month = Time.substring(4,7);
             if(month.equals("Jan")){
                 month = "01";
             }
             if(month.equals("Feb")){
                 month = "02";
             }
             if(month.equals("Mar")){
                 month = "03";
             }
             if(month.equals("Apr")){
                 month = "04";
             }
             if(month.equals("May")){
                 month = "05";
             }
             if(month.equals("Jun")){
                 month = "06";
             }
             if(month.equals("Jul")){
                 month = "07";
             }
             if(month.equals("Aug")){
                 month = "08";
             }
             if(month.equals("Sep")){
                 month = "09";
             }
             if(month.equals("Oct")){
                 month = "10";
             }
             if(month.equals("Nov")){
                 month = "11";
             }
             if(month.equals("Dec")){
                 month = "12";
             }

             String Day = " ";
             int day = Integer.parseInt(Time.substring(8,10));
             for(int i = 0; i < 32; i++){
                 if(day < 10 && day == i){
                     Day = "0"+i;
                 }
                 if(i >= 10 && i == day){
                     Day = ""+i;
                 }
             }

             String year = Time.substring(26,28);
             String date = month+"/"+Day+"/"+year;

             int Unixhour = Integer.parseInt(Time.substring(11,13));
             String hrPeriod = "";
             if(Unixhour > 11){
                hrPeriod = "PM";
             }
             if(Unixhour <= 11){
                 hrPeriod = "AM";
             }
             String hour = "";
             int Hour = Integer.parseInt(Time.substring(11,13));

             if(Hour == 0){
                 hour = "12";
             }else{
                 if(Hour < 13){
                    hour = ""+Hour;
                 }else{
                     if(Hour > 12){
                        int temp = Hour;
                        temp-=12;
                        hour = ""+temp;
                     }
                 }
             }
             String min = Time.substring(14,16);
             String finaltime = hour+":"+min+" "+hrPeriod;

             String finalDate = date+" "+finaltime;
             return  finalDate;
         }
         /* Methond to get the date */
         public String getDate(String dtText){

             String month = dtText.substring(5,7);
             String day = dtText.substring(8,10);


             String finalDate=month+"/"+day+"/18";

             return finalDate;
         }
        
         /* Methond to get the current temp */
         public int getTemp(double temp){
             double temptemp = temp*(1.8);
             double finaltemp = temptemp-459.67;
             double returntemp = Math.round(finaltemp * 100.0) / 100.0;
             String tempString = ""+returntemp;

             for(int i = 0; i < tempString.length(); i++){
                 if(tempString.charAt(i) == '.'){
                     String num = tempString.substring(i+1,i+2);
                     int decider = Integer.parseInt(num);

                     if(decider >= 5 && returntemp > 0){
                         returntemp+=1;
                     }
                     if(decider >= 5 && returntemp < 0){
                         returntemp-=1;
                     }
                 }
             }
             return (int)returntemp;
         }
     }
}


