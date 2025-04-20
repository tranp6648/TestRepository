package org.wisdom.oc01.repository;

import org.springframework.stereotype.Repository;
import org.wisdom.oc01.entity.DanhMuc;
import org.wisdom.oc01.generic.IRepository;

@Repository
public interface DanhMucRepository extends IRepository<DanhMuc, Integer> {
}
