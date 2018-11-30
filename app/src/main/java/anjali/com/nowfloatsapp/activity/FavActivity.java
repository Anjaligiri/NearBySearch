package anjali.com.nowfloatsapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import anjali.com.nowfloatsapp.R;
import anjali.com.nowfloatsapp.adapter.Listadapter;
import anjali.com.nowfloatsapp.database.DatabaseHandler;
import anjali.com.nowfloatsapp.model.Resturent;

public class FavActivity extends AppCompatActivity {
    ArrayList<Resturent> resturents;
    RecyclerView recyclerView;
    Listadapter listadapter;
    private String searchText;
    EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resturents = new ArrayList<>();
        resturents=new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DatabaseHandler databaseHandler=new DatabaseHandler(this);
       resturents=   databaseHandler.getAllContacts();
        listadapter=new Listadapter(this,resturents);
        recyclerView.setAdapter(listadapter);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
                if (arg0.length() == 1) {
                    recyclerView.setAdapter(listadapter);
                } else {
                    String text = searchEditText.getText().toString().toLowerCase(Locale.getDefault());
                    //  adapter.filter(text);
                    listadapter.filter(text);
                    System.out.println("searchtext" + text);
                    if(text.length()>=2)
                    {
                        if(resturents.size()<=0) {

                            Toast.makeText(getApplicationContext(), "Result not Found", Toast.LENGTH_SHORT).show();

                        }
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(recyclerView.getWindowToken(), 0);
                        //    mAdapter = new CustomerAccountInfoAdapter( CustomerAcountInfo.this,contactArrayList);
                        recyclerView.setAdapter(listadapter);
                    }
                }

            }
        });

    }
}
