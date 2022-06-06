package com.api.QuickResponse.Repository;

import com.api.QuickResponse.Model.ItemLogin;
import com.api.QuickResponse.Model.ReturnRegisterStatus.DataRegisterStatus;
import com.api.QuickResponse.Model.ReturnRegisterStatus.ErrorRegister;
import com.api.QuickResponse.Model.Manufacturing.EncodeBase64ToString;
import com.api.QuickResponse.Model.ReturnRegisterStatus.SuccessRegister;
import com.api.QuickResponse.Model.Users;
import com.api.QuickResponse.Model.ItemRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuickResponseRepository {
    public static List<Users> listUsers;        //List users existed on system

    public QuickResponseRepository() {
    }

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
    public List<Users> getall() {
        return listUsers;
    }

    public Object register(ItemRegister itemRegister) {
        Object toReturn;
        int countingSameUsername = 0;
        int lengthOfList = listUsers.size();
        for (Users listUser : listUsers) {
            if (Objects.equals(itemRegister.getUserName(), listUser.getUserName())) {
                countingSameUsername = 1;
                break;
            }
        }
        if (countingSameUsername == 1) {
            toReturn = new ErrorRegister("Duplicated", 100, false);
        } else {
            Users temporatyUser = new Users(
                    itemRegister.getUserName(),
                    itemRegister.getFullName(),
                    itemRegister.getAge(),
                    itemRegister.getGender(),
                    itemRegister.getPassword(),
                    itemRegister.getId(),
                    EncodeBase64ToString.base64Encode(itemRegister.getUserName()));
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

    public Object login(ItemLogin itemLogin) {
        
    }
}
