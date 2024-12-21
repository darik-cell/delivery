package com.myapp.delivery.repository;

import com.myapp.delivery.domain.order.PaymentStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentStatusTypeHandler extends BaseTypeHandler<PaymentStatus> {
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, PaymentStatus parameter, JdbcType jdbcType) throws SQLException {
    ps.setString(i, parameter.getDbValue());
  }

  @Override
  public PaymentStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
    String dbValue = rs.getString(columnName);
    return PaymentStatus.fromDbValue(dbValue);
  }

  @Override
  public PaymentStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    String dbValue = rs.getString(columnIndex);
    return PaymentStatus.fromDbValue(dbValue);
  }

  @Override
  public PaymentStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    String dbValue = cs.getString(columnIndex);
    return PaymentStatus.fromDbValue(dbValue);
  }
}
