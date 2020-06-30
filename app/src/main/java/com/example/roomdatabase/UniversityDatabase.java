package com.example.roomdatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Student.class}, version = 1)
public abstract class UniversityDatabase extends RoomDatabase {

    public abstract StudentDao studentDao();
    private static UniversityDatabase instance;
    public static synchronized UniversityDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, UniversityDatabase.class,"University_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(initialCallBack)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback initialCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitialAsyncTask(instance).execute();
        }
    };

    private static class InitialAsyncTask extends AsyncTask<Void, Void, Void>{
        private StudentDao studentDao;
        public InitialAsyncTask(UniversityDatabase db){
            studentDao = db.studentDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            studentDao.insert(new Student("GAGANDEEP KUMAR", "gagandeep.1822cs1062@kiet.edu","895647899"));
            studentDao.insert(new Student("KARTIKEY SINGH", "gagandeep.1822cs1062@kiet.edu","895647899"));
            studentDao.insert(new Student("PRASHANT KUMAR", "gagandeep.1822cs1062@kiet.edu","895647899"));

            return null;
        }

    }


}
