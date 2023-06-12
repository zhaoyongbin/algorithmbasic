package com.example.java.auditAspect.save.impl;

import com.example.java.User;
import com.example.java.auditAspect.save.AuditLogSaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DefaultAuditLogSaver implements AuditLogSaver {
    private Logger LOG = LoggerFactory.getLogger(DefaultAuditLogSaver.class);

    @Autowired
    @Qualifier("auditLogDataSourceOracle")
    private DataSource dataSource;

    public DefaultAuditLogSaver() {
    }

    @Override
    public Integer saveRequestParam(User userInfo, Date date, String requestURI, String description, String requestParamJSONstr, String fileStoragePath) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Integer logId = null;
        String seqSql = "select bip_index_sql.nextval from dual";
        try {
            conn = this.dataSource.getConnection();
            PreparedStatement pst = conn.prepareStatement(seqSql);
            ResultSet rs1 = pst.executeQuery();
            rs1.next();
            logId = rs1.getInt(1);
            String addAuditLogSQL = "insert into tb_ci_1122(user_name,user_id,user_account,uri) values(?,?,?)";
            ps = conn.prepareStatement(addAuditLogSQL, 1);
            ps.setString(1, userInfo.getUserName());
            ps.setInt(2, userInfo.getUserId());
            ps.setString(3, "");
            ps.executeUpdate();

        } catch (SQLException e) {
            LOG.error("添加审计日志出现异常", e);
            e.printStackTrace();
        } finally {
            this.release(conn, ps, resultSet);
        }
        return logId;

    }

    @Override
    public void saveException(Integer logId, Date date, String exInfo) {
        String updateSql = "update tb_ci_1122 set end_time=?,ex_info=? where index_id=?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = this.dataSource.getConnection();
            ps = conn.prepareStatement(updateSql);
            ps.setObject(1, new java.sql.Timestamp(date.getTime()));
            ps.setString(2, exInfo);
            ps.setInt(3, logId);
            ps.executeUpdate();
        } catch (Exception e) {
            LOG.error("添加审计日志出现异常", e);
        } finally {
            this.release(conn, ps, null);
        }


    }

    @Override
    public void saveResponse(Integer logId, Date endTime, String response) {
        String updateSQL = "update tb_audit_log set end_time=?,response=? where id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = this.dataSource.getConnection();
            ps = conn.prepareStatement(updateSQL);
            ps.setObject(1, endTime);
            ps.setString(2, response);
            ps.setInt(3, logId);
            ps.executeUpdate();
        } catch (Exception e) {
            LOG.error("添加审计日志出现异常", e);
        } finally {
            this.release(conn, ps, null);
        }
    }

    private void release(Connection conn, PreparedStatement ps, ResultSet resultSet) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {

            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
