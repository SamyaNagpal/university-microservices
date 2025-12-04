package com.university.college.students.irepository;

import java.util.List;

import com.university.college.students.model.Student;

public interface IStudentRepository {
  List<Student> GetAllStudents();

  Student GetStudentById(String RollNo);

  Student GetStudentByName(String studentName);

  Student AddStudent(Student student);

  Integer GetStudentCount();

  void DeleteStudentByName(String studentName);
}
