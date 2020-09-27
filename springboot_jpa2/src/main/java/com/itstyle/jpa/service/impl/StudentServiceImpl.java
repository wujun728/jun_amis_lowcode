package com.itstyle.jpa.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Service;

import com.itstyle.jpa.dynamicquery.DynamicQuery;
import com.itstyle.jpa.model.AppStudent;
import com.itstyle.jpa.model.Student;
import com.itstyle.jpa.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {
	
	final static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	
	final static String startTime = sdf.format(new Date());
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;//批量插入
	
	@Override
	public List<Object[]> listStudent() {
		String nativeSql = "SELECT s.id AS studentId,c.id AS classId,c.class_name AS className,c.teacher_name AS teacherName,s.name,s.age FROM app_student s,app_class c";
		List<Object[]> list = dynamicQuery.nativeQueryList(nativeSql, new Object[]{});
		return list;
	}
	
	@Override
	public List<Student> listStudentModel() {
		String nativeSql = "SELECT s.id AS studentId,c.id AS classId,c.class_name AS className,c.teacher_name AS teacherName,s.name,s.age FROM app_student s,app_class c";
		List<Student> list = dynamicQuery.nativeQueryListModel(Student.class, nativeSql, new Object[]{});
		return list;
	}

	@Override
	public List<Map<Object,Object>> listStudentMap() {
		String nativeSql = "SELECT s.id AS studentId,c.id AS classId,c.class_name AS className,c.teacher_name AS teacherName,s.name,s.age FROM app_student s,app_class c";
		List<Map<Object,Object>> list = dynamicQuery.nativeQueryListMap(nativeSql, new Object[]{});
		return list;
	}

	@Override
	public void batchSave() {
		List<AppStudent> list = new ArrayList<AppStudent>();
		list.add(new AppStudent(1,"张三",21));
		list.add(new AppStudent(1,"李四",22));
		list.add(new AppStudent(1,"王二麻子",23));
		SqlParameterSource[] beanSources  = SqlParameterSourceUtils.createBatch(list.toArray());
		String sql = "INSERT INTO app_student(class_id,name,age) VALUES (:classId,:name,:age) ";
		namedParameterJdbcTemplate.batchUpdate(sql, beanSources);
	}

	@Override
	public void statsDemo() throws Exception {
		CountDownLatch latch = new CountDownLatch(5);// 两个赛跑者
		Stats stats1 = new Stats("任务A", 1000, latch);
		Stats stats2 = new Stats("任务B", 2000, latch);
		Stats stats3 = new Stats("任务C", 2000, latch);
		Stats stats4 = new Stats("任务D", 2000, latch);
		Stats stats5 = new Stats("任务E", 2000, latch);
		stats1.start();//任务A开始执行
		stats2.start();//任务B开始执行
		stats3.start();//任务C开始执行
		stats4.start();//任务D开始执行
		stats5.start();//任务E开始执行
		latch.await();// 等待所有人任务结束
		System.out.println("所有的统计任务执行完成:" + sdf.format(new Date()));
	}
	static class Stats extends Thread {
		String statsName;
		int runTime;
		CountDownLatch latch;

		public Stats(String statsName, int runTime, CountDownLatch latch) {
			this.statsName = statsName;
			this.runTime = runTime;
			this.latch = latch;
		}

		public void run() {
			try {
				System.out.println(statsName+ " do stats begin at "+ startTime);
				//模拟任务执行时间
				Thread.sleep(runTime);
				System.out.println(statsName + " do stats complete at "+ sdf.format(new Date()));
				latch.countDown();//单次任务结束，计数器减一
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
