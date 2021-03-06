package com.nttdata.database;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nttdata.model.Autore;
import com.nttdata.model.Libro;


@Mapper
public interface AutoreMapper {

	@Select("select * from Autore where idAutore = #{idAutore}")
	Autore findByIdAutore(@Param("idAutore") int idAutore);

	@Insert("insert into Autore ( nome, cognome, email) values (#{nome}, #{cognome}, #{email})")
	@Options(useGeneratedKeys = true, keyProperty = "idAutore")
	int add(Autore autore);

	@Delete("delete Autore where idAutore = #{idAutore}")
	int delete(@Param("idAutore") int idAutore);

	@Update("update Autore set nome = #{nome},"
			+ " cognome = #{cognome},"
			+ " email = #{email}"
			+ " where idAutore = #{idAutore}")
	int update(Autore autore);

	@Select("<script> select * from Autore"
			+ "<where>"
			+ "<if test='nome != null'> AND nome=#{nome}</if>"
			+ "<if test='cognome != null'> AND cognome=#{cognome}</if>"
			+ "<if test='email != null'> AND email=#{email}</if>"
			+ "</where>"
			+ "</script>")
	List<Autore> findAll(Autore autore);
	
	@Select("select l.* from Libro l, autoreLibro al where l.idLibro=al.idLibro AND al.idAutore=#{idAutore}")
	List<Libro> findLibri(@Param("idAutore") int idAutore);

}
