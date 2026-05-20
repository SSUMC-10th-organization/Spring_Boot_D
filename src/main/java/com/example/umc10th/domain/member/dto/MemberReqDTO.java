package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class MemberReqDTO {

    public record SignUpRequest(
            @NotBlank(message = "이름은 필수입니다.")
            String name,

            @NotBlank(message = "닉네임은 필수입니다.")
            String nickname,

            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "올바른 이메일 형식이어야 합니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
            String password,

            String phoneNumber,

            @NotNull(message = "성별은 필수입니다.")
            Gender gender,

            @NotNull(message = "생년월일은 필수입니다.")
            @Past(message = "생년월일은 과거 날짜여야 합니다.")
            LocalDate birth,

            String address,

            @AssertTrue(message = "서비스 이용약관에 동의해야 합니다.")
            Boolean serviceTermsAgreed,

            @AssertTrue(message = "개인정보 처리 방침에 동의해야 합니다.")
            Boolean privacyPolicyAgreed,

            Boolean locationAgreed,

            Boolean marketingAgreed
    ) {
    }
}
