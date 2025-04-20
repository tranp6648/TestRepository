package org.wisdom.oc01.repository;

import org.springframework.stereotype.Repository;
import org.wisdom.oc01.entity.Cv;
import org.wisdom.oc01.generic.IRepository;

@Repository
public interface CvRepository extends IRepository<Cv, Integer> {
}
