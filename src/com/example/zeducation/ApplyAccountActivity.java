package com.example.zeducation;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ApplyAccountActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apply_account);
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
