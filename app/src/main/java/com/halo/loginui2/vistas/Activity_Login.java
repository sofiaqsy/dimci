package com.halo.loginui2.vistas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.halo.loginui2.MainActivity;
import com.halo.loginui2.Model.Ciudadano;
import com.halo.loginui2.R;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Button;

import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Activity_Login extends AppCompatActivity {

    private static final String TAG = "Activity_Login";
    private static final int REQUEST_SIGNUP = 0;
    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login) Button _loginButton;


    RelativeLayout rellay1, rellay2;
    Button register;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);

        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash

        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(Activity_Login.this, Activity_Register.class);
                startActivity(register);
            }
        });

        ButterKnife.bind(this);
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Activity_Login.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Autenticando...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        Gson gson = new Gson();
                        Ciudadano ciudadano = new Ciudadano();
                        ciudadano.setPhoneNumber(email);
                        ciudadano.setPassword(password);
                        onLoginSuccess(gson.toJson(ciudadano));
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 500);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();

            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess(String gson) {
        _loginButton.setEnabled(true);
        finish();
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject json = new JSONObject();
        try {
            json = new JSONObject(gson);
        } catch (JSONException e) {
            Log.i(TAG,e.toString());
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                "https://dinci-rest.herokuapp.com/ciudadano/autenticacion",
                json,
                (response) -> {
                    //Toast.makeText(context,response.toString(),Toast.LENGTH_LONG).show();
                    //this.limpiarCampos();
                    Gson gsonResponse = new Gson();
                    Ciudadano ciudadano = gsonResponse.fromJson(response.toString(),Ciudadano.class);
                    SharedPreferences.Editor editor = getSharedPreferences("usuario", MODE_PRIVATE).edit();
                    editor.putInt("id", ciudadano.getId());
                    editor.putString("name", ciudadano.getName());
                    //editor.putString("cantPost", ciudadano.ge());
                    editor.commit();
                    Intent intent = new Intent(this, MainActivity.class);
                    //intent.putExtra("id",ciudadano.getId());
                    startActivity(intent);
                    Log.i(TAG,response.toString());
                },
                (error) -> {
                    Log.i(TAG,error.toString());
                });
        requestQueue.add(request);

    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Error de Inicio de Sesion", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

       if (email.isEmpty() ){//!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Introdusca un usuario valido");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() ) {
           _passwordText.setError("Complete el campo");
            valid = false;
        } else {
            _passwordText.setError(null);
        }



        return valid;
    }
}
