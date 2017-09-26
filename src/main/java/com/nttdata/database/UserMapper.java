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

import com.nttdata.model.User;

@Mapper
public interface UserMapper {

	@Select("select * from users where badgeId = #{badgeId}")
	User findByBadgeId(@Param("badgeId") int badgeId);

	@Insert("insert into users (name, surname, email,dataNascita) values (#{name},#{surname}, #{email},#{dataNascita})")
	@Options(useGeneratedKeys = true, keyProperty = "badgeId")
	int add(User user);

	@Delete("delete users where badgeId = #{badgeId}")
	int delete(@Param("badgeId") int badgeId);

	@Update("update users set name = #{name}," + " surname = #{surname}," + " email = #{email},"
			+ " dataNascita = #{dataNascita}" + " where badgeId = #{badgeId}")
	int update(User user);

	@Select("select * from users")
	List<User> findAll();

	@Select("<script> select distinct u.* "
			+ "from users u "
				+ "left outer join Indirizzi i on i.idUtente = u.badgeId "
				+ "left outer join Telefoni t on t.idUtente = u.badgeId " 
			+ "<where>"
				+ "<if test='telefono.numero != null'>AND t.numero=#{telefono.numero}</if>"
				+ "<if test='indirizzo.citta != null'>AND i.citta=#{indirizzo.citta}</if>"
				+ "<if test='user.name != null'>AND name=#{user.name}</if>"
				+ "<if test='user.surname != null'>AND surname=#{user.surname}</if>"
				+ "<if test='user.email != null'>AND email=#{user.email}</if>" 
			+ "</where> "
				+ "order by badgeId"
			+ "</script>")
	List<User> findByParams(Map<String, Object> params);

	
	@Select("select distinct u.* "
			+ "from users u "
				+ "left outer join Indirizzi i on i.idUtente = u.badgeId "
				+ "left outer join Telefoni t on t.idUtente = u.badgeId " 
			+ "where "
				+ " upper(u.name) like upper('%'||#{query}||'%')"
				+ " OR upper(u.surname) like upper('%'||#{query}||'%')"
				+ " OR upper(i.citta) like upper('%'||#{query}||'%')"
				+ " OR  (i.cap) like ('%'||#{query}||'%')"
				+ " OR  (t.numero) like ('%'||#{query}||'%')"
				+ " order by badgeId")
	List<User> findByQuery(@Param("query") String query);

}
