package com.wj.manager.mapper;

import com.wj.manager.common.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.MyMapper;

@Mapper
public interface SysUserMapper extends MyMapper<SysUser> {

}