package com.example.school.ui;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.school.UserHelperClass;
import com.example.school.databinding.FragmentAddUserBinding;
import com.example.school.ui.Dashboard.DashboardViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class AddUserFragment extends Fragment {

    private static final int male = 0;
    private static final int female = 1;


    private DashboardViewModel dashboardViewModel;
    private FragmentAddUserBinding binding;
    String mClass="";
    String mGender="";
    String mSection="";
    String mRole="";

    Boolean valid=true;

    EditText mPassword,Stu_name,Father_name,Phone,Address,Dob,Email;
    RadioButton Male,Female,SelectedRadio_button;
    RadioGroup Gender;
    CheckBox mShowPassword;
    Spinner Spinner_Class,Spinner_Section,Spinner_IsRole;
    Button Add_usr;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    private int Gen;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {






        binding = FragmentAddUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Stu_name=binding.edit1;
        Father_name=binding.edit2;
        Phone=binding.edit3;
        Address=binding.edit4;
        Dob=binding.edit5;
        Email=binding.edit6;
        mPassword=binding.edit7;

        Gender=binding.gender;
        Male=binding.male;
        Female=binding.female;

        Spinner_Class=binding.Class;
        Spinner_Section= binding.Section;
        Spinner_IsRole=binding.Role;

        Add_usr=binding.btnAddUser;


        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        mShowPassword = binding.showPass;
        mShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!mShowPassword.isChecked())mPassword.setTransformationMethod(new PasswordTransformationMethod());
                else mPassword.setTransformationMethod(null);
            }
        });


        Spinner_IsRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String base=""+parent.getSelectedItem();
                if(base.equals("Student")){
                    mRole=""+"1";
                }
                else {
                    mRole=""+"2";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Add_usr.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                checkField(Email);
                checkField(Phone);
                checkField(mPassword);

                 Gen=binding.gender.getCheckedRadioButtonId();
                Log.d("Gender id","is:"+Gen);
                 if(Gen==2131231288){
                     mGender=""+"Male";

                 }
                 else{
                     mGender=""+"Female";
                 }
                Log.d("Gender id","is:"+mGender);




                if(valid){
                    fAuth.createUserWithEmailAndPassword(Email.getText().toString(),mPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user=fAuth.getCurrentUser();
                            Toast.makeText(getContext(),"account created",Toast.LENGTH_SHORT).show();
                            DocumentReference df=fStore.collection("users").document(user.getUid());
                            Map<String,Object> userInfo=new HashMap<>();
                            userInfo.put("Phone",Phone.getText().toString());
                            userInfo.put("UserEmail",Email.getText().toString());
                            userInfo.put("Name",Stu_name.getText().toString());
                            userInfo.put("isRole",Spinner_IsRole.getSelectedItem().toString());
                            df.set(userInfo);

                            //specify if the user is admin
                            /**
                             Role==0 is Admin we are not creating admin from the application to maintain access-levels
                             Role==1 is student;
                             Role==2 is teacher;
                             **/


                            String uid = user.getUid();

                            String path="users/";
                            Log.d("path2","path is:"+path);



                            rootNode= FirebaseDatabase.getInstance();
                            reference=rootNode.getReference(path);

                            String studentName=Stu_name.getText().toString();
                            String fatherName=Father_name.getText().toString();
                            String phone=Phone.getText().toString();
                            String address=Address.getText().toString();
                            String dob=Dob.getText().toString();
                            String email=Email.getText().toString();
                            String gender= mGender;
                            String isRole=Spinner_IsRole.getSelectedItem().toString();
                            String Class=Spinner_Class.getSelectedItem().toString();
                            String Section=Spinner_Section.getSelectedItem().toString();
                            UserHelperClass helperClass=new UserHelperClass(studentName,fatherName,phone,address,dob,email,gender,isRole,Class,Section) ;

                            reference.child(uid).setValue(helperClass);


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(),"Failed to Create user",Toast.LENGTH_SHORT).show();
                        }
                    });
                }






            }
        });

        return binding.getRoot();

    }


    private void checkField(EditText editText) {
        if(editText.getText().toString().isEmpty()){
            editText.setError("Field is empty");
            valid=false;
        }
        else{
            valid=true;

        }
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
