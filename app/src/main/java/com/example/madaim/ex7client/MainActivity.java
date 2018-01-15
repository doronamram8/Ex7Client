package com.example.madaim.ex7client;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
        private final int MY_CALL_REQUEST=1;
    private final int REGISTER_REQUEST=5;

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
                    String url = ((EditText)findViewById(R.id.editText2)).getText().toString();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    if (!url.toLowerCase().contains("https://")){
                        url="https://"+url;
                    }
                    i.setData(Uri.parse(url));
                    startActivity(i);

                    break;
                }
                case R.id.button3:{
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.setType("text/html");
                    email.putExtra(email.EXTRA_EMAIL,new String[]{((EditText)findViewById(R.id.editText3)).getText().toString()});
                    email.putExtra(email.EXTRA_SUBJECT,"TEST");
                    email.putExtra(email.EXTRA_TEXT,"WORK");
                    startActivity(Intent.createChooser(email, "Choose an Email client :"));
                    break;
                }
                case R.id.button4:{
                    EditText etregister = (EditText)findViewById(R.id.editText4);
                    Intent reg = new Intent("com.ex7.action.REGISTER");
                    startActivityForResult(reg, REGISTER_REQUEST);

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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Resources res =getResources();
        String gender="";
        TextView tv = (TextView) findViewById(R.id.editText4);
        if (requestCode == REGISTER_REQUEST && resultCode == RESULT_OK) {
            if (data.getStringExtra("Gender").compareTo("Male") == 0) {
                gender ="Mr.";
            } else
                gender = "Mrs.";
        }
        tv.setText(res.getString(R.string.Welcome ,gender,data.getStringExtra("First Name"),data.getStringExtra("Last Name")));

    }


}
