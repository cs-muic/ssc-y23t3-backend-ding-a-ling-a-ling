package io.muzoo.ssc.springwebapp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// POJO
// Plain Old Java Object
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username;

    private String name;

}
