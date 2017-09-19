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
	
	@Select("select * from Telefoni where idCell = #{idCell} AND idUtente = #{idUtente}")
	Telefoni findByIdCell(@Param("idCell") int idCell, @Param("idUtente") int idUtente) ;

	@Insert("insert into Telefoni(numero, tipo, idUtente) values (#{numero}, #{tipo},#{idUtente})")
	@Options(useGeneratedKeys = true, keyProperty = "idCell")
	int add(Telefoni telefono);

	@Delete("delete Telefoni where idCell = #{idCell}")
	int delete(@Param("idCell") int idCell);

	@Update("update Telefoni set numero = #{numero}," 
	+ " tipo = #{tipo}" 
	+	" where idCell = #{idCell} and idUtente = #{idUtente}")
	int update(Telefoni telefono);

	@Select("select * from Telefoni where idUtente = #{idUtente}")
	List<Telefoni> findAll(@Param ("idUtente") int idUtente);

}
