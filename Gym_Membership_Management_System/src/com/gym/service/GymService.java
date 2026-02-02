package com.gym.service;

import java.util.Date;
import com.gym.bean.*;
import com.gym.dao.*;
import com.gym.util.*;

public class GymService {

    MemberDAO memberDAO = new MemberDAO();
    MembershipDAO membershipDAO = new MembershipDAO();

    public boolean enrollMembership(String memberID,String type,Date start,Date end) throws Exception {

        if(memberID == null || type == null || start == null || end == null || start.after(end))
            throw new ValidationException();

        Member m = memberDAO.findMember(memberID);
        if(m == null) return false;

        int id = membershipDAO.generateMembershipID();

        Membership ms = new Membership();
        ms.setMembershipID(id);
        ms.setMemberID(memberID);
        ms.setMembershipType(type);
        ms.setStartDate(start);
        ms.setEndDate(end);
        ms.setStatus("ACTIVE");

        boolean r = membershipDAO.recordMembership(ms);
        if(r) {
            DBUtil.getDBConnection();
            return true;
        }
        DBUtil.getDBConnection();
        return false;
    }

    public boolean cancelMembership(int id) throws Exception {
        if(id <= 0) throw new ValidationException();

        boolean r = membershipDAO.cancelMembership(id);
        if(r) {
            DBUtil.getDBConnection();
            return true;
        }
        DBUtil.getDBConnection();
        return false;
    }
}