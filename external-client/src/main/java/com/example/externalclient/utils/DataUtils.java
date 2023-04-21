package com.example.externalclient.utils;

import com.example.externalclient.model.UserModel;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;


@UtilityClass
public class DataUtils {

    public static UserModel createUserModel() {
        String firstName = RandomStringUtils.randomAlphabetic(10);
        String lastName = RandomStringUtils.randomAlphabetic(10);
        String email = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(3);
        return UserModel.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .matchingPassword(password)
                .build();
    }
}
