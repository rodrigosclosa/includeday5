package com.ciandt.includeday6.backend.entity;

import com.googlecode.objectify.annotation.Id;

import java.io.Serializable;

/**
 * Created by rodrigogs on 10/07/17.
 */

public abstract class baseEntity implements Serializable {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
