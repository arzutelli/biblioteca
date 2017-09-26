package com.nttdata.database;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nttdata.model.Indirizzi;
import com.nttdata.model.User;

@Mapper
public interface UserMapper {

	@Select("select * from users where badgeId = #{badgeId}")
	User findByBadgeId(@Param("badgeId") int badgeId);

	@Insert("insert into users (name, surname, email,dataNascita) values (#{name},#{surname}, #{email},#{dataNascita})")
	@Options(useGeneratedKeys = true, keyProperty="badgeId")
	int add(User user);
	
	@Delete("delete users where badgeId = #{badgeId}")
	int delete(@Param("badgeId") int badgeId);
	
	@Update("update users set name = #{name},"
			+ " surname = #{surname},"
			+ " email = #{email},"
			+ " dataNascita = #{dataNascita}"
			+ " where badgeId = #{badgeId}")
	int update(User user);

	@Select("select * from users")
	List<User> findAll();

	@Select("<script> select distinct u.* from users u, Indirizzi i "
			    +"<where>"
			    +"i.idUtente = u.badgeId "
			    +"<if test='indirizzo.citta != null'>AND i.citta=#{indirizzo.citta}</if>"
			     +"<if test='user.name != null'>AND name=#{user.name}</if>"
			     +"<if test='user.surname != null'>AND surname=#{user.surname}</if>"
			     +"<if test='user.email != null'>AND email=#{user.email}</if>"
			    + "</where>"
			+"</script>")
	List<User> findByParams(Map<String, Object> params);
	
	@Select("select u.* from users u, Indirizzi i where i.idUtente = u.badgeId and i.citta=#{citta}")
	List<User> findByCitta(Indirizzi indirizzi);
	
}
