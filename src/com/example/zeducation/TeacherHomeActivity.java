package com.example.zeducation;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class TeacherHomeActivity extends Activity {
	private String[] pattern = {"Pattern1","Pattern2","Pattern3","Pattern4"};
	private Spinner spinner;
	private ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teacher_home);
		spinner = (Spinner)this.findViewById(R.id.teacher_home_apply_course);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, pattern);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		//spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
		spinner.setVisibility(View.VISIBLE);
		Button apply = (Button)this.findViewById(R.id.teacher_home_apply_course_button);
		apply.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Applied,pending review", Toast.LENGTH_SHORT).show();  
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.teacher_menu, menu);  
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
        	Intent account = new Intent(TeacherHomeActivity.this, AllCoursesActivity.class);
            startActivity(account);
            overridePendingTransition(R.anim.fade, R.anim.hold);  
            TeacherHomeActivity.this.finish();
            break;  
        case R.id.menu_logoff:  
        	Toast.makeText(this, "Logoff successfully",  
                    Toast.LENGTH_SHORT).show(); 
            Intent logoff = new Intent(TeacherHomeActivity.this, LoginActivity.class);
            startActivity(logoff);
            TeacherHomeActivity.this.finish();
            break;  
        default:  
            break;  
        }  
        return super.onOptionsItemSelected(item);  
    }  

}
