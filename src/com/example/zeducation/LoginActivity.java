package com.example.zeducation;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.zeducation.R.color;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private EditText email;
	private EditText password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		email = (EditText)this.findViewById(R.id.username_edit);
		password = (EditText)this.findViewById(R.id.password_edit);
		Button submit = (Button)this.findViewById(R.id.signin_button);
        submit.setOnClickListener(new View.OnClickListener() {  
            
            @Override  
            public void onClick(View v) {  
                // TODO Auto-generated method stub 
            	if((email.getText().equals(null))||password.getText().equals(null)){
            		Toast.makeText(getApplicationContext(), "Please fill in the required item", Toast.LENGTH_SHORT).show();  
            	}else{
//            		try {
//            			String loginUrl = "";
//            			JSONObject loginData = new JSONObject();
//						loginData.put("email", email.getText().toString());
//						loginData.put("password", password.getText().toString());
//						RetriveData rd = new RetriveData();
//						rd.setPOST_URL(loginUrl);
//						String result = rd.sendPOST(loginData);
//						JSONObject resultObject = new JSONObject(result);
//						String role = resultObject.getString("role");
//						if(role.equals("user")){
//							Toast.makeText(getApplicationContext(), "Logging", Toast.LENGTH_SHORT).show();  
//			                Intent intentUser = new Intent(LoginActivity.this, HomeActivity.class);
//			                startActivity(intentUser);
//			                LoginActivity.this.finish();
//						}else if(role.equals("teacher")){
							Toast.makeText(getApplicationContext(), "Logging", Toast.LENGTH_SHORT).show();  
			                Intent intentTech = new Intent(LoginActivity.this, TeacherHomeActivity.class);
			                Bundle bundle = new Bundle();
                    		bundle.putString("email", email.getText().toString());
                    		intentTech.putExtras(bundle);
			                startActivity(intentTech);
			                LoginActivity.this.finish();
//						}else{
//							Toast.makeText(getApplicationContext(), "Logging to Admin", Toast.LENGTH_SHORT).show();  
//						}
//					} catch (JSONException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
            		
            	}
            }  
        }); 
        TextView findPassword = (TextView)this.findViewById(R.id.login_forgot_password);
        findPassword.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this, FindPasswordActivity.class);
				startActivity(intent);
			}
		});
        TextView signUp = (TextView)this.findViewById(R.id.register_link);
        signUp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
