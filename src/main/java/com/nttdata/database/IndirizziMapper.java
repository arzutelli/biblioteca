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
	
	@Select("select * from Indirizzi where badgeId = #{badgeId}")
	Indirizzi findByBadgeId(@Param("badgeId") String badgeId) ;

	@Insert("insert into Indirizzi (badgeID, via, citta, provicnia, cap) values (#{badgeId}, #{via}, #{citta}, #{provicnia}, #{cap}")
	@Options(useGeneratedKeys = true, keyProperty = "badgeId")
	String add(Indirizzi indirizzi);

	@Delete("delete Indirizzi where badgeId = #{badgeId}")
	String delete(@Param("badgeId") String badgeId);

	@Update("update Indirizzi set  via = #{via}," + "citta = #{citta}" + 
	"provincia = #{provincia}" + "cap = #{cap}" +  
	" where badgeId = #{badgeId}")
	String update(Indirizzi indirizzi);

	@Select("select * from Indirizzi")
	List<Indirizzi> findAll();

}
