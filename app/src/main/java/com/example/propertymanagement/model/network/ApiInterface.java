package com.example.propertymanagement.model.network;

import com.example.propertymanagement.model.pojo.AddTenantMessage;
import com.example.propertymanagement.model.pojo.ForgotPasswordSuccessResponse;
import com.example.propertymanagement.model.pojo.LoginSuccessResponse;
import com.example.propertymanagement.model.pojo.MsgResponse;
import com.example.propertymanagement.model.pojo.PropertyResponse;
import com.example.propertymanagement.model.pojo.TenantList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiInterface {

    @POST("pro_mgt_reg.php?")
    Call<ResponseBody> register(@Query("email") String email,
                                @Query("landlord_email") String landlordEmail,
                                @Query("password") String password,
                                @Query("account_for") String account_for);

    @GET("pro_mgt_login.php?")
    Call<LoginSuccessResponse> login(@Query("email") String email,
                                     @Query("password") String password);

    @POST("pro_mgt_forgot_pass.php?")
    Call<ForgotPasswordSuccessResponse> forgotPassword(@Query("email") String email);

    @POST("pro_mgt_add_pro.php?")
    Call<MsgResponse> AddProperty(@Query("address") String address,
                                  @Query("city") String city,
                                  @Query("state") String state,
                                  @Query("country") String country,
                                  @Query("pro_status") String pro_status,
                                  @Query("purchase_price") String purchase_price,
                                  @Query("mortage_info") String mortage_info,
                                  @Query("userid") String userid,
                                  @Query("usertype") String usertype,
                                  @Query("latitude") String latitude,
                                  @Query("longitude") String longitude);


    @GET("property.php?")
    Call<PropertyResponse> GetPropertyList(@Query("userid") String userid, @Query("usertype") String landlord);

    @DELETE("remove-property.php?")
    Call<MsgResponse> RemoveProperty(@Query("propertyid") String propertyid);

    @POST("pro_mgt_add_tenants.php?")
    Call<ResponseBody> addTenant(@Query("name") String name, @Query("email") String email, @Query("address") String address, @Query("mobile") String mobile, @Query("propertyid") String propertyid, @Query("landlordid") String landlordid);

    @POST("pro_mgt_add_tenants.php?")
    Call<AddTenantMessage> addTenant1(@Query("name") String name, @Query("email") String email, @Query("address") String address, @Query("mobile") String mobile, @Query("propertyid") String propertyid, @Query("landlordid") String landlordid);

    @GET("pro_mgt_tenent_details.php?")
    Call<TenantList> getTenantDetail(@Query("landlordid") String landlordId);
}