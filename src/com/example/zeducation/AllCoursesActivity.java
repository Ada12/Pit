package com.example.zeducation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class AllCoursesActivity extends Activity {

	private ListView lt1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_courses);
		List<Map<String, String>> listItems = new ArrayList<Map<String, String>>(); 
		for(int i = 0; i < 10; i++){
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", "pattern1");
			map.put("dec", "decription");
			map.put("state", "open");
			map.put("beginTime", "2015-3-1");
			map.put("endTime", "2015-8-1");
			listItems.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.course, 
				new String[]{"name","dec","state","beginTime","endTime"}, new int[]{R.id.all_course_pattern_name,
																					R.id.all_course_pattern_desc,
																					R.id.all_course_state,
																					R.id.all_course_apply_time,
																					R.id.all_course_response_time});
		lt1=(ListView)findViewById(R.id.lt1);  
        lt1.setAdapter(adapter);  
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
        	Intent account = new Intent(AllCoursesActivity.this, TeacherHomeActivity.class);
            startActivity(account);
            overridePendingTransition(R.anim.fade, R.anim.hold);  
            AllCoursesActivity.this.finish();
            break; 
        case R.id.menu_apply_account:  
            Toast.makeText(this, "You are in this tab",  
                    Toast.LENGTH_SHORT).show();  
            break;
        case R.id.menu_logoff:  
        	Toast.makeText(this, "Logoff successfully",  
                    Toast.LENGTH_SHORT).show();  
            Intent logoff = new Intent(AllCoursesActivity.this, LoginActivity.class);
            startActivity(logoff);
            AllCoursesActivity.this.finish();
            break;  
        default:  
            break;  
        }  
        return super.onOptionsItemSelected(item);  
    } 

}
