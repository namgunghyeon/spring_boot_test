package com.example.demo.entity;

import javax.persistence.*;

/*
@NamedEntityGraph(
        name = "Enrollment.withService",
        attributeNodes = {
                @NamedAttributeNode(value = "service")
        }
)
*/
@Entity
@Table(name = "access_key")
public class AccessKey {
    @Id
    @GeneratedValue
    private Long id;

    private String key;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="service_id", nullable=false)
    private Service service;

    public AccessKey(String key) {
        this.key = key;
    }

    public AccessKey() {
    }

    public Long getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
