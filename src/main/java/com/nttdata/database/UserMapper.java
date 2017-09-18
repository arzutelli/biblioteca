package com.nttdata.database;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nttdata.model.User;

@Mapper
public interface UserMapper {

	@Select("select * from users where badgeId = #{badgeId}")
	User findByBadgeId(@Param("badgeId") int badgeId);

	@Insert("insert into users (name, surname, email) values (#{name},#{surname}, #{email})")
	@Options(useGeneratedKeys = true, keyProperty="badgeId")
	int add(User user);
	
	@Delete("delete users where badgeId = #{badgeId}")
	int delete(@Param("badgeId") int badgeId);
	
	@Update("update users set name = #{name},"
			+ " surname = #{surname},"
			+ " email = #{email}"
			+ " where badgeId = #{badgeId}")
	int update(User user);

	@Select("select * from users")
	List<User> findAll();

	@Select("<script> select * from users"
			    +"<where>"
			     +"<if test='name != null'>AND name=#{name}</if>"
			     +"<if test='surname != null'>AND surname=#{surname}</if>"
			     +"<if test='email != null'>AND email=#{email}</if>"
			    + "</where>"
			+"</script>")
	List<User> findByParams(User user);
}
