package hello.hellospring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // Base 클래스의 속성들이 하위 클래스에게도 상속 가능
@EntityListeners(AuditingEntityListener.class) //'CreatedDate' 와 'LastModifiedDate' 필드를 자동으로 채워줌
public abstract class Base {
    @CreatedDate //생성일
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy //생성자
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate //수정일
    @Column(updatable = true)
    private LocalDateTime modifiedAt;

    @LastModifiedBy //수정자
    @Column(updatable = true)
    private String modifiedBy;
}
