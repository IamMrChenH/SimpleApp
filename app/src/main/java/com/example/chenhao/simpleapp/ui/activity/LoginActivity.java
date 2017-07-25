package com.example.chenhao.simpleapp.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.app.BaseData;
import com.example.chenhao.simpleapp.base.SuperBaseActivity;
import com.example.chenhao.simpleapp.base.UserInfoBean;
import com.example.chenhao.simpleapp.db.UserTableDBopenhelerService;
import com.example.chenhao.simpleapp.user.ui.ui.activity.UserHomeActivity;
import com.example.chenhao.simpleapp.utils.Utils;

/**
 * 都是自动生成的 滑稽
 */

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends SuperBaseActivity {

    private UserLoginTask mAuthTask = null;

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private UserTableDBopenhelerService instance;

    private CheckBox mRememberPassWord;
    private CheckBox mAutoLogin;
    private SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addAnimation(findView(R.id.activity_login));
        initDatas();
        initViews();

    }

    private void initDatas() {
        instance = UserTableDBopenhelerService.getInstance(this);
        //      'admin','admin','管理员','admin@xx.com','1008611','2017-7-17-21:55',0)");

        instance.insert(new UserInfoBean("admin", "admin", "管理员", "admin@xx.com",
                "1008611", "2017-7-17-21:55", 0));
        mSharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

//        CarTableTableDBopenhelerService.getInstance(this).insert(new Car(1, 1, "2号小车", 1000));
//        CarTableTableDBopenhelerService.getInstance(this).updateBalance(1, 200);
//        CarTableTableDBopenhelerService.getInstance(this).updateBalance(2, 2000);
//        CarTableTableDBopenhelerService.getInstance(this).findAllCar();


//        CarRecordTableTableDBopenhelerService.getInstance(this).insert(new CarRecord(3, 100, "充值", 1, "2017-7-24 0:17"));
//        CarRecordTableTableDBopenhelerService.getInstance(this).findAllCar();


    }

    private void initViews() {
        mEmailView = (AutoCompleteTextView) findViewById(R.id.user_name);
        mPasswordView = (EditText) findViewById(R.id.password);


        mRememberPassWord = findView(R.id.Remember_Password);
        mAutoLogin = findView(R.id.Auto_Login);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);


        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        mRememberPassWord.setChecked(mSharedPreferences.getBoolean("re", false));
        mAutoLogin.setChecked(mSharedPreferences.getBoolean("auto", false));

        if (mRememberPassWord.isChecked()) {
            mEmailView.setText(mSharedPreferences.getString("user", ""));
            mPasswordView.setText(mSharedPreferences.getString("pass", ""));
        }
        if (mAutoLogin.isChecked()) attemptLogin();


    }


    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();


        if (TextUtils.isEmpty(email)) {
            mEmailView.setError("用户名不能为空！");
            mEmailView.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError("密码不能为空！");
            mPasswordView.requestFocus();
            return;
        }


        if (instance.isUserName(email)) {
//            Utils.showToast("用户名不存在！");
            showMsgDialog("用户名不存在！");
            return;
        }
        UserInfoBean userInfoBean = instance.findUserInfoBean(email, password);
        if (userInfoBean == null) {
//            Utils.showToast("密码错误！");
            showMsgDialog("密码错误！");
            return;
        }


        showProgress(true);
        mAuthTask = new UserLoginTask(userInfoBean);
        mAuthTask.execute((Void) null);
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_regist) {
            Utils.showToast("注册");

            startActivity(new Intent(this, RegisterActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public String getToolbarTitle() {
        return "登陆";
    }


    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private UserInfoBean mUserInfoBean;


        UserLoginTask(UserInfoBean bean) {
            mUserInfoBean = bean;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return false;
            }


            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);
            BaseData.mUserInfoBean = mUserInfoBean;
            reLogin();
            switch (mUserInfoBean.getRole()) {
                case 0:
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    Utils.showToast("欢迎您登陆 管理员!");
//                    showMsgDialog("欢迎您登陆 管理员!");
                    finish();
                    break;
                case 1:
                    startActivity(new Intent(LoginActivity.this, UserHomeActivity.class));
                    Utils.showToast(mUserInfoBean.getName() + "欢迎您登陆！");
//                    showMsgDialog(mUserInfoBean.getName() + "欢迎您登陆！");
                    finish();
                    break;
                case 2:
                    Utils.showToast("此用户暂时无权限登陆！");
//                    showMsgDialog("此用户暂时无权限登陆！");
                    break;
                default:
                    break;
            }

        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }


        public void reLogin() {
            mSharedPreferences.edit()
                    .putBoolean("re", mRememberPassWord.isChecked())
                    .putBoolean("auto", mAutoLogin.isChecked())
                    .commit();
            if (mRememberPassWord.isChecked()) {
                mSharedPreferences.edit()
                        .putString("user", mUserInfoBean.getUserName())
                        .putString("pass", mUserInfoBean.getPassword())
                        .commit();
            }

        }
    }
}

