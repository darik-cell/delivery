package com.myapp.delivery.repository;
import com.myapp.delivery.domain.menu_item.Category;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import java.sql.*;

public class CategoryTypeHandler extends BaseTypeHandler<Category> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, Category parameter, JdbcType jdbcType) throws SQLException {
    ps.setString(i, parameter.getDbValue());
  }

  @Override
  public Category getNullableResult(ResultSet rs, String columnName) throws SQLException {
    String dbValue = rs.getString(columnName);
    return Category.fromDbValue(dbValue); // Преобразование из строки в Category
  }

  @Override
  public Category getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    String dbValue = rs.getString(columnIndex);
    return Category.fromDbValue(dbValue);
  }

  @Override
  public Category getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    String dbValue = cs.getString(columnIndex);
    return Category.fromDbValue(dbValue);
  }
}
