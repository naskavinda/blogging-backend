package com.assignment.bloggingbackend.util;

public final class ResponseDetails {

    public static final ResponseDetails S1001 = new ResponseDetails("S1001", "Record is successfully saved!");
    public static final ResponseDetails S1002 = new ResponseDetails("S1002", "Record is successfully deleted!");
    public static final ResponseDetails S1003 = new ResponseDetails("S1003", "Record is successfully Updated!");


    public static final ResponseDetails E1001 = new ResponseDetails("E1001", "Some thing went wrong please try again");
    public static final ResponseDetails E1002 = new ResponseDetails("E1002", "Request Contain fields are not valid");
    public static final ResponseDetails E1003 = new ResponseDetails("E1003", "Can not find Record for given Id");


    private String code;
    private String description;

    private ResponseDetails() {
    }

    private ResponseDetails(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
