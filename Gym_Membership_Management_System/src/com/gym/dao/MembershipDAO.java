package com.gym.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gym.bean.Membership;
import com.gym.util.DBUtil;

public class MembershipDAO {

    public int generateMembershipID() {
        Connection connection = DBUtil.getDBConnection();
        String query = "SELECT TRANSACTIONID_SEQ.NEXTVAL FROM DUAL";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public boolean recordMembership(Membership membership) {
        Connection connection = DBUtil.getDBConnection();
        String query = "INSERT INTO MEMBERSHIP_TBL VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, membership.getMembershipID());
            ps.setString(2, membership.getMemberID());
            ps.setString(3, membership.getMembershipType());
            ps.setDate(4, new Date(membership.getStartDate().getTime()));
            ps.setDate(5, new Date(membership.getEndDate().getTime()));
            ps.setString(6, membership.getStatus());

            int rows = ps.executeUpdate();
            if (rows > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean cancelMembership(int membershipID) {
        Connection connection = DBUtil.getDBConnection();
        String query = "UPDATE MEMBERSHIP_TBL SET STATUS='CANCELLED' WHERE MEMBERSHIP_ID=?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, membershipID);
            int rows = ps.executeUpdate();
            if (rows > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean hasActiveMembership(String memberID) {
        Connection connection = DBUtil.getDBConnection();
        String query = "SELECT COUNT(*) FROM MEMBERSHIP_TBL WHERE MEMBER_ID=? AND STATUS='ACTIVE'";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, memberID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int getActiveMembershipCount(String membershipType) {
        Connection connection = DBUtil.getDBConnection();
        String query = "SELECT COUNT(*) FROM MEMBERSHIP_TBL WHERE MEMBERSHIP_TYPE=? AND STATUS='ACTIVE'";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, membershipType);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
