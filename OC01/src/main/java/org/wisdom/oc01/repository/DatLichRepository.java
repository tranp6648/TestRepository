package org.wisdom.oc01.repository;

import org.springframework.stereotype.Repository;
import org.wisdom.oc01.entity.DatLich;
import org.wisdom.oc01.generic.IRepository;

@Repository
public interface DatLichRepository extends IRepository<DatLich, Integer> {
}
