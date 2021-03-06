package com.example.zeducation;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity{
	public String email;
	private String[] colledge = {"Tongji University","Colledge1","Colledge2","Colledge3","Colledge4"};
	private Spinner spinner;
	private ArrayAdapter<String> adapter;
	private TextView applyStatus;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		spinner = (Spinner)this.findViewById(R.id.home_apply_select_colledge);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,colledge);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		//spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
		spinner.setVisibility(View.VISIBLE);
		String myFlag = "1";
		if(((ApplicationTrans) getApplication()).getFlag() == null){
			((ApplicationTrans)getApplication()).setFlag(myFlag);
			Bundle bundle = this.getIntent().getExtras();
			email = bundle.getString("email");
			((ApplicationTrans)getApplication()).setUsername(email);
		}else{
			email = ((ApplicationTrans) getApplication()).getUsername();
		}
		applyStatus = (TextView)this.findViewById(R.id.home_apply_status);
		Button apply = (Button)this.findViewById(R.id.home_apply_teacher_button);
//		try {
//			String applyStatusUrl = "";
//			JSONObject applyStatusObject = new JSONObject();
//			applyStatusObject.put("email", email);
//			RetriveData rd = new RetriveData();
//			rd.setPOST_URL(applyStatusUrl);
//			String result = rd.sendPOST(applyStatusObject);
//			JSONObject resultObject = new JSONObject(result);
//			String returnMsg = resultObject.getString("return_msg");
			String returnMsg = "0";
			if(returnMsg.equals("0")){
				applyStatus.setText("Send an application now");
				//applyStatus.setText(email);
			}else if(returnMsg.equals("1")){
				applyStatus.setText("Applied, pending review");
				spinner.setVisibility(View.GONE);
				apply.setVisibility(View.GONE);
			}else if(returnMsg.equals("2")){
				applyStatus.setText("Applied,  application denied");
			}else{
				applyStatus.setText("Application approved, please login again");
				spinner.setVisibility(View.GONE);
				apply.setVisibility(View.GONE);
			}			
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		apply.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					String applyTeacherUrl = "";
					JSONObject applyTeacherObject = new JSONObject();
					applyTeacherObject.put("email", email);
					applyTeacherObject.put("college_name", spinner.getSelectedItem());
					RetriveData rd = new RetriveData();
					rd.setPOST_URL(applyTeacherUrl);
					String result = rd.sendPOST(applyTeacherObject);
					JSONObject resultObject = new JSONObject(result);
					String returnMsg = resultObject.getString("return_msg");
					if(returnMsg.equals("0")){
						Toast.makeText(getApplicationContext(), "Application has been sent", Toast.LENGTH_SHORT).show();  
					}else{
						Toast.makeText(getApplicationContext(), "Application send failed, please try again", Toast.LENGTH_SHORT).show();  
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);  
        return true;  
	}
	
	@Override  
    public boolean onOptionsItemSelected(MenuItem item) {  
        switch (item.getItemId()) {  
        case R.id.menu_apply_teacher:  
            Toast.makeText(this, "You are in this tab",  
                    Toast.LENGTH_SHORT).show();  
            break;
        case R.id.menu_apply_account:  
        	Intent account = new Intent(HomeActivity.this, ApplyAccountActivity.class);
            startActivity(account);
            overridePendingTransition(R.anim.fade, R.anim.hold);  
            HomeActivity.this.finish();
            break;  
        case R.id.menu_logoff:  
        	Toast.makeText(this, "Logoff successfully",  
                    Toast.LENGTH_SHORT).show(); 
            Intent logoff = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(logoff);
            HomeActivity.this.finish();
            break;  
        default:  
            break;  
        }  
        return super.onOptionsItemSelected(item);  
    }

}
