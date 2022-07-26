package com.example.springbootguidetoresttemplate.util;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UnicornResponse {
    private String _id;
    private String name;
    private int age;
    private String colour;
}
