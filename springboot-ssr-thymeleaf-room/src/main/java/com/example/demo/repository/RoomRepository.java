package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Room;

// Spring JPA
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> { // Room: Entity, Integer: @Id 的型別
	// 預設自動實現 CRUD
	// 自定義查詢
	// 1. 查詢 roomSize 大於指定值的房間(自動生成 SQL)
	List<Room> findByRoomSizeGreaterThan(Integer size);
	
	// 2. 查詢 roomSize 大於指定值的房間(JPQL 以 entity 來操作)
	@Query("select r from Room r where r.roomSize > :size")
	List<Room> findRoomsBySizeGreaterThan1(@Param("size") Integer size);
	
	// 3. 查詢 roomSize 大於指定值的房間(SQL 以 table 來操作)
	@Query(value = "select room_id, room_name, room_size from room where room_size > :size", nativeQuery = true)
	List<Room> findRoomsBySizeGreaterThen2(@Param("size") Integer  size);
}
