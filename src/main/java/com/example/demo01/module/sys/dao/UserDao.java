package com.example.demo01.module.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo01.module.sys.pojo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *<p>
 *     用户实体
 *</p>
 *
 * @author xiexing
 * @since 2022-10-22
 */
@Mapper
@Repository("userDao")
public interface UserDao extends BaseMapper<User> {
    //ibatis test
    @Select("SELECT\n" +
            "\tsu.id, \n" +
            "\tsu.username, \n" +
            "\tsu.`password`, \n" +
            "\tsu.`name`, \n" +
            "\tsu.avatar, \n" +
            "\tsu.introduction, \n" +
            "\tsu.`status`, \n" +
            "\tsu.create_user, \n" +
            "\tsu.create_time, \n" +
            "\tsu.update_user, \n" +
            "\tsu.update_time\n" +
            "FROM\n" +
            "\tsys_user AS su\n" +
            "WHERE\n" +
            "\tsu.username = #{username}\n")

    @Results({
            @Result(id = true, property = "id",column = "id"),
            @Result(property = "roles",column = "id",javaType = List.class,
                    many = @Many(select = "com.example.demo01.module.sys.dao.Role.findByUid"))
    })

    public User findByUsername(String username);

    @Update("UPDATE \n" +
            " sys_user \n" +
            "SET\n" +
            " id = #{User.id},\n" +
            " username = #{User.username},\n" +
            " `password` = #{User.password},\n" +
            " NAME = #{User.name},\n" +
            " avatar = #{User.avatar},\n" +
            " introduction = #{User.introduction},\n" +
            " STATUS = #{User.status},\n" +
            " create_user = #{User.createUser},\n" +
            " create_time = #{User.createTime},\n" +
            " update_user = #{User.updateUser},\n" +
            " update_time = #{User.updateTime} \n" +
            "WHERE id = #{User.id}")

    public Integer updateUser(User user);


}
