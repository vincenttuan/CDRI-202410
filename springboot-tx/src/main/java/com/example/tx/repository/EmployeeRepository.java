package com.example.tx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.tx.dto.UsernameLeaveDaysDTO;
import com.example.tx.entity.Employee;
import com.example.tx.entity.LeaveRequest;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	@Query(value = "select username from employee", nativeQuery = true)
	List<String> queryAllNames();
	
	@Query(value = "select username from employee where id=:id", nativeQuery = true)
	String getUsernameById(Integer id);
	
	
	@Query(value = """
		    SELECT e.username, 
		           COALESCE(SUM(DATEDIFF(l.end_date, l.start_date) + 1), 0) AS total_leave_days
		    FROM employee e
		    LEFT JOIN leave_request l ON e.id = l.employee_id
		    GROUP BY e.id, e.username
		""", nativeQuery = true)
		List<Object[]> queryUsernamesWithLeaveDays();


}
