package com.example.demo.entity;

import javax.persistence.*;
import java.util.Set;

/*
@NamedEntityGraph(
        name = "Service.withAccessKeys",
        attributeNodes = @NamedAttributeNode("accessKeys")
)
*/
@Entity
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue
    private Long id;

    private String accessUri;

    private int isActivated;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "service")
    private Set<AccessKey> accessKeys;

    public Service(String accessUri, int isActivated) {
        this.accessUri = accessUri;
        this.isActivated = isActivated;
    }

    public Service() {}

    public Long getId() {
        return id;
    }

    public String getAccessUri() {
        return accessUri;
    }

    public int getIsActivated() {
        return isActivated;
    }

    public Set<AccessKey> getAccessKeys() {
        return accessKeys;
    }
}
