package com.nttdata.database;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.nttdata.model.AutoreLibro;

@Mapper
public interface AutoreLibroMapper {

	@Select("select * from AutoreLibro where idLibro = #{idLibro}")
	AutoreLibro findByIdLibro(@Param("idLibro") int idLibro);
	
	@Select("select * from AutoreLibro where idAutore = #{idAutore}")
	AutoreLibro findByIdAutore(@Param("idAutore") int idAutore);
	
	@Select("select * from AutoreLibro where idAutore = #{idAutore} AND idLibro = #{idLibro}")
	AutoreLibro findByIdAutoreIdLibro(@Param("idAutore") int idAutore,@Param("idLibro") int idLibro);
	
	@Insert("insert into AutoreLibro (idAutore, idLibro) values (#{idAutore},#{idLibro})")
	@Options(useGeneratedKeys = true, keyProperty = "idAutore")
	int add(AutoreLibro autoreLibro);
	
	@Delete("delete AutoreLibro where idAutore = #{idAutore} AND idLibro = #{idLibro}")
	int delete(@Param("idAutore") int idAutore,@Param("idLibro") int idLibro);
	
	@Select("select * from AutoreLibro")
	List<AutoreLibro> findAll();
	
	
}

