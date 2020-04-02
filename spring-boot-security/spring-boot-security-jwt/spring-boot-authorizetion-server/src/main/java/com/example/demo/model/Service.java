package com.example.demo.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NamedEntityGraph(
        name = "Service.withAccessKeys",
        attributeNodes = @NamedAttributeNode("accessKeys")
)
@Entity
@Table
public class Service {
    @Id
    @GeneratedValue
    private Long id;

    private String accessUri;

    private int isActivated;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "service")
    private Set<AccessKey> accessKeys;

    public Service(Set<AccessKey> accessKeys, String accessUri, int isActivated) {
        this.accessKeys = accessKeys;
        this.accessUri = accessUri;
        this.isActivated = isActivated;
    }

    public Service() {}

    public String getAccessUri() {
        return accessUri;
    }

    public Long getId() {
        return id;
    }

    public int getIsActivated() {
        return isActivated;
    }

    public Set<AccessKey> getAccessKeys() {
        return accessKeys;
    }
}
