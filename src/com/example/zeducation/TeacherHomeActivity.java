package com.example.zeducation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class TeacherHomeActivity extends Activity {
//	private String[] patternName = {"Pattern1","Pattern2","Pattern3","Pattern4"};
	private String[] patternName;
	private String[] patternDesc;
	private Spinner spinner;
	private ArrayAdapter<String> adapter;
	private String email;
	private ListView patternList;
	private EditText courseEdit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teacher_home);
		//�ж��Ƿ����״ε�½
		String myFlag = "1";
		if(((ApplicationTrans) getApplication()).getFlag() == null){
			((ApplicationTrans)getApplication()).setFlag(myFlag);
			Bundle bundle = this.getIntent().getExtras();
			email = bundle.getString("email");
			((ApplicationTrans)getApplication()).setUsername(email);
		}else{
			email = ((ApplicationTrans) getApplication()).getUsername();
		}
		
		try {
//			String patternUrl = "";
//			RetriveData rd = new RetriveData();
//			rd.setGET_URL(patternUrl);
//			String result = rd.sendGet();
//���Դ���			
			JSONObject test1 = new JSONObject();
			test1.put("pattern_name", "pattern1");
			test1.put("pattern_desc", "dhadhadddsdshd dashdaksdh dasjdhas uidakdaadakd");
			JSONObject test2 = new JSONObject();
			test2.put("pattern_name", "pattern2");
			test2.put("pattern_desc", "sdjsldjsads ssdhsadh  daskdhasasdhk adja");
			JSONObject test3 = new JSONObject();
			test3.put("pattern_name", "pattern2");
			test3.put("pattern_desc", "sdjsldjsads ssdhsadh  daskdhasasdhk adja");
			JSONArray patternMsg = new JSONArray();
			patternMsg.put(0, test1);
			patternMsg.put(1, test2);
			patternMsg.put(2, test3);
//���Դ���			
			//JSONArray patternMsg =  new JSONArray(result);
			patternName = new String[patternMsg.length()];
			patternDesc = new String[patternMsg.length()];
			for(int i=0; i<patternMsg.length(); i++){
				JSONObject pm = patternMsg.getJSONObject(i);
				patternName[i]  = pm.getString("pattern_name");
				patternDesc[i] = pm.getString("pattern_desc");
			}
			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		//ListView pattern����
		List<Map<String, String>> patternListMap = new ArrayList<Map<String, String>>();
		for(int i = 0; i<patternName.length; i++){
			Map<String, String> item = new HashMap<String, String>();
			item.put("pattern_name", patternName[i]);
			item.put("pattern_desc", patternDesc[i]);
			patternListMap.add(item);
		}
		SimpleAdapter adapterList = new SimpleAdapter(this, patternListMap, R.layout.pattern, 
				new String[]{"pattern_name","pattern_desc"}, new int[]{R.id.teacher_home_pattern_name,
				R.id.teacher_home_pattern_desc,});
		patternList = (ListView)this.findViewById(R.id.pattern_msg);
		patternList.setAdapter(adapterList);
		//���������˵�
		spinner = (Spinner)this.findViewById(R.id.teacher_home_apply_course);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, patternName);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setVisibility(View.VISIBLE);
		//���button����
		courseEdit = (EditText)this.findViewById(R.id.teacher_home_course_name_edit);
		Button apply = (Button)this.findViewById(R.id.teacher_home_apply_course_button);
		apply.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					String applyCourseUrl = "";
					RetriveData rd = new RetriveData();
					JSONObject applyCourseObject = new JSONObject();
					applyCourseObject.put("course_name", courseEdit.getText().toString());
					applyCourseObject.put("pattern_name", spinner.getSelectedItem().toString());
					applyCourseObject.put("email", email);
					rd.sendPOST(applyCourseObject);
					//�����￪ʼд
					Toast.makeText(getApplicationContext(), "Applied,pending review", Toast.LENGTH_SHORT).show();  
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
		getMenuInflater().inflate(R.menu.teacher_menu, menu);  
        return true;  
	}
	
	@Override  
    public boolean onOptionsItemSelected(MenuItem item) {  
        switch (item.getItemId()) {  
        case R.id.menu_apply_course:  
            Toast.makeText(this, "You are in this tab",  
                    Toast.LENGTH_SHORT).show();  
            break;
        case R.id.menu_apply_history:  
        	Intent history = new Intent(TeacherHomeActivity.this, ApplyHistoryActivity.class);
            startActivity(history);
            overridePendingTransition(R.anim.fade, R.anim.hold);  
            TeacherHomeActivity.this.finish();
            break; 
        case R.id.menu_all_course:  
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
	
	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event)  
    {  
        if (keyCode == KeyEvent.KEYCODE_BACK )  
        {  
            // �����˳��Ի���  
            AlertDialog isExit = new AlertDialog.Builder(this).create();  
            // ���öԻ������  
            isExit.setTitle("System Prompt");  
            // ���öԻ�����Ϣ  
            isExit.setMessage("Are you sure you want to exit the application?");  
            // ���ѡ��ť��ע�����  
            isExit.setButton("Confirm", listener);  
            isExit.setButton2("Cancel", listener);  
            // ��ʾ�Ի���  
            isExit.show();  
  
        }  
          
        return false;  
          
    }  
    /**�����Ի��������button����¼�*/  
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()  
    {  
        public void onClick(DialogInterface dialog, int which)  
        {  
            switch (which)  
            {  
            case AlertDialog.BUTTON_POSITIVE:// "ȷ��"��ť�˳�����  
                finish();  
                break;  
            case AlertDialog.BUTTON_NEGATIVE:// "ȡ��"�ڶ�����ťȡ���Ի���  
                break;  
            default:  
                break;  
            }  
        }  
    }; 

}
