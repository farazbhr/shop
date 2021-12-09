package de.shop.shop.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class User {

    @NotEmpty
    @NotNull
    // unique
    private String username;

    @NotEmpty
    @NotNull
    private String password;

   //( >1.1.1900, <today)
    private LocalDate  birthday;
}
