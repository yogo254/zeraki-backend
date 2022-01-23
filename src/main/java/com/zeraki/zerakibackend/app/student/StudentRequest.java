package com.zeraki.zerakibackend.app.student;

import lombok.Data;

@Data
public class StudentRequest {
    private String id;
    private String name;
    private String courseId;

}
