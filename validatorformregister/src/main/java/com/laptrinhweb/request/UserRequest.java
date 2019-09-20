package com.laptrinhweb.request;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserRequest {

    @NotEmpty
    @Size(max = 255)
    String fistname;

    @NotEmpty
    @Size(max = 255)
    String lastname;

    @NotNull
    @Min(18)
    @Max(100)
    Integer age;

    @NotEmpty
    @Size(max = 255)
    @Email
    String email;

    @NotEmpty
    @Size(max = 255)
    String phonenumber;
}
