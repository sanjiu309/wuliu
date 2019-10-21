package com.chen.bos.dao;

import java.util.List;

import com.chen.bos.entity.Function;

public interface FunctionDao extends IBaseDao<Function>{

	List<Function> findFunctionByUser(String id);

	List<Function> findAllMenu();

	List<Function> findMenuByUser(String id);

}
