package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.example.domain.Tag;


public interface TagMapper extends BaseMapper<Tag> {
    //删除标签，注意是逻辑删除，所以不能使用mybatisplus提供的，我们要自己使用mybatis写SQL语句
    int myUpdateById(@Param("id") Long id,@Param("flag") int flag);
}