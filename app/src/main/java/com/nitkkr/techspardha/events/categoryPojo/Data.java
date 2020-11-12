package com.nitkkr.techspardha.events.categoryPojo;

import java.io.Serializable;

public class Data implements Serializable
    {
        private String venue;

        private String file;

        private String eventCategory;

        private String banner;

        private Coordinators[] coordinators;

        private String flagship;

        private String description;

        private String eventName;

        private String[] rules;

        private String startTime;

        private String endTime;

        public String getVenue ()
        {
            return venue;
        }

        public void setVenue (String venue)
        {
            this.venue = venue;
        }

        public String getFile ()
        {
            return file;
        }

        public void setFile (String file)
        {
            this.file = file;
        }

        public String getEventCategory ()
        {
            return eventCategory;
        }

        public void setEventCategory (String eventCategory)
        {
            this.eventCategory = eventCategory;
        }

        public Coordinators[] getCoordinators ()
        {
            return coordinators;
        }

        public void setCoordinators (Coordinators[] coordinators)
        {
            this.coordinators = coordinators;
        }

        public String getFlagship ()
        {
            return flagship;
        }

        public void setFlagship (String flagship)
        {
            this.flagship = flagship;
        }

        public String getDescription ()
        {
            return description;
        }

        public void setDescription (String description)
        {
            this.description = description;
        }

        public String getEventName ()
        {
            return eventName;
        }

        public void setEventName (String eventName)
        {
            this.eventName = eventName;
        }

        public String[] getRules ()
        {
            return rules;
        }

        public void setRules (String[] rules)
        {
            this.rules = rules;
        }

        public String getStartTime ()
        {
            return startTime;
        }

        public void setStartTime (String startTime)
        {
            this.startTime = startTime;
        }

        public String getEndTime ()
        {
            return endTime;
        }

        public void setEndTime (String endTime)
        {
            this.endTime = endTime;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }
    }

