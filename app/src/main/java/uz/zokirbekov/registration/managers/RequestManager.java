package uz.zokirbekov.registration.managers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uz.zokirbekov.registration.models.Person;
import uz.zokirbekov.registration.models.PersonUser;
import uz.zokirbekov.registration.models.RegistrationRequest;

public class RequestManager {

    private static RequestManager instance;
    private static final String URL = "";

    private Retrofit retrofit;
    private Api api;

    private RequestManager()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
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

    public void getPersonUser(int id, IResponse<Person> listener)
    {
        api.getPersonUser(id).enqueue(new Callback<Person>() {
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

    public void insertPerson(Person person, IResponse<Person> listener)
    {
        api.insertPerson(person).enqueue(new Callback<Person>() {
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

    public void insertPersonUser(PersonUser user, IResponse<Person> listener)
    {
        api.insertPersonUser(user).enqueue(new Callback<Person>() {
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

    public interface IResponse<T>
    {
        void success(T object);
        void error(String msg);
    }
}
