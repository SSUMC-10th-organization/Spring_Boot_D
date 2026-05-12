package com.example.umc10thmission4.domain.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // 다른 엔티티들이 이 클래스의 필드를 컬럼으로 인식하게 함
@EntityListeners(AuditingEntityListener.class) // 자동으로 날짜를 기록하는 기능을 활성화
@Getter
public abstract class BaseEntity {

    @CreatedDate // 데이터 생성 시점 자동 기록
    @Column(updatable = false) // 생성일은 수정되지 않도록 설정
    private LocalDateTime createdAt;

    @LastModifiedDate // 데이터 수정 시점 자동 기록
    private LocalDateTime updatedAt;
}
