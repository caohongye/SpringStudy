package spring_dbcp;

import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate=jdbcTemplate;
	}
	public void update(User user){
		String sql="update t_user set username=?,password=? where id=?";
		Object[] args={user.getUsername(),user.getPassword(),user.getId()};
		jdbcTemplate.update(sql,args);
	}
}
