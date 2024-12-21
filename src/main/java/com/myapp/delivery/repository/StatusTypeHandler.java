package com.myapp.delivery.repository;

import com.myapp.delivery.domain.order.Status;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusTypeHandler extends BaseTypeHandler<Status> {
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, Status parameter, JdbcType jdbcType) throws SQLException {
    ps.setString(i, parameter.getDbValue());
  }

  @Override
  public Status getNullableResult(ResultSet rs, String columnName) throws SQLException {
    String dbValue = rs.getString(columnName);
    return Status.fromDbValue(dbValue);
  }

  @Override
  public Status getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    String dbValue = rs.getString(columnIndex);
    return Status.fromDbValue(dbValue);
  }

  @Override
  public Status getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    String dbValue = cs.getString(columnIndex);
    return Status.fromDbValue(dbValue);
  }
}
