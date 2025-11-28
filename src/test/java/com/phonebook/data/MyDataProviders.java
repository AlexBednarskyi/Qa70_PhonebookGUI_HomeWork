package com.phonebook.data;

import com.phonebook.models.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyDataProviders {

    @DataProvider(name = "validUserFromCsv")
    public static Iterator<Object[]> registerUserFromCsv() throws IOException {

        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(
                new FileReader("src/test/resources/registration.csv")
        );

        String line = reader.readLine();
        int counter = 1;

        while (line != null) {
            String[] split = line.split(";");

            String baseEmail = split[0].trim();
            String password  = split[1].trim();

            String uniqueEmail = baseEmail.replace("@", counter + "@");
            counter++;

            User user = new User()
                    .setEmail(uniqueEmail)
                    .setPassword(password);

            list.add(new Object[]{user});

            line = reader.readLine();
        }

        reader.close();
        return list.iterator();
    }
}
