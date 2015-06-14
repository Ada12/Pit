package com.example.zeducation;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FindPasswordActivity extends Activity {

	private EditText oldPassword;
	private EditText newPassword;
	private EditText email;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_password);
		oldPassword = (EditText)this.findViewById(R.id.old_password_edit);
		newPassword = (EditText)this.findViewById(R.id.new_password_edit);
		email = (EditText)this.findViewById(R.id.email_edit);
		Button submit = (Button)this.findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {  
            
            @Override  
            public void onClick(View v) {  
                // TODO Auto-generated method stub 
            	if((oldPassword.getText().equals(null))||(newPassword.getText().equals(null))||(email.getText().equals(null))){
            		Toast.makeText(getApplicationContext(), "Please fill in the required item", Toast.LENGTH_SHORT).show();
            	}else if(newPassword.getText().length() < 6){
            		Toast.makeText(getApplicationContext(), "The Password must be longer than 6 digitsem", Toast.LENGTH_SHORT).show();
            	}else{
            		try {
            			String findPasswordUrl = "";
            			JSONObject findPasswordData = new JSONObject();
						findPasswordData.put("email", email.getText().toString());
						findPasswordData.put("old_password", oldPassword.getText().toString());
	            		findPasswordData.put("new_password", newPassword.getText().toString());
	            		RetriveData rd = new RetriveData();
	            		rd.setPOST_URL(findPasswordUrl);
	            		String result = rd.sendPOST(findPasswordData);
	            		JSONObject resultObject = new JSONObject(result);
	            		String return_msg = resultObject.getString("return_msg");
	            		if(return_msg.equals("0")){
	            			Toast.makeText(getApplicationContext(), "Change password successfully,please login again", Toast.LENGTH_SHORT).show(); 
	            			Intent intent = new Intent(FindPasswordActivity.this, LoginActivity.class);
                            startActivity(intent);
                            FindPasswordActivity.this.finish();
	            		}else if(return_msg.equals("1")){
	            			Toast.makeText(getApplicationContext(), "Incorrect old password,please try again", Toast.LENGTH_SHORT).show(); 
	            		}else{
	            			Toast.makeText(getApplicationContext(), "Change password failed,please try again", Toast.LENGTH_SHORT).show(); 
	            		}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		
            	}
            }  
        }); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.find_password, menu);
		return true;
	}

}
