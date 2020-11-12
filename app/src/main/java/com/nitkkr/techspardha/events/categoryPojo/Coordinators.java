package com.nitkkr.techspardha.events.categoryPojo;

import java.io.Serializable;

public class Coordinators implements Serializable
{
    private String coordinator_name;

    private String coordinator_number;

    public String getCoordinator_name ()
    {
        return coordinator_name;
    }

    public void setCoordinator_name (String coordinator_name)
    {
        this.coordinator_name = coordinator_name;
    }

    public String getCoordinator_number ()
    {
        return coordinator_number;
    }

    public void setCoordinator_number (String coordinator_number)
    {
        this.coordinator_number = coordinator_number;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [coordinator_name = "+coordinator_name+", coordinator_number = "+coordinator_number+"]";
    }
}