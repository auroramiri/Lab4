package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.File;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private final static String fname = "BASE_LAB.txt";
    Button button;
    EditText surname;
    EditText name;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        surname = (EditText) findViewById(R.id.surname);
        name = (EditText) findViewById(R.id.name);
        textView = (TextView) findViewById(R.id.textView);
        ExistBase(fname);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Open(view);
                Save(view);

            }
        });
    }
    public boolean ExistBase (String fname)
    {
        boolean rc = false;
        File f = new File(super.getFilesDir(),fname );
        if(rc = f.exists()) Toast.makeText(this,"Файл существует",Toast.LENGTH_SHORT).show();
        else Toast.makeText(this,"Файл"+fname+"не найден",Toast.LENGTH_SHORT).show();
        return rc;
    }
    public void Save (View view )

    {
        FileOutputStream fos =null;
        try
        {
            String text =  surname.getText().toString()+ " " + name.getText().toString();
            fos = openFileOutput(fname, MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();

        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void Open( View view)
    {
        FileInputStream fin = null;
        try
        {
            fin = openFileInput((fname));
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            textView.setText(text);

        }
        catch (IOException ex)
        {
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
        finally
        {
            try
            {
                if(fin!=null)
                    fin.close();
            }
            catch (IOException ex)
            {
                Toast.makeText(this,ex.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }

    }

}
