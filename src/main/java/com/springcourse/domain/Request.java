package com.springcourse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springcourse.enums.RequestState;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "request")
public class Request implements Serializable {

    /*
     * Serial Version UUID
     * */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 75)
    private String subject;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "creation_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(length = 10)
    private Integer code;

    @Column(length = 12)
    @Enumerated(EnumType.STRING)
    private RequestState state;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Getter(onMethod = @__({@JsonIgnore}))
    @OneToMany(mappedBy = "request")
    private List<RequestStage> stages = new ArrayList<>();
}
