package com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Entity;





import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "standard_subjects",uniqueConstraints = @UniqueConstraint(columnNames = "standard")
)
@Data
@NoArgsConstructor
@AllArgsConstructor

@Getter
public class StandardSubject {

    //all properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String standard;

    @Column(nullable = false)
    private String subject;

    public StandardSubject(String standard, String subject) {
        this.standard = standard;
        this.subject = subject;
    }


    //some getter and setter methods

    public String getStandard() {
        return standard;
    }

    public String getSubject() {
        return subject;
    }
}

