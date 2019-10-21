package com.chen.bos.dao;

import java.util.List;

import com.chen.bos.entity.Subarea;

public interface SubareaDao extends IBaseDao<Subarea>{

	List<Object> findSubareasByPro();

}
