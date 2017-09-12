package com.nttdata.database;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nttdata.model.Telefoni;

@Mapper
public interface TelefonoMapper {
	
	@Select("select * from Telefoni where idCell = #{idCell}")
	Telefoni findByIdCell(@Param("idCell") int idCell) ;

	@Insert("insert into telefoni (numero, tipo) values (#{numero}, #{tipo})")
	@Options(useGeneratedKeys = true, keyProperty = "idCell")
	int add(Telefoni telefono);

	@Delete("delete Telefoni where idCell = #{idCell}")
	int delete(@Param("idCell") int idCell);

	@Update("update Telefoni set numero = #{numero}," + " tipo = #{tipo}," +
	" where idCell = #{idCell}")
	int update(Telefoni telefono);

	@Select("select * from Telefoni")
	List<Telefoni> findAll();

}
