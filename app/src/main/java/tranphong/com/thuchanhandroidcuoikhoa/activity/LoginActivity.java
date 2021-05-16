package tranphong.com.thuchanhandroidcuoikhoa.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import tranphong.com.thuchanhandroidcuoikhoa.R;
import tranphong.com.thuchanhandroidcuoikhoa.ultil.Server;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button btndangki,btnDangNhap;
    EditText edtEmail,edtPassword,edtTK,edtMK,edtUsername,edtAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btndangki=findViewById(R.id.btnDangKiUser);

        btnDangNhap=findViewById(R.id.btnDangNhapUser);
        edtTK=findViewById(R.id.edtDangNhapEmail);
        edtMK=findViewById(R.id.edtdangnhapPasswrd);
        auth=FirebaseAuth.getInstance();
        btndangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giaoDien();

            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String taikhoan=edtTK.getText().toString();
                String matkhau=edtMK.getText().toString();
                if(taikhoan.isEmpty()&&matkhau.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Dangnhap();
                }

            }
        });
    }

    private  void giaoDien()
    {
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dong_dang_ki);
        edtEmail=dialog.findViewById(R.id.editTextTextPersonName);
        edtPassword=dialog.findViewById(R.id.editTextTextPersonName1);
        edtUsername=dialog.findViewById(R.id.edtUsername);
        edtAddress=dialog.findViewById(R.id.edtAdress);
        Button btnDangkiXacNhan=dialog.findViewById(R.id.btnDangKi2);
        Button btnhuydangki=dialog.findViewById(R.id.btnHuy);
        btnDangkiXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKi();
                dialog.dismiss();
            }
        });

        btnhuydangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setTitle("Đăng kí Hệ Thống");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    private  void DangKi(){
        String email=edtEmail.getText().toString();
        String password=edtPassword.getText().toString();
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(LoginActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                            dangkiBangUser();

                        } else {

                            Toast.makeText(LoginActivity.this, "Lõi Đăng kí", Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
    private void Dangnhap(){
        String email=edtTK.getText().toString();
        String password=edtMK.getText().toString();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d("phong", "signInWithEmail:success");
                            Toast.makeText(LoginActivity.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent();
                            intent.putExtra("email",email);

                            setResult(RESULT_OK,intent);

                            finish();

                        } else {
                            Toast.makeText(LoginActivity.this, "Lỗi ĐĂNG NHẬP", Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
    public void dangkiBangUser()
    {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.insertUser,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            Toast.makeText(LoginActivity.this, "Thêm User thành công", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "lỗi thêm", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Da xay ra loi", Toast.LENGTH_SHORT).show();
                        Log.d("aaa","Loi!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();

                params.put("email",edtEmail.getText().toString());
                params.put("diachi",edtAddress.getText().toString());
                params.put("username",edtUsername.getText().toString());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}