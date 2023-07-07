package com.soup.calendar.config;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.util.Date;

import static com.soup.calendar.config.BaseResponseStatus.EMPTY_STATUS;
import static com.soup.calendar.config.BaseResponseStatus.INVALID_STATUS;
import static com.soup.calendar.utils.ValidationService.isEmpty;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt", nullable = false)
    private Date updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 10)
    private Status status = Status.ACTIVE;

    public enum Status {

        ACTIVE, INACTIVE, INVALID, SUBMITTED, NOT_SUBMITTED;

        public static Status getStatus(String status) throws BaseException {
            if (isEmpty(status)) {
                throw new BaseException(EMPTY_STATUS);
            }
            try {
                return Status.valueOf(status);
            } catch (Exception ignored) {
                throw new BaseException(INVALID_STATUS);
            }
        }
    }
}