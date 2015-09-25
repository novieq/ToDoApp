package com.example.novieq.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> todoItems;
    ArrayAdapter<String> todoItemAdapter;
    ListView lvItems;
    EditText etEditText;
    int REQUEST_CODE=400;
    int pos;
    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //This is how the layout is tied to this activity
        setContentView(R.layout.activity_main);
        //populateArrayItems();
        readItems();
        todoItemAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,todoItems);
        lvItems = (ListView)findViewById(R.id.lvItems);
        lvItems.setAdapter(todoItemAdapter);
        etEditText = (EditText)findViewById(R.id.etEditText);
        //You can add events that can be listened to by the lvitem list view
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoItems.remove(position);
                todoItemAdapter.notifyDataSetChanged();
                writeItems();

                return true;
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String todoitem = (String) todoItems.get(position);
                Intent intent = new Intent(view.getContext(), EditItemActivity.class);
                intent.putExtra(EXTRA_MESSAGE, todoitem);
                //intent.putExtra("priority", todoitem.getPriority());
                //intent.putExtra("date", todoitem.getDateofcompletion());
                //pos = position;
                startActivityForResult(intent, REQUEST_CODE);


            }
        });

    }

    public void populateArrayItems() {
        todoItems = new ArrayList<>();
        //todoItems.add("Item 1");
        //todoItems.add("Item 2");
        //we need to pass the context(activity) and the resource file that needs to be passed for each row
        //todoItemAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,todoItems);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onAddItem(View view) {
        todoItemAdapter.add(etEditText.getText().toString());
        etEditText.setText("");
        writeItems();

    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            todoItems = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            todoItems = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, todoItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
