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

import com.nttdata.model.Libro;

@Mapper
public interface LibroMapper {

	@Select("select * from Libro where idLibro = #{idLibro}")
	Libro findByIdLibro(@Param("idLibro") int idLibro);

	@Insert("insert into Libro (titolo, genere, prezzo, scaffale) values (#{titolo}, #{genere}, #{prezzo}, #{scaffale})")
	@Options(useGeneratedKeys = true, keyProperty = "idLibro")
	int add(Libro libro);

	@Delete("delete Libro where idLibro = #{idLibro}")
	int delete(@Param("idLibro") int idLibro);

	@Update("update Libro set titolo = #{titolo}," + " genere = #{genere}," + " prezzo = #{prezzo},"
			+ " scaffale = #{scaffale}" + " where idLibro = #{idLibro}")
	int update(Libro libro);

	@Select("<script> select distinct l.* from Libro l, Autore a, AutoreLibro al "
			+ "<where>"
			+ "l.idLibro=al.idLibro and al.idAutore=a.idAutore "
			+ "<if test='libro.genere != null'> AND genere=#{libro.genere}</if>"
			+ "<if test='libro.titolo != null'> AND titolo=#{libro.titolo}</if>"
			+ "<if test='autore.nome != null'> AND nome=#{autore.nome}</if>"
			+ "<if test='autore.cognome != null'> AND cognome=#{autore.cognome}</if>"
			+ "</where>"
			+ "</script>")
	List<Libro> findAll(Map<String, Object> params);
	
	
	
	@Select("select * "
			+ "from Libro "
			+ "where "
				+ " upper(titolo) like upper('%'||#{query}||'%')"
				+ " OR upper(genere) like upper('%'||#{query}||'%')"
				+ " order by idLibro")
	List<Libro> findByQuery(@Param("query") String query);

}
