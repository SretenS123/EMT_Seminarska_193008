package com.example.ordercakemanagment.domain.valueobjects;

import com.example.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class CakeId extends DomainObjectId {

    private CakeId() {
        super(CakeId.randomId(CakeId.class).getId());
    }

    public CakeId(String uuid) {
        super(uuid);
    }

}
