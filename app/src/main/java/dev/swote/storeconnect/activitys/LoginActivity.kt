package dev.swote.storeconnect.activitys

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import dev.swote.storeconnect.R
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {

    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]
    private var     verificationInProgress = false
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var certificationCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //휴대전화 언어 사용
        auth.useAppLanguage()
        //전화 번호 로그인 editText 설정
        login_phone_number.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        login_phone_number.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.length >= 12) {
                    login_certification_button.isEnabled = true
                }
            }
        })
        // sms 인증버튼 눌렀을시 이벤트
        login_certification_button.setOnClickListener {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                login_phone_number.text.toString(),
                60,
                TimeUnit.SECONDS,
                this,
                certificationCallbacks
            )
        }
        certificationCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // 즉시 인증이 가능하거나, 자동 검색 될 때
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // 서버 실패시
                if (e is FirebaseAuthInvalidCredentialsException) {
                    // 유효하지 않은 전화번호
                    Toast.makeText(this@LoginActivity, "잘못된 전화번호 입니다.", Toast.LENGTH_SHORT).show()
                } else if (e is FirebaseTooManyRequestsException) {
                    // 한번에 너무 많은 요청이 왔을 시
                    Toast.makeText(this@LoginActivity, "한 번에 너무 많이 요청하셨습니다.\n잠시 후에 다시 시도하여 주세요.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // 전화번호로 인증 코드가 SMS를 통해 전송된 후
                storedVerificationId = verificationId
                resendToken = token
            }
        }
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // 로그인 성공
                    val user = task.result?.user
                } else {
                    // 로그인 실패
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this@LoginActivity, "인증번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

}
