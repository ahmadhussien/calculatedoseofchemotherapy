package com.example.calculatedoseofchemotherapy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    EditText ID_Patients,FName,LName,Name_of_Dose,DDUnit,date;
    Button submit;
    MyDataBase myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        myDb=new MyDataBase(this);
        ID_Patients=(EditText) findViewById(R.id.ET_idp);
        FName=(EditText) findViewById(R.id.ET_fName);
        LName=(EditText) findViewById(R.id.ED_lNAme);
        Name_of_Dose=(EditText) findViewById(R.id.ET_name_of_Dos);
        DDUnit=(EditText) findViewById(R.id.ET_Dose_Unit);
        date=(EditText) findViewById(R.id.ET_Date);
        DDUnit.setText(Double.toString(calculates.finalResDDU));
        submit=(Button) findViewById(R.id.b_submit);
        submit();
    }


    public void backr(View v) {
        // Do something in response to button click
        Intent i = new Intent(Registration.this, calculates.class);
        startActivity(i);
    }
    public  void  submit(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ID_Patients.getText().toString().trim().length() == 0 && FName.getText().toString().trim().length() == 0 && LName.getText().toString().trim().length() == 0 && Name_of_Dose.getText().toString().trim().length() == 0  && date.getText().toString().trim().length() == 0){
                    ID_Patients.setError("Your message");
                    FName.setError("Your message");
                    LName.setError("Your message");
                    Name_of_Dose.setError("Your message");
                    DDUnit.setError("Your message");
                    date.setError("Your message");
                    Toast.makeText(Registration.this,"Data not is Inserted ",Toast.LENGTH_LONG).show();


                }
                else {
                     myDb.insertData(ID_Patients.getText().toString(),FName.getText().toString(),LName.getText().toString(),Name_of_Dose.getText().toString(),DDUnit.getText().toString(),date.getText().toString());
                    Toast.makeText(Registration.this,"Data is Inserted ",Toast.LENGTH_LONG).show();


                }

            }
        });
    }
}