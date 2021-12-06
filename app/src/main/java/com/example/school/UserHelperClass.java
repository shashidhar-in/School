package com.example.school;

public class UserHelperClass {

    String StudentName,FatherName,Phone,Address,Dob,Email,Gender,Role,Class,Section;

    public UserHelperClass() {
    }


    public UserHelperClass(String studentName, String fatherName, String phone, String address, String dob, String email, String gender, String isRole, String IsClass, String section) {
        StudentName = studentName;
        FatherName = fatherName;
        Phone = phone;
        Address = address;
        Dob = dob;
        Email = email;
        Gender=gender;
        Role=isRole;
        Class=IsClass;
        Section=section;

    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getisRole() {
        return Role;
    }

    public void setisRole(String isRole) {

        this.Role = isRole;
    }

    public String getisClass() {
        return Class;
    }

    public void setClass(String isClass) {
        this.Class = isClass;
    }

    public String getsection() {
        return Section;
    }

    public void setsection(String section) {
        this.Section = section;
    }


    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


}
