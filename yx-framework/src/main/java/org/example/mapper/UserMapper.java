package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.domain.User;
import org.springframework.stereotype.Service;


@Service
public interface UserMapper extends BaseMapper<User> {
}