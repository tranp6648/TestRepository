package org.wisdom.oc01.repository;

import org.springframework.stereotype.Repository;
import org.wisdom.oc01.entity.SanPham;
import org.wisdom.oc01.generic.IRepository;

@Repository
public interface SanPhamRepository extends IRepository<SanPham, Integer> {
}
