package com.api.QuickResponse.Repository;

import com.api.QuickResponse.Model.Login.ItemLogin;
import com.api.QuickResponse.Model.Register.DataRegisterStatus;
import com.api.QuickResponse.Model.Register.ErrorRegister;
import com.api.QuickResponse.Model.Register.SuccessRegister;
import com.api.QuickResponse.Model.YoutubeModel.VideoObject;
import com.api.QuickResponse.Ultilities.JsonWebTokenToString;
import com.api.QuickResponse.Model.Login.*;
import com.api.QuickResponse.Model.User;
import com.api.QuickResponse.Model.Register.ItemRegister;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class QuickResponseRepository {
    private static final String url = "jdbc:mysql://localhost:3306/QuickResponse";
    private static final String username = "root";
    private static final String password = "0944988947t";
    public static QuickResponseRepository quickResponseRepository;

    //SINGLETON
    public static QuickResponseRepository getQuickResponseRepository() {
        if (quickResponseRepository == null) {
            quickResponseRepository = new QuickResponseRepository();
        }
        return quickResponseRepository;
    }

    public static void setQuickResponseRepository(QuickResponseRepository quickResponseRepository) {
        QuickResponseRepository.quickResponseRepository = quickResponseRepository;
    }

    //GET LIST ALL USERS EXISTED
    public List<User> getall() {
        String queryAll = "SELECT * FROM QuickResponse.User;";
        ArrayList<User> listUser = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(queryAll);
            ResultSet resultSet = preparedStatement.executeQuery(queryAll);
            while (resultSet.next()) {
                User newUser = new User(resultSet.getString("userName")
                        , resultSet.getString("fullName")
                        , resultSet.getInt("age")
                        , resultSet.getBoolean("gender")
                        , resultSet.getString("password")
                        , resultSet.getString("accessToken")
                        , resultSet.getString("timeRegister"));
                listUser.add(newUser);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listUser;
    }

    //REGISTER NEW USER
    public Object register(ItemRegister itemRegister) throws SQLException {
        /*
        Date & Time Register Account
         */
        if (itemRegister == null) {
            return new ErrorRegister("Required information for Registering", 999, false);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDt = simpleDateFormat.format(date);

        /*
        Instant time to be a part of accessToken
         */
        long currentMs = new Date().getTime();

        Object toReturn = null;

        String insertDB = "INSERT INTO QuickResponse.User(id,fullName,userName,password,gender,accessToken,age,timeRegister) VALUES(?,?,?,?,?,?,?,?);";

        String checkDB = "SELECT * FROM QuickResponse.User WHERE userName ='" + itemRegister.getUserName() + "'";

        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement checkStatement = connection.prepareStatement(checkDB);
        ResultSet resultSet = checkStatement.executeQuery(checkDB);
//System.out.println(itemRegister.toString());
        if (resultSet.next()) {
            toReturn = new ErrorRegister("Duplicated username", 100, false);
        } else {
            try (PreparedStatement insertStatement = connection.prepareStatement(insertDB)) {

                User newUser = new User(
                        itemRegister.getUserName(),
                        itemRegister.getFullName(),
                        itemRegister.getAge(),
                        itemRegister.getGender(),
                        itemRegister.getPassword(),
                        JsonWebTokenToString.JWTAccessToken(itemRegister.toString() + currentMs),
                        currentDt);
//System.out.println(newUser.toString());

                insertStatement.setString(1, newUser.getId());
                insertStatement.setString(2, newUser.getFullName());
                insertStatement.setString(3, newUser.getUserName());
                insertStatement.setString(4, newUser.getPassword());
                insertStatement.setBoolean(5, newUser.isGender());
                insertStatement.setString(6, newUser.getAccessToken());
                insertStatement.setInt(7, newUser.getAge());
                insertStatement.setString(8, newUser.getTimeRegister());

                insertStatement.execute();

                toReturn = new SuccessRegister(
                        true, 200, new DataRegisterStatus(
                        itemRegister.getUserName(),
                        itemRegister.getFullName(),
                        itemRegister.getAge(),
                        itemRegister.getGender(),
                        newUser.getId()));
            } catch (SQLException e) {
                toReturn = new ErrorRegister(e.getMessage(), 500, false);
                System.out.println(e.getMessage());
            } catch (Exception e) {
                toReturn = new ErrorRegister(e.getMessage(), 500, false);
                e.printStackTrace();
            }
        }
        return toReturn;
    }

    /*
    Login method
     */
    public Object login(ItemLogin itemLogin) throws SQLException {
        Object toReturn = null;
        if (itemLogin == null) {
            return new ErrorLogin("Please fill in with username and password", 400, false);
        }
        /*
         Explanation: This method contains two steps to finalize everything abt this ticket ABC
         First, we have to generate jwt token for user and save to that user
         Second, we pick user info up from list and return
         */
        long currentMs = new Date().getTime();

        /*
        Date and time at the latest login
         */
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDt = simpleDateFormat.format(date);
        /*
        Login query check whether user and password are match
         */
        try {
            String loginQuery = "SELECT * FROM QuickResponse.User " +
                    "WHERE userName ='" + itemLogin.getUserName() + "' AND password ='" + itemLogin.getPassword() + "';";
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement loginStatement = connection.prepareStatement(loginQuery);
            ResultSet resultLogin = loginStatement.executeQuery(loginQuery);
        /*
        Update timeLastLogin and accessToken each time login into account
         */
            String updateQuery = "UPDATE QuickResponse.User" +
                    " SET timeLastLogin = ?, accessToken = ? " +
                    "WHERE userName ='" + itemLogin.getUserName() + "' AND password ='" + itemLogin.getPassword() + "';";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
        /*
        Check if the query has result in resultset notnull
        This means the username and password matches with the existed account in database
         */
            if (resultLogin.next()) {
                String id = resultLogin.getString(1);
                String fullName = resultLogin.getString(2);
                String userName = resultLogin.getString(3);
                String password = resultLogin.getString(4);
                boolean gender = resultLogin.getBoolean(5);
                String accessToken = resultLogin.getString(6);
                int age = resultLogin.getInt(7);
                String timeRegister = resultLogin.getString(8);

                loginStatement.execute();
            /*
            Create new temporary User with the information from database taken
            And set realtime login to it
             */
                User newUser = new User(userName, fullName, age, gender, password, accessToken, timeRegister);
                newUser.setTimeLogin(currentDt);

            /*
            Update latest time login into the account vs accessToken
             */
                updateStatement.setString(1, currentDt);

                String tempaccessToken = JsonWebTokenToString.JWTAccessToken(itemLogin.toString() + currentMs);
                updateStatement.setString(2, tempaccessToken);

                updateStatement.executeUpdate();

                toReturn = new SuccessLogin(true, new DataLoginStatus(userName, fullName, age, gender, id, accessToken));
            } else {
                toReturn = new ErrorLogin("Wrong username or password", 455, false);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            toReturn = new ErrorLogin(e.getMessage(), 404, false);
        }
        return toReturn;
    }

    /*
    Search 20 youtube videos by keyword input
     */
    public Object searchByKeyword(String query) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://youtube-search6.p.rapidapi.com/search/?query=" + query))
                .header("X-RapidAPI-Host", "youtube-search6.p.rapidapi.com")
                .header("X-RapidAPI-Key", "c59b4901edmsh8e650272a17c640p171602jsn243dea0b5ad4")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /*
    Return list of 20 trending videos
     */
    public Object getTrendingVideos() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://youtube-search6.p.rapidapi.com/trending?country=US&lang=en"))
                .header("X-RapidAPI-Host", "youtube-search6.p.rapidapi.com")
                .header("X-RapidAPI-Key", "c59b4901edmsh8e650272a17c640p171602jsn243dea0b5ad4")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public Object getDetails(String id) throws IOException, InterruptedException {
        HttpRequest details = HttpRequest.newBuilder()
                .uri(URI.create("https://youtube-search6.p.rapidapi.com/video/details/?videoId=" + id))
                .header("X-RapidAPI-Key", "87f975e4b1msh141920b7d1554f2p15bdd9jsnc10b3cbf8d55")
                .header("X-RapidAPI-Host", "youtube-search6.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(details, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public Object getSubtitles(String id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://youtube-search6.p.rapidapi.com/video/subtitles/?videoId=" + id))
                .header("X-RapidAPI-Key", "87f975e4b1msh141920b7d1554f2p15bdd9jsnc10b3cbf8d55")
                .header("X-RapidAPI-Host", "youtube-search6.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public Object getComments(String id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://youtube-search6.p.rapidapi.com/video/comments/?videoId=" + id))
                .header("X-RapidAPI-Key", "87f975e4b1msh141920b7d1554f2p15bdd9jsnc10b3cbf8d55")
                .header("X-RapidAPI-Host", "youtube-search6.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public Object getRecommendation(String id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://youtube-search6.p.rapidapi.com/video/recommendation/?videoId=" + id))
                .header("X-RapidAPI-Key", "87f975e4b1msh141920b7d1554f2p15bdd9jsnc10b3cbf8d55")
                .header("X-RapidAPI-Host", "youtube-search6.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /*
    get videoDetails from videoId
    fetch Json result to Object name VideoObject to get channelId
    through it, get channelDetails
     */
    public Object getChannelDetails(String id) throws IOException, InterruptedException {
        Gson gson = new Gson();
        VideoObject videoObject = gson.fromJson((String) getDetails(id), VideoObject.class);
        String channelId = videoObject.getChannelId();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://youtube-search6.p.rapidapi.com/channel/details/?channelId=" + channelId))
                .header("X-RapidAPI-Key", "87f975e4b1msh141920b7d1554f2p15bdd9jsnc10b3cbf8d55")
                .header("X-RapidAPI-Host", "youtube-search6.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}
