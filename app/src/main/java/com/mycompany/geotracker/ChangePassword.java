/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.mycompany.geotracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ChangePassword extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        final Button cancel_button = (Button)findViewById(R.id.cancel);
        final Button submit_button = (Button)findViewById(R.id.submit);

        cancel_button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor sharedPreferenceEditor = sharedPreferences.edit();

                TextView old = (TextView)findViewById(R.id.old_password_field);
                TextView new_password = (TextView)findViewById(R.id.new_password_field);
                TextView check_new_password = (TextView)findViewById(R.id.repeated_password_field);
                String entered_old_password = old.getText().toString();
                String entered_new_password = new_password.getText().toString();
                String confirmed_new_password = check_new_password.getText().toString();
                String current_password = sharedPreferences.getString("password", "");

                if (entered_old_password.equals(current_password) &&
                        entered_new_password.equals(confirmed_new_password)) {
                    sharedPreferenceEditor.putString("password", entered_new_password);
                    sharedPreferenceEditor.apply();
//                    System.out.println(sharedPreferences.getString("password", ""));
                    toMyAccount();
                } else {
                    System.out.println("Wrong");
                }

                //check old password
                //check if new password match
                //change password
//                toMyAccount();
            }
        });

    }

    public void toMyAccount() {
        startActivity(new Intent(this, MyAccountActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_password, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
