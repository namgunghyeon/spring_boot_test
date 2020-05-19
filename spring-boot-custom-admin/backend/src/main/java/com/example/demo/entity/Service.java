package com.example.demo.entity;

import javax.persistence.*;
import java.util.Set;

/*
@NamedEntityGraph(
        name = "Service.withAccessKeys",
        attributeNodes = @NamedAttributeNode("accessKeys")
)


@NamedEntityGraph(
        name = "Service.withGroups",
        attributeNodes = @NamedAttributeNode("groups")
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


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "group_service",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups;


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

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

}
