package com.hog2020.ex81retrofit2test;


import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

//인터페이스 는 클래스 와 비슷하지만 멤버로 추상메소드(이름만 있는 메소드)만 만들 수 있음
public interface RetrofitService {

    //1. 단순하게 get 방식으로 json 문자열을 읽어오는 메소드
    @GET("/Retrofit/board.json") //접속하는 파일의경로를 지정
    Call<Item> getBoardJson();

    //2. 경로의 이름을 위 1처럼 고정하지않고 파라미터로 전달 받아 지정할 수 있음[@Path]
    @GET("/{folder}/{filename}")
    Call<Item> getBoardJsonByParams(@Path("folder") String Folder,@Path("filename") String Filename);

    //Get 방식으로 값을 서버에 전달[@Query]
    @GET("/Retrofit/getTest.php")
    Call<Item> getMethodTest(@Query("name") String name,@Query("msg") String msg);

    //4.GET 방식으로 값을 서버에 보내고 파일경로도 파라미터로 전달 [@Path,@Query]
    @GET("/Retrofit/{filename}")
    Call<Item> getMethodTest2(@Path("filename") String filename,@Query("name") String name,@Query("msg") String msg);

    //5.GET 방식으로 값을 보낼때 Map Collection 을 이용 하여 한방에 값들 전달하기
    @GET("/Retrofit/getTest.php")
    Call<Item> getMethodTest3(@QueryMap Map<String, String> datas);

    //6.POST 방식으로 값 전달[@Body]- 객체를 전달하면 자동으로 Json 문자열로 변환하여 서버로전송
    @POST("/Retrofit/postTest.php")
    Call<Item> postMethodTest(@Body Item item);

    //7.POST 방식으로 값 전달 [@Field]
    //단. @Field 를 사용하려면반드리  @FormUrlEncoded 와 함께 사용해야함
    @FormUrlEncoded
    @POST("/Retrofit/postTest copy.php")
    Call<Item> postMethodTest2(@Field("name") String name,@Field("msg") String msg);

    //8.GET 방식으로 json 배열 값 받아와서 ArrayList<Item>으로 받기
    @GET("/Retrofit/boardArray.json")
    Call<ArrayList<Item>> getBoardArray();

    //9. Gson 을 통해 자동으로 객체로 파싱하지 않고 json 문자열로 그냥 결과 응답받기
    //서버가 echo 를 단순 문자열로 할때 사용하는 방법
    @GET("/Retrofit/board.json")
    Call<String> getJsonString();

}
