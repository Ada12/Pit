package com.example.zeducation;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ApplyAccountActivity extends Activity {
	private TextView accountId;
	private TextView accountPassword;
	private TextView accountValid;
	private TextView t1;
	private TextView t2;
	private TextView t3;
	private Button applyAccount;
	private String email;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apply_account);
		email = ((ApplicationTrans) getApplication()).getUsername();
		accountId = (TextView)this.findViewById(R.id.account_id);
		accountPassword = (TextView)this.findViewById(R.id.account_password);
		accountValid = (TextView)this.findViewById(R.id.account_valid);
		t1 = (TextView)this.findViewById(R.id.textView1);
		t2 = (TextView)this.findViewById(R.id.textView2);
		t3 = (TextView)this.findViewById(R.id.textView5);
		applyAccount = (Button)this.findViewById(R.id.apply_account);
		try {
//			String applyTempStatusUrl = "";
//			JSONObject applyTempStastusObject = new JSONObject();
//			applyTempStastusObject.put("email", email);
//			RetriveData rd = new RetriveData();
//			rd.setPOST_URL(applyTempStatusUrl);
//			String result = rd.sendPOST(applyTempStastusObject);
//			JSONObject resultObject = new JSONObject(result);
//≤‚ ‘¥˙¬Î
			JSONObject resultObject = new JSONObject();
			JSONObject testObject = new JSONObject();
			testObject.put("account_id", "iiiiiid");
			testObject.put("password", "ppppppp");
			testObject.put("is_valid", "vvvvvvv");
			resultObject.put("account_msg", testObject);
			resultObject.put("return_msg", "1");
//≤‚ ‘¥˙¬Î			
			String returnMsg = resultObject.getString("return_msg");
			if(returnMsg.equals("0")){
				accountId.setVisibility(View.GONE);
				accountPassword.setVisibility(View.GONE);
				accountValid.setVisibility(View.GONE);
				t1.setVisibility(View.GONE);
				t2.setVisibility(View.GONE);
				t3.setVisibility(View.GONE);
				applyAccount.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						try {
							String applyTempUrl = "";
							JSONObject applyTempObject = new JSONObject();
							applyTempObject.put("email", email);
							RetriveData rd = new RetriveData();
							rd.setPOST_URL(applyTempUrl);
							String result = rd.sendPOST(applyTempObject);
							JSONObject resultObject = new JSONObject(result);
							String returnMsg = resultObject.getString("return_msg");
							if(returnMsg.equals("0")){
								Toast.makeText(getApplicationContext(), "Application has been sent", Toast.LENGTH_SHORT).show(); 
							}else{
								Toast.makeText(getApplicationContext(), "Application sned failed, please try again", Toast.LENGTH_SHORT).show(); 
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
			}else{
				JSONObject accountMsg = resultObject.getJSONObject("account_msg");
				accountId.setText(accountMsg.getString("account_id"));
				accountPassword.setText(accountMsg.getString("password"));
				accountValid.setText(accountMsg.getString("is_valid"));
				applyAccount.setVisibility(View.GONE);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//		catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
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
        	Intent account = new Intent(ApplyAccountActivity.this, HomeActivity.class);
            startActivity(account);
            overridePendingTransition(R.anim.fade, R.anim.hold);  
            ApplyAccountActivity.this.finish();
            break; 
        case R.id.menu_apply_account:  
            Toast.makeText(this, "You are in this tab",  
                    Toast.LENGTH_SHORT).show();  
            break;
        case R.id.menu_logoff:  
        	Toast.makeText(this, "Logoff successfully",  
                    Toast.LENGTH_SHORT).show();  
            Intent logoff = new Intent(ApplyAccountActivity.this, LoginActivity.class);
            startActivity(logoff);
            ApplyAccountActivity.this.finish();
            break;  
        default:  
            break;  
        }  
        return super.onOptionsItemSelected(item);  
    }  
}
