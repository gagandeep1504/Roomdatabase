package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private UniversityDatabase universityDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        universityDatabase = UniversityDatabase.getInstance(this);

        LiveData<List<Student>> liveStudents = universityDatabase.studentDao().liveStudents();
            liveStudents.observe(this, new Observer<List<Student>>() {
                @Override
                public void onChanged(List<Student> students) {
                    String toastMessage = "";
                    for (Student s: students){
                        toastMessage+= s.getName() + "\n";
                    }
                    textView.setText(toastMessage);
                }
            });

          new MimicUpdateDbAsyncTask().execute();

        }

    private class MimicUpdateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            ArrayList<Student> students = new ArrayList<>();
            students.add(new Student("MANAS SRIVASTAVA","manas@gmail.com","7387773787"));
            students.add(new Student("MANAS SAHNI","manas1@gmail.com","7387773787"));
            students.add(new Student("SHASHWAT BHASIN","manas@gmail.com","7387773787"));

            for (Student s: students){
                try{
                    Thread.sleep(2000);
                    universityDatabase.studentDao().insert(s);
                }catch (InterruptedException e){
                    e.printStackTrace();

                }
            }


            return null;
        }
    }

}
