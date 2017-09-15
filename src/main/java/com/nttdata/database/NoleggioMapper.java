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
	

	@Select("select * from Noleggio where idNoleggio = #{idNoleggio}")
	Noleggio findByIdNoleggio(@Param("idNoleggio") int idNoleggio) ;

	@Insert("insert into Noleggio (idUtente, idLibro, dataPrelievo, dataConsegna) values (#{idUtente}, #{idLibro}, #{dataPrelievo}, #{dataConsegna})")
	@Options(useGeneratedKeys = true, keyProperty = "idNoleggio")
	int add(Noleggio noleggio);

	@Delete("delete Noleggio where idNoleggio = #{idNoleggio}")
	int delete(@Param("idNoleggio") int idNoleggio);

	@Update("update Noleggio set idUtente = #{idUtente},"
			+ " idLibro = #{idLibro},"
			+ "dataPrelievo = #{dataPrelievo},"
			+ "dataConsegna = #{dataConsegna}" + 
			" where idNoleggio = #{idNoleggio}")
	int update(Noleggio noleggio);

	@Select("select * from Noleggio where idUtente = #{idUtente}")
	List<Noleggio> findAllByUtente(@Param ("idUtente") int idUtente);

	@Select("select * from Noleggio")
	List<Noleggio> findAll();

}
