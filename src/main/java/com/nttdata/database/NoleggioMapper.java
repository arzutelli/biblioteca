package com.nttdata.database;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nttdata.model.Noleggio;

@Mapper

public interface NoleggioMapper {
	

	@Select("select * from Telefoni where idNoleggio = #{idNoleggio}")
	Noleggio findByIdNoleggio(@Param("idNoleggio") int idNoleggio) ;

	@Insert("insert into noleggio (badgeID, idLibro, dataPrelievo, dataConsegna) values (#{badgeId}, #{idLibro}, #{dataPrelievo}, #{dataConsegna}")
	@Options(useGeneratedKeys = true, keyProperty = "idNoleggio")
	int add(Noleggio noleggio);

	@Delete("delete Noleggio where idNoleggio = #{idNoleggio}")
	int delete(@Param("idNoleggio") int idNoleggio);

	@Update("update Noleggio set badgeId = #{badgeId}," + " idLibro = #{idLibro}," + "dataPrelievo = #{dataPrelievo}" + 
	"dataConsegna = #{dataConsegna}" + 
	" where idCell = #{idCell}")
	int update(Noleggio noleggio);

	@Select("select * from Noleggio")
	List<Noleggio> findAll();


}
