package uz.zokirbekov.registration.managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uz.zokirbekov.registration.models.Person;
import uz.zokirbekov.registration.models.PersonUser;
import uz.zokirbekov.registration.models.RegistrationRequest;
import uz.zokirbekov.registration.models.StatisticModel;

public class RequestManager {

    private static RequestManager instance;
    private static final String URL = "http://192.168.1.105:62690";

    private Retrofit retrofit;
    private Api api;

    private RequestManager()
    {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        api = retrofit.create(Api.class);
    }

    public static RequestManager getInstance() {
        if (instance == null)
            instance = new RequestManager();
        return instance;
    }

    public void getPerson(int id, IResponse<Person> listener)
    {
        api.getPerson(id).enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                if (response.isSuccessful())
                    listener.success(response.body());
                else
                    listener.error(response.message());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });
    }

    public void getPersonUser(int id, IResponse<List<Person>> listener)
    {
        api.getPersonUser(id).enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                if (response.isSuccessful())
                    listener.success(response.body());
                else
                    listener.error(response.message());
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });
    }

    public void getStatistic(int type, IResponse<List<StatisticModel>> listener)
    {
        api.getStatistic(type).enqueue(new Callback<List<StatisticModel>>() {
            @Override
            public void onResponse(Call<List<StatisticModel>> call, Response<List<StatisticModel>> response) {
                if (response.isSuccessful())
                    listener.success(response.body());
                else
                    listener.error(response.message());
            }

            @Override
            public void onFailure(Call<List<StatisticModel>> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });
    }

    public void getPersonByLogin(RegistrationRequest login, IResponse<Person> listener)
    {
        api.getPersonByLogin(login).enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                if (response.isSuccessful())
                    listener.success(response.body());
                else
                    listener.error(response.message());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });
    }

    public void insertPerson(Person person, IResponse<String> listener)
    {
        api.insertPerson(person).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful())
                    listener.success(response.body());
                else
                    listener.error(response.message());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });
    }

    public void insertPersonUser(PersonUser user, IResponse<String> listener)
    {
        api.insertPersonUser(user).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful())
                    listener.success(response.body());
                else
                    listener.error(response.message());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });
    }

    public interface IResponse<T>
    {
        void success(T object);
        void error(String msg);
    }
}
