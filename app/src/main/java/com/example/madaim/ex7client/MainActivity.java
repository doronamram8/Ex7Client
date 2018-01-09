package com.example.madaim.ex7client;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
        private final int MY_CALL_REQUEST=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
        public void onClick(View view){
            Toast.makeText(this,"hello",Toast.LENGTH_LONG).show();
            switch (view.getId()){
                case R.id.button: {
                    if (checkSelfPermission( Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED)
                    {
                        EditText ed = (EditText) findViewById(R.id.editText);
                        String number = ed.getText().toString();
                        CallPhone(number);
                    }
                    else
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE},MY_CALL_REQUEST);

                    break;
                }
                case  R.id.button2:{
                    break;
                }
                case R.id.button3:{
                    break;
                }
                case R.id.button4:{
                    break;
                }
            }
        }



    @SuppressWarnings({"MissingPermission"})
    private void CallPhone(String Phonenumber){
            String number =  Phonenumber ;
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" +number));
            startActivity(intent);
        }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_CALL_REQUEST:{
                if(grantResults.length>0&&
                        permissions[0].equals(Manifest.permission.CALL_PHONE)&&
                        grantResults[0]==PackageManager.PERMISSION_GRANTED) {

                }

                break;
            }
        }
    }
}
