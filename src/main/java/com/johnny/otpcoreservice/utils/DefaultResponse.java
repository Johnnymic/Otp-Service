package com.johnny.otpcoreservice.utils;

public class DefaultResponse {
    public enum ResponseStatus {
        API_SUCCESS_STATUS("success", "Request processed successfully"),
        API_FAIL_STATUS("fail", "Failed processing request"),
        API_ERROR_STATUS("error", "Error processing request");

        private String code;
        private String description;

        ResponseStatus(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public static ResponseStatus getEnumByResponseCode(String value) {
            if (value == null) throw new IllegalArgumentException();
            for (ResponseStatus v : values()) if (value.equalsIgnoreCase(v.getCode())) return v;
            throw new IllegalArgumentException();
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public enum ResponseCode {
        SUCCESS("00", "Approved or completed successfully"),
        FAILED("99", "Unsuccessful transaction"),
        UNKNOWN("01", "Status unknown, please wait for settlement report"),
        INVALID_ACCOUNT("07", "Request processing in progress"),
        REQUEST_PROCESSING("09", "Status unknown, please wait for settlement report"),
        INVALID_TRANSACTION("12", "Invalid transaction"),

        INVALID_BANK_CODE("16", "Unknown Bank Code"),
        NO_ACTION("21", "No action is taken"),
        IN_SUFFICIENT_FUNDS("51", "No sufficient funds"),
        ACCOUNT_BLOCK("69", "Unsuccessful Account/Amount block"),

        LIMIT_EXCEEDED("61", "Transfer limit Exceeded"),
        DAILY_LIMIT_EXCEEDED("61", "Daily limit Exceeded"),
        BALANCE_LIMIT_EXCEEDED("61", "Deposit/Balance limit Exceeded"),
        ACCOUNT_UNBLOCK("70", "Unsuccessful Account/Amount unblock"),
        DUPLICATE_REQUEST("94", "Duplicate request or transaction"),
        SYSTEM_MALFUNCTION("96", "System malfunction"),
        TIMEOUT("97", "Timeout waiting for a response from destination"),
        ACCOUNT_ALREADY_EXITS("98", "Account already exists"),
        ACCOUNT_ALREADY_IN_USE("105", "Account already in use"),
        INVALID_SOURCE_ACCOUNT("99", "Invalid Source Account"),
        INVALID_BENEFICIARY_ACCOUNT("100", "Invalid Beneficiary Account"),
        SOURCE_ACCOUNT_BLOCK("101", "Unsuccessful Source Account/Amount block"),
        BENEFICIARY_ACCOUNT_BLOCK("102", "Unsuccessful Beneficiary Account/Amount block"),
        DEBIT_OK("103", "Debit ok"),
        INVALID_DATE_FORMAT("104", "Invalid Date format"),
        CUSTOMER_ACCOUNT_RECORD_MISMATCH("105", "Customer account mis match"),
        INVALID_OTP("106", "Invalid otp"),
        INVALID_ACTIVATION_KEY("107", "Invalid activation key"),
        PROFILE_ALREADY_ACTIVATED_KEY("108", "Profile already activated"),
        INVALID_PROFILE("109", "Invalid Profile"),
        DUPLICATE_RECORD("110", "Duplicate record"),
        INVALID_AUTHORITIES("402", "Invalid Authorities"),
        AUTHORIZATION_ERROR("403", "Authorization Error"),
        PHONE_NUMBER_ALREADY_IN_USE("98", "Phone number already exist"),
        EMAIL_ALREADY_REGISTERED("98", "Email already exist");

        private String code;
        private String description;

        ResponseCode(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public static ResponseCode getEnumByResponseCode(String value) {
            if (value == null) throw new IllegalArgumentException();
            for (ResponseCode v : values()) if (value.equalsIgnoreCase(v.getCode())) return v;
            throw new IllegalArgumentException();
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
