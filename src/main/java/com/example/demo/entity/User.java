package com.example.demo.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private long userId;
    private String name;
    private Contact contact;
}
