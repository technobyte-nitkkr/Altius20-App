package com.nitkkr.techspardha.retrofit;

import com.nitkkr.techspardha.Fragments.food.foodPojo.MyPojo;
import com.nitkkr.techspardha.Fragments.sponsership.sponsorshipPojo.SponsorshipData;
import com.nitkkr.techspardha.drawers.TeamTech.pojo.tech1;
import com.nitkkr.techspardha.drawers.developers.developersPojo.DevelopersData;
import com.nitkkr.techspardha.events.categoryPojo.CategoryData;
import com.nitkkr.techspardha.events.categoryPojo.EventCategory;
import com.nitkkr.techspardha.root.RegisteredEvents.Registered;
import com.nitkkr.techspardha.root.registerPojo.EventRegister;
import com.nitkkr.techspardha.root.registerPojo.RegisterData;
import com.nitkkr.techspardha.root.userPojo.Udata;
import com.nitkkr.techspardha.Fragments.guestLecture.lecturesPojo.LectureData;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface Interface {
    @GET("events/description")
    Observable<EventCategory> getData(
            @Query("eventCategory") String category,
            @Query("eventName") String eName
    );

    @GET("events/description")
    Observable<CategoryData> getCatData(
            @Query("eventCategory") String category
    );

    @GET("sponsors/")
    Observable<SponsorshipData> getSponsorship(
    );

    @GET("foodsponsors/")
    Observable<MyPojo> getFoodSponser(
    );

    @GET("lectures/")
    Observable<LectureData> getLectureData(
    );

    @POST("loginApp")
    @FormUrlEncoded
    Observable<Udata> getData(
            @Field("idToken") String token
            );

    @PUT("signUpApp")
    @FormUrlEncoded
    Observable<RegisterData> registerUser(
            @Field("phone") String phone,
            @Field("college") String college,
            @Field("year") String year,
            @Field("email") String email

    );
    @GET("aboutAppDevs")
    Observable<DevelopersData> getDevelopersData(

    );

    @GET("user/eventApp")
    Observable<Registered> getRegisteredEvents(
            @Query("email") String email
    );

    @PUT("user/eventApp")
    @FormUrlEncoded
    Observable<EventRegister> eventregister(
            @Field("email") String email,
            @Field("eventName") String eventName,
            @Field("eventCategory") String Category


    );
    @GET("contacts")
    Observable<tech1> getTeam(

    );




}
