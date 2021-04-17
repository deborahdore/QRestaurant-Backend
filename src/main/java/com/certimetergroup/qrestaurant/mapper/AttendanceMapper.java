package com.certimetergroup.qrestaurant.mapper;

import com.certimetergroup.qrestaurant.model.Attendance;
import com.certimetergroup.qrestaurant.model.Client;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface AttendanceMapper {

    @Select("SELECT * FROM Attendance WHERE idAttendance=#{idAttendance}")
    Attendance getAttendance(@Param("idAttendance") Integer idAttendance);

    @Insert("INSERT INTO Attendance(arrivalTime, idSocietyFK, idClientFK) values (#{attendance.arrivalTime}, #{attendance.idSocietyFK}, #{attendance.idClientFK})")
    @Options(useGeneratedKeys = true, keyProperty = "idAttendance", keyColumn = "idAttendance")
    int postAttendance(@Param("attendance") Attendance attendance);

    @Update("UPDATE Attendance SET arrivalTime=#{attendance.arrivalTime}, idSocietyFK=#{attendance.idSocietyFK}, " +
            "idClientFK=#{attendance.idClientFK} WHERE idAttendance=#{attendance.idAttendance}")
    int putAttendance(@Param("attendance") Attendance attendance);

    @Delete("DELETE FROM Attendance WHERE idAttendance=#{attendance.idAttendance}")
    int deleteAttendance(@Param("attendance") Attendance attendance);

    @Delete("DELETE FROM Attendance WHERE idAttendance=#{idAttendance}")
    int deleteAttendanceByID(@Param("idAttendance") Integer idAttendance);

    @Select("SELECT * FROM Attendance WHERE idClientFK=#{idClient} and arrivalTime >= #{selectPassedDate}")
    List<Attendance> getClientAttendancesFrom(@Param("idClient") Integer idClient, @Param("selectPassedDate") Timestamp selectPassedDate);

    @Select("Select * FROM Attendance WHERE idClientFK=#{idClientFK} and arrivalTime >= #{selectPassedDate}")
    List<Attendance> getFrequentedSocieties(@Param("idClientFK") Integer idClientFK, @Param("selectPassedDate") Timestamp selectPassedDate);

    @Select("SELECT * FROM Client, Attendance WHERE Client.idClient=Attendance.idClientFK " +
            "and Attendance.idSocietyFK=#{idSociety} and Attendance.arrivalTime >= #{arrivalTime}")
    List<Client> getClientListInSocietyFrom(@Param("idSociety") Integer idSociety, @Param("arrivalTime") Timestamp arrivalTime);

    @Select("SELECT * FROM Attendance WHERE Attendance.idSocietyFK=#{idSociety} and " +
            "EXTRACT(DAY FROM Attendance.arrivalTime) =  EXTRACT(DAY FROM #{day}) and " +
            "EXTRACT(MONTH FROM Attendance.arrivalTime) =  EXTRACT(MONTH FROM #{day}) and " +
            "EXTRACT(YEAR FROM Attendance.arrivalTime) =  EXTRACT(YEAR FROM #{day});")
    List<Attendance> getAttendanceClientsInSocietyIn(@Param("idSociety") Integer idSociety, @Param("day") Timestamp day);

    @Select("SELECT * FROM Attendance WHERE idClientFK=#{idClientFK}")
    List<Attendance> getClientAttendances(@Param("idClientFK") Integer idClientFK);

    @Select("SELECT * FROM Attendance WHERE idSocietyFK=#{idSociety}")
    List<Attendance> getAttendanceClientsInSociety(@Param("idSociety") Integer idSociety);
}
