package dao;

import javax.sql.DataSource;

import logic.Sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SaleDaoImpl implements SaleDao {

	private static final String SELECT_MAX_SALEID = "SELECT MAX(sale_id) AS sale_id FROM sale";

	private static final String INSERT = "INSERT INTO sale(sale_id, user_id, update_time) VALUES(?, ?, ?)";

	private SimpleJdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new SimpleJdbcTemplate(dataSource);
	}

	public void create(Sale sale) {
		this.template.update(SaleDaoImpl.INSERT, sale.getSaleId(), sale.getUser().getUserId(), sale.getUpdateTime());
	}

	public Integer findMaxSaleId() {
		return this.template.queryForInt(SELECT_MAX_SALEID);
	}
}