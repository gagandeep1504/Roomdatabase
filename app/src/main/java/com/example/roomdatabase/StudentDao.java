package com.example.roomdatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface StudentDao {
    @Insert
    void insert(Student student);
    @Delete
    void delete(Student student);
    @Update
    void update(Student student);
    @Query("SELECT * FROM students")
    List<Student> getAllStudents();
    @Query("SELECT * FROM students WHERE _id IN (:ids)")
    Student getStudentById(int[] ids);

    @Query("SELECT * FROM students")
    LiveData<List<Student>> liveStudents();
}
