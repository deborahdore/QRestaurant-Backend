package com.certimetergroup.qrestaurant.mapper;

import com.certimetergroup.qrestaurant.model.Manager2Society;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Manager2SocietyMapper {

    @Select("SELECT * FROM Manager2Society WHERE idManager2Society=#{idManager2Society}")
    Manager2Society getManager2Society(@Param("idManager2Society") Integer idManager2Society);

    @Select("SELECT * FROM Manager2Society WHERE idManagerFK=#{idManager} and idSocietyFK=#{idSociety}")
    Manager2Society getManager2SocietyByIDManagerAndIDSociety(@Param("idManager") Integer idManager, @Param("idSociety") Integer idSociety);

    @Insert("INSERT INTO Manager2Society(idManagerFK, idSocietyFK) VALUES (#{manager2Society.idManagerFK}, #{manager2Society.idSocietyFK})")
    @Options(useGeneratedKeys = true, keyProperty = "idManager2Society", keyColumn = "idManager2Society")
    int postManager2Society(@Param("manager2Society") Manager2Society manager2Society);

    @Update("UPDATE Manager2Society SET idManagerFK=#{manager2Society.idManagerFK}, idSocietyFK=#{manager2Society.idSocietyFK} " +
            "WHERE idManager2Society=#{manager2Society.idManager2Society}")
    int putManager2Society(@Param("manager2Society") Manager2Society manager2Society);

    @Delete("DELETE FROM Manager2Society WHERE idManager2Society=#{manager2Society.idManager2Society}")
    int deleteManager2Society(@Param("manager2Society") Manager2Society manager2Society);

    @Delete("DELETE FROM Manager2Society WHERE idManager2Society=#{idManager2Society}")
    int deleteManager2SocietyByID(@Param("idManager2Society") Integer idManager2Society);

    @Select("SELECT idManagerFK FROM Manager2Society WHERE idSocietyFK=#{idSociety}")
    List<Integer> getManagersOfSociety(@Param("idSociety") Integer idSociety);

    @Select("SELECT idSocietyFK FROM Manager2Society WHERE idManagerFK=#{idManager}")
    List<Integer> getSocietiesOfManager(@Param("idManager") Integer idManager);
}
