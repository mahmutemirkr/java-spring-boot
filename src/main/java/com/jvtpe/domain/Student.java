package com.jvtpe.domain;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull(message = "First name can not be null")
    @NotBlank(message = "First name can not be null")
    @Size(min = 2, max = 25, message = "First Name '${validatedValue}' must be beetween {min} and {max} long")
    @Column(nullable = false, length = 25)
    /* final */ private String name;

    @Column(nullable = false, length = 25)
    private String lastName;

    private Integer grade;

    @Column(nullable = false, length = 50, unique = true)
    @Email(message = "Provide valid email")
    private String email;

    private String phoneNumber;

    @Setter(AccessLevel.NONE)
    private LocalDateTime createDate = LocalDateTime.now();

    @OneToMany(mappedBy = "student")
    private List<Book> books = new ArrayList<>();


}
