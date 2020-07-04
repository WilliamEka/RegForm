package com.example.android.aegis_proto5;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.aegis_proto5.Model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "MainActivity";

    FirebaseDatabase database;
    DatabaseReference users;

    TextView editQty, editDate, luckyDip;
    EditText editSpk, editSalesName, editSalesPhone, editCustName, editCustPhone, editWarna, editTandaJadi;
    Button btnSignUp, decrementBtn, incrementBtn;
    DatePickerDialog.OnDateSetListener dateSetListener;
    Spinner spinnerCabang, spinnerJenisKend, spinnerJenisPemb, spinnerShift, spinnerLuckyDip;
    int counter = 0;
    TextView textViewSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        users = database.getReference();

        luckyDip = (TextView)findViewById(R.id.luckyDip);

        editSpk = (EditText) findViewById(R.id.editSpk);
        editSalesPhone = (EditText) findViewById(R.id.editSalesPhone);
        editSalesName = (EditText) findViewById(R.id.editSalesName);
        editCustPhone = (EditText) findViewById(R.id.editCustPhone);
        editCustName = (EditText) findViewById(R.id.editCustName);
        editWarna = (EditText)findViewById(R.id.editWarna);
        editTandaJadi = (EditText)findViewById(R.id.editTandaJadi);

        editDate = (TextView) findViewById(R.id.editDate);
        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        dateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy: " + day + month + year);

                String date = day + "/" + month + "/" + year;
                editDate.setText(date);
            }
        };

        final Spinner spinnerCabang = (Spinner)findViewById(R.id.spinnerCabang);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cabang, android.R.layout.simple_list_item_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCabang.setAdapter(adapter);
        spinnerCabang.setOnItemSelectedListener(this);

        final Spinner spinnerJenisKend = (Spinner)findViewById(R.id.spinnerJenis);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.tipeKend, android.R.layout.simple_list_item_1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJenisKend.setAdapter(adapter1);
        spinnerJenisKend.setOnItemSelectedListener(this);

//        spinnerJenisKend.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String jenisKend = parent.getItemAtPosition(position).toString();
////                Toast.makeText(parent.getContext(), "Selected: " + jenisKend, Toast.LENGTH_SHORT).show();
//
//                if(parent.getItemAtPosition(position).equals("Xpander Ultimate")){
//                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
//                    View mView = getLayoutInflater().inflate(R.layout.dialog_spinner, null);
//                    Spinner mSpinner = (Spinner)mView.findViewById(R.id.spinnerDialog);
//                    ArrayAdapter<String> adapterDialog = new ArrayAdapter<String>(MainActivity.this,
//                            android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.xpander));
//                    adapterDialog.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    mSpinner.setAdapter(adapterDialog);
//
//                    mBuilder.setView(mView);
//                    AlertDialog dialog = mBuilder.create();
//                    dialog.show();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        final Spinner spinnerLuckyDip = (Spinner)findViewById(R.id.spinnerLuckyDip);
        ArrayAdapter<CharSequence> adapterLuckyDip = ArrayAdapter.createFromResource(this, R.array.luckyDip, android.R.layout.simple_list_item_1);
        adapterLuckyDip.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLuckyDip.setAdapter(adapterLuckyDip);
        spinnerLuckyDip.setOnItemSelectedListener(this);

        final Spinner spinnerJenisPemb = (Spinner)findViewById(R.id.spinnerPayment);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.jenisPembayaran, android.R.layout.simple_list_item_1);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJenisPemb.setAdapter(adapter2);
        spinnerJenisPemb.setOnItemSelectedListener(this);

        final Spinner spinnerShift = (Spinner)findViewById(R.id.spinnerShift);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.shift, android.R.layout.simple_list_item_1);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerShift.setAdapter(adapter3);
        spinnerShift.setOnItemSelectedListener(this);

        editQty = (TextView)findViewById(R.id.editQty);
        decrementBtn = (Button)findViewById(R.id.decrementBtn);
        incrementBtn = (Button)findViewById(R.id.incrementBtn);

        editQty.setText("0");

        decrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = counter - 1;
                if(counter <= 0){
                    counter = 0;
                }
                editQty.setText(String.valueOf(counter));
            }
        });

        incrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = counter + 1;
                editQty.setText(String.valueOf(counter));
            }
        });

        textViewSignIn = (TextView) findViewById(R.id.textViewSignIn);
        textViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(s);
            }
        });

        btnSignUp = (Button) findViewById(R.id.btnRegister);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final User user = new User(
                        editSpk.getText().toString(),
                        editSalesName.getText().toString(),
                        editSalesPhone.getText().toString(),
                        editCustName.getText().toString(),
                        editCustPhone.getText().toString(),
                        editDate.getText().toString(),
                        spinnerCabang.getSelectedItem().toString(),
                        editQty.getText().toString(),
                        editTandaJadi.getText().toString(),
                        spinnerJenisKend.getSelectedItem().toString(),
                        spinnerLuckyDip.getSelectedItem().toString(),
                        spinnerJenisPemb.getSelectedItem().toString(),
                        editWarna.getText().toString(),
                        spinnerShift.getSelectedItem().toString());

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(user.getNoSpk().isEmpty()) {
                            editSpk.setError("Please enter No. SPK");
                            editSpk.requestFocus();
                            }else if (user.getPickDate().isEmpty()){
                                editDate.setError("Please choose current date");
                                editDate.requestFocus();
                            }else if(user.getSalesName().isEmpty()){
                                editSalesName.setError("Please enter your name");
                                editSalesName.requestFocus();
                            }else if(user.getSalesPhone().isEmpty() || user.getSalesPhone().length() < 10){
                                editSalesPhone.setError("Please enter valid phone number");
                                editSalesPhone.requestFocus();
                            }else if(user.getCustName().isEmpty()){
                                editCustName.setError("Please enter customer's name");
                                editCustName.requestFocus();
                            }else if(user.getCustPhone().isEmpty() || user.getCustPhone().length() < 10){
                                editCustPhone.setError("Please enter a valid phone number");
                                editCustPhone.requestFocus();
                            }else if(user.getWarnaKendaraan().isEmpty()){
                                editWarna.setError("Please enter color");
                                editWarna.requestFocus();
                            }else if(user.getTotalQty().equals("0")){
                                editQty.setError("Min. transaction is 1 unit");
                                editQty.requestFocus();
                            }else if(user.getTotalTandaJadi().isEmpty()){
                                editTandaJadi.setError("Min. transaction is Rp. 10.000.000,-");
                                editTandaJadi.requestFocus();
                            }else if(user.getJenisKendaraan().contains("Xpander") && spinnerLuckyDip.getSelectedItem().toString().equals("Please choose...")){
                                luckyDip.setError("Please check this !");
                                luckyDip.requestFocus();
                            }else if (dataSnapshot.child(user.getNoSpk()).exists()) {
                                Toast.makeText(MainActivity.this, "SPK's already registered", Toast.LENGTH_SHORT).show();
                        }else{
                            users.child(user.getNoSpk()).setValue(user);
                            Toast.makeText(MainActivity.this, "Successfully registered !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String cabang = parent.getItemAtPosition(position).toString();
//
//        spinnerJenisKend.setOnSListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                if(spinnerJenisKend.getSelectedItem().toString().equals("Pajero Sport")){
//                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
//                    View mView = getLayoutInflater().inflate(R.layout.dialog_spinner, null);
//                    final Spinner mSpinner = (Spinner)mView.findViewById(R.id.spinnerDialog);
//                    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,
//                            getResources().getStringArray(R.array.pajeroSport));
//                    adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    mSpinner.setAdapter(adapter3);
//                    mSpinner.setOnItemSelectedListener(MainActivity.this);
//                }else{
//                    Toast.makeText(MainActivity.this, "Please choose car", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

