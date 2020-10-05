package com.touhid.petclinic.services;

import com.touhid.petclinic.model.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long>{

    Owner findByLastName();

}
