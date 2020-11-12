package com.nitkkr.techspardha.root.registerPojo;

import com.nitkkr.techspardha.root.UserLogin;
import com.nitkkr.techspardha.root.userPojo.userInfo;

public class RegisterData {
    private String success;

    private userInfo information;

    private String message;

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public userInfo getInformation ()
    {
        return information;
    }

    public void setInformation (userInfo information)
    {
        this.information = information;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

}
