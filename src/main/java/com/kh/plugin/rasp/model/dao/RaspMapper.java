package com.kh.plugin.rasp.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.plugin.rasp.model.vo.Device;

@Mapper
public interface RaspMapper {

	@Insert("""
			INSERT
			  INTO
			       RASP_DATA
			       (
			       DEVICE_ID
			     , CREATED_AT
			     , FINISH_AT
			       )
			VALUES
			       (
			       #{deviceId}
			     , #{createdAt}
			     , #{finishAt}
			       )      
			""")
	void save(Device device);

	@Select("""
			SELECT
			       LOG_ID
			     , DEVICE_ID
			     , CREATED_AT
			     , FINISH_AT
			  FROM
			       RASP_DATA
			 WHERE
			       CREATED_AT < SYSTIMESTAMP
			   AND
			       SYSTIMESTAMP < FINISH_AT               
			""")
	List<Device> findCurrent();

	
	@Select("""
			SELECT
			       LOG_ID
			     , DEVICE_ID
			     , CREATED_AT
			     , FINISH_AT
			  FROM
			       RASP_DATA
			 WHERE
			       (SYSTIMESTAMP - INTERVAL '30' MINUTE) <= CREATED_AT      
			""")
	List<Device> findSerial();

}
