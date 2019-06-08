package uz.zokirbekov.registration.managers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import uz.zokirbekov.registration.models.Person;
import uz.zokirbekov.registration.models.PersonUser;
import uz.zokirbekov.registration.models.RegistrationRequest;
import uz.zokirbekov.registration.models.StatisticModel;

public interface Api {

    @GET("/api/Person/{id}")
    public Call<Person> getPerson(@Path("id") int id);

    @GET("/api/Person/user/{id}")
    public Call<List<Person>> getPersonUser(@Path("id") int id);

    @GET("/api/Person/statistic/{id}")
    public Call<List<StatisticModel>> getStatistic(@Path("id") int id);

    @POST("/api/Person/login")
    public Call<Person> getPersonByLogin(@Body RegistrationRequest request);

    @POST("/api/Person/insert")
    public Call<String> insertPerson(@Body Person request);

    @POST("/api/Person/insertUser")
    public Call<String> insertPersonUser(@Body PersonUser request);

}
