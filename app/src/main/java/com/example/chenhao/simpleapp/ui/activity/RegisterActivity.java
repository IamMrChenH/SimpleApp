package com.example.chenhao.simpleapp.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.base.SuperBaseActivity;
import com.example.chenhao.simpleapp.base.UserInfoBean;
import com.example.chenhao.simpleapp.db.UserTableDBopenhelerService;
import com.example.chenhao.simpleapp.utils.Utils;

import java.util.regex.Pattern;

public class RegisterActivity extends SuperBaseActivity {

    private EditText mUserNameText;
    private EditText mNameText;
    private EditText mPassText1;
    private EditText mPassText2;
    private EditText mPhoneTextx;
    private EditText mEmailText;
    private UserTableDBopenhelerService instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        instance = UserTableDBopenhelerService.getInstance(this);
        initViews();

    }

    private void initViews() {
        mUserNameText = findView(R.id.user_name);
        mNameText = findView(R.id.name);
        mPassText1 = findView(R.id.password1);
        mPassText2 = findView(R.id.password2);
        mPhoneTextx = findView(R.id.phone);
        mEmailText = findView(R.id.email);


        findView(R.id.email_sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

    private void register() {
        mUserNameText.setError(null);
        mNameText.setError(null);
        mPassText1.setError(null);
        mPassText2.setError(null);
        mPhoneTextx.setError(null);
        mEmailText.setError(null);

        String userNname = mUserNameText.getText().toString().trim();
        String name = mNameText.getText().toString().trim();
        String pass1 = mPassText1.getText().toString().trim();
        String pass2 = mPassText2.getText().toString().trim();
        String phone = mPhoneTextx.getText().toString().trim();
        String email = mEmailText.getText().toString().trim();

        if (TextUtils.isEmpty(userNname)) {
            mUserNameText.setError("用户名不能为空！");
            mUserNameText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(name)) {
            mNameText.setError("昵称不能为空！");
            mNameText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(pass1)) {
            mPassText1.setError("密码不能为空！");
            mPassText1.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(pass2)) {
            mPassText2.setError("再次密码不能为空！");
            mPassText2.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            mPhoneTextx.setError("电话不能为空！");
            mPhoneTextx.requestFocus();
            return;
        }
        if (!pass1.equals(pass2)) {
            mPassText2.setError("二次密码不同，请重新输入！");
            mPassText2.requestFocus();
            return;
        }


        if (!instance.isUserName(userNname)) {
            mUserNameText.setError("用户名已被注册！");
            mUserNameText.requestFocus();
            return;
        }
        if (!instance.isName(name)) {
            mNameText.setError("昵称已被使用！");
            mNameText.requestFocus();
            return;
        }
        if (!instance.isPhone(phone)) {
            mPhoneTextx.setError("电话已经被注册！");
            mPhoneTextx.requestFocus();
            return;
        }
        if (!instance.isEmail(email)) {
            mEmailText.setError("邮箱已经被注册！");
            mEmailText.requestFocus();
            return;
        }

        Pattern userPattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9]{4,8}$");
        Pattern passPattern = Pattern.compile("^(?=.*[0-9].*)(?=.*[A-Z].*)(?=.*[a-z].*).{6,12}$");


        if (!userPattern.matcher(userNname).matches()) {
            mUserNameText.setError("用户名必须以英文字母开头，长度5-8位！(不包含字符)");
            mUserNameText.requestFocus();
            return;
        }
        if (!passPattern.matcher(pass1).matches()) {
            mPassText1.setError("密码为6-12位，要求必须包含大小写英文字母和数字！");
            mPassText1.requestFocus();
            return;
        }
        String num = "[1][358]\\d{9}";

        if (!phone.matches(num)) {
            mPhoneTextx.setError("手机号只能输入以13、15、18开头的11位数字！");
            mPhoneTextx.requestFocus();
            return;
        }
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

        if (!email.matches(str)) {
            mEmailText.setError("邮箱格式不正确！");
            mEmailText.requestFocus();
            return;
        }


        instance.insert(new UserInfoBean(userNname, pass1, name, email, phone, System.currentTimeMillis() + "", 1));
        Utils.showToast("注册成功");
        finish();
    }


    @Override
    public String getToolbarTitle() {
        return "用户注册";
    }
}
