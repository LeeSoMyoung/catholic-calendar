package com.soup.calendar.utils;


import com.soup.calendar.config.BaseException;
import com.soup.calendar.config.BaseResponseStatus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationService {
    public static String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    public static String phoneNumberRegex = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
    public static String loginIdRegex = "^[a-zA-Z0-9]{6,}$";
    public static String passwordRegex = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[~`!?@#$%\\^&*()-])[A-Za-z[0-9]~`!?@#$%\\^&*()-]{8,}$";
    public static String birthYearRegex = "^[0-9]{2,4}$";
    public static String birthMonthRegex = "^[0-9]{1,2}$";
    public static String birthDayRegex = "^[0-9]{1,2}$";

    public static String nameRegex = "^[가-힣a-zA-Z]{1,10}$";
    public static String urlRegex = "(?i)\\b(?:(?:https?|ftp)://)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,}))\\.?)(?::\\d{2,5})?(?:[/?#]\\S*)?\\b";

    public static boolean isEmpty(String value) {
        return (value == null || value.length() == 0);
    }

    public static boolean isEmpty(Integer value) {
        return (value == null);
    }

    public static boolean isEmpty(Long value) {
        return (value == null);
    }

    public static boolean isEmptyObject(Object value) {
        return (value == null);
    }

    public static void validate(String value, BaseResponseStatus emptyStatus) throws BaseException {
        if (isEmpty(value)) {
            throw new BaseException(emptyStatus);
        }
    }

    public static void validateObject(Object value, BaseResponseStatus emptyStatus) throws BaseException {
        if (isEmptyObject(value)) {
            throw new BaseException(emptyStatus);
        }
    }


    public static void validateInvalid(String value, String regex, BaseResponseStatus invalidStatus) throws BaseException {
        if(value == null) return;

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(value);
        if (!matcher.find()) {
            throw new BaseException(invalidStatus);
        }
    }

    public static void validate(String value, String regex, BaseResponseStatus emptyStatus, BaseResponseStatus invalidStatus) throws BaseException {
        validate(value, emptyStatus);
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(value);
        if (!matcher.find()) {
            throw new BaseException(invalidStatus);
        }
    }
}
