package com.example.cakecatalog.domain.model;

import com.example.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class CakeId extends DomainObjectId {

    private CakeId() {
        super(CakeId.randomId(CakeId.class).getId());
    }
    public CakeId(@NonNull String uuid){
        super(uuid);
    }
    public static CakeId of(String uuid) {
        CakeId c = new CakeId(uuid);
        return c;
    }
}
