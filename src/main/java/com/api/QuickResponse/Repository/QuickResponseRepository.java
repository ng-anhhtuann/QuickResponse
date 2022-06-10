package com.api.QuickResponse.Repository;

import com.api.QuickResponse.Model.Login.ItemLogin;
import com.api.QuickResponse.Model.Register.DataRegisterStatus;
import com.api.QuickResponse.Model.Register.ErrorRegister;
import com.api.QuickResponse.Model.Register.SuccessRegister;
import com.api.QuickResponse.Model.Ultilities.JsonWebTokenToString;
import com.api.QuickResponse.Model.Login.*;
import com.api.QuickResponse.Model.User;
import com.api.QuickResponse.Model.Register.ItemRegister;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class QuickResponseRepository {
    public static List<User> listUsers;
    public static QuickResponseRepository quickResponseRepository;

    //SINGLETON
    public static QuickResponseRepository getQuickResponseRepository() {
        if (quickResponseRepository == null) {
            quickResponseRepository = new QuickResponseRepository();
            listUsers = new ArrayList<>();
        }
        return quickResponseRepository;
    }

    public static void setQuickResponseRepository(QuickResponseRepository quickResponseRepository) {
        QuickResponseRepository.quickResponseRepository = quickResponseRepository;
    }

    //GET LIST ALL USERS EXISTED
    public List<User> getall() {
        return listUsers;
    }

    //REGISTER NEW USER
    public Object register(ItemRegister itemRegister) {
        long currentMs = new Date().getTime();
        Object toReturn;
        int countingSameUsername = 0;
        for (User listUser : listUsers) {
            if (Objects.equals(itemRegister.getUserName(), listUser.getUserName())) {
                countingSameUsername = 1;
                break;
            }
        }
        if (countingSameUsername == 1) {
            toReturn = new ErrorRegister("Duplicated", 100, false);
        } else {
            User temporatyUser = new User(
                    itemRegister.getUserName(),
                    itemRegister.getFullName(),
                    itemRegister.getAge(),
                    itemRegister.getGender(),
                    itemRegister.getPassword(),
                    itemRegister.getId(),
                    JsonWebTokenToString.JWTAccessToken(itemRegister.toString() + currentMs));
            listUsers.add(temporatyUser);
            toReturn = new SuccessRegister(
                    true, 200, new DataRegisterStatus(
                    itemRegister.getUserName(),
                    itemRegister.getFullName(),
                    itemRegister.getAge(),
                    itemRegister.getGender(),
                    itemRegister.getId()));
        }
        return toReturn;
    }

    /*
    Login method
     */
    public Object login(ItemLogin itemLogin) {
        // Explanation: This method contains two steps to finalize everything abt this ticket ABC
        // First, we have to generate jwt token for user and save to that user

        // Second, we pick user info up from list and return
        long currentMs = new Date().getTime();
        Object toReturn = null;
        // Find whether user logged in is duplicated or not
        User userFound = null;
        int userIndex = -1;
        for (User listUser : listUsers) {
            userIndex++;
            if (Objects.equals(itemLogin.getUserName(), listUser.getUserName())) {
                if (Objects.equals(itemLogin.getPassword(), listUser.getPassword())) {
                    userFound = listUser;
                    break;
                } else {
                    return new ErrorLogin("Wrong Password", 150, false);
                }
            }
        }
        if (userFound != null) {
            // Generate accessToken and set to user
            // Finally we have to pick it up
            String accessToken = JsonWebTokenToString.JWTAccessToken(itemLogin.toString() + currentMs);
            User temp = listUsers.get(userIndex);
            temp.setAccessToken(accessToken);
            listUsers.set(userIndex, temp);
            // Return success login status
            return new SuccessLogin(
                    true, new DataLoginStatus(
                    userFound.getUserName(),
                    userFound.getFullName(),
                    userFound.getAge(),
                    userFound.isGender(),
                    userFound.getId(),
                    userFound.getAccessToken()));
        } else {
            return new ErrorLogin("Unexisted Account", 150, false);
        }
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
    public Object trendingVideos() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://youtube-search6.p.rapidapi.com/trending?country=US&lang=en"))
                .header("X-RapidAPI-Host", "youtube-search6.p.rapidapi.com")
                .header("X-RapidAPI-Key", "c59b4901edmsh8e650272a17c640p171602jsn243dea0b5ad4")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public Object continuableVideo() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://youtube-search6.p.rapidapi.com/video/recommendation/continuation/?videoId=sVF_SlzxBJ4&continuationToken=CBQSKRILc1ZGX1NsenhCSjTAAQDIAQDgAQGiAg0o____________AUAA-AIAGAAqmwMI4p64qar6ncwdCJnKz6OLgLm4JAj49f2F1o_U6swBCPXHwfaSybHGQgiqtLXfs_W6thMI7Ync68rUsqziAQi045DznPbn7egBCLfT1vT_voC8wgEIyLby7NLUrO3uAQiliPG_5Kej2lgIk_qG093k85v5AQiz9cL9xLOb1nMI5Zyax5W3pfHfAQjWppG9oN3ivVkIxpKI6_edt75SCP7Qv67Pjpnb-gEItYDj_by_5IorCOns-5P5xsj-sgEIxpGhiYj0nNpTCN6Alr-9m866kAEaxgEKC2tIVTQyOWZsZ0Y0GrYBCgtrSFU0MjlmbGdGNBIBMBoLCPXl7IsGELjxnz8iBgjTkfWNBioECA0QAjJZCgQIZBABCgQIaRAPCgUImAEQAQoFCNwBEAcKBQiQAxABCgUIhgcQFgoFCMAHEAEKBQjFBxABCgUIxgcQAQoFCMcHEAEKBQjIBxABCgUIyQcQAQoFCMoHEAE6EwoCCHAKAwjnAgoDCKgDCgMImQhCBwoDCM0BEAJKBwoDCM0BEAJSBwoDCM0BEAJqD3dhdGNoLW5leHQtZmVlZA%253D%253D"))
                .header("X-RapidAPI-Key", "87f975e4b1msh141920b7d1554f2p15bdd9jsnc10b3cbf8d55")
                .header("X-RapidAPI-Host", "youtube-search6.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
