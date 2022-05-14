package com.example.calculatedoseofchemotherapy;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Patients extends MainActivity {
    MyDataBase myDb;
    EditText ET_Search;
    Button b_viewAll,b_Delete,b_search;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients);
        myDb=new MyDataBase(this);
        ET_Search=(EditText) findViewById(R.id.ET_search);
        b_viewAll=(Button) findViewById(R.id.b_view_all);
        b_search=(Button) findViewById(R.id.b_search);
        b_Delete=(Button) findViewById(R.id.b_delete);
        Deleted();
        ViewAll();
        searchID();














    }
    public void Deleted(){
        b_Delete.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Integer deletedRows =myDb.deleteData(ET_Search.getText().toString());
                                            if (deletedRows > 0)
                                                Toast.makeText(Patients.this,"Data Deletd ",Toast.LENGTH_LONG).show();
                                            else
                                                Toast.makeText(Patients.this,"Data not Deletd ",Toast.LENGTH_LONG).show();
                                        }
                                    }

        );

    }
    public void ViewAll(){
        b_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if(res == null){
                    showMessage("error","notheing found");
                    return;
                }
                StringBuffer buffer= new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID : "+res.getString(0)+"\n");
                    buffer.append("ID Patients : "+res.getString(1)+"\n");
                    buffer.append("first Name: "+res.getString(2)+"\n");
                    buffer.append("last Name: "+res.getString(3)+"\n");
                    buffer.append("name of Dose: "+res.getString(4)+"\n");
                    buffer.append("desired dose unit : "+res.getString(5)+"\n");
                    buffer.append("Date : "+res.getString(6)+"\n\n");
                }
                showMessage("Data",buffer.toString());





            }
        });
    }
    public void searchID(){
        b_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.search(ET_Search.getText().toString());
                if(res==null){
                    showMessage("error","notheing found");
                    return;
                }
                StringBuffer buffer= new StringBuffer();

                    do{
                        /*String res... your stuff*/
                        buffer.append("ID : "+res.getString(0)+"\n");
                        buffer.append("ID Patients : "+res.getString(1)+"\n");
                        buffer.append("first Name: "+res.getString(2)+"\n");
                        buffer.append("last Name: "+res.getString(3)+"\n");
                        buffer.append("name of Dose: "+res.getString(4)+"\n");
                        buffer.append("desired dose unit : "+res.getString(5)+"\n");
                        buffer.append("Date : "+res.getString(6)+"\n\n");
                        showMessage("Data",buffer.toString());
                    }
                    while(res.moveToNext());

            }



        });

    }




    public void showMessage (String title ,String Message){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void backv(View v) {
        // Do something in response to button click
        Intent i = new Intent(Patients.this, MainActivity.class);
        startActivity(i);
    }

}