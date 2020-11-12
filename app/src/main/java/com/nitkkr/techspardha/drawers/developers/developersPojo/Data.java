package com.nitkkr.techspardha.drawers.developers.developersPojo;

public class Data {
    private Information[] information;

    public Information[] getInformation ()
    {
        return information;
    }

    public void setInformation (Information[] information)
    {
        this.information = information;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [information = "+information+"]";
    }
}
