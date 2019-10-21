package com.chen.bos.dao;

import java.util.List;

import com.chen.bos.entity.Region;

public interface RegionDao extends IBaseDao<Region>{

	List<Region> findByQ(String q);

}
