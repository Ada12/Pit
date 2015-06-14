package com.example.zeducation;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	private String[] colledge = {"male","female"};
	private Spinner spinner;
	private ArrayAdapter<String> adapter;
	private EditText name;
	private EditText email;
	private EditText password;
	private EditText phone;
	private EditText organization;
	private EditText department;
	private EditText position;
	private EditText region;
	String genderSelect;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		spinner = (Spinner)this.findViewById(R.id.register_select_gender);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,colledge);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		//spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
		spinner.setVisibility(View.VISIBLE);
		name = (EditText)this.findViewById(R.id.name_edit);
		email = (EditText)this.findViewById(R.id.email_edit);
		password = (EditText)this.findViewById(R.id.password_edit);
		genderSelect = (String) spinner.getSelectedItem();
		phone = (EditText)this.findViewById(R.id.phone_edit);
		organization = (EditText)this.findViewById(R.id.organization_edit);
		department  = (EditText)this.findViewById(R.id.department_edit);
		position = (EditText)this.findViewById(R.id.position_edit);
		region = (EditText)this.findViewById(R.id.region_edit);
		
		Button submit = (Button)this.findViewById(R.id.signup_button);
        submit.setOnClickListener(new View.OnClickListener() {  
            
            @Override  
            public void onClick(View v) {  
                // TODO Auto-generated method stub+
            	if((name.getText().equals(null))||(email.getText().equals(null))||(password.getText().equals(null))||(phone.getText().equals(null))){
            		Toast.makeText(getApplicationContext(), "Please fill in the required item", Toast.LENGTH_SHORT).show(); 
            	}else if(!isEmail(email.getText().toString())){
            		Toast.makeText(getApplicationContext(), "Please check your Email format", Toast.LENGTH_SHORT).show(); 
            	}else if(password.getText().length() < 6){
            		Toast.makeText(getApplicationContext(), "The Password must be longer than 6 digits", Toast.LENGTH_SHORT).show(); 
            	}else if(!isMobileNO(phone.getText().toString())){
            		Toast.makeText(getApplicationContext(), "Please check your phone number", Toast.LENGTH_SHORT).show(); 
            	}else{
//            		try {
//            			RetriveData rd = new RetriveData();
//            			String registerUrl = "";
//            			rd.setPOST_URL(registerUrl);
//            			JSONObject registerData = new JSONObject();
//            			registerData.put("name", name.getText().toString());
//            			registerData.put("email", email.getText().toString());
//            			registerData.put("password", password.getText().toString());
//            			registerData.put("gender", genderSelect);
//            			registerData.put("phone", phone.getText().toString());
//            			registerData.put("organization", organization.getText().toString());
//            			registerData.put("department", department.getText().toString());
//            			registerData.put("position", position.getText().toString());
//            			registerData.put("region", region.getText().toString());
//            			String result = rd.sendPOST(registerData);
//            			JSONObject resultObject = new JSONObject(result);
//            			String returnMsg = resultObject.getString("return_msg");
//            			if(returnMsg.equals("0")){
            				Toast.makeText(getApplicationContext(), "Register successfully, Logging...", Toast.LENGTH_SHORT).show(); 
                    		Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    		Bundle bundle = new Bundle();
                    		bundle.putString("email", email.getText().toString());
                    		intent.putExtras(bundle);
                    		startActivity(intent);
                            RegisterActivity.this.finish();
//            			}else{
//            				Toast.makeText(getApplicationContext(), "Register failed, please try again", Toast.LENGTH_SHORT).show(); 
//            			}
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (JSONException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
            	}
                
            }  
        }); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	public boolean isMobileNO(String mobiles){     
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");     
        Matcher m = p.matcher(mobiles);        
        return m.matches();     
    } 
   
    public boolean isEmail(String email){     
     String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);     
        Matcher m = p.matcher(email);      
        return m.matches();     
    } 
}
