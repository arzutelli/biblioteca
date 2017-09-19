package com.nttdata.database;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nttdata.model.Indirizzi;

@Mapper
public interface IndirizziMapper {
	
	@Select("select * from Indirizzi where idIndirizzi = #{idIndirizzi} AND idUtente = #{idUtente}")
	Indirizzi findByIdIndirizzi (@Param("idIndirizzi") int idIndirizzi, @Param("idUtente") int idUtente);

	@Insert("insert into Indirizzi (idUtente, via, citta, provincia, cap) values (#{idUtente},#{via}, #{citta}, #{provincia}, #{cap})")
	@Options(useGeneratedKeys = true, keyProperty = "idIndirizzi")
	int add(Indirizzi indirizzi);

	@Delete("delete Indirizzi where idIndirizzi = #{idIndirizzi}")
	int delete(@Param("idIndirizzi") int idIndirizzi);

	@Update("update Indirizzi set via = #{via},"
			+ "citta = #{citta},"
			+ "provincia = #{provincia},"
			+ "cap = #{cap}"
			+ " where idIndirizzi = #{idIndirizzi} and idUtente = #{idUtente}")
	int update(Indirizzi indirizzi);

	@Select("<script> select * from Indirizzi"
			+ "<where>"
			+"<if test='idUtente != null'>AND idUtente=#{idUtente}</if>"
			+"<if test='citta != null'>AND citta=#{citta}</if>"
		     +"<if test='provincia != null'>AND provincia=#{provincia}</if>"
		     +"<if test='cap != null'>AND cap=#{cap}</if>"
		    + "</where>"
		+"</script>")			
	List<Indirizzi> findAll(Indirizzi indirizzi);
	
	/*
	@Select("<script> select * from Indirizzi"
		    +"<where>"
		     +"<if test='citta != null'>AND citta=#{citta}</if>"
		     +"<if test='provincia != null'>AND provincia=#{provincia}</if>"
		     +"<if test='cap != null'>AND cap=#{cap}</if>"
		    + "</where>"
		+"</script>")
List<Indirizzi> findByParams(Indirizzi indirizzi);
*/

}
