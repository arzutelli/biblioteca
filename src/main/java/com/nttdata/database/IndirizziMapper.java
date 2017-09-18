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
	
	@Select("select * from Indirizzi where idIndirizzi = #{idIndirizzi}")
	Indirizzi findByIdIndirizzi (@Param("idIndirizzi") int idIndirizzi) ;

	@Insert("insert into Indirizzi (idUtente, via, citta, provincia, cap) values (#{idUtente}, #{via}, #{citta}, #{provincia}, #{cap})")
	@Options(useGeneratedKeys = true, keyProperty = "idIndirizzi")
	int add(Indirizzi indirizzi);

	@Delete("delete Indirizzi where idIndirizzi = #{idIndirizzi}")
	int delete(@Param("idIndirizzi") int idIndirizzi);

	@Update("update Indirizzi set"
			+ "via = #{via},"
			+ "citta = #{citta},"
			+ "provincia = #{provincia},"
			+ "cap = #{cap}"
			+ " where idIndirizzi = #{idIndirizzi} and idUtente = #{idUtente}")
	int update(Indirizzi indirizzi);

	@Select("select * from indirizzi")
	List<Indirizzi> findAll(@Param("badgeId") int badgeId);

}
