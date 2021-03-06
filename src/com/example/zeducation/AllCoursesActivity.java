package com.example.zeducation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.view.ViewGroup;
import org.w3c.dom.Text;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AllCoursesActivity extends Activity {

	private ListView courseList;
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
																					R.id.all_course_response_time})
		{
			 @Override
			 public View getView(int position, View convertView,ViewGroup parent){
				final int p = position;
				final View view = super.getView(position, convertView, parent);
				Button button = (Button)view.findViewById(R.id.all_course_close_button);
				button.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new AlertDialog.Builder(AllCoursesActivity.this).setTitle("Delete")
						.setMessage("You selected " + String.valueOf(p))
						.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Toast.makeText(getApplicationContext(), "You click the yes button", Toast.LENGTH_SHORT).show();  
							}
						}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Toast.makeText(getApplicationContext(), "You click the cancel button", Toast.LENGTH_SHORT).show();  
							}
						}).create().show();
					}
				});
				return view;
			 }
		};
		courseList=(ListView)findViewById(R.id.lt1);
		courseList.setAdapter(adapter);  
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
        case R.id.menu_apply_course:  
        	Intent account = new Intent(AllCoursesActivity.this, TeacherHomeActivity.class);
            startActivity(account);
            overridePendingTransition(R.anim.fade, R.anim.hold);  
            AllCoursesActivity.this.finish();
            break; 
        case R.id.menu_apply_history:  
        	Intent history = new Intent(AllCoursesActivity.this, ApplyHistoryActivity.class);
            startActivity(history);
            overridePendingTransition(R.anim.fade, R.anim.hold);  
            AllCoursesActivity.this.finish();
            break; 
        case R.id.menu_all_course:  
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
	
	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event)  
    {  
        if (keyCode == KeyEvent.KEYCODE_BACK )  
        {  
            // 创建退出对话框  
            AlertDialog isExit = new AlertDialog.Builder(this).create();  
            // 设置对话框标题  
            isExit.setTitle("System Prompt");  
            // 设置对话框消息  
            isExit.setMessage("Are you sure you want to exit the application?");  
            // 添加选择按钮并注册监听  
            isExit.setButton("Confirm", listener);  
            isExit.setButton2("Cancel", listener);  
            // 显示对话框  
            isExit.show();  
  
        }  
          
        return false;  
          
    }  
    /**监听对话框里面的button点击事件*/  
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()  
    {  
        public void onClick(DialogInterface dialog, int which)  
        {  
            switch (which)  
            {  
            case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序  
                finish();  
                break;  
            case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框  
                break;  
            default:  
                break;  
            }  
        }  
    }; 

}
