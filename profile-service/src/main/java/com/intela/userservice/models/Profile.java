package com.intela.userservice.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Profile {
    @Id
    private Integer id;
    private String firstname;
    private String lastname;
    private String mobile;
    private String address;
    private String userId;
}
