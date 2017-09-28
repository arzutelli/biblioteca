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

@Mapper // Marca l'interfaccia come un Mapper del framework Mybatis
public interface UserMapper {

	/*
	 * @Pararm indica che la variabile badgeId sarà ignettata all'interno della query al posto della stringa #{badgeId}
	 */
	@Select("select * from users where badgeId = #{badgeId}") // annotazione per l'esecuzione di una select
	User findByBadgeId(@Param("badgeId") int badgeId); 

	/*
	 * @Insert... utilizza come paramtri le variabili della classe User
	 * @Option... indica che la property badgeId viene autogenerata dal database al momento dell'inserimento 
	 * se non valorizzata. In piu' il valore verrà inserito all'interno dell'oggetto passato come parametro
	 * nel campo badgeId
	 */
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

	/*
	 * tramite <script> si indica che si tratta di una query dinamica.
	 * verrà costruita a runtime sulla base delle clausole dei tag <if...>
	 * 
	 * <where> elimina in autonomia il primo AND che trova per fare in modo che la query sia corretta sintatticamente
	 */
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
