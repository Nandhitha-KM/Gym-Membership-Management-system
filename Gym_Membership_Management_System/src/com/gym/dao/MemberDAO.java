package com.gym.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gym.bean.Member;
import com.gym.util.DBUtil;

public class MemberDAO {

    public Member findMember(String memberID) {
        Connection connection = DBUtil.getDBConnection();
        String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID=?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, memberID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Member m = new Member();
                m.setMemberID(rs.getString(1));
                m.setFullName(rs.getString(2));
                m.setDob(rs.getDate(3));
                m.setPhone(rs.getString(4));
                m.setEmail(rs.getString(5));
                m.setStatus(rs.getString(6));
                return m;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Member> viewAllMembers() {
        List<Member> list = new ArrayList<>();
        Connection connection = DBUtil.getDBConnection();
        String query = "SELECT * FROM MEMBER_TBL";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Member m = new Member();
                m.setMemberID(rs.getString(1));
                m.setFullName(rs.getString(2));
                m.setDob(rs.getDate(3));
                m.setPhone(rs.getString(4));
                m.setEmail(rs.getString(5));
                m.setStatus(rs.getString(6));
                list.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean insertMember(Member member) {
        Connection connection = DBUtil.getDBConnection();
        String query = "INSERT INTO MEMBER_TBL VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, member.getMemberID());
            ps.setString(2, member.getFullName());
            ps.setDate(3, new Date(member.getDob().getTime()));
            ps.setString(4, member.getPhone());
            ps.setString(5, member.getEmail());
            ps.setString(6, member.getStatus());

            int rows = ps.executeUpdate();
            if (rows > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteMember(String memberID) {
        Connection connection = DBUtil.getDBConnection();
        String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID=?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, memberID);
            int rows = ps.executeUpdate();
            if (rows > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
