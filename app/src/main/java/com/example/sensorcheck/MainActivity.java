package com.example.sensorcheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor light, temp, presure, humidity, step, stepCount, proxymity, alxelmeter;
    float currentValue, tempV, presureV, humidityV, stepV, stepCountV, proxyV, oalxelmeterV;
    String deviceName;
    String dateTime;
    Button btnUpdate;
    TextView tvDetails;
    String id;

    DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUpdate = findViewById(R.id.btnUpdate);
        tvDetails = findViewById(R.id.tvDetails);

        databaseUser = FirebaseDatabase.getInstance().getReference("device");

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        temp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        presure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        humidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        step = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        stepCount = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        proxymity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        alxelmeter = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        Log.d("", "onCreate: Sensors List" + deviceSensors);

        deviceName = android.os.Build.MODEL;
        dateTime = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = deviceName + "-" + databaseUser.push().getKey();
                String details = "Light : " + currentValue + "\n" + "Temp : " + tempV + "\n" +  "Presure : " + presureV + "\n" + "Humility :  : " + humidityV + "\n"
                        + "Step : " + stepV + "\n" +  "StepCount : " + stepCountV + "\n" + "Proximity : " + proxyV + "\n" + "Accel : " + oalxelmeterV ;
                tvDetails.setText(details);
                saveSensorData(id, deviceName, dateTime, currentValue, tempV, presureV, humidityV, stepV, stepCountV, proxyV, oalxelmeterV);
            }
        });

    }

    private void saveSensorData(String id, String deviceName, String dateTime, float currentValue, float tempV, float presureV,
                                float humidityV, float stepV, float stepCountV, float proximityV, float acceletorV) {
        Sensordetails sensordetails = new Sensordetails(id, deviceName, dateTime, currentValue, tempV, presureV, humidityV, stepV, stepCountV, proximityV,acceletorV);
        databaseUser.child(id).setValue(sensordetails);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        Sensor sensor = event.sensor;
        if (sensor.getType() == Sensor.TYPE_LIGHT) {
            currentValue = event.values[0];
        } else if (sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            tempV = event.values[0];
        } else if (sensor.getType() == Sensor.TYPE_PRESSURE) {
            presureV = event.values[0];
        } else if (sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            humidityV = event.values[0];
        } else if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            stepV = event.values[0];
        } else if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            stepCountV = event.values[0];
        } else if (sensor.getType() == Sensor.TYPE_PROXIMITY) {
            proxyV = event.values[0];
        } else if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            oalxelmeterV = event.values[0];

            Log.d("", "onCreate: Sensors Val " + "Device Name :" + deviceName + " " + currentValue + " " + tempV + " " + presureV + " " + humidityV);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();


        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, temp, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, presure, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, humidity, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, step, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, stepCount, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, proxymity, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, alxelmeter, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
